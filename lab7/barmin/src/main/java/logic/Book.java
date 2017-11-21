package logic;


public class Book {
    private String name;
    private String author;
    private String postedBy;

    public Book(String name, String author, String postedBy) {
        this.name = name;
        this.author = author;
        this.postedBy = postedBy;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getPostedBy() {
        return postedBy;
    }


}
