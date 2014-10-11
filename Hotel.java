package Hotel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Hotel {
	
	private int capacity;
	private List guestList;
	private int size;
	private BlockingQueue waitQueue;

	
	
	public Hotel(int capacity) {
		this.capacity = capacity;
		guestList = new ArrayList<Guest>(capacity);
		waitQueue= new ArrayBlockingQueue(100, true); 
		this.size = 0;
	}
	
	public void inQueue(Guest g){
		try{
			waitQueue.add(g);
		}catch(IllegalStateException e){
			System.out.println("Извините, очередь заявок заполнена!");
		}
	}
	
	
	public synchronized boolean inHotel(Guest g) throws InterruptedException{
		inQueue(g);
		if (size<capacity) {
			//g.setSettled(true);
			size++;
			Guest tmp;
			tmp = (Guest) waitQueue.remove();						//достает первого на поселение
			System.out.println(tmp.getName() + " вышел из очереди.");
			guestList.add(tmp);
			((Guest) guestList.get(size-1)).setSettled(true);		//"включает" проживание - sleep(msec) - время проживания
			System.out.println("--->Заселён " + ((Guest) guestList.get(size-1)).getName() + "!");
				
			return true;
		}
		else{
			System.out.println( g.getName() + ", извините, места заняты.");
			//synchronized (g){
			//	waitQueue.wait();	//wait для всех потоков внутри очереди? нужно или нет вообще 
			//}
			return false;
		}
		
	}
	
	public synchronized void outHotel(Guest g) throws InterruptedException{
		guestList.remove(size-1);
		size--;
		g.setSettled(false);  //err
		//notifyAll();

		Guest tmp;
		tmp = (Guest) waitQueue.peek();
		//g.t.join();
		this.inHotel(tmp);
		
	}
	
	public synchronized void findGuest(){
		
	}
	
	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	public int getCapacity() {
		return capacity;
	}
	
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	public List getGuestList() {
		return guestList;
	}
	
	public void setGuestList(List guestList) {
		this.guestList = guestList;
	}
	
	
}
