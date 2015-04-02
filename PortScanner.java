
import java.io.*;
import java.net.*;
//------------------------------------------------ PortScanner.java --------------------------------------------------------
//
//	Programmer’s Name:	Nicholas Bowers							Course-Section: CSCI 3400-001 Networking Fundamentals
//	Creation Date:	11/14/13		Date of Last Modification: 11/14/13 	e-mail address: BOWERSNA@goldmail.etsu.edu
//
//--------------------------------------------------------------------------------------------------------------------------
//
//	Purpose - This java application prompts the user for their machines IP Address, it then scans all ports 0 - 65535 and
//			  attempts a connection at each port with a specified amount of time to try and connect, if the connection is 
//			  successful we display the port as being "Open", if the connection times out of was unsuccessful, we display
//			  the port as being "Closed".
//--------------------------------------------------------------------------------------------------------------------------
//
//	Input - IP address from user
//
//	Output - List of all ports and if they are open or closed
//
//--------------------------------------------------------------------------------------------------------------------------
public class PortScanner 
{

	public static void main(String[] args) throws IOException 
	{
		
		InetAddress IP = null;
		String input = null;
		int socketTimeout = 1;
		//object used to get input from user
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

		//prompt the user for an IP Address
		System.out.print("Enter your IP Address: ");
		input = inFromUser.readLine();
		//get the ip by the input string
		IP = InetAddress.getByName(input);
		System.out.print("\n\n");
		System.out.println("Ports : " + "\n-----------------");
		//call the static scan ports method within the class
		scanPorts(IP, socketTimeout);
		//display a message when the scanning is complete
		System.out.print("Port scanning is complete.");
	}//end main
	
	// ---------------------------------------------------------- scanPorts ---------------------------------------------
	//
	// Purpose: This function displays a list of all open TCP ports for a specified IP address and timeout (in milliseconds)
	//
	// Parameters: InetAddress remote, int timeout 
	//
	// Returns: void
	//
	//  Called-by: main()     
    //	 Calls to: N/A
	//
	//  Programmer: Nicholas Bowers
	//	Email: BOWERSNA@goldmail.etsu.edu
	//	Date: 11/26/13
	// --------------------------------------------------------------------------------------------------------------------

	public static void scanPorts (final InetAddress remote, int timeout) throws IOException
	{	
		//for each port 1-65535, scan and try a connection with remote IP address
		//if the connection is successful, the port is open
		//if the connection times out, the port is not open
		for(int port = 1; port <= 65535; port++)
		{
			try
			{
				Socket serverSocket = new Socket();
				serverSocket.connect(new InetSocketAddress(remote, port), timeout);
				System.out.println("Port " + port + " is OPEN / LISTENING");
				serverSocket.close();
			}
			catch(SocketTimeoutException e)
			{
				//do nothing once we get here since we are only displaying the open ports
			}
		}//end for
	}//end scanPorts
}//end PortScanner
