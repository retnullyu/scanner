package top.retnull.myscanner.entities;

import lombok.*;

/**

 * <p>
 * 关联权限设置，时候用到
 * </p>
 * @author luoyu12
 */


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Relation implements java.io.Serializable {

    /**
     * @description 比如: 用户 关联 角色，就表示 用户ID
     * @author retnull
     * @date 2022/1/4
     */
    private Integer targetId;

    /**
     * @description 比如: 用户 关联 角色，就表示 角色ID
     * @author retnull
     * @date 2022/1/4
     */
    private Integer sourceId;

}
