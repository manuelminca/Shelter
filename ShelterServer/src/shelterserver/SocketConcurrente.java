/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shelterserver;

import java.io.IOException;
import java.net.*;

/**
 *
 * @author minguez
 */

//crea los hilos del socket
public class SocketConcurrente {

    public void inicio() {
        int puerto = 4000;
        Socket skCliente = null;
        ServerSocket skServidor = null;
        try {
            MensajesChat mensajes = new MensajesChat();

            skServidor = new ServerSocket(puerto);
            System.out.println("Escucho el puerto " + puerto);

            for (;;) {
                skCliente = skServidor.accept(); // Crea objeto
                System.out.println("Cliente: " + skCliente.getInetAddress().getHostName()
                        +":" + skCliente.getPort()+ " conectado.");

                SocketThread t = new SocketThread(skCliente,mensajes);
                t.start();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }finally{
            try {
                skCliente.close();
                skServidor.close();
            } catch (IOException ex) {
                System.out.println("Error al cerrar el servidor: " + ex.getMessage());
            }
        }
        
        System.out.println("Termino icinicio");

    }
}
