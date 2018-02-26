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
public class SocketThread extends Thread {

    private Socket skCliente;
    private String ipUsuario;
    private String puertoUsuario;

    public SocketThread(Socket p_cliente, String ipUsuario, String puertoUsuario) {
        this.skCliente = p_cliente;
        this.ipUsuario = ipUsuario;
        this.puertoUsuario = puertoUsuario;
    }

    public void run() {
        System.out.println("shelterserver.SocketThread.run()");
    }
}
