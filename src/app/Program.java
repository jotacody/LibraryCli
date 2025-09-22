package app;

import model.entities.Book;
import model.entities.Loan;
import model.entities.User;
import model.impl.BookImpl;
import model.impl.LoanImpl;
import model.impl.UserImpl;

import java.awt.*;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int option = -1;
        UserImpl userI = new UserImpl();
        userI.loadFromJson();
        BookImpl bookI = new BookImpl();
        bookI.loadFromJson();
        LoanImpl loanI = new LoanImpl();
        loanI.loadFromJson();

        try {

            while (option != 0){
                System.out.print(
                        "\nBook Store Menu\n" +
                        "1) Manage Users\n" +
                        "2) Manage Books\n" +
                        "3) Manage Loan\n" +
                        "0) exit\n" +
                                "> "
                );
                option = sc.nextInt();

                switch (option){
                    case 1:
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
                        break;
                    case 2:
                        int choiseBook = -1;
                        while (choiseBook != 0){
                            System.out.println("\n--- Manage Book ---");
                            System.out.println("1. Insert Book");
                            System.out.println("2. Update Book");
                            System.out.println("3. Delete Book");
                            System.out.println("4. List Books");
                            System.out.println("0. Return to main menu");
                            System.out.print("> ");
                            choiseBook = sc.nextInt();

                            switch (choiseBook){
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
                        break;
                    case 3:
                        int choiseLoan = -1;
                        while (choiseLoan != 0){
                            System.out.println("\n--- Manage Loan ---");
                            System.out.println("1. Borrow a Book");
                            System.out.println("2. Return a Book");
                            System.out.println("3. List Loans");
                            System.out.println("0. Return to main menu");
                            System.out.print("> ");
                            choiseLoan = sc.nextInt();

                            switch (choiseLoan){
                                case 1:
                                    System.out.print("User Id: ");
                                    Integer userId = sc.nextInt();
                                    System.out.print("Book Id: ");
                                    Integer bookId = sc.nextInt();
                                    Loan loan = new Loan(userI.searchUser(userId), bookI.searchBook(bookId));
                                    loanI.borrowBook(loan);
                                    break;
                                case 2:
                                    System.out.print("Loan Id: ");
                                    Integer loanId = sc.nextInt();
                                    loanI.returnBook(loanI.searchLoan(loanId));
                                    break;
                                case 3:
                                    loanI.getLoans().forEach(System.out::println);
                            }
                        }

                }
            }
        } catch (RuntimeException e){
            e.printStackTrace();
        } finally {
            sc.close();
        }

        userI.saveToJson();
        bookI.saveToJson();
        loanI.saveToJson();
    }

}
