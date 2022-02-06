package in.geekster.springsecuritydemo.filters;

import in.geekster.springsecuritydemo.utils.JWTUtil;
import in.geekster.springsecuritydemo.utils.LoggedInContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class LoggedInContextFilter extends OncePerRequestFilter {

    @Autowired
    private JWTUtil jwtUtil;

    @Override
    protected void doFilterInternal(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final FilterChain filterChain) throws ServletException, IOException {

        final String authTokenHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (!StringUtils.hasText(authTokenHeader)) {
            log.warn("No Authorization Token Found");
            LoggedInContext.setCurrentUser(-999L);
            filterChain.doFilter(request, response);
            return;
        }

        if (!authTokenHeader.startsWith("Bearer ")) {
            log.warn("No Bearer Token Found in Authorization");
            LoggedInContext.setCurrentUser(-999L);
            filterChain.doFilter(request, response);
            return;
        }

        final String jwt = authTokenHeader.substring(7);
        if (!StringUtils.hasText(jwt)) {
            log.warn("No JWT Token found in Bearer Token");
            LoggedInContext.setCurrentUser(-999L);
        } else {
            final Long currentUser = jwtUtil.getUserId(jwt);
            log.debug("Current User ID: {}", currentUser);
            LoggedInContext.setCurrentUser(currentUser);
        }
        filterChain.doFilter(request, response);
        LoggedInContext.clear();
    }
}
