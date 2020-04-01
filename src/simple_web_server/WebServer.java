package simple_web_server;

import java.io.*;
import java.net.*;
import java.util.*;

public class WebServer{
	
	public static void main(String argv[]){
		
		int clientNumber = 0;
		try (ServerSocket listenSocket = new ServerSocket(6789)){
			while(true) {
				new Processor(listenSocket.accept(),clientNumber++).start();
			}
		} catch(Exception e) {	
		}
	}
	
	public static class Processor extends Thread {
		private int clientNumber;
		private Socket connectionSocket;
		private String fileName = "";
		
		public Processor(Socket socket, int clientNumber) {
			this.connectionSocket = socket;
			this.clientNumber = clientNumber;
		}
		
		public void run() {
			try {
				BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
				DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
				String requestMessageLine = inFromClient.readLine();
				
				StringTokenizer tokenizedLine = new StringTokenizer(requestMessageLine);
				
				
				if (tokenizedLine.nextToken().equals("GET")){
					fileName = tokenizedLine.nextToken();
					if (fileName.startsWith("/") == true )
					fileName = fileName.substring(1);
					File file = new File(fileName);
					int numOfBytes = (int) file.length();
					FileInputStream inFile = new FileInputStream (fileName);
					byte[] fileInBytes = new byte[numOfBytes];
					inFile.read(fileInBytes);
	
					outToClient.writeBytes("HTTP/1.0 200 Document Follows\r\n");
					if (fileName.endsWith(".jpg"))
						outToClient.writeBytes("Content-Type: image/jpeg\r\n");
					if (fileName.endsWith(".gif"))
						outToClient.writeBytes("Content-Type: image/gif\r\n");
				
					outToClient.writeBytes("Content-Length: " + numOfBytes + "\r\n");
					
					outToClient.writeBytes("\r\n");
					outToClient.write(fileInBytes, 0, numOfBytes);
	
				} else 
					System.out.println("Bad request message");
			} catch (IOException e) {
				System.out.println("Error handling client #" + clientNumber);
			} finally {
				try {
					connectionSocket.close();
				} catch (IOException e) {
					System.out.println("Connection with client #" + clientNumber +"closed");
				}
			}
		}
	}
}
