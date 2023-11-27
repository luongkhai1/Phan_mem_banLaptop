package com.shoplaptop.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.shoplaptop.entity.Hang;
import com.shoplaptop.utils.XJdbc;

public class HangDAO implements ShopLaptop365DAO<Hang, Integer> {
	
	String selectAll_SQL = "SELECT * FROM Hang";
	String selectByID_SQL = "SELECT * FROM Hang WHERE ID = ?";
	String insertHang = "INSERT INTO Hang(MaHang, TenHang, HoTro) \r\n"
			+ "VALUES (?, ?, ?)";
	String updateHang = "UPDATE Hang SET TenHang = ?, HoTro = ? WHERE MaHang = ?";
	String deleteHang = "DELETE FROM Hang WHERE MaHang = ?";

	public String insert(Hang entity) {
		try {
			XJdbc.update(insertHang, entity.getMaHang(), entity.getTenHang(), entity.getHoTro());
			return "Add thành công";
		} catch (Exception e) {
			return "Add thất bại";
		}
	}

	public String update(Hang entity) {
		try {
			XJdbc.update(updateHang, entity.getTenHang(), entity.getHoTro(), entity.getMaHang());
			return "Update thành công";
		} catch (Exception e) {
			return "Update thất bại";
		}
	}

	public String delete(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public String deleteHang(String maHang) {
		try {
			XJdbc.update(deleteHang, maHang);
			return "Delete thành công";
		} catch (Exception e) {
			return "Delete thất bại";
		}

	}
	
	public Hang selectById(Integer id) {
		List<Hang> list = this.selectBySQL(selectByID_SQL, id);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	public List<Hang> selectAll() {
		return selectBySQL(selectAll_SQL);
	}

	public List<Hang> selectBySQL(String sql, Object... args) {
		List<Hang> list = new ArrayList<Hang>();
		try {
			ResultSet rs = XJdbc.query(sql, args);
			while (rs.next()) {
				Hang hang = new Hang();
				hang.setId(rs.getInt("ID"));
				hang.setMaHang(rs.getString("MaHang"));
				hang.setTenHang(rs.getString("TenHang"));
				hang.setHoTro(rs.getString("HoTro"));
				list.add(hang);
			}
			rs.getStatement().getConnection().close();
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
