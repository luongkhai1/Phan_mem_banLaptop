package com.shoplaptop.ui;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.shoplaptop.dao.KhachHangDAO;
import com.shoplaptop.entity.HoaDon;
import com.shoplaptop.entity.KhachHang;
import com.toedter.calendar.JDateChooser;

import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class ChiTietKhachHang extends JDialog {

	public static JTextField txtMaKH;
	public static JTextField txtTenKH;
	public static JTextField txtSDT;
	public static JTextField txtEmail;
	public static JTextArea txtDiaChi;
	public static JRadioButton rdoNam;
	public static JRadioButton rdoNu;
	public static Object txtHoTen;
	private QuanLyKhachHang quanLyKhachHang;
	public static KhachHangDAO khachHangDAO;
	public static JDateChooser dateChooser;
	private JTable tblHoaDon;
	private static DefaultTableModel modelHoaDon;

	/**
	 * Create the dialog.
	 */
	public ChiTietKhachHang(QuanLyKhachHang quanLyKhachHang) {
		this.quanLyKhachHang = quanLyKhachHang;
		getContentPane().setBackground(new Color(245, 255, 250));
		setTitle("Quản lý khách hàng");
		setForeground(new Color(51, 204, 204));
		getContentPane().setForeground(new Color(0, 128, 192));
		setBounds(100, 100, 975, 675);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("CHI TIẾT THÔNG TIN KHÁCH HÀNG");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 28));
		lblNewLabel.setForeground(new Color(255, 128, 192));
		lblNewLabel.setBounds(192, 11, 574, 41);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Mã khách hàng :");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(10, 104, 267, 20);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Tên khách hàng :");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(10, 204, 174, 20);
		getContentPane().add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Số điện thoại :");
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_1_2.setBounds(353, 101, 174, 26);
		getContentPane().add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_3 = new JLabel("Địa chỉ :");
		lblNewLabel_1_3.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_1_3.setBounds(10, 297, 174, 14);
		getContentPane().add(lblNewLabel_1_3);

		JLabel lblNewLabel_1_4 = new JLabel("Ngày sinh :");
		lblNewLabel_1_4.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_1_4.setBounds(658, 104, 174, 20);
		getContentPane().add(lblNewLabel_1_4);

		JLabel lblNewLabel_1_5 = new JLabel("Email :");
		lblNewLabel_1_5.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_1_5.setBounds(353, 206, 174, 17);
		getContentPane().add(lblNewLabel_1_5);

		JLabel lblNewLabel_1_6 = new JLabel("Giới tính :");
		lblNewLabel_1_6.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_1_6.setBounds(658, 200, 174, 29);
		getContentPane().add(lblNewLabel_1_6);

		txtMaKH = new JTextField();
		txtMaKH.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtMaKH.setForeground(new Color(0, 0, 0));
		txtMaKH.setBounds(10, 128, 274, 44);
		getContentPane().add(txtMaKH);
		txtMaKH.setColumns(10);

		txtTenKH = new JTextField();
		txtTenKH.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtTenKH.setForeground(new Color(0, 0, 0));
		txtTenKH.setColumns(10);
		txtTenKH.setBounds(10, 233, 274, 41);
		getContentPane().add(txtTenKH);

		txtSDT = new JTextField();
		txtSDT.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtSDT.setForeground(new Color(0, 0, 0));
		txtSDT.setText("\r\n");
		txtSDT.setColumns(10);
		txtSDT.setBounds(353, 128, 267, 44);
		getContentPane().add(txtSDT);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtEmail.setForeground(new Color(0, 0, 0));
		txtEmail.setColumns(10);
		txtEmail.setBounds(353, 233, 274, 41);
		getContentPane().add(txtEmail);

		txtDiaChi = new JTextArea();
		txtDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtDiaChi.setForeground(new Color(0, 0, 0));
		txtDiaChi.setBounds(10, 322, 922, 44);
		getContentPane().add(txtDiaChi);

		rdoNam = new JRadioButton("Nam");
		rdoNam.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		rdoNam.setBounds(658, 236, 67, 38);
		getContentPane().add(rdoNam);

		rdoNu = new JRadioButton("Nữ");
		rdoNu.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		rdoNu.setBounds(830, 232, 57, 42);
		getContentPane().add(rdoNu);

		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rdoNam);
		buttonGroup.add(rdoNu);

		JButton btnSuaKH = new JButton("Sửa");
		btnSuaKH.setBackground(Color.PINK);
		btnSuaKH.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSuaKH.setForeground(new Color(30, 144, 255));
		btnSuaKH.setIcon(new ImageIcon(ChiTietKhachHang.class.getResource("/src/com/shoplaptop/icon/Edit.png")));
		btnSuaKH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChiTietKhachHang.this.quanLyKhachHang.updateKH(getForm());
				ChiTietKhachHang.this.quanLyKhachHang.fillTable(new KhachHangDAO().selectAll());
				dispose();
			}
		});
		btnSuaKH.setBounds(708, 588, 109, 41);
		getContentPane().add(btnSuaKH);

		JButton btnXoaKH = new JButton("Xóa");
		btnXoaKH.setBackground(Color.PINK);
		btnXoaKH.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnXoaKH.setForeground(new Color(0, 128, 255));
		btnXoaKH.setIcon(new ImageIcon(ChiTietKhachHang.class.getResource("/src/com/shoplaptop/icon/Delete.png")));
		btnXoaKH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(getContentPane(), "Xác nhận xóa") == JOptionPane.YES_NO_OPTION) {
					deleteKH();
					ChiTietKhachHang.this.quanLyKhachHang.fillTable(new KhachHangDAO().selectAll());
				}
				dispose();
				ChiTietKhachHang.this.quanLyKhachHang.fillTable(new KhachHangDAO().selectAll());
			}
		});
		btnXoaKH.setBounds(830, 588, 102, 41);
		getContentPane().add(btnXoaKH);

		dateChooser = new JDateChooser();
		dateChooser.setBounds(658, 128, 274, 44);
		getContentPane().add(dateChooser);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 404, 922, 173);
		getContentPane().add(scrollPane);

		modelHoaDon = new DefaultTableModel();
		String[] colsHoaDon = { "Mã hóa đơn", "Ngày tạo", "Mã NV", "Tổng tiền" };
		modelHoaDon.setColumnIdentifiers(colsHoaDon);

		tblHoaDon = new JTable(modelHoaDon);
		tblHoaDon.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		scrollPane.setViewportView(tblHoaDon);

		JLabel lblNewLabel_1_3_1 = new JLabel("Hóa đơn");
		lblNewLabel_1_3_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_1_3_1.setBounds(10, 377, 174, 14);
		getContentPane().add(lblNewLabel_1_3_1);

	}

	public static void fillTable(List<HoaDon> list) {
		modelHoaDon.setRowCount(0);
		for (HoaDon hoaDon : list) {
			Object[] rows = new Object[] { hoaDon.getMaHD(), hoaDon.getNgayTao(), hoaDon.getMaNV(),
					hoaDon.getTongTien() };
			modelHoaDon.addRow(rows);
		}

	}

	public static void setForm(KhachHang khachHang) {
		txtMaKH.setText(khachHang.getMaKH());
		txtTenKH.setText(khachHang.getHoTen());
		txtSDT.setText(khachHang.getSoDienThoai() + "");
		dateChooser.setDate(khachHang.getNgaySinh());
		rdoNam.setSelected(khachHang.isGioiTinh());
		rdoNu.setSelected(!khachHang.isGioiTinh());
		txtEmail.setText(khachHang.getEmail());
		txtDiaChi.setText(khachHang.getDiaChi());
	}

	public KhachHang getForm() {
		KhachHang khachHang = new KhachHang();
		khachHang.setMaKH(txtMaKH.getText());
		khachHang.setHoTen(txtTenKH.getText());
		khachHang.setSoDienThoai(txtSDT.getText());
		khachHang.setNgaySinh(dateChooser.getDate());
		khachHang.setGioiTinh(rdoNam.isSelected());
		khachHang.setEmail(txtEmail.getText());
		khachHang.setDiaChi(txtDiaChi.getText());
		return khachHang;

	}

	public void deleteKH() {
		KhachHang khachHang = getForm();
		JOptionPane.showMessageDialog(getContentPane(), new KhachHangDAO().delete(khachHang.getMaKH()));
	}

}
