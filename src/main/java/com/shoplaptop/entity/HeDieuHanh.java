package com.shoplaptop.entity;

import java.util.Objects;

public class HeDieuHanh {
	private int id;
	private String maHeDieuHanh;
	private String os;
	private String version;
	private int kieu;
	
	public HeDieuHanh() {
		
	}
	
	public HeDieuHanh(int id, String maHeDieuHanh, String os, String version, int kieu) {
		this.id = id;
		this.maHeDieuHanh = maHeDieuHanh;
		this.os = os;
		this.version = version;
		this.kieu = kieu;
	}
	
	public HeDieuHanh(String os) {
		this.os = os;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMaHeDieuHanh() {
		return maHeDieuHanh;
	}

	public void setMaHeDieuHanh(String maHeDieuHanh) {
		this.maHeDieuHanh = maHeDieuHanh;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public int getKieu() {
		return kieu;
	}

	public void setKieu(int kieu) {
		this.kieu = kieu;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(kieu, os, version);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HeDieuHanh other = (HeDieuHanh) obj;
		return kieu == other.kieu && Objects.equals(os, other.os) && Objects.equals(version, other.version);
	}

	@Override
	public String toString() {
		return os + " " + version + " " + kieu + " bit" ;
	}
	
	
	
}
