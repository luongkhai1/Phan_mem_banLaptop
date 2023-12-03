package com.shoplaptop.entity;

import java.util.Date;

public class BaoCao_LS_HoaDon {
	private String manv;
	private String mahd;
	private Date ngaytao;
	private String LS;
	private int HoaDon;
	
	public BaoCao_LS_HoaDon() {

	}
	
	public BaoCao_LS_HoaDon(String manv, String mahd, Date ngaytao, String lS, int hoaDon) {
		this.manv = manv;
		this.mahd = mahd;
		this.ngaytao = ngaytao;
		LS = lS;
		HoaDon = hoaDon;
	}
	public String getManv() {
		return manv;
	}
	public void setManv(String manv) {
		this.manv = manv;
	}
	public String getMahd() {
		return mahd;
	}
	public void setMahd(String mahd) {
		this.mahd = mahd;
	}
	public Date getNgaytao() {
		return ngaytao;
	}
	public void setNgaytao(Date ngaytao) {
		this.ngaytao = ngaytao;
	}
	public String getLS() {
		return LS;
	}
	public void setLS(String lS) {
		LS = lS;
	}
	public int getHoaDon() {
		return HoaDon;
	}
	public void setHoaDon(int hoaDon) {
		HoaDon = hoaDon;
	}
	
	
}
