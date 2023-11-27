package com.shoplaptop.ui;

import javax.swing.JDialog;

import com.shoplaptop.dao.OCungDAO;
import com.shoplaptop.dao.ShopLaptop365Manager;
import com.shoplaptop.entity.OCung;
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
public class OCungManager extends JDialog implements ShopLaptop365Manager<OCung>{
	private JTextField txtMaOCung;
	private JTextField txtKieuOCung;
	private JTextField txtDungLuong;
	private JTable tblOCung;
	private DefaultTableModel model;

	/**
	 * Create the dialog.
	 */
	public OCungManager() {
		setBounds(100, 100, 760, 505);
		setLocationRelativeTo(null);
		setIconImage(XImage.getAppIcon());
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 251, 723, 207);
		getContentPane().add(scrollPane);
		
		model = new DefaultTableModel();
		String [] cols = {"Mã Ổ cứng","Kiểu Ổ cứng","Dung lượng"};
		model.setColumnIdentifiers(cols);
		
		tblOCung = new JTable(model);
		tblOCung.setRowMargin(3);
		tblOCung.setRowHeight(25);
		tblOCung.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		tblOCung.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tblOCung.getSelectedRow();
				setModel(new OCungDAO().selectAll().get(row));
			}
		});
		scrollPane.setViewportView(tblOCung);
		
		JLabel lblCng = new JLabel("Ổ cứng");
		lblCng.setHorizontalAlignment(SwingConstants.CENTER);
		lblCng.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lblCng.setBounds(293, 10, 153, 33);
		getContentPane().add(lblCng);
		
		JLabel lblNewLabel_1 = new JLabel("Mã ổ cứng");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(10, 62, 127, 33);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Kiểu ổ cứng");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(10, 121, 127, 33);
		getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Dung lượng");
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_2.setBounds(10, 179, 127, 33);
		getContentPane().add(lblNewLabel_1_2);
		
		txtMaOCung = new JTextField();
		txtMaOCung.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtMaOCung.setColumns(10);
		txtMaOCung.setBounds(167, 62, 254, 33);
		getContentPane().add(txtMaOCung);
		
		txtKieuOCung = new JTextField();
		txtKieuOCung.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtKieuOCung.setColumns(10);
		txtKieuOCung.setBounds(167, 121, 254, 33);
		getContentPane().add(txtKieuOCung);
		
		txtDungLuong = new JTextField();
		txtDungLuong.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtDungLuong.setColumns(10);
		txtDungLuong.setBounds(167, 179, 254, 33);
		getContentPane().add(txtDungLuong);
		
		JButton btnAdd = new JButton("Thêm");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MsgBox.alert(getContentPane(), new OCungDAO().insert(getModel()));
					fillTable(new OCungDAO().selectAll());
					dispose();
					CreateLaptop.fillCboOCung();
					CreateLaptop.cboOCung.setSelectedIndex(-1);
				} catch (Exception e2) {
					throw new RuntimeException(e2);
				}
			}
		});
		btnAdd.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnAdd.setBounds(539, 51, 109, 33);
		getContentPane().add(btnAdd);
		
		JButton btnUpdate = new JButton("Cập nhật");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MsgBox.alert(getContentPane(), new OCungDAO().update(getModel()));
					fillTable(new OCungDAO().selectAll());
					dispose();
					CreateLaptop.fillCboOCung();
					CreateLaptop.cboOCung.setSelectedIndex(-1);
				} catch (Exception e2) {
					throw new RuntimeException(e2);
				}
			}
		});
		btnUpdate.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnUpdate.setBounds(539, 94, 109, 33);
		getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Xóa");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!Auth.isManager()) {
					MsgBox.alert(getContentPane(), "Bạn không có quyền xóa!");
				} else {
					if (MsgBox.confirm(getContentPane(), "Xác nhận xóa")) {
						MsgBox.alert(getContentPane(), new OCungDAO().deleteOCung(txtMaOCung.getText()));
						dispose();
						CreateLaptop.fillCboOCung();
						CreateLaptop.cboOCung.setSelectedIndex(-1);
					}
				}
			}
		});
		btnDelete.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnDelete.setBounds(539, 137, 109, 33);
		getContentPane().add(btnDelete);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setModel(new OCung());
			}
		});
		btnReset.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnReset.setBounds(539, 179, 109, 33);
		getContentPane().add(btnReset);
		fillTable(new OCungDAO().selectAll());

	}

	public void fillTable(List<OCung> list) {
		model.setRowCount(0);
		try {
			for (OCung oCung : list) {
				Object[] rows = new Object[] {
					oCung.getMaOCung(),
					oCung.getKieuOCung(),
					oCung.getDungLuong()
				};
				model.addRow(rows);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public OCung getModel() {
		OCung oCung = new OCung();
		oCung.setMaOCung(txtMaOCung.getText());
		oCung.setKieuOCung(txtKieuOCung.getText());
		oCung.setDungLuong(Integer.valueOf(txtDungLuong.getText()));
		return oCung;
	}

	public void setModel(OCung entity) {
		txtKieuOCung.setText(entity.getMaOCung());
		txtMaOCung.setText(entity.getKieuOCung());
		txtDungLuong.setText(String.valueOf(entity.getDungLuong()));
	}

}
