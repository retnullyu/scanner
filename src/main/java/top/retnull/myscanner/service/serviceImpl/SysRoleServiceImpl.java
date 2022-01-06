package top.retnull.myscanner.service.serviceImpl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.retnull.myscanner.entities.Relation;
import top.retnull.myscanner.entities.SysRole;
import top.retnull.myscanner.mapper.SysRoleMapper;
import top.retnull.myscanner.service.SysRoleService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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


@Slf4j
@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole, Integer, SysRoleMapper> implements SysRoleService {

    @Override
    public int updateRolePermissions(Integer rid, Set<Integer> permissionIds) {
        List<Relation> collect = permissionIds.stream()
                // 去除重复的 权限 ID
                .distinct()
                .filter(pid -> pid != null && pid > 0)
                // 构造 Relation 对象
                .map(res -> new Relation(rid, res))
                .collect(Collectors.toList());
        log.info("collect: {}", collect);
        // 先删除当前角色，拥有的所有权限
        baseMapper.deleteHavePermissions(rid);
        // 在赋值新的 权限
        return baseMapper.insertByPermissions(collect);
    }

    @Override
    public Set<SysRole> findAllByUserId(Integer uid) {
        return baseMapper.findAllByUserId(uid);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteById(Integer rid) {
        if (baseMapper.userReference(rid) > 0) {
            throw new RuntimeException("当前角色，还有其他用户引用，无法删除！");
        }
        // 删除当前角色，拥有的权限
        baseMapper.deleteHavePermissions(rid);
        // 删除角色
        return baseMapper.deleteByPrimaryKey(rid);
    }

}
