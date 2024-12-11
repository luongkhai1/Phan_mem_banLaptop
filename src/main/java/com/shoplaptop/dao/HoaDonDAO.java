package com.shoplaptop.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.shoplaptop.entity.HoaDon;
import com.shoplaptop.entity.KhachHang;
import com.shoplaptop.utils.XJdbc;

public class HoaDonDAO implements ShopLaptop365DAO<HoaDon, String> {
	String SelectAll_SQL = "SELECT HoaDon.ID, dbo.HoaDon.MaHD, HoaDon.MaKH,dbo.HinhThucVanChuyen.ID AS 'ID_HinhThucVanChuyen', dbo.HinhThucVanChuyen.HinhThuc AS 'HinhThucVanChuyen',dbo.HinhThucThanhToan.ID AS 'ID_HinhThucThanhToan',\r\n"
			+ "	dbo.HinhThucThanhToan.HinhThuc AS 'HinhThucThanhToan',\r\n"
			+ "	dbo.PhieuGiamGia.ID AS 'ID_PhieuGiamGia', PhieuGiamGia.MaPG, dbo.HoaDon.DotGiamGia,\r\n"
			+ "	HoaDon.MaNV,\r\n"
			+ "	dbo.HoaDon.NgayTao, dbo.HoaDon.TongTien, dbo.HoaDon.TienGiam, dbo.HoaDon.ThanhTien\r\n"
			+ "FROM dbo.HoaDon JOIN  dbo.KhachHang ON KhachHang.MaKH = HoaDon.MaKH\r\n"
			+ "			JOIN dbo.HinhThucVanChuyen ON HinhThucVanChuyen.ID = HoaDon.HinhThucVanChuyen\r\n"
			+ "			JOIN dbo.HinhThucThanhToan ON HinhThucThanhToan.ID = HoaDon.HinhThucThanhToan\r\n"
			+ "			LEFT JOIN dbo.PhieuGiamGia ON PhieuGiamGia.ID = HoaDon.PhieuGiamGia\r\n"
			+ "			LEFT JOIN dbo.DotGiamGia ON DotGiamGia.MaDG = dbo.HoaDon.DotGiamGia\r\n"
			+ "			JOIN dbo.NhanVien ON NhanVien.MaNV = HoaDon.MaNV";
	
	String selectHoaDonByMaHoaDon = "SELECT HoaDon.ID, dbo.HoaDon.MaHD, HoaDon.MaKH,dbo.HinhThucVanChuyen.ID AS 'ID_HinhThucVanChuyen', dbo.HinhThucVanChuyen.HinhThuc AS 'HinhThucVanChuyen',dbo.HinhThucThanhToan.ID AS 'ID_HinhThucThanhToan',\r\n"
			+ "	dbo.HinhThucThanhToan.HinhThuc AS 'HinhThucThanhToan',\r\n"
			+ "	dbo.PhieuGiamGia.ID AS 'ID_PhieuGiamGia', PhieuGiamGia.MaPG, dbo.HoaDon.DotGiamGia,\r\n"
			+ "	HoaDon.MaNV,\r\n"
			+ "	dbo.HoaDon.NgayTao, dbo.HoaDon.TongTien, dbo.HoaDon.TienGiam, dbo.HoaDon.ThanhTien\r\n"
			+ "FROM dbo.HoaDon JOIN  dbo.KhachHang ON KhachHang.MaKH = HoaDon.MaKH\r\n"
			+ "			JOIN dbo.HinhThucVanChuyen ON HinhThucVanChuyen.ID = HoaDon.HinhThucVanChuyen\r\n"
			+ "			JOIN dbo.HinhThucThanhToan ON HinhThucThanhToan.ID = HoaDon.HinhThucThanhToan\r\n"
			+ "			LEFT JOIN dbo.PhieuGiamGia ON PhieuGiamGia.ID = HoaDon.PhieuGiamGia\r\n"
			+ "			LEFT JOIN dbo.DotGiamGia ON DotGiamGia.MaDG = dbo.HoaDon.DotGiamGia\r\n"
			+ "			JOIN dbo.NhanVien ON NhanVien.MaNV = HoaDon.MaNV "
			+ "WHERE dbo.HoaDon.MaHD = ?";
	String selectMaKH = "SELECT HoaDon.ID, dbo.HoaDon.MaHD, HoaDon.MaKH,dbo.HinhThucVanChuyen.ID AS 'ID_HinhThucVanChuyen', dbo.HinhThucVanChuyen.HinhThuc AS 'HinhThucVanChuyen',dbo.HinhThucThanhToan.ID AS 'ID_HinhThucThanhToan',\r\n"
			+ "	dbo.HinhThucThanhToan.HinhThuc AS 'HinhThucThanhToan',\r\n"
			+ "	dbo.PhieuGiamGia.ID AS 'ID_PhieuGiamGia', PhieuGiamGia.MaPG, dbo.HoaDon.DotGiamGia,\r\n"
			+ "	HoaDon.MaNV,\r\n"
			+ "	dbo.HoaDon.NgayTao, dbo.HoaDon.TongTien, dbo.HoaDon.TienGiam, dbo.HoaDon.ThanhTien\r\n"
			+ "FROM dbo.HoaDon JOIN  dbo.KhachHang ON KhachHang.MaKH = HoaDon.MaKH\r\n"
			+ "			JOIN dbo.HinhThucVanChuyen ON HinhThucVanChuyen.ID = HoaDon.HinhThucVanChuyen\r\n"
			+ "			JOIN dbo.HinhThucThanhToan ON HinhThucThanhToan.ID = HoaDon.HinhThucThanhToan\r\n"
			+ "			LEFT JOIN dbo.PhieuGiamGia ON PhieuGiamGia.ID = HoaDon.PhieuGiamGia\r\n"
			+ "			LEFT JOIN dbo.DotGiamGia ON DotGiamGia.MaDG = dbo.HoaDon.DotGiamGia\r\n"
			+ "			JOIN dbo.NhanVien ON NhanVien.MaNV = HoaDon.MaNV "
			+ "WHERE dbo.HoaDon.MaKH = ?";
	@Override
	public String insert(HoaDon entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String update(HoaDon entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HoaDon selectById(String id) {
		List<HoaDon> list = this.selectBySQL(selectHoaDonByMaHoaDon, id);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
	
	public List<HoaDon> selectByMaKH(String maKH) {
		
		return this.selectBySQL(selectMaKH,maKH);
		
	}

	@Override
	public List<HoaDon> selectAll() {
		
		return this.selectBySQL(SelectAll_SQL);
	}

	@Override
	public List<HoaDon> selectBySQL(String sql, Object... args) {
		List<HoaDon> list = new ArrayList<HoaDon>();
		try {
			ResultSet rs = XJdbc.query(sql, args);
			while (rs.next()) {
				HoaDon hoaDon = new HoaDon();
				hoaDon.setId(rs.getInt("ID"));
				hoaDon.setMaHD(rs.getString("MaHD"));
				hoaDon.setMaKH(rs.getString("MaKH"));
				hoaDon.setId_HinhThucVanChuyen(rs.getInt("ID_HinhThucVanChuyen"));
				hoaDon.setHinhThucVanChuyen(rs.getString("HinhThucVanChuyen"));
				hoaDon.setId_HinhThucThanhToan(rs.getInt("ID_HinhThucThanhToan"));
				hoaDon.setHinhThucThanhToan(rs.getString("HinhThucThanhToan"));
				hoaDon.setId_PhieuGiamGia(rs.getInt("ID_PhieuGiamGia"));
				hoaDon.setPhieuGiamGia(rs.getString("MaPG"));
				hoaDon.setDotGiamGia(rs.getString("DotGiamGia"));
				hoaDon.setMaNV(rs.getString("MaNV"));
				hoaDon.setNgayTao(rs.getDate("NgayTao"));
				hoaDon.setTongTien(rs.getBigDecimal("TongTien"));
				hoaDon.setTienGiam(rs.getBigDecimal("TienGiam"));
				hoaDon.setThanhTien(rs.getBigDecimal("ThanhTien"));
				list.add(hoaDon);
			}
			rs.getStatement().getConnection().close();
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	

}
