package com.shoplaptop.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shoplaptop.entity.NhaCungCap;
import com.shoplaptop.utils.XJdbc;

public class NhaCungCapDAO implements ShopLaptop365DAO<NhaCungCap, String> {
	
	String selectAll = "SELECT * FROM NhaCungCap";
	String insertNCC = "INSERT INTO NhaCungCap(MaNCC, TenNCC, NguoiPhuTrach, SoDienThoai, Email, DiaChi) VALUES (?,?,?,?,?,?)";
	String updateNCC = "UPDATE NhaCungCap SET TenNCC = ?, NguoiPhuTrach = ?, SoDienThoai = ?, Email = ?, DiaChi = ? WHERE MaNCC = ?";
	String deleteNCC = "DELETE FROM NhaCungCap WHERE MaNCC = ?";
	String selectByID = "SELECT * FROM NhaCungCap WHERE MaNCC = ?";
	
	public String insert(NhaCungCap entity) {
		try {
			XJdbc.update(insertNCC, entity.getMaNCC(), entity.getTenNCC(), entity.getNguoiPhuTrach(),
					entity.getSoDienThoai(), entity.getEmail(), entity.getDiaChi());
			return "Add thành công";
		} catch (SQLException e) {
			return "Add thất bại!";
		}
	}

	public String update(NhaCungCap entity) {
		try {
			XJdbc.update(updateNCC, entity.getTenNCC(), entity.getNguoiPhuTrach(),
					entity.getSoDienThoai(), entity.getEmail(), entity.getDiaChi(), entity.getMaNCC());
			return "Update thành công";
		} catch (SQLException e) {
			return "Update thất bại!";

		}
	}

	public String delete(String id) {
		try {
			XJdbc.update(deleteNCC, id);
			return "Delete thành công";
		} catch (SQLException e) {
			return "Delete thất bại!";

		}
	}

	public NhaCungCap selectById(String id) {
		List<NhaCungCap> list = this.selectBySQL(selectByID, id);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	public List<NhaCungCap> selectAll() {
		return selectBySQL(selectAll);
	}

	public List<NhaCungCap> selectBySQL(String sql, Object... args) {
		List<NhaCungCap> list = new ArrayList<NhaCungCap>();
		try {
			ResultSet rs = XJdbc.query(sql, args);
			while (rs.next()) {
				NhaCungCap nhaCungCap = new NhaCungCap();
				nhaCungCap.setMaNCC(rs.getString("MaNCC"));
				nhaCungCap.setTenNCC(rs.getString("TenNCC"));
				nhaCungCap.setNguoiPhuTrach(rs.getString("NguoiPhuTrach"));
				nhaCungCap.setSoDienThoai(rs.getString("SoDienThoai"));
				nhaCungCap.setEmail(rs.getString("Email"));
				nhaCungCap.setDiaChi(rs.getString("DiaChi"));
				list.add(nhaCungCap);
			}
			rs.getStatement().getConnection().close();
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
