package com.shoplaptop.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.shoplaptop.entity.ManHinh;
import com.shoplaptop.utils.XJdbc;

public class ManHinhDAO implements ShopLaptop365DAO<ManHinh, Integer> {
	
	String selectAll_SQL = "SELECT * FROM ManHinh";
	String selectByID = "SELECT * FROM ManHinh WHERE ID = ?";
	String insert = "INSERT INTO ManHinh(MaManHinh, KichThuocManHinh, CongNgheManHinh, DoPhanGiai, TanSoQuet, TamNen, DoSang, DoPhuMau, TiLemanHinh)\r\n"
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	String update = "UPDATE ManHinh SET KichThuocManHinh = ?, CongNgheManHinh = ?, DoPhanGiai = ?, TanSoQuet = ?, TamNen = ?, DoSang = ?, DoPhuMau = ?, TiLemanHinh = ? WHERE MaManHinh = ?";
	String delete = "DELETE FROM ManHinh WHERE MaManHinh = ?";
	
	public String insert(ManHinh entity) {
		try {
			XJdbc.update(insert, entity.getMaManHinh(), entity.getKichThuocManHinh(), entity.getCongNgheManHinh(), entity.getDoPhanGiai(), entity.getTanSoQuet(), entity.getTamNen(), entity.getDoSang(), entity.getDoPhuMau(), entity.getTiLeManHinh());
			return "Add thành công";
		} catch (Exception e) {
			return "Add thất bại";
		}
	}

	public String update(ManHinh entity) {
		try {
			XJdbc.update(update, entity.getKichThuocManHinh(), entity.getCongNgheManHinh(), entity.getDoPhanGiai(), entity.getTanSoQuet(), entity.getTamNen(), entity.getDoSang(), entity.getDoPhuMau(), entity.getTiLeManHinh(), entity.getMaManHinh());
			return "Update thành công";
		} catch (Exception e) {
			return "Update thất bại";
		}
	}

	public String delete(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String deleteManHinh(String maManHinh) {
		try {
			XJdbc.update(delete, maManHinh);
			return "Delete thành công";
		} catch (Exception e) {
			return "Delete thất bại";
		}
	}

	public ManHinh selectById(Integer id) {
		List<ManHinh> list = this.selectBySQL(selectByID, id);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	public List<ManHinh> selectAll() {
		return selectBySQL(selectAll_SQL);
	}

	public List<ManHinh> selectBySQL(String sql, Object... args) {
		List<ManHinh> list = new ArrayList<ManHinh>();
		try {
			ResultSet rs = XJdbc.query(sql, args);
			while (rs.next()) {
				ManHinh manHinh = new ManHinh();
				manHinh.setId(rs.getInt("ID"));
				manHinh.setMaManHinh(rs.getString("maManHinh"));
				manHinh.setKichThuocManHinh(rs.getDouble("KichThuocManHinh"));
				manHinh.setCongNgheManHinh(rs.getString("CongNgheManHinh"));
				manHinh.setDoPhanGiai(rs.getString("DoPhanGiai"));
				manHinh.setTanSoQuet(rs.getInt("TanSoQuet"));
				manHinh.setTamNen(rs.getString("TamNen"));
				manHinh.setDoSang(rs.getInt("DoSang"));
				manHinh.setDoPhuMau(rs.getString("DoPhuMau"));
				manHinh.setTiLeManHinh(rs.getString("TiLeManHinh"));
				list.add(manHinh);
			}
			rs.getStatement().getConnection().close();
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
