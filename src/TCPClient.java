import java.io.*;
import java.net.*;

/** 
 * @author Gabriel Lorenzo 
 * Programming assignment at Dickinson College, Computer Networks
 * 
 * Objective:
 * Implement the HTTP GET method so that your program retrieves any valid web page on a
 * web server.
 * 
 * Modify original TCP client that used input stream to get host name, port number, and resource that user
 * provides in compiler console.
 * 
 * Run the program from command line/terminal as so: 
 * 		java TCPClient users.dickinson.edu 80 /~siddiquf/
 * In this example, the response from the web server is an HTTP response header
 * followed by the HTML code of my professors web page

 * 
 * Instead of creating an input stream from the keyboard,
 * construct a HTTP request message by utilizing the host, 
 * resource, and port entered at command line 
 * 
 * Retrieves any valid web page on a web server
 * 
 */

class TCPClient {

	public static void main(String argv[]) throws Exception {
		// First command line argument: Host name (i.e. users.dickinson.edu)
		String webServerHostName = argv[0];		
		// Second command line argument: Port Number (i.e. 80)
		int webPortNumber = Integer.parseInt(argv[1]);	
		// Third command line argument: Resource (i.e. /~siddiquf/)
		String resource = argv[2];						
		
		// Socket on server with given host and port number
		Socket clientSocket = new Socket(webServerHostName, webPortNumber);	

		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

		BufferedReader inFromServer =new BufferedReader(new InputStreamReader(
			clientSocket.getInputStream()));

		while (true) {
			// HTTP Request: Implements GET method in the HTTPRequest message 
			String HTTPRequest = "GET " + resource + " HTTP/1.1\n"+ "Host: " + webServerHostName + "\n\n"; 
			outToServer.writeBytes(HTTPRequest + "\n");
			
			// Read from input stream until there is no more incoming data
			for (String line = inFromServer.readLine(); line != null; line = inFromServer.readLine()) {
				System.out.println("RECEIVED FROM SERVER: " + line); // Print each line of HTML Code
			}		
		}
		//clientSocket.close();
	}
}