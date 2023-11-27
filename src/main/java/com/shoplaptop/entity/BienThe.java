package com.shoplaptop.entity;

import java.math.BigDecimal;

public class BienThe {
	private int id;
	private int id_Laptop;
	private String maLaptop;
	private String maBienThe;
	private int id_CPU;
	private String cpu;
	private int id_RAM;
	private String ram;
	private int id_ManHinh;
	private String manHinh;
	private int id_GPU;
	private String gpu;
	private int id_OCung;
	private String oCung;
	private String mauSac;
	private int id_HeDieuHanh;
	private String heDieuHanh;
	private BigDecimal gia;
	private String hinh;
	private int soLuong;
	
	public BienThe() {
		
	}

	public BienThe(int id, int id_Laptop, String maLaptop, String maBienThe, int id_CPU, String cpu, int id_RAM,
			String ram, int id_ManHinh, String manHinh, int id_GPU, String gpu, int id_OCung, String oCung,
			String mauSac, int id_HeDieuHanh, String heDieuHanh, BigDecimal gia, String hinh,
			int soLuong) {
		this.id = id;
		this.id_Laptop = id_Laptop;
		this.maLaptop = maLaptop;
		this.maBienThe = maBienThe;
		this.id_CPU = id_CPU;
		this.cpu = cpu;
		this.id_RAM = id_RAM;
		this.ram = ram;
		this.id_ManHinh = id_ManHinh;
		this.manHinh = manHinh;
		this.id_GPU = id_GPU;
		this.gpu = gpu;
		this.id_OCung = id_OCung;
		this.oCung = oCung;
		this.mauSac = mauSac;
		this.id_HeDieuHanh = id_HeDieuHanh;
		this.heDieuHanh = heDieuHanh;
		this.gia = gia;
		this.hinh = hinh;
		this.soLuong = soLuong;
	}

	public BienThe(int id, int id_Laptop, String maBienThe, int id_CPU, int id_RAM, int id_ManHinh, int id_GPU,
			int id_OCung, String mauSac, int id_HeDieuHanh, BigDecimal gia, String hinh) {
		this.id = id;
		this.id_Laptop = id_Laptop;
		this.maBienThe = maBienThe;
		this.id_CPU = id_CPU;
		this.id_RAM = id_RAM;
		this.id_ManHinh = id_ManHinh;
		this.id_GPU = id_GPU;
		this.id_OCung = id_OCung;
		this.mauSac = mauSac;
		this.id_HeDieuHanh = id_HeDieuHanh;
		this.gia = gia;
		this.hinh = hinh;
	}

	public BienThe(String maLaptop, String maBienThe, String cpu, String ram, String manHinh, String gpu, String oCung,
			String mauSac, String heDieuHanh, BigDecimal gia, int soLuong) {
		this.maLaptop = maLaptop;
		this.maBienThe = maBienThe;
		this.cpu = cpu;
		this.ram = ram;
		this.manHinh = manHinh;
		this.gpu = gpu;
		this.oCung = oCung;
		this.mauSac = mauSac;
		this.heDieuHanh = heDieuHanh;
		this.gia = gia;
		this.soLuong = soLuong;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_Laptop() {
		return id_Laptop;
	}

	public void setId_Laptop(int id_Laptop) {
		this.id_Laptop = id_Laptop;
	}

	public String getMaLaptop() {
		return maLaptop;
	}

	public void setMaLaptop(String maLaptop) {
		this.maLaptop = maLaptop;
	}

	public String getMaBienThe() {
		return maBienThe;
	}

	public void setMaBienThe(String maBienThe) {
		this.maBienThe = maBienThe;
	}

	public int getId_CPU() {
		return id_CPU;
	}

	public void setId_CPU(int id_CPU) {
		this.id_CPU = id_CPU;
	}

	public String getCpu() {
		return cpu;
	}

	public void setCpu(String cpu) {
		this.cpu = cpu;
	}

	public int getId_RAM() {
		return id_RAM;
	}

	public void setId_RAM(int id_RAM) {
		this.id_RAM = id_RAM;
	}

	public String getRam() {
		return ram;
	}

	public void setRam(String ram) {
		this.ram = ram;
	}

	public int getId_ManHinh() {
		return id_ManHinh;
	}

	public void setId_ManHinh(int id_ManHinh) {
		this.id_ManHinh = id_ManHinh;
	}

	public String getManHinh() {
		return manHinh;
	}

	public void setManHinh(String manHinh) {
		this.manHinh = manHinh;
	}

	public int getId_GPU() {
		return id_GPU;
	}

	public void setId_GPU(int id_GPU) {
		this.id_GPU = id_GPU;
	}

	public String getGpu() {
		return gpu;
	}

	public void setGpu(String gpu) {
		this.gpu = gpu;
	}

	public int getId_OCung() {
		return id_OCung;
	}

	public void setId_OCung(int id_OCung) {
		this.id_OCung = id_OCung;
	}

	public String getoCung() {
		return oCung;
	}

	public void setoCung(String oCung) {
		this.oCung = oCung;
	}

	public String getMauSac() {
		return mauSac;
	}

	public void setMauSac(String mauSac) {
		this.mauSac = mauSac;
	}

	public int getId_HeDieuHanh() {
		return id_HeDieuHanh;
	}

	public void setId_HeDieuHanh(int id_HeDieuHanh) {
		this.id_HeDieuHanh = id_HeDieuHanh;
	}

	public String getHeDieuHanh() {
		return heDieuHanh;
	}

	public void setHeDieuHanh(String heDieuHanh) {
		this.heDieuHanh = heDieuHanh;
	}

	public BigDecimal getGia() {
		return gia;
	}

	public void setGia(BigDecimal gia) {
		this.gia = gia;
	}

	public String getHinh() {
		return hinh;
	}

	public void setHinh(String hinh) {
		this.hinh = hinh;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	@Override
	public String toString() {
		return "BienThe [id=" + id + ", id_Laptop=" + id_Laptop + ", maLaptop=" + maLaptop + ", maBienThe=" + maBienThe
				+ ", id_CPU=" + id_CPU + ", cpu=" + cpu + ", id_RAM=" + id_RAM + ", ram=" + ram + ", id_ManHinh="
				+ id_ManHinh + ", manHinh=" + manHinh + ", id_GPU=" + id_GPU + ", gpu=" + gpu + ", id_OCung=" + id_OCung
				+ ", oCung=" + oCung + ", mauSac=" + mauSac + ", id_HeDieuHanh=" + id_HeDieuHanh + ", heDieuHanh="
				+ heDieuHanh + ", gia=" + gia + ", hinh=" + hinh + ", soLuong=" + soLuong + "]";
	}
	
	
	
}
