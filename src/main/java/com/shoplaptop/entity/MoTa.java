package com.shoplaptop.entity;

public class MoTa {
	private int id_MaLaptop;
	private String maLaptop;
	private int soLuongLoa;
	private String keyBoard;
	private String touchPad;
	private String congKetNoi;
	private String wifi;
	private String bluetooth;
	private String webcam;
	private int soLuongQuat;
	private double trongLuong;
	private String pin;
	
	public MoTa() {
		
	}

	public MoTa(int id_MaLaptop, String maLaptop, int soLuongLoa, String keyBoard, String touchPad, String congKetNoi,
			String wifi, String bluetooth, String webcam, int soLuongQuat, double trongLuong, String pin) {
		this.id_MaLaptop = id_MaLaptop;
		this.maLaptop = maLaptop;
		this.soLuongLoa = soLuongLoa;
		this.keyBoard = keyBoard;
		this.touchPad = touchPad;
		this.congKetNoi = congKetNoi;
		this.wifi = wifi;
		this.bluetooth = bluetooth;
		this.webcam = webcam;
		this.soLuongQuat = soLuongQuat;
		this.trongLuong = trongLuong;
		this.pin = pin;
	}
	
	public MoTa(String maLaptop, int soLuongLoa, String keyBoard, String touchPad, String congKetNoi, String wifi,
			String bluetooth, String webcam, int soLuongQuat, double trongLuong, String pin) {
		super();
		this.maLaptop = maLaptop;
		this.soLuongLoa = soLuongLoa;
		this.keyBoard = keyBoard;
		this.touchPad = touchPad;
		this.congKetNoi = congKetNoi;
		this.wifi = wifi;
		this.bluetooth = bluetooth;
		this.webcam = webcam;
		this.soLuongQuat = soLuongQuat;
		this.trongLuong = trongLuong;
		this.pin = pin;
	}

	public int getId_MaLaptop() {
		return id_MaLaptop;
	}

	public void setId_MaLaptop(int id_MaLaptop) {
		this.id_MaLaptop = id_MaLaptop;
	}

	public String getMaLaptop() {
		return maLaptop;
	}

	public void setMaLaptop(String maLaptop) {
		this.maLaptop = maLaptop;
	}

	public int getSoLuongLoa() {
		return soLuongLoa;
	}

	public void setSoLuongLoa(int soLuongLoa) {
		this.soLuongLoa = soLuongLoa;
	}

	public String getKeyBoard() {
		return keyBoard;
	}

	public void setKeyBoard(String keyBoard) {
		this.keyBoard = keyBoard;
	}

	public String getTouchPad() {
		return touchPad;
	}

	public void setTouchPad(String touchPad) {
		this.touchPad = touchPad;
	}

	public String getCongKetNoi() {
		return congKetNoi;
	}

	public void setCongKetNoi(String congKetNoi) {
		this.congKetNoi = congKetNoi;
	}

	public String getWifi() {
		return wifi;
	}

	public void setWifi(String wifi) {
		this.wifi = wifi;
	}

	public String getBluetooth() {
		return bluetooth;
	}

	public void setBluetooth(String bluetooth) {
		this.bluetooth = bluetooth;
	}

	public String getWebcam() {
		return webcam;
	}

	public void setWebcam(String webcam) {
		this.webcam = webcam;
	}

	public int getSoLuongQuat() {
		return soLuongQuat;
	}

	public void setSoLuongQuat(int soLuongQuat) {
		this.soLuongQuat = soLuongQuat;
	}

	public double getTrongLuong() {
		return trongLuong;
	}

	public void setTrongLuong(double trongLuong) {
		this.trongLuong = trongLuong;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	@Override
	public String toString() {
		return "MoTa [id_MaLaptop=" + id_MaLaptop + ", maLaptop=" + maLaptop + ", soLuongLoa=" + soLuongLoa
				+ ", keyBoard=" + keyBoard + ", touchPad=" + touchPad + ", congKetNoi=" + congKetNoi + ", wifi=" + wifi
				+ ", bluetooth=" + bluetooth + ", webcam=" + webcam + ", soLuongQuat=" + soLuongQuat + ", trongLuong="
				+ trongLuong + ", pin=" + pin + "]";
	}

	
	
	
}
