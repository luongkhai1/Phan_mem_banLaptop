package com.shoplaptop.entity;

import java.math.BigDecimal;

public class SerialNumber {
	private int id;
	private int id_BienThe;
	private String maBienThe;
	private String serialNumber;
	private String tenLaptop;
	private BigDecimal gia;
	private boolean trangThai;
	
	public SerialNumber() {
		
	}

	public SerialNumber(int id, int id_BienThe, String maBienThe, String serialNumber, String tenLaptop, BigDecimal gia,
			boolean trangThai) {
		this.id = id;
		this.id_BienThe = id_BienThe;
		this.maBienThe = maBienThe;
		this.serialNumber = serialNumber;
		this.tenLaptop = tenLaptop;
		this.gia = gia;
		this.trangThai = trangThai;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_BienThe() {
		return id_BienThe;
	}

	public void setId_BienThe(int id_BienThe) {
		this.id_BienThe = id_BienThe;
	}

	public String getMaBienThe() {
		return maBienThe;
	}

	public void setMaBienThe(String maBienThe) {
		this.maBienThe = maBienThe;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public boolean isTrangThai() {
		return trangThai;
	}

	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}

	public String getTenLaptop() {
		return tenLaptop;
	}

	public void setTenLaptop(String tenLaptop) {
		this.tenLaptop = tenLaptop;
	}

	public BigDecimal getGia() {
		return gia;
	}

	public void setGia(BigDecimal gia) {
		this.gia = gia;
	}
	
}
