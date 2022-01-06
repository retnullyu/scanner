package top.retnull.myscanner.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

/**
 * All rights Reserved, Designed By retnull.top
 * <p>
 *
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
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TreeNode implements java.io.Serializable {

    /**
     * 获取 当前节点 ID
     *
     * @return int
     * @author retnull
     * @date 2022/1/4
     */
    private long id;

    /**
     * 当前节点标题
     *
     * @return int
     * @author retnull
     * @date 2022/1/4
     */
    private String title;

    /**
     * 上级节点 ID
     *
     * @return int
     * @author retnull
     * @date 2022/1/4
     */
    private long parentId;

    /**
     * 源属性
     *
     * @return int
     * @author retnull
     * @date 2022/1/4
     */
    private Object source;

    /**
     * 获取 所有子节点
     *
     * @JsonInclude(JsonInclude.Include.NON_NULL)
     * @return int
     * @author retnull
     * @date 2022/1/4
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<TreeNode> children;

}
