package pt.rumos.model;

import java.time.LocalDate;

public class Customer {

	private Long id;
	private String nif;
	private String password;
	private String name;
	private LocalDate dob;
	private String phone;
	private String mobile;
	private String email;
	private String occupation;
	
	
	public Customer(
			String name,
			String psw,
			String nif,
			String email,
			String job,
			String phone ,
			String mobilePhone,
			LocalDate dob,
			String Occupation) {
		
		setName(name);
		setPassword(psw);
		setNif(nif);
		setEmail(email);
		setOccupation(job);
		setPhone(phone);
		setMobile(mobilePhone);
		setDob(dob);
		setOccupation(Occupation);
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNif() {
		return nif;
	}
	public void setNif(String nif) {
		this.nif = nif;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	
	
	@Override
	public String toString() {
		return "Customer [id=" + id + ", nif=" + nif + ", password=" + password + ", name=" + name + ", dob=" + dob
				+ ", phone=" + phone + ", mobile=" + mobile + ", email=" + email + ", occupation=" + occupation + "]";
	}	
	
}
