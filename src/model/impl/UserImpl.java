package model.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.services.UserService;
import model.entities.User;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class UserImpl implements UserService {
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private String FILE_NAME = "files/users.json";
    private File file = new File(FILE_NAME);
    private Map<Integer, User> users = new HashMap<>();

    public Map<Integer, User> getUsers() {
        return users;
    }

    @Override
    public Integer newId() {
        int id = 1;
        Set<Integer> ids = users.keySet();

        while (ids.contains(id)){
            id++;
        }
        return id;
    }

    @Override
    public void insertUser(User u) {
        users.put(u.getId(), u);
    }

    @Override
    public void deleteUser(Integer id) {
        users.remove(id);
    }

    @Override
    public void updateUser(Integer id, String name, String email) {
        User user = users.get(id);
        user.setName(name);
        user.setEmail(email);
    }

    @Override
    public User searchUser(Integer userid) {
        return users.get(userid);
    }

    @Override
    public void saveToJson() {
        try (Writer writer = new FileWriter(FILE_NAME)){
            gson.toJson(users, writer);
        }catch (IOException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Map<Integer, User> loadFromJson() {
        if (file.exists()){
            try (Reader reader = new FileReader(FILE_NAME)){
                users = gson.fromJson(reader, new TypeToken<Map<Integer, User>>(){}.getType());
            }catch (FileNotFoundException e){
                users = new HashMap<>();
            }
            catch (IOException e){
                throw new RuntimeException(e.getMessage());
            }
        }else {
            return new HashMap<>();
        }
        return users;
    }

}
