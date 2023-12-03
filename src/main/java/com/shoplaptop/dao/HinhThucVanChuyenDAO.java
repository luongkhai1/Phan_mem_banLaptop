package com.shoplaptop.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.shoplaptop.entity.HinhThucVanChuyen;
import com.shoplaptop.utils.XJdbc;

public class HinhThucVanChuyenDAO implements ShopLaptop365DAO<HinhThucVanChuyen, String> {
	String sellectall = "SELECT * FROM dbo.HinhThucVanChuyen";

	@Override
	public String insert(HinhThucVanChuyen entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String update(HinhThucVanChuyen entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HinhThucVanChuyen selectById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HinhThucVanChuyen> selectAll() {

		return this.selectBySQL(sellectall);

	}

	@Override
	public List<HinhThucVanChuyen> selectBySQL(String sql, Object... args) {
		List<HinhThucVanChuyen> list = new ArrayList<HinhThucVanChuyen>();
		try {
			ResultSet rs = XJdbc.query(sql, args);
			while (rs.next()) {
				int id = rs.getInt("ID");
				String hinhthuc = rs.getString("HinhThuc");
				String donvi = rs.getString("DonVi");
				BigDecimal giavc = rs.getBigDecimal("GiaVC");
				HinhThucVanChuyen hinhthucvanchuyen = new HinhThucVanChuyen(id, hinhthuc, donvi, giavc);
				list.add(hinhthucvanchuyen);
			}
			rs.getStatement().getConnection().close();
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}		
	}

}
