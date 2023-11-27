package com.shoplaptop.ui;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.shoplaptop.dao.PhanLoaiDAO;
import com.shoplaptop.dao.ShopLaptop365Manager;
import com.shoplaptop.entity.PhanLoai;
import com.shoplaptop.utils.Auth;
import com.shoplaptop.utils.MsgBox;
import com.shoplaptop.utils.XImage;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

@SuppressWarnings("serial")
public class PhanLoaiManager extends JDialog implements ShopLaptop365Manager<PhanLoai>{
	
	private JTextField txtMaLoai;
	private JTextField txtTenLoai;
	private JTextField txtMoTa;
	private JTable tblPhanLoai;
	private DefaultTableModel model;

	/**
	 * Create the dialog.
	 */
	public PhanLoaiManager() {
		setBounds(100, 100, 760, 505);
		setLocationRelativeTo(null);
		setIconImage(XImage.getAppIcon());
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 251, 723, 207);
		getContentPane().add(scrollPane);
		
		model = new DefaultTableModel();
		String [] cols = {"Mã Loại","Tên loại","Mô tả"};
		model.setColumnIdentifiers(cols);
		
		tblPhanLoai = new JTable(model);
		tblPhanLoai.setRowMargin(3);
		tblPhanLoai.setRowHeight(25);
		tblPhanLoai.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		tblPhanLoai.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tblPhanLoai.getSelectedRow();
				setModel(new PhanLoaiDAO().selectAll().get(row));
			}
		});
		scrollPane.setViewportView(tblPhanLoai);
		
		JLabel lblNewLabel = new JLabel("Phân Loại");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(293, 10, 153, 33);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Mã loại");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(10, 62, 127, 33);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Tên loại");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(10, 121, 127, 33);
		getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Mô tả");
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_2.setBounds(10, 179, 127, 33);
		getContentPane().add(lblNewLabel_1_2);
		
		txtMaLoai = new JTextField();
		txtMaLoai.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtMaLoai.setBounds(167, 62, 254, 33);
		getContentPane().add(txtMaLoai);
		txtMaLoai.setColumns(10);
		
		txtTenLoai = new JTextField();
		txtTenLoai.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtTenLoai.setColumns(10);
		txtTenLoai.setBounds(167, 121, 254, 33);
		getContentPane().add(txtTenLoai);
		
		txtMoTa = new JTextField();
		txtMoTa.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtMoTa.setColumns(10);
		txtMoTa.setBounds(167, 179, 254, 33);
		getContentPane().add(txtMoTa);
		
		JButton btnAdd = new JButton("Thêm");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MsgBox.alert(getContentPane(), new PhanLoaiDAO().insert(getModel()));
					fillTable(new PhanLoaiDAO().selectAll());
					dispose();
					CreateLaptop.fillCboPhanLoai();
					CreateLaptop.cboPhanLoai.setSelectedIndex(-1);
				} catch (Exception e2) {
					throw new RuntimeException(e2);
				}
			}
		});
		btnAdd.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnAdd.setBounds(529, 50, 109, 33);
		getContentPane().add(btnAdd);
		
		JButton btnUpdate = new JButton("Cập nhật");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MsgBox.alert(getContentPane(), new PhanLoaiDAO().update(getModel()));
					fillTable(new PhanLoaiDAO().selectAll());
					dispose();
					CreateLaptop.fillCboPhanLoai();
					CreateLaptop.cboPhanLoai.setSelectedIndex(-1);
				} catch (Exception e2) {
					throw new RuntimeException(e2);
				}
			}
		});
		btnUpdate.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnUpdate.setBounds(529, 93, 109, 33);
		getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Xóa");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!Auth.isManager()) {
					MsgBox.alert(getContentPane(), "Bạn không có quyền xóa!");
				} else {
					if (MsgBox.confirm(getContentPane(), "Xác nhận xóa")) {
						MsgBox.alert(getContentPane(), new PhanLoaiDAO().deletePL(txtMaLoai.getText()));
						dispose();
						CreateLaptop.fillCboPhanLoai();
						CreateLaptop.cboPhanLoai.setSelectedIndex(-1);
					}
				}
			}
		});
		btnDelete.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnDelete.setBounds(529, 136, 109, 33);
		getContentPane().add(btnDelete);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setModel(new PhanLoai());
			}
		});
		btnReset.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnReset.setBounds(529, 178, 109, 33);
		getContentPane().add(btnReset);

		fillTable(new PhanLoaiDAO().selectAll());

	}

	public void fillTable(List<PhanLoai> list) {
		model.setRowCount(0);
		try {
			for (PhanLoai phanLoai : list) {
				Object[] rows = new Object[] {
					phanLoai.getMaLoai(),
					phanLoai.getTenLoai(),
					phanLoai.getMoTa()
				};
				model.addRow(rows);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public PhanLoai getModel() {
		PhanLoai phanLoai = new PhanLoai();
		phanLoai.setMaLoai(txtMaLoai.getText());
		phanLoai.setTenLoai(txtTenLoai.getText());
		phanLoai.setMoTa(txtMoTa.getText());
		return phanLoai;
	}

	public void setModel(PhanLoai entity) {
		txtMaLoai.setText(entity.getMaLoai());
		txtTenLoai.setText(entity.getTenLoai());
		txtMoTa.setText(entity.getMoTa());
	}
}
