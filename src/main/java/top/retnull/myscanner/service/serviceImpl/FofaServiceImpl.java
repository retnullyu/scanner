package top.retnull.myscanner.service.serviceImpl;

import com.r4v3zn.fofa.core.DO.FofaData;
import com.r4v3zn.fofa.core.client.FofaClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.retnull.myscanner.entities.FofaHistory;
import top.retnull.myscanner.entities.SysUser;
import top.retnull.myscanner.jwt.JwtLoginUser;
import top.retnull.myscanner.mapper.FofaMapper;
import top.retnull.myscanner.mapper.SysUserMapper;
import top.retnull.myscanner.service.BaseService;
import top.retnull.myscanner.service.FofaService;
import top.retnull.myscanner.service.SysUserService;
import top.retnull.myscanner.utils.JsonUtils;
import top.retnull.myscanner.utils.SecurityUtils;
import top.retnull.myscanner.vo.FofaConfig;

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
    public FofaData fofaquery(FofaHistory fofaHistory) throws Exception {

       SysUser sysUser = sysUserService.findById(fofaHistory.getUid());
       String email = sysUser.getEmail();
       String token = sysUser.getFofakey();
       FofaClient fofaClient = new FofaClient(email,token);
        FofaData fofaData = fofaClient.getData(fofaHistory.getQuerykey(),1,100,"ip,title");
        log.info(JsonUtils.objectToJson(fofaData));
        if(create(fofaHistory)>0){
            log.info("插入成功");
        }
        return fofaData;

    }

    @Override
    public FofaHistory fofahistory(SysUser sysUser) {
        return null;
    }

    @Override
    public Boolean export(FofaHistory fofaHistory) {
        return null;
    }
}
