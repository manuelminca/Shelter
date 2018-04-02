/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aux;

import java.io.Serializable;
import java.math.BigInteger;

 


/**
 *
 * @author minguez
 */
public class ObjetoEnvio implements Serializable{
    
    private String emisor;
    private String receptor;
    private String mensaje;
    private String tipo;
    private BigInteger publica;
    private BigInteger privada;
    private BigInteger modulus;
    
    
    
    // id de la conversación
    //private int id;
    
    
    public ObjetoEnvio(String emisor,String receptor,String mensaje,String tipo){
        this.emisor = emisor;
        this.receptor = receptor;
        this.mensaje = mensaje;
        this.tipo = tipo;
    }
    
    public ObjetoEnvio(){

    }
   
    public String getEmisor() {
        return emisor;
    }

    public String getReceptor() {
        return receptor;
    }

    public String getMensaje() {
        
        return mensaje;  
    }
    
    public String getTipo() {
        
        return tipo;  
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setEmisor(String emisor) {
        this.emisor = emisor;
    }

    public void setReceptor(String receptor) {
        this.receptor = receptor;
    }
    
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje; 
    }
    
    public void setPublica(BigInteger pub){
        publica = pub;
    }
    public void setPrivada(BigInteger priv){
        privada = priv;
    }
    
    public BigInteger getPublica(){
        return publica;
    }
    public BigInteger getPrivada(){
        return privada;
    }
    
    public void setModulus(BigInteger mod){
        modulus = mod;
    }
    
    public BigInteger getModulus(){
        return modulus;
    }
    
    
    
    
}
