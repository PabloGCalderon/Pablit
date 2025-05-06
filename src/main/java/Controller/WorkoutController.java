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


public class WorkoutController implements ActionListener
{
    private HomeController homeController;
    private FrmWorkout frmWorkout;
    private FrmHome frmHome;
    private int countDown ;

    public WorkoutController(FrmHome frmHome, HomeController homeController) {
        
        this.frmWorkout = new FrmWorkout(homeController);
        this.homeController = homeController;
        this.frmWorkout.timeListen(this);
        this.frmWorkout.setVisible(true);
        this.frmHome = frmHome;
    }
    public void actionPerformed(ActionEvent e) {
        // Identificar el origen del evento con el comando de acción
        String command = e.getActionCommand();

        switch (command) {
             case "play":
                 if(this.validateValues(frmWorkout)){
                      frmWorkout.startTimer(); // Iniciar el temporizador
                 }
                break;

            case "pause":
                frmWorkout.pauseTimer(); // Pausar el temporizador
                break;

            case "cancel":
                frmWorkout.pauseTimer(); 
                frmWorkout.dispose();
                frmHome.setVisible(true); // Mostrar FrmHome
                this.frmHome.requestFocusInWindow();
                break;

            case "restart":
                frmWorkout.restartTimer(); // Reiniciar el temporizador
                frmWorkout.clean(true);
                break;
        }
    }//end actionPerformed
    
   public boolean validateValues(FrmWorkout frmWorkout) {
        int secondsSelected;
        try {
            secondsSelected = Integer.parseInt(frmWorkout.getSelectedSeconds()); 
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please select a valid number of seconds!", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        int reps = Integer.parseInt(frmWorkout.getSelectedReps());
        int timePerRep = 40;
        int totalReps = timePerRep * reps;

        if (secondsSelected < totalReps) {
            //if the total time required for all reps (time per reps * rep ) 
            //exceeds the time set by the user, this is the message that'll be displayed
            JOptionPane.showMessageDialog(null, "Isn't possible to select that number of reps beacuse you are not going to have enough time for your rest.. \n(;  ́  д `) * Pablit is worry about you *");
            return false;
        }

        return true; 
    }
    
    
}
