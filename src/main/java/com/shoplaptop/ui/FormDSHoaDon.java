package com.shoplaptop.ui;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JScrollPane;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.shoplaptop.dao.CTHoaDonDAO;
import com.shoplaptop.dao.CTPhieuDoiDAO;
import com.shoplaptop.dao.HoaDonDAO;
import com.shoplaptop.dao.KhachHangDAO;
import com.shoplaptop.dao.PhieuDoiDAO;
import com.shoplaptop.entity.CTHoaDon;
import com.shoplaptop.entity.CTPhieuDoi;
import com.shoplaptop.entity.HoaDon;
import com.shoplaptop.entity.KhachHang;
import com.shoplaptop.utils.MsgBox;

import javax.swing.JSeparator;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormDSHoaDon extends JDialog {
	private JTable tblhoadon;
	private JTable tblphieudoi;
	private DefaultTableModel model;
	private JTable tblhoadonct;
	private HoaDonDAO dao = new HoaDonDAO();
	private PhieuDoiDAO dao2 = new PhieuDoiDAO();
	private CTHoaDonDAO dao3 = new CTHoaDonDAO();
	private DefaultTableModel model_CTHD;
	private DefaultTableModel model_spDOI;
	private JTextField txttimkiem;
	
 	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormDSHoaDon dialog = new FormDSHoaDon();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public FormDSHoaDon() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(FormDSHoaDon.class.getResource("/com/shoplaptop/icon/365_1.png")));
		setBounds(100, 100, 975, 675);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		setTitle("ShopLapTop365");
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 137, 941, 241);
		getContentPane().add(scrollPane);
		
		model = new DefaultTableModel();
		String[] colums = new String[] {"Mã Hóa Đơn","Mã Khách Hàng","Họ Tên","HTVC","HTTT","Mã Nhân Viên","Ngày Tạo","Tổng Tiền","Tiền Giảm","Thành Tiền"};
		model.setColumnIdentifiers(colums);
		
		tblhoadon = new JTable(model);
		tblhoadon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 int index = tblhoadon.getSelectedRow();
				 String maHD = (String) tblhoadon.getValueAt(index, 0);
				 
				 List<CTHoaDon> listCTHD = new CTHoaDonDAO().selectHoaDonByMaHoaDon(maHD);
				 FilltableCThoadon(listCTHD);
				 
				 List<CTPhieuDoi> ctPhieuDois = new ArrayList<CTPhieuDoi>();
				 
				try {
					 for (int i = 0; i < listCTHD.size(); i++) {
							CTPhieuDoi ctPhieuDoi = new CTPhieuDoiDAO().selectByMHD_SN(listCTHD.get(i).getMaHD(), listCTHD.get(i).getSerialNumber());
							ctPhieuDois.add(ctPhieuDoi);
						}
						 FilltablePhieuDoi(ctPhieuDois);
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		scrollPane.setViewportView(tblhoadon);
		FilltablehoaDon(dao.selectAll());
		
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 388, 448, 221);
		getContentPane().add(scrollPane_1);
		
		model_CTHD = new DefaultTableModel();
		String[] colums_1 = new String[] {"Tên LapTop","Serial","Giá"};
		model_CTHD.setColumnIdentifiers(colums_1);
		
		tblhoadonct = new JTable(model_CTHD);
		scrollPane_1.setViewportView(tblhoadonct);
		
		JScrollPane scrollPane_1_1 = new JScrollPane();
		scrollPane_1_1.setBounds(503, 388, 448, 221);
		getContentPane().add(scrollPane_1_1);
		
		model_spDOI = new DefaultTableModel();
		String[] colums_2 = new String[] {"Laptop cũ","Serial cũ","Giá cũ"," Laptop mới","Serial mới","Giá mới"};
		model_spDOI.setColumnIdentifiers(colums_2);
		
		tblphieudoi = new JTable(model_spDOI);
		scrollPane_1_1.setViewportView(tblphieudoi);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 57, 941, 2);
		getContentPane().add(separator);
		
		JLabel lblNewLabel = new JLabel("Danh Sách Hóa Đơn");
		lblNewLabel.setIcon(new ImageIcon(FormDSHoaDon.class.getResource("/com/shoplaptop/icon/List.png")));
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblNewLabel.setBounds(381, 20, 211, 27);
		getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Tìm Kiếm");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String sdt = txttimkiem.getText();
					FilltablehoaDon(FindSDT(sdt));
				} catch (NullPointerException e2) {
					MsgBox.alert(getContentPane(), "Không tìm thấy khách hàng");
				}
			}
		});
		btnNewButton.setIcon(new ImageIcon(FormDSHoaDon.class.getResource("/com/shoplaptop/icon/477210.png")));
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnNewButton.setBounds(807, 83, 144, 33);
		getContentPane().add(btnNewButton);
		
		txttimkiem = new JTextField();
		txttimkiem.setBounds(10, 86, 787, 32);
		getContentPane().add(txttimkiem);
		txttimkiem.setColumns(10);
		
	}
	
	public void FilltablehoaDon(List<HoaDon> list) {
		model.setRowCount(0);
		for (HoaDon hoaDon : list) {
			Object[] rows = new Object[] {
					hoaDon.getMaHD(),hoaDon.getMaKH(),new KhachHangDAO().selectByMaKH(hoaDon.getMaKH()).getHoTen(),hoaDon.getHinhThucVanChuyen() ,hoaDon.getHinhThucThanhToan(),
					hoaDon.getMaNV(),hoaDon.getNgayTao(),decimalFormat(hoaDon.getTongTien()),decimalFormat(hoaDon.getTienGiam()),decimalFormat(hoaDon.getThanhTien())
			};
			model.addRow(rows);
		}
	}
	
	public void FilltableCThoadon(List<CTHoaDon> list) {
		model_CTHD.setRowCount(0);
		for (CTHoaDon ctHoaDon : list) {
			Object[] rows = new Object[] {
				ctHoaDon.getTenLaptop(),ctHoaDon.getSerialNumber(),ctHoaDon.getGia()	
			};
			model_CTHD.addRow(rows);
 		}
	}
	public void FilltablePhieuDoi(List<CTPhieuDoi> list) {
		model_spDOI.setRowCount(0);
		for (CTPhieuDoi ctPhieuDoi : list) {  
			Object[] rows = new Object[] {
				ctPhieuDoi.getTenLapCu(),
				ctPhieuDoi.getSerialNumber_Old(),
				ctPhieuDoi.getGiaCu(),
				ctPhieuDoi.getTenLapMoi(),
				ctPhieuDoi.getSerialNumber_New(),
				ctPhieuDoi.getGiaMoi()
		};
			model_spDOI.addRow(rows);
		}
	}
	
	public static String decimalFormat(BigDecimal number) {        
        DecimalFormat decimalFormat = new DecimalFormat("0.####################");
        String formattedNumber = decimalFormat.format(number);
        return formattedNumber;
	}
	
	public List<HoaDon> FindSDT(String SDT) {
		KhachHang khachHang = new KhachHangDAO().selectHoaDonBySoDienThoai(SDT);
		List<HoaDon> hoadon = new HoaDonDAO().selectByMaKH(khachHang.getMaKH());

		return hoadon;
	}
}
