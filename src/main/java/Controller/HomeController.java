/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

//imports
//View
import View.FrmFriendList;
import View.FrmHome;
import View.FrmUser;
import View.FrmWorkout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

//Codigo
public class HomeController implements ActionListener{
    private FrmHome frmHome;
    private FrmWorkout frmWorkout;
    private FrmFriendList frmFriends;
    private FrmUser frmUser;
    

    
    //metdo contructos
    public HomeController() {
        this.frmHome = new FrmHome();
        frmHome.listenButtons(this);
        frmHome.setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.frmHome.requestFocusInWindow();
        switch (e.getActionCommand()){
            case "perfil":
                new UserController();
                break;
                
            case "workOut":
                 new WorkoutController();
                break;
                
            case "friends":
                new FriendsController();
                break;
                
            case "logOut":
                 int optionOut = JOptionPane.showConfirmDialog(null, "Esta seguro que deseas salir?", "Log out", JOptionPane.YES_NO_OPTION);
                if (optionOut == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, "Espero verte pronto... bye!"); 
                    System.exit(0); // Cerrar la aplicación
                }
                break;
        }
        
    }
    
    
    
}
