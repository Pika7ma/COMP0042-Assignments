package homework;

import java.util.Scanner;

public class SixPlusOne extends Lottery {

	public SixPlusOne() {
		number = new int[7];
		prizeNumber = new int[7];
	}
	
	@Override
	public void go() {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		for (int i = 0; i < 7; ++i) {
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
		for (int i = 0; i < 7; ++i) {
			prizeNumber[i] = (int)(10 * Math.random());
		}
	}

	@Override
	protected int prizeLevel() {
		// TODO Auto-generated method stub
		int prizeNum = 0;
		int prizeNumNow = 0;
		boolean isIn = false;
		for (int i = 0; i < 7; ++i) {
			if (prizeNumber[i] == number[i]) {
				if (isIn == true) {
					++prizeNumNow;
				}
				else {
					isIn = true;
					prizeNumNow = 1;
				}
			}
			else {
				if (isIn == true) {
					isIn = false;
					if (prizeNumNow > prizeNum) {
						prizeNum = prizeNumNow;
					}
				}
			}
		}
		if (isIn == true) {
			++prizeNumNow;
			if (prizeNumNow > prizeNum) {
				prizeNum = prizeNumNow;
			}
		}
		if (prizeNum <= 1) {
			return 0;
		}
		else {
			return 7 - prizeNum;
		}
	}

	@Override
	protected boolean isLegalInput(int input) {
		// TODO Auto-generated method stub
		if (input >= 0 && input <= 9) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
