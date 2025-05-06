/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;


public class User 
{
   private String username;
   private int age;
   private double weight;
   private String sex;
   private int level;
   private String password;

   //Constructores
     public User() {
        this.username = "";
        this.password = "";
        this.age = 0;
        this.weight = 0.0;
        this.sex = "";
        this.level= 0;
    }

    public User(String username, String password, int age, double weight, String sex, int level)
    {
        this.username = username;
        this.password = password;
        this.age = age;
        this.weight = weight;
        this.sex = sex;
        this.level = level;
        this.password = password;
    }
 
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
    
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    

    @Override
    public String toString() {
        return "User{" + "username=" + username + ", age=" + age + ", weight=" + weight + ", sex=" + sex + ", level=" + level + ", password=" + password + '}';
    }

   
}
