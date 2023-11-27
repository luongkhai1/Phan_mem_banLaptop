package com.shoplaptop.entity;

import java.util.Objects;

public class CPU {
	private int id;
	private String maCPU;
	private String hangCPU;
	private String congNghe;
	private String loaiCPU;
	private double tocDoCPU;
	private double tocDoToiDa;
	private int soNhan;
	private int soLuong;
	private int boNhoDem;
	
	public CPU() {
		
	}

	public CPU(int id, String maCPU, String hangCPU, String congNghe, String loaiCPU, double tocDoCPU,
			double tocDoToiDa, int soNhan, int soLuong, int boNhoDem) {
		this.id = id;
		this.maCPU = maCPU;
		this.hangCPU = hangCPU;
		this.congNghe = congNghe;
		this.loaiCPU = loaiCPU;
		this.tocDoCPU = tocDoCPU;
		this.tocDoToiDa = tocDoToiDa;
		this.soNhan = soNhan;
		this.soLuong = soLuong;
		this.boNhoDem = boNhoDem;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMaCPU() {
		return maCPU;
	}

	public void setMaCPU(String maCPU) {
		this.maCPU = maCPU;
	}

	public String getHangCPU() {
		return hangCPU;
	}

	public void setHangCPU(String hangCPU) {
		this.hangCPU = hangCPU;
	}

	public String getCongNghe() {
		return congNghe;
	}

	public void setCongNghe(String congNghe) {
		this.congNghe = congNghe;
	}

	public String getLoaiCPU() {
		return loaiCPU;
	}

	public void setLoaiCPU(String loaiCPU) {
		this.loaiCPU = loaiCPU;
	}

	public double getTocDoCPU() {
		return tocDoCPU;
	}

	public void setTocDoCPU(double tocDoCPU) {
		this.tocDoCPU = tocDoCPU;
	}

	public double getTocDoToiDa() {
		return tocDoToiDa;
	}

	public void setTocDoToiDa(double tocDoToiDa) {
		this.tocDoToiDa = tocDoToiDa;
	}

	public int getSoNhan() {
		return soNhan;
	}

	public void setSoNhan(int soNhan) {
		this.soNhan = soNhan;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public int getBoNhoDem() {
		return boNhoDem;
	}

	public void setBoNhoDem(int boNhoDem) {
		this.boNhoDem = boNhoDem;
	}
	
	

	@Override
	public int hashCode() {
		return Objects.hash(hangCPU, loaiCPU);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CPU other = (CPU) obj;
		return Objects.equals(hangCPU, other.hangCPU) && Objects.equals(loaiCPU, other.loaiCPU);
	}

	@Override
	public String toString() {
		return hangCPU + " " + loaiCPU;
	}

	
	
}
