package ie.cit.soft8020.Assignment2.entities;

import org.hibernate.validator.constraints.Email;

public class LoginForm {

	@Email
	private String email;
	
	private String password;
	
	
	public LoginForm(String email, String password) {
		this.email = email;
		this.password = password;
	}
	public LoginForm() {
	}
	public String getUserName() {
		return email;
	}
	public void setUserName(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
	
}
