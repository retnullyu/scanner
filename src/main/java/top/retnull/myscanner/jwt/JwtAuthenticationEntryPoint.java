package top.retnull.myscanner.jwt;


import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import top.retnull.myscanner.utils.JsonResult;
import top.retnull.myscanner.utils.JsonUtils;
import top.retnull.myscanner.utils.ServletUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 授权异常处理
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException e) throws IOException {
        String msg = String.format("请求访问：%s，认证失败，无法访问系统资源", request.getRequestURI());
        JsonResult<String> result = JsonResult.result(HttpStatus.UNAUTHORIZED.value(), msg);
        ServletUtils.renderString(response, JsonUtils.objectToJson(result));
    }

}