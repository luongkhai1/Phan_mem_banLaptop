package com.shoplaptop.entity;

import java.math.BigDecimal;

public class CTPhieuDoi {
	private int ID;
	private int ID_PhieuDoi ;
	private String MaPhieuDoi;
	private int ID_HoaDon;
	private String MaHoaDon;
	private int ID_Serial_Old;
	private String SerialNumber_Old;
	private int ID_Serial_New;
	private String SerialNumber_New;
	private BigDecimal GiaCu;
	private BigDecimal GiaMoi;
	private String TenLapCu;
	private String TenLapMoi;
	private boolean TrangThai;
	private String LiDo;
	
	public CTPhieuDoi() {
		// TODO Auto-generated constructor stub
	}

	public CTPhieuDoi(int iD, int iD_PhieuDoi, String maPhieuDoi, int iD_HoaDon, String maHoaDon, int iD_Serial_Old,
			String serialNumber_Old, int iD_Serial_New, String serialNumber_New, BigDecimal giaCu, BigDecimal giaMoi,
			String tenLapCu, String tenLapMoi, boolean trangThai, String liDo) {
		
		ID = iD;
		ID_PhieuDoi = iD_PhieuDoi;
		MaPhieuDoi = maPhieuDoi;
		ID_HoaDon = iD_HoaDon;
		MaHoaDon = maHoaDon;
		ID_Serial_Old = iD_Serial_Old;
		SerialNumber_Old = serialNumber_Old;
		ID_Serial_New = iD_Serial_New;
		SerialNumber_New = serialNumber_New;
		GiaCu = giaCu;
		GiaMoi = giaMoi;
		TenLapCu = tenLapCu;
		TenLapMoi = tenLapMoi;
		TrangThai = trangThai;
		LiDo = liDo;
	}
	
	public CTPhieuDoi(String maHoaDon, String serialNumber_Old, String serialNumber_New, BigDecimal giaCu,
			BigDecimal giaMoi, String tenLapCu, String tenLapMoi, boolean trangThai, String liDo) {
		MaHoaDon = maHoaDon;
		SerialNumber_Old = serialNumber_Old;
		SerialNumber_New = serialNumber_New;
		GiaCu = giaCu;
		GiaMoi = giaMoi;
		TenLapCu = tenLapCu;
		TenLapMoi = tenLapMoi;
		TrangThai = trangThai;
		LiDo = liDo;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getID_PhieuDoi() {
		return ID_PhieuDoi;
	}

	public void setID_PhieuDoi(int iD_PhieuDoi) {
		ID_PhieuDoi = iD_PhieuDoi;
	}

	public String getMaPhieuDoi() {
		return MaPhieuDoi;
	}

	public void setMaPhieuDoi(String maPhieuDoi) {
		MaPhieuDoi = maPhieuDoi;
	}

	public int getID_HoaDon() {
		return ID_HoaDon;
	}

	public void setID_HoaDon(int iD_HoaDon) {
		ID_HoaDon = iD_HoaDon;
	}

	public String getMaHoaDon() {
		return MaHoaDon;
	}

	public void setMaHoaDon(String maHoaDon) {
		MaHoaDon = maHoaDon;
	}

	public int getID_Serial_Old() {
		return ID_Serial_Old;
	}

	public void setID_Serial_Old(int iD_Serial_Old) {
		ID_Serial_Old = iD_Serial_Old;
	}

	public String getSerialNumber_Old() {
		return SerialNumber_Old;
	}

	public void setSerialNumber_Old(String serialNumber_Old) {
		SerialNumber_Old = serialNumber_Old;
	}

	public int getID_Serial_New() {
		return ID_Serial_New;
	}

	public void setID_Serial_New(int iD_Serial_New) {
		ID_Serial_New = iD_Serial_New;
	}

	public String getSerialNumber_New() {
		return SerialNumber_New;
	}

	public void setSerialNumber_New(String serialNumber_New) {
		SerialNumber_New = serialNumber_New;
	}

	public BigDecimal getGiaCu() {
		return GiaCu;
	}

	public void setGiaCu(BigDecimal giaCu) {
		GiaCu = giaCu;
	}

	public BigDecimal getGiaMoi() {
		return GiaMoi;
	}

	public void setGiaMoi(BigDecimal giaMoi) {
		GiaMoi = giaMoi;
	}

	public String getTenLapCu() {
		return TenLapCu;
	}

	public void setTenLapCu(String tenLapCu) {
		TenLapCu = tenLapCu;
	}

	public String getTenLapMoi() {
		return TenLapMoi;
	}

	public void setTenLapMoi(String tenLapMoi) {
		TenLapMoi = tenLapMoi;
	}

	public boolean isTrangThai() {
		return TrangThai;
	}

	public void setTrangThai(boolean trangThai) {
		TrangThai = trangThai;
	}

	public String getLiDo() {
		return LiDo;
	}

	public void setLiDo(String liDo) {
		LiDo = liDo;
	}

	@Override
	public String toString() {
		return "CTPhieuDoi [ID=" + ID + ", ID_PhieuDoi=" + ID_PhieuDoi + ", MaPhieuDoi=" + MaPhieuDoi + ", ID_HoaDon="
				+ ID_HoaDon + ", MaHoaDon=" + MaHoaDon + ", ID_Serial_Old=" + ID_Serial_Old + ", SerialNumber_Old="
				+ SerialNumber_Old + ", ID_Serial_New=" + ID_Serial_New + ", SerialNumber_New=" + SerialNumber_New
				+ ", GiaCu=" + GiaCu + ", GiaMoi=" + GiaMoi + ", TenLapCu=" + TenLapCu + ", TenLapMoi=" + TenLapMoi
				+ ", TrangThai=" + TrangThai + ", LiDo=" + LiDo + "]";
	}
	
	
}
