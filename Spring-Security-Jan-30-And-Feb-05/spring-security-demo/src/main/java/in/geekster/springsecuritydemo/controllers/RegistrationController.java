package in.geekster.springsecuritydemo.controllers;

import in.geekster.springsecuritydemo.dtos.UserDTO;
import in.geekster.springsecuritydemo.models.requests.RegistrationRequest;
import in.geekster.springsecuritydemo.models.responses.RegisteredResponse;
import in.geekster.springsecuritydemo.services.UserService;
import in.geekster.springsecuritydemo.utils.MapperUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("registrations")
@Slf4j
public class RegistrationController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<RegisteredResponse> register(
            @Validated @RequestBody final RegistrationRequest registrationRequest) {
        log.info("Received Registration Request: {}", registrationRequest.getUsername());
        UserDTO userDTO = MapperUtil.convert(registrationRequest, UserDTO.class);
        userDTO = userService.createUser(userDTO);
        log.info("Created User: {}", userDTO);
        final RegisteredResponse response = MapperUtil.convert(userDTO, RegisteredResponse.class);
        return ResponseEntity.ok(response);
    }
}
