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
public class Chat {

    private Connection myObjCon = null;
    private Statement stmt = null; //allows to execute querys in our db the rsult of the query will be a result set
    private ResultSet result = null;

    public Chat() throws SQLException {
        try {
            myObjCon = DriverManager.getConnection("jdbc:derby://localhost:1527/ShelterDB", "root", "root");
            stmt = myObjCon.createStatement();

        } catch (SQLException e) {
            System.out.println("Ha fallado el constructor de BD/Usuario");
        }
    }

    public int createChat() {
        int ultimo = 0;
        int id_num = -1;
        try {
            
            result = stmt.executeQuery("SELECT * FROM ROOT.CHAT");
            while(result.next()){
                ultimo = result.getInt("ID");
            }
            id_num = ultimo + 1;
            String id = id_num + "";
            String sql = "INSERT INTO ROOT.CHAT VALUES(" + id + ")";
            stmt.executeUpdate(sql);
            
     
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return id_num;
    }

    public void deleteChat(int id) {
        try {
            PreparedStatement st = myObjCon.prepareStatement("DELETE FROM CHAT WHERE id = ?");
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
