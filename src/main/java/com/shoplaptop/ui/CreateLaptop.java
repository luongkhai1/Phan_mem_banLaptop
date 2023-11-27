package com.shoplaptop.ui;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.shoplaptop.dao.BienTheDAO;
import com.shoplaptop.dao.CPUDAO;
import com.shoplaptop.dao.GPUDAO;
import com.shoplaptop.dao.DongLaptopDAO;
import com.shoplaptop.dao.HangDAO;
import com.shoplaptop.dao.HeDieuHanhDAO;
import com.shoplaptop.dao.LaptopDAO;
import com.shoplaptop.dao.ManHinhDAO;
import com.shoplaptop.dao.MoTaDAO;
import com.shoplaptop.dao.NhaCungCapDAO;
import com.shoplaptop.dao.OCungDAO;
import com.shoplaptop.dao.PhanLoaiDAO;
import com.shoplaptop.dao.RAMDAO;
import com.shoplaptop.dao.SerialNumberDAO;
import com.shoplaptop.entity.BienThe;
import com.shoplaptop.entity.CPU;
import com.shoplaptop.entity.DongLaptop;
import com.shoplaptop.entity.GPU;
import com.shoplaptop.entity.Hang;
import com.shoplaptop.entity.HeDieuHanh;
import com.shoplaptop.entity.Laptop;
import com.shoplaptop.entity.ManHinh;
import com.shoplaptop.entity.MoTa;
import com.shoplaptop.entity.OCung;
import com.shoplaptop.entity.PhanLoai;
import com.shoplaptop.entity.RAM;
import com.shoplaptop.entity.SerialNumber;
import com.shoplaptop.utils.Auth;
import com.shoplaptop.utils.MsgBox;
import com.shoplaptop.utils.XImage;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;

import javax.swing.JScrollPane;
import com.shoplaptop.entity.NhaCungCap;
import javax.swing.JTextArea;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class CreateLaptop extends JDialog {
	private LaptopManager laptopManager;
	public static JComboBox<NhaCungCap> cboNhaCC;
	public static JLabel lblMaDonNhap;
	public static JLabel lblTenMay;
	public static JLabel lblMaBienThe;
	public static JLabel lblAnh;
	public static JComboBox<PhanLoai> cboPhanLoai;
	public static JComboBox<Hang> cboHang;
	public static JTextField txtTenLaptop;
	public static JTextField txtMaLaptop;
	public static JTextField txtNamSanXuat;
	public static JTextField txtSoLuongLoa;
	public static JTextField txtKeyboard;
	public static JTextField txtTouchPad;
	public static JTextField txtCongKetNoi;
	public static JTextField txtWifi;
	public static JTextField txtPin;
	public static JTextField txtTrongLuong;
	public static JTextField txtSoLuongQuat;
	public static JTextField txtWebcam;
	public static JTextField txtBluetooth;
	public static JTextField txtMaLaptop_BT;
	public static JTextField txtMaBienThe;
	public static JTextField txtGia;
	public static JComboBox<CPU> cboCPU;
	public static JComboBox<RAM> cboRAM;
	public static JComboBox<ManHinh> cboManHinh;
	public static JComboBox<GPU> cboGPU;
	public static JComboBox<OCung> cboOCung;
	public static JComboBox<HeDieuHanh> cboHeDieuHanh;
	public static JComboBox<DongLaptop> cboDongLaptop;
	public static JTabbedPane tabbedPane;
	public static JTextField txtMauSac;
	private JTable tblDonNhap;
	private JTable tblCTDonNhap;
	private JTextArea txtSerialNumber;
	private DefaultTableModel modelDonNhap;
	private DefaultTableModel modelCTDonNhap;

	/**
	 * Create the dialog.
	 */
	public CreateLaptop(LaptopManager laptopManager) {
		setTitle("Laptop Manager");
		this.laptopManager = laptopManager;
		setBounds(100, 100, 975, 675);
		setIconImage(XImage.getAppIcon());
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Laptop Manager");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		lblNewLabel.setBounds(321, 10, 316, 36);
		getContentPane().add(lblNewLabel);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 60, 941, 572);
		getContentPane().add(tabbedPane);

		JLayeredPane layeredPane = new JLayeredPane();
		tabbedPane.addTab("Laptop", null, layeredPane, null);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Laptop", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 10, 452, 435);
		layeredPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblTnLaptop = new JLabel("Tên Laptop");
		lblTnLaptop.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTnLaptop.setBounds(10, 91, 136, 31);
		panel_1.add(lblTnLaptop);

		JLabel lblTnLaptop_1 = new JLabel("Hãng");
		lblTnLaptop_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTnLaptop_1.setBounds(10, 217, 136, 31);
		panel_1.add(lblTnLaptop_1);

		JLabel lblTnLaptop_1_1 = new JLabel("Phân loại");
		lblTnLaptop_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTnLaptop_1_1.setBounds(10, 150, 136, 31);
		panel_1.add(lblTnLaptop_1_1);

		cboPhanLoai = new JComboBox<PhanLoai>();
		cboPhanLoai.setBounds(156, 150, 250, 31);
		fillCboPhanLoai();
		cboPhanLoai.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		cboPhanLoai.setSelectedItem(null);
		panel_1.add(cboPhanLoai);

		cboHang = new JComboBox<Hang>();
		fillCboHang();
		cboHang.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		cboHang.setSelectedIndex(-1);
		cboHang.setBounds(156, 217, 250, 31);
		panel_1.add(cboHang);

		txtTenLaptop = new JTextField();
		txtTenLaptop.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtTenLaptop.setColumns(10);
		txtTenLaptop.setBounds(156, 91, 250, 31);
		panel_1.add(txtTenLaptop);
		
		JLabel lblTnLaptop_1_2 = new JLabel("Dòng Laptop");
		lblTnLaptop_1_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTnLaptop_1_2.setBounds(10, 287, 136, 31);
		panel_1.add(lblTnLaptop_1_2);
		
		cboDongLaptop = new JComboBox<DongLaptop>();
		cboDongLaptop.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		cboDongLaptop.setBounds(156, 287, 250, 31);
		panel_1.add(cboDongLaptop);
		
		JLabel lblMLaptop = new JLabel("Mã Laptop");
		lblMLaptop.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblMLaptop.setBounds(10, 31, 136, 31);
		panel_1.add(lblMLaptop);
		
		txtMaLaptop = new JTextField();
		txtMaLaptop.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtMaLaptop.setColumns(10);
		txtMaLaptop.setBounds(156, 31, 250, 31);
		panel_1.add(txtMaLaptop);
		
		JLabel lblNm = new JLabel("Năm sản xuất");
		lblNm.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNm.setBounds(10, 356, 136, 31);
		panel_1.add(lblNm);
		
		txtNamSanXuat = new JTextField();
		txtNamSanXuat.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtNamSanXuat.setColumns(10);
		txtNamSanXuat.setBounds(156, 356, 250, 31);
		panel_1.add(txtNamSanXuat);
		
		JButton btnAddPL = new JButton("");
		btnAddPL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PhanLoaiManager().setVisible(true);
			}
		});
		btnAddPL.setIcon(new ImageIcon(CreateLaptop.class.getResource("/com/shoplaptop/icon/Add.png")));
		btnAddPL.setBounds(408, 150, 34, 31);
		panel_1.add(btnAddPL);
		
		JButton btnAddHang = new JButton("");
		btnAddHang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new HangManager().setVisible(true);
			}
		});
		btnAddHang.setIcon(new ImageIcon(CreateLaptop.class.getResource("/com/shoplaptop/icon/Add.png")));
		btnAddHang.setBounds(408, 217, 34, 31);
		panel_1.add(btnAddHang);
		
		JButton btnAddDongLaptop = new JButton("");
		btnAddDongLaptop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DongLaptopManager().setVisible(true);
			}
		});
		btnAddDongLaptop.setIcon(new ImageIcon(CreateLaptop.class.getResource("/com/shoplaptop/icon/Add.png")));
		btnAddDongLaptop.setBounds(408, 287, 34, 31);
		panel_1.add(btnAddDongLaptop);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "M\u00F4 t\u1EA3", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(472, 10, 454, 435);
		layeredPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblMLaptop_1 = new JLabel("Số lượng loa");
		lblMLaptop_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblMLaptop_1.setBounds(10, 25, 136, 31);
		panel_2.add(lblMLaptop_1);
		
		txtSoLuongLoa = new JTextField();
		txtSoLuongLoa.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtSoLuongLoa.setColumns(10);
		txtSoLuongLoa.setBounds(156, 25, 250, 31);
		panel_2.add(txtSoLuongLoa);
		
		JLabel lblMLaptop_2 = new JLabel("Keyboard");
		lblMLaptop_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblMLaptop_2.setBounds(10, 66, 136, 31);
		panel_2.add(lblMLaptop_2);
		
		txtKeyboard = new JTextField();
		txtKeyboard.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtKeyboard.setColumns(10);
		txtKeyboard.setBounds(156, 66, 250, 31);
		panel_2.add(txtKeyboard);
		
		JLabel lblMLaptop_3 = new JLabel("TouchPad");
		lblMLaptop_3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblMLaptop_3.setBounds(10, 107, 136, 31);
		panel_2.add(lblMLaptop_3);
		
		txtTouchPad = new JTextField();
		txtTouchPad.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtTouchPad.setColumns(10);
		txtTouchPad.setBounds(156, 107, 250, 31);
		panel_2.add(txtTouchPad);
		
		JLabel lblMLaptop_4 = new JLabel("Cổng kết nối");
		lblMLaptop_4.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblMLaptop_4.setBounds(10, 148, 136, 31);
		panel_2.add(lblMLaptop_4);
		
		txtCongKetNoi = new JTextField();
		txtCongKetNoi.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtCongKetNoi.setColumns(10);
		txtCongKetNoi.setBounds(156, 148, 250, 31);
		panel_2.add(txtCongKetNoi);
		
		JLabel lblMLaptop_5 = new JLabel("Wifi");
		lblMLaptop_5.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblMLaptop_5.setBounds(10, 189, 136, 31);
		panel_2.add(lblMLaptop_5);
		
		txtWifi = new JTextField();
		txtWifi.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtWifi.setColumns(10);
		txtWifi.setBounds(156, 189, 250, 31);
		panel_2.add(txtWifi);
		
		JLabel lblMLaptop_5_1 = new JLabel("Pin");
		lblMLaptop_5_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblMLaptop_5_1.setBounds(10, 394, 136, 31);
		panel_2.add(lblMLaptop_5_1);
		
		txtPin = new JTextField();
		txtPin.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtPin.setColumns(10);
		txtPin.setBounds(156, 394, 250, 31);
		panel_2.add(txtPin);
		
		JLabel lblMLaptop_4_1 = new JLabel("Trọng lượng");
		lblMLaptop_4_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblMLaptop_4_1.setBounds(10, 353, 136, 31);
		panel_2.add(lblMLaptop_4_1);
		
		txtTrongLuong = new JTextField();
		txtTrongLuong.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtTrongLuong.setColumns(10);
		txtTrongLuong.setBounds(156, 353, 250, 31);
		panel_2.add(txtTrongLuong);
		
		txtSoLuongQuat = new JTextField();
		txtSoLuongQuat.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtSoLuongQuat.setColumns(10);
		txtSoLuongQuat.setBounds(156, 312, 250, 31);
		panel_2.add(txtSoLuongQuat);
		
		JLabel lblMLaptop_3_1 = new JLabel("Số lượng quạt");
		lblMLaptop_3_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblMLaptop_3_1.setBounds(10, 312, 136, 31);
		panel_2.add(lblMLaptop_3_1);
		
		JLabel lblMLaptop_2_1 = new JLabel("Webcam");
		lblMLaptop_2_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblMLaptop_2_1.setBounds(10, 271, 136, 31);
		panel_2.add(lblMLaptop_2_1);
		
		txtWebcam = new JTextField();
		txtWebcam.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtWebcam.setColumns(10);
		txtWebcam.setBounds(156, 271, 250, 31);
		panel_2.add(txtWebcam);
		
		txtBluetooth = new JTextField();
		txtBluetooth.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtBluetooth.setColumns(10);
		txtBluetooth.setBounds(156, 230, 250, 31);
		panel_2.add(txtBluetooth);
		
		JLabel lblMLaptop_1_1 = new JLabel("Bluetooth");
		lblMLaptop_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblMLaptop_1_1.setBounds(10, 230, 136, 31);
		panel_2.add(lblMLaptop_1_1);

		JButton btnLuu = new JButton("Lưu");
		btnLuu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new LaptopDAO().insert(getFormLaptop());
					Laptop laptop = new Laptop();
					laptop = new LaptopDAO().selectById(txtMaLaptop.getText());
					MsgBox.alert(getContentPane(), new MoTaDAO().insert(getFormMota(laptop.getId())));
					dispose();
					CreateLaptop.this.laptopManager.setPage(new LaptopDAO().selectAll());
					CreateLaptop.this.laptopManager.fillTableLaptop(new LaptopDAO().selectAllLaptop(1));
				} catch (Exception e2) {
					MsgBox.alert(getContentPane(), "Enter full data");
				}
			}
		});
		btnLuu.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnLuu.setBounds(384, 480, 128, 42);
		layeredPane.add(btnLuu);

		JButton btnHuy = new JButton("Hủy");
		btnHuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					setFormLaptop(new Laptop());
					setFormMoTa(new MoTa());
				} catch (Exception e2) {
					throw new RuntimeException(e2);
				}
			}
		});
		btnHuy.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnHuy.setBounds(798, 480, 128, 42);
		layeredPane.add(btnHuy);

		JButton btnUpdate = new JButton("Cập nhật");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Laptop laptop = new Laptop();
					laptop = new LaptopDAO().selectById(txtMaLaptop.getText());
					new MoTaDAO().update(getFormMota(laptop.getId()));
					MsgBox.alert(getContentPane(), new LaptopDAO().update(getFormLaptop()));
					dispose();
					CreateLaptop.this.laptopManager.fillTableLaptop(new LaptopDAO().selectAllLaptop(1));
				} catch (Exception e2) {
					MsgBox.alert(getContentPane(), "Enter full data");
				}
			}
		});
		btnUpdate.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnUpdate.setBounds(521, 480, 128, 42);
		layeredPane.add(btnUpdate);

		JButton btnXoa = new JButton("Xóa");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!Auth.isManager()) {
					MsgBox.alert(getContentPane(), "Bạn không có quyền xóa Laptop!");
				} else {
					if (MsgBox.confirm(getContentPane(), "Xác nhận xóa Laptop")) {
						MsgBox.alert(getContentPane(), new LaptopDAO().delete(txtMaLaptop.getText()));
						dispose();
						CreateLaptop.this.laptopManager.setPage(new LaptopDAO().selectAll());
						CreateLaptop.this.laptopManager.fillTableLaptop(new LaptopDAO().selectAllLaptop(1));
					}
				}
			}
		});
		btnXoa.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnXoa.setBounds(660, 480, 128, 42);
		layeredPane.add(btnXoa);
		
		JButton btnImport = new JButton("Import");
		btnImport.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnImport.setBounds(246, 480, 128, 42);
		layeredPane.add(btnImport);

		JLayeredPane layeredPane_1 = new JLayeredPane();
		tabbedPane.addTab("Biến thể", null, layeredPane_1, null);

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBounds(10, 10, 648, 440);
		layeredPane_1.add(panel_1_1);

		JLabel lblTnLaptop_3 = new JLabel("Mã Laptop");
		lblTnLaptop_3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTnLaptop_3.setBounds(10, 10, 136, 31);
		panel_1_1.add(lblTnLaptop_3);

		JLabel lblTnLaptop_1_1_2 = new JLabel("CPU");
		lblTnLaptop_1_1_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTnLaptop_1_1_2.setBounds(10, 172, 136, 31);
		panel_1_1.add(lblTnLaptop_1_1_2);

		JLabel lblTnLaptop_2_1 = new JLabel("RAM");
		lblTnLaptop_2_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTnLaptop_2_1.setBounds(10, 255, 136, 31);
		panel_1_1.add(lblTnLaptop_2_1);

		JLabel lblTnLaptop_1_2_3 = new JLabel("Màn hình");
		lblTnLaptop_1_2_3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTnLaptop_1_2_3.setBounds(10, 334, 136, 31);
		panel_1_1.add(lblTnLaptop_1_2_3);
		
		JLabel lblTnLaptop_3_1 = new JLabel("Mã Biến thể");
		lblTnLaptop_3_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTnLaptop_3_1.setBounds(10, 91, 136, 31);
		panel_1_1.add(lblTnLaptop_3_1);
		
		JLabel lblTnLaptop_1_1_1_2 = new JLabel("GPU");
		lblTnLaptop_1_1_1_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTnLaptop_1_1_1_2.setBounds(314, 10, 136, 31);
		panel_1_1.add(lblTnLaptop_1_1_1_2);
		
		JLabel lblTnLaptop_1_1_1_2_1 = new JLabel("Ổ cứng");
		lblTnLaptop_1_1_1_2_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTnLaptop_1_1_1_2_1.setBounds(314, 91, 136, 31);
		panel_1_1.add(lblTnLaptop_1_1_1_2_1);
		
		JLabel lblTnLaptop_1_2_3_1 = new JLabel("Hệ điều hành");
		lblTnLaptop_1_2_3_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTnLaptop_1_2_3_1.setBounds(314, 172, 136, 31);
		panel_1_1.add(lblTnLaptop_1_2_3_1);
		
		JLabel lblTnLaptop_1_2_3_1_1 = new JLabel("Giá");
		lblTnLaptop_1_2_3_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTnLaptop_1_2_3_1_1.setBounds(314, 255, 136, 31);
		panel_1_1.add(lblTnLaptop_1_2_3_1_1);
		
		txtMaLaptop_BT = new JTextField();
		txtMaLaptop_BT.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtMaLaptop_BT.setBounds(10, 51, 262, 31);
		panel_1_1.add(txtMaLaptop_BT);
		txtMaLaptop_BT.setColumns(10);
		
		txtMaBienThe = new JTextField();
		txtMaBienThe.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtMaBienThe.setColumns(10);
		txtMaBienThe.setBounds(10, 132, 262, 31);
		panel_1_1.add(txtMaBienThe);
		
		txtGia = new JTextField();
		txtGia.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtGia.setColumns(10);
		txtGia.setBounds(314, 296, 262, 31);
		panel_1_1.add(txtGia);
		
		cboCPU = new JComboBox<CPU>();
		fillCboCPU();
		cboCPU.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		cboCPU.setSelectedIndex(-1);
		cboCPU.setBounds(10, 213, 262, 31);
		panel_1_1.add(cboCPU);
		
		cboRAM = new JComboBox<RAM>();
		fillCboRAM();
		cboRAM.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		cboRAM.setSelectedIndex(-1);
		cboRAM.setBounds(10, 296, 262, 31);
		panel_1_1.add(cboRAM);
		
		cboManHinh = new JComboBox<ManHinh>();
		fillCboManHinh();
		cboManHinh.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		cboManHinh.setSelectedIndex(-1);
		cboManHinh.setBounds(10, 375, 262, 31);
		panel_1_1.add(cboManHinh);
		
		cboGPU = new JComboBox<GPU>();
		fillCboGPU();
		cboGPU.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		cboGPU.setSelectedIndex(-1);
		cboGPU.setBounds(314, 50, 262, 31);
		panel_1_1.add(cboGPU);
		
		cboOCung = new JComboBox<OCung>();
		fillCboOCung();
		cboOCung.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		cboOCung.setSelectedIndex(-1);
		cboOCung.setBounds(314, 132, 262, 31);
		panel_1_1.add(cboOCung);
		
		cboHeDieuHanh = new JComboBox<HeDieuHanh>();
		fillHeDieuHanh();
		cboHeDieuHanh.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		cboHeDieuHanh.setSelectedIndex(-1);
		cboHeDieuHanh.setBounds(314, 213, 262, 31);
		panel_1_1.add(cboHeDieuHanh);
		
		JLabel lblTnLaptop_1_2_3_1_2_1 = new JLabel("Màu sắc");
		lblTnLaptop_1_2_3_1_2_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblTnLaptop_1_2_3_1_2_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTnLaptop_1_2_3_1_2_1.setBounds(314, 334, 136, 31);
		panel_1_1.add(lblTnLaptop_1_2_3_1_2_1);
		
		txtMauSac = new JTextField();
		txtMauSac.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtMauSac.setColumns(10);
		txtMauSac.setBounds(314, 375, 262, 31);
		panel_1_1.add(txtMauSac);
		
		JButton btnAddCPU = new JButton("");
		btnAddCPU.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CPUManager().setVisible(true);
			}
		});
		btnAddCPU.setIcon(new ImageIcon(CreateLaptop.class.getResource("/com/shoplaptop/icon/Add.png")));
		btnAddCPU.setBounds(276, 213, 34, 31);
		panel_1_1.add(btnAddCPU);
		
		JButton btnAddRAM = new JButton("");
		btnAddRAM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new RAMManager().setVisible(true);
			}
		});
		btnAddRAM.setIcon(new ImageIcon(CreateLaptop.class.getResource("/com/shoplaptop/icon/Add.png")));
		btnAddRAM.setBounds(276, 296, 34, 31);
		panel_1_1.add(btnAddRAM);
		
		JButton btnAddManHinh = new JButton("");
		btnAddManHinh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ManHinhManager().setVisible(true);
			}
		});
		btnAddManHinh.setIcon(new ImageIcon(CreateLaptop.class.getResource("/com/shoplaptop/icon/Add.png")));
		btnAddManHinh.setBounds(276, 375, 34, 31);
		panel_1_1.add(btnAddManHinh);
		
		JButton btnAddGPU = new JButton("");
		btnAddGPU.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GPUManager().setVisible(true);
			}
		});
		btnAddGPU.setIcon(new ImageIcon(CreateLaptop.class.getResource("/com/shoplaptop/icon/Add.png")));
		btnAddGPU.setBounds(581, 51, 34, 31);
		panel_1_1.add(btnAddGPU);
		
		JButton btnAddOCung = new JButton("");
		btnAddOCung.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new OCungManager().setVisible(true);
			}
		});
		btnAddOCung.setIcon(new ImageIcon(CreateLaptop.class.getResource("/com/shoplaptop/icon/Add.png")));
		btnAddOCung.setBounds(581, 132, 34, 31);
		panel_1_1.add(btnAddOCung);
		
		JButton btnAddHeDieuHanh = new JButton("");
		btnAddHeDieuHanh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new HeDieuHanhManager().setVisible(true);
			}
		});
		btnAddHeDieuHanh.setIcon(new ImageIcon(CreateLaptop.class.getResource("/com/shoplaptop/icon/Add.png")));
		btnAddHeDieuHanh.setBounds(581, 213, 34, 31);
		panel_1_1.add(btnAddHeDieuHanh);

		JButton btnHoanThanh = new JButton("Hoàn thành");
		btnHoanThanh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MsgBox.alert(getContentPane(), new BienTheDAO().insert(getFormBienThe(new LaptopDAO().selectById(txtMaLaptop_BT.getText()).getId())));
					dispose();
					CreateLaptop.this.laptopManager.fillTableLaptop(new LaptopDAO().selectAllLaptop(1));
				} catch (Exception e2) {
					MsgBox.alert(getContentPane(), "Enter full data");
				}
			}
		});
		btnHoanThanh.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnHoanThanh.setBounds(184, 493, 128, 42);
		layeredPane_1.add(btnHoanThanh);

		JButton btnCancelBT = new JButton("Hủy");
		btnCancelBT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					clearFormBienThe();
				} catch (Exception e2) {
					throw new RuntimeException(e2);
				}
			}
		});
		btnCancelBT.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnCancelBT.setBounds(598, 493, 128, 42);
		layeredPane_1.add(btnCancelBT);

		JButton btnCapNhat = new JButton("Cập nhật");
		btnCapNhat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MsgBox.alert(getContentPane(), new BienTheDAO().update(getFormBienThe(new LaptopDAO().selectById(txtMaLaptop_BT.getText()).getId())));
					dispose();
					CreateLaptop.this.laptopManager.fillTableLaptop(new LaptopDAO().selectAllLaptop(1));
				} catch (Exception e2) {
					MsgBox.alert(getContentPane(), "Enter full data");
				}
			}
		});
		btnCapNhat.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnCapNhat.setBounds(322, 493, 128, 42);
		layeredPane_1.add(btnCapNhat);
		
		JPanel panel = new JPanel();
		panel.setBounds(668, 10, 258, 326);
		layeredPane_1.add(panel);
		panel.setLayout(null);
		
		lblAnh = new JLabel("Choose Image");
		lblAnh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				chooseImage();
			}
		});
		lblAnh.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblAnh.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnh.setBounds(0, 0, 258, 326);
		panel.add(lblAnh);
		
		JButton btnXoaBT = new JButton("Xóa");
		btnXoaBT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!Auth.isManager()) {
					MsgBox.alert(getContentPane(), "Bạn không có quyền xóa Laptop!");
				} else {
					if (MsgBox.confirm(getContentPane(), "Xác nhận xóa Laptop")) {
						MsgBox.alert(getContentPane(), new BienTheDAO().delete(txtMaBienThe.getText()));
						dispose();
						CreateLaptop.this.laptopManager.fillTableLaptop(new LaptopDAO().selectAllLaptop(1));
					}
				}
			}
		});
		btnXoaBT.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnXoaBT.setBounds(460, 493, 128, 42);
		layeredPane_1.add(btnXoaBT);
		
		JLayeredPane layeredPane_2 = new JLayeredPane();
		tabbedPane.addTab("Nhập hàng", null, layeredPane_2, null);
		
		JLabel lblNewLabel_2 = new JLabel("Tên máy");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(10, 126, 155, 30);
		layeredPane_2.add(lblNewLabel_2);
		
		lblTenMay = new JLabel("---");
		lblTenMay.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTenMay.setBounds(175, 126, 247, 30);
		layeredPane_2.add(lblTenMay);
		
		JLabel lblNewLabel_2_1 = new JLabel("SerialNumber");
		lblNewLabel_2_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_2_1.setBounds(474, 102, 171, 30);
		layeredPane_2.add(lblNewLabel_2_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(474, 154, 452, 147);
		layeredPane_2.add(scrollPane);
		
		txtSerialNumber = new JTextArea();
		txtSerialNumber.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		scrollPane.setViewportView(txtSerialNumber);
		
		JLabel lblNewLabel_2_2 = new JLabel("Mã biến thể");
		lblNewLabel_2_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_2_2.setBounds(10, 67, 155, 30);
		layeredPane_2.add(lblNewLabel_2_2);
		
		lblMaBienThe = new JLabel("---");
		lblMaBienThe.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblMaBienThe.setBounds(175, 67, 247, 30);
		layeredPane_2.add(lblMaBienThe);
		
		JLabel lblNewLabel_2_2_1 = new JLabel("Mã đơn nhập");
		lblNewLabel_2_2_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_2_2_1.setBounds(10, 10, 155, 30);
		layeredPane_2.add(lblNewLabel_2_2_1);
		
		lblMaDonNhap = new JLabel("---");
		lblMaDonNhap.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblMaDonNhap.setBounds(175, 10, 247, 30);
		layeredPane_2.add(lblMaDonNhap);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Nhà cung cấp");
		lblNewLabel_2_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_2_1_1.setBounds(474, 10, 171, 30);
		layeredPane_2.add(lblNewLabel_2_1_1);
		
		cboNhaCC = new JComboBox<NhaCungCap>();
		cboNhaCC.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		fillCboNCC();
		cboNhaCC.setSelectedIndex(-1);
		cboNhaCC.setBounds(474, 62, 413, 30);
		layeredPane_2.add(cboNhaCC);
		
		JButton btnNhapHang = new JButton("Nhập hàng");
		btnNhapHang.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnNhapHang.setBounds(28, 216, 129, 31);
		layeredPane_2.add(btnNhapHang);
		
		JButton btnImportFile = new JButton("Import file");
		btnImportFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				importFile();
			}
		});
		btnImportFile.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnImportFile.setBounds(167, 216, 129, 31);
		layeredPane_2.add(btnImportFile);
		
		JButton btnCancel = new JButton("Hủy");
		btnCancel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnCancel.setBounds(306, 216, 116, 31);
		layeredPane_2.add(btnCancel);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 338, 452, 197);
		layeredPane_2.add(scrollPane_1);
		
		modelDonNhap = new DefaultTableModel();
		String[] colsDN = {"Mã đơn nhập","Nhà cung cấp","Mã nhân viên","Ngày tạo","Tổng tiền"};
		modelDonNhap.setColumnIdentifiers(colsDN);
		
		tblDonNhap = new JTable(modelDonNhap);
		scrollPane_1.setViewportView(tblDonNhap);
		
		JScrollPane scrollPane_1_1 = new JScrollPane();
		scrollPane_1_1.setBounds(474, 338, 452, 197);
		layeredPane_2.add(scrollPane_1_1);
		
		modelCTDonNhap = new DefaultTableModel();
		String[] colsCTDN = {"Mã đơn nhập","SerialNumber"};
		modelCTDonNhap.setColumnIdentifiers(colsCTDN);
		
		tblCTDonNhap = new JTable(modelCTDonNhap);
		scrollPane_1_1.setViewportView(tblCTDonNhap);
		
		JButton btnAddNCC = new JButton("");
		btnAddNCC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				CreateLaptop.this.laptopManager.tabbedPane.setSelectedIndex(1);
			}
		});
		btnAddNCC.setIcon(new ImageIcon(CreateLaptop.class.getResource("/com/shoplaptop/icon/Add.png")));
		btnAddNCC.setBounds(892, 62, 34, 31);
		layeredPane_2.add(btnAddNCC);

		cboHang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Hang hang = (Hang) cboHang.getSelectedItem();
				if (hang != null) {
					fillCboDonglaptop(hang.getTenHang());
					cboDongLaptop.setSelectedIndex(-1);
				}
			}
		});
		btnNhapHang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String [] lines = txtSerialNumber.getText().split("\n");
					int id_BienThe = new BienTheDAO().selectById(lblMaBienThe.getText()).getId();
					for (String string : lines) {
						if (!string.trim().isEmpty()) {
							SerialNumber serialNumber = new SerialNumber();
							serialNumber.setId_BienThe(id_BienThe);
							serialNumber.setSerialNumber(string);
							serialNumber.setTrangThai(true);
							new SerialNumberDAO().insert(serialNumber);
						}
					}
					MsgBox.alert(getContentPane(), "Add thành công");
					dispose();
					CreateLaptop.this.laptopManager.fillTableLaptop(new LaptopDAO().selectAllLaptop(1));
				} catch (Exception e2) {
					MsgBox.alert(getContentPane(), "SerialNumber is exist");
				}
			}
		});
		
	}
	
	public static void fillCboPhanLoai() {
		DefaultComboBoxModel<PhanLoai> model = (DefaultComboBoxModel<PhanLoai>) cboPhanLoai.getModel();
		model.removeAllElements();
		List<PhanLoai> list = new PhanLoaiDAO().selectAll();
		for (PhanLoai phanLoai : list) {
			model.addElement(phanLoai);
		}
	}
	
	public static void fillCboHang() {
		DefaultComboBoxModel<Hang> model = (DefaultComboBoxModel<Hang>) cboHang.getModel();
		model.removeAllElements();
		List<Hang> list = new HangDAO().selectAll();
		for (Hang hang : list) {
			model.addElement(hang);
		}
	}
	
	public static void fillCboDonglaptop(String hang) {
		DefaultComboBoxModel<DongLaptop> model = (DefaultComboBoxModel<DongLaptop>) cboDongLaptop.getModel();
		model.removeAllElements();
		List<DongLaptop> list = new DongLaptopDAO().selectAllDongLaptop(hang);
		for (DongLaptop dongLaptop : list) {
			model.addElement(dongLaptop);
		}
	}
	
	public static void fillCboCPU() {
		DefaultComboBoxModel<CPU> model = (DefaultComboBoxModel<CPU>) cboCPU.getModel();
		model.removeAllElements();
		List<CPU> list = new CPUDAO().selectAll();
		for (CPU cpu : list) {
			model.addElement(cpu);
		}
	}
	
	public static void fillCboGPU() {
		DefaultComboBoxModel<GPU> model = (DefaultComboBoxModel<GPU>) cboGPU.getModel();
		model.removeAllElements();
		List<GPU> list = new GPUDAO().selectAll();
		for (GPU gpu : list) {
			model.addElement(gpu);
		}
	}
	
	public static void fillCboManHinh() {
		DefaultComboBoxModel<ManHinh> model = (DefaultComboBoxModel<ManHinh>) cboManHinh.getModel();
		model.removeAllElements();
		List<ManHinh> list = new ManHinhDAO().selectAll();
		for (ManHinh manHinh : list) {
			model.addElement(manHinh);
		}
	}
	
	public static void fillCboOCung() {
		DefaultComboBoxModel<OCung> model = (DefaultComboBoxModel<OCung>) cboOCung.getModel();
		model.removeAllElements();
		List<OCung> list = new OCungDAO().selectAll();
		for (OCung oCung : list) {
			model.addElement(oCung);
		}
	}
	
	public static void fillHeDieuHanh() {
		DefaultComboBoxModel<HeDieuHanh> model = (DefaultComboBoxModel<HeDieuHanh>) cboHeDieuHanh.getModel();
		model.removeAllElements();
		List<HeDieuHanh> list = new HeDieuHanhDAO().selectAll();
		for (HeDieuHanh heDieuHanh : list) {
			model.addElement(heDieuHanh);
		}
	}
	
	public static void fillCboRAM() {
		DefaultComboBoxModel<RAM> model = (DefaultComboBoxModel<RAM>) cboRAM.getModel();
		model.removeAllElements();
		List<RAM> list = new RAMDAO().selectAll();
		for (RAM ram : list) {
			model.addElement(ram);
		}
	}
	
	public static void fillCboNCC() {
		DefaultComboBoxModel<NhaCungCap> model = (DefaultComboBoxModel<NhaCungCap>) cboNhaCC.getModel();
		model.removeAllElements();
		List<NhaCungCap> list = new NhaCungCapDAO().selectAll();
		for (NhaCungCap nhaCungCap : list) {
			model.addElement(nhaCungCap);
		}
	}
	
	public static void setFormLaptop(Laptop laptop) {
		txtMaLaptop.setText(laptop.getMaLaptop());
		txtTenLaptop.setText(laptop.getTenLaptop());
		PhanLoai phanLoai = new PhanLoaiDAO().selectById(laptop.getPhanLoai());
		cboPhanLoai.setSelectedItem(phanLoai);
		Hang hang = new HangDAO().selectById(laptop.getHang());
		cboHang.setSelectedItem(hang);
		DongLaptop dongLaptop = new DongLaptopDAO().selectById(laptop.getDongLaptop());
		cboDongLaptop.setSelectedItem(dongLaptop);
		txtNamSanXuat.setText(laptop.getNamSanXuat()+"");
	}
	
	public static void setFormMoTa(MoTa moTa) {
		txtSoLuongLoa.setText(moTa.getSoLuongLoa()+"");
		txtKeyboard.setText(moTa.getKeyBoard());
		txtTouchPad.setText(moTa.getTouchPad());
		txtCongKetNoi.setText(moTa.getCongKetNoi());
		txtWifi.setText(moTa.getWifi());
		txtBluetooth.setText(moTa.getBluetooth());
		txtWebcam.setText(moTa.getWebcam());
		txtSoLuongQuat.setText(moTa.getSoLuongQuat()+"");
		txtTrongLuong.setText(moTa.getTrongLuong()+"");
		txtPin.setText(moTa.getPin());
	}
	
	public static void setFormBienThe(BienThe bienThe) {
		txtMaBienThe.setText(bienThe.getMaBienThe());
		txtMaLaptop_BT.setText(bienThe.getMaLaptop());
		CPU cpu = new CPUDAO().selectById(bienThe.getId_CPU());
		cboCPU.setSelectedItem(cpu);
		RAM ram = new RAMDAO().selectById(bienThe.getId_RAM());
		cboRAM.setSelectedItem(ram);
		ManHinh manHinh = new ManHinhDAO().selectById(bienThe.getId_ManHinh());
		cboManHinh.setSelectedItem(manHinh);
		GPU gpu = new GPUDAO().selectById(bienThe.getId_GPU());
		cboGPU.setSelectedItem(gpu);
		OCung oCung = new OCungDAO().selectById(bienThe.getId_OCung());
		cboOCung.setSelectedItem(oCung);
		HeDieuHanh heDieuHanh = new HeDieuHanhDAO().selectById(bienThe.getId_HeDieuHanh());
		cboHeDieuHanh.setSelectedItem(heDieuHanh);
		txtGia.setText(LaptopManager.decimalFormat(bienThe.getGia()));
		txtMauSac.setText(bienThe.getMauSac());
		lblAnh.setToolTipText(bienThe.getHinh());
		lblAnh.setIcon(XImage.read(bienThe.getHinh()));
	}
	
	public Laptop getFormLaptop() {
		Laptop laptop = new Laptop();
		laptop.setMaLaptop(txtMaLaptop.getText());
		laptop.setTenLaptop(txtTenLaptop.getText());
		PhanLoai phanLoai = (PhanLoai) cboPhanLoai.getSelectedItem();
		laptop.setPhanLoai(phanLoai.getId());
		DongLaptop dongLaptop = (DongLaptop) cboDongLaptop.getSelectedItem();
		laptop.setDongLaptop(dongLaptop.getId());
		laptop.setNamSanXuat(Integer.valueOf(txtNamSanXuat.getText()));
		return laptop;
	}
	
	public MoTa getFormMota(int id) {
		MoTa moTa = new MoTa();
		moTa.setId_MaLaptop(id);
		moTa.setSoLuongLoa(Integer.valueOf(txtSoLuongLoa.getText()));
		moTa.setKeyBoard(txtKeyboard.getText());
		moTa.setTouchPad(txtTouchPad.getText());
		moTa.setCongKetNoi(txtCongKetNoi.getText());
		moTa.setWifi(txtWifi.getText());
		moTa.setBluetooth(txtBluetooth.getText());
		moTa.setWebcam(txtWebcam.getText());
		moTa.setSoLuongQuat(Integer.valueOf(txtSoLuongQuat.getText()));
		moTa.setTrongLuong(Double.valueOf(txtTrongLuong.getText()));
		moTa.setPin(txtPin.getText());
		return moTa;
	}
	
	public BienThe getFormBienThe(int id) {
		BienThe bienThe = new BienThe();
		bienThe.setId_Laptop(id);
		bienThe.setMaBienThe(txtMaBienThe.getText());
		CPU cpu = (CPU) cboCPU.getSelectedItem();
		bienThe.setId_CPU(cpu.getId());
		RAM ram = (RAM) cboRAM.getSelectedItem();
		bienThe.setId_RAM(ram.getId());
		ManHinh manHinh = (ManHinh) cboManHinh.getSelectedItem();
		bienThe.setId_ManHinh(manHinh.getId());
		GPU gpu = (GPU) cboGPU.getSelectedItem();
		bienThe.setId_GPU(gpu.getId());
		OCung oCung = (OCung) cboOCung.getSelectedItem();
		bienThe.setId_OCung(oCung.getId());
		bienThe.setMauSac(txtMauSac.getText());
		HeDieuHanh heDieuHanh = (HeDieuHanh) cboHeDieuHanh.getSelectedItem();
		bienThe.setId_HeDieuHanh(heDieuHanh.getId());
		bienThe.setGia(BigDecimal.valueOf(Double.valueOf(txtGia.getText())));
		bienThe.setHinh(lblAnh.getToolTipText());
		return bienThe;
	}
	
	public void chooseImage() {
		try {
			JFileChooser fileChooser = new JFileChooser();
			if (fileChooser.showOpenDialog(getContentPane()) == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				XImage.save(file);
				ImageIcon icon = XImage.read(file.getName());
				lblAnh.setIcon(icon);
				lblAnh.setToolTipText(file.getName());
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void clearFormBienThe() {
		txtMaBienThe.setText(null);
		txtMaLaptop_BT.setText(null);
		cboCPU.setSelectedIndex(-1);
		cboRAM.setSelectedIndex(-1);
		cboManHinh.setSelectedIndex(-1);
		cboGPU.setSelectedIndex(-1);
		cboOCung.setSelectedIndex(-1);
		cboHeDieuHanh.setSelectedIndex(-1);
		txtGia.setText(null);
		txtMauSac.setText(null);
		lblAnh.setToolTipText(null);
		lblAnh.setIcon(null);
	}
	
	public void importFile() {
		JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            try {
                FileInputStream file = new FileInputStream(selectedFile);

                // Tạo một workbook mới từ file Excel
                @SuppressWarnings("resource")
				Workbook workbook = new XSSFWorkbook(file);

                // Lấy sheet đầu tiên từ workbook
                Sheet sheet = workbook.getSheetAt(0);

                // Duyệt qua từng hàng một
                for (Row row : sheet) {
                    // Duyệt qua từng cột trong hàng
                    for (Cell cell : row) {
                        // In ra giá trị của ô
//                        System.out.println(cell.toString());
                    	txtSerialNumber.append(cell.toString()+"\n");
                    }
                }

                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
}
