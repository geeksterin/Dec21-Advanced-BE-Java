package in.geekster.springdatajpademo.configurations;

import in.geekster.springdatajpademo.filters.AuthenticationFilter;
import in.geekster.springdatajpademo.filters.AuthorizationFilter;
import in.geekster.springdatajpademo.filters.SecurityContextFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Slf4j
public class FilterConfigurations {


    @Bean
    @Autowired
    public FilterRegistrationBean<AuthorizationFilter> registerAuthorizationFilter(final AuthorizationFilter authorizationFilter) {
        final FilterRegistrationBean<AuthorizationFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        log.info("Registering Filter");
        filterRegistrationBean.setFilter(authorizationFilter);
        final List<String> urls = new ArrayList<>();
        urls.add("/roles/*");
        urls.add("/projects/*");
        filterRegistrationBean.setUrlPatterns(urls);
        filterRegistrationBean.setOrder(2);
        log.info("After Registering filter");
        return filterRegistrationBean;
    }

    @Bean
    @Autowired
    public FilterRegistrationBean<SecurityContextFilter> registerSecurityContextFilter(final SecurityContextFilter securityContextFilter) {
        final FilterRegistrationBean<SecurityContextFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        log.info("Registering Filter");
        filterRegistrationBean.setFilter(securityContextFilter);
        filterRegistrationBean.setOrder(3);
        log.info("After Registering filter");
        return filterRegistrationBean;
    }

    @Bean
    @Autowired
    public FilterRegistrationBean<AuthenticationFilter> registerAuthenticationFilter(final AuthenticationFilter authenticationFilter) {
        final FilterRegistrationBean<AuthenticationFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        log.info("Registering Filter");
        filterRegistrationBean.setFilter(authenticationFilter);
        filterRegistrationBean.setOrder(1);
        log.info("After Registering filter");
        return filterRegistrationBean;
    }

    @Bean(name = "authenticationFilter")
    public AuthenticationFilter authenticationFilter() {
        log.info("Authentication Filter Bean");
        return new AuthenticationFilter();
    }

    @Bean(name = "authorizationFilter")
    public AuthorizationFilter authorizationFilter() {
        log.info("Authorization Filter Bean");
        return new AuthorizationFilter();
    }

    @Bean
    public SecurityContextFilter securityContextFilter() {
        log.info("Security Context Filter Bean");
        return new SecurityContextFilter();
    }
}
