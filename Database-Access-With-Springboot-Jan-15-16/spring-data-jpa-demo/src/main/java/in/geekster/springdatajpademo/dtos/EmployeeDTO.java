package in.geekster.springdatajpademo.dtos;

import in.geekster.springdatajpademo.enums.Gender;
import in.geekster.springdatajpademo.enums.IdentityType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EmployeeDTO extends AbstractDTO {
    private String firstName;
    private String lastName;
    private Gender gender;
    private IdentityType identityType;
    private String identityNumber;
    private RoleDTO role;
    private EmployeeMetaDTO employeeMeta;

}
