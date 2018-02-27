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

//hilo del socke
public class SocketThread extends Thread {

    private Socket skCliente;
    private String ipUsuario;
    private String puertoUsuario;
    final static List<Cliente> clientes = new ArrayList<Cliente>(); //Aqui se guardan las ip, los puertos y el nombre de usuario, pienso que tambien deberiamos guardar el socket
    //abierto correspondiente para directamente buscar por username y mandar la info al socket

    public SocketThread(Socket p_cliente) {
        this.skCliente = p_cliente;
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

    private void addUsuario(String[] partes) {
        String nuevoUsuario = partes[0];
        boolean aux = false;
        for (Cliente cliente : clientes) {
            if (cliente.getUsername().equals(partes[0])) {
                aux = true;
            }
        }

        if (!aux) {
            //añadir el usuario a la lista de usuarios conectados 

            Socket socket;
            try {
                int puerto = Integer.parseInt(partes[2]);
                System.out.println(puerto);
                socket = new Socket(partes[1], skCliente.getPort());
                Cliente cliente = new Cliente(partes[1], partes[2], partes[3], socket);

                clientes.add(cliente); //añadimos el cliente a la lista de clientes cuando no esta previamente registrado
            } catch (IOException ex) {
                Logger.getLogger(SocketThread.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public void procesaCadena(String mensaje) {

        String[] partes = mensaje.split("@");
        if (partes[0].equals("REGISTRO")) {
            addUsuario(partes);
        }

        //Aqui tenemos que mirar ahora si es un mensaje normal de chat y entonces buscar el usuario y enviarselo a esa direccion
        //Como sabemos el puerto e ip del usuario tenemos que enviarle al socket correspondiente
    }

    public void run() {

        System.out.println("Comienza el run()");

        try {
            String mensaje = leerSocket(skCliente); //de primeras recibimos la cadena para registrar el usuario
            System.out.println("cadena recibida de  " + skCliente.getInetAddress().getHostName()
                        +":" + skCliente.getPort());
            procesaCadena(mensaje); //cuando esto acabe ya hay un elemento mas en el arraylist con ip, puerto, nombre de usuario y socket
            System.out.println("termina el run()");

        } catch (IOException ex) {
            System.out.println("cdsfsdf el run()");

            Logger.getLogger(SocketThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
