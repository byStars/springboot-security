package com.cloud.service.lostfound.service;

import com.cloud.service.lostfound.domain.Menu;
import com.cloud.service.lostfound.domain.UserDto;
import com.cloud.service.lostfound.domain.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Author romance
 * @date 2022/1/10 22:46
 */
@Service
public class MenuService {
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public List<Menu> getMenusWithRole() {
        return menuMapper.getMenusWithRole();
    }

    public List<Menu> selectMenusList() {
        Integer userId = ((UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();

        ValueOperations<String, Object> valueOperations =
                redisTemplate.opsForValue();
        List<Menu> menusList = (List<Menu>) valueOperations.get("menu_" + userId);
        if (CollectionUtils.isEmpty(menusList)) {
            menusList = menuMapper.getMenusByAdminId(userId);
            valueOperations.set("menu_" + userId, menusList);
        }
        return menusList;

    }
}
