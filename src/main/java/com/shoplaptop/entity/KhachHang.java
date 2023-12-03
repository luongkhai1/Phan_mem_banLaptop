package com.shoplaptop.entity;

import java.util.Date;

public class KhachHang {
	private String MaKH;
	private String HoTen;
	private String SoDienThoai;
	private Date NgaySinh;
	private boolean GioiTinh;
	private String Email;
	private String DiaChi;

	public KhachHang() {
		
	}

	public KhachHang(String maKH, String hoTen, String soDienThoai, Date ngaySinh, boolean gioiTinh, String email,
			String diaChi) {
		MaKH = maKH;
		HoTen = hoTen;
		SoDienThoai = soDienThoai;
		NgaySinh = ngaySinh;
		GioiTinh = gioiTinh;
		Email = email;
		DiaChi = diaChi;
	}

	public String getMaKH() {
		return MaKH;
	}

	public void setMaKH(String maKH) {
		MaKH = maKH;
	}

	public String getHoTen() {
		return HoTen;
	}

	public void setHoTen(String hoTen) {
		HoTen = hoTen;
	}

	public String getSoDienThoai() {
		return SoDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		SoDienThoai = soDienThoai;
	}

	public Date getNgaySinh() {
		return NgaySinh;
	}

	public void setNgaySinh(Date date) {
		NgaySinh = date;
	}

	public boolean isGioiTinh() {
		return GioiTinh;
	}

	public void setGioiTinh(boolean gioiTinh) {
		GioiTinh = gioiTinh;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getDiaChi() {
		return DiaChi;
	}

	public void setDiaChi(String diaChi) {
		DiaChi = diaChi;
	}

	@Override
	public String toString() {
		return "KhachHang [MaKH=" + MaKH + ", HoTen=" + HoTen + ", SoDienThoai=" + SoDienThoai + ", NgaySinh="
				+ NgaySinh + ", GioiTinh=" + GioiTinh + ", Email=" + Email + ", DiaChi=" + DiaChi + "]";
	}

}