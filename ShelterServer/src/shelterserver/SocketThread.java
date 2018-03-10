/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shelterserver;

import aux.ObjetoEnvio;
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
    
    // los chats abiertos
    final static List<ObjetoEnvio> objetos = new ArrayList<ObjetoEnvio>();
    // todos los usuarios
    final static List<String> clientes = new ArrayList<String>(); 

    public SocketThread(Socket p_cliente,MensajesChat mensaje) {
        this.skCliente = p_cliente;
        this.mensajes = mensaje;
    }

    public ObjetoEnvio leerSocket(Socket socket) throws IOException, ClassNotFoundException {
        InputStream aux = socket.getInputStream();
        ObjectInputStream flujo = new ObjectInputStream(aux);
        ObjetoEnvio objeto;
        objeto = (ObjetoEnvio) flujo.readObject();
        System.out.println("5");
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
        if (!aux){
            
            clientes.add(objeto.getEmisor());
            objeto.setMensaje("");
            //recojo el usuario
            String user = objeto.getEmisor();
            objeto.setEmisor("servidor");
            objeto.setReceptor(user);
            //ACK ok del servidor que se ha registrado
            objeto.setTipo("ACK");
            String mensaje = "Usuario " + user + "registrado";
            objeto.setMensaje("Usuario " + user + "registrado");
            
            mensajes.setObjeto(objeto);
        }
    }
    
    
    public void devolverUsuarios(ObjetoEnvio objeto){
        
        String cadenaClientes = "";
        for(int i = 0;i<clientes.size(); i++){
            cadenaClientes += clientes.get(i) + ":";
        }

        objeto.setMensaje(cadenaClientes);
        String receptor = objeto.getEmisor();
        objeto.setEmisor("servirdor");
        objeto.setReceptor(receptor);
        mensajes.setObjeto(objeto);
    }

    public void procesaCadena(ObjetoEnvio objeto) throws IOException {
        
        String tipo = objeto.getTipo();
        System.out.println("tipo: " + tipo);
        if (tipo.equals("REGISTRO")) {
            addUsuario(objeto);
        }else if(tipo.equals("LISTAR")){
            devolverUsuarios(objeto);         
        }else  mensajes.setObjeto(objeto);
    }

    @Override
    public void run() {
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
            escribirSocket(skCliente, (ObjetoEnvio) arg);
        } catch (IOException ex) {
            System.out.println("Error al enviar mensaje al cliente (" + ex.getMessage() + ").");
        }
    }
}
