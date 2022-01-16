package in.geekster.bookmanagement.controlleradvisors;

import in.geekster.bookmanagement.controllers.AuthorController;
import in.geekster.bookmanagement.controllers.BookController;
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

   @ExceptionHandler(value = {AuthTokenMissingException.class, IllegalArgumentException.class})
   public ResponseEntity<String> handleNoAuthTokenFoundException(final RuntimeException exception) {

       if (exception instanceof AuthTokenMissingException) {

       }
       // glsjlsfjlsjf
       return ResponseEntity.badRequest().body("No Auth Token");
   }

//    @ExceptionHandler(value = {IllegalArgumentException.class})
//    public ResponseEntity<String> handleIllegalArgExc(final IllegalArgumentException exception) {
//        // glsjlsfjlsjf
//        return ResponseEntity.badRequest().build();
//    }
}
