package dao.daoimpl;

import dao.BookDAO;
import entities.Book;

import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements BookDAO {
    private List<Book> list = new ArrayList<>();

    public List<Book> getList() {
        return list;
    }

    public void setList(List<Book> list) {
        this.list = list;
    }

    @Override
    public void insertBook(Book b) {
        list.add(b);
    }

    @Override
    public void removeBook(String title) {
        list.removeIf(t -> t.getTitle().equals(title));
    }
}
