package in.geekster.springdatajpademo.models.responses;

import in.geekster.springdatajpademo.enums.Gender;
import in.geekster.springdatajpademo.enums.IdentityType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EmployeeResponse extends IDedResponse {
    private String firstName;
    private String lastName;
    private Gender gender;
    private IdentityType identityType;
    private String identityNumber;
    private RoleResponse role;
    private EmployeeMetaResponse employeeMeta;
}
