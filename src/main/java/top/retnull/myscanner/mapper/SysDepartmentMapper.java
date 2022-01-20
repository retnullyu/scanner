//package top.retnull.myscanner.mapper;
//
//
//import org.apache.ibatis.annotations.Select;
//
///**
// * <p>
// *
// * </p>
// *
// * @author luoyu12
// */
//
//
//public interface SysDepartmentMapper extends BaseMapper<SysDepartment, Integer> {
//
//    /**
//     * 判断某个部门下，是否还拥有 子部门
//     *
//     * @param id
//     * @return int
//     * @author retnull
//     * @date 2022/1/4
//     */
//    @Select("SELECT COUNT(*) FROM tb_sys_department WHERE parent_id = #{id}")
//    int haveChildren(Integer id);
//
//
//    /**
//     * 判断某个部门下，是否还拥有 用户
//     *
//     * @param id
//     * @return int
//     * @author retnull
//     * @date 2022/1/4
//     */
//    @Select("SELECT COUNT(*) FROM tb_sys_user WHERE dept_id = #{id}")
//    int haveUsers(Integer id);
//
//}
