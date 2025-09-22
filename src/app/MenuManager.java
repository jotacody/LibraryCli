package app;

import model.entities.Book;
import model.entities.User;
import model.impl.BookImpl;
import model.impl.UserImpl;

import java.util.List;
import java.util.Scanner;

public class MenuManager {
    public static void menuUser(Scanner sc, UserImpl userI){
        int choiseUser = -1;
        while (choiseUser != 0){
            System.out.println("\n--- Manage Users ---");
            System.out.println("1. Insert User");
            System.out.println("2. Update User");
            System.out.println("3. Delete User");
            System.out.println("4. List Users");
            System.out.println("0. Return to main menu");
            System.out.print("> ");
            choiseUser = sc.nextInt();

            switch (choiseUser){
                case 1:
                    System.out.print("Name: ");
                    sc.nextLine();
                    String name = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    User user = new User(userI.newId(), name, email);
                    userI.insertUser(user);
                    System.out.println("User id: " + user.getId());
                    break;
                case 2:
                    System.out.print("Id: ");
                    Integer idUpdate = sc.nextInt();
                    System.out.print("Name: ");
                    sc.nextLine();
                    String nameUpdate = sc.nextLine();
                    System.out.print("Email: ");
                    String emailUpdate = sc.nextLine();
                    userI.updateUser(idUpdate, nameUpdate, emailUpdate);
                    break;
                case 3:
                    System.out.print("Id: ");
                    Integer idDelete = sc.nextInt();
                    userI.deleteUser(idDelete);
                    break;
                case 4:
                    userI.getUsers().forEach(System.out::println);
                    break;
                case 0:
                    break;
            }

        }
    }

    public static void menuBook(Scanner sc, BookImpl bookI){
        int choise = -1;
        while (choise != 0){
            System.out.println("\n--- Manage Book ---");
            System.out.println("1. Insert Book");
            System.out.println("2. Update Book");
            System.out.println("3. Delete Book");
            System.out.println("4. List Books");
            System.out.println("0. Return to main menu");
            System.out.print("> ");
            choise = sc.nextInt();

            switch (choise){
                case 1:
                    System.out.print("Title: ");
                    sc.nextLine();
                    String title = sc.nextLine();
                    System.out.print("Author: ");
                    String author = sc.nextLine();
                    System.out.print("Year: ");
                    String year = sc.nextLine();
                    Book book = new Book(bookI.newId(), title, author, year);
                    bookI.insertBook(book);
                    System.out.println("Book id: " + book.getId());
                    break;
                case 2:
                    System.out.print("Id: ");
                    Integer idUpdate = sc.nextInt();
                    System.out.print("Title: ");
                    sc.nextLine();
                    String titleUpdate = sc.nextLine();
                    System.out.print("Author: ");
                    String authorUpdate = sc.nextLine();
                    System.out.print("Year: ");
                    String yearUpdate = sc.nextLine();
                    bookI.updateBook(idUpdate, titleUpdate, authorUpdate, yearUpdate);
                    break;
                case 3:
                    System.out.print("Id: ");
                    Integer idDelete = sc.nextInt();
                    bookI.removeBook(idDelete);
                    break;
                case 4:
                    List<Book> list = bookI.getBooks();
                    if (list.isEmpty()){
                        System.out.println("No registered book");
                    }else {
                        list.forEach(System.out::println);
                    }
                    break;
                case 0:
                    break;
            }

        }
    }
}
