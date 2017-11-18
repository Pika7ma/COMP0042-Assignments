package exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Clinet {
	
	Point p1 = new Point(1, 1);
	Point p2 = new Point(4, 5);
	
	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		Clinet c = new Clinet();
		Socket s = new Socket("127.0.0.1", 1010);
		ObjectOutputStream os = new ObjectOutputStream(s.getOutputStream());
		os.writeObject((Object)c.p1);
		os.writeObject((Object)c.p2);
		BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		//System.out.println(s.getInputStream());
		System.out.println(br.readLine());
		os.close();
		s.close();
	}

}
