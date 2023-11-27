package com.shoplaptop.entity;

import java.util.Objects;

public class Hang {
	private int id;
	private String maHang;
	private String tenHang;
	private String hoTro;
	
	public Hang() {
		
	}
	
	public Hang(int id, String maHang, String tenHang, String hoTro) {
		this.id = id;
		this.maHang = maHang;
		this.tenHang = tenHang;
		this.hoTro = hoTro;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMaHang() {
		return maHang;
	}

	public void setMaHang(String maHang) {
		this.maHang = maHang;
	}

	public String getTenHang() {
		return tenHang;
	}

	public void setTenHang(String tenHang) {
		this.tenHang = tenHang;
	}

	public String getHoTro() {
		return hoTro;
	}

	public void setHoTro(String hoTro) {
		this.hoTro = hoTro;
	}
	
	

	@Override
	public int hashCode() {
		return Objects.hash(tenHang);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hang other = (Hang) obj;
		return Objects.equals(tenHang, other.tenHang);
	}

	@Override
	public String toString() {
		return tenHang;
	}
	
	
	
	
}
