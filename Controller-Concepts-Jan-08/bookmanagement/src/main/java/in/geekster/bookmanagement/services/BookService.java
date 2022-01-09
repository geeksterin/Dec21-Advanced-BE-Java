package in.geekster.bookmanagement.services;

import in.geekster.bookmanagement.daos.BookDAO;
import in.geekster.bookmanagement.models.BookCreateRequestDTO;

import java.util.List;

public interface BookService {

    List<BookDAO> getAllBooksByAuthorId(final Long id);

    BookDAO createNewBook(final BookCreateRequestDTO bookCreateRequestDTO);

}
