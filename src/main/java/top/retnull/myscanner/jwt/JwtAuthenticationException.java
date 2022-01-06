package top.retnull.myscanner.jwt;

import org.springframework.security.core.AuthenticationException;

/**
 * <p>
 *  jwt 认证异常
 * </p>
 */

public class JwtAuthenticationException extends AuthenticationException {

    public JwtAuthenticationException(String msg, Throwable t) {
        super(msg, t);
    }

    public JwtAuthenticationException(String msg) {
        super(msg);
    }

}
