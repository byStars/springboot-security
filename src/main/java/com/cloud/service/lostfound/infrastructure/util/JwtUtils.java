package com.cloud.service.lostfound.infrastructure.util;

import com.cloud.service.lostfound.domain.UserDto;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * @author: xmx
 * @date: 2022/1/11 15:48
 * @description: jwt工具类
 **/
@Component
public class JwtUtils {

    public static UserDto getLoginUser() {
        return (UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
