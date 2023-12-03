package com.shoplaptop.entity;

public class BaoCao_LS_PhieuDoi {
	private String Manv;
	private String maphieudoi;
	private String LS;
	private int PhieuDoi;
	
	public BaoCao_LS_PhieuDoi() {
		
	}
	public BaoCao_LS_PhieuDoi(String manv, String maphieudoi, String lS, int phieuDoi) {	
		Manv = manv;
		this.maphieudoi = maphieudoi;
		LS = lS;
		PhieuDoi = phieuDoi;
	}
	public String getManv() {
		return Manv;
	}
	public void setManv(String manv) {
		Manv = manv;
	}
	public String getMaphieudoi() {
		return maphieudoi;
	}
	public void setMaphieudoi(String maphieudoi) {
		this.maphieudoi = maphieudoi;
	}
	public String getLS() {
		return LS;
	}
	public void setLS(String lS) {
		LS = lS;
	}
	public int getPhieuDoi() {
		return PhieuDoi;
	}
	public void setPhieuDoi(int phieuDoi) {
		PhieuDoi = phieuDoi;
	}

	
}
