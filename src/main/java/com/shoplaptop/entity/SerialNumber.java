package com.shoplaptop.entity;

public class SerialNumber {
	private int id;
	private int id_BienThe;
	private String maBienThe;
	private String serialNumber;
	private boolean trangThai;
	
	public SerialNumber() {
		
	}

	public SerialNumber(int id, int id_BienThe, String maBienThe, String serialNumber, boolean trangThai) {
		this.id = id;
		this.id_BienThe = id_BienThe;
		this.maBienThe = maBienThe;
		this.serialNumber = serialNumber;
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
	
	
}
