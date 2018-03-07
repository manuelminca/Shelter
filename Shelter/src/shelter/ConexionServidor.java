package shelter;

import aux.ObjetoEnvio;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JTextField;
import static shelter.AES.doEncryptedAES;

/**
 */
public class ConexionServidor implements ActionListener {

    private Usuario usuario;
    private Socket socket;
    private JTextField tfMensaje;
    private String key;

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
    public ConexionServidor(Usuario usuario, String key) {
        this.usuario = usuario;
        String user = usuario.getUsuario();
        String ip = usuario.getIP();
        int port = usuario.getPuerto();
        this.key = key;

        try {
            socket = new Socket(ip, port);
            System.out.println("Socket creado correctamente.");
            //registro al usuario en el servidor
            //String mensaje = "REGISTRO: " + ip + ":" + port + ":" + user;

            ObjetoEnvio objeto = new ObjetoEnvio(user,"servidor","","REGISTRO");
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
        objeto.setTipo("MENSAJE");
        
        String mensajeCifrado = doEncryptedAES(user + ": " + tfMensaje.getText(), key);
        
        objeto.setMensaje(mensajeCifrado);
        escribirSocket(socket, objeto);
        tfMensaje.setText("");
    }
    

}
