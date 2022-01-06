package top.retnull.myscanner.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.retnull.myscanner.entities.SysDepartment;
import top.retnull.myscanner.pojo.TreeNode;
import top.retnull.myscanner.service.SysDepartmentService;
import top.retnull.myscanner.utils.JsonResult;
import top.retnull.myscanner.utils.TreeNodeUtils;

import java.util.List;
import java.util.stream.Collectors;


/**
 * All rights Reserved, Designed By retnull.top
 * <p>
 * 部门管理 控制器
 * </p>
 *
 * @author retnull
 * @version 2.0
 * @date 2022/1/4
 * 
 */


@Slf4j
@Api(tags = "[ 权限管理 ] 部门管理")
@RestController
@RequestMapping("/sys/department")
public class SysDepartmentController extends BaseController<SysDepartment, Integer, SysDepartmentService> {

    @ApiOperation(value = "查询所有[树节点]", notes = "以树节点的形式展示")
    @GetMapping("/tree")
    public JsonResult tree() {
        List<TreeNode> collect = baseService.findAll(null)
                .stream()
                .distinct()
                .map(res -> new TreeNode(res.getId(), res.getName(), res.getParentId(), res, null))
                .collect(Collectors.toList());
        List<TreeNode> roots = TreeNodeUtils.findRoots(collect);
        return JsonResult.success(roots);
    }

}
