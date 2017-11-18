import java.net.*;
import java.util.*;
import java.io.*;

public class ChatClient {
	DatagramSocket socket;
	DatagramPacket packet;
	Scanner s = new Scanner(System.in);
	ChatClient() {
		try {
			socket = new DatagramSocket();
			doIt();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	void doIt() {
		while (true) {
			try {
				byte[] out = new byte[1024*1024];
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				DataOutputStream dos = new DataOutputStream(baos);
				dos.writeUTF(s.nextLine());
				out = baos.toByteArray();
				packet = new DatagramPacket(out, out.length, InetAddress.getByName("localhost"), 8848);
				socket.send(packet);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//new ChatClient();
		System.out.println("".compareTo(""));
	}
}