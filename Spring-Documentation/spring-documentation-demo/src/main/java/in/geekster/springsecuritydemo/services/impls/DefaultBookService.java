package in.geekster.springsecuritydemo.services.impls;

import in.geekster.springsecuritydemo.dtos.BookDTO;
import in.geekster.springsecuritydemo.entities.BookEntity;
import in.geekster.springsecuritydemo.enums.BookStatus;
import in.geekster.springsecuritydemo.repositories.BookRepository;
import in.geekster.springsecuritydemo.services.BookService;
import in.geekster.springsecuritydemo.utils.LoggedInContext;
import in.geekster.springsecuritydemo.utils.MapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class DefaultBookService implements BookService {


    @Autowired
    private BookRepository bookRepository;

    @Override
    public BookDTO createBook(final BookDTO bookDTO) {
        log.debug("Creating Book with Book Details: {}", bookDTO);
        BookEntity bookEntity = MapperUtil.convert(bookDTO, BookEntity.class);
        bookEntity.setCreatedBy(LoggedInContext.getCurrentUser());
        bookEntity.setCreatedOn(LocalDateTime.now());
        bookEntity.setStatus(BookStatus.AVAILABLE);
        bookEntity = bookRepository.save(bookEntity);
        log.debug("Saved Book: {}", bookEntity);
        return MapperUtil.convert(bookEntity, BookDTO.class);
    }

    @Override
    public List<BookDTO> getAll() {
        final List<BookDTO> bookDTOS = new ArrayList<>();
        bookRepository.findAll().forEach((bookEntity) -> {
            final BookDTO b = MapperUtil.convert(bookEntity, BookDTO.class);
            bookDTOS.add(b);
        });
        return bookDTOS;
    }
}
