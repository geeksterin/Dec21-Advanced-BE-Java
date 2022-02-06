package in.geekster.springsecuritydemo.models.responses;

import in.geekster.springsecuritydemo.enums.BookCategory;
import in.geekster.springsecuritydemo.enums.BookStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BookCreatedResponse extends IDedResponse {

    private String name;
    private String isbn;
    private BookCategory category;
    private BookStatus status;
}
