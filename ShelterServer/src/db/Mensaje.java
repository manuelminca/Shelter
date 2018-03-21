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
public class Mensaje {

    private Connection myObjCon = null;
    private Statement stmt = null; //allows to execute querys in our db the rsult of the query will be a result set
    private ResultSet result = null;

    public Mensaje() throws SQLException {
        try {
            myObjCon = DriverManager.getConnection("jdbc:derby://localhost:1527/ShelterDB", "root", "root");
            stmt = myObjCon.createStatement();

        } catch (SQLException e) {
            System.out.println("Ha fallado el constructor de BD/Usuario");
        }
    }

    public void createMensaje(String mensaje, int chat) {
        try {
            
            int ultimo = 0;
            result = stmt.executeQuery("SELECT * FROM ROOT.MENSAJE");
            while(result.next()){
                ultimo = result.getInt("ID");
            }
            int id_num = ultimo + 1;
            String id = id_num + "";
            String chat_string = chat +"";
            String sql = "INSERT INTO ROOT.MENSAJE VALUES(" + id + ", '" + mensaje + "', " + chat_string + ")";
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getMensajes(int chat) {
        String msj = "";
        try {
            PreparedStatement st = myObjCon.prepareStatement("SELECT MENSAJE FROM ROOT.MENSAJE WHERE chat = ?");
            st.setInt(1, chat);
            result = st.executeQuery();
            
            while(result.next()){
                msj = msj + result.getString("MENSAJE") + "\n";
            }
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return msj;
    }

}
