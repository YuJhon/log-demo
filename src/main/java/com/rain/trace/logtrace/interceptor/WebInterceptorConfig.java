package com.rain.trace.logtrace.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <p>功能描述</br>框架公共拦截器</p>
 *
 * @author rain
 * @version v1.0
 * @projectName log-trace
 * @date 2019/12/16 15:51
 */
@Configuration
public class WebInterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private WebHeaderInterceptor webHeaderInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(webHeaderInterceptor);
    }
}
