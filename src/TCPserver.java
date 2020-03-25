import java.io.*;
import java.net.*;

public class TCPserver {

	private static ServerSocket welcomeSocket;

	public static void main(String[] args) throws Exception {
		String clientSentence; String capitalizedSentence;
		int tcp_port = 0;
		
		if (args.length !=1) {
			System.out.println("Usage: tcpserver [tcp port]");
			System.exit(0);
		}
		try {
			tcp_port = Integer.parseInt(args[0]);
		} catch (Exception e) {
			System.out.println("Usage: tcpserver [tcp port]");
			System.exit(0);
		}
		
		welcomeSocket = new ServerSocket(tcp_port);
		while(true) {
			System.out.println("TCP Server is listening for client connect at port: " + tcp_port);
			Socket connectionSocket = welcomeSocket.accept();
//			connectionSocket.
			System.out.println("  - Got client connect from: " + connectionSocket.getInetAddress().getHostAddress() + ":"  + connectionSocket.getPort());
			BufferedReader inFromClient = new BufferedReader(
					new InputStreamReader(connectionSocket.getInputStream()));
			DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
			clientSentence = inFromClient.readLine();
			System.out.println("  - Received from client: " + clientSentence);
			System.out.println("  - Send to client: " + clientSentence.toUpperCase());
			capitalizedSentence = clientSentence.toUpperCase() + '\n';
			outToClient.writeBytes(capitalizedSentence);
			System.out.println("  - Finish working with client.\n\n");
		}
	}
}
	