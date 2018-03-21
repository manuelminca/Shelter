/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author minguez
 */
public class Conversacion {

    private Connection myObjCon = null;
    private Statement stmt = null; //allows to execute querys in our db the rsult of the query will be a result set
    private ResultSet result = null;

    public Conversacion() throws SQLException {
        try {
            myObjCon = DriverManager.getConnection("jdbc:derby://localhost:1527/ShelterDB", "root", "root");
            stmt = myObjCon.createStatement();

        } catch (SQLException e) {
            System.out.println("Ha fallado el constructor de BD/Usuario");
        }
    }

    public void getUsuarios(int chat) {
        try {
            String usuarios = "";
            result = stmt.executeQuery("SELECT Usuarios FROM ROOT.CONVERSACION WHERE CHAT=" + chat);
            while(result.next()){
                usuarios = result.getString("USUARIO") + ":";
            }

            System.out.println(usuarios);
     
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String devolverKey(int chat,String emisor){
        String key = "";
        
        try {
            String usuarios = "";
            result = stmt.executeQuery("SELECT cifrado FROM ROOT.CONVERSACION WHERE CHAT=" + chat + "and USUARIO='" + emisor + "'");
            while(result.next()){
                key = result.getString("CIFRADO");
            }

            System.out.println(usuarios);
     
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        return key;
    }

    public void getChats(String user) {
        
    }
    
    public int existeChat(String user1, String user2, String key){

        try {
            String usuarios = "";
            
            
            String consulta1 = "SELECT CHAT FROM ROOT.CONVERSACION WHERE USUARIO = ?";

            PreparedStatement st1 = myObjCon.prepareStatement(consulta1);
            st1.setString(1, user1);
            ResultSet result1 = st1.executeQuery();
            while(result1.next()){
                String consulta2 = "SELECT CHAT FROM ROOT.CONVERSACION WHERE USUARIO = ?";
                PreparedStatement st2 = myObjCon.prepareStatement(consulta2);
                st2.setString(1, user2);
                ResultSet result2 = st2.executeQuery();            

                while(result2.next()){
                    int a = result1.getInt("CHAT");
                    int b = result2.getInt("CHAT");
                    
                    if(result1.getInt("CHAT") == result2.getInt("CHAT")){
                        System.out.println("DEBERIA ENTRAR AQUI");
                        return result1.getInt("CHAT");
                    }
                }  
            }

            Chat chat = new Chat();
            int id_chat = chat.createChat();
            createConversacion(id_chat, user1, key);
            createConversacion(id_chat, user2, key);
            return id_chat;
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    
    public int existeChat(String user1, String user2){

        try {
            String usuarios = "";
            
            
            String consulta1 = "SELECT CHAT FROM ROOT.CONVERSACION WHERE USUARIO = ?";

            PreparedStatement st1 = myObjCon.prepareStatement(consulta1);
            st1.setString(1, user1);
            ResultSet result1 = st1.executeQuery();
            while(result1.next()){
                String consulta2 = "SELECT CHAT FROM ROOT.CONVERSACION WHERE USUARIO = ?";
                PreparedStatement st2 = myObjCon.prepareStatement(consulta2);
                st2.setString(1, user2);
                ResultSet result2 = st2.executeQuery();            

                while(result2.next()){
                    int a = result1.getInt("CHAT");
                    int b = result2.getInt("CHAT");
                    
                    if(result1.getInt("CHAT") == result2.getInt("CHAT")){
                        return result1.getInt("CHAT");
                    }
                }  
            }            
            
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    
    
        public int createConversacion(int chat, String usuario, String key) {
        int ultimo = 0;
        int id_num = -1;
        try {
            
            String id = chat + "";
            
            String sql = "INSERT INTO ROOT.CONVERSACION VALUES('"+ usuario +"'," + id + ",'"+ key +"')";
            stmt.executeUpdate(sql);
            
     
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return id_num;
    }

}
