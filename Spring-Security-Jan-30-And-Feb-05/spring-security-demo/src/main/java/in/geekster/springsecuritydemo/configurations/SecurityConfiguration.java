package in.geekster.springsecuritydemo.configurations;

import in.geekster.springsecuritydemo.dtos.UserPrincipal;
import in.geekster.springsecuritydemo.enums.Role;
import in.geekster.springsecuritydemo.filters.JWTAuthFilter;
import in.geekster.springsecuritydemo.services.impls.UserPrincipalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserPrincipalService userPrincipalService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTAuthFilter jwtAuthFilter;

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userPrincipalService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
//        super.configure(http);
        http.cors().and().csrf().disable()
                .authorizeRequests()

                .antMatchers(HttpMethod.POST,"/registrations").permitAll()
                .antMatchers(HttpMethod.POST, "/authenticate").permitAll()

                .antMatchers(HttpMethod.GET, "/books")
                .hasAnyAuthority(Role.ROLE_ADMIN.name(), Role.ROLE_LIB.name(), Role.ROLE_STUDENT.name())

                .antMatchers(HttpMethod.POST, "/books")
                .hasAnyAuthority(Role.ROLE_ADMIN.name(), Role.ROLE_LIB.name())

                .antMatchers("/users")
                .hasAuthority(Role.ROLE_ADMIN.name())

                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
