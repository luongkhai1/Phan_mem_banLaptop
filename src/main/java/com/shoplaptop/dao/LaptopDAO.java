package com.shoplaptop.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shoplaptop.entity.Laptop;
import com.shoplaptop.utils.XJdbc;

public class LaptopDAO implements ShopLaptop365DAO<Laptop, String>{
	
	String selectAll_SQL = "SELECT Laptop.ID, MaLaptop, TenLaptop, PhanLoai.ID AS PhanLoai, PhanLoai.Tenloai, Hang.ID AS Hang, Hang.TenHang, DongLaptop.ID AS DongLaptop, DongLaptop.TenDong, NamSanXuat  FROM Laptop JOIN PhanLoai ON Laptop.PhanLoai = PhanLoai.ID JOIN DongLaptop ON Laptop.DongLaptop = DongLaptop.ID JOIN Hang ON Hang.ID = DongLaptop.Hang";
	String insertLaptop = "INSERT INTO Laptop(MaLaptop, TenLaptop, PhanLoai, DongLaptop, NamSanXuat) VALUES (?,?,?,?,?)";
	String updateLaptop = "UPDATE Laptop SET TenLaptop = ?, PhanLoai = ?, DongLaptop = ?, NamSanXuat = ? WHERE MaLaptop = ?";
	String deleteLaptop = "DELETE FROM Laptop WHERE MaLaptop = ?";
	String selectByID_SQL = "SELECT Laptop.ID, MaLaptop, TenLaptop, PhanLoai.ID AS PhanLoai, PhanLoai.Tenloai, Hang.ID AS Hang, Hang.TenHang, DongLaptop.ID AS DongLaptop, DongLaptop.TenDong, NamSanXuat  FROM Laptop JOIN PhanLoai ON Laptop.PhanLoai = PhanLoai.ID JOIN DongLaptop ON Laptop.DongLaptop = DongLaptop.ID JOIN Hang ON Hang.ID = DongLaptop.Hang WHERE MaLaptop = ?";
	String selectLaptop = "SELECT * FROM\r\n"
			+ "    (SELECT ROW_NUMBER() OVER (ORDER BY MaLaptop DESC) AS rownum, Laptop.ID, MaLaptop, TenLaptop, PhanLoai.ID AS PhanLoai, PhanLoai.Tenloai, Hang.ID AS Hang, Hang.TenHang, DongLaptop.ID AS DongLaptop, DongLaptop.TenDong, NamSanXuat  FROM Laptop JOIN PhanLoai ON Laptop.PhanLoai = PhanLoai.ID JOIN DongLaptop ON Laptop.DongLaptop = DongLaptop.ID JOIN Hang ON Hang.ID = DongLaptop.Hang)\r\n"
			+ "    AS temp\r\n"
			+ "    WHERE rownum BETWEEN ? AND ?";
	String selectByLaptop = "";
	
	public String insert(Laptop laptop) {
		try {
			XJdbc.update(insertLaptop,  laptop.getMaLaptop(),  laptop.getTenLaptop(),  laptop.getPhanLoai(),  laptop.getDongLaptop(),  laptop.getNamSanXuat());
			return "Add thành công";
		} catch (SQLException e) {
			return "Add thất bại";
			
		}
	}

	public String update(Laptop laptop) {
		try {
			XJdbc.update(updateLaptop,  laptop.getTenLaptop(),  laptop.getPhanLoai(),  laptop.getDongLaptop(),  laptop.getNamSanXuat(),  laptop.getMaLaptop());
			return "Update thành công";
		} catch (SQLException e) {
			return "Update thất bại";
			
		}
	}

	public String delete(String string) {
		try {
			XJdbc.update(deleteLaptop,  string);
			return "Xóa thành công";
		} catch (SQLException e) {
			return "Xóa thất bại";
			
		}
	}

	public Laptop selectById(String id) {
		List<Laptop> list = this.selectBySQL(selectByID_SQL, id);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	public List<Laptop> selectAll() {
		return selectBySQL(selectAll_SQL);
	}
	
	public List<Laptop> selectAllLaptop(int count) {
		return selectBySQL(selectLaptop,count,count+7);
	}
	
	public List<Laptop> selectBySQL(String sql, Object... args) {
		List<Laptop> list = new ArrayList<Laptop>();
		try {
			ResultSet rs = XJdbc.query(sql, args);
			while (rs.next()) {
				Laptop laptop = new Laptop();
				laptop.setId(rs.getInt("ID"));
				laptop.setMaLaptop(rs.getString("MaLaptop"));
				laptop.setTenLaptop(rs.getString("TenLaptop"));
				laptop.setPhanLoai(rs.getInt("PhanLoai"));
				laptop.setTenLoai(rs.getString("TenLoai"));
				laptop.setHang(rs.getInt("Hang"));
				laptop.setTenHang(rs.getString("TenHang"));
				laptop.setDongLaptop(rs.getInt("DongLaptop"));
				laptop.setTenDong(rs.getString("TenDong"));
				laptop.setNamSanXuat(rs.getInt("NamSanXuat"));
				list.add(laptop);
			}
			rs.getStatement().getConnection().close();
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
