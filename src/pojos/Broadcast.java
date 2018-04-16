package pojos;

import java.time.LocalTime;


public class Broadcast implements Comparable<Broadcast>{

//	private Movie movie;
//	private LocalTime projectionTime;
//	private Hall projectionHall;
//	private int places;
//
//	public Broadcast(Movie movie, LocalTime projectionTime, Hall projectionHall) {
//		this.movie = movie;
//		this.projectionTime = projectionTime;
//		this.projectionHall = projectionHall;
//		this.places = this.projectionHall.getSits();
//	}
//
//	public Movie getMovie() {
//		return movie;
//	}
//	
//	public int getPlaces() {
//		return places;
//	}
//	
//	public LocalTime getProjectionTime() {
//		return projectionTime;
//	}
//
//	public Hall getProjectionHall() {
//		return projectionHall;
//	}
//
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((projectionHall == null) ? 0 : projectionHall.hashCode());
//		result = prime * result + ((projectionTime == null) ? 0 : projectionTime.hashCode());
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Broadcast other = (Broadcast) obj;
//		if (projectionHall == null) {
//			if (other.projectionHall != null)
//				return false;
//		} else if (!projectionHall.equals(other.projectionHall))
//			return false;
//		if (projectionTime == null) {
//			if (other.projectionTime != null)
//				return false;
//		} else if (!projectionTime.equals(other.projectionTime))
//			return false;
//		return true;
//	}
//
//	@Override
//	public int compareTo(Broadcast b) {
//		if(this.projectionHall.getNumber() - b.projectionHall.getNumber() == 0) {
//			return this.projectionTime.compareTo(b.projectionTime);
//		}
//		return this.projectionHall.getNumber() - b.projectionHall.getNumber();
//	}


}
