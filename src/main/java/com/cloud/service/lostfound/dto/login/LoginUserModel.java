package com.cloud.service.lostfound.dto.login;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author romance
 * @date 2022/1/10 23:15
 */
@Data
public class LoginUserModel {

    @ApiModelProperty(value = "用户名",required = true)
    private String username;
    @ApiModelProperty(value = "密码",required = true)
    private String password;
}
