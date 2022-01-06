package top.retnull.myscanner.service;



import top.retnull.myscanner.entities.SysRole;

import java.util.Set;

/**
 * All rights Reserved, Designed By retnull.top
 * <p>
 * 角色 Service 接口
 * </p>
 *
 * @author retnull
 * @version 2.0
 * @date 2022/1/4
 * 
 */


public interface SysRoleService extends BaseService<SysRole, Integer> {

    /**
     * 修改角色的权限
     *
     * @param rid
     * @param permissionIds
     * @return int
     * @author retnull
     * @date 2022/1/4
     */
    int updateRolePermissions(Integer rid, Set<Integer> permissionIds);

    /**
     * 根据 用户id 查出 拥有的 角色
     *
     * @param uid
     * @return Set<SysRole>
     * @author retnull
     * @date 2022/1/4
     */
    Set<SysRole> findAllByUserId(Integer uid);

}
