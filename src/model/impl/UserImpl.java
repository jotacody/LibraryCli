package model.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.services.UserService;
import entities.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserImpl implements UserService {
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private String FILE_NAME = "files/users.json";
    private File file = new File(FILE_NAME);
    private List<User> users = new ArrayList<>();

    public List<User> getUsers() {
        return users;
    }

    @Override
    public void insertUser(User u) {
        users.add(u);
    }

    @Override
    public void deleteUser(Integer id) {
        users.removeIf(t -> t.getId().equals(id));
    }

    @Override
    public void saveToJson() {

        if (users.isEmpty()){
            if (file.exists()){
                file.delete();
            }
        }

        try (Writer writer = new FileWriter(FILE_NAME)){
            gson.toJson(users, writer);
        }catch (IOException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<User> loadFromJson() {
        if (file.exists()){
            try (Reader reader = new FileReader(FILE_NAME)){
                users = gson.fromJson(reader, new TypeToken<List<User>>(){}.getType());
            }catch (FileNotFoundException e){
                users = new ArrayList<>();
            }
            catch (IOException e){
                throw new RuntimeException(e.getMessage());
            }
        }else {
            return new ArrayList<>();
        }
        return users;
    }

}
