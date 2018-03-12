/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shelter;

import aux.ObjetoEnvio;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private List<JLabel> labelsUsuarios;
    private int indiceUsuarios;
    private List<Mensaje> listaMensajes;
    GridBagConstraints c = new GridBagConstraints();

    public Shelter() {
        super("selter");
        this.setVisible(true);
        initComponents();
        key = "1234567890000";
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        usuario = new Usuario(this, true);
        usuario.dispose();
        //registro el usuario en servidor
        cs = new ConexionServidor(usuario, key);
        //mensaje = new Mensaje(this,true,usuario,cs);
        mensaje = new Mensaje(usuario, cs);
        cs.setMensaje(mensaje);
        DynamicPanel.setLayout(layout);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        DynamicPanel.add(mensaje, c);
        mensaje.setVisible(false);

        labelsUsuarios = new ArrayList<JLabel>();
        listaMensajes = new ArrayList<Mensaje>(); 

        indiceUsuarios = 0;

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
        button1 = new java.awt.Button();
        labelUsuarios = new java.awt.Label();
        icono = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        reloadUsers = new javax.swing.JLabel();
        scrollPane = new javax.swing.JScrollPane();
        panelUsuarios = new javax.swing.JPanel();
        DynamicPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        button1.setLabel("button1");
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        labelUsuarios.setText("Usuarios conectados");

        icono.setIcon(new javax.swing.ImageIcon(getClass().getResource("/shelter/img/home (1).png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("PingFang SC", 0, 24)); // NOI18N
        jLabel1.setText("Shelter");

        reloadUsers.setText("Reload");
        reloadUsers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reloadUsersMouseClicked(evt);
            }
        });

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMainLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
            .addGroup(panelMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addComponent(labelUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(reloadUsers)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(reloadUsers)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(67, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout DynamicPanelLayout = new javax.swing.GroupLayout(DynamicPanel);
        DynamicPanel.setLayout(DynamicPanelLayout);
        DynamicPanelLayout.setHorizontalGroup(
            DynamicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 577, Short.MAX_VALUE)
        );
        DynamicPanelLayout.setVerticalGroup(
            DynamicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
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

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        mensaje.setVisible(true);
    }//GEN-LAST:event_button1ActionPerformed

    private void listarUsuarios(String lista) {
        //Devuelve la lista con los usuarios conectados y lo pone en labels 
        
        System.out.println("lista: " + lista);
        String[] partes = lista.split(":");
        panelUsuarios.removeAll();
        indiceUsuarios = 0;
        
        
        for (int i = 0; i < partes.length; i++) {
            if (!partes[i].equals(usuario.getUsuario())) {
                
                String receptor = partes[i];
                JLabel user = new JLabel(partes[i]);
                //se crea "la conversacion" por cada usuario conectado
                user.addMouseListener(new MouseAdapter(){ 
                    public void mouseClicked(MouseEvent e){
                        //ocultamos el mensaje actual
                        mensaje.setVisible(false);
                        Mensaje nuevo;
                        //busco el nuevo, y direcamente estoy en el mensaje
                        nuevo = buscarUsuario(receptor);
                       
                        //creamos el mensaje para que no ve vaya siempre
                        //cada vez que le damos click
                        if(nuevo == null){
                            nuevo = new Mensaje(usuario,receptor,cs);
                            listaMensajes.add(nuevo);
                        }
                        
                        //copiamos el mensaje de la conversacion abierta
                        mensaje = nuevo;
                        //cs.setMensaje(mensaje);
                        //GridBagConstraints c = new GridBagConstraints();
                        //c.gridx = 0;
                        //c.gridy = 0;
                        DynamicPanel.add(mensaje, c);
                        //acutalizamos el mensaje de la actualización;
                        cs.setMensaje(mensaje);
                        mensaje.setVisible(true);
                    }  
            }); 
            panelUsuarios.add(user);
            labelsUsuarios.add(user);
            indiceUsuarios++;
            panelUsuarios.updateUI();
            }
        }
    }
    
    private Mensaje buscarUsuario(String receptor){
        
        Mensaje result = null;
        String emisor = usuario.getUsuario();
        boolean salir = false;
        for(int i = 0; i < listaMensajes.size() && !salir;i++){
            String emisorMensaje = listaMensajes.get(i).getEmisor();
            String receptorMensaje = listaMensajes.get(i).getReceptor();
            //Lo cambiaria por ID, ya que cuandl sea grupo...
            if(emisor.equals(emisorMensaje) && receptor.equals(receptorMensaje)) {
                result = listaMensajes.get(i);
                salir = true;
            }
        }
        return result;
    }
    
    
    private void reloadUsersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reloadUsersMouseClicked
        //pedir la lista de usuarios
        String user = usuario.getUsuario();

        ObjetoEnvio objeto = new ObjetoEnvio(user, "servidor", "", "LISTAR");
        Socket socket = cs.getSocket();
        cs.escribirSocket(socket, objeto);
    }//GEN-LAST:event_reloadUsersMouseClicked

    public Mensaje buscarMensaje(ObjetoEnvio objeto){
      
        Mensaje result = null;
        //obtengo los mensajes del objeto Envio
        String yo = usuario.getUsuario();
        String receptor = objeto.getReceptor();
        String emisor = objeto.getEmisor();
        Usuario aux;
        boolean salir = false;
        //busco si estoy en el objeto envio
        
        if(emisor.equals(yo) || receptor.equals(yo)){
            //recorro la lista de mensajes para buscar el mensaje equivalente 
            for(int i = 0; i < listaMensajes.size() && !salir;i++){
                String emisorMensaje = listaMensajes.get(i).getEmisor();
                String receptorMensaje = listaMensajes.get(i).getReceptor();
                if(emisorMensaje.equals(yo) || receptorMensaje.equals(yo)){
                    System.out.println("estoy comprobando los char");
                    result = listaMensajes.get(i);
                    salir = true;
                }

            }
        }
        return result;
    }

    public void recibirMensajesServidor() {
        Socket socket = cs.getSocket();
        JTextArea textChat = mensaje.getJTextArea();

        ObjetoEnvio objeto;
        Mensaje mensajeActual;
        // Bucle infinito que recibe mensajes del servidor
        boolean conectado = true;
        while (conectado) {
            try {
                objeto = cs.leerSocket(socket);
                if (objeto.getTipo().equals("ACK")) {

                } else if (objeto.getTipo().equals("LISTAR")) {
                    if (objeto.getReceptor().equals(usuario.getUsuario())) {
                        listarUsuarios(objeto.getMensaje());
                    }
                } else { //Si es de tipo mensaje
                    String mensajeDescifrado = doDecryptedAES(objeto.getMensaje(), key);
                     System.out.println("mensajeDescifrado: " + mensajeDescifrado);
                    textChat.append(mensajeDescifrado + System.lineSeparator());
                    //ciframos
                    mensajeActual = buscarMensaje(objeto);
                    mensaje = mensajeActual;
                    //actualizamos el mensaje del cs
                    cs.setMensaje(mensaje);
                    //mostramos el mensajeActual
                    System.out.println("emisor: " + mensaje.getEmisor());
                    System.out.println("receptor: " + mensaje.getReceptor());
                    textChat = mensaje.getJTextArea();
                    mensaje.setJTextArea(textChat);
                }

            } catch (IOException ex) {
                System.out.println("Error al leer del stream de entrada: " + ex.getMessage());
                conectado = false;
            } catch (NullPointerException ex) {
                System.out.println("El socket no se creo correctamente. ");
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
    private java.awt.Button button1;
    private javax.swing.JLabel icono;
    private javax.swing.JLabel jLabel1;
    private java.awt.Label labelUsuarios;
    private javax.swing.JPanel panelMain;
    private javax.swing.JPanel panelUsuarios;
    private javax.swing.JLabel reloadUsers;
    private javax.swing.JScrollPane scrollPane;
    // End of variables declaration//GEN-END:variables
}


//VIDEO MUY CLAVE https://www.youtube.com/watch?v=AH4v_rQRyAk&t=1s
