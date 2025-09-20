package dao;

import entities.Book;
import entities.User;

public interface UserDAO {
    public void insertUser(User u);
    public void deleteUser(Integer id);
    public void borrowBook(User u, Book b);
}
