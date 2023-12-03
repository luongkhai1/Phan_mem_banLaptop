package com.shoplaptop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;

import com.shoplaptop.entity.NhanVien;

import com.shoplaptop.utils.XDate;
import com.shoplaptop.utils.XJdbc;

public class NhanVienService implements ShopLaptop365DAO<NhanVien, String>{
	
	Connection connection = new XJdbc().Connect();
	Statement st = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	
 	String Insert_SQL = "INSERT INTO NhanVien (MaNV,HoTen,SoDienThoai,NgaySinh,GioiTinh,Email,Hinh,DiaChi) VALUES (?,?,?,?,?,?,?,?) ";
					
	String Delete_SQL = "DELETE FROM dbo.NhanVien WHERE MaNV = ?";
			
	String Update_SQL = "UPDATE dbo.NhanVien SET HoTen =? , SoDienThoai =? , NgaySinh =? , GioiTinh =? , Email =? , Hinh =? , DiaChi =? WHERE MaNV =? ";

			
	String SelectById_SQL = "SELECT NhanVien.MaNV,HoTen,SoDienThoai,NgaySinh,GioiTinh,Email,Hinh,DiaChi,VaiTro FROM dbo.NhanVien JOIN dbo.TaiKhoan ON TaiKhoan.MaNV = NhanVien.MaNV Where NhanVien.MaNV = ?";
		
	String selectAll = "SELECT NhanVien.MaNV,HoTen,SoDienThoai,NgaySinh,GioiTinh,Email,Hinh,DiaChi,VaiTro FROM dbo.NhanVien JOIN dbo.TaiKhoan ON TaiKhoan.MaNV = NhanVien.MaNV";
	
	String Update_sql = "UPDATE dbo.TaiKhoan SET VaiTro =? WHERE MaNV =?";
	
	String query = "\r\n"
			+ "SELECT * FROM\r\n"
			+ "    (SELECT ROW_NUMBER() OVER (ORDER BY  NhanVien.MaNV DESC) AS rownum,   NhanVien.MaNV,HoTen,SoDienThoai,NgaySinh,GioiTinh,Email,Hinh,DiaChi,VaiTro FROM dbo.NhanVien JOIN dbo.TaiKhoan ON TaiKhoan.MaNV = NhanVien.MaNV)\r\n"
			+ "    AS temp\r\n"
			+ "    WHERE rownum BETWEEN ? AND ?";
	
	
		
	public String insert(NhanVien entity) {
		try {
			XJdbc.update(Insert_SQL, entity.getMaNV(),entity.getHoTen(),entity.getSoDienThoai(),entity.getNgaySinh(),entity.isGioiTinh(),entity.getEmail(),entity.getHinh(),entity.getDiaChi());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	
	public String update(NhanVien entity) {
		try {
			XJdbc.update(Update_SQL, entity.getHoTen(),entity.getSoDienThoai(),entity.getNgaySinh(),entity.isGioiTinh(),entity.getEmail(),entity.getHinh(),entity.getDiaChi(),entity.getMaNV());
			return "Update thành công";
		} catch (Exception e) {
			System.out.println(e);
			return "Update k thành công";
		}
		
	}
	
	public String updateTK(NhanVien nhanVien) {
		try {
			XJdbc.update(Update_sql, nhanVien.isVaiTro(),nhanVien.getMaNV());
			return "Update thành công";
		} catch (Exception e) {
			return "Update k thành công";
			// TODO: handle exception
		}
		
	}

	
	public String delete(String id) {
		
		try {
			XJdbc.update(Delete_SQL, id);
			return "Xóa thành công";
		} catch (Exception e) {
			System.out.println(e);
			return "Xóa k thành công";
			// TODO: handle exception
		}
		
	}

	
	public NhanVien selectById(String id) {
		List<NhanVien> list = this.selectBySQL(SelectById_SQL, id);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	
	public List<NhanVien> selectAll() {
		return selectBySQL(selectAll);
	}

	
	public List<NhanVien> selectBySQL(String sql, Object... args) {
		List<NhanVien> list = new ArrayList<NhanVien>();
		try {
			ResultSet rs = XJdbc.query(sql, args);
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien();
				nhanVien.setMaNV(rs.getString("MaNv"));
				nhanVien.setHoTen(rs.getString("HoTen"));
				nhanVien.setSoDienThoai(rs.getString("SoDienThoai"));
				nhanVien.setGioiTinh(rs.getBoolean("GioiTinh"));
				nhanVien.setEmail(rs.getString("Email"));
				nhanVien.setHinh(rs.getString("Hinh"));
				nhanVien.setDiaChi(rs.getString("DiaChi"));
				nhanVien.setNgaySinh(XDate.toDate(rs.getString("NgaySinh"), "yyyy-MM-dd"));
				nhanVien.setVaiTro(rs.getBoolean("VaiTro"));
//				nhanVien.setVaiTro(rs.getBoolean("VaiTro"));
				list.add(nhanVien);		
			}
			rs.getStatement().getConnection().close();
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	
	}
	
	public List<NhanVien> sellectAllNhanVien(int count) {
		return selectBySQL(query, count,count+2);
	}
	
	
}
