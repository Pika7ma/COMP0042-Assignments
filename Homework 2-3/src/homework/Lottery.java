﻿package homework;

public abstract class Lottery {

	public abstract void go();
	
	protected abstract void generateNumber();
	protected abstract int prizeLevel();
	protected abstract boolean isLegalInput(int input);
	protected int[] number;
	protected int[] prizeNumber;
	protected int prize;
	protected void output() {
		if (prize > 0) {
			System.out.println("厉害了！您中奖辣！");
			System.out.println("您中了" + prize + "等奖！");
		}
		else {
			System.out.println("辣鸡没中奖");
		}
		System.out.print("您的号码是：");
		for (int i = 0; i < number.length; ++i) {
			System.out.print(number[i] + " ");
		}
		System.out.println();
		System.out.print("中奖号码是：");
		for (int i = 0; i < prizeNumber.length; ++i) {
			System.out.print(prizeNumber[i] + " ");
		}
		System.out.println();
	}
}
