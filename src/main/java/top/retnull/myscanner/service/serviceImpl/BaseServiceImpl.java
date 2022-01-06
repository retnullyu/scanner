package top.retnull.myscanner.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import top.retnull.myscanner.entities.BaseEntity;
import top.retnull.myscanner.common.BaseMapper;
import top.retnull.myscanner.service.BaseService;
import top.retnull.myscanner.utils.QueryParameter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 通用 service 实现类
 * </p>
 */

@Slf4j
public class BaseServiceImpl<T extends BaseEntity, ID, M extends BaseMapper<T, ID>> implements BaseService<T, ID> {

    @Autowired
    protected M baseMapper;

    @Override
    public PageInfo<T> findAllPage(QueryParameter parameter) {
        log.info("findAll parameter: {}", parameter);
        PageInfo<T> pageInfo = PageHelper
                .startPage(parameter.getPageNum(), parameter.getPageSize())
                .doSelectPageInfo(() -> baseMapper.selectAll());
        return pageInfo;
    }

    @Override
    public List<T> findAll(T entity) {
        log.info("findAll entity: {}", entity);
        if (entity == null) {
            baseMapper.selectAll();
        }
        return baseMapper.select(entity);
    }

    @Override
    public T findById(ID id) {
        log.info("findById id: {}", id);
        return baseMapper.selectByPrimaryKey(id);
    }

    @Override
    public T find(T entity) {
        log.info("find entity: {}", entity);
        return baseMapper.selectOne(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int create(T entity) {
        entity.setCreateTime(LocalDateTime.now());
        entity.setLastUpdateTime(LocalDateTime.now());
        log.info("create entity: {}", entity);
        return baseMapper.insertUseGeneratedKeys(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int batchCreate(List<T> list) {
        list.forEach(res -> {
            res.setCreateTime(LocalDateTime.now());
            res.setLastUpdateTime(LocalDateTime.now());
        });
        log.info("batchCreate list: {}", list);
        return baseMapper.insertList(list);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int update(T entity) {
        entity.setLastUpdateTime(LocalDateTime.now());
        log.info("update entity: {}", entity);
        return baseMapper.updateByPrimaryKeySelective(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteById(ID id) {
        log.info("deleteById id: {}", id);
        return baseMapper.deleteByPrimaryKey(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int delete(T entity) {
        log.info("delete entity: {}", entity);
        return baseMapper.delete(entity);
    }

    @Override
    public int count(T entity) {
        log.info("count entity: {}", entity);
        return baseMapper.selectCount(entity);
    }

    @Override
    public boolean existsWithPrimaryKey(ID id) {
        log.info("existsWithPrimaryKey id: {}", id);
        return baseMapper.existsWithPrimaryKey(id);
    }
}
