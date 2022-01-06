package top.retnull.myscanner.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * All rights Reserved, Designed By retnull.top
 * <p>
 * token 返回值
 * </p>
 *
 * @author retnull
 * @version 2.0
 * @date 2022/1/4
 * 
 */


@Getter
@Setter
@Builder
public class TokenValue implements java.io.Serializable {

    /**
     * @description: 请求头的值
     */
    private String header;

    /**
     * @description: token 值
     */
    private String value;

    /**
     * @description: token 前缀
     */
    private String prefix;

    /**
     * @description: 过期时间（毫秒，这里默认20分钟）
     */
    private Long expiration;

}
