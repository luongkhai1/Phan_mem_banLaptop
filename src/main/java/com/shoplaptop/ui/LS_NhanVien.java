package com.shoplaptop.ui;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import com.shoplaptop.dao.LS_HoaDonDao;
import com.shoplaptop.dao.LS_PhieuDoiDao;
import com.shoplaptop.dao.NhanVienService;
import com.shoplaptop.entity.NhanVien;
import com.shoplaptop.utils.MsgBox;
import com.shoplaptop.utils.XDate;
import com.shoplaptop.utils.XImage;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

@SuppressWarnings("serial")
public class LS_NhanVien extends JDialog {
	public static JTextField txtmanhanvien;
	public static JTextField txttennhanvien;
	public static JTextField txtdiachi;
	public static JTextField txtemail;
	public static JTextField txtngaysinh;
	public static JTextField txtsodeinthoai;
	public static JRadioButton rdoquanly;
	public static JRadioButton rdonhanvien;
	public static JRadioButton rdonam;
	public static JRadioButton rdonu;
	public static JLabel lblimage;
	public static ButtonGroup buttonGroup;
	public static ButtonGroup buttonGroup2;
	private QLNhanVien qlNhanVien;
	private NhanVienService service = new NhanVienService();
	private  int index = 1;
	private LS_HoaDonDao ls_HoaDonDao = new LS_HoaDonDao();
	private LS_PhieuDoiDao ls_PhieuDoiDao = new LS_PhieuDoiDao();

	/**
	 * Create the dialog.
	 */
	public LS_NhanVien(QLNhanVien qlNhanVien) {
		getContentPane().setFont(new Font("Times New Roman", Font.PLAIN, 17));
		setTitle("ShopLapTop365");
		setIconImage(Toolkit.getDefaultToolkit().getImage(LS_NhanVien.class.getResource("/com/shoplaptop/icon/365_1.png")));
		this.qlNhanVien = qlNhanVien;
		setBounds(100, 100, 841, 614);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Chi tiết nhân viên ");
		lblNewLabel.setIcon(new ImageIcon(LS_NhanVien.class.getResource("/com/shoplaptop/icon/nhanvien.png")));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 20, 238, 27);
		getContentPane().add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 57, 807, 2);
		getContentPane().add(separator);
		
		JLabel lblNewLabel_1 = new JLabel("Tên nhân viên ");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(10, 263, 124, 26);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Mã nhân viên ");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblNewLabel_1_1.setBounds(432, 87, 324, 26);
		getContentPane().add(lblNewLabel_1_1);
		
		txtmanhanvien = new JTextField();
		txtmanhanvien.setColumns(10);
		txtmanhanvien.setBounds(432, 123, 324, 19);
		getContentPane().add(txtmanhanvien);
		
		txttennhanvien = new JTextField();
		txttennhanvien.setColumns(10);
		txttennhanvien.setBounds(10, 299, 324, 19);
		getContentPane().add(txttennhanvien);
		
		JLabel lblNewLabel_1_2 = new JLabel("Địa Chỉ ");
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblNewLabel_1_2.setBounds(10, 328, 124, 26);
		getContentPane().add(lblNewLabel_1_2);
		
		txtdiachi = new JTextField();
		txtdiachi.setColumns(10);
		txtdiachi.setBounds(10, 364, 324, 19);
		getContentPane().add(txtdiachi);
		
		JLabel lblNewLabel_1_3 = new JLabel("Email ");
		lblNewLabel_1_3.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblNewLabel_1_3.setBounds(432, 163, 124, 26);
		getContentPane().add(lblNewLabel_1_3);
		
		txtemail = new JTextField();
		txtemail.setColumns(10);
		txtemail.setBounds(432, 199, 324, 19);
		getContentPane().add(txtemail);
		
		JLabel lblNewLabel_1_4 = new JLabel("Ngày Sinh ");
		lblNewLabel_1_4.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblNewLabel_1_4.setBounds(432, 393, 124, 26);
		getContentPane().add(lblNewLabel_1_4);
		
		txtngaysinh = new JTextField();
		txtngaysinh.setColumns(10);
		txtngaysinh.setBounds(432, 442, 324, 19);
		getContentPane().add(txtngaysinh);
		
		JLabel lblNewLabel_1_4_1 = new JLabel("Giới tính ");
		lblNewLabel_1_4_1.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblNewLabel_1_4_1.setBounds(432, 238, 124, 26);
		getContentPane().add(lblNewLabel_1_4_1);
		
		rdonam = new JRadioButton("Nam ");
		rdonam.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		rdonam.setBounds(432, 296, 97, 20);
		getContentPane().add(rdonam);
		
		rdonu = new JRadioButton("Nữ ");
		rdonu.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		rdonu.setBounds(562, 299, 103, 21);
		getContentPane().add(rdonu);
		
		JLabel lblNewLabel_1_4_2 = new JLabel("Số điện thoại ");
		lblNewLabel_1_4_2.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblNewLabel_1_4_2.setBounds(432, 328, 124, 26);
		getContentPane().add(lblNewLabel_1_4_2);
		
		txtsodeinthoai = new JTextField();
		txtsodeinthoai.setColumns(10);
		txtsodeinthoai.setBounds(432, 364, 324, 19);
		getContentPane().add(txtsodeinthoai);
		
		JLabel lblNewLabel_1_4_1_1 = new JLabel("Vai Trò ");
		lblNewLabel_1_4_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblNewLabel_1_4_1_1.setBounds(10, 393, 124, 26);
		getContentPane().add(lblNewLabel_1_4_1_1);
		
		rdoquanly = new JRadioButton("Quản lý ");
		rdoquanly.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		rdoquanly.setBounds(144, 439, 103, 21);
		getContentPane().add(rdoquanly);
		
		rdonhanvien = new JRadioButton("Nhân viên ");
		rdonhanvien.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		rdonhanvien.setBounds(10, 439, 103, 21);
		getContentPane().add(rdonhanvien);
		
		JButton btnNewButton = new JButton("Sửa ");
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		btnNewButton.setIcon(new ImageIcon(LS_NhanVien.class.getResource("/com/shoplaptop/icon/Edit.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				service.update(getForm());
				MsgBox.alert(getContentPane(), service.updateTK(getForm()));
				LS_NhanVien.this.qlNhanVien.filltable(service.sellectAllNhanVien((index - 1)*3+1));
				dispose();
			}
		});
		btnNewButton.setBounds(10, 512, 109, 33);
		getContentPane().add(btnNewButton);
		
		JButton btnXa = new JButton("Xóa ");
		btnXa.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		btnXa.setIcon(new ImageIcon(LS_NhanVien.class.getResource("/com/shoplaptop/icon/Delete.png")));
		btnXa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ls_HoaDonDao.delete(txtmanhanvien.getText());
				ls_PhieuDoiDao.delete(txtmanhanvien.getText());
				LS_NhanVien.this.qlNhanVien.delete(getForm());
				LS_NhanVien.this.qlNhanVien.filltable(service.sellectAllNhanVien((index - 1)*3+1));
				LS_NhanVien.this.qlNhanVien.settable(new NhanVienService().selectAll());
				dispose();
			}
		});
		btnXa.setBounds(151, 512, 109, 33);
		getContentPane().add(btnXa);
		
		JButton btnBoCoBn = new JButton("Báo cáo ");
		btnBoCoBn.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		btnBoCoBn.setIcon(new ImageIcon(LS_NhanVien.class.getResource("/com/shoplaptop/icon/Price list.png")));
		btnBoCoBn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new BaoCao().setVisible(true);
			
			}
		});
		btnBoCoBn.setBounds(277, 512, 127, 33);
		getContentPane().add(btnBoCoBn);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 69, 143, 169);
		getContentPane().add(panel);
		
		lblimage = new JLabel("");
		lblimage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				chooseImage();
			}
		});
		lblimage.setBounds(0, 0, 143, 188);
		panel.add(lblimage);
		buttonGroup = new ButtonGroup();
		buttonGroup.add(rdonam);
		buttonGroup.add(rdonu);		
		buttonGroup2 = new ButtonGroup();
		buttonGroup2.add(rdonhanvien);
		buttonGroup2.add(rdoquanly);
		
		JButton btnBoCoBn_1 = new JButton("Thoát");
		btnBoCoBn_1.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		btnBoCoBn_1.setIcon(new ImageIcon(LS_NhanVien.class.getResource("/com/shoplaptop/icon/Log out.png")));
		btnBoCoBn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
			
		});
		btnBoCoBn_1.setBounds(414, 512, 115, 33);
		getContentPane().add(btnBoCoBn_1);

	}
	public NhanVien getForm() {
		NhanVien nhanVien = new NhanVien();
		nhanVien.setHinh(lblimage.getToolTipText());
		nhanVien.setMaNV(txtmanhanvien.getText());
		nhanVien.setHoTen(txttennhanvien.getText());
		nhanVien.setDiaChi(txtdiachi.getText());
		nhanVien.setEmail(txtemail.getText());
		nhanVien.setNgaySinh(XDate.toDate(txtngaysinh.getText(), "yyyy-MM-dd"));
		nhanVien.setGioiTinh(rdonam.isSelected());
		nhanVien.setSoDienThoai(txtsodeinthoai.getText());
		nhanVien.setVaiTro(rdoquanly.isSelected());
		return nhanVien;
		
	}
	
	public void chooseImage() {
		try {
			JFileChooser fileChooser = new JFileChooser();
			if (fileChooser.showOpenDialog(getContentPane()) == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				XImage.save(file);
				ImageIcon icon = XImage.read(file.getName());
				lblimage.setIcon(icon);
				lblimage.setToolTipText(file.getName());
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
