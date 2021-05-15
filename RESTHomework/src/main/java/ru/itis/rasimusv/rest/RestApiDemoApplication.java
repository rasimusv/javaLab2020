package ru.itis.rasimusv.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.rasimusv.rest.token.AccessTokenFilter;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class RestApiDemoApplication {

    private final AccessTokenFilter accessTokenFilter;

    public RestApiDemoApplication(AccessTokenFilter accessTokenFilter) {
        this.accessTokenFilter = accessTokenFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("ru.itis.rest.rasimusv.controllers"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public FilterRegistrationBean<AccessTokenFilter> getTokenFilter()
    {
        FilterRegistrationBean<AccessTokenFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(accessTokenFilter);
        registrationBean.addUrlPatterns("/token");
        registrationBean.setOrder(1); // set precedence
        return registrationBean;
    }

    public static void main(String[] args) {
        SpringApplication.run(RestApiDemoApplication.class, args);
    }

}
