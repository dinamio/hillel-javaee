package database.dto;

import database.model.Author;
import database.model.Book;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class BookDTO {
    private int id;
    private String name;
    private HashSet<AuthorDTO> authors;
    private String postedBy;
    public BookDTO(Book arg){
        id = arg.getId();
        name = arg.getName();
        authors = new HashSet<>();
        postedBy = arg.getUser_id().getId();
        for(Author cur : arg.getAuthors()){
            try {
                authors.add(new AuthorDTO(cur));
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public HashSet<AuthorDTO> getAuthors() {
        return authors;
    }

    @Override
    public String toString() {
        StringBuilder authors = new StringBuilder();
        authors.append("[");
        for(AuthorDTO el : this.authors){
            authors.append(el.getName())
                    .append(" ")
                    .append(el.getSurname())
                    .append(", ");
        }
        authors.delete(authors.length()-2, authors.length());
        authors.append("]");
        return "BookDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", authors=" + authors.toString() +
                "}";
    }

    public static List<BookDTO> booksToDTO(List<Book> arg){
        List<BookDTO> result = new ArrayList<>(arg.size());

        for (Book cur : arg) {
//                System.out.print(cur.toString() + " authors: ");
//                cur.setAuthors(cur.getAuthors());
            result.add(new BookDTO(cur));
//                for (Author curAuth : cur.getAuthors()) {
//                    System.out.print(curAuth.getName() + curAuth.getSurname() + " ");
//                }
//                System.out.println();
        }


        return result;
    }
}