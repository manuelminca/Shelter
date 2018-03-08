/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author minguez
 */
public class Modelo {
    private Connection myObjCon = null;
    private Statement myStatObj = null; //allows to execute querys in our db the rsult of the query will be a result set
    private ResultSet result = null;    //representation of the data

    public void prepararBD(){
             
        try {
            myObjCon = DriverManager.getConnection("jdbc:derby://localhost:1527/ShelterDB", "root", "root");
            myStatObj = myObjCon.createStatement();
        
        
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    
    
}
