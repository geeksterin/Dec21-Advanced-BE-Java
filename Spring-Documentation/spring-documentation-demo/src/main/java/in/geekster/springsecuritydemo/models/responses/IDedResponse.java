package in.geekster.springsecuritydemo.models.responses;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class IDedResponse extends AuditedResponse {
    private Long id;
}
