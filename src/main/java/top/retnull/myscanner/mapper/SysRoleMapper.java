package top.retnull.myscanner.mapper;

import top.retnull.myscanner.common.BaseMapper;
import top.retnull.myscanner.entities.Relation;
import top.retnull.myscanner.entities.SysRole;

import java.util.List;
import java.util.Set;


/**

 * <p>
 * 角色 Mapper 接口
 * </p>
 */

public interface SysRoleMapper extends BaseMapper<SysRole, Integer> {

    /**
     * 根据 用户 ID 查询拥有的角色列表
     *
     * @param uid
     * @return Set<SysRole>
     * @author retnull
     * @date 2022/1/4
     */
    Set<SysRole> findAllByUserId(Integer uid);

    /**
     * 角色关联，多个权限
     *
     * @param record
     * @return int
     * @author retnull
     * @date 2022/1/4
     */
    int insertByPermissions(List<Relation> record);

    /**
     * 删除某个角色，拥有的所有权限
     *
     * @param rid
     * @return int
     * @author retnull
     * @date 2022/1/4
     */
    int deleteHavePermissions(Integer rid);

    /**
     * 判断是否某个角色，是否还被其他用户引用
     *
     * @param rid
     * @return Integer
     * @author retnull
     * @date 2022/1/4
     */
    Integer userReference(Integer rid);

    /**
     * 根据 角色名称，和描述，模糊匹配
     *
     * @param keywords
     * @return Set<SysRole>
     * @author retnull
     * @date 2022/1/4
     */
    Set<SysRole> selectByKeywords(String keywords);

}
