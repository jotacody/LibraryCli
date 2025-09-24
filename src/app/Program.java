package app;
import model.impl.BookImpl;
import model.impl.LoanImpl;
import model.impl.UserImpl;

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
        Menu menu = new Menu(sc, userImpl, bookImpl, loanImpl);

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
                        menu.menuUser();
                        break;
                    case 2:
                        menu.menuBook();
                        break;
                    case 3:
                        menu.menuLoan();
                        break;
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
