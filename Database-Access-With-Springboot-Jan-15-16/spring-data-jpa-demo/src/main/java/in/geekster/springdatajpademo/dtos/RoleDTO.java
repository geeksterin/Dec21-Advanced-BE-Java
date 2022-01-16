package in.geekster.springdatajpademo.dtos;

import in.geekster.springdatajpademo.enums.Department;
import in.geekster.springdatajpademo.enums.Designation;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RoleDTO extends AbstractDTO {

    private Department department;
    private Designation designation;
}
