package com.hmdp.config;

import com.hmdp.utils.LoginIntercepter;
import com.hmdp.utils.RefreshTokenIntercepter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //登录拦截器  后执行
        registry.addInterceptor(new LoginIntercepter())
                .excludePathPatterns(
                        "/shop/**",
                        "/voucher/**",
                        "/shop-type/**",
                        "/upload/**",
                        "/blog/hot",
                        "/user/code",
                        "/user/login"
                ).order(1);
        //token刷新的拦截器  先执行
        registry.addInterceptor(new RefreshTokenIntercepter(stringRedisTemplate))
                .addPathPatterns("/**").order(0);
    }
}
