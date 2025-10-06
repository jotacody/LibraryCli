package model.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.services.LoanService;
import model.entities.Book;
import model.entities.Loan;
import model.entities.User;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class LoanImpl implements LoanService {
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private String FILE_NAME = "files/loans.json";
    private File file = new File(FILE_NAME);
    private Map<Integer, Loan> loans = new HashMap<>();

    public Map<Integer, Loan> getLoans() {
        return loans;
    }

    public Integer newId() {
        int id = 1;
        Set<Integer> ids = loans.keySet();

        while (ids.contains(id)){
            id++;
        }
        return id;
    }

    private void insertLoan(Loan l) {
        l.setId(newId());
        loans.put(l.getId(), l);
    }

    private void deleteLoan(Loan l) {
        loans.remove(l.getId());
    }

    @Override
    public void borrowBook(Loan l) {
        insertLoan(l);
        l.getBook().setLoan(true);
        l.getUser().getLoanBooks().add(l.getBook());
    }

    @Override
    public void returnBook(Loan l) {
        l.getBook().setLoan(false);
        l.getUser().getLoanBooks().removeIf(t -> t.getTitle().equals(l.getBook().getTitle()));
        deleteLoan(l);
    }

    @Override
    public Loan searchLoan(Integer loanId) {
        return loans.get(loanId);
    }

    @Override
    public void saveToJson() {
        try (Writer writer = new FileWriter(FILE_NAME)){
            gson.toJson(loans, writer);
        }catch (IOException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Map<Integer, Loan> loadFromJson() {
        if (file.exists()){
            try (Reader reader = new FileReader(FILE_NAME)){
                loans = gson.fromJson(reader, new TypeToken<Map<Integer, Loan>>(){}.getType());
            }catch (FileNotFoundException e){
                loans = new HashMap<>();
            }
            catch (IOException e){
                throw new RuntimeException(e.getMessage());
            }
        }else {
            return new HashMap<>();
        }

        return loans;
    }
}
