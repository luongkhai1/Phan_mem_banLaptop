package com.shoplaptop.entity;

import java.util.Objects;

public class PhanLoai {
	private int id;
	private String maLoai;
	private String TenLoai;
	private String moTa;
	
	public PhanLoai() {
		
	}
	
	public PhanLoai(int id, String maLoai, String tenLoai, String moTa) {
		this.id = id;
		this.maLoai = maLoai;
		TenLoai = tenLoai;
		this.moTa = moTa;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMaLoai() {
		return maLoai;
	}

	public void setMaLoai(String maLoai) {
		this.maLoai = maLoai;
	}

	public String getTenLoai() {
		return TenLoai;
	}

	public void setTenLoai(String tenLoai) {
		TenLoai = tenLoai;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}
	
	

	@Override
	public int hashCode() {
		return Objects.hash(TenLoai);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PhanLoai other = (PhanLoai) obj;
		return Objects.equals(TenLoai, other.TenLoai);
	}

	@Override
	public String toString() {
		return TenLoai ;
	}
	
	
	
}
