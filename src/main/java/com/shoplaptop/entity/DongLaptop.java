package com.shoplaptop.entity;

import java.util.Objects;

public class DongLaptop {
	private int id;
	private String maDong;
	private int id_Hang;
	private String tenHang;
	private String tenDong;
	
	public DongLaptop() {
		
	}

	public DongLaptop(int id, String maDong, int id_Hang, String tenHang, String tenDong) {
		this.id = id;
		this.maDong = maDong;
		this.id_Hang = id_Hang;
		this.tenHang = tenHang;
		this.tenDong = tenDong;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMaDong() {
		return maDong;
	}

	public void setMaDong(String maDong) {
		this.maDong = maDong;
	}

	public int getId_Hang() {
		return id_Hang;
	}

	public void setId_Hang(int id_Hang) {
		this.id_Hang = id_Hang;
	}

	public String getTenHang() {
		return tenHang;
	}

	public void setTenHang(String tenHang) {
		this.tenHang = tenHang;
	}

	public String getTenDong() {
		return tenDong;
	}

	public void setTenDong(String tenDong) {
		this.tenDong = tenDong;
	}
	
	

	@Override
	public int hashCode() {
		return Objects.hash(tenDong, tenHang);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DongLaptop other = (DongLaptop) obj;
		return Objects.equals(tenDong, other.tenDong) && Objects.equals(tenHang, other.tenHang);
	}

	@Override
	public String toString() {
		return tenHang + " " + tenDong ;
	}
	
	
}
