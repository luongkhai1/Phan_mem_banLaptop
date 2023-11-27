package com.shoplaptop.ui;

import javax.swing.JDialog;

import com.shoplaptop.dao.ManHinhDAO;
import com.shoplaptop.dao.ShopLaptop365Manager;
import com.shoplaptop.entity.ManHinh;
import com.shoplaptop.utils.Auth;
import com.shoplaptop.utils.MsgBox;
import com.shoplaptop.utils.XImage;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

@SuppressWarnings("serial")
public class ManHinhManager extends JDialog implements ShopLaptop365Manager<ManHinh>{
	private JTextField txtMaManHinh;
	private JTextField txtKichThuoc;
	private JTextField txtCongNghe;
	private JTextField txtDoPhanGiai;
	private JTextField txtTanSoQuet;
	private JTextField txtTamNen;
	private JTextField txtDoSang;
	private JTextField txtDoPhuMau;
	private JTextField txtTiLeManHinh;
	private JTable tblManHinh;
	private DefaultTableModel model;

	/**
	 * Create the dialog.
	 */
	public ManHinhManager() {
		setBounds(100, 100, 760, 505);
		setLocationRelativeTo(null);
		setIconImage(XImage.getAppIcon());
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 276, 723, 182);
		getContentPane().add(scrollPane);
		
		model = new DefaultTableModel();
		String [] cols = {"Mã màn hình","Kích thước","Công nghệ","Độ phân giải","Tần số quét","Tấm nền","Độ sáng","Độ phủ màu","Tỉ lệ màn hình"};
		model.setColumnIdentifiers(cols);
		
		tblManHinh = new JTable(model);
		tblManHinh.setRowMargin(3);
		tblManHinh.setRowHeight(25);
		tblManHinh.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		tblManHinh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tblManHinh.getSelectedRow();
				setModel(new ManHinhDAO().selectAll().get(row));
			}
		});
		scrollPane.setViewportView(tblManHinh);
		
		JLabel lblMnHnh = new JLabel("Màn hình");
		lblMnHnh.setHorizontalAlignment(SwingConstants.CENTER);
		lblMnHnh.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lblMnHnh.setBounds(293, 10, 153, 33);
		getContentPane().add(lblMnHnh);
		
		JLabel lblNewLabel_1 = new JLabel("Mã màn hình");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(10, 62, 127, 33);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Kích thước");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(10, 105, 127, 33);
		getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Công nghệ");
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_2.setBounds(10, 148, 127, 33);
		getContentPane().add(lblNewLabel_1_2);
		
		txtMaManHinh = new JTextField();
		txtMaManHinh.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtMaManHinh.setColumns(10);
		txtMaManHinh.setBounds(147, 62, 213, 33);
		getContentPane().add(txtMaManHinh);
		
		txtKichThuoc = new JTextField();
		txtKichThuoc.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtKichThuoc.setColumns(10);
		txtKichThuoc.setBounds(147, 105, 213, 33);
		getContentPane().add(txtKichThuoc);
		
		txtCongNghe = new JTextField();
		txtCongNghe.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtCongNghe.setColumns(10);
		txtCongNghe.setBounds(147, 148, 213, 33);
		getContentPane().add(txtCongNghe);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Độ phân giải");
		lblNewLabel_1_2_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_2_1.setBounds(10, 191, 127, 33);
		getContentPane().add(lblNewLabel_1_2_1);
		
		txtDoPhanGiai = new JTextField();
		txtDoPhanGiai.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtDoPhanGiai.setColumns(10);
		txtDoPhanGiai.setBounds(147, 191, 213, 33);
		getContentPane().add(txtDoPhanGiai);
		
		JLabel lblNewLabel_1_2_2 = new JLabel("Tần số quét");
		lblNewLabel_1_2_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_2_2.setBounds(10, 234, 127, 33);
		getContentPane().add(lblNewLabel_1_2_2);
		
		txtTanSoQuet = new JTextField();
		txtTanSoQuet.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtTanSoQuet.setColumns(10);
		txtTanSoQuet.setBounds(147, 234, 213, 33);
		getContentPane().add(txtTanSoQuet);
		
		JLabel lblNewLabel_1_3 = new JLabel("Tấm nền");
		lblNewLabel_1_3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_3.setBounds(383, 62, 127, 33);
		getContentPane().add(lblNewLabel_1_3);
		
		txtTamNen = new JTextField();
		txtTamNen.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtTamNen.setColumns(10);
		txtTamNen.setBounds(520, 62, 213, 33);
		getContentPane().add(txtTamNen);
		
		txtDoSang = new JTextField();
		txtDoSang.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtDoSang.setColumns(10);
		txtDoSang.setBounds(520, 105, 213, 33);
		getContentPane().add(txtDoSang);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Độ sáng");
		lblNewLabel_1_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_1_1.setBounds(383, 105, 127, 33);
		getContentPane().add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_2_3 = new JLabel("Độ phủ màu");
		lblNewLabel_1_2_3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_2_3.setBounds(383, 148, 127, 33);
		getContentPane().add(lblNewLabel_1_2_3);
		
		txtDoPhuMau = new JTextField();
		txtDoPhuMau.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtDoPhuMau.setColumns(10);
		txtDoPhuMau.setBounds(520, 148, 213, 33);
		getContentPane().add(txtDoPhuMau);
		
		txtTiLeManHinh = new JTextField();
		txtTiLeManHinh.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtTiLeManHinh.setColumns(10);
		txtTiLeManHinh.setBounds(520, 191, 213, 33);
		getContentPane().add(txtTiLeManHinh);
		
		JLabel lblNewLabel_1_2_1_1 = new JLabel("Tỉ lệ màn hình");
		lblNewLabel_1_2_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_2_1_1.setBounds(383, 191, 127, 33);
		getContentPane().add(lblNewLabel_1_2_1_1);
		
		JButton btnAdd = new JButton("Thêm");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MsgBox.alert(getContentPane(), new ManHinhDAO().insert(getModel()));
					fillTable(new ManHinhDAO().selectAll());
					dispose();
					CreateLaptop.fillCboManHinh();
					CreateLaptop.cboManHinh.setSelectedIndex(-1);
				} catch (Exception e2) {
					throw new RuntimeException(e2);
				}
			}
		});
		btnAdd.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnAdd.setBounds(370, 234, 85, 32);
		getContentPane().add(btnAdd);
		
		JButton btnUpdate = new JButton("Sửa");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MsgBox.alert(getContentPane(), new ManHinhDAO().update(getModel()));
					fillTable(new ManHinhDAO().selectAll());
					dispose();
					CreateLaptop.fillCboManHinh();
					CreateLaptop.cboManHinh.setSelectedIndex(-1);
				} catch (Exception e2) {
					throw new RuntimeException(e2);
				}
			}
		});
		btnUpdate.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnUpdate.setBounds(462, 234, 85, 32);
		getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Xóa");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!Auth.isManager()) {
					MsgBox.alert(getContentPane(), "Bạn không có quyền xóa!");
				} else {
					if (MsgBox.confirm(getContentPane(), "Xác nhận xóa")) {
						MsgBox.alert(getContentPane(), new ManHinhDAO().deleteManHinh(txtMaManHinh.getText()));
						dispose();
						CreateLaptop.fillCboManHinh();
						CreateLaptop.cboManHinh.setSelectedIndex(-1);
					}
				}
			}
		});
		btnDelete.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnDelete.setBounds(556, 234, 85, 32);
		getContentPane().add(btnDelete);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setModel(new ManHinh());
			}
		});
		btnReset.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnReset.setBounds(648, 234, 85, 32);
		getContentPane().add(btnReset);

		fillTable(new ManHinhDAO().selectAll());

	}

	public void fillTable(List<ManHinh> list) {
		model.setRowCount(0);
		try {
			for (ManHinh manHinh : list) {
				Object[] rows = new Object[] {
					manHinh.getMaManHinh(),
					manHinh.getKichThuocManHinh(),
					manHinh.getCongNgheManHinh(),
					manHinh.getDoPhanGiai(),
					manHinh.getTanSoQuet(),
					manHinh.getTamNen(),
					manHinh.getDoSang(),
					manHinh.getDoPhuMau(),
					manHinh.getTiLeManHinh()
				};
				model.addRow(rows);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public ManHinh getModel() {
		ManHinh manHinh = new ManHinh();
		manHinh.setMaManHinh(txtMaManHinh.getText());
		manHinh.setKichThuocManHinh(Double.valueOf(txtKichThuoc.getText()));
		manHinh.setCongNgheManHinh(txtCongNghe.getText());
		manHinh.setDoPhanGiai(txtDoPhanGiai.getText());
		manHinh.setTanSoQuet(Integer.valueOf(txtTanSoQuet.getText()));
		manHinh.setTamNen(txtTamNen.getText());
		manHinh.setDoSang(Integer.valueOf(txtDoSang.getText()));
		manHinh.setDoPhuMau(txtDoPhuMau.getText());
		manHinh.setTiLeManHinh(txtTiLeManHinh.getText());
		return manHinh;
	}

	public void setModel(ManHinh entity) {
		txtMaManHinh.setText(entity.getMaManHinh());
		txtKichThuoc.setText(String.valueOf(entity.getKichThuocManHinh()));
		txtCongNghe.setText(entity.getCongNgheManHinh());
		txtDoPhanGiai.setText(entity.getDoPhanGiai());
		txtTanSoQuet.setText(String.valueOf(entity.getTanSoQuet()));
		txtTamNen.setText(entity.getTamNen());
		txtDoSang.setText(String.valueOf(entity.getDoSang()));
		txtDoPhuMau.setText(entity.getDoPhuMau());
		txtTiLeManHinh.setText(entity.getTiLeManHinh());
	}

}
