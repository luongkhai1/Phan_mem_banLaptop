package com.shoplaptop.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.shoplaptop.entity.RAM;
import com.shoplaptop.utils.XJdbc;

public class RAMDAO implements ShopLaptop365DAO<RAM, Integer> {

	String selectAll_SQL = "SELECT * FROM RAM";
	String selectByID = "SELECT * FROM RAM WHERE ID = ?";
	String insert = "INSERT INTO RAM(MaRAM, LoaiRAM, DungLuong, TocDoRAM) \r\n"
			+ "VALUES (?, ?, ?, ?)";
	String update = "UPDATE RAM SET LoaiRAM = ?, DungLuong = ?, TocDoRAM = ? WHERE MaRAM = ?";
	String delete = "DELETE FROM RAM SET MaRAM = ?";
	
	public String insert(RAM entity) {
		try {
			XJdbc.update(insert, entity.getMaRAM(), entity.getLoaiRAM(), entity.getDungLuong(), entity.getTocDoRAM());
			return "Add thành công";
		} catch (Exception e) {
			return "Add thất bại";
		}
	}

	public String update(RAM entity) {
		try {
			XJdbc.update(insert, entity.getLoaiRAM(), entity.getDungLuong(), entity.getTocDoRAM(), entity.getMaRAM());
			return "Update thành công";
		} catch (Exception e) {
			return "Update thất bại";
		}
	}

	public String delete(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String deleteRAM(String maRAM) {
		try {
			XJdbc.update(delete, maRAM);
			return "Delete thành công";
		} catch (Exception e) {
			return "Delete thất bại";
		}
	}

	public RAM selectById(Integer id) {
		List<RAM> list = this.selectBySQL(selectByID, id);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	public List<RAM> selectAll() {
		return selectBySQL(selectAll_SQL);
	}

	public List<RAM> selectBySQL(String sql, Object... args) {
		List<RAM> list = new ArrayList<RAM>();
		try {
			ResultSet rs = XJdbc.query(sql, args);
			while (rs.next()) {
				RAM ram = new RAM();
				ram.setId(rs.getInt("ID"));
				ram.setMaRAM(rs.getString("MaRAM"));
				ram.setLoaiRAM(rs.getString("LoaiRAM"));
				ram.setDungLuong(rs.getInt("DungLuong"));
				ram.setTocDoRAM(rs.getInt("TocDoRAM"));
				list.add(ram);
			}
			rs.getStatement().getConnection().close();
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
