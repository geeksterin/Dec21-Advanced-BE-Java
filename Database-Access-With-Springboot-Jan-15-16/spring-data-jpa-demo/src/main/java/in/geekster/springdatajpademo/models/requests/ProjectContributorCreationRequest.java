package in.geekster.springdatajpademo.models.requests;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ProjectContributorCreationRequest {

    @NotNull
    @Size(min = 0)
    private Long employeeId;
}
