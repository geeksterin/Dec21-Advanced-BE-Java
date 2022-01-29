package in.geekster.springdatajpademo.models.requests;

import in.geekster.springdatajpademo.enums.Gender;
import in.geekster.springdatajpademo.enums.IdentityType;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.*;

@Data
public class EmployeeCreationRequest {

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 100)
    private String firstName;

    @NotEmpty
    @Size(min = 1, max = 100)
    private String lastName;

    @NotNull
    private Gender gender;

    @NotNull
    private IdentityType identityType;

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 50, message = "ID number must be within 1 to 50 characters")
    private String identityNumber;

    @NotNull
    @Min(0)
    private Long roleId;

    @Valid
    private EmployeeMetaRequest employeeMeta;
}
