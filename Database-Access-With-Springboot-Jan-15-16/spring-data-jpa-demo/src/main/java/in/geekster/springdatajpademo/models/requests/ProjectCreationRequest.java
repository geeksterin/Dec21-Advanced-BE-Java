package in.geekster.springdatajpademo.models.requests;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ProjectCreationRequest {

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 255)
    private String name;

    @Size(min = 1, max = 255)
    private String description;
}
