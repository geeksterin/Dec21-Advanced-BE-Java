package in.geekster.bookmanagement.services.impls;

import in.geekster.bookmanagement.daos.AuthorDAO;
import in.geekster.bookmanagement.services.AuthorService;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Service("fastService")
public class EfficientAuthorService implements AuthorService {

    @Override
    public AuthorDAO getAuthorByName(String name) {
        return null;
    }
}
