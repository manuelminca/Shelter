/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shelterserver;

/**
 *
 * @author minguez
 */
public class Cliente {
    private String ip;
    private String puerto;
    private String username;

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
    
    
    
}
