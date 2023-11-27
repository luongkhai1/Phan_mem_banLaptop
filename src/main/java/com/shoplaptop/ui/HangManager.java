package com.shoplaptop.ui;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.shoplaptop.dao.HangDAO;
import com.shoplaptop.dao.ShopLaptop365Manager;
import com.shoplaptop.entity.Hang;
import com.shoplaptop.utils.Auth;
import com.shoplaptop.utils.MsgBox;
import com.shoplaptop.utils.XImage;

import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

@SuppressWarnings("serial")
public class HangManager extends JDialog implements ShopLaptop365Manager<Hang> {

	private JTextField txtMaHang;
	private JTextField txtTenHang;
	private JTextField txtHoTro;
	private JTable tblHang;
	private DefaultTableModel model;

	/**
	 * Create the dialog.
	 */
	public HangManager() {
		setBounds(100, 100, 760, 505);
		setLocationRelativeTo(null);
		setIconImage(XImage.getAppIcon());
		getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 251, 723, 207);
		getContentPane().add(scrollPane);

		model = new DefaultTableModel();
		String[] cols = { "Mã Hãng", "Tên hãng", "Hỗ trợ" };
		model.setColumnIdentifiers(cols);

		tblHang = new JTable(model);
		tblHang.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		tblHang.setRowMargin(3);
		tblHang.setRowHeight(25);
		tblHang.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tblHang.getSelectedRow();
				setModel(new HangDAO().selectAll().get(row));
			}
		});
		scrollPane.setViewportView(tblHang);

		JLabel lblNewLabel = new JLabel("Hãng");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lblNewLabel.setBounds(293, 10, 153, 33);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Mã hãng");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(10, 62, 127, 33);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Tên hãng");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(10, 121, 127, 33);
		getContentPane().add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Hỗ trợ");
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_2.setBounds(10, 179, 127, 33);
		getContentPane().add(lblNewLabel_1_2);

		txtMaHang = new JTextField();
		txtMaHang.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtMaHang.setColumns(10);
		txtMaHang.setBounds(167, 62, 254, 33);
		getContentPane().add(txtMaHang);

		txtTenHang = new JTextField();
		txtTenHang.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtTenHang.setColumns(10);
		txtTenHang.setBounds(167, 121, 254, 33);
		getContentPane().add(txtTenHang);

		txtHoTro = new JTextField();
		txtHoTro.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtHoTro.setColumns(10);
		txtHoTro.setBounds(167, 179, 254, 33);
		getContentPane().add(txtHoTro);

		JButton btnAdd = new JButton("Thêm");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MsgBox.alert(getContentPane(), new HangDAO().insert(getModel()));
					fillTable(new HangDAO().selectAll());
					dispose();
					CreateLaptop.fillCboHang();
					CreateLaptop.cboHang.setSelectedIndex(-1);
				} catch (Exception e2) {
					throw new RuntimeException(e2);
				}
			}
		});
		btnAdd.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnAdd.setBounds(527, 62, 109, 33);
		getContentPane().add(btnAdd);

		JButton btnUpdate = new JButton("Cập nhật");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MsgBox.alert(getContentPane(), new HangDAO().update(getModel()));
					fillTable(new HangDAO().selectAll());
					dispose();
					CreateLaptop.fillCboHang();
					CreateLaptop.cboHang.setSelectedIndex(-1);
				} catch (Exception e2) {
					throw new RuntimeException(e2);
				}
			}
		});
		btnUpdate.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnUpdate.setBounds(527, 105, 109, 33);
		getContentPane().add(btnUpdate);

		JButton btnDelete = new JButton("Xóa");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!Auth.isManager()) {
					MsgBox.alert(getContentPane(), "Bạn không có quyền xóa!");
				} else {
					if (MsgBox.confirm(getContentPane(), "Xác nhận xóa")) {
						MsgBox.alert(getContentPane(), new HangDAO().deleteHang(txtMaHang.getText()));
						dispose();
						CreateLaptop.fillCboHang();
						CreateLaptop.cboHang.setSelectedIndex(-1);
					}
				}
			}
		});
		btnDelete.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnDelete.setBounds(527, 148, 109, 33);
		getContentPane().add(btnDelete);

		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setModel(new Hang());
			}
		});
		btnReset.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnReset.setBounds(527, 190, 109, 33);
		getContentPane().add(btnReset);
		fillTable(new HangDAO().selectAll());

	}

	public void fillTable(List<Hang> list) {
		model.setRowCount(0);
		try {
			for (Hang hang : list) {
				Object[] rows = new Object[] {
					hang.getMaHang(),
					hang.getTenHang(),
					hang.getHoTro()
				};
				model.addRow(rows);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public Hang getModel() {
		Hang hang = new Hang();
		hang.setMaHang(txtMaHang.getText());
		hang.setTenHang(txtTenHang.getText());
		hang.setHoTro(txtHoTro.getText());
		return hang;
	}

	public void setModel(Hang entity) {
		txtMaHang.setText(entity.getMaHang());
		txtTenHang.setText(entity.getTenHang());
		txtHoTro.setText(entity.getHoTro());
	}
}
