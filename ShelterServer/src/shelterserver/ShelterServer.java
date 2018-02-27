/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shelterserver;

/**
 *
 * @author minguez
 */
public class ShelterServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Pagina para crear y el siguiente video usar la BD https://www.youtube.com/watch?v=HLx1ySEVAgQ
        
        SocketConcurrente sk_concurrente = new SocketConcurrente();
        sk_concurrente.inicio();
             
    }
    
}
