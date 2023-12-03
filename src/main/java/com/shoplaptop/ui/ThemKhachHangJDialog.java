package com.shoplaptop.ui;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTextField;

import com.shoplaptop.dao.KhachHangDAO;
import com.shoplaptop.entity.KhachHang;

import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class ThemKhachHangJDialog extends JDialog {
	public static JTextField txtMaKH;
	public static JTextField txtTenKH;
	public static JTextField txtSDT;
	public static JTextArea txtDiaChi;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ThemKhachHangJDialog dialog = new ThemKhachHangJDialog();
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
	public ThemKhachHangJDialog() {
		setTitle("Quản lý khách hàng");
		setBounds(100, 100, 683, 477);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("THÊM KHÁCH HÀNG");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 28));
		lblNewLabel.setForeground(new Color(255, 128, 192));
		lblNewLabel.setBounds(196, 0, 298, 33);
		getContentPane().add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 255, 240));
		panel.setBorder(new LineBorder(new Color(128, 255, 255)));
		panel.setBounds(10, 31, 649, 399);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Thông tin khách hàng");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_1.setForeground(new Color(0, 128, 255));
		lblNewLabel_1.setBounds(23, 11, 319, 20);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Tên khách hàng :");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(23, 150, 134, 20);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("Mã khách hàng :");
		lblNewLabel_2_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_2_1.setBounds(23, 77, 134, 14);
		panel.add(lblNewLabel_2_1);

		JLabel lblNewLabel_2_2 = new JLabel("Số điện thoại :");
		lblNewLabel_2_2.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_2_2.setBounds(372, 74, 134, 20);
		panel.add(lblNewLabel_2_2);

		JLabel lblNewLabel_2_3 = new JLabel("Địa chỉ :");
		lblNewLabel_2_3.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_2_3.setBounds(23, 241, 134, 20);
		panel.add(lblNewLabel_2_3);

		txtMaKH = new JTextField();
		txtMaKH.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtMaKH.setToolTipText("");
		txtMaKH.setForeground(new Color(0, 0, 0));
		txtMaKH.setBounds(23, 102, 271, 30);
		panel.add(txtMaKH);
		txtMaKH.setColumns(10);

		txtTenKH = new JTextField();
		txtTenKH.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtTenKH.setForeground(new Color(0, 0, 0));
		txtTenKH.setColumns(10);
		txtTenKH.setBounds(23, 181, 271, 30);
		panel.add(txtTenKH);

		txtSDT = new JTextField();
		txtSDT.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtSDT.setForeground(new Color(0, 0, 0));
		txtSDT.setColumns(10);
		txtSDT.setBounds(372, 102, 271, 30);
		panel.add(txtSDT);

		JButton btnHuyLuuKH = new JButton("Hủy");
		btnHuyLuuKH.setBounds(538, 352, 105, 37);
		panel.add(btnHuyLuuKH);
		btnHuyLuuKH.setBackground(Color.PINK);
		btnHuyLuuKH.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnHuyLuuKH.setForeground(new Color(0, 128, 255));
		btnHuyLuuKH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearForm();
			}
		});
		btnHuyLuuKH.setIcon(new ImageIcon(ThemKhachHangJDialog.class.getResource("/com/shoplaptop/icon/Refresh.png")));

		JButton btnLuuKH = new JButton("Lưu");
		btnLuuKH.setBounds(407, 352, 105, 37);
		panel.add(btnLuuKH);
		btnLuuKH.setBackground(Color.PINK);
		btnLuuKH.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLuuKH.setForeground(new Color(0, 128, 255));
		btnLuuKH.setIcon(new ImageIcon(ThemKhachHangJDialog.class.getResource("/com/shoplaptop/icon/Save as.png")));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 271, 633, 71);
		panel.add(scrollPane);
		
		txtDiaChi = new JTextArea();
		scrollPane.setViewportView(txtDiaChi);
		btnLuuKH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtTenKH.getText().trim().isEmpty()) {
					txtTenKH.setText(txtMaKH.getText());
				}
				if (txtDiaChi.getText().trim().isEmpty()) {
					txtDiaChi.setText("Đ/c không được cung cấp");
				}
				JOptionPane.showMessageDialog(getContentPane(), new KhachHangDAO().insertKH(getForm()));
				dispose();
				HoaDonManager.txtSoDienThoai.setText(txtSDT.getText());
			}
		});

	}

	public void setForm(KhachHang khachHang) {
		txtMaKH.setText(khachHang.getMaKH());
		txtTenKH.setText(khachHang.getHoTen());
		txtSDT.setText(khachHang.getSoDienThoai());
		txtDiaChi.setText(khachHang.getDiaChi());
	}

	public KhachHang getForm() {
		KhachHang khachHang = new KhachHang();
		khachHang.setMaKH(txtMaKH.getText());
		khachHang.setHoTen(txtTenKH.getText());
		khachHang.setSoDienThoai(txtSDT.getText());
		khachHang.setDiaChi(txtDiaChi.getText());
		return khachHang;

	}

	public void addKH() {
		KhachHang khachHang = getForm();
		JOptionPane.showMessageDialog(getContentPane(), new KhachHangDAO().insert(khachHang));

	}

	public void clearForm() {
		txtMaKH.setText(null);
		txtTenKH.setText(null);
		txtSDT.setText(null);
		txtDiaChi.setText(null);
	}
}
