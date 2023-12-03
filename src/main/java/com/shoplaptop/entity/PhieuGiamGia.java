package com.shoplaptop.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class PhieuGiamGia {
    private int ID;
    private String MaPG;
    private String TenPhieu;
    private Date Han;
    private int SoLuong;
    private BigDecimal GiaGiam;
    private BigDecimal DieuKienGiam;

    public PhieuGiamGia() {
    }

    public PhieuGiamGia(int ID, String MaPG, String TenPhieu, Date Han, int SoLuong, BigDecimal GiaGiam, BigDecimal DieuKienGiam) {
        this.ID = ID;
        this.MaPG = MaPG;
        this.TenPhieu = TenPhieu;
        this.Han = Han;
        this.SoLuong = SoLuong;
        this.GiaGiam = GiaGiam;
        this.DieuKienGiam = DieuKienGiam;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getMaPG() {
        return MaPG;
    }

    public void setMaPG(String MaPG) {
        this.MaPG = MaPG;
    }

    public String getTenPhieu() {
        return TenPhieu;
    }

    public void setTenPhieu(String TenPhieu) {
        this.TenPhieu = TenPhieu;
    }

    public Date getHan() {
        return Han;
    }

    public void setHan(Date Han) {
        this.Han = Han;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public BigDecimal getGiaGiam() {
        return GiaGiam;
    }

    public void setGiaGiam(BigDecimal GiaGiam) {
        this.GiaGiam = GiaGiam;
    }

    public BigDecimal getDieuKienGiam() {
        return DieuKienGiam;
    }

    public void setDieuKienGiam(BigDecimal DieuKienGiam) {
        this.DieuKienGiam = DieuKienGiam;
    }

    @Override
    public String toString() {
        return "PhieuGiamGia{" + "ID=" + ID + ", MaPG=" + MaPG + ", TenPhieu=" + TenPhieu + ", Han=" + Han + ", SoLuong=" + SoLuong + ", GiaGiam=" + GiaGiam + ", DieuKienGiam=" + DieuKienGiam + '}';
    }

    
  
    
}