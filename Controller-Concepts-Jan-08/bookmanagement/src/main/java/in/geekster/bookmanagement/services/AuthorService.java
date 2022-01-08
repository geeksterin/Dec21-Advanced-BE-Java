package in.geekster.bookmanagement.services;


import in.geekster.bookmanagement.daos.AuthorDAO;

public interface AuthorService {

    AuthorDAO getAuthorByName(final String name);

}
