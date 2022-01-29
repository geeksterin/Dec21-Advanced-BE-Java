package in.geekster.springdatajpademo.models.responses;

import lombok.Data;

@Data
public class Error {
    private String code;
    private String description;

    public static Error create(final String code, final String description) {
        final Error error = new Error();
        error.setCode(code);
        error.setDescription(description);
        return error;
    }
}
