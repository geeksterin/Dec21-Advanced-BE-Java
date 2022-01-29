package in.geekster.springdatajpademo.controlleradvisors;

import in.geekster.springdatajpademo.constants.ErrorCode;
import in.geekster.springdatajpademo.models.responses.ApiResponse;
import in.geekster.springdatajpademo.models.responses.Error;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalControllerAdvisor {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ApiResponse<Void>> handleGenericException(final Exception e) {
        log.error("Handling Global Error:\n", e);
        final Error error = Error.create(ErrorCode.INTERNAL_SERVER_ERROR, "Something Went Wrong");
        return ResponseEntity.internalServerError().body(ApiResponse.error(error));
    }
}
