package model.services;

import model.entities.Book;
import model.entities.Loan;
import model.entities.User;

import java.util.List;
import java.util.Map;

public interface LoanService {
    public void borrowBook(Loan l);
    public void returnBook(Loan l);
    public Loan searchLoan(Integer loanId);
    public void saveToJson();
    public Map<Integer, Loan> loadFromJson();
}
