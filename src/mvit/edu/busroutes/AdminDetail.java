package mvit.edu.busroutes;

public class AdminDetail {
	String admin_name;
	String designation;
	String gender;
	String phone;
	String email;

	public AdminDetail(String admin_name, String designation, String gender,
			String phone, String email) {
		this.admin_name = admin_name;
		this.designation = designation;
		this.gender = gender;
		this.phone = phone;
		this.email = email;
	}

	public AdminDetail() {
		// TODO Auto-generated constructor stub
	}

	public String getAdmin_name() {
		return admin_name;
	}

	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
