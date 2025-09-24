package app;

import model.entities.Book;
import model.entities.Loan;
import model.entities.User;
import model.impl.BookImpl;
import model.impl.LoanImpl;
import model.impl.UserImpl;

import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int option = -1;
        UserImpl userImpl = new UserImpl();
        userImpl.loadFromJson();
        BookImpl bookImpl = new BookImpl();
        bookImpl.loadFromJson();
        LoanImpl loanImpl = new LoanImpl();
        loanImpl.loadFromJson();

        try {

            while (option != 0){
                System.out.print(
                        "\n--- Book Store Menu ---\n" +
                        "1. Manage Users\n" +
                        "2. Manage Books\n" +
                        "3. Manage Loan\n" +
                        "0. exit\n" +
                                "> "
                );
                option = sc.nextInt();
                if (option != 1 && option != 2 && option != 3 && option != 0) {
                    System.out.println("\nThis option does not exist");
                }

                switch (option){
                    case 1:
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
                                    userImpl.getUsers().forEach(System.out::println);
                                    break;
                                case 0:
                                    break;
                            }

                        }
                        break;
                    case 2:
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
                                    List<Book> list = bookImpl.getBooks();
                                    if (list.isEmpty()){
                                        System.out.println("\nNo registered book");
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
                                    loanImpl.getLoans().forEach(System.out::println);
                                    break;
                                case 0:
                                    break;
                            }
                        }

                }
            }
        } catch (RuntimeException e){
            e.printStackTrace();
        } finally {
            sc.close();
            userImpl.saveToJson();
            bookImpl.saveToJson();
            loanImpl.saveToJson();
        }

    }

}
