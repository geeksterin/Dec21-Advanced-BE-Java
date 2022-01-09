package in.geekster.bookmanagement.controlleradvisors;

import in.geekster.bookmanagement.exceptions.AuthTokenMissingException;
import in.geekster.bookmanagement.models.ApiResponse;
import in.geekster.bookmanagement.models.Error;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * The purpose of this class is to handle exceptions occurring globally in any controller
 */

@RestControllerAdvice
@Slf4j
public class GlobalControllerAdvisor {

    @ExceptionHandler({AuthTokenMissingException.class})
    public ResponseEntity<ApiResponse> handleAuthTokenMissingException(final AuthTokenMissingException authTokenMissingException) {
        log.error("Got Auth token missing error:\n", authTokenMissingException);
        final Error error = new Error();
        error.setErrorCode("ERR001");
        error.setErrorMessage(authTokenMissingException.getMessage());
        final ApiResponse apiResponse = new ApiResponse();
        apiResponse.setError(error);
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }
}
