package com.shoplaptop.entity;

import java.math.BigDecimal;

public class HinhThucVanChuyen {
	private int id;
	private String hinhThuc;
	private String donVi;
	private BigDecimal giaVC;
	
	
	public HinhThucVanChuyen() {
		
	}

	public HinhThucVanChuyen(int id, String hinhThuc, String donVi, BigDecimal giaVC) {
		this.id = id;
		this.hinhThuc = hinhThuc;
		this.donVi = donVi;
		this.giaVC = giaVC;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHinhThuc() {
		return hinhThuc;
	}

	public void setHinhThuc(String hinhThuc) {
		this.hinhThuc = hinhThuc;
	}

	public String getDonVi() {
		return donVi;
	}

	public void setDonVi(String donVi) {
		this.donVi = donVi;
	}

	public BigDecimal getGiaVC() {
		return giaVC;
	}

	public void setGiaVC(BigDecimal giaVC) {
		this.giaVC = giaVC;
	}

	@Override
	public String toString() {
		return hinhThuc;
	}

}