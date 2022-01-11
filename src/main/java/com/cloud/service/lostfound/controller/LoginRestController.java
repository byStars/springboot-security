package com.cloud.service.lostfound.controller;

import com.cloud.service.lostfound.domain.UserDto;
import com.cloud.service.lostfound.dto.login.LoginUserModel;
import com.cloud.service.lostfound.infrastructure.common.Result;
import com.cloud.service.lostfound.service.UserAuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

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
    public Result<?> login(@RequestBody LoginUserModel loginUserModel, HttpServletRequest request){
        return userAuthService.login(loginUserModel.getUsername(),loginUserModel.getPassword(),loginUserModel.getCode(),request);
    }

    @ApiOperation(value = "获取当前登录用户的信息")
    @GetMapping("/info")
    public UserDto getAdminInfo(Principal principal){
        if (null==principal){
            return null;
        }
        String username = principal.getName();
        UserDto userDto = userAuthService.getAdminByUserName(username);
        userDto.setRoles(userAuthService.getRoles(userDto.getId()));
        return userDto;
    }

    @ApiOperation(value = "退出登录")
    @PostMapping("/logout")
    public  Result<?> logout(){
        return  Result.ok("注销成功！");
    }

}
