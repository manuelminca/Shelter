package shelter;

import aux.ObjetoEnvio;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JTextField;
import org.apache.commons.codec.binary.Base64;

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
        
        String mensajeCifrado = doEncryptedAES(user + ": " + tfMensaje.getText(), key);
        
        objeto.setMensaje(mensajeCifrado);
        escribirSocket(socket, objeto);
        tfMensaje.setText("");
    }
    
    
    //#########################################################
    //#           SISTEMA DE CIFRADO EN AES-128               #
    //#########################################################
    
    static private final String ENCODING = "UTF-8";
    static private final String TRANSFORMATION = "AES/CBC/PKCS5Padding";
    static private final String AES = "AES";

    public static String doEncryptedAES(String msj, String key) {
        String msjEncrypted = "error_encrypted";
        byte[] msjEncryptedbyte = null;
        byte[] keyByte = null;
        Cipher cp;
        SecretKeySpec sks = null;
        IvParameterSpec ips = null;
        try {
            msjEncryptedbyte = msj.getBytes(ENCODING);
            keyByte = getKeyBytes(key);
        } catch (NullPointerException | UnsupportedEncodingException e) {
            System.out.println(e.getMessage());
            return msjEncrypted;
        }

        sks = new SecretKeySpec(keyByte, AES);
        ips = new IvParameterSpec(keyByte);

        try {
            cp = Cipher.getInstance(TRANSFORMATION);
            cp.init(Cipher.ENCRYPT_MODE, sks, ips);
            msjEncryptedbyte = cp.doFinal(msjEncryptedbyte);
            msjEncrypted = new String(Base64.encodeBase64(msjEncryptedbyte));
            return msjEncrypted;
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
            System.out.println(e.getMessage());
            return msjEncrypted;
        }
    }

    public static String doDecryptedAES(String msjEncrypted, String key) {
        String msjDecrypted = "error_decrypted";
        byte[] msjEncryptedByte;
        byte[] keyByte;
        try {
            msjEncryptedByte = Base64.decodeBase64(msjEncrypted.getBytes("UTF8"));
            keyByte = getKeyBytes(key);
        } catch (NullPointerException | UnsupportedEncodingException e) {
            System.out.println(e.getMessage());
            return msjDecrypted;
        }
        SecretKeySpec sks = new SecretKeySpec(keyByte, AES);
        IvParameterSpec ips = new IvParameterSpec(keyByte);
        try {
            Cipher cp = Cipher.getInstance(TRANSFORMATION);
            cp.init(Cipher.DECRYPT_MODE, sks, ips);
            msjEncryptedByte = cp.doFinal(msjEncryptedByte);
            msjDecrypted = new String(msjEncryptedByte, ENCODING);
            return msjDecrypted;
        } catch (UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            System.out.println(e.getMessage());
            return msjDecrypted;
        }
    }
    
    private static byte[] getKeyBytes(String key) {
        byte[] keyBytes = new byte[16];
        try {
            byte[] parameterKeyBytes = key.getBytes(ENCODING);
            System.arraycopy(parameterKeyBytes, 0, keyBytes, 0, Math.min(parameterKeyBytes.length, keyBytes.length));
        } catch (UnsupportedEncodingException e) {
            System.out.println("[Error][AES][getKeyBytes][0]: " + e.getMessage());
        }
        return keyBytes;
}
	

}
