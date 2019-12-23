package cn.error0.config;


import cn.error0.interceptors.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class MyWebMvcConfigurerAdapter implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**/*").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX+"/static/");
        /*虚拟路径映射*/
        registry.addResourceHandler("/picture/**").addResourceLocations("file:D:\\picture\\");

    }


    //    @Override
    //    public void addInterceptors(InterceptorRegistry registry) {
    //        registry.addInterceptor(loginInterceptor).addPathPatterns("/admin/**");
    //   }


}
