package in.geekster.springsecuritydemo.models.requests;

import in.geekster.springsecuritydemo.enums.Role;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RegistrationRequest {

    @NotNull(message = "firstName cannot be null")
    @NotEmpty(message = "firstName cannot be empty")
    @Size(min = 3, max = 100, message = "firstName should be within 3 to 100 characters long")
    private String firstName;

    @NotEmpty(message = "firstName cannot be empty")
    @Size(min = 3, max = 100, message = "firstName should be within 3 to 100 characters long")
    private String lastName;

    @NotNull(message = "role cannot be null")
    private Role role;

    @NotNull(message = "username cannot be null")
    @NotEmpty(message = "username cannot be empty")
    @Size(min = 1, message = "username should be at least 1 character long")
    private String username;

    @NotNull(message = "password cannot be null")
    @NotEmpty(message = "password cannot be empty")
    @Size(min = 8, message = "password should be at least 8 characters long")
    private String password;
}
