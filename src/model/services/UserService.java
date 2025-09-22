package model.services;

import model.entities.User;

import java.util.List;

public interface UserService {
    public Integer newId();
    public void insertUser(User u);
    public void deleteUser(Integer id);
    public void updateUser(Integer id, String name, String email);
    public void saveToJson();
    public List<User> loadFromJson();
}
