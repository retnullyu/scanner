package top.retnull.myscanner.common;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * <p>
 * MyBatis 通用 Mapper 接口
 * </p>
 * 通用mapper不能被扫描到！！！！！
 * @author luoyu12
 */

public interface BaseMapper<T, ID> extends Mapper<T>, MySqlMapper<T> {

}
