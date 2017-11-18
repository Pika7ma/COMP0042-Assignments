package homework;

import java.net.*;
import java.io.*;

public class Server {
	
	DatagramSocket socket;
	DatagramPacket packet;
	ByteArrayInputStream bais;
	ObjectInputStream ois;
	byte[] inBuff = new byte[1024*1024];
	
	Server() {
		try {
			socket = new DatagramSocket(8848);
			packet = new DatagramPacket(inBuff, inBuff.length);
			while (true) {
				socket.receive(packet);
				bais = new ByteArrayInputStream(packet.getData());
				ois = new ObjectInputStream(bais);
				Word w = (Word)ois.readObject();
				System.out.println(w.k);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Server();
	}

}
