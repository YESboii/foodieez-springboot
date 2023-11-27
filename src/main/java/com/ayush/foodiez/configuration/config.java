package com.ayush.foodiez.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class config implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
            registry.addResourceHandler("/productImages/**")
                    .addResourceLocations("file:foodiez/src/main/resources/static/productImages/");
    }
        //This can be used to map simple views with url where no business logic is required in cases such as just returning
    //the view
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/signup").setViewName("signup");
//    }
}
