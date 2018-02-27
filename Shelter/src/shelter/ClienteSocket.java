/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shelter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author minguez
 */
public class ClienteSocket {

    private Socket socketServidor;

    public ClienteSocket() {
        try {
            socketServidor = new Socket("localhost", 4000);
        } catch (Exception e) {
            System.out.println("ERROR LANZADO");
        }

    }

    public String leerSocket(Socket socket) throws IOException {
        String datos;

        InputStream aux = socket.getInputStream();
        DataInputStream flujo = new DataInputStream(aux);
        datos = new String();
        datos = flujo.readUTF();
        return datos;
    }

    //pasamos la informacion al servidor
    public void escribirSocket(Socket socket, String datos) throws IOException {
        OutputStream aux = socket.getOutputStream();
        DataOutputStream flujo = new DataOutputStream(aux);
        flujo.writeUTF(datos);
    }

    public void cierraSocket() throws IOException {
        socketServidor.close();
    }

    public void procesaPeticion(String cadena) {
        try {
            escribirSocket(socketServidor, cadena);
        } catch (Exception e) {
            System.out.println("ERROR LANZADO en Procesa Cadena");
        }
    }

    
    
    public void iniciaServidor() throws IOException{
        ServerSocket skServidor = new ServerSocket(3000);
        Socket socketCliente = skServidor.accept();
    }
    
    
    public void iniciaConexion(String ip, String puerto, String username) {
        try {

            String cadena = "REGISTRO@" + ip + "@" + puerto + "@" + username;
            System.out.println(cadena);
            escribirSocket(socketServidor, cadena);
        } catch (Exception e) {
            System.out.println("ERROR LANZADO");
        }

    }

}
