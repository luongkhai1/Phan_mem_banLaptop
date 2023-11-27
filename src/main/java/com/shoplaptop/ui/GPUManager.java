package com.shoplaptop.ui;

import javax.swing.JDialog;

import com.shoplaptop.dao.GPUDAO;
import com.shoplaptop.dao.ShopLaptop365Manager;
import com.shoplaptop.entity.GPU;
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
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class GPUManager extends JDialog implements ShopLaptop365Manager<GPU>{
	private JTextField txtMaGPU;
	private JTextField txtLoaiCard;
	private JTextField txtHang;
	private JTable tblGPU;
	private DefaultTableModel model;

	/**
	 * Create the dialog.
	 */
	public GPUManager() {
		setBounds(100, 100, 760, 505);
		setLocationRelativeTo(null);
		setIconImage(XImage.getAppIcon());
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 251, 723, 207);
		getContentPane().add(scrollPane);
		
		model = new DefaultTableModel();
		String [] cols = {"Mã GPU","Loại Card","Hãng"};
		model.setColumnIdentifiers(cols);
		
		tblGPU = new JTable(model);
		tblGPU.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		tblGPU.setRowMargin(3);
		tblGPU.setRowHeight(25);
		tblGPU.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = tblGPU.getSelectedRow();
				setModel(new GPUDAO().selectAll().get(index));
			}
		});
		scrollPane.setViewportView(tblGPU);
		
		JLabel lblGpu = new JLabel("GPU");
		lblGpu.setHorizontalAlignment(SwingConstants.CENTER);
		lblGpu.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lblGpu.setBounds(293, 10, 153, 33);
		getContentPane().add(lblGpu);
		
		JLabel lblNewLabel_1 = new JLabel("Mã GPU");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(10, 62, 127, 33);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Loại Card");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(10, 121, 127, 33);
		getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Hãng");
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_2.setBounds(10, 179, 127, 33);
		getContentPane().add(lblNewLabel_1_2);
		
		txtMaGPU = new JTextField();
		txtMaGPU.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtMaGPU.setColumns(10);
		txtMaGPU.setBounds(167, 62, 254, 33);
		getContentPane().add(txtMaGPU);
		
		txtLoaiCard = new JTextField();
		txtLoaiCard.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtLoaiCard.setColumns(10);
		txtLoaiCard.setBounds(167, 121, 254, 33);
		getContentPane().add(txtLoaiCard);
		
		txtHang = new JTextField();
		txtHang.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtHang.setColumns(10);
		txtHang.setBounds(167, 179, 254, 33);
		getContentPane().add(txtHang);
		
		JButton btnAdd = new JButton("Thêm");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MsgBox.alert(getContentPane(), new GPUDAO().insert(getModel()));
					fillTable(new GPUDAO().selectAll());
					dispose();
					CreateLaptop.fillCboGPU();
					CreateLaptop.cboGPU.setSelectedIndex(-1);
				} catch (Exception e2) {
					throw new RuntimeException(e2);
				}
			}
		});
		btnAdd.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnAdd.setBounds(532, 62, 109, 33);
		getContentPane().add(btnAdd);
		
		JButton btnUpdate = new JButton("Cập nhật");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MsgBox.alert(getContentPane(), new GPUDAO().update(getModel()));
					fillTable(new GPUDAO().selectAll());
					dispose();
					CreateLaptop.fillCboGPU();
					CreateLaptop.cboGPU.setSelectedIndex(-1);
				} catch (Exception e2) {
					throw new RuntimeException(e2);
				}
			}
		});
		btnUpdate.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnUpdate.setBounds(532, 105, 109, 33);
		getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Xóa");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!Auth.isManager()) {
					MsgBox.alert(getContentPane(), "Bạn không có quyền xóa!");
				} else {
					if (MsgBox.confirm(getContentPane(), "Xác nhận xóa")) {
						MsgBox.alert(getContentPane(), new GPUDAO().deleteGPU(txtMaGPU.getText()));
						dispose();
						CreateLaptop.fillCboGPU();
						CreateLaptop.cboGPU.setSelectedIndex(-1);
					}
				}
			}
		});
		btnDelete.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnDelete.setBounds(532, 148, 109, 33);
		getContentPane().add(btnDelete);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setModel(new GPU());
			}
		});
		btnReset.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnReset.setBounds(532, 190, 109, 33);
		getContentPane().add(btnReset);
		fillTable(new GPUDAO().selectAll());
		
	}
	
	public void fillTable(List<GPU> list) {
		model.setRowCount(0);
		try {
			for (GPU gpu : list) {
				Object[] rows = new Object[] {
					gpu.getMaGPU(),
					gpu.getLoaiCard(),
					gpu.getHang()
				};
				model.addRow(rows);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public GPU getModel() {
		GPU gpu = new GPU();
		gpu.setMaGPU(txtMaGPU.getText());
		gpu.setLoaiCard(txtLoaiCard.getText());
		gpu.setHang(txtHang.getText());
		return gpu;
	}
	
	public void setModel(GPU gpu) {
		txtMaGPU.setText(gpu.getMaGPU());
		txtLoaiCard.setText(gpu.getLoaiCard());
		txtHang.setText(gpu.getHang());
	}

}
