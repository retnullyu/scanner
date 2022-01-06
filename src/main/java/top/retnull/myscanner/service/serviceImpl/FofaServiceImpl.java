package top.retnull.myscanner.service.serviceImpl;

import com.r4v3zn.fofa.core.DO.FofaData;
import com.r4v3zn.fofa.core.client.FofaClient;
import top.retnull.myscanner.entities.FofaQueryHistory;
import top.retnull.myscanner.entities.SysUser;
import top.retnull.myscanner.mapper.SysUserMapper;
import top.retnull.myscanner.service.BaseService;
import top.retnull.myscanner.service.FofaService;
import top.retnull.myscanner.service.SysUserService;
import top.retnull.myscanner.vo.FofaConfig;

/**
 * @program: spring-restful-api
 * @description: fofa相关实现类
 * @author: luoyu
 * @create: 2022-01-05 20:47
 **/
public class FofaServiceImpl extends BaseServiceImpl<SysUser, Integer, SysUserMapper> implements FofaService{
    @Override
    public Boolean fofaconfig(FofaConfig fofaConfig) {
        String email = fofaConfig.getEmail();
        String token = fofaConfig.getToken();
        FofaClient client = new FofaClient(email,token);
        if(client!=null){

        }
        return null;
    }

    @Override
    public FofaData fofaquery(FofaQueryHistory fofaQueryHistory) {
        return null;
    }

    @Override
    public FofaQueryHistory fofahistory(SysUser sysUser) {
        return null;
    }

    @Override
    public Boolean export(FofaQueryHistory fofaQueryHistory) {
        return null;
    }
}
