/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.User;
import Model.UserRegister;
import View.DialogLogin;
import View.DialogRegister;
import View.FrmLogin;
import View.FrmUser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;


public class UserController implements ActionListener
{
    private User user;
    private UserRegister userRegister;
    private DialogLogin dialogLogin;
    private FrmLogin frmLogin;
    private DialogRegister dialogRegister;
    private HomeController homeController;
    
    public UserController() 
    {
        this.user = new User();
        this.userRegister = new UserRegister();
        this.dialogRegister = new DialogRegister(frmLogin, true);
        this.frmLogin = new FrmLogin();
        this.frmLogin.setVisible(true);        
        this.dialogLogin = new DialogLogin(frmLogin, true);
        this.dialogRegister.setListen(this);
        this.dialogLogin.setListen(this);
        this.frmLogin.setListenFrm(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        
        switch(action) 
        {
            case "Login":
                dialogLogin.setVisible(true);
                break;
                
            case "Register":
                registerUser();
                break;
                
            case "Exit":
               System.exit(0);
                break;
                
            case "LoginDialog":
                loginUser();
                break;
                
            case "ExitDialog":
                 dialogLogin.dispose();
                 new UserController();
                break;
                
            case "RegisterSuccess":
                dialogRegister.dispose();
                break;
                
            case "BackDialogRegister":
                dialogRegister.dispose();
               
        }
    }
    
    private void loginUser() {
        String username = dialogLogin.getUsername();
        String password = dialogLogin.getPassword();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(dialogLogin, "Be sure to fill all the fields.");

        } else {

        User user = userRegister.searchByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            this.user = user;
            dialogLogin.dispose();
            frmLogin.dispose();
            this.homeController = new HomeController(user, userRegister);
        } else {
            JOptionPane.showMessageDialog(dialogLogin, "Username or password incorrect.");
          }
        }
    }
    
    private void registerUser() 
    {
        dialogRegister.setVisible(true);
        String username = dialogRegister.getUsername();
        String password = dialogRegister.getPassword();
        String ageStr = dialogRegister.getAge();
        String weightStr = dialogRegister.getWeight();
        String sex = dialogRegister.getGenre();

        if (username.isEmpty() || password.isEmpty() || ageStr.isEmpty() || weightStr.isEmpty() || sex.isEmpty()) {
            JOptionPane.showMessageDialog(dialogRegister, "Be sure to fill all the fields");
            return;
        }

        try {
            int age = Integer.parseInt(ageStr);
            double weight = Double.parseDouble(weightStr);

            User user = new User(username, password, age, weight, sex, 1); 
            String message = userRegister.addUser(user);
            JOptionPane.showMessageDialog(dialogRegister, message);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(dialogRegister, "Edad o peso inválidos");
        }
        dialogRegister.dispose();
    }
    
    
    
 }

