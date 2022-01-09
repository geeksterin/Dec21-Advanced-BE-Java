package in.geekster.bookmanagement.models;

import lombok.Data;

@Data
public class Error {
    private String errorMessage;
    private String errorCode;
}
