package com.shoplaptop.entity;

import java.math.BigDecimal;

public class CTPhieuDoi {
	private int ID;
	private int ID_PhieuDoi ;
	private String MaPhieuDoi;
	private int ID_Serial_Old;
	private String SerialNumber_Old;
	private int ID_Serial_New;
	private String SerialNumber_New;
	private BigDecimal giaCu;
	private BigDecimal giaMoi;
	private String TenLapCu;
	private String TenLapMoi;
	
	public CTPhieuDoi(int iD, int iD_PhieuDoi, String maPhieuDoi, int iD_Serial_Old, String serialNumber_Old,
			int iD_Serial_New, String serialNumber_New, BigDecimal giaCu, BigDecimal giaMoi, String tenLapCu,
			String tenLapMoi) {
		super();
		ID = iD;
		ID_PhieuDoi = iD_PhieuDoi;
		MaPhieuDoi = maPhieuDoi;
		ID_Serial_Old = iD_Serial_Old;
		SerialNumber_Old = serialNumber_Old;
		ID_Serial_New = iD_Serial_New;
		SerialNumber_New = serialNumber_New;
		this.giaCu = giaCu;
		this.giaMoi = giaMoi;
		TenLapCu = tenLapCu;
		TenLapMoi = tenLapMoi;
	}

	public CTPhieuDoi() {
		// TODO Auto-generated constructor stub
	}
	
	public CTPhieuDoi(int iD, int iD_PhieuDoi, String maPhieuDoi, int iD_Serial_Old, String serialNumber_Old,
			int iD_Serial_New, String serialNumber_New, BigDecimal giaCu, BigDecimal giaMoi) {
		ID = iD;
		ID_PhieuDoi = iD_PhieuDoi;
		MaPhieuDoi = maPhieuDoi;
		ID_Serial_Old = iD_Serial_Old;
		SerialNumber_Old = serialNumber_Old;
		ID_Serial_New = iD_Serial_New;
		SerialNumber_New = serialNumber_New;
		this.giaCu = giaCu;
		this.giaMoi = giaMoi;
	}
	
	public CTPhieuDoi(int iD_PhieuDoi, int iD_Serial_Old, int iD_Serial_New) {
		super();
		ID_PhieuDoi = iD_PhieuDoi;
		ID_Serial_Old = iD_Serial_Old;
		ID_Serial_New = iD_Serial_New;
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
		return giaCu;
	}

	public void setGiaCu(BigDecimal giaCu) {
		this.giaCu = giaCu;
	}

	public BigDecimal getGiaMoi() {
		return giaMoi;
	}

	public void setGiaMoi(BigDecimal giaMoi) {
		this.giaMoi = giaMoi;
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

	@Override
	public String toString() {
		return "CTPhieuDoi [ID_PhieuDoi=" + ID_PhieuDoi + ", MaPhieuDoi=" + MaPhieuDoi + ", ID_Serial_Old="
				+ ID_Serial_Old + ", SerialNumber_Old=" + SerialNumber_Old + ", ID_Serial_New=" + ID_Serial_New
				+ ", SerialNumber_New=" + SerialNumber_New + "]";
	}
	
}
