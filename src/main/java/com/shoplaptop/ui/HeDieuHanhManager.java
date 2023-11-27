package com.shoplaptop.ui;

import javax.swing.JDialog;

import com.shoplaptop.dao.HeDieuHanhDAO;
import com.shoplaptop.dao.ShopLaptop365Manager;
import com.shoplaptop.entity.HeDieuHanh;
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
public class HeDieuHanhManager extends JDialog implements ShopLaptop365Manager<HeDieuHanh>{
	private JTextField txtMaHDH;
	private JTextField txtOS;
	private JTextField txtVersion;
	private JTextField txtKieu;
	private JTable tblHDH;
	private DefaultTableModel model;
	
	/**
	 * Create the dialog.
	 */
	public HeDieuHanhManager() {
		setBounds(100, 100, 760, 505);
		setLocationRelativeTo(null);
		setIconImage(XImage.getAppIcon());
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 251, 723, 207);
		getContentPane().add(scrollPane);
		
		model = new DefaultTableModel();
		String [] cols = {"Mã hệ điều hành","OS","Version","Kiểu"};
		model.setColumnIdentifiers(cols);
		
		tblHDH = new JTable(model);
		tblHDH.setRowMargin(3);
		tblHDH.setRowHeight(25);
		tblHDH.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		tblHDH.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tblHDH.getSelectedRow();
				setModel(new HeDieuHanhDAO().selectAll().get(row));
			}
		});
		scrollPane.setViewportView(tblHDH);
		
		JLabel lblHiuHnh = new JLabel("Hệ điều hành");
		lblHiuHnh.setHorizontalAlignment(SwingConstants.CENTER);
		lblHiuHnh.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lblHiuHnh.setBounds(293, 10, 153, 33);
		getContentPane().add(lblHiuHnh);
		
		JLabel lblNewLabel_1 = new JLabel("Mã hệ điều hành");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(10, 62, 175, 33);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("OS");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(10, 105, 175, 33);
		getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Version");
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_2.setBounds(10, 148, 175, 33);
		getContentPane().add(lblNewLabel_1_2);
		
		txtMaHDH = new JTextField();
		txtMaHDH.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtMaHDH.setColumns(10);
		txtMaHDH.setBounds(212, 62, 254, 33);
		getContentPane().add(txtMaHDH);
		
		txtOS = new JTextField();
		txtOS.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtOS.setColumns(10);
		txtOS.setBounds(212, 105, 254, 33);
		getContentPane().add(txtOS);
		
		txtVersion = new JTextField();
		txtVersion.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtVersion.setColumns(10);
		txtVersion.setBounds(212, 148, 254, 33);
		getContentPane().add(txtVersion);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Kiểu");
		lblNewLabel_1_2_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_2_1.setBounds(10, 191, 175, 33);
		getContentPane().add(lblNewLabel_1_2_1);
		
		txtKieu = new JTextField();
		txtKieu.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtKieu.setColumns(10);
		txtKieu.setBounds(212, 191, 254, 33);
		getContentPane().add(txtKieu);
		
		JButton btnAdd = new JButton("Thêm");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MsgBox.alert(getContentPane(), new HeDieuHanhDAO().insert(getModel()));
					fillTable(new HeDieuHanhDAO().selectAll());
					dispose();
					CreateLaptop.fillHeDieuHanh();
					CreateLaptop.cboHeDieuHanh.setSelectedIndex(-1);
				} catch (Exception e2) {
					throw new RuntimeException(e2);
				}
			}
		});
		btnAdd.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnAdd.setBounds(551, 62, 109, 33);
		getContentPane().add(btnAdd);
		
		JButton btnUpdate = new JButton("Cập nhật");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MsgBox.alert(getContentPane(), new HeDieuHanhDAO().update(getModel()));
					fillTable(new HeDieuHanhDAO().selectAll());
					dispose();
					CreateLaptop.fillHeDieuHanh();
					CreateLaptop.cboHeDieuHanh.setSelectedIndex(-1);
				} catch (Exception e2) {
					throw new RuntimeException(e2);
				}
			}
		});
		btnUpdate.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnUpdate.setBounds(551, 105, 109, 33);
		getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Xóa");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!Auth.isManager()) {
					MsgBox.alert(getContentPane(), "Bạn không có quyền xóa!");
				} else {
					if (MsgBox.confirm(getContentPane(), "Xác nhận xóa")) {
						MsgBox.alert(getContentPane(), new HeDieuHanhDAO().deleteHDH(txtMaHDH.getText()));
						dispose();
						CreateLaptop.fillHeDieuHanh();
						CreateLaptop.cboHeDieuHanh.setSelectedIndex(-1);
					}
				}
			}
		});
		btnDelete.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnDelete.setBounds(551, 148, 109, 33);
		getContentPane().add(btnDelete);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setModel(new HeDieuHanh());
			}
		});
		btnReset.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnReset.setBounds(551, 190, 109, 33);
		getContentPane().add(btnReset);
		
		fillTable(new HeDieuHanhDAO().selectAll());
	}

	public void fillTable(List<HeDieuHanh> list) {
		model.setRowCount(0);
		try {
			for (HeDieuHanh heDieuHanh : list) {
				Object[] rows = new Object[] {
					heDieuHanh.getMaHeDieuHanh(),
					heDieuHanh.getOs(),
					heDieuHanh.getVersion(),
					heDieuHanh.getKieu()
				};
				model.addRow(rows);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public HeDieuHanh getModel() {
		HeDieuHanh heDieuHanh = new HeDieuHanh();
		heDieuHanh.setMaHeDieuHanh(txtMaHDH.getText());
		heDieuHanh.setOs(txtOS.getText());
		heDieuHanh.setVersion(txtVersion.getText());
		heDieuHanh.setKieu(Integer.valueOf(txtKieu.getText()));
		return heDieuHanh;
	}

	public void setModel(HeDieuHanh entity) {
		txtMaHDH.setText(entity.getMaHeDieuHanh());
		txtOS.setText(entity.getOs());
		txtVersion.setText(entity.getVersion());
		txtKieu.setText(String.valueOf(entity.getKieu()));
	}
}
