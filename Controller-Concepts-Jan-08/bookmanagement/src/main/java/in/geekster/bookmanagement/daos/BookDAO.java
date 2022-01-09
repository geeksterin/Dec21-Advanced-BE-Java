package in.geekster.bookmanagement.daos;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * The purpose of a DAO class is to basically represent the object from Database
 * It is always a good idea to have DAOs and DTOs separately because we may have
 * many fields in our DAOs that we do not want exposed to our clients using DTOs
 */

@Data
public class BookDAO {
    public Long id;
    public String name;
    public List<AuthorDAO> authors;
    private LocalDate publishedOn;
    private String publisher;
    public LocalDateTime createOn;
    public Long createdBy;
}
