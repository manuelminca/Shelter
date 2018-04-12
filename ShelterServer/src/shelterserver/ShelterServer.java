/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shelterserver;

import db.Chat;
import db.Clave;
import db.Conversacion;
import db.Mensaje;
import db.Usuario;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author minguez
 */
public class ShelterServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        
        //Clave cl = new Clave();
        
        //System.out.println(cl.getModulus("1"));
        
        SocketConcurrente sk_concurrente = new SocketConcurrente();
        sk_concurrente.inicio();
    }
    
}
