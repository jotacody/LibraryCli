package services;

import entities.Book;
import entities.Loan;
import entities.User;

import java.util.List;

public interface BookService {
    public void insertBook(Book b);
    public void removeBook(String title);
    public void saveToJson();
    public List<Book> loadFromJson();
}
