import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPClient {
	
	DatagramSocket socket;
	DatagramPacket out;
	Point p = new Point(0, 0);
	
	UDPClient() {
		try {
			socket = new DatagramSocket();
			doIt();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	void doIt() {
		
		try {
			byte[] outBuff = new byte[1024*1024];
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(p);
			outBuff = baos.toByteArray();
			out = new DatagramPacket(outBuff, outBuff.length, InetAddress.getByName("localhost"), 8848);
			socket.send(out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new UDPClient();
	}

}
