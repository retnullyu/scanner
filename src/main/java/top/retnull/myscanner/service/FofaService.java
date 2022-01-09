package top.retnull.myscanner.service;

import com.r4v3zn.fofa.core.DO.FofaData;
import top.retnull.myscanner.entities.FofaHistory;
import top.retnull.myscanner.entities.SysUser;
import top.retnull.myscanner.vo.FofaConfig;


/**
 * @program: spring-restful-api
 * @description: Fofa相关服务
 * @author: luoyu
 * @create: 2022-01-05 20:12
 **/
public interface FofaService extends BaseService<FofaHistory, Integer> {


    /**
     * fofa配置类
     * @param fofaConfig
     * @return
     */
    Boolean fofaconfig(FofaConfig fofaConfig);


    FofaData fofaquery(FofaHistory fofaQueryHistory) throws Exception;

     FofaHistory fofahistory(SysUser sysUser);

     Boolean export(FofaHistory fofaQueryHistory);


}
