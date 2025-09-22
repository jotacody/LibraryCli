package model.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.services.LoanService;
import model.entities.Book;
import model.entities.Loan;
import model.entities.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LoanImpl implements LoanService {
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private String FILE_NAME = "files/loans.json";
    private File file = new File(FILE_NAME);
    private List<Loan> loans = new ArrayList<>();


    @Override
    public void insertLoan(Loan l) {
        loans.add(l);
    }

    @Override
    public void deleteLoan(Loan l) {
        loans.removeIf(t -> t.equals(l.getId()));
    }

    @Override
    public void borrowBook(User u, Book b) {
        b.setLoan(true);
        u.getLoanBooks().add(b);
    }

    @Override
    public void returnBook(User u, Book b) {
        b.setLoan(false);
        u.getLoanBooks().removeIf(t -> t.getTitle().equals(b.getTitle()));
    }

    @Override
    public void saveToJson() {

        if (loans.isEmpty()){
            if (file.exists()){
                file.delete();
            }
        }

        try (Writer writer = new FileWriter(FILE_NAME)){
            gson.toJson(loans, writer);
        }catch (IOException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Loan> loadFromJson() {
        if (file.exists()){
            try (Reader reader = new FileReader(FILE_NAME)){
                loans = gson.fromJson(reader, new TypeToken<List<Loan>>(){}.getType());
            }catch (FileNotFoundException e){
                loans = new ArrayList<>();
            }
            catch (IOException e){
                throw new RuntimeException(e.getMessage());
            }
        }else {
            return new ArrayList<>();
        }

        return loans;
    }
}
