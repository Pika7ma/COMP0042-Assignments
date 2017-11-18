import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

interface fuck {
	void f();
}

class father {

	public void f() {
		// TODO Auto-generated method stub
		
	}
	
}

abstract public class Test extends father {
	
	Frame frame = new Frame("fuck you");
	
	void init() {
		frame.setLayout(new GridLayout());
		for (int i = 1; i <= 100; ++i) {
			frame.add(new JButton(Integer.toString(i)));
		}
		frame.setVisible(true);
	}
	
	int son;
	
	int number(int i) {
		return son;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Object[] obj = new Object[10];
		for (Object o : obj) {
			System.out.println(o);
		}
	}

	@Override
	public void f() {
		// TODO Auto-generated method stub
		System.out.println("fuck");
	}

}
