package homework;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Student {
	
	public Student(int identity, String name) {
		this.identity = identity;
		this.name = new String(name);
		grade = new int[10];
		average = 0;
		max = 0;
		min = 100;
		for (int i = 0; i < 10; ++i) {
			grade[i] = (int) (101 * Math.random());
			average += grade[i];
		}
		average /= 10;
		for (int i = 0; i < 10; ++i) {
			if (grade[i] > max) {
				max = grade[i];
			}
			if (grade[i] < min) {
				min = grade[i];
			}
		}
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return identity;
	}

	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return obj.hashCode() == hashCode();
	}

	public int getIdentity() {
		return identity;
	}
	
	public String getName() {
		return name;
	}
	
	public int getAverage() {
		return average;
	}
	
	public int getMax() {
		return max;
	}
	
	public int getMin() {
 		return min;
	}
	
	@Override
	public String toString() {
		return "学号：" + identity + " 姓名" + name + " ";
	}

	protected int identity;
	protected String name;
	protected int[] grade;
	protected int average;
	protected int max;
	protected int min;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Set<Student> student = new HashSet<Student>();
		Set<Student> maxGrade = new TreeSet<Student>(new DescMaxComparator());
		Set<Student> minGrade = new TreeSet<Student>(new DescMinComparator());
		Set<Student> averageGrade = new TreeSet<Student>(new DescAverageComparator());
		for (int i = 0; i < 5; ++i) {
			Scanner in = new Scanner(System.in);
			Student newStudent = new Student(in.nextInt(), in.next());
			student.add(newStudent);
			maxGrade.add(newStudent);
			minGrade.add(newStudent);
			averageGrade.add(newStudent);
		}
		System.out.println("平均分排名：");
		Iterator<Student> ite = averageGrade.iterator();
		while (ite.hasNext()) {
			Student theStudent = ite.next();
			System.out.println(theStudent.toString() + "平均分：" + theStudent.getAverage());
		}
		System.out.println("最高分排名：");
		ite = maxGrade.iterator();
		while (ite.hasNext()) {
			Student theStudent = ite.next();
			System.out.println(theStudent.toString() + "最高分：" + theStudent.getMax());
		}
		System.out.println("最低分排名：");
		ite = minGrade.iterator();
		while (ite.hasNext()) {
			Student theStudent = ite.next();
			System.out.println(theStudent.toString() + "最低分：" + theStudent.getMin());
		}
	}

}
