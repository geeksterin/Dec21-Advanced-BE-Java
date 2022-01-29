package in.geekster.springdatajpademo.models.responses;

import in.geekster.springdatajpademo.enums.ProjectStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProjectResponse {
    private Long id;
    private String name;
    private String description;
    private ProjectStatus status;
    private LocalDateTime finishedOn;
}
