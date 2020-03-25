package udp_client_server;

import java.io.*;
import java.net.*;

public class UDPclient {
	public static void main(String[] args) throws Exception {
//		String UDPServer = "127.0.0.1";
		
		BufferedReader inputFromUser = new BufferedReader(new InputStreamReader(System.in));
		
		DatagramSocket clientSocket = new DatagramSocket();
		InetAddress IPAddress = InetAddress.getLocalHost();
		
		byte[] sendData = new byte[1024];
		byte[] receiveData = new byte[1024];
		
		String sentence = inputFromUser.readLine();
		sendData = sentence.getBytes();
		
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 6969);
		clientSocket.send(sendPacket);
		
		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
		clientSocket.receive(receivePacket);
		
		String modifiedSentence = new String(receivePacket.getData());
		System.out.println("From SERVER: "+ modifiedSentence);
		
		clientSocket.close();
	}
}
