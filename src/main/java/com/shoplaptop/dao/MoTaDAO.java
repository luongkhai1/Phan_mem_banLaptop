package com.shoplaptop.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shoplaptop.entity.MoTa;
import com.shoplaptop.utils.XJdbc;

public class MoTaDAO implements ShopLaptop365DAO<MoTa, String>{
	String selectMota = "SELECT dbo.Laptop.MaLaptop, SoLuongLoa, Keyboard, TouchPad, CongKetNoi, Wifi, Bluetooth, Webcam, SoLuongQuat, TrongLuong, Pin FROM dbo.MoTa JOIN dbo.Laptop ON Laptop.ID = MoTa.MaLaptop WHERE dbo.Laptop.MaLaptop = ?";
	String insertMota = "INSERT INTO MoTa(MaLaptop, SoLuongLoa, Keyboard, TouchPad, CongKetNoi, Wifi, Bluetooth, Webcam, SoLuongQuat, TrongLuong, Pin) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	String updateMota = "UPDATE dbo.MoTa SET SoLuongLoa = ?, Keyboard = ?, TouchPad = ?, CongKetNoi = ?, Wifi = ?, Bluetooth = ?, Webcam = ?, SoLuongQuat = ?, TrongLuong = ?, Pin = ? WHERE MaLaptop = ?  ";
	
	public String insert(MoTa entity) {
		try {
			XJdbc.update(insertMota,  entity.getId_MaLaptop(), entity.getSoLuongLoa(), entity.getKeyBoard(), entity.getTouchPad(), entity.getCongKetNoi(), entity.getWifi(), entity.getBluetooth(), entity.getWebcam(), entity.getSoLuongQuat(), entity.getTrongLuong(), entity.getPin());
			return "Add thành công";
		} catch (SQLException e) {
			return "Add thất bại";
			
		}
	}

	public String update(MoTa entity) {
		try {
			XJdbc.update(updateMota, entity.getSoLuongLoa(), entity.getKeyBoard(), entity.getTouchPad(), entity.getCongKetNoi(), entity.getWifi(), entity.getBluetooth(), entity.getWebcam(), entity.getSoLuongQuat(), entity.getTrongLuong(), entity.getPin(),  entity.getId_MaLaptop());
			return "Add thành công";
		} catch (SQLException e) {
			return "Add thất bại";
			
		}
	}

	public String delete(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public MoTa selectById(String id) {
		List<MoTa> list = this.selectBySQL(selectMota, id);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	public List<MoTa> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<MoTa> selectBySQL(String sql, Object... args) {
		List<MoTa> list = new ArrayList<MoTa>();
		try {
			ResultSet rs = XJdbc.query(sql, args);
			while (rs.next()) {
				MoTa moTa = new MoTa();
				moTa.setMaLaptop(rs.getString("MaLaptop"));
				moTa.setSoLuongLoa(rs.getInt("SoLuongLoa"));
				moTa.setKeyBoard(rs.getString("Keyboard"));
				moTa.setTouchPad(rs.getString("TouchPad"));
				moTa.setCongKetNoi(rs.getString("CongKetNoi"));
				moTa.setWifi(rs.getString("Wifi"));
				moTa.setBluetooth(rs.getString("Bluetooth"));
				moTa.setWebcam(rs.getString("Webcam"));
				moTa.setSoLuongQuat(rs.getInt("SoLuongQuat"));
				moTa.setTrongLuong(rs.getDouble("TrongLuong"));
				moTa.setPin(rs.getString("Pin"));
				list.add(moTa);
			}
			rs.getStatement().getConnection().close();
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
