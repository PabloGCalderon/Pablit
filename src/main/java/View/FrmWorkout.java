/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Controller.HomeController;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;


public class FrmWorkout extends javax.swing.JFrame {
    public Timer timer;
    private HomeController homeController;
    private int countDown, totalReps;
    private boolean phasePart=false;
    private int repActual=1;
    
    /**
     * Creates new form FrmWorkout
     */
    public FrmWorkout(HomeController homeController) {
        this.homeController = homeController;
        initComponents();
         setLocationRelativeTo(null);
    }
    
    public void timeListen(ActionListener controller){
        this.btnPause.addActionListener(controller);
        this.btnPlay.addActionListener(controller);
        this.btnCancel.addActionListener(controller); 
        this.btnRestart.addActionListener(controller);
    }
   
   //---------Timer, Reps & phase of the 
   public void startTimer() {
       int secondsSelected ;
       if (timer != null) {
           timer.start(); // this part allows to pause the timer and continue the timer in that same part
        } else {
           // If the timer is not created at this point, in that case is iniciated
           secondsSelected = Integer.parseInt(cboxSeconds.getSelectedItem().toString());
          
           countDown = secondsSelected; //save the number of seconds selected
            lblTiempo.setText(String.valueOf(countDown)); // show the Label with the time
         
            //-----------Training part
            int reps = Integer.parseInt(cboxReps.getSelectedItem().toString());
            String training = cboxTraining.getSelectedItem().toString();
            String rest = "Rest";
            int timePerRep = 40; // 30s exercise + 10s break
            int exerciseTime=30;
            totalReps = timePerRep*reps;   //To compare the part below with the total time of the reps with the time selected by the user 
            //end training part 
         
           timer = new Timer(1000, e -> {
                countDown--; //to reduce the time
                lblTiempo.setText(String.valueOf(countDown)); //update of the label with the "time"
                lblTiempo.setForeground(countDown <= 10 ? Color.RED : Color.BLACK); //Change the color of the label when the timer reach 10s
                   
                //This part sets the labels with a default value so they dont display "0" or a "------------"
                lblRep.setText(String.valueOf(repActual));
                lblPhase.setText(training); // exercise phase
    
                
                // ---------- 30s Exercise & 10s Rest Phase
                if ((secondsSelected - countDown) % timePerRep < exerciseTime && countDown > 0) {
                    lblPhase.setText(training); // Exercise phase
                } else if ((secondsSelected - countDown) % timePerRep >= exerciseTime && countDown > 0) {
                    lblPhase.setText(rest); // Rest phase
                }

                // ----------40s per Rep
                if ((secondsSelected - countDown) % timePerRep == 0 && countDown > 0) {
                    lblPhase.setText(training); // Vuelve a la fase de ejercicio
                    repActual++; // Avanza la repetición
                    lblRep.setText(String.valueOf(repActual));
                }

                    
                if (countDown == 0) {
                   timer.stop(); // stop the timer

                   homeController.levelUpUser(); // <--- SUBIR NIVEL y guardar

                   SwingUtilities.invokeLater(() -> {
                       // Mostrar ventana de victoria
                       FrmWin frmWin = new FrmWin();
                       frmWin.setVisible(true);
                   });

                   //dispose(); // cerrar esta ventana
               }
            });
            timer.start(); // start the timer     
        }//end timer
   }//end startTimer
    
    public void pauseTimer() {
        if (timer != null) {
            timer.stop(); // Pause the timer
        }
    }

    public void restartTimer() {
        if (timer != null) {
            timer.stop(); // Stop the timer
        }

        // restart the values
        lblTiempo.setForeground(Color.BLACK);
        countDown = Integer.parseInt(cboxSeconds.getSelectedItem().toString());
        lblTiempo.setText(String.valueOf(countDown));

    }
    //end timer    
    //---------------------------
    
    //get values of each combo box
    public String getSelectedTraining() {
        return cboxTraining.getSelectedItem().toString();
    }

    public String getSelectedReps() {
        return cboxReps.getSelectedItem().toString();
    }

    public String getSelectedSeconds() {
        return cboxSeconds.getSelectedItem().toString();
    }
    //---------------------------
    
    //--------- clean
    public void clean(boolean preserveMessage) { 
        // Reset visual elements
        this.lblTiempo.setText("0:00:00");
        this.lblRep.setText("0");
        this.lblPhase.setText("------------");
        
        //reset combobox's info
        this.cboxTraining.setSelectedIndex(0); 
        this.cboxReps.setSelectedIndex(0); 
        this.cboxSeconds.setSelectedIndex(0);
    }    
        


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cboxSets2 = new javax.swing.JComboBox<>();
        btnCancel = new javax.swing.JButton();
        Exercise = new javax.swing.JLabel();
        btnPause = new javax.swing.JButton();
        btnPlay = new javax.swing.JButton();
        btnRestart = new javax.swing.JButton();
        cboxTraining = new javax.swing.JComboBox<>();
        lblTiempo = new javax.swing.JLabel();
        cboxSeconds = new javax.swing.JComboBox<>();
        cboxReps = new javax.swing.JComboBox<>();
        lbl = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        Reps = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lblRep = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        Reps1 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        lblPhase = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        bg = new javax.swing.JLabel();

        cboxSets2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---", "1", "2", "3", "4", "5", "10", "15", "20", "25", "30", " " }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel.png"))); // NOI18N
        btnCancel.setActionCommand("cancel");
        btnCancel.setBorderPainted(false);
        btnCancel.setContentAreaFilled(false);
        getContentPane().add(btnCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 0, 80, 80));

        Exercise.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Exercise.setForeground(new java.awt.Color(255, 255, 255));
        Exercise.setText("Exercise");
        getContentPane().add(Exercise, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, -1, -1));

        btnPause.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pause.png"))); // NOI18N
        btnPause.setActionCommand("pause");
        btnPause.setBorderPainted(false);
        btnPause.setContentAreaFilled(false);
        getContentPane().add(btnPause, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 560, 100, 90));

        btnPlay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/play.png"))); // NOI18N
        btnPlay.setActionCommand("play");
        btnPlay.setBorderPainted(false);
        btnPlay.setContentAreaFilled(false);
        getContentPane().add(btnPlay, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 560, 100, 90));

        btnRestart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reiniciar.png"))); // NOI18N
        btnRestart.setActionCommand("restart");
        btnRestart.setBorderPainted(false);
        btnRestart.setContentAreaFilled(false);
        getContentPane().add(btnRestart, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 570, -1, 70));

        cboxTraining.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---", "Bulgarian squat", "Hip thrust", "Calf raises", "Leg curls", "Leg extensions", "Bench press", "Pull-ups", "Dips", "Shoulder press", "Bicep curls", "Triceps extensions", "Plank", "Russian twists", "Leg raises", "Hanging knee raises" }));
        getContentPane().add(cboxTraining, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 180, 170, 30));

        lblTiempo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTiempo.setForeground(new java.awt.Color(51, 0, 51));
        lblTiempo.setText("0:00:00");
        getContentPane().add(lblTiempo, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 290, 70, 50));

        cboxSeconds.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---", "40", "80", "120", "160", "200", "240", "280", "320", "360", "400" }));
        getContentPane().add(cboxSeconds, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 290, 90, 40));

        cboxReps.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        getContentPane().add(cboxReps, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 120, 30));

        lbl.setText("rep");
        getContentPane().add(lbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 430, 20, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 0, 51));
        jLabel5.setText("Timer");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, 180, -1));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Instructions:  ");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 100, 220, 20));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/aqua.png"))); // NOI18N
        jLabel4.setText("jLabel4");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 540, 610, 10));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bg_tiempo.png"))); // NOI18N
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 290, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 0, 51));
        jLabel7.setText("Remaining Time");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 260, -1, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Reps");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, -1));

        Reps.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Reps.setText("Actual Rep:");
        getContentPane().add(Reps, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 430, -1, -1));

        jLabel1.setBackground(new java.awt.Color(51, 0, 51));
        jLabel1.setForeground(new java.awt.Color(51, 0, 51));
        jLabel1.setText("second");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 310, -1, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Cycle phase");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 460, 90, -1));

        lblRep.setText("0");
        getContentPane().add(lblRep, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 430, 20, -1));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(153, 255, 255));
        jLabel23.setText("___________________________________________________________________________________________________________________________");
        getContentPane().add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, -1, -1));

        Reps1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Reps1.setText("Phase:");
        getContentPane().add(Reps1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 470, -1, -1));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("-Pick the number of repetitions.");
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 120, 270, 20));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("-Choose your exercise type.  ");
        getContentPane().add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 140, 270, 20));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("*Number of times you perform a group of sets");
        getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 270, 20));

        lblPhase.setText("------------");
        getContentPane().add(lblPhase, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 470, 140, -1));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("-Select your total workout time.");
        getContentPane().add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 160, 270, 20));

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("*Be ensure you have enough time for all reps.");
        getContentPane().add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 210, 270, 20));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Workout ");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, 180, -1));

        jLabel2.setBackground(new java.awt.Color(51, 0, 51));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 0, 51));
        jLabel2.setText("*Divide the seconds by 60 if you want to know how many minutes there are. Remember, 3600/60 = 60 minutes");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 340, -1, 30));

        jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("*Each repetition includes 10s of rest after the workout. ");
        getContentPane().add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 190, 270, 20));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bg.png"))); // NOI18N
        jLabel3.setText("jLabel3");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 620, 250));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("*Number of times you perform a group of sets");
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 130, 270, 20));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/aqua.png"))); // NOI18N
        jLabel11.setText("jLabel11");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 620, 140));

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bg_actualEje.png"))); // NOI18N
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 420, 250, -1));

        bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bg.png"))); // NOI18N
        bg.setText("jLabel1");
        getContentPane().add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 390, 620, 280));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Exercise;
    private javax.swing.JLabel Reps;
    private javax.swing.JLabel Reps1;
    private javax.swing.JLabel bg;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnPause;
    private javax.swing.JButton btnPlay;
    private javax.swing.JButton btnRestart;
    private javax.swing.JComboBox<String> cboxReps;
    private javax.swing.JComboBox<String> cboxSeconds;
    private javax.swing.JComboBox<String> cboxSets2;
    private javax.swing.JComboBox<String> cboxTraining;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lbl;
    private javax.swing.JLabel lblPhase;
    private javax.swing.JLabel lblRep;
    private javax.swing.JLabel lblTiempo;
    // End of variables declaration//GEN-END:variables
}
