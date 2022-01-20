//package top.retnull.myscanner.entities;
//
//import lombok.*;
//
//import javax.persistence.*;
//
//
///**
// * <p>
// * 后台系统 部门
// * </p>
// *
// */
//
//
//@Entity
//@Table(name = "sys_department")
//@Getter
//@Setter
//@ToString
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//@EqualsAndHashCode(callSuper = false)
//public class SysDepartment extends BaseEntity implements java.io.Serializable {
//
//    /**
//     * @description: 部门 ID
//     * @date 2022/1/4
//     */
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    /**
//     * @description: 部门 名称
//     * @date 2022/1/4
//     */
//    private String name;
//
//    /**
//     * @description: 部门 名称
//     * @date 2022/1/4
//     */
//    private Integer parentId;
//
//    /**
//     * @description: 部门排序
//     * @date 2022/1/4
//     */
//    private Integer level;
//
//    /**
//     * @description: 部门 描述
//     * @date 2022/1/4
//     */
//    private String description;
//
//}