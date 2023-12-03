package com.shoplaptop.entity;

import java.math.BigDecimal;
import java.util.Date;

public class HoaDon {
	private int id;
	private String maHD;
	private String maKH;
	private int id_HinhThucVanChuyen;
	private String hinhThucVanChuyen;
	private int id_HinhThucThanhToan;
	private String hinhThucThanhToan;
	private int id_PhieuGiamGia;
	private String phieuGiamGia;
	private String dotGiamGia;
	private String maNV;
	private Date ngayTao;
	private BigDecimal tongTien;
	private BigDecimal tienGiam;
	private BigDecimal thanhTien;
	private String trangThai;

	public HoaDon() {

	}

	public HoaDon(int id, String maHD, String maKH, int id_HinhThucVanChuyen, String hinhThucVanChuyen,
			int id_HinhThucThanhToan, String hinhThucThanhToan, int id_PhieuGiamGia, String phieuGiamGia,
			String dotGiamGia, String maNV, Date ngayTao, BigDecimal tongTien, BigDecimal tienGiam,
			BigDecimal thanhTien, String trangThai) {
		this.id = id;
		this.maHD = maHD;
		this.maKH = maKH;
		this.id_HinhThucVanChuyen = id_HinhThucVanChuyen;
		this.hinhThucVanChuyen = hinhThucVanChuyen;
		this.id_HinhThucThanhToan = id_HinhThucThanhToan;
		this.hinhThucThanhToan = hinhThucThanhToan;
		this.id_PhieuGiamGia = id_PhieuGiamGia;
		this.phieuGiamGia = phieuGiamGia;
		this.dotGiamGia = dotGiamGia;
		this.maNV = maNV;
		this.ngayTao = ngayTao;
		this.tongTien = tongTien;
		this.tienGiam = tienGiam;
		this.thanhTien = thanhTien;
		this.trangThai = trangThai;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMaHD() {
		return maHD;
	}

	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}

	public String getMaKH() {
		return maKH;
	}

	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}

	public int getId_HinhThucVanChuyen() {
		return id_HinhThucVanChuyen;
	}

	public void setId_HinhThucVanChuyen(int id_HinhThucVanChuyen) {
		this.id_HinhThucVanChuyen = id_HinhThucVanChuyen;
	}

	public String getHinhThucVanChuyen() {
		return hinhThucVanChuyen;
	}

	public void setHinhThucVanChuyen(String hinhThucVanChuyen) {
		this.hinhThucVanChuyen = hinhThucVanChuyen;
	}

	public int getId_HinhThucThanhToan() {
		return id_HinhThucThanhToan;
	}

	public void setId_HinhThucThanhToan(int id_HinhThucThanhToan) {
		this.id_HinhThucThanhToan = id_HinhThucThanhToan;
	}

	public String getHinhThucThanhToan() {
		return hinhThucThanhToan;
	}

	public void setHinhThucThanhToan(String hinhThucThanhToan) {
		this.hinhThucThanhToan = hinhThucThanhToan;
	}

	public int getId_PhieuGiamGia() {
		return id_PhieuGiamGia;
	}

	public void setId_PhieuGiamGia(int id_PhieuGiamGia) {
		this.id_PhieuGiamGia = id_PhieuGiamGia;
	}

	public String getPhieuGiamGia() {
		return phieuGiamGia;
	}

	public void setPhieuGiamGia(String phieuGiamGia) {
		this.phieuGiamGia = phieuGiamGia;
	}

	public String getDotGiamGia() {
		return dotGiamGia;
	}

	public void setDotGiamGia(String dotGiamGia) {
		this.dotGiamGia = dotGiamGia;
	}

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public Date getNgayTao() {
		return ngayTao;
	}

	public void setNgayTao(Date ngayTao) {
		this.ngayTao = ngayTao;
	}

	public BigDecimal getTongTien() {
		return tongTien;
	}

	public void setTongTien(BigDecimal tongTien) {
		this.tongTien = tongTien;
	}

	public BigDecimal getTienGiam() {
		return tienGiam;
	}

	public void setTienGiam(BigDecimal tienGiam) {
		this.tienGiam = tienGiam;
	}

	public BigDecimal getThanhTien() {
		return thanhTien;
	}

	public void setThanhTien(BigDecimal thanhTien) {
		this.thanhTien = thanhTien;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
	
}