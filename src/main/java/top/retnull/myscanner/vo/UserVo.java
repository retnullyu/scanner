package top.retnull.myscanner.vo;

import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;


/**
 * All rights Reserved, Designed By retnull.top
 * <p>
 * 登录成功后，用户详细 Vo 模型
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
@NoArgsConstructor
@AllArgsConstructor
public class UserVo implements java.io.Serializable {

    private Integer uid;

    /**
     * 头像
     *
     * @date 2022/1/4
     */
    private String avatar;

    /**
     * 用户名
     *
     * @date 2022/1/4
     */
    private String username;

    /**
     * 邮箱
     *
     * @date 2022/1/4
     */
    private String email;

    /**
     * 昵称
     *
     * @date 2022/1/4
     */
    private String nickname;

    /**
     * 性别
     *
     * @date 2022/1/4
     */
    private Integer gender;

//    /**
//     * 部门名称
//     *
//     * @date 2022/1/4
//     */
//    private String departmentName;

    /**
     * 角色名称，列表
     *
     * @date 2022/1/4
     */
    private Set<String> roles;

    /**
     * 生日
     *
     * @date 2022/1/4
     */
    private LocalDate birthday;

    /**
     * @describe 按钮
     * @date 2022/1/4
     * @author retnull
     */
    private List<ButtonVo> buttons;

    /**
     * @describe 菜单
     * @date 2022/1/4
     * @author retnull
     */
    private List<MenuVo> menus;

}
