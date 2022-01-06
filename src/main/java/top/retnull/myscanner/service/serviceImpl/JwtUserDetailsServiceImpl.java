package top.retnull.myscanner.service.serviceImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.retnull.myscanner.entities.SysUser;
import top.retnull.myscanner.jwt.JwtLoginUser;
import top.retnull.myscanner.service.SysUserService;


/**
 * <p>
 * spring security 认证 UserDetailsService 实现类
 * </p>
 */


@Slf4j
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {
    //从数据库中取出信息给UserDetails(经过认证过的对象）
    @Autowired
    private SysUserService sysUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = sysUserService.find(SysUser.builder().username(username).build());
        if (user == null || StringUtils.isEmpty(user.getUid())) {
            throw new UsernameNotFoundException(String.format("'%s'.这个用户不存在", username));
        } else {
            return new JwtLoginUser(user.getUid(), null, user.getUsername(), user.getPassword(), user.getStatus(), null);
        }
    }


}
