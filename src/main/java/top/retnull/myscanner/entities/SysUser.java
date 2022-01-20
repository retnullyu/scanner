package top.retnull.myscanner.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * <p>
 * 后台系统用户
 * </p>
 * @author luoyu12
 */


@Entity
@Table(name = "sys_user")
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SysUser extends BaseEntity implements java.io.Serializable {

    /**
     * 用户唯一id
     *
     * @date 2022/1/4
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
     * 密码
     *
     * @date 2022/1/4
     */
    private String password;

    /**
     * 性别
     *
     * @date 2022/1/4
     */
    private Integer gender;

    /**
     * 生日
     *
     * @date 2022/1/4
     */
    private LocalDate birthday;

    /**
     * 状态 [ 0.禁用 1.正常 2.被删除 ]
     *
     * @date 2022/1/4
     */
    private Integer status;

    /**
     * fofa key
     */
    private String fofakey;

}