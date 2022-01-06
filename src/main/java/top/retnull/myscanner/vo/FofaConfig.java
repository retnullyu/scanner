package top.retnull.myscanner.vo;

import lombok.*;

/**
 * @program: spring-restful-api
 * @description: Fofa vo
 * @author: luoyu
 * @create: 2022-01-05 20:08
 **/

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FofaConfig {
    private String email;
    private String token;
}
