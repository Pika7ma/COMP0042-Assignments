package homework;

public class TestLamp {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Lamp lamp = new Lamp();
		class myLight implements Light {
			public void shine() {
				System.out.println("shine in red");
			}
		}
		Light light1 = new myLight();
		lamp.on(light1);
		lamp.on(new Light() {
			public void shine() {
				System.out.println("shine in yellow");
			}
		});
	}

}
