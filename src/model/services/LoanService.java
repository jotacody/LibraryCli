package model.services;

import model.entities.Book;
import model.entities.Loan;
import model.entities.User;

import java.util.List;

public interface LoanService {
    public void insertLoan(Loan l);
    public void deleteLoan(Loan l);
    public void borrowBook(User u, Book b);
    public void returnBook(User u, Book b);
    public void saveToJson();
    public List<Loan> loadFromJson();
}
