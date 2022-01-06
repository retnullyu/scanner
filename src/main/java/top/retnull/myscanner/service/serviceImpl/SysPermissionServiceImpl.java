package top.retnull.myscanner.service.serviceImpl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.retnull.myscanner.entities.SysPermission;
import top.retnull.myscanner.mapper.SysPermissionMapper;
import top.retnull.myscanner.service.SysPermissionService;

import java.util.Set;

/**
 * All rights Reserved, Designed By retnull.top
 * <p>
 * 权限 Service 实现类
 * </p>
 *
 * @author retnull
 * @version 2.0
 * @date 2022/1/4
 * 
 */

@Slf4j
@Service
public class SysPermissionServiceImpl extends BaseServiceImpl<SysPermission, Integer, SysPermissionMapper> implements SysPermissionService {

    @Override
    public Set<SysPermission> findAllByUserId(Integer uid) {
        return baseMapper.findAllByUserId(uid);
    }

    @Override
    public Set<SysPermission> findPermissionByRoleId(Integer rid) {
        return baseMapper.findAllByRoleId(rid);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteById(Integer id) {
        if (baseMapper.haveChildren(id) > 0) {
            throw new RuntimeException("当前权限下，还有子权限，无法删除！");
        }
        if (baseMapper.roleReference(id) > 0) {
            throw new RuntimeException("当前权限，还有其他角色在引用，无法删除！");
        }
        return baseMapper.deleteByPrimaryKey(id);
    }

}
