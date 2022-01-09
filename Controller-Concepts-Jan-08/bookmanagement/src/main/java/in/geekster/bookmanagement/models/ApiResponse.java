package in.geekster.bookmanagement.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * The purpose of this class is to be a wrapper around our actual response data
 * Benefits of having a wrapper class is to ensure if our response structure
 * changes in the future (ie. sending Map instead of List), it would not affect the consuming clients.
 * Also, using List / Maps as response leaves no room for adding more field to the response
 */

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse {
    private Object data;
    private Error error;
}
