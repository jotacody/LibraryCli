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
import java.util.Set;
import java.util.stream.Collectors;

public class LoanImpl implements LoanService {
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private String FILE_NAME = "files/loans.json";
    private File file = new File(FILE_NAME);
    private List<Loan> loans = new ArrayList<>();

    public List<Loan> getLoans() {
        return loans;
    }

    public Integer newId() {
        int id = 1;
        Set<Integer> ids = loans.stream()
                .map(Loan::getId)
                .collect(Collectors.toSet());

        while (ids.contains(id)){
            id++;
        }
        return id;
    }

    private void insertLoan(Loan l) {
        l.setId(newId());
        loans.add(l);
    }

    private void deleteLoan(Loan l) {
        loans.remove(l);
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
        for (Loan l : loans){
            if (l.getId().equals(loanId)){
                return l;
            }
        }
        return null;
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
