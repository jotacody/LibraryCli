package dao;

import entities.Book;

public interface BookDAO {
    public void insertBook(Book b);
    public void removeBook(String title);
}
