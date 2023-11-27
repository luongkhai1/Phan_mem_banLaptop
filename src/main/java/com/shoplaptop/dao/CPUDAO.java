package com.shoplaptop.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shoplaptop.entity.CPU;
import com.shoplaptop.utils.XJdbc;

public class CPUDAO implements ShopLaptop365DAO<CPU, Integer>{

	String selectAll_SQL = "SELECT * FROM CPU";
	String selectByID = "SELECT * FROM CPU WHERE ID = ?";
	String insertCPU = "INSERT INTO CPU(MaCPU, HangCPU, CongNghe, LoaiCPU, TocDoCPU, TocDoToiDa, SoNhan, SoLuong, BoNhoDem) \r\n"
			+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
	String updateCPU = "UPDATE CPU SET HangCPU = ?, CongNghe = ?, LoaiCPU = ?, TocDoCPU = ?, TocDoToiDa = ?, SoNhan = ?, SoLuong = ?, BoNhoDem = ? WHERE MaCPU = ?";
	String deleteCPU = "DELETE FROM CPU WHERE MaCPU = ?";
	
	public String insert(CPU entity) {
		try {
			XJdbc.update(insertCPU, entity.getMaCPU(), entity.getHangCPU(), entity.getCongNghe(), entity.getLoaiCPU(), entity.getTocDoCPU(), entity.getTocDoToiDa(), entity.getSoNhan(), entity.getSoLuong(), entity.getBoNhoDem());
			return "Add thành công";
		} catch (SQLException e) {
			return "Add thất bại";
		}
	}

	public String update(CPU entity) {
		try {
			XJdbc.update(updateCPU, entity.getHangCPU(), entity.getCongNghe(), entity.getLoaiCPU(), entity.getTocDoCPU(), entity.getTocDoToiDa(), entity.getSoNhan(), entity.getSoLuong(), entity.getBoNhoDem(), entity.getMaCPU());
			return "Update thành công";
		} catch (SQLException e) {
			return "Update thất bại";
		}
	}

	public String delete(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String deleteByMa(String maCPU) {
		try {
			XJdbc.update(deleteCPU, maCPU);
			return "Delete thành công";
		} catch (SQLException e) {
			return "Delete thất bại";
		}
		
	}

	public CPU selectById(Integer id) {
		List<CPU> list = this.selectBySQL(selectByID, id);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	public List<CPU> selectAll() {
		return selectBySQL(selectAll_SQL);
	}

	public List<CPU> selectBySQL(String sql, Object... args) {
		List<CPU> list = new ArrayList<CPU>();
		try {
			ResultSet rs = XJdbc.query(sql, args);
			while (rs.next()) {
				CPU cpu = new CPU();
				cpu.setId(rs.getInt("ID"));
				cpu.setMaCPU(rs.getString("MaCPU"));
				cpu.setHangCPU(rs.getString("HangCPU"));
				cpu.setCongNghe(rs.getString("CongNghe"));
				cpu.setLoaiCPU(rs.getString("LoaiCPU"));
				cpu.setTocDoCPU(rs.getDouble("TocDoCPU"));
				cpu.setTocDoToiDa(rs.getDouble("TocDoToiDa"));
				cpu.setSoNhan(rs.getInt("SoNhan"));
				cpu.setSoLuong(rs.getInt("SoLuong"));
				cpu.setBoNhoDem(rs.getInt("BoNhoDem"));
				list.add(cpu);
			}
			rs.getStatement().getConnection().close();
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
