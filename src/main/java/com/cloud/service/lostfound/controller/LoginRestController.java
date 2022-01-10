package com.cloud.service.lostfound.controller;

import com.cloud.service.lostfound.dto.login.LoginUserModel;
import com.cloud.service.lostfound.infrastructure.common.Result;
import com.cloud.service.lostfound.service.UserAuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author romance
 * @date 2022/1/10 23:13
 */
@Api(tags = "登录接口")
@RestController
@RequestMapping
public class LoginRestController {

    @Autowired
    private UserAuthService userAuthService;

    @ApiOperation(value = "登录之后返回token")
    @PostMapping("/login")
    public Result login(@RequestBody LoginUserModel loginUserModel, HttpServletRequest request){
        return userAuthService.login(loginUserModel.getUsername(),loginUserModel.getPassword(),request);
    }

}
