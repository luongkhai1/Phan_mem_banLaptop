package com.shoplaptop.entity;

import java.util.Date;

public class PhieuDoi {
	private  int ID;
	private  String maPhieuDoi;
	private  String MaKH ;
	private  String TenKH;
	private  int ID_HoaDon;
	private  String MaHD ;
	private  String MaNV ;
	private  String TenNV;
	private   Date NgayTao;
	private  String liDo;
	
	public PhieuDoi() {
		// TODO Auto-generated constructor stub
	}
	

	public PhieuDoi(int iD, String maPhieuDoi, String maKH, int iD_HoaDon, String maHD, String maNV, Date ngayTao,
			String liDo) {
		
		ID = iD;
		this.maPhieuDoi = maPhieuDoi;
		MaKH = maKH;
		ID_HoaDon = iD_HoaDon;
		MaHD = maHD;
		MaNV = maNV;
		NgayTao = ngayTao;
		this.liDo = liDo;
	}
	

	


	public PhieuDoi(String maPhieuDoi, String maKH, int iD_HoaDon, String maNV, Date ngayTao, String liDo) {
		this.maPhieuDoi = maPhieuDoi;
		MaKH = maKH;
		ID_HoaDon = iD_HoaDon;
		MaNV = maNV;
		NgayTao = ngayTao;
		this.liDo = liDo;
	}
	
	

	public PhieuDoi(String maPhieuDoi, String maKH, String maHD, String maNV, Date ngayTao, String liDo) {
		
		this.maPhieuDoi = maPhieuDoi;
		MaKH = maKH;
		MaHD = maHD;
		MaNV = maNV;
		NgayTao = ngayTao;
		this.liDo = liDo;
	}


	public PhieuDoi(int iD, String maPhieuDoi, String maKH, String tenKH, int iD_HoaDon, String maHD, String maNV,
			String tenNV, Date ngayTao, String liDo) {

		ID = iD;
		this.maPhieuDoi = maPhieuDoi;
		MaKH = maKH;
		TenKH = tenKH;
		ID_HoaDon = iD_HoaDon;
		MaHD = maHD;
		MaNV = maNV;
		TenNV = tenNV;
		NgayTao = ngayTao;
		this.liDo = liDo;
	}

	public String getTenKH() {
		return TenKH;
	}


	public void setTenKH(String tenKH) {
		TenKH = tenKH;
	}


	public String getTenNV() {
		return TenNV;
	}


	public void setTenNV(String tenNV) {
		TenNV = tenNV;
	}


	

	public int getID() {
		return ID;
	}


	public void setID(int iD) {
		ID = iD;
	}


	public String getMaPhieuDoi() {
		return maPhieuDoi;
	}

	public void setMaPhieuDoi(String maPhieuDoi) {
		this.maPhieuDoi = maPhieuDoi;
	}

	public String getMaKH() {
		return MaKH;
	}

	public void setMaKH(String maKH) {
		MaKH = maKH;
	}

	public int getID_HoaDon() {
		return ID_HoaDon;
	}

	public void setID_HoaDon(int iD_HoaDon) {
		ID_HoaDon = iD_HoaDon;
	}

	public String getMaHD() {
		return MaHD;
	}

	public void setMaHD(String maHD) {
		MaHD = maHD;
	}

	public String getMaNV() {
		return MaNV;
	}

	public void setMaNV(String maNV) {
		MaNV = maNV;
	}

	public Date getNgayTao() {
		return NgayTao;
	}

	public void setNgayTao(Date ngayTao) {
		NgayTao = ngayTao;
	}

	public String getLiDo() {
		return liDo;
	}

	public void setLiDo(String liDo) {
		this.liDo = liDo;
	}

	@Override
	public String toString() {
		return "PhieuDoi [maPhieuDoi=" + maPhieuDoi + ", MaKH=" + MaKH + ", ID_HoaDon=" + ID_HoaDon + ", MaHD=" + MaHD
				+ ", MaNV=" + MaNV + ", NgayTao=" + NgayTao + ", liDo=" + liDo + "]";
	}
	
}
