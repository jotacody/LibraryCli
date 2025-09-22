package model.entities;

import java.util.Objects;

public class Book {
    private Integer id;
    private String title;
    private String author;
    private String year;
    private Boolean loan;

    public Book() {
    }

    public Book(Integer id, String title, String author, String year) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.loan = false;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Boolean getLoan() {
        return loan;
    }

    public void setLoan(Boolean loan) {
        this.loan = loan;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "{\nBook> \n" +
                "Book id: " + id +
                "\nTitle: " + title +
                "\nAuthor: " + author +
                "\nYear: " + year +
                "\nLoan: " + loan +
                "\n}"
                ;
    }

}
