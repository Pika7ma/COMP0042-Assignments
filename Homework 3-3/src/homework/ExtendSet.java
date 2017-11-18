package homework;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class ExtendSet {
	
	public static int count(ArrayList<?> list) {
		HashSet count = new HashSet<>();
		Iterator it = list.iterator();
		while (true == it.hasNext()) {
			count.add(it.next());
		}
		return count.size();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Integer> a = new ArrayList<>();
		ArrayList<Integer> b = new ArrayList<>();
		a.add(2);
		a.add(1);
		a.add(2);
		a.add(2);
		b.add(1);
		b.add(0);
		b.add(0);
		b.add(1);
		System.out.println(ExtendSet.count(a));
	}

}
