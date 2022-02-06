package in.geekster.springsecuritydemo.dtos;

import in.geekster.springsecuritydemo.enums.AccountStatus;
import in.geekster.springsecuritydemo.enums.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserDTO extends IDedDTO {
    private String firstName;
    private String lastName;
    private Role role;
    private AccountStatus status;
    private String username;
    private String password;
}
