package com.shoplaptop.entity;

import java.util.Objects;

public class ManHinh {
	private int id;
	private String maManHinh;
	private double kichThuocManHinh;
	private String congNgheManHinh;
	private String doPhanGiai;
	private int tanSoQuet;
	private String tamNen;
	private int doSang;
	private String doPhuMau;
	private String tiLeManHinh;
	
	public ManHinh() {
		
	}
	
	public ManHinh(int id, String maManHinh, double kichThuocManHinh, String congNgheManHinh, String doPhanGiai,
			int tanSoQuet, String tamNen, int doSang, String doPhuMau, String tiLeManHinh) {
		this.id = id;
		this.maManHinh = maManHinh;
		this.kichThuocManHinh = kichThuocManHinh;
		this.congNgheManHinh = congNgheManHinh;
		this.doPhanGiai = doPhanGiai;
		this.tanSoQuet = tanSoQuet;
		this.tamNen = tamNen;
		this.doSang = doSang;
		this.doPhuMau = doPhuMau;
		this.tiLeManHinh = tiLeManHinh;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMaManHinh() {
		return maManHinh;
	}

	public void setMaManHinh(String maManHinh) {
		this.maManHinh = maManHinh;
	}

	public double getKichThuocManHinh() {
		return kichThuocManHinh;
	}

	public void setKichThuocManHinh(double kichThuocManHinh) {
		this.kichThuocManHinh = kichThuocManHinh;
	}

	public String getCongNgheManHinh() {
		return congNgheManHinh;
	}

	public void setCongNgheManHinh(String congNgheManHinh) {
		this.congNgheManHinh = congNgheManHinh;
	}

	public String getDoPhanGiai() {
		return doPhanGiai;
	}

	public void setDoPhanGiai(String doPhanGiai) {
		this.doPhanGiai = doPhanGiai;
	}

	public int getTanSoQuet() {
		return tanSoQuet;
	}

	public void setTanSoQuet(int tanSoQuet) {
		this.tanSoQuet = tanSoQuet;
	}

	public String getTamNen() {
		return tamNen;
	}

	public void setTamNen(String tamNen) {
		this.tamNen = tamNen;
	}

	public int getDoSang() {
		return doSang;
	}

	public void setDoSang(int doSang) {
		this.doSang = doSang;
	}

	public String getDoPhuMau() {
		return doPhuMau;
	}

	public void setDoPhuMau(String doPhuMau) {
		this.doPhuMau = doPhuMau;
	}

	public String getTiLeManHinh() {
		return tiLeManHinh;
	}

	public void setTiLeManHinh(String tiLeManHinh) {
		this.tiLeManHinh = tiLeManHinh;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(congNgheManHinh, doPhanGiai, kichThuocManHinh);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ManHinh other = (ManHinh) obj;
		return Objects.equals(congNgheManHinh, other.congNgheManHinh) && Objects.equals(doPhanGiai, other.doPhanGiai)
				&& Double.doubleToLongBits(kichThuocManHinh) == Double.doubleToLongBits(other.kichThuocManHinh);
	}

	@Override
	public String toString() {
		return congNgheManHinh + " - " + kichThuocManHinh + " - " + doPhanGiai;
	}
	
	
	
}
