package in.geekster.bookmanagement.controlleradvisors;

import in.geekster.bookmanagement.controllers.AuthorController;
import in.geekster.bookmanagement.exceptions.AuthorNotFoundException;
import in.geekster.bookmanagement.models.ApiResponse;
import in.geekster.bookmanagement.models.Error;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * This class here applies only to {@link in.geekster.bookmanagement.controllers.AuthorController}
 */

@RestControllerAdvice(assignableTypes = {AuthorController.class})
@Slf4j
public class AuthorControllerAdvisor {

    @ExceptionHandler({AuthorNotFoundException.class})
    public ResponseEntity<ApiResponse> handleAuthorNotFoundException(final AuthorNotFoundException e) {
        log.error("Handling Author Not found exception:\n", e);
        final Error error = new Error();
        error.setErrorCode("ERR001");
        error.setErrorMessage(e.getMessage());
        final ApiResponse apiResponse = new ApiResponse();
        apiResponse.setError(error);
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }
}
