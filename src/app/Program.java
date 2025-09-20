package app;

import dao.daoimpl.BookDaoImpl;
import dao.daoimpl.UserDaoImpl;
import entities.Book;
import entities.User;

public class Program {
    public static void main(String[] args) {
        User user = new User("Joao", "joao@gmail.com");
        UserDaoImpl userDao = new UserDaoImpl();
        Book book1 = new Book("Harry Potter and the Philosopher's Stone", "J.K. Rowling", "1997", "haha");
        Book book2 = new Book("The Hobbit", "J.R.R. Tolkien", "1937", "haha");
        Book book3 = new Book("1984", "George Orwell", "1949", "haha");
        BookDaoImpl bookDao = new BookDaoImpl();
        bookDao.insertBook(book1);
        bookDao.insertBook(book2);
        bookDao.insertBook(book3);
        bookDao.getList().forEach(System.out::println);
        userDao.insertUser(user);
        userDao.borrowBook(user, book1);
        userDao.borrowBook(user, book3);
        userDao.borrowBook(user, book2);

        userDao.getList().forEach(System.out::println);
        bookDao.getList().forEach(System.out::println);
        userDao.returnBook(user, book1);
        userDao.getList().forEach(System.out::println);
        bookDao.getList().forEach(System.out::println);
    }

}
