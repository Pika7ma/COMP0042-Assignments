import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPServer {
	
	DatagramPacket in;
	//DatagramPacket out;
	DatagramSocket socket;
	byte[] inBuff = new byte[1024*1024];
	ByteArrayInputStream bais;
	ObjectInputStream ois;
	
	UDPServer() {
		try {
			socket = new DatagramSocket(8848);
			in = new DatagramPacket(inBuff, inBuff.length);
			doIt();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void doIt() {
		while(true) {
			try {
				socket.receive(in);
				bais = new ByteArrayInputStream(in.getData());
				ois = new ObjectInputStream(bais);
				try {
					System.out.println((Point)ois.readObject());
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new UDPServer();
	}

}
