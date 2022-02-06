package in.geekster.springsecuritydemo.models.responses;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public abstract class AuditedResponse implements IResponse {
    private Long createdBy;
    private Long updatedBy;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
