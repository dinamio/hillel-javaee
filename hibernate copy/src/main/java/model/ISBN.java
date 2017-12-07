package model;

import javax.persistence.*;

/**
 * Created by eugen on 11/3/17.
 */
@Entity
public class ISBN {

    @Id
    @GeneratedValue
    Integer id;
    @Column(name = "country_number")
    String countryNumber;
    @Column(name = "typography_number")
    String typographyNumber;
    @Column(name = "book_number")
    String bookNumber;

    @Override
    public String toString() {
        return "ISBN{" +
                "controlNumber='" + controlNumber + '\'' +
                ", bookNumber='" + bookNumber + '\'' +
                ", typographyNumber='" + typographyNumber + '\'' +
                ", countryNumber='" + countryNumber + '\'' +
                ", id=" + id +
                '}';
    }

    @Column(name = "control_number")
    String controlNumber;
    @OneToOne(mappedBy = "isbn")
    Book book;

    public ISBN() {
    }

    public ISBN(String countryNumber, String typographyNumber, String bookNumber, String controlNumber) {
        this.countryNumber = countryNumber;
        this.typographyNumber = typographyNumber;
        this.bookNumber = bookNumber;
        this.controlNumber = controlNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountryNumber() {
        return countryNumber;
    }

    public void setCountryNumber(String countryNumber) {
        this.countryNumber = countryNumber;
    }

    public String getTypographyNumber() {
        return typographyNumber;
    }

    public void setTypographyNumber(String typographyNumber) {
        this.typographyNumber = typographyNumber;
    }

    public String getBookNumber() {
        return bookNumber;
    }

    public void setBookNumber(String bookNumber) {
        this.bookNumber = bookNumber;
    }

    public String getControlNumber() {
        return controlNumber;
    }

    public void setControlNumber(String controlNumber) {
        this.controlNumber = controlNumber;
    }
}
