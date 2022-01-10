package com.cloud.service.lostfound.service;

import com.cloud.service.lostfound.domain.Menu;
import com.cloud.service.lostfound.domain.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author romance
 * @date 2022/1/10 22:46
 */
@Service
public class MenuService {
    @Autowired
    private MenuMapper menuMapper;

    public List<Menu> getMenusWithRole() {
        return menuMapper.getMenusWithRole();
    }
}
