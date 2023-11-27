package com.shoplaptop.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shoplaptop.entity.BienThe;
import com.shoplaptop.utils.XJdbc;

public class BienTheDAO implements ShopLaptop365DAO<BienThe, String> {

	String selectBienTheByMaLaptop = "SELECT dbo.BienThe.ID, dbo.BienThe.MaBienThe, \r\n"
			+ "dbo.Laptop.MaLaptop,dbo.CPU.ID AS [ID_CPU], (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU) AS [CPU], \r\n"
			+ "dbo.RAM.ID AS [ID_RAM], (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB ' + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz') AS [RAM], \r\n"
			+ "dbo.ManHinh.ID AS [ID_ManHinh], (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - ' + dbo.ManHinh.doPhanGiai) AS [Màn hình], \r\n"
			+ "dbo.GPU.ID AS [ID_GPU], (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang) AS [GPU], \r\n"
			+ "dbo.OCung.ID AS [ID_OCung], (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB') AS [Ổ cứng], \r\n"
			+ "MauSac, \r\n"
			+ "dbo.HeDieuHanh.ID AS [ID_HeDieuHanh], (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)') AS [Hệ điều hành], \r\n"
			+ "Gia, \r\n"
			+ "Hinh, \r\n"
			+ "COUNT(SerialNumber) AS [Số lượng]  \r\n"
			+ "FROM dbo.BienThe JOIN dbo.Laptop ON Laptop.ID = BienThe.ID_Laptop LEFT JOIN dbo.Serial ON Serial.ID_BienThe = BienThe.ID JOIN dbo.CPU ON CPU.ID = BienThe.CPU JOIN dbo.RAM ON RAM.ID = BienThe.RAM JOIN dbo.ManHinh ON ManHinh.ID = BienThe.ManHinh JOIN dbo.GPU ON GPU.ID = BienThe.GPU JOIN dbo.OCung ON OCung.ID = BienThe.OCung JOIN dbo.HeDieuHanh ON HeDieuHanh.ID = BienThe.HeDieuHanh \r\n"
			+ "WHERE dbo.BienThe.MaBienThe = ? \r\n"
			+ "GROUP BY (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU),\r\n"
			+ "         (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB '\r\n"
			+ "         + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz'\r\n"
			+ "         ),\r\n"
			+ "         (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - '\r\n"
			+ "         + dbo.ManHinh.doPhanGiai\r\n"
			+ "         ),\r\n"
			+ "         (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang),\r\n"
			+ "         (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB'),\r\n"
			+ "         (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)'),\r\n"
			+ "         BienThe.ID,\r\n"
			+ "			MaBienThe,\r\n"
			+ "         MaLaptop,\r\n"
			+ "         CPU.ID,\r\n"
			+ "         RAM.ID,\r\n"
			+ "         ManHinh.ID,\r\n"
			+ "         GPU.ID,\r\n"
			+ "         OCung.ID,\r\n"
			+ "         MauSac,\r\n"
			+ "         HeDieuHanh.ID,\r\n"
			+ "		 Gia,\r\n"
			+ "		 Hinh";

	String storedProcedureInsert = "{CALL InsertIntoBienThe(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )}";

	String updateBienThe = "UPDATE dbo.BienThe SET CPU = ?, RAM = ?, ManHinh = ?, GPU = ?, OCung = ?, MauSac = ?, HeDieuHanh = ?, Gia = ?, Hinh = ? WHERE MaBienThe = ? ";
	
	String deleteBienThe = "DELETE FROM BienThe WHERE MaBienThe = ?";
	
	public String insert(BienThe bienThe) {
		try {
			XJdbc.update(storedProcedureInsert, bienThe.getId_Laptop(), bienThe.getMaBienThe(), bienThe.getId_CPU(),
					bienThe.getId_RAM(), bienThe.getId_ManHinh(), bienThe.getId_GPU(), bienThe.getId_OCung(),
					bienThe.getMauSac(), bienThe.getId_HeDieuHanh(), bienThe.getGia(), bienThe.getHinh());
			return "Add thành công";
		} catch (SQLException e) {
			return "Biến thể đã tồn tại!";

		}
	}

	public String update(BienThe bienThe) {
		try {
			XJdbc.update(updateBienThe, bienThe.getId_CPU(),
					bienThe.getId_RAM(), bienThe.getId_ManHinh(), bienThe.getId_GPU(), bienThe.getId_OCung(),
					bienThe.getMauSac(), bienThe.getId_HeDieuHanh(), bienThe.getGia(), bienThe.getHinh(), bienThe.getMaBienThe());
			return "Update thành công";
		} catch (SQLException e) {
			return "Update thất bại";
		}
	}

	public String delete(String id) {
		try {
			XJdbc.update(deleteBienThe, id);
			return "Delete thành công";
		} catch (SQLException e) {
			return "Delete thất bại";
		}
	}

	public BienThe selectById(String id) {
		List<BienThe> list = this.selectBySQL(selectBienTheByMaLaptop, id);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	public List<BienThe> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<BienThe> selectBySQL(String sql, Object... args) {
		List<BienThe> list = new ArrayList<BienThe>();
		try {
			ResultSet rs = XJdbc.query(sql, args);
			while (rs.next()) {
				BienThe bienThe = new BienThe();
				bienThe.setId(rs.getInt("ID"));
				bienThe.setMaBienThe(rs.getString("MaBienThe"));
				bienThe.setMaLaptop(rs.getString("MaLaptop"));
				bienThe.setId_CPU(rs.getInt("ID_CPU"));
				bienThe.setCpu(rs.getString("CPU"));
				bienThe.setId_RAM(rs.getInt("ID_RAM"));
				bienThe.setRam(rs.getString("RAM"));
				bienThe.setId_ManHinh(rs.getInt("ID_ManHinh"));
				bienThe.setManHinh(rs.getString("Màn hình"));
				bienThe.setId_GPU(rs.getInt("ID_GPU"));
				bienThe.setGpu(rs.getString("GPU"));
				bienThe.setId_OCung(rs.getInt("ID_OCung"));
				bienThe.setoCung(rs.getString("Ổ cứng"));
				bienThe.setMauSac(rs.getString("MauSac"));
				bienThe.setId_HeDieuHanh(rs.getInt("ID_HeDieuHanh"));
				bienThe.setHeDieuHanh(rs.getString("Hệ điều hành"));
				bienThe.setGia(rs.getBigDecimal("Gia"));
				bienThe.setSoLuong(rs.getInt("Số lượng"));
				bienThe.setHinh(rs.getString("Hinh"));
				list.add(bienThe);
			}
			rs.getStatement().getConnection().close();
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
