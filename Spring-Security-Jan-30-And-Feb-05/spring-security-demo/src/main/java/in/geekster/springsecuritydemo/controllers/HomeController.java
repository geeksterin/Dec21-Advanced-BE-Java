package in.geekster.springsecuritydemo.controllers;

import in.geekster.springsecuritydemo.dtos.TokenDTO;
import in.geekster.springsecuritydemo.dtos.UserDTO;
import in.geekster.springsecuritydemo.models.requests.AuthenticationRequest;
import in.geekster.springsecuritydemo.models.responses.AuthenticatedTokenResponse;
import in.geekster.springsecuritydemo.services.UserService;
import in.geekster.springsecuritydemo.utils.JWTUtil;
import in.geekster.springsecuritydemo.utils.MapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@Slf4j
public class HomeController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JWTUtil jwtUtil;

    @GetMapping
    public ResponseEntity<String> hello() {
        log.info("Call came to Hello");
        return ResponseEntity.ok("Hey there");
    }


    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticatedTokenResponse> authenticate(
            @Validated @RequestBody final AuthenticationRequest authenticationRequest) {

        log.info("Received authentication request: {}", authenticationRequest.getUsername());
        final UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        try {
            Authentication authentication = authenticationManager.authenticate(token);
        } catch (final DisabledException | LockedException | BadCredentialsException e) {
            log.error("Error Authenticating Username: " + authenticationRequest.getUsername(), e);
            if (e instanceof BadCredentialsException) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        }
        UserDTO userDTO = userService.getByUsername(authenticationRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("User with username: " + authenticationRequest.getUsername() + " not found"));


        // Token Generation
        final TokenDTO tokenDTO = jwtUtil.generateToken(userDTO);


        log.info("Generated Token: {}", tokenDTO);
        return ResponseEntity.ok(MapperUtil.convert(tokenDTO, AuthenticatedTokenResponse.class));
    }
}
