package top.retnull.myscanner.utils;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * All rights Reserved, Designed By retnull.top
 * <p>
 * 通用查询参数
 * </p>
 *
 * @author retnull
 * @version 2.0
 * @date 2022/1/4
 * 
 */

@Getter
@Setter
@ToString
public class QueryParameter implements java.io.Serializable {

    /**
     * @description: 当前第几页
     * @date 2022/1/4
     */
    private int pageNum = 1;

    /**
     * @description: 每页多少条数据
     * @date 2022/1/4
     */
    private int pageSize = 10;

    /**
     * @description: 搜索，关键字
     * @date 2022/1/4
     */
    private String keywords;

}
