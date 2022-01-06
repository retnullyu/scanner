package top.retnull.myscanner.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.retnull.myscanner.utils.JsonResult;
import top.retnull.myscanner.vo.FofaConfig;

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
public class FofaController {


    @RequestMapping("config")
    public JsonResult fofaconfig(FofaConfig fofaConfig){
        if(fofaConfig.getToken()!=null){

        }


    }
    @RequestMapping("query")
    public JsonResult fofaquery(){


    }
    @RequestMapping("history")
    public JsonResult fofahistory(){


    }

}
