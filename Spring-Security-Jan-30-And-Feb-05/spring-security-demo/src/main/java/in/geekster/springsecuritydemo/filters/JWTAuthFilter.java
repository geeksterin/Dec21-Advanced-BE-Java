package in.geekster.springsecuritydemo.filters;

import in.geekster.springsecuritydemo.dtos.UserDTO;
import in.geekster.springsecuritydemo.dtos.UserPrincipal;
import in.geekster.springsecuritydemo.services.UserService;
import in.geekster.springsecuritydemo.services.impls.UserPrincipalService;
import in.geekster.springsecuritydemo.utils.JWTUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JWTAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final FilterChain filterChain) throws ServletException, IOException {

        final String authTokenHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (!StringUtils.hasText(authTokenHeader)) {
            log.warn("No Authorization Token Found");
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "UNAUTHORIZED");
            return;
        }

        if (!authTokenHeader.startsWith("Bearer ")) {
            log.warn("No Bearer Token Found in Authorization");
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "UNAUTHORIZED");
            return;
        }

        final String jwt = authTokenHeader.substring(7);
        if (!StringUtils.hasText(jwt)) {
            log.warn("No JWT Token found in Bearer Token");
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "UNAUTHORIZED");
            return;
        }

        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            final String username = jwtUtil.getUsername(jwt);
            final UserDTO userDTO = userService.getByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " not found"));
            if (jwtUtil.validateToken(jwt, userDTO)) {
                log.info("JWT {} validated", jwt);
                final UserDetails userPrincipal = new UserPrincipal(userDTO);
                final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                        = new UsernamePasswordAuthenticationToken(userPrincipal, null, userPrincipal.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }


    @Override
    protected boolean shouldNotFilter(final HttpServletRequest request) throws ServletException {
        final String requestPath = request.getRequestURI();
        return "/authenticate".equals(requestPath) || "/registrations".equals(requestPath);
    }
}
