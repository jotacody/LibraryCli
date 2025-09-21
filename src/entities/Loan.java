package entities;

import java.time.LocalDate;
import java.util.Objects;

public class Loan {
    private Integer id;
    private User user;
    private Book book;
    private String checkOutDate;
    private String returnDate;

    public Loan() {
    }

    public Loan(User user, Book book) {
        this.user = user;
        this.book = book;
        this.checkOutDate = LocalDate.now().toString();
        this.returnDate = LocalDate.now().plusDays(7).toString();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Loan loan = (Loan) o;
        return Objects.equals(id, loan.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Loan Id: " + id +
                "\n User: " + user.getName() +
                "\n Book: " + book.getTitle() +
                "\n Check-out: " + checkOutDate.toString() +
                "\n Return: " + returnDate.toString();
    }
}
