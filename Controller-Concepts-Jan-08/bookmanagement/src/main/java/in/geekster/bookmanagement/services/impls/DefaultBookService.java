package in.geekster.bookmanagement.services.impls;

import in.geekster.bookmanagement.daos.BookDAO;
import in.geekster.bookmanagement.services.BookService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Service
public class DefaultBookService implements BookService {


    @Override
    public List<BookDAO> getAllBooksByAuthorId(Long id) {
        return null;
    }
}
