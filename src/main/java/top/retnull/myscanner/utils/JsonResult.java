package top.retnull.myscanner.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * All rights Reserved, Designed By retnull.top
 * <p>
 * RESTful API 统一返回实体类
 * </p>
 *
 * @author retnull
 * @version 2.0
 * @date 2022/1/4
 * 
 */


@Getter
@Setter
public class JsonResult<T> implements Serializable {

    private static final long serialVersionUID = 1456468469856161L;

    /**
     * 返回状态码
     */
    private Integer status;

    /**
     * 返回消息
     */
    private String message;

    /**
     * 返回内容
     */
    private T data;

    private JsonResult() {
    }

    /**
     * 自定义返回状态码
     *
     * @param status
     * @param message
     * @param data
     * @return com.ifsaid.shark.util.JsonResult
     * @author retnull
     * @date 2022/1/4
     */
    private static <T> JsonResult result(int status, String message, T data) {
        JsonResult result = new JsonResult();
        result.status = status;
        result.message = message;
        result.data = data;
        return result;
    }

    /**
     * 自定义返回状态码
     *
     * @param httpStatus
     * @param data
     * @return com.ifsaid.shark.util.JsonResult
     * @author retnull
     * @date 2022/1/4
     */
    private static <T> JsonResult result(HttpStatus httpStatus, T data) {
        return result(httpStatus.status, httpStatus.value, data);
    }

    /**
     * 自定义返回状态码 [不建议使用，建议扩展方法]
     *
     * @param status
     * @param message
     * @return com.ifsaid.shark.util.JsonResult
     * @author retnull
     * @date 2022/1/4
     */
    public static <T> JsonResult result(int status, String message) {
        return result(status, message, null);
    }


    /**
     * 操作成功
     *
     * @return
     * @author retnull
     * @date 2022/1/4
     */
    public static <T> JsonResult success() {
        return success(HttpStatus.SUCCESS.value, null);
    }

    /**
     * 操作成功
     *
     * @param totalPage
     * @param total
     * @param list
     * @author retnull
     * @date 2022/1/4
     */
    public static <T> JsonResult success(int totalPage, long total, List<T> list) {
        return success(new ResultPage<T>(totalPage, total, list));
    }


    /**
     * 操作成功
     *
     * @param total
     * @param list
     * @author retnull
     * @date 2022/1/4
     */
    public static <T> JsonResult success(long total, List<T> list) {
        return success(new ResultPage<T>(list.size(), total, list));
    }

    /**
     * 操作成功
     *
     * @param resultPage
     * @author retnull
     * @date 2022/1/4
     */
    public static <T> JsonResult success(ResultPage<T> resultPage) {
        return success(HttpStatus.SUCCESS.value, resultPage);
    }




    /**
     * 操作成功
     *
     * @param data
     * @return com.ifsaid.shark.util.JsonResult
     * @author retnull
     * @date 2022/1/4
     */
    public static <T> JsonResult success(T data) {
        return success(HttpStatus.SUCCESS.value, data);
    }

    /**
     * 操作成功
     *
     * @param message
     * @param data
     * @return com.ifsaid.shark.util.JsonResult
     * @author retnull
     * @date 2022/1/4
     */
    public static <T> JsonResult success(String message, T data) {
        return result(HttpStatus.SUCCESS.status, message, data);
    }


    /**
     * 操作失败
     *
     * @param message
     * @return com.ifsaid.shark.util.JsonResult
     * @author retnull
     * @date 2022/1/4
     */
    public static <T> JsonResult fail(String message) {
        return result(HttpStatus.FAIL.status, message, null);
    }


    /**
     * 未授权
     *
     * @return com.ifsaid.shark.util.JsonResult
     * @author retnull
     * @date 2022/1/4
     */
    public static <T> JsonResult error401() {
        return result(HttpStatus.UNAUTHORIZED.status, HttpStatus.UNAUTHORIZED.value, null);
    }


    /**
     * 不支持的请求类型
     *
     * @return com.ifsaid.shark.util.JsonResult
     * @author retnull
     * @date 2022/1/4
     */
    public static <T> JsonResult error415() {
        return result(HttpStatus.UNSUPPORTED_MEDIA_TYPE.status, HttpStatus.UNSUPPORTED_MEDIA_TYPE.value, null);
    }


    /**
     * 404 未找到
     *
     * @return com.ifsaid.shark.util.JsonResult
     * @author retnull
     * @date 2022/1/4
     */
    public static <T> JsonResult error404() {
        return result(HttpStatus.NOT_FOUND.status, HttpStatus.NOT_FOUND.value, null);
    }


    /**
     * 服务器内部错误
     *
     * @return com.ifsaid.shark.util.JsonResult
     * @author retnull
     * @date 2022/1/4
     */
    public static <T> JsonResult error500() {
        return result(HttpStatus.INTERNAL_SERVER_ERROR.status, HttpStatus.INTERNAL_SERVER_ERROR.value, null);
    }


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ResultPage<T> {

        /**
         * 当前页，有多少条
         */
        private Integer totalPage;

        /**
         * 总共，有多少条
         */
        private Long total;

        /**
         * 数据
         */
        private List<T> list;

    }

    /**
     * restful api 返回状态码。不够的时候自己扩展
     *
     * @author retnull
     * @return com.ifsaid.shark.util.JsonResult
     * @date 2022/1/4
     */
    @Getter

    public enum HttpStatus {

        /**
         * 正常 返回代码
         *
         * @author retnull
         * @date 2022/1/4
         */
        SUCCESS(200, "操作成功"),

        /**
         * 错误 返回代码
         *
         * @author retnull
         * @date 2022/1/4
         */
        FAIL(100, "操作失败"),

        /**
         * 参数检验失败 返回代码
         *
         * @author retnull
         * @date 2022/1/4
         */
        VALIDATE_FAILED(304, "参数检验失败"),

        /**
         * 404 为找到
         *
         * @author retnull
         * @date 2022/1/4
         */
        NOT_FOUND(404, "Not Found"),

        /**
         * 未经授权
         *
         * @author retnull
         * @date 2022/1/4
         */
        UNAUTHORIZED(401, "你还没有经过授权认证"),

        /**
         * 不支持的媒体类型
         *
         * @author retnull
         * @date 2022/1/4
         */
        UNSUPPORTED_MEDIA_TYPE(415, "不支持此请求类型！"),


        /**
         * 服务器内部错误 返回代码
         *
         * @author retnull
         * @date 2022/1/4
         */
        INTERNAL_SERVER_ERROR(500, "服务器内部错误");

        private int status;

        private String value;

        HttpStatus(int status, String value) {
            this.status = status;
            this.value = value;
        }

    }

}
