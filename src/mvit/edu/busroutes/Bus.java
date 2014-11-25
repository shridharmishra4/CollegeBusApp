package mvit.edu.busroutes;

public class Bus {

	long BusNo;
	String RouteName;
	long RouteNo;
	String VechileNo;

	public Bus(long busNo, String routeName, long routeNo, String vechileNo) {
		super();
		BusNo = busNo;
		RouteName = routeName;
		RouteNo = routeNo;
		VechileNo = vechileNo;
	}

	public Bus() {
		// TODO Auto-generated constructor stub
	}

	public long getBusNo() {
		return BusNo;
	}

	public void setBusNo(long busNo) {
		BusNo = busNo;
	}

	public String getRouteName() {
		return RouteName;
	}

	public void setRouteName(String routeName) {
		RouteName = routeName;
	}

	public long getRouteNo() {
		return RouteNo;
	}

	public void setRouteNo(long routeNo) {
		RouteNo = routeNo;
	}

	public String getVechileNo() {
		return VechileNo;
	}

	public void setVechileNo(String vechileNo) {
		VechileNo = vechileNo;
	}

}
