package in.geekster.springsecuritydemo.services;

import in.geekster.springsecuritydemo.dtos.BookDTO;

import java.util.List;

public interface BookService {

    BookDTO createBook(final BookDTO bookDTO);
    List<BookDTO> getAll();
}
