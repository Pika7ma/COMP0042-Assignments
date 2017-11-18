import java.awt.BorderLayout;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class GetMsgClient implements Runnable {
	JTextArea output;
	Socket s;
	
	GetMsgClient(JTextArea output, Socket s) {
		this.output = output;
		this.s = s;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				DataInputStream dis = new DataInputStream(s.getInputStream());
				output.append("Server:\n" + dis.readUTF() + '\n');
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}

public class QQClient {
	
	JFrame f = new JFrame("QQ 2019 Client");
	JTextField input = new JTextField();
	JTextArea output = new JTextArea();
	Socket s;
	
	void init() {
		f.add(input, BorderLayout.SOUTH);
		f.add(output, BorderLayout.CENTER);
		output.setLineWrap(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.setSize(500, 300);
		input.addActionListener(l -> {
			if (!input.getText().equals("")) {
				try {
					DataOutputStream dos = new DataOutputStream(s.getOutputStream());
					dos.writeUTF(input.getText());
					dos.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				output.append("Me:\n" + input.getText() + '\n');
				input.setText("");
			}
		});
	}
	
	QQClient() {
		init();
		try {
			s = new Socket("localhost", 8848);
			GetMsgClient getMsg = new GetMsgClient(output, s);
			Thread thread = new Thread(getMsg);
			thread.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new QQClient();
	}
}
