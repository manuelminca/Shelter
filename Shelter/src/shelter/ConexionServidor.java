
package shelter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import javax.swing.JTextField;

/**
 */


public class ConexionServidor implements ActionListener{
    

    private Usuario usuario;
    private Socket socket;
    private JTextField tfMensaje;
    
    
    public void escribirSocket(Socket socket, String datos) throws IOException {

        OutputStream aux = socket.getOutputStream();
        DataOutputStream flujo = new DataOutputStream(aux);
        flujo.writeUTF(datos);
    }
    
    //registra el usuario en el servidor
    public ConexionServidor(Usuario usuario) {
        this.usuario = usuario;
        String user = usuario.getUsuario();
        String ip = usuario.getIP();
        int port = usuario.getPuerto();
        
        try {
            socket = new Socket(ip,port);
            System.out.println("Socket creado correctamente.");
            //registro al usuario en el servidor
            String mensaje = "REGISTRO: " + ip +  ":" + port + ":" + user;
            escribirSocket(socket,mensaje);
            
        } catch (IOException ex) {
            System.out.println("Error al crear socket");
        }
        
    }
    
    public void setJTextField(JTextField tfMensaje){
        this.tfMensaje = tfMensaje;
    }
    
    public Socket getSocket(){return socket;}
    
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("0");
        String user = usuario.getUsuario();
        
        try {
            escribirSocket(socket,user + ": " + tfMensaje.getText() );
            tfMensaje.setText("");
        } catch (IOException ex) {
            System.out.println("Error al intentar enviar un mensaje: " + ex.getMessage());
        }
    }



}