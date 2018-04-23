/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shelter;

import aux.ObjetoEnvio;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.io.IOException;
import java.math.BigInteger;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import static shelter.AES.doDecryptedAES;

/**
 *
 * @author rafaelsoriadiez
 */
public class Shelter extends javax.swing.JFrame {

    /**
     * Creates new form Shelter
     *
     *
     */
    private Usuario usuario;

    //el mensaje Actual del usuario
    private Mensaje mensaje;
    private ConexionServidor cs;
    private String key;
    GridBagLayout layout = new GridBagLayout();
    private List<Mensaje> listaMensajes;
    RSA rsa;
    private int contador;
    private String receptor;

    public Shelter() {
        super("Shelter");
        this.setVisible(false);
        initComponents();
        usuario = new Usuario(this, true, false);
        usuario.Visible();
        //registro el usuario en servidor

        //miro a ver si hay login para invocar un constructor o otro
        reiniciar();
        labelUsuario.setText("Usuario: " + usuario.getUsuario());

        //mensaje = new Mensaje(this,true,usuario,cs);
        //SALIMOS
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                ObjetoEnvio objeto = new ObjetoEnvio(usuario.getUsuario(), "", "", "SALIR");
                cs.escribirSocket(objeto);

            }
        });

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelMain = new javax.swing.JPanel();
        labelUsuarios = new java.awt.Label();
        icono = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        reloadUsers = new javax.swing.JLabel();
        scrollPane = new javax.swing.JScrollPane();
        panelUsuarios = new javax.swing.JPanel();
        labelUsuario = new javax.swing.JLabel();
        DynamicPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelMain.setBackground(new java.awt.Color(236, 229, 221));

        labelUsuarios.setText("Usuarios conectados");

        icono.setBackground(new java.awt.Color(255, 127, 80));
        icono.setIcon(new javax.swing.ImageIcon(getClass().getResource("/shelter/img/home (1).png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("PingFang SC", 0, 24)); // NOI18N
        jLabel1.setText("Shelter");

        reloadUsers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/shelter/img/rotate.png"))); // NOI18N
        reloadUsers.setText("Actualizar");
        reloadUsers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reloadUsersMouseClicked(evt);
            }
        });

        panelUsuarios.setBackground(new java.awt.Color(255, 250, 250));
        panelUsuarios.setLayout(new java.awt.GridLayout(0, 1));
        scrollPane.setViewportView(panelUsuarios);

        javax.swing.GroupLayout panelMainLayout = new javax.swing.GroupLayout(panelMain);
        panelMain.setLayout(panelMainLayout);
        panelMainLayout.setHorizontalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMainLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(icono))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(panelMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addComponent(labelUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(reloadUsers)
                            .addComponent(labelUsuario)
                            .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        panelMainLayout.setVerticalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(icono)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(labelUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(reloadUsers)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(75, Short.MAX_VALUE))
        );

        DynamicPanel.setBackground(new java.awt.Color(236, 229, 221));

        jLabel2.setFont(new java.awt.Font("Malayalam Sangam MN", 0, 36)); // NOI18N
        jLabel2.setText("Bienvenido a Shelter");

        jLabel3.setText("Envía mensajes a tus amigos y familiares de forma segura.");

        jLabel4.setText("Para comenzar haz click en \"Actualizar\" para ver la lista de");

        jLabel5.setText("usuarios conectados con los que podrás hablar.");

        javax.swing.GroupLayout DynamicPanelLayout = new javax.swing.GroupLayout(DynamicPanel);
        DynamicPanel.setLayout(DynamicPanelLayout);
        DynamicPanelLayout.setHorizontalGroup(
            DynamicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DynamicPanelLayout.createSequentialGroup()
                .addContainerGap(52, Short.MAX_VALUE)
                .addGroup(DynamicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DynamicPanelLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46))
        );
        DynamicPanelLayout.setVerticalGroup(
            DynamicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DynamicPanelLayout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel4)
                .addGap(6, 6, 6)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelMain, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DynamicPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(DynamicPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(panelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void iniciarConversacion(String receptor, String publicaReceptor, String modulusReceptor) {

        mensaje.setVisible(true);
        mensaje.getJTextArea().setText("");
        mensaje.setReceptor(receptor);

        BigInteger publica = rsa.toBigInteger(publicaReceptor);
        BigInteger modulus = rsa.toBigInteger(modulusReceptor);
        
        key = PasswordGenerator.getPassword(
		PasswordGenerator.MINUSCULAS+
		PasswordGenerator.MAYUSCULAS+
		PasswordGenerator.ESPECIALES,10);
        
        BigInteger claveSesionReceptor = rsa.encrypt(key, publica, modulus);
        BigInteger claveSesionEmisor = rsa.encrypt(key, rsa.getPublicKey(), rsa.getModulus());

        String cadena = rsa.toString(claveSesionEmisor) + ":" + rsa.toString(claveSesionReceptor);

        ObjetoEnvio obj = new ObjetoEnvio(usuario.getUsuario(), receptor, cadena, "CHAT");
        cs.escribirSocket(obj);
    }

    public void devolverClave(String receptor) {
        this.receptor = receptor;
        if(contador == 0){
            DynamicPanel.setLayout(layout);
            DynamicPanel.setSize(400, 400);
            GridBagConstraints c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = 0;
            //mensaje.setExtendedState();

            DynamicPanel.add(mensaje, c);
            mensaje.setVisible(false);
            
            jLabel2.setVisible(false);
            jLabel3.setVisible(false);
            jLabel4.setVisible(false);
            jLabel5.setVisible(false);
        }
        contador++;
       
        ObjetoEnvio obj = new ObjetoEnvio(usuario.getUsuario(), receptor, "", "CLAVE");
        cs.escribirSocket(obj);
    }

    public void print(String mensaje) {
        System.out.println(mensaje);
    }

    private void listarUsuarios(String lista) {
        //Devuelve la lista con los usuarios conectados y lo pone en labels 

        System.out.println("lista: " + lista);
        String[] partes = lista.split(":");
        panelUsuarios.removeAll();
        

        for (int i = 0; i < partes.length; i++) {
            if (!partes[i].equals(usuario.getUsuario())) {

                String receptor = partes[i];
                JLabel user = new JLabel(partes[i]);
                //se crea "la conversacion" por cada usuario conectado
                user.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                       print("HOLAHOLAHOLAHOLAHOLAHOLAHOLAKS FGKLJHSG KJSHFG KSJFHG KSJFHG KLSJFGLKDSFJGLSKDFJLFJG KLJGHDS LKFJGHSDFKLJH");
                        print("el receptor es: " + receptor);
                        mensaje.setReceptor(receptor);
                        print(mensaje.getReceptor());
                        cs.setMensaje(mensaje);
                        devolverClave(receptor);
                    }
                });

                panelUsuarios.add(user);
                panelUsuarios.updateUI();
                validate();
            }
        }
    }

    private void reloadUsersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reloadUsersMouseClicked
        //pedir la lista de usuarios
        String user = usuario.getUsuario();

        ObjetoEnvio objeto = new ObjetoEnvio(user, "servidor", "", "LISTAR");
        cs.escribirSocket(objeto);
    }//GEN-LAST:event_reloadUsersMouseClicked

    public void reiniciar() {
        Login login = usuario.getLogin();
        try {
            if (login.comprobar()) {
                //registro
                Usuario actual = new Usuario(login);
                usuario = actual;
                cs = new ConexionServidor(actual, key);
            }
        } catch (NullPointerException e) {
            
            cs = new ConexionServidor(usuario);
        }
        mensaje = new Mensaje(usuario, cs);
        cs.setMensaje(mensaje);
        DynamicPanel.setLayout(null);
        rsa = new RSA(1024);
        
    }

    public String prepararChat(String mensaje, String key) {
        String[] partes = mensaje.split("\n");
        print(mensaje);
        String resultado = "";
        for (int i = 0; i < partes.length; i++) {
            print(partes[i]);
            resultado = resultado + doDecryptedAES(partes[i], key) + "\n";
        }
        return resultado;
    }

    //Para login
    public void ACK(ObjetoEnvio objeto) {
        BigInteger publica;
        BigInteger privada;
        BigInteger modulus;

        //le ponemos los datos del objeto al usuario
        usuario.setName(objeto.getReceptor());
        usuario.setPassword(objeto.getPassword());
        publica = rsa.toBigInteger(objeto.getPublicaEmisor());
        rsa.setPublicKey(publica);

        String privadaRSA = doDecryptedAES(objeto.getPrivada(), usuario.getPassword());
        privada = rsa.toBigInteger(privadaRSA);
        rsa.setPrivateKey(privada);
        modulus = rsa.toBigInteger(objeto.getModulus());
        rsa.setModulus(modulus);
        cs.setMensaje(mensaje);
        this.setVisible(true);

    }

    public void REGISTRO(ObjetoEnvio objeto) {

        if (objeto.getReceptor().equals(usuario.getUsuario())) {
            String mens = objeto.getMensaje();
            usuario = new Usuario(this, true, true);
            if (mens.equals("USUARIO REGISTRADO")) {
                usuario.setMensaje("Error, usuario registrado, intentaolo con otro.");
            } else {
                usuario.setMensaje("Usuario registrado correctamente.");
            }
            usuario.Visible();
            reiniciar();

        }

    }

    public void LISTAR(ObjetoEnvio objeto) {
        if (objeto.getReceptor().equals(usuario.getUsuario())) {
            listarUsuarios(objeto.getMensaje());
        }

    }

    public void CLAVE(ObjetoEnvio objeto) {
        
        String emisor = objeto.getEmisor();
        String usuarioActual = usuario.getUsuario();
        if (emisor.equals(usuarioActual)) {
            String receptor = objeto.getReceptor();
            String publicaReceptor = objeto.getPublicaReceptor();
            print("CLAVE PUBLICA: " + publicaReceptor);
            String modulusReceptor = objeto.getModulusReceptor();
            print("CLAVE modulus: " + modulusReceptor);
            iniciarConversacion(receptor, publicaReceptor, modulusReceptor);
        }

    }

    public void CHAT(ObjetoEnvio objeto, JTextArea textChat) {
        System.out.println("LLEGA MENSAJE DE TIPO CHAT");
        
        print(objeto.getReceptor() + " usuario getusuario " + usuario.getUsuario());
        
        if (objeto.getReceptor().equals(usuario.getUsuario())) {
            print("Hemos entrado dentro del if");
            String[] partes = objeto.getMensaje().split(":");

            print(partes[0]);
            print(partes[1]);
            
            print("hemos acabado de hacer las partes");

            key = rsa.decrypt(rsa.toBigInteger(partes[0]));
            cs.setKey(key);

            print("La clave AES de la conversacion es: " + key);

            String texto = prepararChat(partes[1], key);
            JTextArea chat = new JTextArea();
            textChat.append(texto);
            mensaje.setJTextArea(chat);
            textChat.setText(texto);

        }

    }

    public void DEFAULT(ObjetoEnvio objeto, JTextArea textChat) {
        
        print(objeto.getEmisor());
        print(objeto.getReceptor());
        print(mensaje.getReceptor());
        print(usuario.getUsuario());
        
        
        
        if ((objeto.getEmisor().equals(usuario.getUsuario()) && receptor.equals(objeto.getReceptor()))
                || (objeto.getReceptor().equals(usuario.getUsuario()) && receptor.equals(objeto.getEmisor()))) {
            print("mensaje cifrado:" + objeto.getMensaje());
            print("key:" + key);
            String mensajeDescifrado = doDecryptedAES(objeto.getMensaje(), key);
            System.out.println("mensajeDescifrado: " + mensajeDescifrado);
            textChat.append(mensajeDescifrado + System.lineSeparator());
            mensaje.setJTextArea(textChat);
        }

    }

    public void NOACK(ObjetoEnvio objeto) {
        String m = "Error, el usuario y/o contraseña es incorrecto.Intentalo de nuevo.";

        boolean ok = usuario.getOK();
        if (objeto.getReceptor().equals(usuario.getUsuario())) {
            usuario = new Usuario(this, true, true);
            usuario.setMensaje(m);
            usuario.setOK(false);
            usuario.Visible();
            reiniciar();
        }
    }

    public void recibirMensajesServidor() {
        Socket socket = cs.getSocket();
        JTextArea textChat = mensaje.getJTextArea();

        ObjetoEnvio objeto;
        // Bucle infinito que recibe mensajes del servidor
        boolean conectado = true;
        while (conectado) {
            try {
                objeto = cs.leerSocket(socket);

                switch (objeto.getTipo()) {
                    //login
                    case "ACK":
                        if (objeto.getReceptor().equals(usuario.getUsuario())) {
                            ACK(objeto);
                        }
                        break;
                    case "REGISTRO":
                        REGISTRO(objeto);
                        break;
                    case "!ACK":
                        NOACK(objeto);
                        break;
                    case "LISTAR":
                        LISTAR(objeto);
                        break;
                    case "CLAVE":
                        CLAVE(objeto);
                        break;
                    case "CHAT":
                        CHAT(objeto, textChat);
                        break;
                    default:
                        DEFAULT(objeto, textChat);
                        break;
                }

            } catch (IOException ex) {
                System.out.println("Error al leer del stream de entrada: " + ex.getMessage());
                conectado = false;
            } catch (NullPointerException ex) {
                ex.printStackTrace();
                conectado = false;
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        Shelter shelter = new Shelter();
        shelter.recibirMensajesServidor();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel DynamicPanel;
    private javax.swing.JLabel icono;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel labelUsuario;
    private java.awt.Label labelUsuarios;
    private javax.swing.JPanel panelMain;
    private javax.swing.JPanel panelUsuarios;
    private javax.swing.JLabel reloadUsers;
    private javax.swing.JScrollPane scrollPane;
    // End of variables declaration//GEN-END:variables

}


//VIDEO MUY CLAVE https://www.youtube.com/watch?v=AH4v_rQRyAk&t=1s
