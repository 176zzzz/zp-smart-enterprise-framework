package com.zp.sef.common.model.base;


import com.baomidou.mybatisplus.extension.service.IService;
import com.zp.sef.common.model.web.ResponseResult;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * BaseController
 * @author ZP
 */

public class BaseController<M extends IService<T>,T>{

    @Autowired
    private M service;

    @PostMapping("/save")
    @Operation(summary = "save", description = "save")
    public ResponseResult<Integer> saveUserInfo(@RequestBody T userInfo) {
        service.save(userInfo);
        return ResponseResult.success();
    }

    @PostMapping("/batchSave")
    @Operation(summary = "batchSave", description = "batchSave")
    public ResponseResult<Integer> batchSave(@RequestBody List<T> entities) {
        boolean success = service.saveBatch(entities);
        return success ? ResponseResult.success() : ResponseResult.fail();
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "delete", description = "delete by id")
    public ResponseResult<Integer> delete(@PathVariable("id") Long id) {
        boolean success = service.removeById(id);
        if (success) {
            return ResponseResult.success();
        } else {
            return ResponseResult.fail();
        }
    }

    @DeleteMapping("/batchDelete")
    @Operation(summary = "batchDelete", description = "batchDelete")
    public ResponseResult<Integer> deleteByIds(@RequestBody List<Long> ids) {
        boolean success = service.removeByIds(ids);
        return success ? ResponseResult.success() : ResponseResult.fail();
    }

    @PostMapping("/update")
    @Operation(summary = "update", description = "update")
    public ResponseResult<Integer> update(@RequestBody T entity) {
        boolean success = service.updateById(entity);
        return success ? ResponseResult.success() : ResponseResult.fail("更新失败");
    }

    @PostMapping("/saveOrUpdate")
    @Operation(summary = "saveOrUpdate", description = "saveOrUpdate")
    public ResponseResult<Integer> batchSaveOrUpdate(@RequestBody T entity) {
        boolean success = service.saveOrUpdate(entity);
        return success ? ResponseResult.success() : ResponseResult.fail("批量新增或更新失败");
    }

    @PostMapping("/findAll")
    @Operation(summary = "findAll", description = "findAll")
    public ResponseResult<List<T>> findAllUserInfo() {
        return ResponseResult.success(service.list());
    }

    @PostMapping("/findById/{id}")
    @Operation(summary = "findById", description = "findById")
    public ResponseResult<T> findById(@PathVariable("id") Long id) {
        return ResponseResult.success(service.getById(id));
    }











}
