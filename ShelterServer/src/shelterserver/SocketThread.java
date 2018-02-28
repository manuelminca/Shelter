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
import static java.lang.Math.log;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author minguez
 */

//hilo del socke
public class SocketThread extends Thread implements Observer{

    private Socket skCliente;
    //private String ipUsuario;
    //private String puertoUsuario;
    private MensajesChat mensajes;
    

    final static List<Cliente> clientes = new ArrayList<Cliente>(); //Aqui se guardan las ip, los puertos y el nombre de usuario, pienso que tambien deberiamos guardar el socket
    //abierto correspondiente para directamente buscar por username y mandar la info al socket

    public SocketThread(Socket p_cliente,MensajesChat mensaje) {
        this.skCliente = p_cliente;
        this.mensajes = mensaje;
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
        String nuevoUsuario = partes[3];
        boolean aux = false;

        System.out.println(clientes.size());

        if (clientes.size() != 0) {
            for (Cliente cliente : clientes) {
                if (cliente.getUsername().equals(partes[3])) {
                    aux = true;
                }
            }
        }

        if (!aux) {
            //añadir el usuario a la lista de usuarios conectados 

            //Socket socket;
            //socket = new Socket(partes[1], Integer.parseInt(partes[2])); //socket = new Socket(partes[1], Integer.parseInt(partes[2]));
            Cliente cliente = new Cliente(partes[1], partes[2], partes[3]);
            clientes.add(cliente); //añadimos el cliente a la lista de clientes cuando no esta previamente registrado
            System.out.println("Cliente " + partes[3] + " añadido al arraylist");
            mensajes.setMensaje("usuario recibido");

        }
          
    }

    public void procesaCadena(String mensaje) throws IOException {
        String[] partes = mensaje.split(":");
        if (partes[0].equals("REGISTRO")) {
            addUsuario(partes);
        }else {
            mensajes.setMensaje(mensaje);
            //Aqui tenemos que mirar ahora si es un mensaje normal de chat y entonces buscar el usuario y enviarselo a esa direccion
            //Como sabemos el puerto e ip del usuario tenemos que enviarle al socket correspondiente   
            //escribirSocket(clientes.get(0).getSocket(), "Hola soy el servidor tio");
         
        }

        
    }

    @Override
    public void run() {

        System.out.println("Comienza el run()");

        try {
            //decimos los metodos observados
            mensajes.addObserver(this);

            while(true){
                String mensaje = leerSocket(skCliente); //de primeras recibimos la cadena para registrar el usuario
                System.out.println("cadena recibida de  " + skCliente.getInetAddress().getHostName()
                        +":" + skCliente.getPort());
  
                procesaCadena(mensaje); //cuando esto acabe ya hay un elemento mas en el arraylist con ip, puerto, nombre de usuario y socket
            }           
        } catch (IOException ex) {

            Logger.getLogger(SocketThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void update(Observable o,Object arg) {
        try {
            // Envia el mensaje al cliente
            System.out.println("update " + arg.toString());
            escribirSocket(skCliente,arg.toString());
        } catch (IOException ex) {
            System.out.println("Error al enviar mensaje al cliente (" + ex.getMessage() + ").");
        }
    }
}
