package top.retnull.myscanner.entities;

import lombok.*;

/**
 * @program: spring-restful-api
 * @description: fofa查询历史
 * @author: luoyu
 * @create: 2022-01-05 17:45
 **/


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FofaQueryHistory {
    private Integer uid;
    private String queryKey;
    private Boolean isExported;
}
