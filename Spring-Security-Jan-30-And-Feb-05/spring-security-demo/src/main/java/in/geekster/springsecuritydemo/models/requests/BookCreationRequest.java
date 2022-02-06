package in.geekster.springsecuritydemo.models.requests;

import in.geekster.springsecuritydemo.enums.BookCategory;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class BookCreationRequest {

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String isbn;

    @NotNull
    private BookCategory category;
}
