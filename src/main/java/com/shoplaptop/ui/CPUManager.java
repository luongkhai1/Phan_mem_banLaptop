package com.shoplaptop.ui;

import javax.swing.JDialog;

import com.shoplaptop.dao.CPUDAO;
import com.shoplaptop.dao.ShopLaptop365Manager;
import com.shoplaptop.entity.CPU;
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
public class CPUManager extends JDialog implements ShopLaptop365Manager<CPU> {
	private JTextField txtMaCPU;
	private JTextField txtHangCPU;
	private JTextField txtCongNghe;
	private JTextField txtLoaiCPU;
	private JTextField txtTocDoCPU;
	private JTextField txtTocDoToiDa;
	private JTextField txtSoNhan;
	private JTextField txtSoLuong;
	private JTextField txtBoNhoDem;
	private JTable tblCPU;
	private DefaultTableModel model;

	/**
	 * Create the dialog.
	 */
	public CPUManager() {
		setBounds(100, 100, 760, 505);
		setLocationRelativeTo(null);
		setIconImage(XImage.getAppIcon());
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 276, 723, 182);
		getContentPane().add(scrollPane);
		
		model = new DefaultTableModel();
		String [] cols = {"Mã CPU","Hãng CPU","Công nghệ","Loại CPU","Tốc độ CPU","Tốc độ tối đa","Số nhân","Số luồng","Bộ nhớ đệm"};
		model.setColumnIdentifiers(cols);
		
		tblCPU = new JTable(model);
		tblCPU.setRowMargin(3);
		tblCPU.setRowHeight(25);
		tblCPU.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		tblCPU.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = tblCPU.getSelectedRow();
				setModel(new CPUDAO().selectAll().get(index));
			}
		});
		scrollPane.setViewportView(tblCPU);
		
		JLabel lblCpu = new JLabel("CPU");
		lblCpu.setHorizontalAlignment(SwingConstants.CENTER);
		lblCpu.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lblCpu.setBounds(293, 10, 153, 33);
		getContentPane().add(lblCpu);
		
		JLabel lblNewLabel_1 = new JLabel("Mã CPU");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(10, 62, 127, 33);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Hãng CPU");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(10, 105, 127, 33);
		getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Công nghệ");
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_2.setBounds(10, 148, 127, 33);
		getContentPane().add(lblNewLabel_1_2);
		
		txtMaCPU = new JTextField();
		txtMaCPU.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtMaCPU.setColumns(10);
		txtMaCPU.setBounds(147, 62, 213, 33);
		getContentPane().add(txtMaCPU);
		
		txtHangCPU = new JTextField();
		txtHangCPU.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtHangCPU.setColumns(10);
		txtHangCPU.setBounds(147, 105, 213, 33);
		getContentPane().add(txtHangCPU);
		
		txtCongNghe = new JTextField();
		txtCongNghe.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtCongNghe.setColumns(10);
		txtCongNghe.setBounds(147, 148, 213, 33);
		getContentPane().add(txtCongNghe);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Loại CPU");
		lblNewLabel_1_2_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_2_1.setBounds(10, 191, 127, 33);
		getContentPane().add(lblNewLabel_1_2_1);
		
		txtLoaiCPU = new JTextField();
		txtLoaiCPU.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtLoaiCPU.setColumns(10);
		txtLoaiCPU.setBounds(147, 191, 213, 33);
		getContentPane().add(txtLoaiCPU);
		
		JLabel lblNewLabel_1_2_2 = new JLabel("Tốc độ CPU");
		lblNewLabel_1_2_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_2_2.setBounds(10, 234, 127, 33);
		getContentPane().add(lblNewLabel_1_2_2);
		
		txtTocDoCPU = new JTextField();
		txtTocDoCPU.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtTocDoCPU.setColumns(10);
		txtTocDoCPU.setBounds(147, 234, 213, 33);
		getContentPane().add(txtTocDoCPU);
		
		JLabel lblNewLabel_1_3 = new JLabel("Tốc độ tối đa");
		lblNewLabel_1_3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_3.setBounds(383, 62, 127, 33);
		getContentPane().add(lblNewLabel_1_3);
		
		txtTocDoToiDa = new JTextField();
		txtTocDoToiDa.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtTocDoToiDa.setColumns(10);
		txtTocDoToiDa.setBounds(520, 62, 213, 33);
		getContentPane().add(txtTocDoToiDa);
		
		txtSoNhan = new JTextField();
		txtSoNhan.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtSoNhan.setColumns(10);
		txtSoNhan.setBounds(520, 105, 213, 33);
		getContentPane().add(txtSoNhan);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Số nhân");
		lblNewLabel_1_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_1_1.setBounds(383, 105, 127, 33);
		getContentPane().add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_2_3 = new JLabel("Số luồng");
		lblNewLabel_1_2_3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_2_3.setBounds(383, 148, 127, 33);
		getContentPane().add(lblNewLabel_1_2_3);
		
		txtSoLuong = new JTextField();
		txtSoLuong.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtSoLuong.setColumns(10);
		txtSoLuong.setBounds(520, 148, 213, 33);
		getContentPane().add(txtSoLuong);
		
		txtBoNhoDem = new JTextField();
		txtBoNhoDem.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtBoNhoDem.setColumns(10);
		txtBoNhoDem.setBounds(520, 191, 213, 33);
		getContentPane().add(txtBoNhoDem);
		
		JLabel lblNewLabel_1_2_1_1 = new JLabel("Bộ nhớ đệm");
		lblNewLabel_1_2_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_2_1_1.setBounds(383, 191, 127, 33);
		getContentPane().add(lblNewLabel_1_2_1_1);
		
		JButton btnAdd = new JButton("Thêm");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MsgBox.alert(getContentPane(), new CPUDAO().insert(getModel()));
					fillTable(new CPUDAO().selectAll());
					dispose();
					CreateLaptop.fillCboCPU();
					CreateLaptop.cboCPU.setSelectedIndex(-1);
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
					MsgBox.alert(getContentPane(), new CPUDAO().update(getModel()));
					fillTable(new CPUDAO().selectAll());
					dispose();
					CreateLaptop.fillCboCPU();
					CreateLaptop.cboCPU.setSelectedIndex(-1);
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
						MsgBox.alert(getContentPane(), new CPUDAO().deleteByMa(txtMaCPU.getText()));
						dispose();
						CreateLaptop.fillCboCPU();
						CreateLaptop.cboCPU.setSelectedIndex(-1);
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
				setModel(new CPU());
			}
		});
		btnReset.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnReset.setBounds(648, 234, 85, 32);
		getContentPane().add(btnReset);
		
		fillTable(new CPUDAO().selectAll());

	}
	
	public void fillTable(List<CPU> list) {
		model.setRowCount(0);
		try {
			for (CPU cpu : list) {
				Object[] rows = new Object[] {
					cpu.getMaCPU(),
					cpu.getHangCPU(),
					cpu.getCongNghe(),
					cpu.getLoaiCPU(),
					cpu.getTocDoCPU(),
					cpu.getTocDoToiDa(),
					cpu.getSoNhan(),
					cpu.getSoLuong(),
					cpu.getBoNhoDem()
				};
				model.addRow(rows);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public CPU getModel() {
		CPU cpu = new CPU();
		cpu.setMaCPU(txtMaCPU.getText());
		cpu.setHangCPU(txtHangCPU.getText());
		cpu.setCongNghe(txtCongNghe.getText());
		cpu.setLoaiCPU(txtLoaiCPU.getText());
		cpu.setTocDoCPU(Double.valueOf(txtTocDoCPU.getText()));
		cpu.setTocDoToiDa(Double.valueOf(txtTocDoToiDa.getText()));
		cpu.setSoNhan(Integer.valueOf(txtSoNhan.getText()));
		cpu.setSoLuong(Integer.valueOf(txtSoLuong.getText()));
		cpu.setBoNhoDem(Integer.valueOf(txtBoNhoDem.getText()));
		return cpu;
	}
	
	public void setModel(CPU cpu) {
		txtMaCPU.setText(cpu.getMaCPU());
		txtHangCPU.setText(cpu.getHangCPU());
		txtCongNghe.setText(cpu.getCongNghe());
		txtLoaiCPU.setText(cpu.getLoaiCPU());
		txtTocDoCPU.setText(cpu.getTocDoCPU()+"");
		txtTocDoToiDa.setText(cpu.getTocDoToiDa()+"");
		txtSoNhan.setText(cpu.getSoNhan()+"");
		txtSoLuong.setText(cpu.getSoLuong()+"");
		txtBoNhoDem.setText(cpu.getBoNhoDem()+"");
	}
	
}
