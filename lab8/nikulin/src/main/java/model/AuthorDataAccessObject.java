package model;

public interface AuthorDataAccessObject {

    Author createAuthor(Author author);

    Author getAuthorByName(String name);

    Author getAuthorById(long id);
}
