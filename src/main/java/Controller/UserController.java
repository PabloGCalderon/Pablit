/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.DialogRegister;
import View.FrmLogin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class UserController implements ActionListener
{
    private DialogRegister dialogRegister;
    private FrmLogin frmLogin;
    
    public UserController ()
    {
        this.dialogRegister = new DialogRegister(this.frmLogin,true);
        this.dialogRegister.setListen(this);
        dialogRegister.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Register":
                System.out.println("Register");
                break;
              case "Back":
                System.out.println("Back");
                dialogRegister.dispose();
                break;  
        }
    }   
}
