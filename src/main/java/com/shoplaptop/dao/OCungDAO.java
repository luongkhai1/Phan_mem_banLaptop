package com.shoplaptop.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.shoplaptop.entity.OCung;
import com.shoplaptop.utils.XJdbc;

public class OCungDAO implements ShopLaptop365DAO<OCung, Integer> {
	
	String selectAll_SQL = "SELECT * FROM OCung";
	String selectByID = "SELECT * FROM OCung WHERE ID = ?";
	String insert = "INSERT INTO OCung(MaOCung, KieuOCung, DungLuong) \r\n"
			+ "VALUES (?, ?, ?)";
	String update = "UPDATE OCung SET KieuOCung = ?, DungLuong = ? WHERE MaOCung = ?";
	String delete = "DELETE FROM OCung WHERE MaOCung = ?";
	
	public String insert(OCung entity) {
		try {
			XJdbc.update(insert, entity.getMaOCung(), entity.getKieuOCung(), entity.getDungLuong());
			return "Add thành công";
		} catch (Exception e) {
			return "Add thất bại";
		}
	}

	public String update(OCung entity) {
		try {
			XJdbc.update(update, entity.getKieuOCung(), entity.getDungLuong(), entity.getMaOCung());
			return "Update thành công";
		} catch (Exception e) {
			return "Update thất bại";
		}
	}

	public String delete(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public String deleteOCung(String maOCung) {
		try {
			XJdbc.update(delete, maOCung);
			return "Delete thành công";
		} catch (Exception e) {
			return "Delete thất bại";
		}
	}
	
	public OCung selectById(Integer id) {
		List<OCung> list = this.selectBySQL(selectByID, id);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	public List<OCung> selectAll() {
		return selectBySQL(selectAll_SQL);
	}

	public List<OCung> selectBySQL(String sql, Object... args) {
		List<OCung> list = new ArrayList<OCung>();
		try {
			ResultSet rs = XJdbc.query(sql, args);
			while (rs.next()) {
				OCung oCung = new OCung();
				oCung.setId(rs.getInt("ID"));
				oCung.setMaOCung(rs.getString("MaOCung"));
				oCung.setKieuOCung(rs.getString("KieuOCung"));
				oCung.setDungLuong(rs.getInt("DungLuong"));
				list.add(oCung);
			}
			rs.getStatement().getConnection().close();
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
