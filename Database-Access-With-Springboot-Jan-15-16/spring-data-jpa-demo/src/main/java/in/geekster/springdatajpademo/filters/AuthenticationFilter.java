package in.geekster.springdatajpademo.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class AuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final FilterChain filterChain) throws ServletException, IOException {

        log.info("Authentication Filter in ACTION");
        final String authToken = request.getHeader("X-Auth-Token");
        if (!StringUtils.hasText(authToken)) {
            log.warn("No Auth Token Found");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }
        log.info("Request Authenticated");
        filterChain.doFilter(request, response);
    }
}
