package in.geekster.bookmanagement.services.impls;

import in.geekster.bookmanagement.daos.AuthorDAO;
import in.geekster.bookmanagement.exceptions.AuthorNotFoundException;
import in.geekster.bookmanagement.services.AuthorService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class DefaultAuthorService implements AuthorService {


    /**
     * Implementation left blank intentionally
     */
    @Override
    public AuthorDAO getAuthorByName(final String name) {
        return null;
    }

    /**
     * This is to show how Service layer can throw exceptions
     * This exception will eventually be bubbled up all the way
     * to the Controller and be caught there.
     */
    @Override
    public AuthorDAO getAuthorByID(final Long id) {
        final String errorMessage = String.format("Could not find any author by ID: %s", id);
        throw new AuthorNotFoundException(errorMessage);
    }
}
