package homework;

import java.net.*;
import java.io.*;
import java.util.*;

class Ha {
	String k = "fuck";
}

class Word extends Ha implements Serializable {
	
	String s;
	
	void getS() {
		System.out.println(s);
	}
	
	Word() {
		Scanner scanner = new Scanner(System.in);
		s = new String(scanner.nextLine());
	}
}

public class Client {

	DatagramSocket socket;
	DatagramPacket packet;
	ByteArrayOutputStream baos;
	ObjectOutputStream oos;
	byte[] outBuff;
		
	Client() {
		try {
			while (true) {
				Word w = new Word();
				socket = new DatagramSocket();
				baos = new ByteArrayOutputStream();
				oos = new ObjectOutputStream(baos);
				oos.writeObject(w);
				outBuff = baos.toByteArray();
				packet = new DatagramPacket(outBuff, outBuff.length, InetAddress.getByName("localhost"), 8848);
				socket.send(packet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Client();
	}

}
