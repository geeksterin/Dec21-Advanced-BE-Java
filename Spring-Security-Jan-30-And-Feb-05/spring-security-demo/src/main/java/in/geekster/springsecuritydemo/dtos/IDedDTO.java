package in.geekster.springsecuritydemo.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public abstract class IDedDTO extends AuditedDTO {
    private Long id;
}
