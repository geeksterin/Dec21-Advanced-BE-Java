package in.geekster.springdatajpademo.models.responses;

import in.geekster.springdatajpademo.enums.Department;
import in.geekster.springdatajpademo.enums.Designation;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RoleResponse extends IDedResponse {
    private Department department;
    private Designation designation;
}
