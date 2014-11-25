package mvit.edu.busroutes;

public class Destination {

	long Busno;
	String Destination;
	String Morning_dept;

	public long getBusno() {
		return Busno;
	}

	public void setBusno(long busno) {
		Busno = busno;
	}

	public String getDestination() {
		return Destination;
	}

	public void setDestination(String destination) {
		Destination = destination;
	}

	public String getMorning_dept() {
		return Morning_dept;
	}

	public void setMorning_dept(String morning_dept) {
		Morning_dept = morning_dept;
	}

	public Destination(long busno, String destination, String morning_dept) {
		super();
		Busno = busno;
		Destination = destination;
		Morning_dept = morning_dept;
	}

	public Destination() {
		// TODO Auto-generated constructor stub
	}

}
