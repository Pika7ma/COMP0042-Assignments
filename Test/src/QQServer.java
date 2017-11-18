import java.awt.BorderLayout;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class ControlThread implements Runnable {
	JTextArea output;
	ServerSocket ss;
	ArrayList<Socket> list;
	
	ControlThread(JTextArea output, ServerSocket ss, ArrayList<Socket> list) {
		this.output = output;
		this.ss = ss;
		this.list = list;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				Socket s = ss.accept();
				list.add(s);
				int number = list.size();
				GetMsg getMsg = new GetMsg(output, s, number);
				Thread thread = new Thread(getMsg);
				thread.start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}

class GetMsg implements Runnable {
	JTextArea output;
	Socket s;
	int number;
	
	GetMsg(JTextArea output, Socket s, int number) {
		this.output = output;
		this.s = s;
		this.number = number;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				DataInputStream dis = new DataInputStream(s.getInputStream());
				output.append("Client" + Integer.toString(number) + ":\n" + dis.readUTF() + '\n');
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}

public class QQServer {
	
	JFrame f = new JFrame("QQ 2019 Server");
	JTextField input = new JTextField();
	JTextArea output = new JTextArea();
	ArrayList<Socket> list = new ArrayList<>();
	ServerSocket ss;
	
	void init() {
		f.add(input, BorderLayout.SOUTH);
		f.add(output, BorderLayout.CENTER);
		output.setLineWrap(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.setSize(500, 300);
		input.addActionListener(l -> {
			if (!input.getText().equals("")) {
				for (Socket s : list) {
					try {
						DataOutputStream dos = new DataOutputStream(s.getOutputStream());
						dos.writeUTF(input.getText());
						dos.flush();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				output.append("Me:\n" + input.getText() + '\n');
				input.setText("");
			}
		});
	}
	
	QQServer() {
		init();
		try {
			ss = new ServerSocket(8848);
			ControlThread controlThread = new ControlThread(output, ss, list);
			Thread thread = new Thread(controlThread);
			thread.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new QQServer();
	}

}
