import java.io.Serializable;

public class Point implements Serializable {
	public int x = 0;
	public int y = 0;
	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public void editX(int x) {
		this.x = x;
	}
	public void editY(int y) {
		this.y = y;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "(" + x + "," + y + ")";
	}
	
}
