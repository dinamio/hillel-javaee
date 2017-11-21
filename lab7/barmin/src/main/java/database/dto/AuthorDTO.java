package database.dto;

import database.model.Author;

public class AuthorDTO {
    private Integer author_id;
    private String name;
    private String surname;

    public AuthorDTO(Author arg) {
        this.author_id = arg.getAuthor_id();
        this.name = arg.getName();
        this.surname = arg.getSurname();
    }

    public Integer getAuthor_id() {
        return author_id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
}
