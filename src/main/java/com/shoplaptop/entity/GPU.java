package com.shoplaptop.entity;

import java.util.Objects;

public class GPU {
	private int id;
	private String maGPU;
	private String loaiCard;
	private String hang;
	
	public GPU() {
		
	}
	
	public GPU(int id, String maGPU, String loaiCard, String hang) {
		this.id = id;
		this.maGPU = maGPU;
		this.loaiCard = loaiCard;
		this.hang = hang;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMaGPU() {
		return maGPU;
	}

	public void setMaGPU(String maGPU) {
		this.maGPU = maGPU;
	}

	public String getLoaiCard() {
		return loaiCard;
	}

	public void setLoaiCard(String loaiCard) {
		this.loaiCard = loaiCard;
	}

	public String getHang() {
		return hang;
	}

	public void setHang(String hang) {
		this.hang = hang;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(hang, loaiCard);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GPU other = (GPU) obj;
		return Objects.equals(hang, other.hang) && Objects.equals(loaiCard, other.loaiCard);
	}

	@Override
	public String toString() {
		return loaiCard + " - " + hang;
	}
	
	
	
	
}
