//package top.retnull.myscanner.service.serviceImpl;
//
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import top.retnull.myscanner.entities.SysDepartment;
//import top.retnull.myscanner.mapper.SysDepartmentMapper;
//import top.retnull.myscanner.service.SysDepartmentService;
//
//
///**
//
// * <p>
// * 部门 Service 实现类
// * </p>
//**/
//
//@Slf4j
//@Service
//public class SysDepartmentServiceImpl extends BaseServiceImpl<SysDepartment, Integer, SysDepartmentMapper> implements SysDepartmentService {
//
//    @Transactional(rollbackFor = Exception.class)
//    @Override
//    public int deleteById(Integer id) {
//        if (baseMapper.haveChildren(id) > 0) {
//            throw new RuntimeException("当前部门下，还有子部门，无法删除！");
//        }
//        if (baseMapper.haveUsers(id) > 0) {
//            throw new RuntimeException("当前部门下，还有其用户在使用，无法删除！");
//        }
//        return baseMapper.deleteByPrimaryKey(id);
//    }
//
//}
