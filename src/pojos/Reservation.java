package pojos;



public class Reservation {
	
	private Broadcast broadcast;
	private int places;
	private boolean canceled;
	
	public Reservation(Broadcast b , int places) {
		this.places = places;
		this.broadcast = b;
	}
	
	public void cancel() {
		this.canceled = true;
	}

}
