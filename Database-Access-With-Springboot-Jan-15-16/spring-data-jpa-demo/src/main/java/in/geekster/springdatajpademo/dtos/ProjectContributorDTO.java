package in.geekster.springdatajpademo.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProjectContributorDTO extends AbstractDTO {

    private Long projectId;
    private Long employeeId;
}
