/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shelter;


import java.awt.event.ComponentEvent;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JTextField;


/**
 *
 * @author rafaelsoriadiez
 */
public class Mensaje extends javax.swing.JDialog {

    /**
     * Creates new form Mensaje
     */
    private Usuario usuario;
    private ConexionServidor cs;
    private Socket socket;
    
    
    
    
    //crea el socket y registra el usuario con el cliente
    public Mensaje(java.awt.Frame parent, boolean modal,
            Usuario usuario,ConexionServidor cs) {
        
         
        super(parent,"Mensaje", modal);
        initComponents();
       
        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        
        //System.out.println("recibiendo asdadsads...");
        //System.out.println("recibiendo asdadsads...");
        this.usuario = usuario;
        this.cs = cs;
        //this.socket = cs.getSocket();
        //le paso el texto
        System.out.println("recibiendo asdadsads...");
        cs.setJTextField(jTextFieldChat);
        
        buttonEnviar.addActionListener(cs);
        //this.setVisible(true);
        
        
    }
    

    public String leerSocket(Socket socket) throws IOException {
        String datos;

        InputStream aux = socket.getInputStream();
        DataInputStream flujo = new DataInputStream(aux);
        datos = new String();
        datos = flujo.readUTF();
        return datos;
    }
    
    public void iniciarMensajes(){
        this.setVisible(true);
    }
    
    public void setJTextArea(java.awt.TextArea textChat){
        this.textChat = textChat;
    }
    
    public java.awt.TextArea getJTextArea(){
        return textChat;
    }
    
    public ConexionServidor getCS(){return cs;}
    
    public void recibirMensajesServidor(){
        

        String mensaje;
        // Bucle infinito que recibe mensajes del servidor
        boolean conectado = true;
        while (conectado) {
            try {
                mensaje = leerSocket(socket);
                System.out.println("mensaje " + mensaje);
                textChat.append(mensaje + System.lineSeparator());
                 
                setVisible(true);
            } catch (IOException ex) {
                System.out.println("Error al leer del stream de entrada: " + ex.getMessage());
                conectado = false;
            } catch (NullPointerException ex) {
                System.out.println("El socket no se creo correctamente. ");
                conectado = false;
            }
        }
    }

    


        
      
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextFieldChat = new javax.swing.JTextField();
        textChat = new java.awt.TextArea();
        buttonEnviar = new java.awt.Button();
        button1 = new java.awt.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTextFieldChat.setText("jTextField1");

        buttonEnviar.setLabel("Enviar");
   

        button1.setLabel("button1");
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(textChat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldChat, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(buttonEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(textChat, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldChat, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonEnviar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        System.out.println("recibiendo mensajes...");

        String mensaje;
        // Bucle infinito que recibe mensajes del servidor
        boolean conectado = true;
        while (conectado) {
            try {
                
                mensaje = leerSocket(socket);
                System.out.println("mensaje " + mensaje);
                textChat.append(mensaje + System.lineSeparator());
                try  {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Mensaje.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (IOException ex) {
                System.out.println("Error al leer del stream de entrada: " + ex.getMessage());
                conectado = false;
            } catch (NullPointerException ex) {
                System.out.println("El socket no se creo correctamente. ");
                conectado = false;
            }
        }
    }//GEN-LAST:event_button1ActionPerformed





    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button button1;
    private java.awt.Button buttonEnviar;
    private javax.swing.JTextField jTextFieldChat;
    private java.awt.TextArea textChat;
    // End of variables declaration//GEN-END:variables
}
