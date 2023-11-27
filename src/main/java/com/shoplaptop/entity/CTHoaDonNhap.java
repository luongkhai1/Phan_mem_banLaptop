package com.shoplaptop.entity;

public class CTHoaDonNhap {
	private int id;
	private int id_HDN;
	private String maDN;
	private String serialNumber;
	
	public CTHoaDonNhap() {
		
	}

	public CTHoaDonNhap(int id, int id_HDN, String maDN, String serialNumber) {
		this.id = id;
		this.id_HDN = id_HDN;
		this.maDN = maDN;
		this.serialNumber = serialNumber;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_HDN() {
		return id_HDN;
	}

	public void setId_HDN(int id_HDN) {
		this.id_HDN = id_HDN;
	}

	public String getMaDN() {
		return maDN;
	}

	public void setMaDN(String maDN) {
		this.maDN = maDN;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	

}
