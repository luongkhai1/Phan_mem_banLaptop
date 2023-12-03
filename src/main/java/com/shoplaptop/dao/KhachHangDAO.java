package com.shoplaptop.dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import com.shoplaptop.entity.KhachHang;
import com.shoplaptop.utils.XDate;
import com.shoplaptop.utils.XJdbc;

public class KhachHangDAO implements ShopLaptop365DAO<KhachHang, String> {
	Connection connection = new XJdbc().Connect();
	Statement statement = null;
	PreparedStatement pStatement = null;
	ResultSet rSet = null;
	
	
	
		String insertKH_SQL="INSERT INTO dbo.KhachHang(MaKH, HoTen, SoDienThoai, NgaySinh, GioiTinh, Email, DiaChi) VALUES (?,?,?,?,?,?,?)";
		String updateKH_SQL="UPDATE dbo.KhachHang SET HoTen=?, SoDienThoai=?, NgaySinh=?, GioiTinh=?, Email=?, DiaChi=? WHERE MaKH=?";
		String deleteKH_SQL="DELETE FROM KhachHang WHERE MaKH=?";
		String selectBySDT = "SELECT * FROM KhachHang WHERE SoDienThoai like ? OR MaKH=? ";
		String selectBySoDienThoai = "SELECT * FROM KhachHang WHERE SoDienThoai like ? ";
		String selectByMaKH = "SELECT * FROM KhachHang WHERE MaKH=?";
		String selectAll_SQL="SELECT * FROM KhachHang";
		String query =  "SELECT * FROM (SELECT ROW_NUMBER() OVER (ORDER BY MaKH) AS rownum,  * FROM KhachHang) AS temp WHERE rownum BETWEEN ? AND ?";
		
		String insertKH_No_Infomation = "INSERT INTO dbo.KhachHang (MaKH, HoTen, SoDienThoai, DiaChi) VALUES (?, ?, ?, ?)";
		

	@Override
	public String insert(KhachHang khachHang) {
		 
		try {
			pStatement = connection.prepareStatement(insertKH_SQL);
			pStatement.setString(1, khachHang.getMaKH());
			pStatement.setString(2, khachHang.getHoTen());
			pStatement.setString(3, khachHang.getSoDienThoai());
			pStatement.setString(4, XDate.toString(khachHang.getNgaySinh(), "yyyy-MM-dd"));
			pStatement.setBoolean(5, khachHang.isGioiTinh());
			pStatement.setString(6, khachHang.getEmail());
			pStatement.setString(7, khachHang.getDiaChi());
			
			pStatement.executeUpdate();
			return "Thêm khách hàng thành công";
			
		} catch (Exception e) {
			
			throw new RuntimeException();
		}
	}
	
	public String insertKH(KhachHang khachHang) {
		try {
			XJdbc.update(insertKH_No_Infomation, khachHang.getMaKH(), khachHang.getHoTen(), khachHang.getSoDienThoai(), khachHang.getDiaChi());
			return "Thêm KH thành công";
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String update(KhachHang khachHang) {
		try {
			XJdbc.update(updateKH_SQL, khachHang.getHoTen(),khachHang.getSoDienThoai(),khachHang.getNgaySinh(),khachHang.isGioiTinh(),khachHang.getEmail(),khachHang.getDiaChi(),khachHang.getMaKH());
			return "Sửa khách hàng thành công";
			
		} catch (Exception e) {
			return "Sửa khách hàng thất bại";
			
		}
		
	}

	@Override
	public String delete(String MaKH) {
		try {
			pStatement = connection.prepareStatement(deleteKH_SQL);
			
			pStatement.setString(1, MaKH);
			
			pStatement.executeUpdate();
			return "Xóa khách hàng thành công";
			
		} catch (Exception e) {
			return "Xóa khách hàng thất bại";
		}
	}

	
	public KhachHang selectById(String SDT, String MaKH) {
		List<KhachHang> list = this.selectBySQL(selectBySDT, SDT, MaKH);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<KhachHang> selectAll() {
		return selectBySQL(selectAll_SQL);
	}

	@Override
	public List<KhachHang> selectBySQL(String sql, Object... args) {
		List<KhachHang> list = new ArrayList<KhachHang>();
		try {
			ResultSet rSet = XJdbc.query(sql, args);
			while (rSet.next()) {
				String MaKH = rSet.getString("MaKH");
				String HoTen = rSet.getString("HoTen");
				String SoDienThoai = rSet.getString("SoDienThoai");
				Date NgaySinh = rSet.getDate("NgaySinh");
				boolean GioiTinh = rSet.getBoolean("GioiTinh");
				String Email = rSet.getString("Email");
				String DiaChi = rSet.getString("DiaChi");
				
				KhachHang khachHang = new KhachHang(MaKH, HoTen, SoDienThoai, NgaySinh, GioiTinh, Email, DiaChi);
				list.add(khachHang);
				
			}
			rSet.getStatement().getConnection().close();
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public KhachHang selectByMaKH(String MaKH) {
		List<KhachHang> list = this.selectBySQL(selectByMaKH, MaKH);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
	public KhachHang selectBySoDienThoai(String SDT) {
		List<KhachHang> list = this.selectBySQL(selectBySoDienThoai, SDT);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	public KhachHang selectByTenKH(String TenKH) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KhachHang selectById(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	public List<KhachHang> sellectAllKhachHang(int count) {
		return selectBySQL(query, count, count +4);
		
		
	}




}