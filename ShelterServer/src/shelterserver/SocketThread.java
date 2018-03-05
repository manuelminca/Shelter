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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
    

    final static List<String> clientes = new ArrayList<String>(); //Aqui se guardan las ip, los puertos y el nombre de usuario, pienso que tambien deberiamos guardar el socket
    //abierto correspondiente para directamente buscar por username y mandar la info al socket

    public SocketThread(Socket p_cliente,MensajesChat mensaje) {
        this.skCliente = p_cliente;
        this.mensajes = mensaje;
    }

    public ObjetoEnvio leerSocket(Socket socket) throws IOException, ClassNotFoundException {
        InputStream aux = socket.getInputStream();
        ObjectInputStream flujo = new ObjectInputStream(aux);
        ObjetoEnvio objeto;
        objeto = (ObjetoEnvio) flujo.readObject();
        return objeto;
    }
    
    
    
    //le envio la informacion al cliente
    public void escribirSocket(Socket socket, ObjetoEnvio objeto) throws IOException {

        OutputStream aux = socket.getOutputStream();
        ObjectOutputStream flujo = new ObjectOutputStream(aux);
        flujo.writeObject(objeto);
    }

    private void addUsuario(ObjetoEnvio objeto) {
      
        boolean aux = false;

        if (clientes.size() != 0) {
            for (String cliente : clientes) {
                if (cliente.equals(objeto.getEmisor())) {
                    aux = true;
                }
            }
        }

        if (!aux) {
            //añadir el usuario a la lista de usuarios conectados 
            devolverUsuarios();

        }
          
    }
    
    
    public void devolverUsuarios(){
        ObjetoEnvio obj = new ObjetoEnvio();
        String cadenaClientes = "";
            for(int i = 0;i<clientes.size(); i++){
                cadenaClientes += clientes.get(i) + ":";
            }
            
            obj.setMensaje(cadenaClientes);
            mensajes.setObjeto(obj);
    }

    public void procesaCadena(ObjetoEnvio objeto) throws IOException {
        String[] partes = objeto.getMensaje().split(":");
        if (partes[0].equals("REGISTRO")) {
            addUsuario(objeto);
        }else {
            System.out.println("asdasddasd");
            mensajes.setObjeto(objeto);
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
            ObjetoEnvio objeto = new ObjetoEnvio();
            

            while(true){
                objeto = leerSocket(skCliente); //de primeras recibimos la cadena para registrar el usuario  
                procesaCadena(objeto); //cuando esto acabe ya hay un elemento mas en el arraylist con ip, puerto, nombre de usuario y socket
            }           
        } catch (Exception ex) {
            System.out.println("Ha fallado el try del run()");
        }
    }
    
    @Override
    public void update(Observable o,Object arg) {
        try {
            // Envia el mensaje al cliente            
            escribirSocket(skCliente, (ObjetoEnvio) arg);
        } catch (IOException ex) {
            System.out.println("Error al enviar mensaje al cliente (" + ex.getMessage() + ").");
        }
    }
}
