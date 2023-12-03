package com.shoplaptop.entity;

import java.math.BigDecimal;

public class CTHoaDon {
	private int id;
	private int id_HoaDon ;
	private String maHD;
	private int id_Serial;
	private String SerialNumber;
	private String tenLaptop;
	private BigDecimal gia;
	
	public CTHoaDon() {
		
	}

	public CTHoaDon(int id, int id_HoaDon, String maHD, int id_Serial, String serialNumber, String tenLaptop,
			BigDecimal gia) {
		super();
		this.id = id;
		this.id_HoaDon = id_HoaDon;
		this.maHD = maHD;
		this.id_Serial = id_Serial;
		SerialNumber = serialNumber;
		this.tenLaptop = tenLaptop;
		this.gia = gia;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_HoaDon() {
		return id_HoaDon;
	}

	public void setId_HoaDon(int id_HoaDon) {
		this.id_HoaDon = id_HoaDon;
	}

	public String getMaHD() {
		return maHD;
	}

	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}

	public int getId_Serial() {
		return id_Serial;
	}

	public void setId_Serial(int id_Serial) {
		this.id_Serial = id_Serial;
	}

	public String getSerialNumber() {
		return SerialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		SerialNumber = serialNumber;
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