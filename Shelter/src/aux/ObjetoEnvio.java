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
    private String publicaEmisor;
    private String privada;
    private String modulus;
    private String publicaReceptor;
    
    
    
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
    
    public void setPublicaEmisor(String pub){
        publicaEmisor = pub;
    }
    public void setPrivada(String priv){
        privada = priv;
    }
    
    public void setPublicaReceptor(String pub){
        publicaReceptor = pub;
    }

    public String getPublicaEmisor(){
        return publicaEmisor;
    }
    public String getPrivada(){
        return privada;
    }
    
    public String getPublicaReceptor(){
        return publicaReceptor;
    }
  
    
    public void setModulus(String mod){
        modulus = mod;
    }
    
    public String getModulus(){
        return modulus;
    }
    
   
    
    public BigInteger toBigInteger(String foo){
        return new BigInteger(foo);
    }

    public String toString(BigInteger bar){
        return bar.toString();
    }
    
    
}
