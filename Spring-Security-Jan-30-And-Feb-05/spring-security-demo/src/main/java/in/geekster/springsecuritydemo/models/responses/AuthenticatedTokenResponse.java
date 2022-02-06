package in.geekster.springsecuritydemo.models.responses;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AuthenticatedTokenResponse implements IResponse {
    private String token;
    private LocalDateTime expiry;
}
