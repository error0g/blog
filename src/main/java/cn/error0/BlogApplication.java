package cn.error0;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@MapperScan("cn.error0.dao")
public class BlogApplication  {

    public static void main(String[] args) {

            SpringApplication.run(BlogApplication.class, args);

    }

}
