package model.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.entities.User;
import model.services.BookService;
import model.entities.Book;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BookImpl implements BookService {
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private List<Book> books = new ArrayList<>();
    private String FILE_NAME = "files/books.json";
    private File file = new File(FILE_NAME);

    public List<Book> getBooks() {
        return books;
    }

    @Override
    public Integer newId() {
        int id = 1;
        Set<Integer> ids = books.stream()
                .map(Book::getId)
                .collect(Collectors.toSet());

        while (ids.contains(id)){
            id++;
        }
        return id;
    }

    @Override
    public void insertBook(Book b) {
        books.add(b);
    }

    @Override
    public void removeBook(Integer id) {
        books.removeIf(t -> t.getId().equals(id));
    }

    @Override
    public void updateBook(Integer id, String title, String author, String year) {
        books.stream().filter(b -> b.getId().equals(id))
                .forEach(b -> {b.setTitle(title); b.setAuthor(author); b.setYear(year);});
    }

    @Override
    public Book searchBook(Integer bookId) {
        for (Book b : books){
            if (b.getId().equals(bookId)){
                return b;
            }
        }
        return null;
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
