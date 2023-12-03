package com.shoplaptop.ui;

import javax.swing.JDialog;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.shoplaptop.dao.LS_HoaDonDao;
import com.shoplaptop.dao.LS_PhieuDoiDao;
import com.shoplaptop.entity.BaoCao_LS_HoaDon;
import com.shoplaptop.entity.BaoCao_LS_PhieuDoi;

@SuppressWarnings("serial")
public class BaoCao extends JDialog {
	private JTable tblhoadon;
	private DefaultTableModel model;
	private LS_HoaDonDao dao = new LS_HoaDonDao();
	private LS_PhieuDoiDao dao2 = new LS_PhieuDoiDao();

	String SellectAll = "select LS_HoaDon.manv,maHD,Lichsulamviec from LS_HoaDon join HoaDon on LS_HoaDon.mahoadon = Hoadon.id where LS_Hoadon.manv =?";
	String Sellectall_1 = "SELECT LS_phieudoi.manv,maphieudoi,lichsulamviec FROM dbo.LS_PhieuDoi join PhieuDoi on phieudoi.id=LS_phieudoi.Phieudoi where LS_Phieudoi.manv = ?";
	private JTable tblPhieuDoi;

	public BaoCao() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(BaoCao.class.getResource("/com/shoplaptop/icon/365_1.png")));
		setTitle("ShopLapTop365\r\n");
		setBounds(100, 100, 748, 409);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 10, 714, 349);
		getContentPane().add(tabbedPane);
		
		JLayeredPane layeredPane = new JLayeredPane();
		tabbedPane.addTab("Hóa Đơn", null, layeredPane, null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 689, 302);
		layeredPane.add(scrollPane);
		JLayeredPane layeredPane_1 = new JLayeredPane();
		tabbedPane.addTab("Phiếu Đổi ", null, layeredPane_1, null);
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 10, 689, 302);
		layeredPane_1.add(scrollPane_1);
		
		model = new DefaultTableModel();
		String[] colums = new String[] {"Mã nhân viên","Mã hóa đơn","Lịch sử làm việc"};
		model.setColumnIdentifiers(colums);
		
		tblhoadon = new JTable(model);
		tblhoadon.setRowMargin(3);
		tblhoadon.setRowHeight(25);
		scrollPane.setViewportView(tblhoadon);
		filltableHoaDon(dao.selectBySQL(SellectAll, LS_NhanVien.txtmanhanvien.getText()));
		
		model = new DefaultTableModel();
		String[] cloums = new String[] {"Mã Nhân Viên","Mã Phiếu Đổi","Lịch Sử Làm Việc"};
		model.setColumnIdentifiers(cloums);
		
		tblPhieuDoi = new JTable(model);
		tblPhieuDoi.setRowHeight(25);
		scrollPane_1.setViewportView(tblPhieuDoi);
		filltablePhieuDoi(dao2.selectBySQL(Sellectall_1, LS_NhanVien.txtmanhanvien.getText()));
			

	}
	public void filltableHoaDon(List<BaoCao_LS_HoaDon> list) {
		model.setRowCount(0);
		for (BaoCao_LS_HoaDon baoCao_LS_HoaDon : list) {
			Object[] rows = new Object[] {
				baoCao_LS_HoaDon.getManv(),
				baoCao_LS_HoaDon.getMahd(),
				baoCao_LS_HoaDon.getLS()
			};
			model.addRow(rows);
		}
	}
	public void filltablePhieuDoi(List<BaoCao_LS_PhieuDoi> list) {
		model.setRowCount(0);
		for (BaoCao_LS_PhieuDoi baoCao_LS_PhieuDoi : list) {
			Object[] rows = new Object[] {
				baoCao_LS_PhieuDoi.getManv(),
				baoCao_LS_PhieuDoi.getMaphieudoi(),
				baoCao_LS_PhieuDoi.getLS()
			};
			model.addRow(rows);
		}
	}
}
