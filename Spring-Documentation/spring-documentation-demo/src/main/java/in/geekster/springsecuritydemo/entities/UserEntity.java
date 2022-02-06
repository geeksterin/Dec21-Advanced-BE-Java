package in.geekster.springsecuritydemo.entities;

import in.geekster.springsecuritydemo.constants.EntityName;
import in.geekster.springsecuritydemo.constants.TableName;
import in.geekster.springsecuritydemo.enums.AccountStatus;
import in.geekster.springsecuritydemo.enums.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity(name = EntityName.USER)
@Table(name = TableName.USER, uniqueConstraints = {
        @UniqueConstraint(name = "uk_user_username", columnNames = {"username"})
})
public class UserEntity extends IDedEntity {

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "authority", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

}
