package exam;

import java.io.Serializable;

public class Point implements Comparable<Point>, Serializable {
	private double x;
	private double y;
	//private static final long serialVersionUID = 1L;
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public void editX(double x) {
		this.x = x;
	}
	public void editY(double y) {
		this.y = y;
	}
	Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	public double distance(Point p) {
		return Math.sqrt(Math.pow(x - p.getX(), 2) + Math.pow(y - p.getY(), 2));
	}
	
	@Override
	public int compareTo(Point p) {
		// TODO Auto-generated method stub
		if (distance(new Point(0, 0)) - p.distance(new Point(0, 0)) < 0) {
			return -1;
		}
		else if (distance(new Point(0, 0)) - p.distance(new Point(0, 0)) == 0) {
			return 0;
		}
		else {
			return 1;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
