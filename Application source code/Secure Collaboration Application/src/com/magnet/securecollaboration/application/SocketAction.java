package com.magnet.securecollaboration.application;

//--SocketAction Class
//--SocketAction.java

/**
 * @author Kashif Dar
 * @Description SocketAction class builds input and output streams for the specified socket
 * and contains methods which are used to send or receive the data to or from the remote host
*/
//--Imports--
import java.net.*;
import java.io.*;

/**
 * @Description: This class is used to send or receive text/object data to/from remote hosts. 
 * @author Kashif
 *
 */
public class SocketAction extends Thread {
//-- data members--
public static final int PORT = 1234;
private DataInputStream inStream = null;
protected PrintStream   outStream = null;
private Socket          socket = null;

//-- constructor SocketAction--
public SocketAction() {}
/**
 * @Description: Associate the input and output streams with the server socket input and output
 * streams and port number and initialize the client socket object.
 * @param sock: The required client socket to connect with.
 * @throws IOException
 * @return void
 */
public SocketAction(Socket sock) {
  super("SocketAction");
  try {
    inStream = new DataInputStream(new
      BufferedInputStream(sock.getInputStream(), PORT));
    outStream = new PrintStream(new
      BufferedOutputStream(sock.getOutputStream(), PORT), true);
    socket = sock;
  }
  catch (IOException e) {
    System.out.println("Couldn't initialize SocketAction: " + e);
    System.exit(1);
  }
}

//-- run, relax--
public void run() { }

/**
 * @Description Sends the text data to remote host.
 * @param String: Text data to send
 * @return void
 */
public void send(String s) {
	s = s +"#";//-- Append explicit string terminating character. 
	outStream.println(s);
}

/**
 * @Description Sends any object  to remote host.
 * @param Object to send
 * @throws IOException
 * @return void
 */
public void sendObject(Object objToSend) throws IOException {
	
	ObjectOutputStream objOutputStream = new ObjectOutputStream
										(socket.getOutputStream());
	objOutputStream.writeObject(objToSend);
}

/**
* @Description Receive the text data from remote host.
* @param void
* @return String
*/
public String receive() 
{
	String recData = "", line = "";
	try
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(inStream)); 
		//--read whole content of the buffer
		line = br.readLine();
		while ( !(line.endsWith("#")))
		{
			recData = recData + line;
			line = br.readLine();
		}
	}
	catch(Exception exp) {}
	
	recData = recData + line; //-- Append the last line as well
	
	return recData;
}
/**
 * @Description Receive any object from remote host.
 * @param void
 * @throws IOException, ClassNotfoundException.
 * @return object received
 */
public Object receiveObject() throws IOException, ClassNotFoundException {
	
	ObjectInputStream objInputStream = new ObjectInputStream
										(socket.getInputStream());
	return objInputStream.readObject();
}

//-- close the connection--
/**
 * @Description This method is used to close the connection after communication.
 * @param void
 * @return void
 */
public void closeConnections() {
  try {
    socket.close();
    socket = null;
  }
  catch (IOException e) {
    System.out.println("Couldn't close socket: " + e);
  }
}

/**
 * @Description Check whether the connection is established or not
 * @param void
 * @return void 
 */
public boolean isConnected() {
  return ((inStream != null) && (outStream != null) &&
    (socket != null));
  }
}
