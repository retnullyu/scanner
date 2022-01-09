package top.retnull.myscanner.entities;

import lombok.*;

import javax.persistence.*;

/**
 * @program: myScanner
 * @description: fofa查询历史
 * @author: luoyu
 * @create: 2022-01-06 19:51
 **/

@Entity
@Table(name = "fofa_history")
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class FofaHistory extends BaseEntity {

    /**
     * 用户唯一id
     *
     * @date 2022/1/4
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rid;

    /**
     * 用户id
     *
     * @date 2022/1/4
     */
    private Integer uid;

    /**
     * 查询关键字
     *
     * @date 2022/1/4
     */
    @Column(name = "query_key")
    private String querykey;

    /**
     * 是否已导出
     *
     * @date 2022/1/4
     */
    @Column(name = "is_exported")
    private Integer isexported;

}
