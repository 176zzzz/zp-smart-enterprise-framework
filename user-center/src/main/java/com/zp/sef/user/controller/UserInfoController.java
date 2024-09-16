package com.zp.sef.user.controller;


import com.zp.sef.user.model.common.ResponseResult;
import com.zp.sef.user.model.entity.UserInfo;
import com.zp.sef.user.service.UserInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户信息表 前端控制器
 *
 * @author ZP
 */
@RestController
@Slf4j
@RequiredArgsConstructor
@Tag(name = "用户信息表")
@RequestMapping("/userInfo")
public class UserInfoController {

    private final UserInfoService userInfoService;

    @PostMapping("/save")
    @Operation(summary = "新增用户信息表", description = "新增用户信息表")
    public ResponseResult<Integer> saveUserInfo(@RequestBody UserInfo userInfo) {
        userInfoService.save(userInfo);
        return ResponseResult.success(userInfo.getId());
    }

    @PostMapping("/findAll")
    @Operation(summary = "查询所有用户信息表", description = "查询所有用户信息表")
    public ResponseResult<List<UserInfo>> findAllUserInfo() {
        return ResponseResult.success(userInfoService.list());
    }

}

