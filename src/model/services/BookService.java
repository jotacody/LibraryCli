package model.services;

import model.entities.Book;

import java.util.List;

public interface BookService {
    public Integer newId();
    public void insertBook(Book b);
    public void removeBook(Integer id);
    public void updateBook(Integer id, String title, String author, String year);
    public Book searchBook(Integer bookId);
    public void saveToJson();
    public List<Book> loadFromJson();
}
