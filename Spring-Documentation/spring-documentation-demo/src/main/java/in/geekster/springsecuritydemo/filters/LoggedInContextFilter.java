package in.geekster.springsecuritydemo.filters;

import in.geekster.springsecuritydemo.utils.LoggedInContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class LoggedInContextFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final FilterChain filterChain) throws ServletException, IOException {

        LoggedInContext.setCurrentUser(-999L);
        filterChain.doFilter(request, response);
        LoggedInContext.clear();
    }
}
