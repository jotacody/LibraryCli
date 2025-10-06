package model.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.services.BookService;
import model.entities.Book;

import java.io.*;
import java.util.*;

public class BookImpl implements BookService {
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private String FILE_NAME = "files/books.json";
    private File file = new File(FILE_NAME);
    private Map<Integer, Book> books = new HashMap<>();

    public Map<Integer, Book> getBooks() {
        return books;
    }

    @Override
    public Integer newId() {
        int id = 1;
        Set<Integer> ids = books.keySet();
        while (ids.contains(id)){
            id++;
        }
        return id;
    }

    @Override
    public void insertBook(Book b) {
        books.put(b.getId(), b);
    }

    @Override
    public void removeBook(Integer id) {
        books.remove(id);
    }

    @Override
    public void updateBook(Integer id, String title, String author, String year) {
        Book book = books.get(id);
        if (book != null){
            book.setYear(year);
            book.setAuthor(author);
            book.setTitle(title);
        }
    }

    @Override
    public Book searchBook(Integer bookId) {
        return books.get(bookId);
    }

    @Override
    public void saveToJson() {
        try (Writer writer = new FileWriter(FILE_NAME)){
            gson.toJson(books, writer);
        }catch (IOException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Map<Integer, Book> loadFromJson() {

        if (file.exists()){
            try (Reader reader = new FileReader(FILE_NAME)) {
                books = gson.fromJson(reader, new TypeToken<Map<Integer, Book>>(){}.getType());
            }catch (IOException e){
                throw new RuntimeException();
            }
        } else {
            return new HashMap<>();
        }
        return books;
    }
}
