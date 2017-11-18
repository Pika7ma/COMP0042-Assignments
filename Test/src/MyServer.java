import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {
	
	MyServer() {
		try {
			ServerSocket ss = new ServerSocket(8000);
			Socket s = ss.accept();
			while (true) {
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				try {
					Point point = (Point) ois.readObject();
					System.out.println(Math.sqrt(point.x * point.x + point.y * point.y));
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MyServer();
	}

}
