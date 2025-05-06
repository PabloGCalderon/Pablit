/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class UserRegister 
{
    private GenericDAO_JSON<User> userDAO;

    public UserRegister() 
    {   
        Type type = new TypeToken<User[]>() {}.getType();
        this.userDAO = new GenericDAO_JSON<>("users.json", type);
    }

    public String addUser(User user) {
        if (userDAO.save(user)) {
            return "The user was created successfully";
        } else {
            return "This username already exist, try another";
        }
    }
    
 
    public boolean updateUser(User updatedUser) {
        return userDAO.update(updatedUser);
    }

    public User getUser(String username) {
        return userDAO.findByUsername(username);
    }

    public boolean login(String username, String password) {
        User user = userDAO.findByUsername(username);
        return user != null && user.getPassword().equals(password);
    }
    
    public User searchByUsername(String username) {
        return this.userDAO.findByUsername(username);
}

    
}
