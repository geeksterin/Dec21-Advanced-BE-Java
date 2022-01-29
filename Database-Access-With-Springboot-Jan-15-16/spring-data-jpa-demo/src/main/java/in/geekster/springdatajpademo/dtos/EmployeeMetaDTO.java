package in.geekster.springdatajpademo.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EmployeeMetaDTO extends AbstractDTO {
    private String phone;
    private String email;
}
