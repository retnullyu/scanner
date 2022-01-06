package top.retnull.myscanner.service;

import com.github.pagehelper.PageInfo;
import top.retnull.myscanner.entities.BaseEntity;
import top.retnull.myscanner.utils.QueryParameter;

import java.util.List;

/**
 * All rights Reserved, Designed By retnull.top
 * <p>
 * 通用 service 接口
 * </p>
 *
 * @author retnull
 * @version 2.0
 * @date 2022/1/4
 * 
 */

public interface BaseService<T extends BaseEntity, ID> {

    /**
     * 分页查询
     *
     * @param parameter
     * @return PageInfo<T>
     * @author retnull
     * @date 2022/1/4
     */
    PageInfo<T> findAllPage(QueryParameter parameter);

    /**
     * 查看所有数据，根据条件查询
     *
     * @param entity
     * @return List<T>
     * @author retnull
     * @date 2022/1/4
     */
    List<T> findAll(T entity);

    /**
     * 根据ID查询数据
     *
     * @param id
     * @return T
     * @author retnull
     * @date 2022/1/4
     */
    T findById(ID id);

    /**
     * 根据条件查询，只返回一条数据
     *
     * @param entity
     * @return T
     * @author retnull
     * @date 2022/1/4
     */
    T find(T entity);

    /**
     * 新增数据
     *
     * @param entity
     * @return int
     * @author retnull
     * @date 2022/1/4
     */
    int create(T entity);

    /**
     * 批量新增数据， 返回 新增的数量
     *
     * @param list
     * @return int
     * @author retnull
     * @date 2022/1/4
     */
    int batchCreate(List<T> list);

    /**
     * 修改数据，必须带 ID ，返回 被修改的数量
     *
     * @param entity
     * @return int
     * @author retnull
     * @date 2022/1/4
     */
    int update(T entity);

    /**
     * 根据ID删除数据，返回 删除的数量
     *
     * @param id
     * @return int
     * @author retnull
     * @date 2022/1/4
     */
    int deleteById(ID id);

    /**
     * 根据条件删除，返回 删除的数量
     *
     * @param entity
     * @return int
     * @author retnull
     * @date 2022/1/4
     */
    int delete(T entity);

    /**
     * 根据ID判断数据是否存在
     *
     * @param entity
     * @return int
     * @author retnull
     * @date 2022/1/4
     */
    int count(T entity);

    /**
     * 根据ID判断数据是否存在
     *
     * @param id
     * @return boolean
     * @author retnull
     * @date 2022/1/4
     */
    boolean existsWithPrimaryKey(ID id);

}
