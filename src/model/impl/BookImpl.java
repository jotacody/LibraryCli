package model.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.services.BookService;
import entities.Book;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BookImpl implements BookService {
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private List<Book> books = new ArrayList<>();
    private String FILE_NAME = "files/books.json";
    private File file = new File(FILE_NAME);

    @Override
    public void insertBook(Book b) {
        books.add(b);
    }

    @Override
    public void removeBook(String title) {
        books.removeIf(t -> t.getTitle().equals(title));
    }

    @Override
    public void saveToJson() {

        if (books.isEmpty()){
            if (file.exists()){
                file.delete();
            }
        }
        try (Writer writer = new FileWriter(FILE_NAME)){
            gson.toJson(books, writer);
        }catch (IOException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Book> loadFromJson() {

        if (file.exists()){
            try (Reader reader = new FileReader(FILE_NAME)) {
                books = gson.fromJson(reader, new TypeToken<List<Book>>(){}.getType());
            }catch (IOException e){
                throw new RuntimeException();
            }
        } else {
            return new ArrayList<>();
        }
        return books;
    }
}
