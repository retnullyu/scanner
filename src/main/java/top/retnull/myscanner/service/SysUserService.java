package top.retnull.myscanner.service;

import com.github.pagehelper.PageInfo;
import top.retnull.myscanner.entities.SysUser;
import top.retnull.myscanner.utils.QueryParameter;
import top.retnull.myscanner.vo.ResetPassword;
import top.retnull.myscanner.vo.SysUserVo;
import top.retnull.myscanner.vo.UpdatePassword;

import java.util.Set;

/**
 * All rights Reserved, Designed By retnull.top
 * <p>
 * 用户 Service 接口
 * </p>
 *
 * @author retnull
 * @version 2.0
 * @date 2022/1/4
 * 
 */

public interface SysUserService extends BaseService<SysUser, Integer> {

    /**
     * 修改用户的角色
     *
     * @param uid
     * @param roleIds
     * @return int
     * @author retnull
     * @date 2022/1/4
     */
    int updateUserRoles(Integer uid, Set<Integer> roleIds);

    /**
     * 修改密码
     *
     * @param pwd
     * @return int
     * @author retnull
     * @date 2022/1/4
     */
    int updatePassword(UpdatePassword pwd);

    /**
     * 重置密码
     *
     * @param pwd
     * @return int
     * @author retnull
     * @date 2022/1/4
     */
    int resetPassword(ResetPassword pwd);

    /**
     * 分页擦好像 获取用户详细信息
     *
     * @param parameter
     * @return PageInfo<SysUserVo>
     */
    PageInfo<SysUserVo> findAllPageInfo(QueryParameter parameter);

}
