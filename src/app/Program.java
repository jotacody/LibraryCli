package app;

import model.entities.Book;
import model.entities.User;
import model.impl.BookImpl;
import model.impl.LoanImpl;
import model.impl.UserImpl;

import java.awt.*;
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

        while (option != 0){
            System.out.print(
                    "\nBook Store Menu\n" +
                    "1) Manage Users\n" +
                    "2) Manage Books\n" +
                    "3) Borrow a book\n" +
                    "0) exit\n" +
                    "Choose an option\n" +
                            "> "
            );
            option = sc.nextInt();

            switch (option){
                case 1:
                    MenuManager.menuUser(sc, userI);
                    break;
                case 2:
                    MenuManager.menuBook(sc, bookI);

            }
        }

        userI.saveToJson();
        bookI.saveToJson();
        loanI.saveToJson();
    }

}
