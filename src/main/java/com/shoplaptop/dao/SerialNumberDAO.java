package com.shoplaptop.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shoplaptop.entity.SerialNumber;
import com.shoplaptop.utils.XJdbc;

public class SerialNumberDAO implements ShopLaptop365DAO<SerialNumber, String>{

	String insertSerial = "INSERT INTO Serial(ID_BienThe, SerialNumber, TrangThai) \r\n"
			+ "VALUES (?, ?, ?)";
	
	String selectBySerialNumber = "SELECT Serial.ID, ID_BienThe, TenLaptop, dbo.BienThe.MaBienThe, SerialNumber, Gia, TrangThai \r\n"
			+ "FROM dbo.Serial JOIN dbo.BienThe ON BienThe.ID = Serial.ID_BienThe JOIN dbo.Laptop ON Laptop.ID = BienThe.ID_Laptop \r\n"
			+ "WHERE SerialNumber = ?";
	
	String selectAllSerial = "SELECT Serial.ID, ID_BienThe, TenLaptop, dbo.BienThe.MaBienThe, SerialNumber, Gia, TrangThai \r\n"
			+ "FROM dbo.Serial JOIN dbo.BienThe ON BienThe.ID = Serial.ID_BienThe JOIN dbo.Laptop ON Laptop.ID = BienThe.ID_Laptop \r\n"
			+ "WHERE TrangThai = 1";
	
	@Override
	public String insert(SerialNumber entity) {
		try {
			XJdbc.update(insertSerial, entity.getId_BienThe(), entity.getSerialNumber(), entity.isTrangThai());
			return "Add thành công";
		} catch (SQLException e) {
			return "Serial đã tồn tại";

		}
	}

	@Override
	public String update(SerialNumber entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SerialNumber selectById(String id) {
		List<SerialNumber> list = this.selectBySQL(selectBySerialNumber, id);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<SerialNumber> selectAll() {
		return selectBySQL(selectAllSerial);
	}

	@Override
	public List<SerialNumber> selectBySQL(String sql, Object... args) {
		List<SerialNumber> list = new ArrayList<SerialNumber>();
		try {
			ResultSet rs = XJdbc.query(sql, args);
			while (rs.next()) {
				SerialNumber serialNumber = new SerialNumber();
				serialNumber.setId(rs.getInt("ID"));
				serialNumber.setId_BienThe(rs.getInt("ID_BienThe"));
				serialNumber.setTenLaptop(rs.getString("TenLaptop"));
				serialNumber.setMaBienThe(rs.getString("MaBienThe"));
				serialNumber.setSerialNumber(rs.getString("SerialNumber"));
				serialNumber.setGia(rs.getBigDecimal("Gia"));
				serialNumber.setTrangThai(rs.getBoolean("TrangThai"));
				list.add(serialNumber);
			}
			rs.getStatement().getConnection().close();
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
