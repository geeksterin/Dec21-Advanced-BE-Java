package in.geekster.springsecuritydemo.configurations;

import in.geekster.springsecuritydemo.filters.LoggedInContextFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfiguration {

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
