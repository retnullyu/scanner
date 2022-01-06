package top.retnull.myscanner.entities;

import lombok.*;

import javax.persistence.*;

/**
 * <p>
 * 后台系统 权限
 * </p>
 * @author luoyu12
 */


@Entity
@Table(name = "sys_permission")
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SysPermission extends BaseEntity implements java.io.Serializable {

    /**
     * @description: 权限 唯一ID
     * @date 2022/1/4
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pid;

    /**
     * @description: 权限 上级权限ID
     * @date 2022/1/4
     */
    private Integer parentId;

    /**
     * @description: 权限 全局资源标识符
     * @date 2022/1/4
     */
    private String resources;

    /**
     * @description: 权限 标题
     * @date 2022/1/4
     */
    private String title;

    /**
     * @description: 权限，如果是菜单的话，那么就是图标名称。如果是按钮，可以不赋值
     * @date 2022/1/4
     */
    private String icon;

    /**
     * @description: 权限，button 或者 menu 只能 二选一
     * @date 2022/1/4
     */
    private String type;

    /**
     * @description: 权限 描述
     * @date 2022/1/4
     */
    private String description;

}