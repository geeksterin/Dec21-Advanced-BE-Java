package in.geekster.bookmanagement.models;

import in.geekster.bookmanagement.enums.BookCategory;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
public class BookDTO {

    private Long id;
    private String name;
    private BookCategory category;
    private List<Author> authors;
    private LocalDate publishedOn;
    private String publisher;
}
