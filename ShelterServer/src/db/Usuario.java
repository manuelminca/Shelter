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
            stmt.executeUpdate("INSERT INTO ROOT.USUARIO VALUES ('" + usuario + "')");
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
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
