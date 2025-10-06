package model.services;

import model.entities.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    public Integer newId();
    public void insertUser(User u);
    public void deleteUser(Integer id);
    public void updateUser(Integer id, String name, String email);
    public User searchUser(Integer userid);
    public void saveToJson();
    public Map<Integer, User> loadFromJson();
}
