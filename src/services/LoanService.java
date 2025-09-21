package services;

import entities.Book;
import entities.Loan;
import entities.User;

import java.util.List;

public interface LoanService {
    public void insertLoan(Loan l);
    public void deleteLoan(Loan l);
    public void borrowBook(User u, Book b);
    public void returnBook(User u, Book b);
    public void saveToJson();
    public List<Loan> loadFromJson();
}
