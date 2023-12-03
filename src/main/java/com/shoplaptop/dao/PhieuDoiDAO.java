package com.shoplaptop.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.shoplaptop.entity.PhieuDoi;
import com.shoplaptop.utils.XDate;
import com.shoplaptop.utils.XJdbc;

public class PhieuDoiDAO implements ShopLaptop365DAO<PhieuDoi, String>{
		
	String Insert_PhieuDoi = "INSERT dbo.PhieuDoi(MaPhieuDoi,MaKH,MaHD,MaNV,NgayTao,LiDo)VALUES(?,?,?,?,?,?)";
	String Update_PhieuDoi = "UPDATE dbo.PhieuDoi SET LiDo = ? WHERE MaPhieuDoi = ?";
	String Delete_SQL = "DELETE FROM dbo.PhieuDoi WHERE MaPhieuDoi = ?";
//	String SelectCTPhieuDoi_SQL = "SELECT * FROM dbo.CTPhieuDoi";
	
	String SelectPhieuDoi_SQL = "SELECT * FROM dbo.PhieuDoi"; 
	
	String SelectPhieuDoiByMaPhieuDoi = "SELECT dbo.PhieuDoi.ID, dbo.PhieuDoi.MaPhieuDoi, dbo.PhieuDoi.MaKH,dbo.KhachHang.HoTen AS 'HoTenKhachHang',\r\n"
			+ "	dbo.HoaDon.MaHD,dbo.PhieuDoi.MaNV, dbo.NhanVien.HoTen AS 'HoTenNhanVien',dbo.PhieuDoi.NgayTao,dbo.PhieuDoi.LiDo\r\n"
			+ "	FROM dbo.PhieuDoi JOIN dbo.KhachHang ON KhachHang.MaKH = PhieuDoi.MaKH\r\n"
			+ "	JOIN dbo.NhanVien ON NhanVien.MaNV = PhieuDoi.MaNV\r\n"
			+ "	JOIN HoaDon ON HoaDon.ID = PhieuDoi.MaHD WHERE MaPhieuDoi = ?";
	String SelectPhieuDoiSetForm = "SELECT dbo.PhieuDoi.ID, dbo.PhieuDoi.MaPhieuDoi, dbo.PhieuDoi.MaKH,dbo.KhachHang.HoTen AS 'HoTenKhachHang',\r\n"
			+ "	dbo.HoaDon.MaHD,dbo.PhieuDoi.MaNV, dbo.NhanVien.HoTen AS 'HoTenNhanVien',dbo.PhieuDoi.NgayTao,dbo.PhieuDoi.LiDo\r\n"
			+ "	FROM dbo.PhieuDoi JOIN dbo.KhachHang ON KhachHang.MaKH = PhieuDoi.MaKH\r\n"
			+ "	JOIN dbo.NhanVien ON NhanVien.MaNV = PhieuDoi.MaNV\r\n"
			+ "	JOIN HoaDon ON HoaDon.ID = PhieuDoi.MaHD";
	@Override
	public String insert(PhieuDoi phieuDoi) {
		try {
			XJdbc.update(Insert_PhieuDoi, phieuDoi.getMaPhieuDoi(),phieuDoi.getMaKH(),phieuDoi.getID_HoaDon(),phieuDoi.getMaNV(),phieuDoi.getNgayTao(),phieuDoi.getLiDo());
			return "Insert Thành Công";
		} catch (Exception e) {
			return "Insert Thất Bại";
		}
		
	}
	
	
	@Override
	public String update(PhieuDoi phieuDoi) {
		try {
			XJdbc.update(Update_PhieuDoi, phieuDoi.getLiDo(),phieuDoi.getMaPhieuDoi() );
			return "Update Thành Công";
		} catch (Exception e) {
			return "Update Thất Bại";
		}
		
	}
	
	
	@Override
	public String delete(String MaPhieuDoi) {
		try {
			XJdbc.update(Delete_SQL, MaPhieuDoi);
			return "Delete Thành Công";
		} catch (Exception e) {
			return "Delete Thất Bại";
		}
	}
	
	@Override
	public PhieuDoi selectById(String id) {
		List<PhieuDoi> list = this.selectBySQLSetForm(SelectPhieuDoiByMaPhieuDoi, id);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<PhieuDoi> selectAll() {
		return this.selectBySQL(SelectPhieuDoi_SQL);
	}
	
	@Override
	public List<PhieuDoi> selectBySQL(String sql, Object... args) {
		List<PhieuDoi> list = new ArrayList<PhieuDoi>();
		try {
			ResultSet rs = XJdbc.query(sql, args);
			while(rs.next()) {
				PhieuDoi phieuDoi = new PhieuDoi();
				phieuDoi.setID(rs.getInt("ID"));
				phieuDoi.setMaPhieuDoi(rs.getString("MaPhieuDoi"));
				phieuDoi.setMaKH(rs.getString("MaKH"));
				phieuDoi.setMaHD(rs.getString("MaHD"));
				phieuDoi.setMaNV(rs.getString("MaNV"));
				phieuDoi.setNgayTao(XDate.toDate(rs.getString("NgayTao"), "yyyy-MM-dd HH:mm:ss"));
				phieuDoi.setLiDo(rs.getString("LiDo"));
				list.add(phieuDoi);				
			}
			rs.getStatement().getConnection().close();
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public List<PhieuDoi> selectAllSetForm() {
		return this.selectBySQLSetForm(SelectPhieuDoiSetForm);
	}
	
	
	public List<PhieuDoi> selectBySQLSetForm(String sql, Object... args) {
		List<PhieuDoi> list = new ArrayList<PhieuDoi>();
		try {
			ResultSet rs = XJdbc.query(sql, args);
			while(rs.next()) {
				PhieuDoi phieuDoi = new PhieuDoi();
				phieuDoi.setID(rs.getInt("ID"));
				phieuDoi.setMaPhieuDoi(rs.getString("MaPhieuDoi"));
				phieuDoi.setMaKH(rs.getString("MaKH"));
				phieuDoi.setTenKH(rs.getString("HoTenKhachHang"));
				phieuDoi.setMaHD(rs.getString("MaHD"));
				phieuDoi.setMaNV(rs.getString("MaNV"));
				phieuDoi.setTenNV(rs.getString("HoTenNhanVien"));
				phieuDoi.setNgayTao(XDate.toDate(rs.getString("NgayTao"), "yyyy-MM-dd HH:mm:ss"));
				phieuDoi.setLiDo(rs.getString("LiDo"));
				list.add(phieuDoi);				
			}
			rs.getStatement().getConnection().close();
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		
	}
	
	
	
	
	
}
