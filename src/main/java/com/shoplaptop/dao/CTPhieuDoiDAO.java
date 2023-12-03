package com.shoplaptop.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.shoplaptop.entity.CTPhieuDoi;
import com.shoplaptop.utils.XJdbc;

public class CTPhieuDoiDAO implements ShopLaptop365DAO<CTPhieuDoi, String> {
	String Insert_CTPhieuDoi = "INSERT dbo.CTPhieuDoi(ID_PhieuDoi,ID_Serial_Old,ID_Serial_New)VALUES(?,?,? )";
	String Update_CTPhieuDoi ="UPDATE dbo.CTPhieuDoi SET ID_Serial_New = ? WHERE ID_PhieuDoi = ?";
	String SelectCTPhieuDoi_SQL = "SELECT \r\n"
			+ "	dbo.CTPhieuDoi.ID,\r\n"
			+ "    dbo.PhieuDoi.ID AS 'ID_PhieuDoi', \r\n"
			+ "    dbo.PhieuDoi.MaPhieuDoi,\r\n"
			+ "    dbo.CTPhieuDoi.ID_Serial_Old AS 'ID_Serial_Old', \r\n"
			+ "    SerialOld.SerialNumber AS 'SerialNumber_Old',\r\n"
			+ "	   LapTop_Old.TenLaptop AS 'TenLapTop_Old',\r\n"
			+ "	   BienThe_Old.Gia AS 'Gia_Old',\r\n"
			+ "    dbo.CTPhieuDoi.ID_Serial_New AS 'ID_Serial_New', \r\n"
			+ "    SerialNew.SerialNumber AS 'SerialNumber_New',\r\n"
			+ "	LapTop_New.TenLaptop AS 'TenLapTop_New',\r\n"
			+ "	   BienThe_New.Gia AS 'Gia_New'\r\n"
			+ "FROM  \r\n"
			+ "    dbo.CTPhieuDoi \r\n"
			+ "JOIN \r\n"
			+ "    dbo.PhieuDoi ON PhieuDoi.ID = CTPhieuDoi.ID_PhieuDoi\r\n"
			+ "JOIN \r\n"
			+ "    dbo.Serial AS SerialOld ON SerialOld.ID = CTPhieuDoi.ID_Serial_Old\r\n"
			+ "JOIN \r\n"
			+ "    dbo.Serial AS SerialNew ON SerialNew.ID = CTPhieuDoi.ID_Serial_New\r\n"
			+ "JOIN \r\n"
			+ "    dbo.BienThe AS BienThe_Old ON BienThe_Old.ID = SerialOld.ID_BienThe\r\n"
			+ "JOIN \r\n"
			+ "	dbo.BienThe AS BienThe_New ON BienThe_New.ID = SerialNew.ID_BienThe\r\n"
			+ "JOIN \r\n"
			+ "	dbo.Laptop AS LapTop_Old ON LapTop_Old.ID = BienThe_Old.ID_Laptop\r\n"
			+ "JOIN \r\n"
			+ "	dbo.Laptop AS LapTop_New ON LapTop_New.ID = BienThe_New.ID_Laptop\r\n"
			+ "WHERE MaPhieuDoi = ?";
	
	String SelectCTPhieuDoi_Xuat = "SELECT \r\n"
			+ "	dbo.CTPhieuDoi.ID,\r\n"
			+ "    dbo.PhieuDoi.ID AS 'ID_PhieuDoi', \r\n"
			+ "    dbo.PhieuDoi.MaPhieuDoi,\r\n"
			+ "    dbo.CTPhieuDoi.ID_Serial_Old AS 'ID_Serial_Old', \r\n"
			+ "    SerialOld.SerialNumber AS 'SerialNumber_Old',\r\n"
			+ "	   LapTop_Old.TenLaptop AS 'TenLapTop_Old',\r\n"
			+ "	   BienThe_Old.Gia AS 'Gia_Old',\r\n"
			+ "    dbo.CTPhieuDoi.ID_Serial_New AS 'ID_Serial_New', \r\n"
			+ "    SerialNew.SerialNumber AS 'SerialNumber_New',\r\n"
			+ "	LapTop_New.TenLaptop AS 'TenLapTop_New',\r\n"
			+ "	   BienThe_New.Gia AS 'Gia_New'\r\n"
			+ "FROM  \r\n"
			+ "    dbo.CTPhieuDoi \r\n"
			+ "JOIN \r\n"
			+ "    dbo.PhieuDoi ON PhieuDoi.ID = CTPhieuDoi.ID_PhieuDoi\r\n"
			+ "JOIN \r\n"
			+ "    dbo.Serial AS SerialOld ON SerialOld.ID = CTPhieuDoi.ID_Serial_Old\r\n"
			+ "JOIN \r\n"
			+ "    dbo.Serial AS SerialNew ON SerialNew.ID = CTPhieuDoi.ID_Serial_New\r\n"
			+ "JOIN \r\n"
			+ "    dbo.BienThe AS BienThe_Old ON BienThe_Old.ID = SerialOld.ID_BienThe\r\n"
			+ "JOIN \r\n"
			+ "	dbo.BienThe AS BienThe_New ON BienThe_New.ID = SerialNew.ID_BienThe\r\n"
			+ "JOIN \r\n"
			+ "	dbo.Laptop AS LapTop_Old ON LapTop_Old.ID = BienThe_Old.ID_Laptop\r\n"
			+ "JOIN \r\n"
			+ "	dbo.Laptop AS LapTop_New ON LapTop_New.ID = BienThe_New.ID_Laptop\r\n";
	
	
	
	
	String Delete_CTPhieuDoi = "DELETE FROM dbo.CTPhieuDoi WHERE ID_PhieuDoi = ?";
	@Override
	public String insert(CTPhieuDoi ctPhieuDoi) {
		try {
			XJdbc.update(Insert_CTPhieuDoi,ctPhieuDoi.getID_PhieuDoi(), ctPhieuDoi.getID_Serial_Old(),ctPhieuDoi.getID_Serial_New());
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return null;
		
	}

	@Override
	public String update(CTPhieuDoi ctPhieuDoi) {
		try {
			XJdbc.update(Update_CTPhieuDoi, ctPhieuDoi.getID_Serial_New(),ctPhieuDoi.getID_PhieuDoi() );
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	
	public String delete(Integer ID_PhieuDoi) {
		try {
			XJdbc.update(Delete_CTPhieuDoi, ID_PhieuDoi );
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	@Override
	public CTPhieuDoi selectById(String maPhieuDoi) {

		return null;
	}

	@Override
	public List<CTPhieuDoi> selectAll() {
		
		return selectBySQL(SelectCTPhieuDoi_Xuat);
	}
	public List<CTPhieuDoi> selectAllCTPhieuDoiByMaPhieuDoi(String MaPhieuDoi) {
		return selectBySQL(SelectCTPhieuDoi_SQL, MaPhieuDoi);
	}
	

	@Override
	public List<CTPhieuDoi> selectBySQL(String sql, Object... args) {
		List<CTPhieuDoi> list = new ArrayList<CTPhieuDoi>();
		try {
			ResultSet rs = XJdbc.query(sql, args);
			while(rs.next()) {
				CTPhieuDoi ctPhieuDoi = new CTPhieuDoi();
				ctPhieuDoi.setID(rs.getInt("ID"));
				ctPhieuDoi.setID_PhieuDoi(rs.getInt("ID_PhieuDoi"));
				ctPhieuDoi.setMaPhieuDoi(rs.getString("MaPhieuDoi"));
				ctPhieuDoi.setID_Serial_Old(rs.getInt("ID_Serial_Old"));
				ctPhieuDoi.setSerialNumber_Old(rs.getString("SerialNumber_Old"));
				ctPhieuDoi.setTenLapCu(rs.getString("TenLapTop_Old"));
				ctPhieuDoi.setGiaCu(rs.getBigDecimal("Gia_Old"));
				ctPhieuDoi.setID_Serial_New(rs.getInt("ID_Serial_New"));
				ctPhieuDoi.setSerialNumber_New(rs.getString("SerialNumber_New"));
				ctPhieuDoi.setTenLapMoi(rs.getString("TenLapTop_New"));
				ctPhieuDoi.setGiaMoi(rs.getBigDecimal("Gia_New"));
				list.add(ctPhieuDoi);
			}
			rs.getStatement().getConnection().close();
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
	

	@Override
	public String delete(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
