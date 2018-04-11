/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shelterserver;

import aux.ObjetoEnvio;
import db.Clave;
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
import java.math.BigInteger;
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
        return objeto;
    }
    
    
    
    //le envio la informacion al cliente
    public void escribirSocket(Socket socket, ObjetoEnvio objeto) throws IOException {

        OutputStream aux = socket.getOutputStream();
        ObjectOutputStream flujo = new ObjectOutputStream(aux);
        flujo.writeObject(objeto);
    }
    
    
    private void login(ObjetoEnvio objeto){
        
        String usuario = objeto.getEmisor();
        String password = objeto.getPassword();
        
        if(us.validarUsuario(usuario,password)){
 
            us.setOnline(usuario,true);
            Clave clave;
            try {
                clave = new Clave();
                String modulus = clave.getModulus(usuario);
                String privada = clave.getPrivada(usuario);
                String publica = clave.getPublica(usuario);
                objeto.setModulus(modulus);
                objeto.setPrivada(privada);
                objeto.setPublicaEmisor(publica);
                objeto.setTipo("ACK");
               
                objeto.setMensaje("Usuario " + usuario + "registrado");
            } catch (SQLException ex) {
                Logger.getLogger(SocketThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }else{
            objeto.setTipo("!ACK");
        }
        
         mensajes.setObjeto(objeto);
        
    }

    private void addUsuario(ObjetoEnvio objeto) {
        // Creamos en la base de datos el usuario
        String usuario = objeto.getEmisor();
        String pass = objeto.getPassword();
       
        us.createUsuario(usuario,pass);
        try {
            Clave clave = new Clave();
            clave.createClave(usuario, objeto.getPublicaEmisor(), objeto.getPrivada(), objeto.getModulus());

        } catch (SQLException ex) {
            Logger.getLogger(SocketThread.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
        //aádimos el mensaje en el array (Ver si es util)
        objeto.setMensaje("");
        //recojo el usuario
        String user = objeto.getEmisor();
        objeto.setEmisor("servidor");
        objeto.setReceptor(user);
        //ACK ok del servidor que se ha registrado
        objeto.setTipo("REGISTRO");
        String mensaje = "Usuario " + user + "registrado";
        objeto.setMensaje("Usuario " + user + "registrado");
               
        mensajes.setObjeto(objeto);
    }
    
    
    public void devolverUsuarios(ObjetoEnvio objeto){
        
        String cadenaClientes = "";
        //buscamos los usuarios online
        cadenaClientes = us.devolverUsuario();
        System.out.println("cadena: " + cadenaClientes);
        objeto.setMensaje(cadenaClientes);
        String receptor = objeto.getEmisor();
        objeto.setEmisor("servidor");
        objeto.setReceptor(receptor);
        mensajes.setObjeto(objeto);
    }
    
    
    public String getClaveReceptor(ObjetoEnvio objeto){
        String clave = "";
        
        try {
            Clave key = new Clave();
            String receptor = objeto.getReceptor();
            clave = key.getPublica(receptor);
            objeto.setPublicaReceptor(clave);
            mensajes.setObjeto(objeto);
        } catch (SQLException ex) {
            Logger.getLogger(SocketThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return clave;
    }
    
    public int crearChat(ObjetoEnvio objeto,Conversacion con) throws SQLException{
        String receptor = objeto.getReceptor();
        String emisor = objeto.getEmisor();
        int chat = -1;
        Clave clave = new Clave();
        
        //recupero las claves publicas del emisor y receptor
        
        String publicaReceptor  = clave.getPublica(receptor);
        String publicaEmisor  = clave.getPublica(emisor);
        
        chat = con.crearChat(emisor, receptor,publicaReceptor,publicaEmisor);
        
       
        String privada = clave.getPrivada(receptor);
        String modulus = clave.getModulus(receptor);
        objeto.setPublicaEmisor(publicaReceptor);
        objeto.setPrivada(privada);
        objeto.setModulus(modulus);
        
        return chat;
    }

    
    public void iniciarChat(ObjetoEnvio objeto){
        
        Conversacion con;
        String receptor = objeto.getReceptor();
        String emisor = objeto.getEmisor();
        try {
            con = new Conversacion();
            int chat = con.existeChat(emisor, receptor, objeto.getMensaje());
            
            //SI NO EXISTE EL CHAT LO CREAMOS Y CONSEGUIKOS LAS CLAVES DEL RECEPTOR
            if(chat == -1) chat = crearChat(objeto,con);

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
        }else if(tipo.equals("CLAVE")){
            getClaveReceptor(objeto);
        }else if(tipo.equals("LOGIN")){
            login(objeto);
           
        }else{
            //ESTE ES EL CASO QUE YA EXISTA EL CHAT
           
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
