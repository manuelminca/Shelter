/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shelter;

import aux.ObjetoEnvio;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import javax.swing.JTextArea;
import static shelter.AES.doEncryptedAES;

/**
 *
 * @author minguez
 */
public class Mensaje extends javax.swing.JPanel {

    /**
     * Creates new form Mensaje
     */
    
    private Usuario usuario;
    private String emisor;
    private String receptor;
    private ConexionServidor cs;

    
    public Mensaje(){}
    
    //cambio realizado 2
    
    public Mensaje(Usuario usuario,ConexionServidor cs) {
        initComponents();
        
        this.usuario = usuario;
        this.cs = cs;
        System.out.println("recibiendo asdadsads...");
        cs.setJTextField(jTextField1);
        
        jButton1.addActionListener(cs);
        jTextField1.addActionListener(cs);
    } 
    
    
    public Mensaje(Usuario us,String r,ConexionServidor cs){
        initComponents();
        usuario = us;
        emisor = us.getUsuario();
        receptor = r;
        this.cs = cs;
        cs.setJTextField(jTextField1);
        
        jButton1.addActionListener(cs);
        jTextField1.addActionListener(cs);
        setVisible(false);
    }
    
    public ObjetoEnvio leerSocket(Socket socket) throws IOException, ClassNotFoundException {
        InputStream aux = socket.getInputStream();
        ObjectInputStream flujo = new ObjectInputStream(aux);
        ObjetoEnvio objeto;
        objeto = (ObjetoEnvio) flujo.readObject();
        return objeto;
    }
    
    public void iniciarMensajes(){
        this.setVisible(true);
    }
    
    public Usuario getUsuario(){return usuario;}
    public String getReceptor(){return receptor;}
    public String getEmisor(){return emisor;}
    
    public void setJTextArea(JTextArea textChat){
        this.textChat = textChat;
    }
    
    public JTextArea getJTextArea(){
        return textChat;
    }
    
    public void setReceptor(String r){receptor = r;}
    
    public ConexionServidor getCS(){return cs;}
   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        textChat = new javax.swing.JTextArea();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        textChat.setColumns(20);
        textChat.setRows(5);
        jScrollPane1.setViewportView(textChat);

        jButton1.setText("Envía");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(0, 11, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextArea textChat;
    // End of variables declaration//GEN-END:variables
}
