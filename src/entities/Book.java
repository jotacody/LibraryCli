package entities;

import java.util.Objects;

public class Book {
    private Integer id;
    private String title;
    private String author;
    private String year;
    private String synopsis;
    private Boolean loan;

    public Book() {
    }

    public Book(String title, String author, String year, String synopsis) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.synopsis = synopsis;
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

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
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
        return Objects.equals(title, book.title);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(title);
    }

    @Override
    public String toString() {
        return "[Book: " +
                "Title: " + title +
                ", Author: " + author +
                ", Loan: " + loan +
                "]"
                ;
    }

}
