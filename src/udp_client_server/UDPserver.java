package udp_client_server;

import java.io.*;
import java.net.*;

public class UDPserver {
	private static DatagramSocket serverSocket;

	public static void main(String[] args) throws IOException {
		serverSocket = new DatagramSocket(6969);
		
		byte[] receiveData = new byte[1024];
		byte[] sendData = new byte[1024];
		
		while(true) {
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			serverSocket.receive(receivePacket);
			
			String sentence = new String(receivePacket.getData());
			InetAddress IPAddress = receivePacket.getAddress();
			
			int port = receivePacket.getPort();
			String capitalizedSentence = sentence.toUpperCase();
			sendData = capitalizedSentence.getBytes();
			
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
			
			serverSocket.send(sendPacket);
		}
	}
}
