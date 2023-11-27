package com.shoplaptop.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.shoplaptop.entity.GPU;
import com.shoplaptop.utils.XJdbc;

public class GPUDAO implements ShopLaptop365DAO<GPU, Integer> {
	
	String selectAll_SQL = "SELECT * FROM GPU";
	String selectByID = "SELECT * FROM GPU WHERE ID = ?";
	String insertGPU = "INSERT INTO GPU(MaGPU, LoaiCard, Hang) \r\n"
			+ "VALUES (? ,? ,?)";
	String updateGPU = "UPDATE GPU SET LoaiCard = ?, Hang = ? WHERE MaGPU = ?";
	String deleteGPU = "DELETE FROM GPU WHERE MaGPU = ?";

	public String insert(GPU entity) {
		try {
			XJdbc.update(insertGPU, entity.getMaGPU(), entity.getLoaiCard(), entity.getHang());
			return "Add thành công";
		} catch (Exception e) {
			return "Add thất bại";
		}
	}

	public String update(GPU entity) {
		try {
			XJdbc.update(updateGPU, entity.getLoaiCard(), entity.getHang(), entity.getMaGPU());
			return "Update thành công";
		} catch (Exception e) {
			return "Update thất bại";
		}
	}

	public String delete(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String deleteGPU(String maGPU) {
		try {
			XJdbc.update(deleteGPU, maGPU);
			return "Delete thành công";
		} catch (Exception e) {
			return "Delete thất bại";
		}
	}

	public GPU selectById(Integer id) {
		List<GPU> list = this.selectBySQL(selectByID, id);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	public List<GPU> selectAll() {
		return selectBySQL(selectAll_SQL);
	}

	public List<GPU> selectBySQL(String sql, Object... args) {
		List<GPU> list = new ArrayList<GPU>();
		try {
			ResultSet rs = XJdbc.query(sql, args);
			while (rs.next()) {
				GPU doHoa = new GPU();
				doHoa.setId(rs.getInt("ID"));
				doHoa.setMaGPU(rs.getString("MaGPU"));
				doHoa.setLoaiCard(rs.getString("LoaiCard"));
				doHoa.setHang(rs.getString("Hang"));
				list.add(doHoa);
			}
			rs.getStatement().getConnection().close();
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
