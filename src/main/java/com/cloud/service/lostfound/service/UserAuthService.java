package com.cloud.service.lostfound.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.cloud.service.lostfound.domain.Role;
import com.cloud.service.lostfound.domain.UserAuth;
import com.cloud.service.lostfound.domain.UserDto;
import com.cloud.service.lostfound.domain.mapper.RoleMapper;
import com.cloud.service.lostfound.domain.mapper.UserAuthMapper;
import com.cloud.service.lostfound.infrastructure.common.Result;
import com.cloud.service.lostfound.infrastructure.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author romance
 * @date 2022/1/10 22:50
 */
@Service
public class UserAuthService {

    @Autowired
    private UserAuthMapper userAuthMapper;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private RoleMapper roleMapper;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    public UserDto getAdminByUserName(String username) {
        UserAuth userAuth = userAuthMapper.selectOne(new QueryWrapper<UserAuth>().eq("username", username));
        UserDto userDto = new UserAuth().fromUserAuth(userAuth);
        return userDto;

    }

    public List<Role> getRoles(Integer id) {
        return roleMapper.getRoles(id);
    }

    public Result login( String username, String password,String code, HttpServletRequest request) {
        String captcha = (String) request.getSession().getAttribute("captcha");
        if (StringUtils.isBlank(code) || !captcha.equalsIgnoreCase(code)) {
            return Result.fail("验证码填写错误！");
        }
        //登录
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (null==userDetails||!passwordEncoder.matches(password,userDetails.getPassword())){
            return Result.fail("用户名或密码不正确");
        }
        if (!userDetails.isEnabled()){
            return Result.fail("账号被禁用，请联系管理员！");
        }
        //更新security登录用户对象
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails
                ,null,userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //生成token
        String token = jwtTokenUtil.generateToken(userDetails);
        Map<String,String> tokenMap = new HashMap<>(16);
        tokenMap.put("token",token);
        tokenMap.put("tokenHead",tokenHead);
        return Result.ok(tokenMap);

    }
}
