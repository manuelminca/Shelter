/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shelter;

import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 *
 * @author rafaelsoriadiez
 *  Jdialog es una ventana secundaria, que es como si fuera el hijo de
    la principal, en este caso Jframe
 */
public class Usuario extends javax.swing.JDialog {

    private Login login;
    private String usuario;
    private String ip;
    private int puerto;
    private String password;
    private boolean ok;
    
    public Usuario(java.awt.Frame parent, boolean modal,boolean registro) {

        super(parent,"Usuario", modal);
        initComponents();
        ok = true;
        if(registro) setMensaje("Usuario registrado con exicto.");
        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
    }
    
    
    
    public Usuario(Login login){
        usuario = login.getUsuario();
        this.password = login.getPassword();
        this.ip = login.getIP();
        this.puerto = login.getPuerto();
    }
    
    public void Visible(){
        this.setVisible(true);
    }
    
    public Login getLogin(){
        return login;
    }
    
    //metodos gets
    public String getUsuario(){return usuario;}
    public String getIP(){return ip;}
    public int getPuerto(){return puerto;}
    public String getPassword(){return password;}
    public boolean getOK(){return ok;}
    public void setPassword(String a){password = a;}
    public void setOK(boolean a){ok = a;}

    
    public void setUsuario(){
        usuario = textUsuario.getText();
        ip = textIP.getText();
        puerto = Integer.parseInt(textPuerto.getText());
        password = textClave.getText();
    }
    
    public void setMensaje(String m){
        labelMensaje.setText(m);
    }

    public void setVisibleALL(boolean visible){
        
    botonEnviar.setVisible(visible);
    button1.setVisible(visible);
    buttonRegistro.setVisible(visible);
    jButton1.setVisible(visible);
    labelClave.setVisible(visible);
    labelIP.setVisible(visible);
    labelMensaje.setVisible(visible);
    labelPuerto.setVisible(visible);
    labelUsuario.setVisible(visible);
    textClave.setVisible(visible);
    textIP.setVisible(visible);
    textPuerto.setVisible(visible);
    textUsuario.setVisible(visible);
        
    }
 
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        button1 = new java.awt.Button();
        jButton1 = new javax.swing.JButton();
        labelUsuario = new java.awt.Label();
        labelIP = new java.awt.Label();
        labelPuerto = new java.awt.Label();
        textUsuario = new java.awt.TextField();
        textIP = new java.awt.TextField();
        textPuerto = new java.awt.TextField();
        labelClave = new java.awt.Label();
        labelMensaje = new javax.swing.JLabel();
        botonEnviar = new javax.swing.JButton();
        buttonRegistro = new javax.swing.JButton();
        textClave = new javax.swing.JPasswordField();

        button1.setLabel("button1");

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        labelUsuario.setText("Usuario");

        labelIP.setText("IP");

        labelPuerto.setText("Puerto");

        textIP.setText("localhost");

        textPuerto.setText("4000");

        labelClave.setText("Contraseña");

        labelMensaje.setText("jLabel1");

        botonEnviar.setLabel("Enviar");
        botonEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEnviarActionPerformed(evt);
            }
        });

        buttonRegistro.setText("Registrarse");
        buttonRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRegistroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(labelMensaje))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(79, 79, 79)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelPuerto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelClave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(29, 29, 29))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(buttonRegistro)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(botonEnviar)
                            .addComponent(textUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textIP, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                            .addComponent(textPuerto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textClave, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap(134, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(textUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelClave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textClave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelPuerto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textPuerto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonEnviar)
                    .addComponent(buttonRegistro))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 106, Short.MAX_VALUE)
                .addComponent(labelMensaje)
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEnviarActionPerformed
        usuario = textUsuario.getText();
        ip = textIP.getText();
        puerto = Integer.parseInt(textPuerto.getText());
        password = org.apache.commons.codec.digest.DigestUtils.sha256Hex(textClave.getText() +"salt");
                
        this.setVisible(false);
        
        
    }//GEN-LAST:event_botonEnviarActionPerformed

    private void buttonRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRegistroActionPerformed
        login = new Login(this);
        
        login.setSize(400,300); //tamaño del jpanel
        login.setLocation(5,5); //posicion dentro del panel principal
        
        this.setVisibleALL(false);
        this.add(login,BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }//GEN-LAST:event_buttonRegistroActionPerformed

    /**
     * @param args the command line arguments
     */
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonEnviar;
    private java.awt.Button button1;
    private javax.swing.JButton buttonRegistro;
    private javax.swing.JButton jButton1;
    private java.awt.Label labelClave;
    private java.awt.Label labelIP;
    private javax.swing.JLabel labelMensaje;
    private java.awt.Label labelPuerto;
    private java.awt.Label labelUsuario;
    private javax.swing.JPasswordField textClave;
    private java.awt.TextField textIP;
    private java.awt.TextField textPuerto;
    private java.awt.TextField textUsuario;
    // End of variables declaration//GEN-END:variables
}
