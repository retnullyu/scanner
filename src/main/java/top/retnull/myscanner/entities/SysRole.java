package top.retnull.myscanner.entities;

import lombok.*;

import javax.persistence.*;


/**
 * <p>
 * 后台系统角色
 * </p>
 */


@Entity
@Table(name = "sys_role")
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SysRole extends BaseEntity implements java.io.Serializable {

    /**
     * @description: 角色ID
     * @date 2022/1/4
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rid;

    /**
     * @description: 角色 名称
     * @date 2022/1/4
     */
    private String roleName;

    /**
     * @description: 角色 描述
     * @date 2022/1/4
     */
    private String description;

}