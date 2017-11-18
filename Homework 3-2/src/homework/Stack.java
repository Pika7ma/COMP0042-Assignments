﻿package homework;
import java.util.*;

public class Stack<E> {
	private LinkedList<E> linkedList;
	
    public Stack() {
        linkedList = new LinkedList<>();
    }
    public void push(E name) {
        //将元素加入串行前端
        linkedList.addFirst(name);
    }
    public E top() {
        //取得串行第一个元素
        return linkedList.getFirst();
    }
    public E pop() {
        //移出第一个元素
        return linkedList.removeFirst();
    }
    public boolean isEmpty() {
        //串行是否为空
        return linkedList.isEmpty();
    }
    public static void main(String[] args) {
    	Stack<String> sstack = new Stack<>();
    	sstack.push("first");
    	sstack.push("second");
    	sstack.push("third");
    	
    	while(!sstack.isEmpty()){
    		System.out.println(sstack.pop());
    	}    	
    }

}
