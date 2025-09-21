package services;

import entities.User;

import java.util.List;

public interface UserService {
    public void insertUser(User u);
    public void deleteUser(Integer id);
    public void saveToJson();
    public List<User> loadFromJson();
}
