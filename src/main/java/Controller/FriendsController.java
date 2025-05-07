/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Friends;
import Model.GenericDAO_JSON;
import Model.User;
import Model.UserRegister;
import View.FrmFriendList;
import View.FrmFriendsTop;
import com.google.gson.reflect.TypeToken;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Type;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FriendsController implements ActionListener {

    private GenericDAO_JSON<Friends> friendsDAO;
    private String currentUser;
    private FrmFriendList frmFriendList;
    private FrmFriendsTop frmFriendsTop;

    public FriendsController(String currentUser) {
        this.currentUser = currentUser;
        Type type = new TypeToken<Friends[]>() {
        }.getType();
        this.friendsDAO = new GenericDAO_JSON<>("friends.json", type);
    }

    public void setFrmFriendList(FrmFriendList frm) {
        this.frmFriendList = frm;// Asigna el formulario a la variable de clase
        this.frmFriendList.listenButtons(this);
    }

    public void setFrmFriendsTop(FrmFriendsTop frm) {
        this.frmFriendsTop = frm;// Asigna el formulario a la variable de clase
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();

        switch (action) {
            case "Add":
                String friendUsername = frmFriendList.getFriendUsername(); // Obtiene el nombre ingresado

                if (friendUsername.isEmpty()) {       // Verifica si está vacío
                    JOptionPane.showMessageDialog(null, "Por favor ingresa un nombre de usuario.");
                    return;
                }

                if (friendUsername.equals(currentUser)) {
                    JOptionPane.showMessageDialog(null, "No puedes agregarte a ti mismo.");
                    return;
                }

                if (isAlreadyFriend(friendUsername)) { // Verifica si ya es amigo
                    JOptionPane.showMessageDialog(null, "Ya agregaste a este amigo.");
                    return;
                }

                UserRegister userRegister = new UserRegister(); // Instancia manejador de usuarios
                User foundUser = userRegister.searchByUsername(friendUsername); // Busca al usuario

                if (foundUser == null) {
                    JOptionPane.showMessageDialog(null, "El usuario no existe.");
                    return;
                }

                friendsDAO.save(new Friends(currentUser, friendUsername)); // Guarda la amistad en la base de datos
                JOptionPane.showMessageDialog(null, "Amigo agregado exitosamente.");
                break;

            case "Show":

                frmFriendsTop = new FrmFriendsTop(this);
                frmFriendsTop.setVisible(true);
                frmFriendList.dispose();
                break;

            case "Back":
                frmFriendList.dispose();
                break;

            case "Back1":
                frmFriendsTop.dispose();
                if (frmFriendList != null) {
                    frmFriendList.setVisible(true);
                }
                break;

            case "Delete":
                System.out.println("Delete");
                int selectedRow = frmFriendsTop.getSelectedRow(); //  obtenemos la fila seleccionada
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Por favor selecciona un amigo para eliminar.");
                    return;
                }

                String friendToDelete = frmFriendsTop.getFriendUsernameAtRow(selectedRow); // obtenemos el username del amigo

                ArrayList<Friends> allFriends = friendsDAO.getAll();
                Friends toRemove = null;
                for (Friends f : allFriends) {
                    if (f.getUsername().equals(currentUser) && f.getFriendUsername().equals(friendToDelete)) {
                        toRemove = f;
                        break;
                    }
                }

                if (toRemove != null) {
                    friendsDAO.delete(toRemove);
                    JOptionPane.showMessageDialog(null, "Amigo eliminado correctamente.");
                    frmFriendsTop.loadFriends(); // refrescar la tabla
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo encontrar al amigo para eliminar.");
                }
                System.out.println("Delete");
                break;
                
                case "Top":
                frmFriendsTop.loadFriendsByLevel();
                break;
                case "Recent":
                frmFriendsTop.loadFriends();
                break;


        }
    }
    //Clase interna para nodo
     private class Nodo {

        private User dato;
        private Nodo siguiente;

        public Nodo(User dato) {
            this.dato = dato;
            this.siguiente = null;
        }

        public User getDato() {
            return dato;
        }

        public Nodo getSiguiente() {
            return siguiente;
        }

        public void setSiguiente(Nodo siguiente) {
            this.siguiente = siguiente;
        }
    }
  
    // Clase interna tipo pila
    private class PilaAmigos {

        private Nodo cima;
        private int longitud;

        public PilaAmigos() {
            cima = null;
            longitud = 0;
        }

        public void push(User amigo) {
            Nodo nodo = new Nodo(amigo);
            nodo.setSiguiente(cima);
            cima = nodo;
            longitud++;
        }

        public boolean isEmpty() {
            return cima == null;
        }

        public ArrayList<User> toList() {
            ArrayList<User> lista = new ArrayList<>();
            Nodo nodo = cima;
            while (nodo != null) {
                lista.add(nodo.getDato());
                nodo = nodo.getSiguiente();
            }
            return lista;
        }
    }

    private boolean isAlreadyFriend(String friendUsername) { // Verifica si el amigo ya fue agregado
        ArrayList<Friends> allFriends = friendsDAO.getAll(); // Obtiene todas los amigoa
        for (Friends f : allFriends) {
            if (f.getUsername().equals(currentUser) && f.getFriendUsername().equals(friendUsername)) {
                return true;
            }
        }
        return false;
    }

   public DefaultTableModel getFriendsTableModel(boolean ordenarPorNivel) {
        String[] columnNames = {"Username", "Nivel"};
        ArrayList<Friends> allFriends = friendsDAO.getAll();
        ArrayList<String> friendList = new ArrayList<>();

        for (Friends f : allFriends) {
            if (f.getUsername().equals(currentUser)) {
                friendList.add(f.getFriendUsername());
            }
        }

        UserRegister userRegister = new UserRegister();
        PilaAmigos pila = new PilaAmigos();

        for (String username : friendList) {
            User amigo = userRegister.searchByUsername(username);
            if (amigo != null) {
                pila.push(amigo);
            }
        }

        ArrayList<User> amigos = pila.toList();
        if (ordenarPorNivel) {
            amigos.sort((a, b) -> Integer.compare(b.getLevel(), a.getLevel()));
        }

        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        for (User user : amigos) {
            model.addRow(new Object[]{user.getUsername(), user.getLevel()});
        }

        return model;
    }
}
