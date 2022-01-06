package top.retnull.myscanner.vo;

import lombok.*;

import java.util.List;

/**
 * All rights Reserved, Designed By retnull.top
 * <p>
 * 菜单权限
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
@EqualsAndHashCode
public class MenuVo implements java.io.Serializable {

    private Integer pid;

    private Integer parentId;

    private String icon;

    private String resources;

    private String title;

    private List<MenuVo> children;

}
