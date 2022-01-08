package in.geekster.bookmanagement.services;

import in.geekster.bookmanagement.daos.BookDAO;

import java.util.List;

public interface BookService {

    List<BookDAO> getAllBooksByAuthorId(final Long id);
}
