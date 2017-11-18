package homework;

import java.util.Scanner;

public class Select5From21 extends Lottery {
	
	public Select5From21() {
		number = new int[5];
		prizeNumber = new int[5];
	}

	@Override
	public void go() {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		for (int i = 0; i < 5; ++i) {
			int nextNumber = in.nextInt();
			if (isLegalInput(nextNumber)) {
				number[i] = nextNumber;
			}
			else {
				System.out.println("WRONG");
				return;
			}
		}
		generateNumber();
		prize = prizeLevel();
		output();
	}

	@Override
	protected void generateNumber() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 5; ++i) {
			prizeNumber[i] = (int)(21 * Math.random() + 1);
		}
	}

	@Override
	protected int prizeLevel() {
		// TODO Auto-generated method stub
		int prizeNum = 0;
		boolean[] isMatched = new boolean[5];
		for (int i = 0; i < 5; ++i) {
			for (int j = 0; j < 5; ++j) {
				if (number[j] == prizeNumber[i] && isMatched[i] == false) {
					--prizeNum;
					isMatched[i] = true;
					break;
				}
			}
		}
		if (prizeNum <= 3) {
			return prizeNum;
		}
		else {
			return 0;
		}
	}

	@Override
	protected boolean isLegalInput(int input) {
		// TODO Auto-generated method stub
		if (input > 0 && input <= 21) {
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		Lottery lottery = new Select5From21();
		lottery.go();
		lottery = new SixPlusOne();
		lottery.go();
	}
}
