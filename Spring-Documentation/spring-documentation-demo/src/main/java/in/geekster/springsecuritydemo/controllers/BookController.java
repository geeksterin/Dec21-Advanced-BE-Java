package in.geekster.springsecuritydemo.controllers;

import in.geekster.springsecuritydemo.dtos.BookDTO;
import in.geekster.springsecuritydemo.models.requests.BookCreationRequest;
import in.geekster.springsecuritydemo.models.responses.BookCreatedResponse;
import in.geekster.springsecuritydemo.services.BookService;
import in.geekster.springsecuritydemo.utils.LoggedInContext;
import in.geekster.springsecuritydemo.utils.MapperUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("books")
@Slf4j
@Api(tags = {"Books"})
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    private ResponseEntity<BookCreatedResponse> createBook(
            @Validated @RequestBody final BookCreationRequest bookCreationRequest) {
        log.info("Received Book Creation Request: {}", bookCreationRequest);
        BookDTO bookDTO = MapperUtil.convert(bookCreationRequest, BookDTO.class);
        bookDTO = bookService.createBook(bookDTO);
        log.info("Created Book: {}", bookDTO);
        return ResponseEntity.ok(MapperUtil.convert(bookDTO, BookCreatedResponse.class));
    }

    @GetMapping
    public ResponseEntity<List<BookCreatedResponse>> getAllBooks() {
        log.info("Getting All Books. Requested By: {}", LoggedInContext.getCurrentUser());
        final List<BookCreatedResponse> bookCreatedResponses = new ArrayList<>();
        bookService.getAll().forEach((bdto) -> {
            final BookCreatedResponse bResponse = MapperUtil.convert(bdto, BookCreatedResponse.class);
            bookCreatedResponses.add(bResponse);
        });
        log.info("Retrieved Book List: {}", bookCreatedResponses);
        return ResponseEntity.ok(bookCreatedResponses);
    }
}
