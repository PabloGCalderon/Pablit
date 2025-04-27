/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Model.Workout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;
import javax.swing.Timer;


public class FrmWorkout extends javax.swing.JFrame {
    public Timer timer;
    private int segundos;
    private int countDown;

    /**
     * Creates new form FrmWorkout
     */
    public FrmWorkout() {
        initComponents();
    }
    
    public void timeListen(ActionListener controller){
        this.btnPause.addActionListener(controller);
        this.btnPlay.addActionListener(controller);
        this.btnCancel.addActionListener(controller); 
        this.btnRestart.addActionListener(controller);
    }
    
    public void clean(){ 
        //limpiar el cbox
        this.cboxSeconds.setSelectedItem("---"); 
    }
     
     
   public void startTimer() {
    if (timer != null) {
        timer.start(); // Para permitir pausar  continuar desde donde se pauso
    } else {
        // Si el temporizador aún no ha sido creado, iniciarlo desde el principio
        int secondsSelected = Integer.parseInt(cboxSeconds.getSelectedItem().toString());
        countDown = secondsSelected; // para guardar el dato donde empieza el tiempo
        lblTiempo.setText(String.valueOf(countDown)); // Mostrar en el Label la cantidad de tiempo
        timer = new Timer(1000, e -> {
            countDown--; //disminuir el tiempo
            lblTiempo.setText(String.valueOf(countDown)); // Actualizar el tiempo
            lblTiempo.setForeground(countDown <= 60 ? Color.RED : Color.BLACK); // Cambiar el color si quedan 60 segundos

            if (countDown == 0) {
                //Para mostrar el frm de Felicitaciones si el timer lego a 0 
                timer.stop(); // Detener el temporizador
                SwingUtilities.invokeLater(() -> {
                    //Para invocar la ventana de felicitaciones y animar el usuario
                    FrmWin frmWin = new FrmWin();
                    frmWin.setVisible(true); 
                    
                });
                dispose(); // Cerrar FrmWorkout
            }
        });
        timer.start(); // Iniciar el temporizador
    }
   }
    
    public void pauseTimer() {
        if (timer != null) {
            timer.stop(); // Pausar el temporizador
        }
    }

    public void restartTimer() {
        if (timer != null) {
            timer.stop(); // Detener el temporizador actual
        }
        lblTiempo.setForeground(Color.BLACK); // Reiniciar el color del texto
        countDown = Integer.parseInt(cboxSeconds.getSelectedItem().toString()); // Reiniciar el tiempo
        lblTiempo.setText(String.valueOf(countDown)); // Actualizar el tiempo inicial en el label
    }
        
    
//    public void updateTime() {
//         int secondsSelected = Integer.parseInt(this.cboxSeconds.getSelectedItem().toString());
//        
//         //variable para tiempo bjar
//        countDown=secondsSelected;
//         timer = new Timer(1000, e -> {
//            countDown--;//La variable se reduce poco a poco su cantidad inicializada
//           
//            lblTiempo.setText(String.valueOf(countDown));
//       
//            if (countDown <= 60) {
//                lblTiempo.setForeground(Color.RED); // Cambiar a rojo cuando queden 10 segundos
//                for (java.awt.Window window : java.awt.Window.getWindows()) {
//                    //Esta sección del método se encarga de detectar si se abre una ventana diferente a la actual, y en ese caso, detiene el tiempo.
//                    if (window != this && window.isShowing()) {
//                        timer.stop();//Detiene el tiempo
//                    }
//                }//Fin for para parar tiempo
//
//            } else {
//                lblTiempo.setForeground(Color.BLACK); // Mantener blanco en el resto del tiempo
//                for (java.awt.Window window : java.awt.Window.getWindows()) {
//                    //Esta sección del método se encarga de detectar si se abre una ventana diferente a la actual, y en ese caso, detiene el tiempo.
//                    if (window != this && window.isShowing()) {
//                        timer.stop();//Detiene el tiempo
//                    }
//                }//Fin for para parar tiempo
//                
//            }
//
//            if (countDown == 0) {
//                //Esta sección del método se activa cuando el countDown llega a cero, lo que provoca que se abra la ventana de guiLose o derrota
//                timer.stop();
//                // Invocar la ventana de derrtota
//                SwingUtilities.invokeLater(() -> {
//                    FrmWin frmWin = new FrmWin();
//                   frmWin.setVisible(true);
//                });
//                dispose(); // Cierra la ventana actual
//            }
//            
//        });
//
//        timer.start(); // Iniciar el temporizador
//        
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnCancel = new javax.swing.JButton();
        btnPause = new javax.swing.JButton();
        btnPlay = new javax.swing.JButton();
        btnRestart = new javax.swing.JButton();
        lblTiempo = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cboxSeconds = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        bg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel.png"))); // NOI18N
        btnCancel.setActionCommand("cancel");
        btnCancel.setBorderPainted(false);
        btnCancel.setContentAreaFilled(false);
        getContentPane().add(btnCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 0, 100, 90));

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

        lblTiempo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTiempo.setForeground(new java.awt.Color(51, 0, 51));
        lblTiempo.setText("0:00:00");
        getContentPane().add(lblTiempo, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 100, 70, 60));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Temporizador");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 180, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/aqua.png"))); // NOI18N
        jLabel4.setText("jLabel4");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 530, 610, 10));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bg_tiempo.png"))); // NOI18N
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 100, -1, -1));

        cboxSeconds.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---", "120", "300", "600", "1200", "1800", "2400", "3000", "3600" }));
        getContentPane().add(cboxSeconds, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 110, 90, 40));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("segundos");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 130, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("*Divida por 60 los segundos si desea saber cuantos minutos son. Recuerde 3600/60=60minutos");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 160, -1, 30));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bg_nb.png"))); // NOI18N
        jLabel3.setText("jLabel3");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 620, 220));

        bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bg.png"))); // NOI18N
        bg.setText("jLabel1");
        getContentPane().add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 620, 670));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bg;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnPause;
    private javax.swing.JButton btnPlay;
    private javax.swing.JButton btnRestart;
    private javax.swing.JComboBox<String> cboxSeconds;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel lblTiempo;
    // End of variables declaration//GEN-END:variables

    
}
