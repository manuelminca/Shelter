/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shelterserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author minguez
 */
public class SocketThread extends Thread {

    private Socket skCliente;
    private String ipUsuario;
    private String puertoUsuario;
    List<Cliente> clientes; 

    public SocketThread(Socket p_cliente) {
        this.skCliente = p_cliente;
        clientes = new ArrayList<Cliente>();
    }

    public String leerSocket(Socket socket) throws IOException {
        String datos;

        InputStream aux = socket.getInputStream();
        DataInputStream flujo = new DataInputStream(aux);
        datos = new String();
        datos = flujo.readUTF();
        return datos;
    }

    //le envio la informacion al cliente
    public void escribirSocket(Socket socket, String datos) throws IOException {

        OutputStream aux = socket.getOutputStream();
        DataOutputStream flujo = new DataOutputStream(aux);
        flujo.writeUTF(datos);
    }

    private void addUsuario(String[] partes){
        String nuevoUsuario = partes[0];
        boolean aux = false;
        for (Cliente cliente : clientes) {
        if (cliente.getUsername().equals(partes[0])) {
            aux = true;
        }
        }
        
        if(!aux){
            //añadir el usuario a la lista de usuarios conectados 
            Cliente cliente = new Cliente();
            cliente.setIp(partes[1]);
            cliente.setPuerto(partes[2]);
            cliente.setUsername(partes[3]);
            
            clientes.add(cliente); //añadimos el cliente a la lista de clientes cuando no esta previamente registrado
        }
    }
    
    public void procesaCadena(String mensaje){
        
        String[] partes = mensaje.split("@");
        if(partes[0].equals("REGISTRO")){
            addUsuario(partes);
        }
        
        
        
    }
    public void run() {
        
        System.out.println("Comienza el run()");
        
        try {
            String mensaje = leerSocket(skCliente); //de primeras recibimos la cadena para registrar el usuario
            procesaCadena(mensaje); //cuando esto acabe ya hay un elemento mas en el arraylist con ip, puerto y nombre de usuario
            
        } catch (IOException ex) {
            Logger.getLogger(SocketThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
