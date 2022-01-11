package com.cloud.service.lostfound.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;

/**
 * @Author romance
 * @date 2021/12/25 23:41
 */
@Api(tags = "验证码接口")
@RestController
@RequestMapping
public class CaptchaRestController {

    @ApiOperation(value = "验证码")
    @GetMapping(value = "/captcha", produces = "image/jpeg")
    public void captcha(HttpServletRequest request, HttpServletResponse response) {
        // 定义response输出类型为image/jpeg类型
        response.setDateHeader("Expires", 0);
        // Set standard HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control", "no-store, no-cache, mustrevalidate");
        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        // Set standard HTTP/1.0 no-cache header.
        response.setHeader("Pragma", "no-cache");
        // return a jpeg
        response.setContentType("image/jpeg");
        //定义图形验证码的长、宽、验证码字符数、干扰线宽度
        ServletOutputStream outputStream = null;
        try {
            ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(100, 50, 4, 4);
            //ShearCaptcha captcha = new ShearCaptcha(200, 100, 4, 4);
            //图形验证码写出，可以写出到文件，也可以写出到流
            captcha.createCode();
            captcha.createImage(captcha.getCode());
            //将验证码放入session中
            request.getSession().setAttribute("captcha", captcha.getCode());
            //验证图形验证码的有效性，返回boolean值
            System.out.println("验证码内容:"+captcha.getCode());
            outputStream = response.getOutputStream();
            captcha.write(outputStream);
            outputStream.flush();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (null != outputStream) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

