/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shelterserver;

import java.net.*;

/**
 *
 * @author minguez
 */
public class SocketConcurrente {

    public void inicio() {
        int puerto = 4000;

        try {
            ServerSocket skServidor = new ServerSocket(puerto);
            System.out.println("Escucho el puerto " + puerto);

            for (;;) {
                Socket skCliente = skServidor.accept(); // Crea objeto
                System.out.println("Sirviendo cliente...");

                Thread t = new SocketThread(skCliente, "3000", "4000");
                t.start();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }
    }
}
