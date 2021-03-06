package top.retnull.myscanner.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import top.retnull.myscanner.constant.StatusConstant;
import top.retnull.myscanner.entities.Relation;
import top.retnull.myscanner.entities.SysUser;
import top.retnull.myscanner.jwt.JwtLoginUser;
import top.retnull.myscanner.mapper.SysUserMapper;
import top.retnull.myscanner.service.SysRoleService;
import top.retnull.myscanner.service.SysUserService;
import top.retnull.myscanner.utils.QueryParameter;
import top.retnull.myscanner.utils.SecurityUtils;
import top.retnull.myscanner.vo.ResetPassword;
import top.retnull.myscanner.vo.SysUserVo;
import top.retnull.myscanner.vo.UpdatePassword;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * All rights Reserved, Designed By retnull.top
 * <p>
 * 用户 Service 实现类
 * </p>
 *
 * @author retnull
 * @version 2.0
 * @date 2022/1/4
 * 
 */

@Slf4j
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUser, Integer, SysUserMapper> implements SysUserService {

    @Autowired
    private SysRoleService sysRoleService;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public PageInfo<SysUserVo> findAllPageInfo(QueryParameter parameter) {
        PageInfo<SysUser> pageInfo = PageHelper
                .startPage(parameter.getPageNum(), parameter.getPageSize())
                .doSelectPageInfo(() -> {
                    if (StringUtils.isEmpty(parameter.getKeywords())) {
                        baseMapper.selectAll();
                    } else {
                        baseMapper.selectByKeywords(parameter.getKeywords());
                    }
                });
        List<SysUserVo> collect = pageInfo.getList().stream()
                .map(res -> {
                    SysUserVo sysUserVo = new SysUserVo();
                    BeanUtils.copyProperties(res, sysUserVo);
                    List<SysUserVo.RoleVo> roles = sysRoleService.findAllByUserId(res.getUid())
                            .stream()
                            .map(role -> {
                                return new SysUserVo.RoleVo(role.getRid(), role.getRoleName(), role.getDescription());
                            })
                            .collect(Collectors.toList());
                    sysUserVo.setRoles(roles);
//                    String departmentName = departmentService.findById(res.getDeptId()).getName();
//                    sysUserVo.setDepartmentName(departmentName);
                    return sysUserVo;
                }).collect(Collectors.toList());
        PageInfo<SysUserVo> result = new PageInfo<>();
        result.setList(collect);
        result.setTotal(pageInfo.getTotal());
        return result;
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteById(Integer uid) {
        if (SecurityUtils.isAdmin(uid)) {
            throw new RuntimeException("此用户是管理员，无法删除！");
        }
        // 删除用户拥有的角色
        baseMapper.deleteHaveRoles(uid);
        return baseMapper.deleteByPrimaryKey(uid);
    }

    @Override
    public SysUser findById(Integer uid) {
        SysUser sysUser = baseMapper.selectByPrimaryKey(uid);
        sysUser.setPassword(null);
        return sysUser;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int create(SysUser entity) {
        String encodePassword = passwordEncoder.encode(entity.getPassword());
        entity.setPassword(encodePassword);
        entity.setStatus(StatusConstant.NORMAL);
        entity.setCreateTime(LocalDateTime.now());
        entity.setLastUpdateTime(LocalDateTime.now());
        return baseMapper.insertSelective(entity);
    }

    /**
     * 修改用户权限
     *
     * @param uid
     * @param roleIds
     * @return int
     */
    @Override
    public int updateUserRoles(Integer uid, Set<Integer> roleIds) {
        List<Relation> collect = roleIds.stream()
                // 去除重复的角色ID
                .distinct()
                // 构造 Relation 对象
                .map(res -> new Relation(uid, res))
                .collect(Collectors.toList());
        // 先删除当前用户拥有的所有角色
        baseMapper.deleteHaveRoles(uid);
        // 在赋值新的角色
        return baseMapper.insertByRoles(collect);
    }

    @Override
    public int updatePassword(UpdatePassword pwd) {
        SysUser sysUser = baseMapper.selectByPrimaryKey(SecurityUtils.getLoginUser().getUid());
        if (!pwd.getNewPwd().equals(pwd.getConfirmPwd())) {
            throw new RuntimeException("新密码与确认密码不一致，请重新输入！");
        }
        if (passwordEncoder.matches(pwd.getOldPwd(), sysUser.getPassword())) {
            if (passwordEncoder.matches(pwd.getNewPwd(), sysUser.getPassword())) {
                throw new RuntimeException("新密码与旧密码相同，请重新输入！");
            } else {
                String newPassword = passwordEncoder.encode(pwd.getNewPwd());
                return super.update(SysUser.builder().uid(sysUser.getUid()).password(newPassword).build());
            }
        }
        throw new RuntimeException("旧密码错误，请重新输入！");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int resetPassword(ResetPassword pwd) {

        SysUser sysUser = baseMapper.selectByPrimaryKey(pwd.getUid());
        if (sysUser != null) {
            String newPassword = passwordEncoder.encode(pwd.getNewPwd());
            return baseMapper.updateByPrimaryKeySelective(
                    SysUser.builder().uid(pwd.getUid()).password(newPassword).build()
            );
        }
        throw new RuntimeException("用户不存在，无法重置密码！");
    }

    @Override
    public int update(SysUser entity) {
        // 不允许使用 update 修改密码！
        entity.setPassword(null);
        entity.setAvatar(null);
        entity.setUid(SecurityUtils.getLoginUser().getUid());
        return super.update(entity);

    }
    @Override
    public Boolean updateUer(SysUser entity){
        return baseMapper.updateByPrimaryKeySelective(entity)>0;
    }
}
