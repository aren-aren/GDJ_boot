package com.winter.app.config;

import com.winter.app.interceptor.TestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    private TestInterceptor testInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /* 어떤 URL이 왔을 때 어떤 Interceptor를 거치게 할 지 지정 */
//        registry.addInterceptor(testInterceptor)        // Interceptor 등록
//                .addPathPatterns("/notice/*")           // path 등록
//                .excludePathPatterns("/notice/add");    // path 제외
    }
}
