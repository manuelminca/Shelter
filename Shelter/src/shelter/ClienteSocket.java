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
import java.net.Socket;

/**
 *
 * @author minguez
 */
public class ClienteSocket {
    private Socket socket;
    
    public ClienteSocket() {
        try{
            final int puerto = 4000;
            socket = new Socket("192.168.1.3",puerto);
            System.out.println(socket.getPort());
        }catch(Exception e){
            System.out.println("ERROR LANZADO");
        }
        
    }
    
    public String leerSocket(Socket socket, String datos) throws IOException {
        InputStream aux = socket.getInputStream();
        DataInputStream flujo = new DataInputStream(aux);
        datos = flujo.readUTF();
        return datos;
    }

    //pasamos la informacion al servidor
    public void escribirSocket(Socket socket, String datos) throws IOException {
        OutputStream aux = socket.getOutputStream();
        DataOutputStream flujo = new DataOutputStream(aux);
        flujo.writeUTF(datos);
    }

    public void cierraSocket() throws IOException{
        socket.close();
    }
    
    public void procesaPeticion(String cadena) {
        try {
            escribirSocket(socket, cadena);
        } catch (Exception e) {
            System.out.println("ERROR LANZADO en Procesa Cadena");
        }   
    }
    public void iniciaConexion(String ip, String puerto, String username) {
        try{
            
            String cadena = "REGISTRO@" + ip + "@" + puerto + "@" + username;
            
            System.out.println("cadena que le paso al servidor: " + cadena);
            System.out.println("puesrto: " + socket.getPort());
            escribirSocket(socket, cadena);
           
        }catch(Exception e){
            System.out.println("ERROR LANZADO");
        }
        
    }

}
