package top.retnull.myscanner.mapper;


import top.retnull.myscanner.common.BaseMapper;
import top.retnull.myscanner.entities.Relation;
import top.retnull.myscanner.entities.SysUser;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 用户 Mapper 接口
 * </p>
 */

public interface SysUserMapper extends BaseMapper<SysUser, Integer> {

    /**
     * 根据 用户名，和 昵称，模糊匹配
     *
     * @param keywords
     * @return Set<SysRole>
     * @author retnull
     * @date 2022/1/4
     */
    Set<SysUser> selectByKeywords(String keywords);

    /**
     * 角色关联，多个角色
     *
     * @param record
     * @return int
     * @author retnull
     * @date 2022/1/4
     */
    int insertByRoles(List<Relation> record);

    /**
     * 删除某个用户，拥有的角色
     *
     * @param uid
     * @return int
     * @author retnull
     * @date 2022/1/4
     */
    int deleteHaveRoles(Integer uid);

}
