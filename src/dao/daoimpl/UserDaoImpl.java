package dao.daoimpl;

import dao.UserDAO;
import entities.Book;
import entities.User;

import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDAO {
    private List<User> list = new ArrayList<>();

    @Override
    public void insertUser(User u) {
        list.add(u);
    }

    @Override
    public void deleteUser(Integer id) {
        list.removeIf(t -> t.getId().equals(id));
    }

    @Override
    public void borrowBook(User u, Book b) {
        b.setLoan(true);
        u.getLoanBooks().add(b);
    }

    public void returnBook(User u, Book b){
        b.setLoan(false);
        u.getLoanBooks().removeIf(t -> t.getTitle().equals(b.getTitle()));
    }

    public List<User> getList() {
        return list;
    }

    public void setList(List<User> list) {
        this.list = list;
    }
}
