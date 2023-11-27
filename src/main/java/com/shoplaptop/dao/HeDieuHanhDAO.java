package com.shoplaptop.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.shoplaptop.entity.HeDieuHanh;
import com.shoplaptop.utils.XJdbc;

public class HeDieuHanhDAO implements ShopLaptop365DAO<HeDieuHanh, Integer> {

	String selectAll_SQL = "SELECT * FROM HeDieuHanh";
	String selectByID_SQL = "SELECT * FROM HeDieuHanh WHERE ID = ?";
	private String OS;
	String insertHDH = "INSERT INTO HeDieuHanh(MaHeDieuHanh, OS, Versions, Kieu) \r\n"
			+ "VALUES (?, ?, ?, ?)";
	String updateHDH = "UPDATE HeDieuHanh SET OS = ?, Versions = ?, Kieu = ? WHERE MaHeDieuHanh = ?";
	String deleteHDH = "DELETE FROM HeDieuHanh WHERE MaHeDieuHanh = ?";

	
	public String insert(HeDieuHanh entity) {
		try {
			XJdbc.update(insertHDH, entity.getMaHeDieuHanh(), entity.getOs(), entity.getVersion(), entity.getKieu());
			return "Add thành công";
		} catch (Exception e) {
			return "Add thất bại";
		}
	}

	public String update(HeDieuHanh entity) {
		try {
			XJdbc.update(updateHDH, entity.getOs(), entity.getVersion(), entity.getKieu(), entity.getMaHeDieuHanh());
			return "Update thành công";
		} catch (Exception e) {
			return "Update thất bại";
		}
	}

	public String delete(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String deleteHDH(String maHDH) {
		try {
			XJdbc.update(deleteHDH, maHDH);
			return "Delete thành công";
		} catch (Exception e) {
			return "Delete thất bại";
		}
	}

	public HeDieuHanh selectById(Integer id) {
		List<HeDieuHanh> list = this.selectBySQL(selectByID_SQL, id);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	public List<HeDieuHanh> selectAll() {
		return selectBySQL(selectAll_SQL);
	}

	public List<HeDieuHanh> selectBySQL(String sql, Object... args) {
		List<HeDieuHanh> list = new ArrayList<HeDieuHanh>();
		try {
			ResultSet rs = XJdbc.query(sql, args);
			while (rs.next()) {
				HeDieuHanh heDieuHanh = new HeDieuHanh();
				heDieuHanh.setId(rs.getInt("ID"));
				heDieuHanh.setMaHeDieuHanh(rs.getString("MaHeDieuHanh"));
				heDieuHanh.setOs(rs.getString("OS"));
				heDieuHanh.setVersion(rs.getString("Versions"));
				heDieuHanh.setKieu(rs.getInt("Kieu"));
				list.add(heDieuHanh);
			}
			rs.getStatement().getConnection().close();
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<String> selectOS(String sql, Object... args) {
		List<String> list = new ArrayList<String>();
		try {
			ResultSet rs = XJdbc.query(sql, args);
			while (rs.next()) {
				OS = rs.getString("OS");
				list.add(OS);
			}
			rs.getStatement().getConnection().close();
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
