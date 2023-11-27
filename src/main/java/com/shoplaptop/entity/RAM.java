package com.shoplaptop.entity;

import java.util.Objects;

public class RAM {
	private int id;
	private String maRAM;
	private String loaiRAM;
	private int dungLuong;
	private int tocDoRAM;
	
	public RAM() {
		
	}
	
	public RAM(int id, String maRAM, String loaiRAM, int dungLuong, int tocDoRAM) {
		this.id = id;
		this.maRAM = maRAM;
		this.loaiRAM = loaiRAM;
		this.dungLuong = dungLuong;
		this.tocDoRAM = tocDoRAM;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMaRAM() {
		return maRAM;
	}

	public void setMaRAM(String maRAM) {
		this.maRAM = maRAM;
	}

	public String getLoaiRAM() {
		return loaiRAM;
	}

	public void setLoaiRAM(String loaiRAM) {
		this.loaiRAM = loaiRAM;
	}

	public int getDungLuong() {
		return dungLuong;
	}

	public void setDungLuong(int dungLuong) {
		this.dungLuong = dungLuong;
	}

	public int getTocDoRAM() {
		return tocDoRAM;
	}

	public void setTocDoRAM(int tocDoRAM) {
		this.tocDoRAM = tocDoRAM;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(dungLuong, loaiRAM, tocDoRAM);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RAM other = (RAM) obj;
		return dungLuong == other.dungLuong && Objects.equals(loaiRAM, other.loaiRAM) && tocDoRAM == other.tocDoRAM;
	}

	@Override
	public String toString() {
		return loaiRAM + " - " + dungLuong + " GB " + tocDoRAM + " MHz";
	}
	
	
	
}
