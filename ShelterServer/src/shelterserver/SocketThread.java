/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shelterserver;

import aux.ObjetoEnvio;
import db.Conversacion;
import db.Mensaje;
import db.Usuario;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import static java.lang.Math.log;
import java.net.Socket;
import java.sql.SQLException;
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
    private Usuario us;
    
    public SocketThread(Socket p_cliente,MensajesChat mensaje) {
        try {  
            this.us = new Usuario();
        } catch (SQLException ex) {
            Logger.getLogger(SocketThread.class.getName()).log(Level.SEVERE, null, ex);
        }
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
      

        // Creamos en la base de datos el usuario
        String usuario = objeto.getEmisor();
        if(us.buscarUsuario(usuario)) us.setOnline(usuario,true);
        else us.createUsuario(objeto.getEmisor());
        
        //aádimos el mensaje en el array (Ver si es util)
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
    
    
    public void devolverUsuarios(ObjetoEnvio objeto){
        
        String cadenaClientes = "";
        //buscamos los usuarios online
        cadenaClientes = us.devolverUsuario();
      
        objeto.setMensaje(cadenaClientes);
        String receptor = objeto.getEmisor();
        objeto.setEmisor("servidor");
        objeto.setReceptor(receptor);
        mensajes.setObjeto(objeto);
    }

    
    public void iniciarChat(ObjetoEnvio objeto){
        
        Conversacion con;
        try {
            con = new Conversacion();
            int chat = con.existeChat(objeto.getEmisor(), objeto.getReceptor(), objeto.getMensaje());
            //obtenemos la key
            String key = con.devolverKey(chat, objeto.getEmisor());
            Mensaje msj = new Mensaje();
            //obtenemos los mensajes
            String conversacion = msj.getMensajes(chat);
            
            if(conversacion.equals("")){
                conversacion = key +  ":\n";
            }else{
                conversacion = key +  ":" + conversacion;
            }
            
            
            ObjetoEnvio obj = new ObjetoEnvio("Servidor", objeto.getEmisor(), conversacion, "CHAT");
            mensajes.setObjeto(obj);
        } catch (SQLException ex) {
            Logger.getLogger(SocketThread.class.getName()).log(Level.SEVERE, null, ex);
        }        
        
    }
    
    public void desconectarUsuario(ObjetoEnvio objeto){
        us.setOnline(objeto.getEmisor(),false);
       
    }
    
    public void procesaCadena(ObjetoEnvio objeto) throws IOException {
        
        String tipo = objeto.getTipo();
        System.out.println("tipo: " + tipo);
        if (tipo.equals("REGISTRO")) {
            addUsuario(objeto);
        }else if(tipo.equals("LISTAR")){
            devolverUsuarios(objeto);         
        }else if(tipo.equals("CHAT")){
            iniciarChat(objeto);   
        }else if(tipo.equals("SALIR")){
            desconectarUsuario(objeto);
           
        }else{
            
            try {
                Conversacion con = new Conversacion();
                int chat = con.existeChat(objeto.getEmisor(), objeto.getReceptor());

                Mensaje msj = new Mensaje();
                msj.createMensaje(objeto.getMensaje(), chat);
                
                
                mensajes.setObjeto(objeto);
            } catch (SQLException ex) {
                Logger.getLogger(SocketThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void run() {
        try {
            //decimos los metodos observados
            mensajes.addObserver(this);
            ObjetoEnvio objeto;
            
            while(true){
                objeto = leerSocket(skCliente);
                procesaCadena(objeto); 
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
