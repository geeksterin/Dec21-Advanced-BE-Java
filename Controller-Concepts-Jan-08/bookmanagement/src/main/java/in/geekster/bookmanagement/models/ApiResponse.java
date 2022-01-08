package in.geekster.bookmanagement.models;

import in.geekster.bookmanagement.enums.Status;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class ApiResponse {
    private Object data;
}
