package in.geekster.bookmanagement.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import in.geekster.bookmanagement.daos.AuthorDAO;
import in.geekster.bookmanagement.daos.BookDAO;
import in.geekster.bookmanagement.enums.BookCategory;
import in.geekster.bookmanagement.enums.Status;
import in.geekster.bookmanagement.models.ApiResponse;
import in.geekster.bookmanagement.models.BookDTO;
import in.geekster.bookmanagement.models.BookSearchRequest;
import in.geekster.bookmanagement.services.AuthorService;
import in.geekster.bookmanagement.services.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    @GetMapping
    public ResponseEntity<ApiResponse> getAllBooksByCategory(@RequestParam("category") final BookCategory category) {
        log.info("All book request by Category: {}", category);
        final List<BookDTO> bookDTOList = new ArrayList<>();
        final ApiResponse response = new ApiResponse();
        response.setData(bookDTOList);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllBooksByCategory(@RequestParam("author") final String name) {
        log.info("All book request by Author: {}", name);
        final AuthorDAO authorDAO = authorService.getAuthorByName(name);
        log.info("Fetched Author: {}", authorDAO);
        final List<BookDAO> bookDAOList = bookService.getAllBooksByAuthorId(authorDAO.getId());
        final List<BookDTO> bookDTOList = new ArrayList<>();

        bookDAOList.forEach((bookDAO -> {
            final BookDTO bookDTO = objectMapper.convertValue(bookDAO, BookDTO.class);
            bookDTOList.add(bookDTO);
        }));
        final ApiResponse response = new ApiResponse();
        response.setData(bookDTOList);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("search")
    public ResponseEntity<ApiResponse> searchBookByParams(@RequestBody final BookSearchRequest searchRequest) {
        return null;
    }



}
