package top.retnull.myscanner.mapper;
import top.retnull.myscanner.common.BaseMapper;
import top.retnull.myscanner.entities.SysPermission;

import java.util.Set;

/**
 * <p>
 * 权限 Mapper 接口
 * </p>
 * @author luoyu12
 */

public interface SysPermissionMapper extends BaseMapper<SysPermission, Integer> {

    /**
     * 根据 用户ID 获取拥有的权限
     *
     * @param uid
     * @return Set<SysPermission>
     * @author retnull
     * @date 2022/1/4
     */
    Set<SysPermission> findAllByUserId(Integer uid);

    /**
     * 根据 角色ID 获取拥有的权限
     *
     * @param rid
     * @return Set<SysPermission>
     * @author retnull
     * @date 2022/1/4
     */
    Set<SysPermission> findAllByRoleId(Integer rid);

    /**
     * 判断是否某个权限下，是否还有拥有子权限
     *
     * @param pid
     * @return Integer
     * @throws
     * @author retnull
     * @date 2022/1/4
     */
    Integer haveChildren(Integer pid);


    /**
     * 判断是否某个权限，是否还被其他角色引用
     *
     * @param pid
     * @return Integer
     * @author retnull
     * @date 2022/1/4
     */
    Integer roleReference(Integer pid);

}
