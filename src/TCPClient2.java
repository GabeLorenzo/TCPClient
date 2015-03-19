import java.io.*;
import java.net.*;

/** 
 * @author Gabriel Lorenzo 
 * Programming assignment at Dickinson College, Computer Networks
 * 
 * Objective:
 * Implement the HTTP GET method so the program only prints the HTTP header 
 * of the returned HTML page
 * 
 * Modify original TCP client that used input stream to get host name, port number, and resource that user
 * provides in compiler console.
 * 
 * Run the program from command line/terminal as so: 
 * 		java TCPClient users.dickinson.edu 80 /~siddiquf/
 * In this example, the response from the web server is an HTTP response header

 * Instead of creating an input stream from the keyboard,
 * construct a HTTP request message by utilizing the host, 
 * resource, and port entered at command line 
 * 
 * Retrieves just the HTTP response header
 * 
 */

class TCPClient2 {

	public static void main(String argv[]) throws Exception {
			
		String webServerHostName = argv[0];				// First command line argument: Host name (i.e. users.dickinson.edu)
		int webPortNumber = Integer.parseInt(argv[1]);	// Second command line argument: Port Number (i.e. 80)
		String resource = argv[2];						// Third command line argument: Resource (i.e. /~siddiquf/)
		
		Socket clientSocket = new Socket(webServerHostName, webPortNumber);	// Socket on server with given host and port number

		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

		BufferedReader inFromServer =new BufferedReader(new InputStreamReader(
			clientSocket.getInputStream()));

		while (true) {
			// HTTP Request: Implements the HEAD message in the HTTP request message
			String HTTPRequest = "HEAD " + resource + " HTTP/1.1\n"+ "Host: " + webServerHostName + "\n\n"; 
			outToServer.writeBytes(HTTPRequest + "\n");
			
			// Read from input stream until there is no more incoming data
			for (String line = inFromServer.readLine(); line != null; line = inFromServer.readLine()) {
				System.out.println("RECEIVED FROM SERVER: " + line);
			}		
		}
		//clientSocket.close();
	}
}