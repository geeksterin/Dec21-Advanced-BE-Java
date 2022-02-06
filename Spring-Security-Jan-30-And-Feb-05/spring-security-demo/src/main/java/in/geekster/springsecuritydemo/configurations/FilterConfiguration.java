package in.geekster.springsecuritydemo.configurations;

import in.geekster.springsecuritydemo.filters.JWTAuthFilter;
import in.geekster.springsecuritydemo.filters.LoggedInContextFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfiguration {

    @Bean
    public JWTAuthFilter jwtAuthFilter() {
        return new JWTAuthFilter();
    }

    @Autowired
    @Bean(name = "jwtFilterRegistrationBean")
    public FilterRegistrationBean<JWTAuthFilter> filterRegistrationBean(final JWTAuthFilter jwtAuthFilter) {
        final FilterRegistrationBean<JWTAuthFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(jwtAuthFilter);
        return filterRegistrationBean;
    }

    @Bean
    public LoggedInContextFilter loggedInContextFilter() {
        return new LoggedInContextFilter();
    }

    @Autowired
    @Bean(name = "loggedInFilterRegistrationBean")
    public FilterRegistrationBean<LoggedInContextFilter> filterRegistrationBean(final LoggedInContextFilter loggedInContextFilter) {
        final FilterRegistrationBean<LoggedInContextFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(loggedInContextFilter);
        return filterRegistrationBean;
    }
}
