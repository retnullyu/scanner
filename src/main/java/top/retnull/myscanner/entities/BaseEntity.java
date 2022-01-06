package top.retnull.myscanner.entities;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * <p>
 * 父实体类
 * </p>
 * @author luoyu12
 */

@Getter
@Setter
public class BaseEntity implements java.io.Serializable {

    /**
     * 创建时间
     * @date 2022/1/4
     */
    private LocalDateTime createTime;

    /**
     * 最后一次修改时间
     * @date 2022/1/4
     */
    private LocalDateTime lastUpdateTime;

}
