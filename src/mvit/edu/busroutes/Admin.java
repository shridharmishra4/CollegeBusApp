package mvit.edu.busroutes;

import java.io.Serializable;

public class Admin implements Serializable{

	String Admin_name;
	String Admin_pass;

	public Admin(String admin_name, String admin_pass) {
		super();
		Admin_name = admin_name;
		Admin_pass = admin_pass;
	}

	public Admin() {
		// TODO Auto-generated constructor stub
	}

	public String getAdmin_name() {
		return Admin_name;
	}

	public void setAdmin_name(String admin_name) {
		Admin_name = admin_name;
	}

	public String getAdmin_pass() {
		return Admin_pass;
	}

	public void setAdmin_pass(String admin_pass) {
		Admin_pass = admin_pass;
	}

}
