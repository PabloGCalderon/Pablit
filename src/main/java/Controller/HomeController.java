/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

//imports
//View
import Model.User;
import Model.UserRegister;
import View.FrmFriendList;
import View.FrmHome;
import View.FrmUser;
import View.FrmWorkout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

//Codigo
public class HomeController implements ActionListener
{
    private FrmHome frmHome;
    private FrmWorkout frmWorkout;
    private FrmFriendList frmFriends;
    private FrmUser frmUser;
    private User user;
    private UserRegister userRegister;
    private String currentUser;
    private boolean firstSession;
    
    //metdo contructos
    public HomeController(User user, UserRegister userRegister) 
    {
        this.frmUser = new FrmUser();
        this.frmHome = new FrmHome();
        this.user = user;
        this.userRegister = userRegister;
        this.currentUser = user.getUsername();
        this.firstSession=false;
        this.frmUser.setUserData(user);
        frmHome.listenButtons(this);
        frmHome.setVisible(true);
        frmUser.setListenFrmUser(this);
        
        frmHome.updateLevelDisplay(user.getLevel());
   
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.frmHome.requestFocusInWindow();
        switch (e.getActionCommand()){
            case "perfil":
                frmUser.setVisible(true);
                break;
                
            case "workOut":
                 new WorkoutController(frmHome,this);
                 this.frmHome.setVisible(false);
                 firstSession = true;
                 updatePablitImage();
                break;
                
            case "friends":
                if (frmFriends == null) {
                    frmFriends = new FrmFriendList(currentUser); // Usamos currentUser para el controlador
                }
                // Mostrar la ventana de amigos
                frmFriends.setVisible(true);
              // Ocultar la ventana principal
                System.out.println("Controller.HomeController.actionPerformed()");
                break;
                
            case "logOut":
                int optionOut = JOptionPane.showConfirmDialog(null, "Esta seguro que deseas salir?", "Log out", JOptionPane.YES_NO_OPTION);
                if (optionOut == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, "Espero verte pronto... bye!"); 
                    System.exit(0); // Cerrar la aplicación
                }
                break;
                
                 case "EditUser":
                editUser();
                break;
                
                 case "BackUser":
                frmUser.dispose();
        }
        
    }  
    
    private void editUser()
    {
   try {
       
        int newAge = Integer.parseInt(frmUser.getAge()); //Obtiene el nuevo valor con el que va a sustituir la edad desde FrmUser.getAge
        double newWeight = Double.parseDouble(frmUser.getWeight()); //Obtiene el nuevo valor con el que va a sustituir el peso desde FrmUser.getWeight
        String newGenre = frmUser.getGenre(); //Obtiene el nuevo valor con el que va a sustituir el genero desde FrmUser.getGenre

        user.setAge(newAge); //setea los nuevos valores 
        user.setWeight(newWeight);
        user.setSex(newGenre);

        userRegister.updateUser(user); //Guarda los cambios en el json
        
        JOptionPane.showMessageDialog(frmUser, "Profile updated."); //Avisa que se cambiaron
    }
    catch (NumberFormatException e) 
    {
        JOptionPane.showMessageDialog(frmUser, "Age or Weight invalid.");
    }
        
  }
    
    private void updatePablitImage()
    {
        String imagePath;
        if (firstSession) {
            imagePath = "images/pabloFeli.png";
        } else {
            imagePath = "images/pabloEnojao.png";
        }
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource(imagePath));
        frmHome.setWorkoutStatusIcon(icon);
    }
    
    public void levelUpUser() {
    int currentLevel = user.getLevel();
    user.setLevel(currentLevel + 1); // Sube el nivel

    userRegister.updateUser(user); // Guarda en JSON

    frmUser.updateLevelDisplay(user.getLevel()); // Refresca JLabel en frmUser
    
    frmHome.updateLevelDisplay(user.getLevel()); // Refresca el JLabel en FrmHome
}
    
}
