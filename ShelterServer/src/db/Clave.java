/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.math.BigInteger;
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
public class Clave {

    private Connection myObjCon = null;
    private Statement stmt = null; //allows to execute querys in our db the rsult of the query will be a result set
    private ResultSet result = null;

    public Clave() throws SQLException {
        try {
            myObjCon = DriverManager.getConnection("jdbc:derby://localhost:1527/ShelterDB", "root", "root");
            stmt = myObjCon.createStatement();

        } catch (SQLException e) {
            System.out.println("Ha fallado el constructor de BD/Usuario");
        }
    }

    public void createClave(String usuario, BigInteger publica, BigInteger privada, BigInteger modulus) {

        if (!comprobarClave(usuario)) {
            try {
                int ultimo = 0;
                result = stmt.executeQuery("SELECT * FROM ROOT.CLAVE");
                while (result.next()) {
                    ultimo = result.getInt("ID");
                }
                int id_num = ultimo + 1;
                String id = id_num + "";
                String sql = "INSERT INTO ROOT.CLAVE VALUES(" + id + "," + usuario + "," + publica + "," + privada + "," + modulus + ")";
                stmt.executeUpdate(sql);

            } catch (SQLException ex) {
                Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean comprobarClave(String usuario) {
        int numero = 0;
        try {
            String consulta = "SELECT Count(Usuario) FROM ROOT.Clave WHERE Usuario = ?";
            PreparedStatement st = myObjCon.prepareStatement(consulta);
            st.setString(1, usuario);
            result = st.executeQuery();

            while (result.next()) {
                // 1 es el numero de columna
                numero = result.getInt(1);
            }

            if (numero == 0) {
                return false;
            } else {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
