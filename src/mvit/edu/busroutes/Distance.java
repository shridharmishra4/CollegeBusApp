package mvit.edu.busroutes;

public class Distance {

	String Destination;
	long Distance;

	public String getDestination() {
		return Destination;
	}

	public void setDestination(String destination) {
		Destination = destination;
	}

	public long getDistance() {
		return Distance;
	}

	public void setDistance(long distance) {
		Distance = distance;
	}

	public Distance(String destination, long distance) {
		super();
		Destination = destination;
		Distance = distance;
	}

	public Distance() {
		// TODO Auto-generated constructor stub
	}

}
