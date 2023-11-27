package com.shoplaptop.entity;

import java.util.Objects;

public class OCung {
	private int id;
	private String maOCung;
	private String kieuOCung;
	private int dungLuong;
	
	public OCung() {
		
	}
	
	public OCung(int id, String maOCung, String kieuOCung, int dungLuong) {
		this.id = id;
		this.maOCung = maOCung;
		this.kieuOCung = kieuOCung;
		this.dungLuong = dungLuong;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMaOCung() {
		return maOCung;
	}

	public void setMaOCung(String maOCung) {
		this.maOCung = maOCung;
	}

	public String getKieuOCung() {
		return kieuOCung;
	}

	public void setKieuOCung(String kieuOCung) {
		this.kieuOCung = kieuOCung;
	}

	public int getDungLuong() {
		return dungLuong;
	}

	public void setDungLuong(int dungLuong) {
		this.dungLuong = dungLuong;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(dungLuong, kieuOCung);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OCung other = (OCung) obj;
		return dungLuong == other.dungLuong && Objects.equals(kieuOCung, other.kieuOCung);
	}

	@Override
	public String toString() {
		return "Ổ " + kieuOCung + " - " + dungLuong + " GB";
	}
	
	
}
