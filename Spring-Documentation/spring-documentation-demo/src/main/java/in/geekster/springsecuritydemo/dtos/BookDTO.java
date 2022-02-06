package in.geekster.springsecuritydemo.dtos;

import in.geekster.springsecuritydemo.entities.BookEntity;
import in.geekster.springsecuritydemo.enums.BookCategory;
import in.geekster.springsecuritydemo.enums.BookStatus;
import in.geekster.springsecuritydemo.utils.MapperUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BookDTO extends IDedDTO {
    private String name;
    private String isbn;
    private BookCategory category;
    private BookStatus status;
}
