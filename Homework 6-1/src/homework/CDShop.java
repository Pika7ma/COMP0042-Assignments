package homework;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;

//存货进程
class Stock extends Thread {
	public CDShop theShop;
	public Stock(CDShop theShop) {
		super();
		this.theShop = theShop;
	}
	@Override
	public void run() {
		synchronized (theShop.sellList) {
			while (true) {
				try {
					theShop.sellList.wait(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				theShop.stock(); 
				System.out.println(new Date() + " 进货了 o(*￣▽￣*)ブ");
				theShop.sellList.notifyAll();
			}
		}
	}
}

//待售CD数据结构
class CD4Sell {
	private int number;
	private int amount;
	private String name;
	public CD4Sell(int number, String name) {
		this.number = number;
		this.name = name;
		amount = 10;
	}
	public void update() {
		amount = 10;
	}
	public int getNumber() {
		return number;
	}
	public int getAmount() {
		return amount;
	}
	public String getString() {
		return name;
	}
	public void sell(int amount) {
		this.amount -= amount;
	}
}

//待租CD数据结构
class CD4Rent {
	private int number;
	private String name;
	private boolean isRented;
	public CD4Rent(int number, String name) {
		this.number = number;
		this.name = name;
		isRented = false;
	}
	public int getNumber() {
		return number;
	}
	public boolean hasRented() {
		return isRented;
	}
	public String getString() {
		return name;
	}
	public void rent() {
		isRented = true;
	}
	public void back() {
		isRented = false;
	}
}

//CD销售进程
class SellCD extends Thread {
	public CDShop theShop;
	public SellCD(CDShop theShop) {
		super();
		this.theShop = theShop;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			try {
				sleep((int)(Math.random() * 500));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int number = (int)(Math.random() * 5);
			int amount = (int)(Math.random() * 5) + 1;
			synchronized (theShop.sellList) {
				if (theShop.sell(number, amount)) {
					System.out.println(new Date() + " " + theShop.sellList.get(number).getString() + " 成功卖了" + amount + "本 （。＾▽＾）");
				}
				else {
					if ((int)(Math.random() * 2) == 0) {
						System.out.println(new Date() + " " + theShop.sellList.get(number).getString() + " 没货了亲下次来再买吧 （＞人＜；）");
					}
					else {
						System.out.println(new Date() + " 亲" + theShop.sellList.get(number).getString() + " 正在补货请稍等 （＞人＜；）");
						theShop.sellList.notify();
						do {
							try {
								theShop.sellList.wait();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} while (theShop.sell(number, amount));
						System.out.println(new Date() + " 通过补货" + theShop.sellList.get(number).getString() + " 成功卖了" + amount + "本 （。＾▽＾）");
					}
				}
			}
		}
	}
}


//CD出租进程
class RentCD extends Thread {
	public CDShop theShop;
	public RentCD(CDShop theShop) {
		super();
		this.theShop = theShop;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				sleep((int)(Math.random() * 300));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int number = (int)(Math.random() * 5);
			synchronized (theShop.rentList) {
				if (theShop.rent(number)) {
					System.out.println(new Date() + " " + theShop.rentList.get(number).getString() + " 租出去了 （。＾▽＾）");
					try {
						theShop.rentList.wait((int)(Math.random() * 100) + 200);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					theShop.back(number);
					System.out.println(new Date() + " " + theShop.rentList.get(number).getString() + " 还回来了 o(*￣▽￣*)ブ");
				}
				else {
					if ((int)(Math.random() * 2) == 0) {
						System.out.println(new Date() + " " + theShop.rentList.get(number).getString() + " 租出去了亲下次来再租吧 （＞人＜；）");
					}
					else {
						System.out.println(new Date() + " 亲请稍等" + theShop.rentList.get(number).getString() + " 一会就还回来了 （＞人＜；）");
						do {
							try {
								theShop.rentList.wait(100);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} while (theShop.rent(number));
						System.out.println(new Date() + " 通过等待" + theShop.rentList.get(number).getString() + " 租出去了 （。＾▽＾）");
					}
				}
			}
		}
	}
}

//总控线程
class ControlThread extends Thread
{
	CDShop cdShop;

	public ControlThread(CDShop cdshop) {
		this.cdShop = cdshop;
		this.setDaemon(true);
	}
	public void run()
	{
		Stock stock = new Stock(cdShop);
		stock.setDaemon(true);
		stock.start();
		SellCD sellCD = new SellCD(cdShop);
		sellCD.start();
		sellCD = new SellCD(cdShop);
		sellCD.start();
		RentCD rentCD = new RentCD(cdShop);
		rentCD.start();
		rentCD = new RentCD(cdShop);
		rentCD.start();
	}
	
}

public class CDShop {
	public ArrayList<CD4Rent> rentList = new ArrayList<>();
	public ArrayList<CD4Sell> sellList = new ArrayList<>();
	
	public CDShop() {
		for (int i = 0; i < 5; ++i) {
			rentList.add(new CD4Rent(i, "Harry Potter " + (i + 1)));
			sellList.add(new CD4Sell(i, "A Song of Ice and Fire " + (i + 1)));
		}
	}
	
	//内部出租方法
	public boolean rent(int number) {
		for (CD4Rent cd : rentList) {
			if (cd.getNumber() == number) {
				if (cd.hasRented()) {
					return false;
				}
				else {
					cd.rent();
					return true;
				}
			}
		}
		return false;
	}
	
	//内部归还方法
	public void back(int number) {
		for (CD4Rent cd : rentList) {
			if (cd.getNumber() == number) {
				cd.back();
			}
		}
	}
	
	//内部出售方法
	public boolean sell(int number, int amount) {
		for (CD4Sell cd : sellList) {
			if (cd.getNumber() == number) {
				if (cd.getAmount() >= amount) {
					cd.sell(amount);
					return true;
				}
				else {
					return false;
				}
			}
		}
		return false;
	}
	
	//内部进货方法
	public void stock() {
		for (CD4Sell cd : sellList) {
			cd.update();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			PrintStream ps = new PrintStream(new FileOutputStream("record.txt"));
			System.setOut(ps);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ControlThread ct = new ControlThread(new CDShop());
		ct.start();
		try {
			Thread.sleep(120000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
