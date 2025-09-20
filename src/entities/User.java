package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private Integer id;
    private String name;
    private String email;
    private List<Book> loanBooks = new ArrayList<>();

    public User() {
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Book> getLoanBooks() {
        return loanBooks;
    }

    public void setLoanBooks(List<Book> loanBooks) {
        this.loanBooks = loanBooks;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email);
    }

    @Override
    public String toString() {
        return "User {" +
                "\n  Name: " + name +
                "\n  Email: " + email +
                "\n  Loaned Books: " + loanBooks +
                "\n}";
    }
}
