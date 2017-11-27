package com.hillel.configuration;


import com.google.common.collect.ImmutableList;
import com.hillel.interceptor.EncodingInterceptor;
import com.hillel.security.interceptor.SecurityInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.ArrayList;
import java.util.List;

@EnableWebMvc
@Configuration
@ComponentScan("com.hillel")
public class WebApplicationConfiguration extends WebMvcConfigurerAdapter {


    //Influences on path to our resources, that we can determine into pages, so on
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("/resources/css/").setCachePeriod(0);
        registry.addResourceHandler("/img/**").addResourceLocations("/resources/img/").setCachePeriod(0);
        registry.addResourceHandler("/js/**").addResourceLocations("/resources/js/").setCachePeriod(0);
    }

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setDefaultEncoding("UTF-8");
        return multipartResolver;
    }


    @Bean
    public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
        ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
        resolver.setContentNegotiationManager(manager);
        List<View> views = new ArrayList<>();
        views.add(jsonView());
        resolver.setDefaultViews(views);
        resolver.setViewResolvers(ImmutableList.of(internalResourceViewResolver()));
        return resolver;
    }


    @Bean
    public MappingJackson2JsonView jsonView() {
        MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
        jsonView.setPrettyPrint(true);
        return jsonView;
    }


    @Bean("viewResolver")
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(securityInterceptor())
//                .addPathPatterns("/users/*", "/books/*", "/users", "/books", "/*")
                .addPathPatterns("/**")
                .excludePathPatterns("/login", "/user/register", "/start");
        registry.addInterceptor(encodingInterceptor()).addPathPatterns("/**");
    }

    @Bean
    public HandlerInterceptor securityInterceptor() {
        return new SecurityInterceptor();
    }

    @Bean
    public HandlerInterceptor encodingInterceptor() {
        return new EncodingInterceptor();
    }

}
