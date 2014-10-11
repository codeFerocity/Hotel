package Hotel;

import java.util.Random;

public class HotelTest {

	public static void main(String[] args) throws InterruptedException {
		Random rand = new Random();
		Hotel hotel = new Hotel(2);
		
		hotel.inHotel(new Guest("Иванов", 6000, 1, hotel));
		Thread.currentThread().sleep(rand.nextInt(1000));
		hotel.inHotel(new Guest("Петров", 5000, 1, hotel));
		Thread.currentThread().sleep(rand.nextInt(1000));
		hotel.inHotel(new Guest("Сидоров", 9000, 1, hotel));
		
		
	}

}
