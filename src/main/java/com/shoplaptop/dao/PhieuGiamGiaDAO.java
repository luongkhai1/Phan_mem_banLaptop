package com.shoplaptop.dao;
import java.sql.*;

import com.shoplaptop.entity.PhieuGiamGia;
import com.shoplaptop.utils.XDate;
import com.shoplaptop.utils.XJdbc;
import java.util.ArrayList;
import java.util.List;

public class PhieuGiamGiaDAO implements ShopLaptop365DAO<PhieuGiamGia, String> {
    PhieuGiamGia pgg;
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    String findByMaGG = "SELECT * FROM dbo.PhieuGiamGia WHERE MaPG = ? AND SoLuong > 0 AND Han > GETDATE() ";
    
    public ArrayList<PhieuGiamGia> getALLDAO(){
        ArrayList<PhieuGiamGia> dspg = new ArrayList<>();
        String sql = "select * from PhieuGiamGia";
        try {
            con = new XJdbc().Connect();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
              dspg.add(new PhieuGiamGia(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getInt(5), rs.getBigDecimal(6), rs.getBigDecimal(7)));
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return dspg;
    }
    public void ADDAO(PhieuGiamGia pgg){
        String sql = "insert into PhieuGiamGia values(?,?,?,?,?,?)";
        try {
            con = new XJdbc().Connect();
            ps = con.prepareStatement(sql);
            ps.setString(1, pgg.getMaPG());
            ps.setString(2, pgg.getTenPhieu());
            ps.setString(3, XDate.toString(pgg.getHan(), "yyyy-MM-dd HH:mm:ss"));
            ps.setInt(4, pgg.getSoLuong());
            ps.setBigDecimal(5, pgg.getGiaGiam());
            ps.setBigDecimal(6, pgg.getDieuKienGiam());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void XOADAO(String MaPG){
        String sql = "delete PhieuGiamGia where MaPG = ?";
        try {
            con = new XJdbc().Connect();
            ps = con.prepareStatement(sql);
            ps.setString(1, MaPG);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void UPDATEDAO(PhieuGiamGia pgg){
        String sql = "Update PhieuGiamGia \n"
                + "set TenPhieu = ?,\n"
                + "    Han = ?,\n"
                + "     SoLuong = ?,\n"
                + "	GiaGiam =?,\n"
                + "	DieuKienHoaDon = ? \n"
                + "	where MaPG = ?";
        try {
        	XJdbc.update(sql, pgg.getTenPhieu(),pgg.getHan(),pgg.getSoLuong(),pgg.getGiaGiam(),pgg.getDieuKienGiam(),pgg.getMaPG());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public List<PhieuGiamGia> selectAllPhieu(int count) {
    	String selectPhieu = "SELECT * FROM\r\n"
    			+ "   (SELECT ROW_NUMBER() OVER (ORDER BY MaPG DESC) AS rownum, * from PhieuGiamGia)\r\n"
    			+ "	AS temp\r\n"
    			+ "    WHERE rownum BETWEEN ? AND ?";
		return selectBySQL(selectPhieu,count,count+4);
	}
    
    public ArrayList<PhieuGiamGia> getALLDAOLOC(){
        String sql="select * from PhieuGiamGia";
        return getALLSQL(sql);
    }
    
    public List<PhieuGiamGia> selectPhieuConHan(int count) {
    	String selectPhieu = "SELECT * FROM\r\n"
    			+ "   (SELECT ROW_NUMBER() OVER (ORDER BY MaPG DESC) AS rownum, * from PhieuGiamGia where Han>(SELECT CAST(SYSDATETIME() AS DATE) AS CurrentDate))\r\n"
    			+ "	AS temp\r\n"
    			+ "    WHERE rownum BETWEEN ? AND ?";
		return selectBySQL(selectPhieu,count,count+4);
	}
    
    public ArrayList<PhieuGiamGia> getALLDAOLOCCONHAN(){
        String sql="select * from PhieuGiamGia where Han>(SELECT CAST(SYSDATETIME() AS DATE) AS CurrentDate)";
        return getALLSQL(sql);
    }
    
    public List<PhieuGiamGia> selectPhieuHetHan(int count) {
    	String selectPhieu = "SELECT * FROM\r\n"
    			+ "   (SELECT ROW_NUMBER() OVER (ORDER BY MaPG DESC) AS rownum, * from PhieuGiamGia where Han<(SELECT CAST(SYSDATETIME() AS DATE) AS CurrentDate))\r\n"
    			+ "	AS temp\r\n"
    			+ "    WHERE rownum BETWEEN ? AND ?";
		return selectBySQL(selectPhieu,count,count+4);
	}
    
    public ArrayList<PhieuGiamGia> getALLDAOLOCHETHAN(){
        String sql="select * from PhieuGiamGia where Han<(SELECT CAST(SYSDATETIME() AS DATE) AS CurrentDate)";
        return getALLSQL(sql);
    }
    public ArrayList<PhieuGiamGia> getALLSQL(String sql){
        ArrayList<PhieuGiamGia> dspg = new ArrayList<>();
        try {
            con = new XJdbc().Connect();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                dspg.add(new PhieuGiamGia(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getInt(5), rs.getBigDecimal(6), rs.getBigDecimal(7)));
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return dspg;
    }
    
	@Override
	public String insert(PhieuGiamGia entity) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String update(PhieuGiamGia entity) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String delete(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public PhieuGiamGia selectById(String id) {
		List<PhieuGiamGia> list = this.selectBySQL(findByMaGG, id);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
	@Override
	public List<PhieuGiamGia> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<PhieuGiamGia> selectBySQL(String sql, Object... args) {
		
	List<PhieuGiamGia> list = new ArrayList<PhieuGiamGia>();
			try {
				ResultSet rs = XJdbc.query(sql, args);
				while (rs.next()) {
					PhieuGiamGia phieuGiamGia = new PhieuGiamGia(rs.getInt("ID"), rs.getString("MaPG"), rs.getString("TenPhieu"), XDate.toDate(rs.getString("Han"), "yyyy-MM-dd HH:mm:ss"), rs.getInt("SoLuong"), rs.getBigDecimal("GiaGiam"), rs.getBigDecimal("DieuKienHoaDon"));
					list.add(phieuGiamGia);
				}
				rs.getStatement().getConnection().close();
				return list;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
	
	}
}