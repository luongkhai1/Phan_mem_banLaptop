package com.shoplaptop.entity;

public class HinhThucThanhToan {
	int id;
	String hinhThuc;

	public HinhThucThanhToan() {

	}

	public HinhThucThanhToan(int id, String hinhThuc) {

		this.id = id;
		this.hinhThuc = hinhThuc;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHinhthuc() {
		return hinhThuc;
	}

	public void setHinhthuc(String hinhThuc) {
		this.hinhThuc = hinhThuc;
	}

	@Override
	public String toString() {
		return hinhThuc;
	}

}