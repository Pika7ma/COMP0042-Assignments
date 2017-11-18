package homework;

import java.util.Scanner;
import homework.Point;

public class MyLine {
	
	private Point e1, e2;
	
	MyLine(Point p1, Point p2) {
		e1 = new Point(p1.printX(),p1.printY());
		e2 = new Point(p2.printX(),p2.printY());
	}
	
	public boolean isFirstQuadrant() {
		if (e1.printX() > 0 && e2.printY() > 0 && e1.printY() > 0 && e2.printX() > 0) {
			return true;
		}
		return false;
	}
	
	public double length() {
		return Math.sqrt(Math.pow(e1.printX() - e2.printX(), 2) + Math.pow(e1.printY() - e2.printY(), 2));
	}
	
	public double direction(Point pi, Point pj, Point pk)
	{
	    return (pi.printX() - pk.printX()) * (pi.printY() - pj.printY()) - (pi.printY() - pk.printY()) * (pi.printX() - pj.printX());
	}
	
	public boolean onSegment(Point pi, Point pj, Point pk)
	{
	    if(Math.min(pi.printX(), pj.printX()) <= pk.printX() && pk.printX() <= Math.max(pi.printX(), pj.printX()))
	    {
	        if(Math.min(pi.printY(),pj.printY()) <= pk.printY() && pk.printY() <= Math.max(pi.printY(), pj.printY()))
	        {
	            return true;
	        }
	    }
	    return false;
	}
	
	public boolean isIntersect(MyLine line) {
	    double d1 = direction(line.e1, line.e2, e1);
	    double d2 = direction(line.e1, line.e2, e2);
	    double d3 = direction(e1, e2, line.e1);
	    double d4 = direction(e1, e2, line.e2);
	    if(d1 * d2 < 0 && d3 * d4 < 0)
	        return true;
	    if(d1 == 0 && onSegment(line.e1, line.e2, e1))
	        return true;
	    if(d2 == 0 && onSegment(line.e1, line.e2, e2))
	        return true;
	    if(d3 == 0 && onSegment(e1, e2, line.e1))
	        return true;
	    if(d4 == 0 && onSegment(e1, e2, line.e2))
	        return true;
	    return false;
	}
	
	public double distance(Point p) {
		if (length() == 0) {
			return Math.sqrt(Math.pow(e1.printX() - p.printX(), 2) + Math.pow(e1.printY() - p.printY(), 2));
		}
		else {
			double a = Math.sqrt(Math.pow(e1.printX() - p.printX(), 2) + Math.pow(e1.printY() - p.printY(), 2));
			double b = Math.sqrt(Math.pow(p.printX() - e2.printX(), 2) + Math.pow(p.printY() - e2.printY(), 2));
			double c = length();
			double semiCircumference = (a + b + c) / 2;
			double area = Math.sqrt(semiCircumference * (semiCircumference - a) * (semiCircumference - b) * (semiCircumference - c));
			return area  * 2.0 / c;
		}
	}
	
	public String toString() {
		return e1.toString() + "->" + e2.toString();
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("请输入线段两端点：");
		MyLine line1 = new MyLine(new Point(scan.nextDouble(), scan.nextDouble()), new Point(scan.nextDouble(), scan.nextDouble()));
		System.out.println("1. 判断该线段是否在第一象限；");
		System.out.println("2. 计算该线段长度；");
		System.out.println("3. 输入一点求点到该线段距离；");
		System.out.println("4. 输入一条线段判断两线段是否相交。");
		System.out.print("请输入：");
		switch (scan.nextInt()) {
		case 1:
			if (line1.isFirstQuadrant() == true) {
				System.out.println("该线段在第一象限内。");
			}
			else {
				System.out.println("该线段不在第一象限内。");
			}
			break;
		case 2:
			System.out.print("该线段长度为：");
			System.out.println(line1.length());
			break;
		case 3:
			System.out.print("请输入一点：");
			Point p = new Point(scan.nextDouble(), scan.nextDouble());
			System.out.print("点到该线段距离为：");
			System.out.println(line1.distance(p));
			break;
		case 4:
			System.out.print("请输入待测线段两端点：");
			if (line1.isIntersect(new MyLine(new Point(scan.nextDouble(), scan.nextDouble()), new Point(scan.nextDouble(), scan.nextDouble())))) {
				System.out.println("两条线段相交。");
			}
			else {
				System.out.println("两条线段不相交。");
			}
		}
	}

}
