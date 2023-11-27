package com.shoplaptop.ui;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.shoplaptop.dao.DongLaptopDAO;
import com.shoplaptop.dao.HangDAO;
import com.shoplaptop.dao.ShopLaptop365Manager;
import com.shoplaptop.entity.DongLaptop;
import com.shoplaptop.entity.Hang;
import com.shoplaptop.utils.Auth;
import com.shoplaptop.utils.MsgBox;
import com.shoplaptop.utils.XImage;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class DongLaptopManager extends JDialog implements ShopLaptop365Manager<DongLaptop> {
	
	private JTextField txtMaDong;
	private JTextField txtTenDong;
	private JTable tblDongLT;
	private static JComboBox<Hang> cboHang;
	private DefaultTableModel model;

	/**
	 * Create the dialog.
	 */
	public DongLaptopManager() {
		setBounds(100, 100, 760, 505);
		setLocationRelativeTo(null);
		setIconImage(XImage.getAppIcon());
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 251, 723, 207);
		getContentPane().add(scrollPane);
		
		model = new DefaultTableModel();
		String [] cols = {"Mã dòng","Hãng","Tên dòng"};
		model.setColumnIdentifiers(cols);
		
		tblDongLT = new JTable(model);
		tblDongLT.setRowMargin(3);
		tblDongLT.setRowHeight(25);
		tblDongLT.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		tblDongLT.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = tblDongLT.getSelectedRow();
				setModel(new DongLaptopDAO().selectAll().get(index));
			}
		});
		scrollPane.setViewportView(tblDongLT);
		
		JLabel lblDngLaptop = new JLabel("Dòng Laptop");
		lblDngLaptop.setHorizontalAlignment(SwingConstants.CENTER);
		lblDngLaptop.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lblDngLaptop.setBounds(293, 10, 153, 33);
		getContentPane().add(lblDngLaptop);
		
		JLabel lblNewLabel_1 = new JLabel("Hãng");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(10, 62, 147, 33);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Mã dòng Laptop");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(10, 121, 147, 33);
		getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Tên dòng Laptop");
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_2.setBounds(10, 179, 147, 33);
		getContentPane().add(lblNewLabel_1_2);
		
		txtMaDong = new JTextField();
		txtMaDong.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtMaDong.setColumns(10);
		txtMaDong.setBounds(167, 121, 254, 33);
		getContentPane().add(txtMaDong);
		
		txtTenDong = new JTextField();
		txtTenDong.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtTenDong.setColumns(10);
		txtTenDong.setBounds(167, 179, 254, 33);
		getContentPane().add(txtTenDong);
		
		cboHang = new JComboBox<Hang>();
		fillCboHang();
		cboHang.setSelectedIndex(-1);
		cboHang.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		cboHang.setBounds(167, 62, 254, 33);
		getContentPane().add(cboHang);
		
		JButton btnAdd = new JButton("Thêm");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MsgBox.alert(getContentPane(), new DongLaptopDAO().insert(getModel()));
					fillTable(new DongLaptopDAO().selectAll());
					dispose();
					Hang hang = (Hang) CreateLaptop.cboHang.getSelectedItem();
					if (hang != null) {
						CreateLaptop.fillCboDonglaptop(hang.getTenHang());
						CreateLaptop.cboDongLaptop.setSelectedIndex(-1);
					}
				} catch (Exception e2) {
					throw new RuntimeException(e2);
				}
			}
		});
		btnAdd.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnAdd.setBounds(547, 51, 109, 33);
		getContentPane().add(btnAdd);
		
		JButton btnUpdate = new JButton("Cập nhật");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MsgBox.alert(getContentPane(), new DongLaptopDAO().update(getModel()));
					fillTable(new DongLaptopDAO().selectAll());
					dispose();
					Hang hang = (Hang) CreateLaptop.cboHang.getSelectedItem();
					if (hang != null) {
						CreateLaptop.fillCboDonglaptop(hang.getTenHang());
						CreateLaptop.cboDongLaptop.setSelectedIndex(-1);
					}
				} catch (Exception e2) {
					throw new RuntimeException(e2);
				}
			}
		});
		btnUpdate.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnUpdate.setBounds(547, 94, 109, 33);
		getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Xóa");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!Auth.isManager()) {
					MsgBox.alert(getContentPane(), "Bạn không có quyền xóa!");
				} else {
					if (MsgBox.confirm(getContentPane(), "Xác nhận xóa")) {
						MsgBox.alert(getContentPane(), new DongLaptopDAO().deleteDongLT(txtMaDong.getText()));
						dispose();
						Hang hang = (Hang) CreateLaptop.cboHang.getSelectedItem();
						if (hang != null) {
							CreateLaptop.fillCboDonglaptop(hang.getTenHang());
							CreateLaptop.cboDongLaptop.setSelectedIndex(-1);
						}
					}
				}
			}
		});
		btnDelete.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnDelete.setBounds(547, 137, 109, 33);
		getContentPane().add(btnDelete);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setModel(new DongLaptop());
			}
		});
		btnReset.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnReset.setBounds(547, 179, 109, 33);
		getContentPane().add(btnReset);
		fillTable(new DongLaptopDAO().selectAll());

	}
	
	public void fillTable(List<DongLaptop> list) {
		model.setRowCount(0);
		try {
			for (DongLaptop dongLaptop : list) {
				Object[] rows = new Object[] {
					dongLaptop.getMaDong(),
					dongLaptop.getTenHang(),
					dongLaptop.getTenDong()
				};
				model.addRow(rows);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public DongLaptop getModel() {
		DongLaptop dongLaptop = new DongLaptop();
		dongLaptop.setMaDong(txtMaDong.getText());
		Hang hang = (Hang) cboHang.getSelectedItem();
		dongLaptop.setId_Hang(hang.getId());
		dongLaptop.setTenDong(txtTenDong.getText());
		return dongLaptop;
	}
	
	public void setModel(DongLaptop dongLaptop) {
		txtMaDong.setText(dongLaptop.getMaDong());
		cboHang.setSelectedItem(new HangDAO().selectById(dongLaptop.getId_Hang()));
		txtTenDong.setText(dongLaptop.getTenDong());
	}
	
	public static void fillCboHang() {
		DefaultComboBoxModel<Hang> model = (DefaultComboBoxModel<Hang>) cboHang.getModel();
		model.removeAllElements();
		List<Hang> list = new HangDAO().selectAll();
		for (Hang hang : list) {
			model.addElement(hang);
		}
	}
}
