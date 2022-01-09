package top.retnull.myscanner.controller;

import com.r4v3zn.fofa.core.DO.FofaData;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.retnull.myscanner.entities.FofaHistory;
import top.retnull.myscanner.entities.SysUser;
import top.retnull.myscanner.jwt.JwtLoginUser;
import top.retnull.myscanner.service.FofaService;
import top.retnull.myscanner.service.SysUserService;
import top.retnull.myscanner.utils.JsonResult;
import top.retnull.myscanner.utils.JsonUtils;
import top.retnull.myscanner.utils.SecurityUtils;
import top.retnull.myscanner.vo.ExcelVo;
import top.retnull.myscanner.vo.FofaConfig;
import top.retnull.myscanner.vo.FofaVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: spring-restful-api
 * @description: fofa信息收集controller
 * @author: luoyu
 * @create: 2022-01-05 17:16
 **/
@Slf4j
@Api(tags = "[ 信息收集 ] fofa")
@RestController
@RequestMapping("/information/fofa")
public class FofaController extends BaseController<FofaHistory, Integer, FofaService> {

    @RequestMapping("config")
    public JsonResult fofaconfig(@RequestBody FofaConfig fofaConfig){
        log.info(JsonUtils.objectToJson(fofaConfig));
        if(fofaConfig.getToken()==null){
            return JsonResult.fail("token不能为空");
        }else if(baseService.fofaconfig(fofaConfig)){
            JsonResult jsonResult = JsonResult.success(1);
            log.info(JsonUtils.objectToJson(jsonResult));
            return jsonResult;
        }
        return JsonResult.fail("更新失败");
    }
    @RequestMapping("query")
    public JsonResult<FofaVo> fofaquery(String keyword,String currentPage){
//        keyword = keyword.replace("#","\\\"");
        JwtLoginUser userDetails = SecurityUtils.getLoginUser();
        FofaHistory fofaHistory = FofaHistory.builder().querykey(keyword).uid(userDetails.getUid()).isexported(0).build();
        try {
            FofaData fofaData = baseService.fofaquery(fofaHistory);
            List<List<String>> lists = fofaData.getResults();
            ArrayList<ExcelVo> excelVos = new ArrayList<>();
            for(List<String> temp:lists){
               ExcelVo excelVo = ExcelVo.builder().host(temp.get(0)).title(temp.get(1)).build();
               excelVos.add(excelVo);
            }
            FofaVo fofaVo =  FofaVo.builder().result(excelVos).page(fofaData.getPage()).totalpages(fofaData.getTotalPage()).build();
            return JsonResult.success(fofaVo);

        } catch (Exception e) {
           return JsonResult.fail(e.getMessage());
        }
    }
    @RequestMapping("history")
    public JsonResult fofahistory(){

    return null;
    }

}
