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
public class AuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final FilterChain filterChain) throws ServletException, IOException {
        log.info("Authorization Filter IN ACTION");
        final String httpMethod = request.getMethod();
        if ("GET".equals(httpMethod)) {
            log.info("Authorized GET");
            filterChain.doFilter(request, response);
        } else {
            if (!StringUtils.hasText(request.getHeader("X-Auth-Manager"))) {
                log.warn("Not Authorized for this HTTP Method: {}", httpMethod);
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                return;
            }
            log.info("Authorized with HEADER");
            filterChain.doFilter(request, response);
        }
    }
}
