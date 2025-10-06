package app;

import model.entities.Book;
import model.entities.Loan;
import model.entities.User;
import model.impl.BookImpl;
import model.impl.LoanImpl;
import model.impl.UserImpl;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    private Scanner sc;
    private UserImpl userImpl;
    private BookImpl bookImpl;
    private LoanImpl loanImpl;

    public Menu(Scanner sc, UserImpl userImpl, BookImpl bookImpl, LoanImpl loanImpl) {
        this.sc = sc;
        this.userImpl = userImpl;
        this.bookImpl = bookImpl;
        this.loanImpl = loanImpl;
    }

    public void menuUser(){
        int choiceUser = -1;
        while (choiceUser != 0){
            System.out.println("\n--- Manage Users ---");
            System.out.println("1. Insert User");
            System.out.println("2. Update User");
            System.out.println("3. Delete User");
            System.out.println("4. List Users");
            System.out.println("0. Return");
            System.out.print("> ");
            choiceUser = sc.nextInt();
            if (choiceUser != 1 && choiceUser != 2 && choiceUser != 3 && choiceUser != 4 && choiceUser != 0) {
                System.out.println("\nThis option does not exist");
            }

            switch (choiceUser){
                case 1:
                    System.out.print("Name: ");
                    sc.nextLine();
                    String name = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    User user = new User(userImpl.newId(), name, email);
                    userImpl.insertUser(user);
                    System.out.println("User id: " + user.getId());
                    break;
                case 2:
                    if (userImpl.getUsers().isEmpty()){
                        System.out.println("\nNo registered user!");
                        break;
                    }
                    System.out.print("Id: ");
                    Integer idToUpdate = sc.nextInt();
                    if (userImpl.searchUser(idToUpdate) == null){
                        System.out.println("\nUser Not Found!");
                        break;
                    }
                    System.out.print("Name: ");
                    sc.nextLine();
                    String nameToUpdate = sc.nextLine();
                    System.out.print("Email: ");
                    String emailToUpdate = sc.nextLine();
                    userImpl.updateUser(idToUpdate, nameToUpdate, emailToUpdate);
                    break;
                case 3:
                    if (userImpl.getUsers().isEmpty()){
                        System.out.println("\nNo registered user!");
                        break;
                    }
                    System.out.print("Id: ");
                    Integer idDelete = sc.nextInt();
                    if (userImpl.searchUser(idDelete) == null){
                        System.out.println("\nUser Not Found!");
                        break;
                    }
                    userImpl.deleteUser(idDelete);
                    break;
                case 4:
                    if (userImpl.getUsers().isEmpty()){
                        System.out.println("\nNo registered user!");
                        break;
                    }
                    System.out.println(userImpl.getUsers());
                    break;
                case 0:
                    break;
            }

        }
    }

    public void menuBook(){
        int choiceBook = -1;
        while (choiceBook != 0){
            System.out.println("\n--- Manage Book ---");
            System.out.println("1. Insert Book");
            System.out.println("2. Update Book");
            System.out.println("3. Delete Book");
            System.out.println("4. List Books");
            System.out.println("0. Return");
            System.out.print("> ");
            choiceBook = sc.nextInt();
            if (choiceBook != 1 && choiceBook != 2 && choiceBook != 3 && choiceBook != 4 && choiceBook != 0) {
                System.out.println("\nThis option does not exist");
            }

            switch (choiceBook){
                case 1:
                    System.out.print("Title: ");
                    sc.nextLine();
                    String title = sc.nextLine();
                    System.out.print("Author: ");
                    String author = sc.nextLine();
                    System.out.print("Year: ");
                    String year = sc.nextLine();
                    Book book = new Book(bookImpl.newId(), title, author, year);
                    bookImpl.insertBook(book);
                    System.out.println("Book id: " + book.getId());
                    break;
                case 2:
                    if (bookImpl.getBooks().isEmpty()){
                        System.out.println("\nNo registered book");
                        break;
                    }
                    System.out.print("Id: ");
                    Integer idUpdate = sc.nextInt();
                    if (bookImpl.searchBook(idUpdate) == null){
                        System.out.println("\nBook Not Found");
                        break;
                    }
                    System.out.print("Title: ");
                    sc.nextLine();
                    String titleUpdate = sc.nextLine();
                    System.out.print("Author: ");
                    String authorUpdate = sc.nextLine();
                    System.out.print("Year: ");
                    String yearUpdate = sc.nextLine();
                    bookImpl.updateBook(idUpdate, titleUpdate, authorUpdate, yearUpdate);
                    break;
                case 3:
                    if (bookImpl.getBooks().isEmpty()){
                        System.out.println("\nNo registered book");
                        break;
                    }
                    System.out.print("Id: ");
                    Integer idDelete = sc.nextInt();
                    if (bookImpl.searchBook(idDelete) == null){
                        System.out.println("\nBook Not Found");
                        break;
                    }
                    bookImpl.removeBook(idDelete);
                    break;
                case 4:
                    Map<Integer, Book> list = bookImpl.getBooks();
                    if (list.isEmpty()){
                        System.out.println("\nNo registered book");
                    }else {
                        System.out.println(list);
                    }
                    break;
                case 0:
                    break;
            }
        }
    }

    public void menuLoan(){
        int choiceLoan = -1;
        while (choiceLoan != 0){
            System.out.println("\n--- Manage Loan ---");
            System.out.println("1. Borrow a Book");
            System.out.println("2. Return a Book");
            System.out.println("3. List Loans");
            System.out.println("0. Return");
            System.out.print("> ");
            choiceLoan = sc.nextInt();
            if (choiceLoan != 1 && choiceLoan != 2 && choiceLoan != 3 && choiceLoan != 0) {
                System.out.println("\nThis option does not exist");
            }

            switch (choiceLoan){
                case 1:
                    System.out.print("User Id: ");
                    Integer userId = sc.nextInt();
                    User user = userImpl.searchUser(userId);
                    if (user == null){
                        System.out.println("\nUser Not Found!");
                        break;
                    }
                    System.out.print("Book Id: ");
                    Integer bookId = sc.nextInt();
                    Book book = bookImpl.searchBook(bookId);
                    if (book == null){
                        System.out.println("\nBook Not Found!");
                        break;
                    } else if(book.getLoan() == true){
                        System.out.println("\nBook already borrowed");
                        break;
                    }
                    Loan loan = new Loan(user, book);
                    loanImpl.borrowBook(loan);
                    break;
                case 2:
                    if (loanImpl.getLoans().isEmpty()){
                        System.out.println("No registered Loan");
                        break;
                    }
                    System.out.print("Loan Id: ");
                    Integer loanId = sc.nextInt();
                    Loan l = loanImpl.searchLoan(loanId);
                    if (l == null){
                        System.out.println("\nLoan Not Found!");
                        break;
                    }
                    loanImpl.returnBook(l);
                    break;
                case 3:
                    if (loanImpl.getLoans().isEmpty()){
                        System.out.println("\nNo registered Loan");
                        break;
                    }
                    System.out.println(loanImpl.getLoans());
                    break;
                case 0:
                    break;
            }
        }
    }
}
