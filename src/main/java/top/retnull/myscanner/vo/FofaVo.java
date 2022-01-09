package top.retnull.myscanner.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import java.util.List;

/**
 * @program: myScanner
 * @description: FofaVo
 * @author: luoyu
 * @create: 2022-01-07 16:52
 **/

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class FofaVo implements java.io.Serializable {

    Integer rid;

    Integer page;

    Integer totalpages;

    List<ExcelVo> result;

}
