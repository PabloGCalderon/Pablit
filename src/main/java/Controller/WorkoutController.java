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
    private int reps;

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
                frmHome.setVisible(true); // show FrmHome
                this.frmHome.requestFocusInWindow();
                break;

            case "restart":
                frmWorkout.restartTimer(); // restart the timer
                frmWorkout.clean(true);
                cleanRep(frmWorkout);//
                break;
        }
    }//end actionPerformed
    
   public boolean validateValues(FrmWorkout frmWorkout) {
        int secondsSelected;
        String training;
        try {
            secondsSelected = Integer.parseInt(frmWorkout.getSelectedSeconds()); 
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please select a valid number of seconds!", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        try {
            reps  = Integer.parseInt(frmWorkout.getSelectedReps()); 
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please select a number of reps", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return false;
        } 
        
         if(frmWorkout.getSelectedTraining().equals("---")){
            JOptionPane.showMessageDialog(null, "Please select a training to continue", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return false;
        
         }

        reps = Integer.parseInt(frmWorkout.getSelectedReps());
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
    
    public void cleanRep(FrmWorkout frmWorkout) {
        reps=0;
    }
}
