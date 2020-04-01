package simple_web_server;

import java.io.*;
import java.net.*;

public class WebClient {
	public static void main(String[] args) throws IOException {
		String server_ip = "";
		int server_port = 0;
		Socket client_socket = null;
		
		if (args.length !=2) {
			System.out.println("Usage: client [server address] [server port]");
			System.exit(0);
		}
		try {
			server_port = Integer.parseInt(args[1]);
			server_ip = args[0];
		} catch (Exception e) {
			System.out.println("Usage: client [server address] [server port]");
			System.exit(0);
		}
		
		try {
			System.out.println("Connecting to Server at: [" + server_ip + ":" + server_port + "]");
			client_socket = new Socket(server_ip, server_port);
		} catch (Exception e) {
			System.out.println("Cannot connect to Server.\n” + “Please check the server and run tcpclient again.");
			System.exit(0);
		}
		
		System.out.println("Server connected. Local client port: "+ client_socket.getLocalPort());
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		DataOutputStream outToServer = new DataOutputStream(client_socket.getOutputStream());
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(client_socket.getInputStream()));
	}
}
