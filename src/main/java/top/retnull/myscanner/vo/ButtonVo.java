package top.retnull.myscanner.vo;

import lombok.*;


/**
 * <p>
 * 用户登录成功后，返回的按钮权限
 * </p>
 */


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ButtonVo implements java.io.Serializable {

    private Integer pid;

    private String resources;

    private String title;


}
