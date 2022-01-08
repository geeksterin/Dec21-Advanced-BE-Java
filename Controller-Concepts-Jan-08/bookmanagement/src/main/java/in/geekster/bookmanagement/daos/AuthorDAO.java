package in.geekster.bookmanagement.daos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AuthorDAO {

    private Long id;
    private String name;
    private LocalDate createdOn;
}
