package top.retnull.myscanner.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.retnull.myscanner.constant.PermissionType;
import top.retnull.myscanner.entities.SysPermission;
import top.retnull.myscanner.pojo.TreeNode;
import top.retnull.myscanner.service.SysPermissionService;
import top.retnull.myscanner.utils.JsonResult;
import top.retnull.myscanner.utils.TreeNodeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * All rights Reserved, Designed By retnull.top
 * <p>
 * 权限管理 控制器
 * </p>
 *
 * @author retnull
 * @version 2.0
 * @date 2022/1/4
 * 
 */


@Slf4j
@Api(tags = "[ 权限管理 ] 权限管理")
@RestController
@RequestMapping("/sys/permission")
public class SysPermissionController extends BaseController<SysPermission, Integer, SysPermissionService> {

    @ApiOperation(value = "查询所有[树节点]", notes = "以树节点的形式展示 <br> \n\n 如果 filter 是 true，那么就是要过滤掉，按钮。如果是 false。就是菜单和按钮全要")
    @GetMapping("/tree")
    public JsonResult tree(@RequestParam(defaultValue = "false") boolean filter) {
        List<TreeNode> collect = baseService.findAll(null)
                .stream()
                .distinct()
                // 如果 filter 是 true，那么就是要过滤掉，按钮。如果是 false。就是菜单和按钮全要
                .filter(res -> filter ? PermissionType.MENU.equals(res.getType().toLowerCase()) : true)
                .map(res -> new TreeNode(res.getPid(), res.getTitle(), res.getParentId(), res, new ArrayList<>()))
                .collect(Collectors.toList());
        return JsonResult.success(TreeNodeUtils.findRoots(collect));
    }


}
