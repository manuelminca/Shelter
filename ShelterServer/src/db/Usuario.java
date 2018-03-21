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
public class Usuario {

    private Connection myObjCon = null;
    private Statement stmt = null; //allows to execute querys in our db the rsult of the query will be a result set
    private ResultSet result = null;
    private boolean online = false;

    public Usuario() throws SQLException {
        try {
            myObjCon = DriverManager.getConnection("jdbc:derby://localhost:1527/ShelterDB", "root", "root");
            stmt = myObjCon.createStatement();

        } catch (SQLException e) {
            System.out.println("Ha fallado el constructor de BD/Usuario");
        }
    }

    public void createUsuario(String usuario) {
        try {
            stmt.executeUpdate("INSERT INTO ROOT.USUARIO VALUES ('" + usuario + "', true)");
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public String devolverUsuario() {
         String cadena = "";
        try {
           
            String consulta = "SELECT  NOMBRE FROM ROOT.USUARIO WHERE online = ?";
            PreparedStatement st = myObjCon.prepareStatement(consulta);
            st.setBoolean(1, true);
            result = st.executeQuery();
            
            while (result.next()) { 
               
                cadena += result.getString(1) + ":";
            }
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cadena;
    }
    
    
    public boolean comprobarOnline(String usuario){
       boolean ok = false;
        try {
            String consulta = "SELECT online FROM ROOT.USUARIO WHERE nombre = ?";
            PreparedStatement st = myObjCon.prepareStatement(consulta);
            st.setString(1, usuario);
            result = st.executeQuery();
            
            while (result.next()) { 
                // 1 es el numero de columna
                ok = result.getBoolean(1);
            }
           

            //ok = result.getBoolean("online");
            
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return ok;
    }
    
    
    public void setOnline(String usuario,boolean estado){
        try {
            String consulta = "UPDATE ROOT.USUARIO SET online = ? WHERE nombre = ?";
            PreparedStatement st = myObjCon.prepareStatement(consulta);
            st.setBoolean(1, estado);
            st.setString(2, usuario);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    public boolean buscarUsuario(String usuario){
        boolean encontrado = false;
        try {
            String consulta = "SELECT  NOMBRE FROM ROOT.USUARIO WHERE NOMBRE = ?";
            PreparedStatement st = myObjCon.prepareStatement(consulta);
            st.setString(1, usuario);
            result = st.executeQuery();
            while (result.next()) { 
                if(result.getString(1).equals(usuario)) encontrado = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return encontrado;
    }
    
    

    public void deleteUsuario(String usuario) {
        try {
            PreparedStatement st = myObjCon.prepareStatement("DELETE FROM USUARIO WHERE nombre = ?");
            st.setString(1, usuario);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
