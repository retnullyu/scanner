package top.retnull.myscanner.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.retnull.myscanner.entities.BaseEntity;
import top.retnull.myscanner.service.BaseService;
import top.retnull.myscanner.utils.JsonResult;
import top.retnull.myscanner.utils.QueryParameter;

import javax.annotation.Resource;
import java.util.List;


/**
 * <p>
 * 通用的  增 , 删 , 改 , 查所有 , Id查询 , 分页 控制器
 * </p>
 */


@Slf4j
public class BaseController<T extends BaseEntity, ID, S extends BaseService<T, ID>> {

    @Autowired
    protected S baseService;

    @ApiOperation(value = "单个查询", notes = "根据Id查询")
    @GetMapping("/{id}")
    public JsonResult<T> findById(@PathVariable("id") ID id) {
        log.info("get ID : {}", id);
        return JsonResult.success(baseService.findById(id));
    }

    @ApiOperation(value = "查询所有", notes = "查询所有")
    @GetMapping("/all")
    public JsonResult<List<T>> findAll() {
        return JsonResult.success(baseService.findAll(null));
    }

    @ApiOperation(value = "分页", notes = "分页 查询所有")
    @GetMapping("/page")
    public JsonResult<Page<T>> findAll(QueryParameter parameter) {
        PageInfo<T> page = baseService.findAllPage(parameter);
        return JsonResult.success(page.getSize(), page.getTotal(), page.getList());
    }

    @ApiOperation(value = "新增", notes = "不需要添加id")
    @PostMapping()
    public JsonResult<T> create(@RequestBody T entity) {
        log.info("create:  {}", entity);
        try {
            int result = baseService.create(entity);
            return JsonResult.success(result);
        } catch (Exception e) {
            return JsonResult.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "修改", notes = "修改必须要id")
    @PutMapping()
    public JsonResult<T> update(@RequestBody T entity) {
        log.info("update:  {}", entity);
        try {
            int result = baseService.update(entity);
            return JsonResult.success(result);
        } catch (Exception e) {
            return JsonResult.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "删除", notes = "只需要id即可")
    @DeleteMapping("/{id}")
    public JsonResult<String> delete(@PathVariable("id") ID id) {
        log.info("delete:  {}", id);
        try {
            int result = baseService.deleteById(id);
            return JsonResult.success(result);
        } catch (Exception e) {
            return JsonResult.fail(e.getMessage());
        }
    }

}
