package top.retnull.myscanner.vo;


import lombok.*;
import top.retnull.myscanner.entities.SysUser;

import java.util.List;


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
@NoArgsConstructor
@AllArgsConstructor
public class SysUserVo extends SysUser implements java.io.Serializable {

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
    private List<RoleVo> roles;

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RoleVo {

        private Integer rid;

        private String roleName;

        private String description;

    }

}
