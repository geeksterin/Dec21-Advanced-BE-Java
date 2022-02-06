package in.geekster.springsecuritydemo.controllers;

import in.geekster.springsecuritydemo.models.responses.UserCreatedResponse;
import in.geekster.springsecuritydemo.services.BookService;
import in.geekster.springsecuritydemo.services.UserService;
import in.geekster.springsecuritydemo.utils.LoggedInContext;
import in.geekster.springsecuritydemo.utils.MapperUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("users")
@Slf4j
@Api(tags = {"Users"})
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserCreatedResponse>> getAllUsers() {
        log.info("Getting all Users requested by: {}", LoggedInContext.getCurrentUser());
        final List<UserCreatedResponse> userCreatedResponses = new ArrayList<>();
        userService.getAll().forEach((udto) -> {
            final UserCreatedResponse uResponse = MapperUtil.convert(udto, UserCreatedResponse.class);
            userCreatedResponses.add(uResponse);
        });
        log.info("Users: {}", userCreatedResponses);
        return ResponseEntity.ok(userCreatedResponses);
    }
}
