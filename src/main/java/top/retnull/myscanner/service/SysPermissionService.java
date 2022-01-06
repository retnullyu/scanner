package top.retnull.myscanner.service;



import top.retnull.myscanner.entities.SysPermission;

import java.util.Set;


/**
 * All rights Reserved, Designed By retnull.top
 * <p>
 * 权限 Service 接口
 * </p>
 *
 * @author retnull
 * @version 2.0
 * @date 2022/1/4
 * 
 */

public interface SysPermissionService extends BaseService<SysPermission, Integer> {

    /**
     * 根据用户Id，查询拥有权限
     *
     * @param uid
     * @return Set<SysPermission>
     * @author retnull
     * @date 2022/1/4
     */
    Set<SysPermission> findAllByUserId(Integer uid);

    /**
     * 根据角色Id，查询拥有权限
     *
     * @param rid
     * @return Set<Integer>
     * @author retnull
     * @date 2022/1/4
     */
    Set<SysPermission> findPermissionByRoleId(Integer rid);

}
