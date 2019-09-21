/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shelterserver;

/**
 *
 * @author rafaelsoriadiez
 */
import aux.ObjetoEnvio;
import java.util.Observable;


public class MensajesChat extends Observable{

    
    private ObjetoEnvio objeto;
        
    public ObjetoEnvio getObjeto(){
        return objeto;
    }
    
    public void setObjeto(ObjetoEnvio objeto){

        this.objeto = objeto;
        // Indica que el mensaje ha cambiado
        this.setChanged();
        // Notifica a los observadores que el mensaje ha cambiado y se lo pasa
        // (Internamente notifyObservers llama al metodo update del observador)
        this.notifyObservers(this.getObjeto());
    }
}
