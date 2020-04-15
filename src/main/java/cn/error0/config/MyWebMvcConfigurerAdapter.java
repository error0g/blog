package cn.error0.config;


import cn.error0.interceptors.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class MyWebMvcConfigurerAdapter implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**/*").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX+"/static/");
        //虚拟路径映射
        registry.addResourceHandler("/picture/**").addResourceLocations("file:D:/picture/");

    }
    //登陆拦截
        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(loginInterceptor).addPathPatterns("/admin/**");
       }

    @Override
    public void addViewControllers(ViewControllerRegistry registry)

    {
        //虚拟映射
        registry.addViewController("/admin").setViewName("forward:/admin/index.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        WebMvcConfigurer.super.addViewControllers(registry);

    }

}
