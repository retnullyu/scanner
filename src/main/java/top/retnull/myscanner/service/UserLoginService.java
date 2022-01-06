package top.retnull.myscanner.service;

import org.springframework.security.core.AuthenticationException;
import top.retnull.myscanner.jwt.JwtLoginUser;
import top.retnull.myscanner.vo.TokenValue;
import top.retnull.myscanner.vo.UserVo;

/**
 * All rights Reserved, Designed By retnull.top
 * <p>
 * 用户 登录 Service 接口
 * </p>
 *
 * @author retnull
 * @version 2.0
 * @date 2022/1/4
 * 
 */

public interface UserLoginService {

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @return String token 值
     * @throws AuthenticationException
     */
    TokenValue login(String username, String password) throws AuthenticationException;

    /**
     * 用户退出登录
     *
     * @param loginUser
     */
    void logout();

    /**
     * 刷新 token
     *
     * @return UserVo
     */
    TokenValue refresh();

    /**
     * 校验登录的用户是否存在
     *
     * @param loginId 登录ID
     * @return 操作结果
     * @throws AuthenticationException
     */
    JwtLoginUser validateUser(String loginId) throws AuthenticationException;

    /**
     * 获取用户详细信息
     *
     * @return UserVo
     */
    UserVo findUserInfo();


}
