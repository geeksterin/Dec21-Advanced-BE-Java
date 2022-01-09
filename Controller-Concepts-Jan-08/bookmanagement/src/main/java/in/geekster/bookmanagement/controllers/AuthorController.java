package in.geekster.bookmanagement.controllers;

import in.geekster.bookmanagement.daos.AuthorDAO;
import in.geekster.bookmanagement.exceptions.AuthTokenMissingException;
import in.geekster.bookmanagement.exceptions.AuthorNotFoundException;
import in.geekster.bookmanagement.models.ApiResponse;
import in.geekster.bookmanagement.models.Error;
import in.geekster.bookmanagement.services.AuthorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

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
    public ResponseEntity<ApiResponse> getAuthorById(@PathVariable("authorId") final Long id, final HttpServletRequest httpServletRequest) throws Exception {
        log.info("Fetching author details by Author ID: {}", id);
        final String authToken = httpServletRequest.getHeader("X-Auth-Token");

        /**
         * This here demonstrates global exception handling.
         * Because this error is common to both {@link AuthorController} and {@link BookController},
         * the handling of this particular error is done in some place common
         * {@link in.geekster.bookmanagement.controlleradvisors.GlobalControllerAdvisor}
         */
        if (!StringUtils.hasText(authToken)) {
            throw new AuthTokenMissingException("Auth token missing");
        }
        final AuthorDAO authorDAO = authorService.getAuthorByID(id);
        final ApiResponse apiResponse = new ApiResponse();
        apiResponse.setData(authorDAO);
        return ResponseEntity.ok(apiResponse);
    }


}
