import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;



public class Cliente {
	private static Scanner sc;




	public String leerSocket(Socket socket,String datos) throws IOException{
		
		InputStream aux = socket.getInputStream();
		DataInputStream flujo = new DataInputStream( aux );
		datos = flujo.readUTF();
		
		return datos;
		
	}
	
	//pasamos la informacion al servidor
	public void escribirSocket(Socket socket,String datos) throws IOException{

		OutputStream aux = socket.getOutputStream();
		DataOutputStream flujo= new DataOutputStream( aux );
		flujo.writeUTF(datos);    
		
	}
	
 
 
    public static void main(String args[]) throws UnknownHostException, IOException {
         
		Cliente cl = new Cliente();
        Socket socket;
        sc = new Scanner(System.in);
        
        //Creamos nuestro socket
        socket = new Socket("localhost", 9999);

        String cadena = "";
        Boolean salir = false;
        
        while(!salir) {
	        System.out.print("Cliente: ");
	        cadena = sc.nextLine();

	        if(cadena.equals("salir")){
        		salir = true;
        		System.out.println("Desconectando...");
        		System.out.println("Desconectado.");
        		cl.escribirSocket(socket, cadena);
	            socket.close();
	            System.exit(0);
	        }
	       
	        //pasamos la operacion al servidor
	        cl.escribirSocket(socket, cadena);
	
	        //obetenemos el mensaje obtenido.
	        cadena = cl.leerSocket(socket,cadena);
	        
	        if(cadena.equals("salir")){
        		salir = true;
        		System.out.println("El servidor se ha desconectado.");
        		System.out.println("Cerrando conexión.");
	            socket.close();
	            System.exit(0);
	        }
	        System.out.println("Servidor: " + cadena);
	        
        } 
    }

}
