package in.geekster.bookmanagement.services;


import in.geekster.bookmanagement.daos.AuthorDAO;
import in.geekster.bookmanagement.exceptions.AuthorNotFoundException;

public interface AuthorService {

    AuthorDAO getAuthorByName(final String name);
    AuthorDAO getAuthorByID(final Long id);

}
