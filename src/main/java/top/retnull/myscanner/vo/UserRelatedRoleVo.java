package top.retnull.myscanner.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * All rights Reserved, Designed By retnull.top
 * <p>
 * 用户关联角色
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
public class UserRelatedRoleVo implements java.io.Serializable {

    @NotNull(message = "用户ID不能为空!")
    private Integer uid;

    @NotNull(message = "角色ID列表,不能为空!")
    @Size(min = 1, message = "用户角色最少赋值一个")
    private Set<Integer> roles;

}
