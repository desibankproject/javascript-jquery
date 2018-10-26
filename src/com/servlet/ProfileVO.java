package com.servlet;

import java.sql.Timestamp;
import java.util.Arrays;

public class ProfileVO {
	private int sno;
	private String name;
	private String email;
	private String mobile;
	private String gender;
	private byte[] photo;
	private Timestamp doe;

	public ProfileVO() {

	}

	public ProfileVO(int sno, String name, String email, String mobile, String gender,
			byte[] photo, Timestamp doe) {
		super();
		this.sno = sno;
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.gender = gender;
		this.photo = photo;
		this.doe = doe;
	}

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public Timestamp getDoe() {
		return doe;
	}

	public void setDoe(Timestamp doe) {
		this.doe = doe;
	}

	@Override
	public String toString() {
		return "ProfileVO [sno=" + sno + ", name=" + name + ", email=" + email + ", education=" + mobile
				+ ", gender=" + gender + ", photo=" + Arrays.toString(photo) + ", doe=" + doe + "]";
	}

}
