package in.geekster.bookmanagement.services.impls;

import com.fasterxml.jackson.databind.ObjectMapper;
import in.geekster.bookmanagement.daos.AuthorDAO;
import in.geekster.bookmanagement.daos.BookDAO;
import in.geekster.bookmanagement.exceptions.AuthorNotFoundException;
import in.geekster.bookmanagement.exceptions.InvalidParameterException;
import in.geekster.bookmanagement.models.BookCreateRequestDTO;
import in.geekster.bookmanagement.services.AuthorService;
import in.geekster.bookmanagement.services.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class DefaultBookService implements BookService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private AuthorService authorService;

    /**
     * Implementation left blank intentionally
     */
    @Override
    public List<BookDAO> getAllBooksByAuthorId(final Long id) {
        return null;
    }


    @Override
    public BookDAO createNewBook(final BookCreateRequestDTO bookCreateRequestDTO) {

        if (bookCreateRequestDTO.getPublishedOn() <= 0 ) {
            throw new InvalidParameterException("Published Year cannot be negative");
        }

        final List<Long> authorIds = bookCreateRequestDTO.getAuthorIds();
        log.debug("Looking for author details by author IDs: {}", authorIds);
        final List<AuthorDAO> authorDAOS = new ArrayList<>();

        authorIds.forEach((id) -> {
            log.debug("Current Author ID: {}", id);
            final AuthorDAO authorDAO = authorService.getAuthorByID(id);
            authorDAOS.add(authorDAO);
        });

        final BookDAO createdBookDAO = saveBookDetails(bookCreateRequestDTO, authorDAOS);
        log.debug("Created Book: {}", createdBookDAO);
        return createdBookDAO;
    }


    /**
     * Method written to imitate DB persistence behavior
     */
    private BookDAO saveBookDetails(final BookCreateRequestDTO bookCreateRequestDTO, final List<AuthorDAO> authorDAOS) {
        return null;
    }
}
