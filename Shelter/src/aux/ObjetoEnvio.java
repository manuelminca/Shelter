/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aux;

import java.io.Serializable;

 


/**
 *
 * @author minguez
 */
public class ObjetoEnvio implements Serializable{
    
    private String emisor;
    private String receptor;
    private String mensaje;
    private String tipo;
    
    
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
    
    
    
    
}
