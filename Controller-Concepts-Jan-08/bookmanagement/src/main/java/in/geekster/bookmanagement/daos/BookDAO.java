package in.geekster.bookmanagement.daos;

import lombok.Data;

import java.util.List;

@Data
public class BookDAO {

    public Long id;
    public String name;
    public List<Long> authorIds;
}
