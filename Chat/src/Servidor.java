import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;



public class Servidor {
	private static Scanner sc;
	
		//leo la informacion obtenida del cliente
		//los datos seria, los dos numeros pasados por el cliente.
		public String leerSocket(Socket socket) throws IOException{
			String datos;
			
			InputStream aux = socket.getInputStream();
			DataInputStream flujo = new DataInputStream( aux );
			datos = new String();
			datos = flujo.readUTF();
			
			return datos;
			
		}
		
		//le envio la informacion al cliente
		public void escribirSocket(Socket socket,String datos) throws IOException{

			OutputStream aux = socket.getOutputStream();
			DataOutputStream flujo= new DataOutputStream( aux );
			flujo.writeUTF(datos);    
			
			
		}
		
		public static void main(String args[]) {
			sc = new Scanner(System.in);
	        try {
        		Servidor sv = new Servidor();
        		ServerSocket serverSocket = new ServerSocket(9999);
	 
	            System.out.println("Servidor: Esperando una conexión...");
	            String datos = "";
	            Boolean salir = false;
	            for(;;) {
				
		            Socket socketCliente = serverSocket.accept();
		            
		            //si dice Adios salimos del chat
		            while(!salir){
			            //leemos la informacion obtenida del cliente
			            datos = sv.leerSocket(socketCliente);
			            if(datos.equals("salir")){
		            		salir = true;
		            		System.out.println("El cliente se ha desconectado.");
		            		break;
			            }else {
				            System.out.println("Cliente: " + datos);
				            
				            System.out.print("Servidor: ");
				            datos = sc.nextLine();
	
				            //si escribe salir, se cierra el servidor.
				            if(datos.equals("salir")) salir = true;
				            sv.escribirSocket(socketCliente, datos);
			            }
						
		            }
		            
		            socketCliente.close();
		            System.out.println("Cerrando conexión.");
					System.exit(0);	
						
	            }
	        } catch (IOException e) {
	            System.out.println("Error: "  + e.getMessage());
	        }
	 
	    }

}
