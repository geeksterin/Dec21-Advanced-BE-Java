package in.geekster.springsecuritydemo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TokenDTO {

    private String token;
    private LocalDateTime expiry;
}
