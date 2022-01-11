package com.cloud.service.lostfound.controller;


import com.cloud.service.lostfound.domain.Menu;
import com.cloud.service.lostfound.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: xmx
 * @date: 2021/12/27 13:54
 * @description: 菜单接口
 **/
@Api(tags = "菜单接口")
@RestController
@RequestMapping("/menus")
public class MenuRestController {

    @Autowired
    private MenuService menuService;


    @ApiOperation(value = "获取当前登录用户下的菜单列表")
    @GetMapping
    public List<Menu> getMenusById() {
        return menuService.selectMenusList();
    }
}
