package shelter;

import aux.ObjetoEnvio;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;
import static shelter.AES.doEncryptedAES;

/**
 */
public class ConexionServidor implements ActionListener {

    private Usuario usuario;
    private Socket socket;
    private JTextField tfMensaje;
    private String key;
    private Mensaje mensaje;
    

    public void escribirSocket(ObjetoEnvio objeto) {

        //OutputStream flujo = null;
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(socket.getOutputStream());
            System.out.println(objeto);
            oos.writeObject(objeto);
        } catch (Exception ex) {
            System.out.println("Fallo en envia socket de Conexion servidor");
        }

    }
    
    public ObjetoEnvio leerSocket(Socket socket) throws IOException {
        ObjetoEnvio objeto = null;

        InputStream aux = socket.getInputStream();
        ObjectInputStream flujo = new ObjectInputStream(aux);
        try {
            objeto = (ObjetoEnvio) flujo.readObject();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Shelter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return objeto;
    }
    
    public void print(String mensaje){
        System.out.println(mensaje);
    }
    
    public void setKey(String a){ key = a;}
    
    public void getPublica(RSA rsa,ObjetoEnvio objeto){
        BigInteger publica= rsa.getPublicKey();
        String stringPublica = rsa.toString(publica);
        objeto.setPublicaEmisor(stringPublica);
        key = stringPublica;
    }
    
    public void getPrivada(RSA rsa,ObjetoEnvio objeto){
        BigInteger privada= rsa.getPrivateKey();
           
        String stringPrivada = rsa.toString(privada);
        String password = usuario.getPassword();
        stringPrivada = doEncryptedAES(stringPrivada,password);
        objeto.setPrivada(stringPrivada);
        
        
    }
    
    public void getModulus(RSA rsa,ObjetoEnvio objeto){
        BigInteger modulus= rsa.getModulus();
        String stringModulus = rsa.toString(modulus);
        objeto.setModulus(stringModulus);
        
    }

    //registra el usuario en el servidor
    public ConexionServidor(Usuario usuario, String key) {
        this.usuario = usuario;
        String user = usuario.getUsuario();
        String ip = usuario.getIP();
        String password = usuario.getPassword();
        int port = usuario.getPuerto();
        try {
            socket = new Socket(ip, port);
            System.out.println("Socket creado correctamente.");
            ObjetoEnvio objeto = new ObjetoEnvio(user,"servidor","","REGISTRO");
            objeto.setPassword(password);
            //Parte de RSA
            RSA objetoRSA = new RSA(1024);
            getPrivada(objetoRSA,objeto);
            getPublica(objetoRSA,objeto);
            getModulus(objetoRSA,objeto);
            escribirSocket(objeto);

        } catch (IOException ex) {
            System.out.println("Error al crear socket");
        }

    }
    
    //login del usuario
    public ConexionServidor(Usuario usuario) {
        this.usuario = usuario;
        String user = usuario.getUsuario();
        String password = usuario.getPassword();
        String ip = usuario.getIP();
        int port = usuario.getPuerto();
        
        
        try {
            socket = new Socket(ip, port);
            System.out.println("Socket creado correctamente.");
            ObjetoEnvio objeto = new ObjetoEnvio(user,"servidor","","LOGIN");
            objeto.setPassword(password);
            
           
            escribirSocket(objeto);

        } catch (IOException ex) {
            System.out.println("Error al crear socket");
        }

    }

    public void setJTextField(JTextField tfMensaje) {
        this.tfMensaje = tfMensaje;
    }

    public Socket getSocket() {
        return socket;
    }
    
    public void setMensaje(Mensaje m){mensaje = m;}

    @Override
    public void actionPerformed(ActionEvent e) {
        
        String user = usuario.getUsuario();
        print("usuario: " + user);
        String receptor = mensaje.getReceptor();
        print("receptor: " + receptor);
        ObjetoEnvio objeto = new ObjetoEnvio();
        objeto.setEmisor(user);
        objeto.setReceptor(receptor);
        objeto.setTipo("MENSAJE");
        
        print("key: " + key);
        String mensajeCifrado = doEncryptedAES(user + ": " + tfMensaje.getText(), key);
        print("mensaje cifrado: " + mensajeCifrado);
        objeto.setMensaje(mensajeCifrado);
        escribirSocket(objeto);
        tfMensaje.setText("");
    }
    

}
