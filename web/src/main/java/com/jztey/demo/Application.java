package com.jztey.demo;

import com.jztey.demo.config.LoggerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.*;


import com.jztey.framework.boot.ApplicationDruid;
import com.jztey.framework.boot.ApplicationDubbo;

/**
 * Created by Charles on 2016/2/27.
 */

@SpringBootApplication
@EnableScheduling
@Import({ApplicationDruid.class, ApplicationDubbo.class // 取消本地dubbo服务
        // , ApplicationMonitoring.class
})

public class Application {


    @Bean
    public WebMvcConfigurer corsConfigurer() { // 允许跨域
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*").allowedMethods("POST", "DELETE", "PUT", "GET",
                        "OPTIONS");
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }


}
