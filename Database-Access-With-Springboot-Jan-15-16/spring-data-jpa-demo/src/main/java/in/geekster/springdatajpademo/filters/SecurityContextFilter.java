package in.geekster.springdatajpademo.filters;

import in.geekster.springdatajpademo.utils.SecurityContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class SecurityContextFilter extends OncePerRequestFilter {

    private static final String DEFAULT_REQUESTING_CLIENT = "SYSTEM";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("Security Context Filter in ACTION");
        if (StringUtils.hasText(request.getHeader("X-Requesting-Client"))) {
            SecurityContextUtil.setRequestingClient(request.getHeader("X-Requesting-Client"));
        } else {
            SecurityContextUtil.setRequestingClient(DEFAULT_REQUESTING_CLIENT);
        }
        filterChain.doFilter(request, response);
        SecurityContextUtil.clear();
    }
}
