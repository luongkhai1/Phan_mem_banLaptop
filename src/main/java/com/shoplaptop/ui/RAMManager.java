package com.shoplaptop.ui;

import javax.swing.JDialog;

import com.shoplaptop.dao.RAMDAO;
import com.shoplaptop.dao.ShopLaptop365Manager;
import com.shoplaptop.entity.RAM;
import com.shoplaptop.utils.Auth;
import com.shoplaptop.utils.MsgBox;
import com.shoplaptop.utils.XImage;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

@SuppressWarnings("serial")
public class RAMManager extends JDialog implements ShopLaptop365Manager<RAM>{
	private JTextField txtMaRAM;
	private JTextField txtLoaiRAM;
	private JTextField txtDungLuong;
	private JTable tblRAM;
	private JTextField txtTocDoRAM;
	private DefaultTableModel model;

	/**
	 * Create the dialog.
	 */
	public RAMManager() {
		setBounds(100, 100, 760, 505);
		setLocationRelativeTo(null);
		setIconImage(XImage.getAppIcon());
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 251, 723, 207);
		getContentPane().add(scrollPane);
		
		model = new DefaultTableModel();
		String [] cols = {"Mã RAM","Loại RAM","Dung lượng","Tốc độ RAM"};
		model.setColumnIdentifiers(cols);
		
		tblRAM = new JTable(model);
		tblRAM.setRowMargin(3);
		tblRAM.setRowHeight(25);
		tblRAM.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		tblRAM.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tblRAM.getSelectedRow();
				setModel(new RAMDAO().selectAll().get(row));
			}
		});
		scrollPane.setViewportView(tblRAM);
		
		JLabel lblRam = new JLabel("RAM");
		lblRam.setHorizontalAlignment(SwingConstants.CENTER);
		lblRam.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lblRam.setBounds(293, 10, 153, 33);
		getContentPane().add(lblRam);
		
		JLabel lblNewLabel_1 = new JLabel("Mã RAM");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(10, 62, 127, 33);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Loại RAM");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(10, 105, 127, 33);
		getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Dung lượng");
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_2.setBounds(10, 148, 127, 33);
		getContentPane().add(lblNewLabel_1_2);
		
		txtMaRAM = new JTextField();
		txtMaRAM.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtMaRAM.setColumns(10);
		txtMaRAM.setBounds(167, 62, 363, 33);
		getContentPane().add(txtMaRAM);
		
		txtLoaiRAM = new JTextField();
		txtLoaiRAM.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtLoaiRAM.setColumns(10);
		txtLoaiRAM.setBounds(167, 105, 363, 33);
		getContentPane().add(txtLoaiRAM);
		
		txtDungLuong = new JTextField();
		txtDungLuong.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtDungLuong.setColumns(10);
		txtDungLuong.setBounds(167, 148, 363, 33);
		getContentPane().add(txtDungLuong);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Tốc độ RAM");
		lblNewLabel_1_2_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_2_1.setBounds(10, 191, 127, 33);
		getContentPane().add(lblNewLabel_1_2_1);
		
		txtTocDoRAM = new JTextField();
		txtTocDoRAM.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtTocDoRAM.setColumns(10);
		txtTocDoRAM.setBounds(167, 191, 363, 33);
		getContentPane().add(txtTocDoRAM);
		
		JButton btnAdd = new JButton("Thêm");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MsgBox.alert(getContentPane(), new RAMDAO().insert(getModel()));
					fillTable(new RAMDAO().selectAll());
					dispose();
					CreateLaptop.fillCboRAM();
					CreateLaptop.cboRAM.setSelectedIndex(-1);
				} catch (Exception e2) {
					throw new RuntimeException(e2);
				}
			}
		});
		btnAdd.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnAdd.setBounds(588, 62, 109, 33);
		getContentPane().add(btnAdd);
		
		JButton btnUpdate = new JButton("Cập nhật");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MsgBox.alert(getContentPane(), new RAMDAO().update(getModel()));
					fillTable(new RAMDAO().selectAll());
					dispose();
					CreateLaptop.fillCboRAM();
					CreateLaptop.cboRAM.setSelectedIndex(-1);
				} catch (Exception e2) {
					throw new RuntimeException(e2);
				}
			}
		});
		btnUpdate.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnUpdate.setBounds(588, 105, 109, 33);
		getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Xóa");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!Auth.isManager()) {
					MsgBox.alert(getContentPane(), "Bạn không có quyền xóa!");
				} else {
					if (MsgBox.confirm(getContentPane(), "Xác nhận xóa")) {
						MsgBox.alert(getContentPane(), new RAMDAO().deleteRAM(txtMaRAM.getText()));
						dispose();
						CreateLaptop.fillCboRAM();
						CreateLaptop.cboRAM.setSelectedIndex(-1);
					}
				}
			}
		});
		btnDelete.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnDelete.setBounds(588, 148, 109, 33);
		getContentPane().add(btnDelete);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setModel(new RAM());
			}
		});
		btnReset.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnReset.setBounds(588, 190, 109, 33);
		getContentPane().add(btnReset);
		fillTable(new RAMDAO().selectAll());

	}

	public void fillTable(List<RAM> list) {
		model.setRowCount(0);
		try {
			for (RAM ram : list) {
				Object[] rows = new Object[] {
					ram.getMaRAM(),
					ram.getLoaiRAM(),
					ram.getDungLuong(),
					ram.getTocDoRAM()
				};
				model.addRow(rows);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public RAM getModel() {
		RAM ram = new RAM();
		ram.setMaRAM(txtMaRAM.getText());
		ram.setLoaiRAM(txtLoaiRAM.getText());
		ram.setDungLuong(Integer.valueOf(txtDungLuong.getText()));
		ram.setTocDoRAM(Integer.valueOf(txtTocDoRAM.getText()));
		return ram;
	}

	public void setModel(RAM entity) {
		txtMaRAM.setText(entity.getMaRAM());
		txtLoaiRAM.setText(entity.getLoaiRAM());
		txtDungLuong.setText(String.valueOf(entity.getDungLuong()));
		txtTocDoRAM.setText(String.valueOf(entity.getTocDoRAM()));
	}
}
