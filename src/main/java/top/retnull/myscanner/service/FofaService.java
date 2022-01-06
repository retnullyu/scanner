package top.retnull.myscanner.service;

import com.r4v3zn.fofa.core.DO.FofaData;
import top.retnull.myscanner.entities.FofaQueryHistory;
import top.retnull.myscanner.entities.SysUser;
import top.retnull.myscanner.vo.FofaConfig;


/**
 * @program: spring-restful-api
 * @description: Fofa相关服务
 * @author: luoyu
 * @create: 2022-01-05 20:12
 **/
public interface FofaService extends BaseService<SysUser, Integer> {


    /**
     * fofa配置类
     * @param fofaConfig
     * @return
     */
    public Boolean fofaconfig(FofaConfig fofaConfig);


    public FofaData fofaquery(FofaQueryHistory fofaQueryHistory);

    public FofaQueryHistory fofahistory(SysUser sysUser);

    public Boolean export(FofaQueryHistory fofaQueryHistory);


}
