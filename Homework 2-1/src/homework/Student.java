package homework;

import java.util.Date;

public class Student {
	public String name;
	public Date birthday;
    public int stdcode;
    public boolean equals(Student student) {
    	if (name.equals(student.name) && birthday.equals(student.birthday) && stdcode == student.stdcode) {
    		return true;
    	}
    	return false;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		Student readyForClone = new Student();
		readyForClone.name = new String(name);
		readyForClone.birthday = (Date) birthday.clone();
		readyForClone.stdcode = stdcode;
		return super.clone();
	}

}
