package in.geekster.springsecuritydemo.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public abstract class AuditedDTO {
    private Long createdBy;
    private Long updatedBy;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
