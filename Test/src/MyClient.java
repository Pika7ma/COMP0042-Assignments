import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class MyClient {
	
	Scanner scanner = new Scanner(System.in);
	
	MyClient() {
		try {
			Socket s = new Socket("127.0.0.1", 8000);
			while (true) {
				Point point = new Point(scanner.nextInt(), scanner.nextInt());
				ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
				oos.writeObject(point);
				oos.flush();
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MyClient();
	}

}
