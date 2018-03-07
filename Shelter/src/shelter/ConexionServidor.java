package shelter;

import aux.ObjetoEnvio;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;

/**
 */
public class ConexionServidor implements ActionListener {

    private Usuario usuario;
    private Socket socket;
    private JTextField tfMensaje;

    public void escribirSocket(Socket socket, ObjetoEnvio objeto) {

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

    //registra el usuario en el servidor
    public ConexionServidor(Usuario usuario) {
        this.usuario = usuario;
        String user = usuario.getUsuario();
        String ip = usuario.getIP();
        int port = usuario.getPuerto();

        try {
            socket = new Socket(ip, port);
            System.out.println("Socket creado correctamente.");
            //registro al usuario en el servidor
            String mensaje = "REGISTRO: " + ip + ":" + port + ":" + user;

            ObjetoEnvio objeto = new ObjetoEnvio();
            objeto.setMensaje(mensaje);
            objeto.setEmisor(user);
            objeto.setReceptor("");
            System.out.println(objeto.getMensaje());
            System.out.println(objeto.getEmisor());
            
            
            escribirSocket(socket, objeto);

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

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("0");
        String user = usuario.getUsuario();

        ObjetoEnvio objeto = new ObjetoEnvio();
        objeto.setEmisor(user);
        objeto.setMensaje(user + ": " + tfMensaje.getText());
        escribirSocket(socket, objeto);
        tfMensaje.setText("");

    }

}
