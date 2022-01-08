package in.geekster.bookmanagement.services.impls;

import in.geekster.bookmanagement.daos.AuthorDAO;
import in.geekster.bookmanagement.services.AuthorService;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Service
@Primary
public class DefaultAuthorService implements AuthorService {


    @Override
    public AuthorDAO getAuthorByName(String name) {
        return null;
    }
}
