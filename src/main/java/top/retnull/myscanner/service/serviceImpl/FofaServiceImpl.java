package top.retnull.myscanner.service.serviceImpl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.r4v3zn.fofa.core.DO.FofaData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import top.retnull.myscanner.entities.FofaHistory;
import top.retnull.myscanner.entities.SysUser;
import top.retnull.myscanner.jwt.JwtLoginUser;
import top.retnull.myscanner.mapper.FofaMapper;
import top.retnull.myscanner.utils.FofaClient;
import top.retnull.myscanner.service.FofaService;
import top.retnull.myscanner.service.SysUserService;
import top.retnull.myscanner.utils.JsonUtils;
import top.retnull.myscanner.utils.SecurityUtils;
import top.retnull.myscanner.vo.ExcelVo;
import top.retnull.myscanner.vo.FofaConfig;
import top.retnull.myscanner.vo.FofaVo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @program: spring-restful-api
 * @description: fofa相关实现类
 * @author: luoyu
 * @create: 2022-01-05 20:47
 **/
@Slf4j
@Service
public class FofaServiceImpl extends BaseServiceImpl<FofaHistory, Integer, FofaMapper> implements FofaService{


    @Autowired
    SysUserService sysUserService;

    @Override
    public Boolean fofaconfig(FofaConfig fofaConfig) {
        //从SecurityContextHolder中获取到，当前登录的用户信息。
        JwtLoginUser userDetails = SecurityUtils.getLoginUser();
        // 根据用户Id，获取用户详细信息。
        SysUser sysUser = sysUserService.findById(userDetails.getUid());
        sysUser.setFofakey(fofaConfig.getToken());
        sysUser.setEmail(fofaConfig.getEmail());
        return sysUserService.updateUer(sysUser);

    }



    @Override
    public FofaVo fofaquery(FofaHistory fofaHistory,Integer page) throws Exception {

       SysUser sysUser = sysUserService.findById(fofaHistory.getUid());
       String email = sysUser.getEmail();
       String token = sysUser.getFofakey();
        FofaClient fofaClient = new FofaClient(email, token);
        FofaData fofaData = fofaClient.getData(fofaHistory.getQuerykey(),page,100,"host,title,ip,domain,port,protocol,server");
        List<List<String>> lists = fofaData.getResults();
        ArrayList<ExcelVo> excelVos = new ArrayList<>();
        for(List<String> temp:lists){
            ExcelVo excelVo = ExcelVo.builder().url(temp.get(0)).host(temp.get(2)).title(temp.get(1)).domain(temp.get(3)).port(Integer.valueOf(temp.get(4))).protocol(temp.get(5)).server(temp.get(6)).build();
            excelVos.add(excelVo);
        }
        Integer rid = null;
        if(page==1){
            rid = create(fofaHistory);
        }else {
             rid = null;
        }
        FofaVo fofaVo =  FofaVo.builder().result(excelVos).page(fofaData.getPage()).totalpages(fofaData.getTotalPage()).size(fofaData.getSize()).rid(rid).build();
        return fofaVo;

    }

    @Override
    public List<FofaHistory> fofahistory() {
        JwtLoginUser userDetails = SecurityUtils.getLoginUser();
        FofaHistory fofaHistory = FofaHistory.builder().uid(userDetails.getUid()).isexported(1).build();
        List<FofaHistory> list = baseMapper.select(fofaHistory);
        return list;

    }

    @Override
    public String export(FofaHistory fofaHistory) throws Exception {

        SysUser sysUser = sysUserService.findById(fofaHistory.getUid());
        String email = sysUser.getEmail();
        String token = sysUser.getFofakey();
        FofaClient fofaClient = new FofaClient(email,token);
        FofaData fofadata = fofaClient.getData(fofaHistory.getQuerykey(),1,10000,"host,title,ip,domain,port,protocol,server");
        List<List<String>> lists = fofadata.getResults();
        ArrayList<ExcelVo> excelVos = new ArrayList<>();
        for(List<String> temp:lists){
            ExcelVo excelVo = ExcelVo.builder().url(temp.get(0)).host(temp.get(2)).title(temp.get(1)).domain(temp.get(3)).port(Integer.valueOf(temp.get(4))).protocol(temp.get(5)).server(temp.get(6)).build();
            excelVos.add(excelVo);
        }
        try {
            String basePath = ResourceUtils.getURL("classpath:").getPath() + "static/";
            String newName = UUID.randomUUID()+".csv";
            File file = new File(basePath, newName);
            ExcelWriter excelWriter = EasyExcel.write(file, ExcelVo.class).build();
            WriteSheet writeSheet = EasyExcel.writerSheet("fofa").build();
            excelWriter.write(excelVos, writeSheet);
            excelWriter.finish();
            fofaHistory.setIsexported(1);
            fofaHistory.setUrl(newName);
            baseMapper.updateByPrimaryKeySelective(fofaHistory);
            return newName;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;

    }
}
