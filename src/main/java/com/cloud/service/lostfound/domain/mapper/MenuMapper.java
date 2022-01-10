package com.cloud.service.lostfound.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloud.service.lostfound.domain.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author romance
 * @date 2022/1/10 22:52
 */
@Repository
public interface MenuMapper extends BaseMapper<Menu> {
    List<Menu> getMenusWithRole();

}
