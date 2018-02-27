/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shelterserver;

import java.net.Socket;

/**
 *
 * @author minguez
 */


// informacion del cliuente
public class Cliente {
    private String ip;
    private String puerto;
    private String username;
    private Socket socket;

    public Cliente(String ip, String puerto, String username, Socket socket) {
        this.ip = ip;
        this.puerto = puerto;
        this.username = username;
        this.socket = socket;
    }

    public String getIp() {
        return ip;
    }

    public String getPuerto() {
        return puerto;
    }

    public String getUsername() {
        return username;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setPuerto(String puerto) {
        this.puerto = puerto;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
    
    
    
    
    
}
