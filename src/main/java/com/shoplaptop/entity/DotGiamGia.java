package com.shoplaptop.entity;

import java.math.BigDecimal;
import java.util.Date;

public class DotGiamGia {
	private String ma;
	private String ten;
	private Date han;
	private BigDecimal giaGiam;
	private BigDecimal dieuKien;
	private String moTa;

	public DotGiamGia() {
		
	}

	public DotGiamGia(String ma, String ten, Date han, BigDecimal giaGiam, BigDecimal dieuKien, String moTa) {
		super();
		this.ma = ma;
		this.ten = ten;
		this.han = han;
		this.giaGiam = giaGiam;
		this.dieuKien = dieuKien;
		this.moTa = moTa;
	}

	public String getMa() {
		return ma;
	}

	public void setMa(String ma) {
		this.ma = ma;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public Date getHan() {
		return han;
	}

	public void setHan(Date han) {
		this.han = han;
	}

	public BigDecimal getGiaGiam() {
		return giaGiam;
	}

	public void setGiaGiam(BigDecimal giaGiam) {
		this.giaGiam = giaGiam;
	}

	public BigDecimal getDieuKien() {
		return dieuKien;
	}

	public void setDieuKien(BigDecimal dieuKien) {
		this.dieuKien = dieuKien;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}
	
}