package com.zp.sef.user.controller;


import com.zp.sef.common.model.base.BaseController;
import com.zp.sef.user.entity.UserInfo;
import com.zp.sef.user.service.UserInfoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
public class UserInfoController extends BaseController<UserInfoService,UserInfo> {

}

