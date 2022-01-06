package top.retnull.myscanner.jwt;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import top.retnull.myscanner.utils.JsonResult;
import top.retnull.myscanner.utils.JsonUtils;
import top.retnull.myscanner.utils.ServletUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 无权限访问资源控制器
 *
 * @author retnull
 * @date 2022/1/4
 */
@Slf4j
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException e) throws IOException {
        String msg = String.format("请求访问：%s，权限不足，请联系管理员", request.getRequestURI());
        JsonResult result = JsonResult.result(HttpStatus.FORBIDDEN.value(), msg);
        ServletUtils.renderString(response, JsonUtils.objectToJson(result));
    }

}