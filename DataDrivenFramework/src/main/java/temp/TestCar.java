package temp;

public class TestCar {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MaruticCar mc=new MaruticCar(); //child class
		mc.start();
		mc.refuel();
		mc.musicSystem();
		
		Car c=new Car();  //parent class
		c.start();
		c.refuel();

	}

}
