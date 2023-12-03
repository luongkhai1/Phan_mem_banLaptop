package com.shoplaptop.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.shoplaptop.entity.DotGiamGia;
import com.shoplaptop.utils.XDate;
import com.shoplaptop.utils.XJdbc;

public class DotGiamGiaDAO implements ShopLaptop365DAO<DotGiamGia, String>{
	
	String selectDG = "SELECT * FROM DotGiamGia WHERE Han >= GETDATE() AND DieuKienHoaDon <= ? ORDER BY GiaGiam DESC ";

	@Override
	public String insert(DotGiamGia entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String update(DotGiamGia entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DotGiamGia selectById(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public DotGiamGia selectDGG(BigDecimal dkHoaDon) {
		List<DotGiamGia> list = this.selectBySQL(selectDG, dkHoaDon);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<DotGiamGia> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DotGiamGia> selectBySQL(String sql, Object... args) {
		List<DotGiamGia> list = new ArrayList<DotGiamGia>();
		try {
			ResultSet rs = XJdbc.query(sql, args);
			while (rs.next()) {
				DotGiamGia dotGiamGia = new DotGiamGia();
				dotGiamGia.setMa(rs.getString("MaDG"));
				dotGiamGia.setTen(rs.getString("TenDG"));
				dotGiamGia.setHan(XDate.toDate(rs.getString("Han"), "yyyy-MM-dd HH:mm:ss"));
				dotGiamGia.setGiaGiam(rs.getBigDecimal("GiaGiam"));
				dotGiamGia.setDieuKien(rs.getBigDecimal("DieuKienHoaDon"));
				dotGiamGia.setMoTa(rs.getString("MoTa"));
				list.add(dotGiamGia);
			}
			rs.getStatement().getConnection().close();
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
