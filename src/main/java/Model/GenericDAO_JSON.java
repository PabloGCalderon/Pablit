/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;


public class GenericDAO_JSON<T>
{
    private String fileName;
    private Gson gson;
    private Type type;

    public GenericDAO_JSON(String fileName, Type type) {
        this.fileName = fileName;
        this.type = type;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

 
    public ArrayList<T> getAll() {
        try (FileReader reader = new FileReader(fileName)) {
            T[] elements = gson.fromJson(reader, type);
            return elements == null ? new ArrayList<>() : new ArrayList<>(Arrays.asList(elements));
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public void saveAll(ArrayList<T> elements) {
        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(elements.toArray(), writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean save(T newElement) {
        ArrayList<T> elements = getAll();

        if (newElement instanceof User userNew) {
            for (T e : elements) {
                if (e instanceof User user) {
                    if (user.getUsername().equals(userNew.getUsername())) {
                        return false; 
                    }
                }
            }
        }

        elements.add(newElement);
        saveAll(elements);
        return true;
    }

    public boolean update(T updatedElement) {
        ArrayList<T> elements = getAll();
        if (updatedElement instanceof User updatedUser) {
            for (int i = 0; i < elements.size(); i++) {
                if (elements.get(i) instanceof User user &&
                    user.getUsername().equals(updatedUser.getUsername())) {
                    elements.set(i, updatedElement);
                    saveAll(elements);
                    return true;
                }
            }
        }
        return false;
    }

    public T findByUsername(String username) {
        ArrayList<T> elements = getAll();
        for (T e : elements) {
            if (e instanceof User user) {
                if (user.getUsername().equals(username)) {
                    return e;
                }
            }
        }
        return null;
    }
    
    public void delete(T itemToDelete) {
        ArrayList<T> items = getAll();
        items.removeIf(item -> item.equals(itemToDelete)); // Usa equals()
        saveAll(items);
    }
}

