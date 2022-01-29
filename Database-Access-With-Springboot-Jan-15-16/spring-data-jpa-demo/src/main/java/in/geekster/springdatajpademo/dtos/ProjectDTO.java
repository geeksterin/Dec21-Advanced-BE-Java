package in.geekster.springdatajpademo.dtos;

import in.geekster.springdatajpademo.enums.ProjectStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProjectDTO extends AbstractDTO {
    private String name;
    private String description;
    private ProjectStatus status;
    private LocalDateTime finishedOn;
}
