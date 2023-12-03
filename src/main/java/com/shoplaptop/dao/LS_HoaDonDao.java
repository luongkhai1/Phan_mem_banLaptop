package com.shoplaptop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.shoplaptop.entity.BaoCao_LS_HoaDon;
import com.shoplaptop.utils.XJdbc;

public class LS_HoaDonDao implements ShopLaptop365DAO<BaoCao_LS_HoaDon, String>{
	
	Connection connection = new XJdbc().Connect();
	Statement st = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	String SellectAll = "select LS_HoaDon.manv,maHD,Lichsulamviec from LS_HoaDon join HoaDon on LS_HoaDon.mahoadon = Hoadon.id where LS_Hoadon.manv =?";
	
	String  deleteSQL = "DELETE FROM dbo.LS_HoaDon WHERE MaNV = ?";
			
	
	public String insert(BaoCao_LS_HoaDon entity) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String update(BaoCao_LS_HoaDon entity) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String delete(String id) {
		try {
			XJdbc.update(deleteSQL, id);
			return "xóa thành công";
		} catch (Exception e) {
			System.out.println(e);
			return "Xóa k thành công";
			// TODO: handle exception
		}
	}

	
	public BaoCao_LS_HoaDon selectById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<BaoCao_LS_HoaDon> selectAll() {
		return selectBySQL(SellectAll);
	}

	
	public List<BaoCao_LS_HoaDon> selectBySQL(String sql, Object... args) {
		List<BaoCao_LS_HoaDon> list = new ArrayList<BaoCao_LS_HoaDon>();
		try {
			ResultSet rs = XJdbc.query(sql, args);
			while (rs.next()) {
				BaoCao_LS_HoaDon baoCao = new BaoCao_LS_HoaDon();
				baoCao.setManv(rs.getString("MaNV"));
				baoCao.setMahd(rs.getString("maHD"));
				baoCao.setLS(rs.getString("LichSuLamViec"));
				list.add(baoCao);
				
			}
			rs.getStatement().getConnection().close();
			return list;
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
}
