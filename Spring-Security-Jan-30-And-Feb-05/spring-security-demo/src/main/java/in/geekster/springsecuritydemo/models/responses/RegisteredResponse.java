package in.geekster.springsecuritydemo.models.responses;

import in.geekster.springsecuritydemo.enums.AccountStatus;
import in.geekster.springsecuritydemo.enums.Role;
import lombok.Data;

@Data
public class RegisteredResponse implements IResponse {
    private String firstName;
    private String lastName;
    private Role role;
    private AccountStatus status;
    private String username;
}
