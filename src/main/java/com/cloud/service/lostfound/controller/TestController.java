package com.cloud.service.lostfound.controller;

import com.cloud.service.lostfound.infrastructure.common.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author romance
 * @date 2022/1/10 23:06
 */
@Api(tags = "测试Controller")
@RequestMapping
@RestController
public class TestController {

    @GetMapping("/test")
    public Result test(){
        return Result.ok("测试成功");
    }
}
