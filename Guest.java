package Hotel;

public class Guest implements Runnable {
	//Заявка на проживание
	
	public Guest (String name,int millis,int guestQuant, Hotel hotel){
		this.name = name;
		this.millis = millis;
		this.guestQuant = guestQuant;	//пока что не нужно
		this.t = new Thread(this,name);
		System.out.println(t.getName() + " подал заявку и стал в очередь.");
		t.start();
		this.hotel = hotel;
	}
	

	@Override
	public void run() {
		while(isSettled()){
			try {
				Thread.sleep(millis);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(t.getName() + " выселился.--->"); 
			try {
				hotel.outHotel(this);			//явный вызов выселения самой заявкой
			} catch (InterruptedException e) {
				e.printStackTrace();
			}							
			//this.setSettled(false);
				

		}
	}
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getMillis() {
		return millis;
	}


	public void setMillis(int millis) {
		this.millis = millis;
	}


	public boolean isSuspendFlag() {
		return suspendFlag;
	}


	public void setSuspendFlag(boolean suspendFlag) {
		this.suspendFlag = suspendFlag;
	}


	public boolean isStopFlag() {
		return stopFlag;
	}


	public void setStopFlag(boolean stopFlag) {
		this.stopFlag = stopFlag;
	}
	
	public int getGuestQuant() {
		return guestQuant;
	}


	public void setGuestQuant(int guestQuant) {
		this.guestQuant = guestQuant;
	}
	

	public boolean isSettled() {
		return settled;
	}


	public void setSettled(boolean settled) {
		this.settled = settled;
	}
	
	protected Thread t;
	private String name;
	private int millis;
	private int guestQuant;
	private Hotel hotel;

	private boolean suspendFlag,stopFlag,settled;


}
