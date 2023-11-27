package com.shoplaptop.entity;

import java.math.BigDecimal;
import java.util.Date;

public class HoaDonNhap {
	private int id;
	private String maDN;
	private int id_NhaCungCap;
	private String nhaCungCap; //TenNCC +  Đ/c
	private String maNV;
	private Date ngayTao;
	private BigDecimal tongTien;
	
	public HoaDonNhap() {
		
	}

	public HoaDonNhap(int id, String maDN, int id_NhaCungCap, String nhaCungCap, String maNV, Date ngayTao,
			BigDecimal tongTien) {
		this.id = id;
		this.maDN = maDN;
		this.id_NhaCungCap = id_NhaCungCap;
		this.nhaCungCap = nhaCungCap;
		this.maNV = maNV;
		this.ngayTao = ngayTao;
		this.tongTien = tongTien;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMaDN() {
		return maDN;
	}

	public void setMaDN(String maDN) {
		this.maDN = maDN;
	}

	public int getId_NhaCungCap() {
		return id_NhaCungCap;
	}

	public void setId_NhaCungCap(int id_NhaCungCap) {
		this.id_NhaCungCap = id_NhaCungCap;
	}

	public String getNhaCungCap() {
		return nhaCungCap;
	}

	public void setNhaCungCap(String nhaCungCap) {
		this.nhaCungCap = nhaCungCap;
	}

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public Date getNgayTao() {
		return ngayTao;
	}

	public void setNgayTao(Date ngayTao) {
		this.ngayTao = ngayTao;
	}

	public BigDecimal getTongTien() {
		return tongTien;
	}

	public void setTongTien(BigDecimal tongTien) {
		this.tongTien = tongTien;
	}

	
	
}
