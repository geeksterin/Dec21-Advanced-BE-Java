package in.geekster.bookmanagement.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import in.geekster.bookmanagement.daos.AuthorDAO;
import in.geekster.bookmanagement.daos.BookDAO;
import in.geekster.bookmanagement.enums.BookCategory;
import in.geekster.bookmanagement.exceptions.AuthTokenMissingException;
import in.geekster.bookmanagement.exceptions.AuthorNotFoundException;
import in.geekster.bookmanagement.exceptions.InvalidParameterException;
import in.geekster.bookmanagement.models.*;
import in.geekster.bookmanagement.models.BookCreateRequestDTO;
import in.geekster.bookmanagement.models.Error;
import in.geekster.bookmanagement.services.AuthorService;
import in.geekster.bookmanagement.services.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("books")
@Slf4j
public class BookController {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllBooks() {
        log.info("Receiving all books req");
        final List<BookDTO> bookDTOList = new ArrayList<>();
        final ApiResponse response = new ApiResponse();
        response.setData(bookDTOList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    /**
     * Multiple GET urls based on query param
     */
    @GetMapping(params = "category")
    public ResponseEntity<ApiResponse> getAllBooksByCategory(@RequestParam(value = "category") final BookCategory category) {
        log.info("All book request by Category: {}", category);
        final List<BookDTO> bookDTOList = new ArrayList<>();
        final ApiResponse response = new ApiResponse();
        response.setData(bookDTOList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @PostMapping
    public ResponseEntity<ApiResponse> createNewBook(@RequestBody final BookCreateRequestDTO requestDTO, final HttpServletRequest request) {
        log.debug("Received Book Create Request: {}", requestDTO);
        final String authToken = request.getHeader("X-Auth-Token");

        /**
         * This here is an example of global exception handling.
         * When this controller throws an exception, it will be handled by
         * {@link in.geekster.bookmanagement.controlleradvisors.GlobalControllerAdvisor} class.
         *
         * In the absence of any such advisor, Spring's default error handling mechanism
         * will kick in
         */
        if (!StringUtils.hasText(authToken)) {
            throw new AuthTokenMissingException("No auth token found");
        }

        final String bookName = requestDTO.getName();


        if (!StringUtils.hasText(bookName)) {
            final ApiResponse apiResponse = new ApiResponse();
            apiResponse.setData("Book name is null");
            return ResponseEntity.badRequest().body(apiResponse);
        }

        /**
         * This here is an example of local exception handling. The service layer throws an exception
         * {@link AuthorNotFoundException} from {@link in.geekster.bookmanagement.services.impls.DefaultAuthorService},
         * which is then bubbled all the way up to this controller. The controller then catches it here and
         * sends a proper response.
         *
         * Why is it called local exception here? Because this particular error is not thrown in the other controller
         */
        try {
            final BookDAO bookDAO = bookService.createNewBook(requestDTO);
            final ApiResponse apiResponse = new ApiResponse();
            apiResponse.setData(bookDAO);
            return ResponseEntity.ok(apiResponse);
        } catch (final InvalidParameterException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
