package com.shoplaptop.entity;

public class Laptop {
	private int id;
	private String maLaptop;
	private String tenLaptop;
	private int dongLaptop;
	private String tenHang;
	private int hang;
	private String tenDong;
	private int phanLoai;
	private String tenLoai;
	private int namSanXuat;
	
	public Laptop() {
		
	}
	
	public Laptop(int id, String maLaptop, String tenLaptop, int dongLaptop, String tenHang, int hang, String tenDong,
			int phanLoai, String tenLoai, int namSanXuat) {
		this.id = id;
		this.maLaptop = maLaptop;
		this.tenLaptop = tenLaptop;
		this.dongLaptop = dongLaptop;
		this.tenHang = tenHang;
		this.hang = hang;
		this.tenDong = tenDong;
		this.phanLoai = phanLoai;
		this.tenLoai = tenLoai;
		this.namSanXuat = namSanXuat;
	}

	public Laptop(int id, String maLaptop, String tenLaptop, int dongLaptop, int phanLoai, int namSanXuat) {
		this.id = id;
		this.maLaptop = maLaptop;
		this.tenLaptop = tenLaptop;
		this.dongLaptop = dongLaptop;
		this.phanLoai = phanLoai;
		this.namSanXuat = namSanXuat;
	}

	public Laptop(String maLaptop, String tenLaptop, String tenHang, String tenDong, String tenLoai, int namSanXuat) {
		this.maLaptop = maLaptop;
		this.tenLaptop = tenLaptop;
		this.tenHang = tenHang;
		this.tenDong = tenDong;
		this.tenLoai = tenLoai;
		this.namSanXuat = namSanXuat;
	}
	
	public Laptop(String maLaptop, String tenLaptop, int dongLaptop, int phanLoai, int namSanXuat) {
		this.maLaptop = maLaptop;
		this.tenLaptop = tenLaptop;
		this.dongLaptop = dongLaptop;
		this.phanLoai = phanLoai;
		this.namSanXuat = namSanXuat;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMaLaptop() {
		return maLaptop;
	}

	public void setMaLaptop(String maLaptop) {
		this.maLaptop = maLaptop;
	}

	public String getTenLaptop() {
		return tenLaptop;
	}

	public void setTenLaptop(String tenLaptop) {
		this.tenLaptop = tenLaptop;
	}

	public int getDongLaptop() {
		return dongLaptop;
	}

	public void setDongLaptop(int dongLaptop) {
		this.dongLaptop = dongLaptop;
	}
	
	public int getHang() {
		return hang;
	}

	public void setHang(int hang) {
		this.hang = hang;
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

	public int getPhanLoai() {
		return phanLoai;
	}

	public void setPhanLoai(int phanLoai) {
		this.phanLoai = phanLoai;
	}

	public String getTenLoai() {
		return tenLoai;
	}

	public void setTenLoai(String tenLoai) {
		this.tenLoai = tenLoai;
	}

	public int getNamSanXuat() {
		return namSanXuat;
	}

	public void setNamSanXuat(int namSanXuat) {
		this.namSanXuat = namSanXuat;
	}

	@Override
	public String toString() {
		return "Laptop [id=" + id + ", maLaptop=" + maLaptop + ", tenLaptop=" + tenLaptop + ", dongLaptop=" + dongLaptop
				+ ", tenHang=" + tenHang + ", hang=" + hang + ", tenDong=" + tenDong + ", phanLoai=" + phanLoai
				+ ", tenLoai=" + tenLoai + ", namSanXuat=" + namSanXuat + "]";
	}

	
	
}
