/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.FrmHome;
import View.FrmWorkout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;


public class WorkoutController implements ActionListener{
    private FrmWorkout frmWorkout;
    private FrmHome frmHome;
    private int countDown ;

    public WorkoutController() {
        this.frmWorkout = new FrmWorkout();
        this.frmWorkout.timeListen(this);
        this.frmWorkout.setVisible(true);
        this.frmHome = new FrmHome();
    }
    public void actionPerformed(ActionEvent e) {
        // Identificar el origen del evento con el comando de acción
        String command = e.getActionCommand();

        switch (command) {
             case "play":
                frmWorkout.startTimer(); // Iniciar el temporizador
                break;

            case "pause":
                frmWorkout.pauseTimer(); // Pausar el temporizador
                break;

            case "cancel":
                frmWorkout.pauseTimer(); 
                frmWorkout.setVisible(false);
                frmHome.setVisible(true); // Mostrar FrmHome
                new HomeController();
                this.frmHome.requestFocusInWindow();
                break;

            case "restart":
                frmWorkout.restartTimer(); // Reiniciar el temporizador
                break;
        }
    }
    //fin actionPerformed
    
  
}