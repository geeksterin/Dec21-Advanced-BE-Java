package in.geekster.bookmanagement.controllers;

import in.geekster.bookmanagement.models.ApiResponse;
import in.geekster.bookmanagement.services.AuthorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("authors")
@Slf4j
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllAuthors() {
        return null;
    }

    @GetMapping("{authorId}")
    public ResponseEntity<ApiResponse> getAuthorById(@PathVariable("authorId") final Long id) {
        return null;
    }


    @GetMapping
    public ResponseEntity<ApiResponse> getAllAuthorsByBookId(@RequestParam("bookId") final Long id) {
        return null;
    }


}
