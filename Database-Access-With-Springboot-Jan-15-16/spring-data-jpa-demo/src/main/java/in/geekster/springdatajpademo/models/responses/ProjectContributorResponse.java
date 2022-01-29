package in.geekster.springdatajpademo.models.responses;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProjectContributorResponse extends IDedResponse {

    private Long projectId;
    private Long employeeId;
}
