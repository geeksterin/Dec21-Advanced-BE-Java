package in.geekster.bookmanagement.models;

import in.geekster.bookmanagement.enums.BookCategory;

import java.time.LocalDate;

public class BookSearchRequest {

    private String bookName;
    private String authorName;
    private String publisher;
    private LocalDate publishedOn;
    private BookCategory category;
}
