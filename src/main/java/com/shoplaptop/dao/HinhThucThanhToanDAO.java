package com.shoplaptop.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.shoplaptop.entity.HinhThucThanhToan;
import com.shoplaptop.utils.XJdbc;

public class HinhThucThanhToanDAO implements ShopLaptop365DAO<HinhThucThanhToan, String> {

	String sellectall = "SELECT * FROM dbo.HinhThucThanhToan";

	@Override
	public String insert(HinhThucThanhToan entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String update(HinhThucThanhToan entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HinhThucThanhToan selectById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HinhThucThanhToan> selectAll() {
		return this.selectBySQL(sellectall);

	}

	@Override
	public List<HinhThucThanhToan> selectBySQL(String sql, Object... args) {
		List<HinhThucThanhToan> list = new ArrayList<HinhThucThanhToan>();
		try {
			ResultSet rs = XJdbc.query(sql, args);
			while (rs.next()) {
				int id = rs.getInt("ID");
				String HinhThuc = rs.getString("HinhThuc");
				HinhThucThanhToan hinhthucthanhtoan = new HinhThucThanhToan(id, HinhThuc);
				list.add(hinhthucthanhtoan);
			}
			rs.getStatement().getConnection().close();
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

}