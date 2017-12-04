package spring.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class SpringBootConfig extends WebMvcConfigurerAdapter{

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/WEB-INF/web-resources/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/booksList").setViewName("registered/books/booksList");
        registry.addViewController("/addBook").setViewName("registered/books/addBook");
        registry.addViewController("/welcome").setViewName("registered/welcome");
        registry.addViewController("/unregistered/register").setViewName("unregistered/register");
        registry.addViewController("/login").setViewName("index");

    }
}
