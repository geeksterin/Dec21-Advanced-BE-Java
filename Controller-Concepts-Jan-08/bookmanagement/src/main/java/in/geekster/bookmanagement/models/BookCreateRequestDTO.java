package in.geekster.bookmanagement.models;

import in.geekster.bookmanagement.enums.BookCategory;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * These are DTOs or Data Transfer Objects. Purpose of such classes is to just carry the data
 * from Client to Server. When client sends a request, it gets mapped on to this DTO.
 * These are different from DAOs since DAOs represent database entities and may have more
 * fields that are not captured here
 */

@Data
public class BookCreateRequestDTO {
    private String name;
    private String publisher;
    private Integer publishedOn;
    private List<Long> authorIds;
    private BookCategory category;
}
