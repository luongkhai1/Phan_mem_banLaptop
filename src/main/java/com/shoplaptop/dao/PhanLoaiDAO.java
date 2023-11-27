package com.shoplaptop.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.shoplaptop.entity.PhanLoai;
import com.shoplaptop.utils.XJdbc;

public class PhanLoaiDAO implements ShopLaptop365DAO<PhanLoai, Integer> {
	
	String selectAll_SQL = "SELECT * FROM PhanLoai";
	String selectByID = "SELECT * FROM PhanLoai WHERE ID = ?";
	String insert = "INSERT INTO PhanLoai(MaLoai, TenLoai, MoTa) \r\n"
			+ "VALUES (?, ?, ?)";
	String update = "UPDATE PhanLoai SET TenLoai = ?, MoTa = ? WHERE MaLoai = ?";
	String delete = "DELETE FROM PhanLoai WHERE MaLoai = ?";

	public String insert(PhanLoai entity) {
		try {
			XJdbc.update(insert, entity.getMaLoai(), entity.getTenLoai(), entity.getMoTa());
			return "Add thành công";
		} catch (Exception e) {
			return "Add thất bại";
		}
	}

	public String update(PhanLoai entity) {
		try {
			XJdbc.update(update, entity.getTenLoai(), entity.getMoTa(), entity.getMaLoai());
			return "Update thành công";
		} catch (Exception e) {
			return "Update thất bại";
		}
	}

	public String delete(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public String deletePL(String maLoai) {
		try {
			XJdbc.update(delete, maLoai);
			return "Delete thành công";
		} catch (Exception e) {
			return "Delete thất bại";
		}
	}
	
	public PhanLoai selectById(Integer id) {
		List<PhanLoai> list = this.selectBySQL(selectByID, id);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	public List<PhanLoai> selectAll() {
		return selectBySQL(selectAll_SQL);
	}

	public List<PhanLoai> selectBySQL(String sql, Object... args) {
		List<PhanLoai> list = new ArrayList<PhanLoai>();
		try {
			ResultSet rs = XJdbc.query(sql, args);
			while (rs.next()) {
				PhanLoai phanLoai = new PhanLoai();
				phanLoai.setId(rs.getInt("ID"));
				phanLoai.setMaLoai(rs.getString("MaLoai"));
				phanLoai.setTenLoai(rs.getString("TenLoai"));
				phanLoai.setMoTa(rs.getString("MoTa"));
				list.add(phanLoai);
			}
			rs.getStatement().getConnection().close();
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
