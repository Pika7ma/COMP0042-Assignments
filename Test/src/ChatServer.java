import java.net.*;
import java.util.*;
import java.io.*;

public class ChatServer {
	DatagramSocket socket;
	DatagramPacket packet;
	byte[] in = new byte[1024*1024];
	
	ChatServer() {
		try {
			socket = new DatagramSocket(8848);
			packet = new DatagramPacket(in, in.length);
			doIt();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	void doIt() {
		while (true) {
			try {
				socket.receive(packet);
				ByteArrayInputStream bais = new ByteArrayInputStream(in);
				DataInputStream dis = new DataInputStream(bais);
				System.out.println(dis.readUTF());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ChatServer();
	}
}