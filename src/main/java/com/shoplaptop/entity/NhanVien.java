package com.shoplaptop.entity;

import java.util.Date;

public class NhanVien {
	private String maNV;
	private String hoTen;
	private String soDienThoai;
	private Date ngaySinh;
	private boolean gioiTinh;
	private String email;
	private String hinh;
	private String diaChi;
	private boolean vaiTro;

	
	public NhanVien() {
		
	}


	public NhanVien(String maNV, String hoTen, String soDienThoai, Date ngaySinh, boolean gioiTinh, String email,
			String hinh, String diaChi, boolean vaiTro) {
		this.maNV = maNV;
		this.hoTen = hoTen;
		this.soDienThoai = soDienThoai;
		this.ngaySinh = ngaySinh;
		this.gioiTinh = gioiTinh;
		this.email = email;
		this.hinh = hinh;
		this.diaChi = diaChi;
		this.vaiTro = vaiTro;
	}


	public String getMaNV() {
		return maNV;
	}


	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}


	public String getHoTen() {
		return hoTen;
	}


	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}


	public String getSoDienThoai() {
		return soDienThoai;
	}


	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}


	public Date getNgaySinh() {
		return ngaySinh;
	}


	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}


	public boolean isGioiTinh() {
		return gioiTinh;
	}


	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getHinh() {
		return hinh;
	}


	public void setHinh(String hinh) {
		this.hinh = hinh;
	}


	public String getDiaChi() {
		return diaChi;
	}


	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}


	public boolean isVaiTro() {
		return vaiTro;
	}


	public void setVaiTro(boolean vaiTro) {
		this.vaiTro = vaiTro;
	}
	

}
