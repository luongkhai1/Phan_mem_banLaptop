package com.shoplaptop.ui;

import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.List;

import javax.swing.JSeparator;
import javax.swing.JPanel;

import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.shoplaptop.dao.NhanVienService;
import com.shoplaptop.dao.TaiKhoanDAO;
import com.shoplaptop.entity.NhanVien;
import com.shoplaptop.entity.TaiKhoan;

import com.shoplaptop.utils.MsgBox;
import com.shoplaptop.utils.XDate;
import com.shoplaptop.utils.XImage;

import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import org.apache.poi.ss.usermodel.Row;

@SuppressWarnings("serial")
public class QLNhanVien extends JDialog {
	private final JPanel contentPanel = new JPanel();
	private JTextField txtmanhanvien;
	private JTextField txttennhanvien;
	private JTextField txtdiachi;
	private JTextField txtemail;
	private JTextField txtngaysinh;
	private JTextField txttendangnhap;
	private JTextField txtsodienthoai;
	private JTable tblquanlynhanvien;
	private ButtonGroup buttonGroup;
	private DefaultTableModel model;
	private JRadioButton rdonam;
	private JRadioButton rdonu;
	private JLabel lblimage;
	private NhanVienService service = new NhanVienService();
	private JRadioButton rdonhanvien;
	private JRadioButton rdoquanly;

	private ButtonGroup buttonGroup_1;
	private JPasswordField txtmatkhau;
	private TaiKhoanDAO dao = new TaiKhoanDAO();
	private JTextField txttimkiem;
	private int index = 1;

	String selectSQl_PhânTrang = "SELECT NhanVien.MaNV,HoTen,SoDienThoai,NgaySinh,GioiTinh,Email,Hinh,DiaChi,VaiTro FROM dbo.NhanVien JOIN dbo.TaiKhoan ON TaiKhoan.MaNV = NhanVien.MaNV Where NhanVien.MaNV like ? OR HoTen LIKE ? OR SoDienThoai LIKE ?";
	String SELECTBYID = "\r\n" + "SELECT * FROM\r\n"
			+ "    (SELECT ROW_NUMBER() OVER (ORDER BY  NhanVien.MaNV DESC) AS rownum,   NhanVien.MaNV,HoTen,SoDienThoai,NgaySinh,GioiTinh,Email,Hinh,DiaChi,VaiTro FROM dbo.NhanVien JOIN dbo.TaiKhoan ON TaiKhoan.MaNV = NhanVien.MaNV Where NhanVien.MaNV like ? OR HoTen LIKE ? OR SoDienThoai LIKE ?)\r\n"
			+ "    AS temp\r\n" + "    WHERE rownum BETWEEN ? AND ?";
	String FindQuanLy = "SELECT NhanVien.MaNV,HoTen,SoDienThoai,NgaySinh,GioiTinh,Email,Hinh,DiaChi,VaiTro FROM dbo.NhanVien JOIN dbo.TaiKhoan ON TaiKhoan.MaNV = NhanVien.MaNV where vaitro = 1";
	String FindNhanVien = "SELECT NhanVien.MaNV,HoTen,SoDienThoai,NgaySinh,GioiTinh,Email,Hinh,DiaChi,VaiTro FROM dbo.NhanVien JOIN dbo.TaiKhoan ON TaiKhoan.MaNV = NhanVien.MaNV where vaitro = 0";
	String FilterQuanLy = "SELECT * FROM (SELECT ROW_NUMBER() OVER (ORDER BY  NhanVien.MaNV DESC) AS rownum,   NhanVien.MaNV,HoTen,SoDienThoai,NgaySinh,GioiTinh,Email,Hinh,DiaChi,VaiTro \r\n"
			+ "FROM dbo.NhanVien JOIN dbo.TaiKhoan ON TaiKhoan.MaNV = NhanVien.MaNV Where vaitro = 1) AS temp\r\n"
			+ "WHERE rownum BETWEEN ? AND ?";
	String FilterNhanVien = "SELECT * FROM (SELECT ROW_NUMBER() OVER (ORDER BY  NhanVien.MaNV DESC) AS rownum,   NhanVien.MaNV,HoTen,SoDienThoai,NgaySinh,GioiTinh,Email,Hinh,DiaChi,VaiTro \r\n"
			+ "FROM dbo.NhanVien JOIN dbo.TaiKhoan ON TaiKhoan.MaNV = NhanVien.MaNV Where vaitro = 0) AS temp\r\n"
			+ "WHERE rownum BETWEEN ? AND ?";
	String query = "\r\n" + "SELECT * FROM\r\n"
			+ "    (SELECT ROW_NUMBER() OVER (ORDER BY  NhanVien.MaNV DESC) AS rownum,   NhanVien.MaNV,HoTen,SoDienThoai,NgaySinh,GioiTinh,Email,Hinh,DiaChi,VaiTro FROM dbo.NhanVien JOIN dbo.TaiKhoan ON TaiKhoan.MaNV = NhanVien.MaNV)\r\n"
			+ "    AS temp\r\n" + "    WHERE rownum BETWEEN ? AND ?";
	String FindByQuanLy = "\r\n" + "SELECT * FROM\r\n"
			+ "    (SELECT ROW_NUMBER() OVER (ORDER BY  NhanVien.MaNV DESC) AS rownum,   NhanVien.MaNV,HoTen,SoDienThoai,NgaySinh,GioiTinh,Email,Hinh,DiaChi,VaiTro FROM dbo.NhanVien JOIN dbo.TaiKhoan ON TaiKhoan.MaNV = NhanVien.MaNV Where NhanVien.MaNV like ? OR HoTen LIKE ? OR SoDienThoai LIKE ? and VaiTro =1)\r\n"
			+ "    AS temp\r\n" + "    WHERE rownum BETWEEN ? AND ?";
	String FindByNhanVien = "\r\n" + "SELECT * FROM\r\n"
			+ "    (SELECT ROW_NUMBER() OVER (ORDER BY  NhanVien.MaNV DESC) AS rownum,   NhanVien.MaNV,HoTen,SoDienThoai,NgaySinh,GioiTinh,Email,Hinh,DiaChi,VaiTro FROM dbo.NhanVien JOIN dbo.TaiKhoan ON TaiKhoan.MaNV = NhanVien.MaNV Where NhanVien.MaNV like ? OR HoTen LIKE ? OR SoDienThoai LIKE ? and VaiTro =0)\r\n"
			+ "    AS temp\r\n" + "    WHERE rownum BETWEEN ? AND ?";

	private JLabel lbldem;
	private JLabel lblTo;
	private JLabel lblTo_1;
	private double size;
	private int rows;
	private JComboBox<String> comboBox;
	private JButton btnchitiet;
	private JButton btnNewButton_1;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QLNhanVien dialog = new QLNhanVien();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public QLNhanVien() {
		getContentPane().setFont(new Font("Times New Roman", Font.PLAIN, 17));
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(QLNhanVien.class.getResource("/com/shoplaptop/icon/365_1.png")));
		setTitle("ShopLapTop365");
		setBounds(100, 100, 975, 612);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Quản lý nhân viên ");
		lblNewLabel.setIcon(new ImageIcon(QLNhanVien.class.getResource("/com/shoplaptop/icon/User.png")));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel.setBounds(325, 10, 245, 44);
		getContentPane().add(lblNewLabel);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 64, 1016, 2);
		getContentPane().add(separator);

		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 178, 143, 169);
		getContentPane().add(panel);
		panel.setLayout(null);

		lblimage = new JLabel("          ChooseImage");
		lblimage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				chooseImage();
			}
		});
		lblimage.setBounds(0, 0, 143, 188);
		panel.add(lblimage);

		JLabel lblNewLabel_2 = new JLabel("Mã nhân viên ");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblNewLabel_2.setBounds(163, 194, 101, 13);
		getContentPane().add(lblNewLabel_2);

		txtmanhanvien = new JTextField();
		txtmanhanvien.setBounds(274, 193, 154, 19);
		getContentPane().add(txtmanhanvien);
		txtmanhanvien.setColumns(10);

		JLabel lblNewLabel_2_1 = new JLabel("Tên nhân viên ");
		lblNewLabel_2_1.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblNewLabel_2_1.setBounds(163, 247, 101, 13);
		getContentPane().add(lblNewLabel_2_1);

		txttennhanvien = new JTextField();
		txttennhanvien.setColumns(10);
		txttennhanvien.setBounds(274, 246, 154, 19);
		getContentPane().add(txttennhanvien);

		JLabel lblNewLabel_2_1_1 = new JLabel("Địa Chỉ ");
		lblNewLabel_2_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblNewLabel_2_1_1.setBounds(163, 291, 90, 13);
		getContentPane().add(lblNewLabel_2_1_1);

		txtdiachi = new JTextField();
		txtdiachi.setColumns(10);
		txtdiachi.setBounds(274, 290, 154, 19);
		getContentPane().add(txtdiachi);

		JLabel lblNewLabel_2_1_2 = new JLabel("Email");
		lblNewLabel_2_1_2.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblNewLabel_2_1_2.setBounds(438, 194, 90, 13);
		getContentPane().add(lblNewLabel_2_1_2);

		txtemail = new JTextField();
		txtemail.setColumns(10);
		txtemail.setBounds(526, 193, 154, 19);
		getContentPane().add(txtemail);

		JLabel lblNewLabel_2_1_2_1 = new JLabel("Ngày sinh");
		lblNewLabel_2_1_2_1.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblNewLabel_2_1_2_1.setBounds(438, 245, 90, 17);
		getContentPane().add(lblNewLabel_2_1_2_1);

		txtngaysinh = new JTextField();
		txtngaysinh.setColumns(10);
		txtngaysinh.setBounds(526, 246, 154, 19);
		getContentPane().add(txtngaysinh);

		JLabel lblNewLabel_2_1_2_1_1 = new JLabel("Giới tính ");
		lblNewLabel_2_1_2_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblNewLabel_2_1_2_1_1.setBounds(438, 291, 90, 13);
		getContentPane().add(lblNewLabel_2_1_2_1_1);

		rdonam = new JRadioButton("Nam ");
		rdonam.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		rdonam.setBounds(526, 287, 72, 21);
		getContentPane().add(rdonam);

		rdonu = new JRadioButton("Nữ ");
		rdonu.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		rdonu.setBounds(600, 287, 72, 21);
		getContentPane().add(rdonu);

		JLabel lblNewLabel_2_1_2_2 = new JLabel("Tên đăng nhập ");
		lblNewLabel_2_1_2_2.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblNewLabel_2_1_2_2.setBounds(690, 191, 101, 19);
		getContentPane().add(lblNewLabel_2_1_2_2);

		txttendangnhap = new JTextField();
		txttendangnhap.setColumns(10);
		txttendangnhap.setBounds(801, 193, 154, 19);
		getContentPane().add(txttendangnhap);

		JLabel lblNewLabel_2_1_2_3 = new JLabel("Mật khẩu ");
		lblNewLabel_2_1_2_3.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblNewLabel_2_1_2_3.setBounds(690, 244, 90, 18);
		getContentPane().add(lblNewLabel_2_1_2_3);

		JLabel lblNewLabel_2_1_2_4 = new JLabel("Số điện thoại ");
		lblNewLabel_2_1_2_4.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblNewLabel_2_1_2_4.setBounds(690, 288, 101, 18);
		getContentPane().add(lblNewLabel_2_1_2_4);

		txtsodienthoai = new JTextField();
		txtsodienthoai.setColumns(10);
		txtsodienthoai.setBounds(801, 290, 154, 19);
		getContentPane().add(txtsodienthoai);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 370, 941, 156);
		getContentPane().add(scrollPane);

		model = new DefaultTableModel();
		String[] colums = new String[] { "Mã nhân viên", "Tên nhân viên", "Địa chỉ", "Email", "Ngày Sinh", "Giới tính",
				"Số điện thoại", "Hình", "Vai Trò" };
		model.setColumnIdentifiers(colums);

		tblquanlynhanvien = new JTable(model);
		tblquanlynhanvien.setRowMargin(3);
		tblquanlynhanvien.setRowHeight(25);
		tblquanlynhanvien.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				index = tblquanlynhanvien.getSelectedRow();
				setForm(service.selectById((String) tblquanlynhanvien.getValueAt(index, 0)));
				setForm_1(dao.selectbymanhanvien((String) tblquanlynhanvien.getValueAt(index, 0)));
				btnchitiet.setEnabled(true);

			}
		});
		scrollPane.setViewportView(tblquanlynhanvien);
		filltable(service.sellectAllNhanVien(1));

		buttonGroup = new ButtonGroup();
		buttonGroup.add(rdonu);
		buttonGroup.add(rdonam);

		JButton btnthemmoi = new JButton("Thêm mới ");
		btnthemmoi.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		btnthemmoi.setIcon(new ImageIcon(QLNhanVien.class.getResource("/com/shoplaptop/icon/Add.png")));
		btnthemmoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (vali()) {
					int insert = JOptionPane.showConfirmDialog(contentPanel, "Bạn có muốn thêm không");
					if (insert == JOptionPane.YES_OPTION) {
						insert();
						settable(service.selectAll());
						filltable(service.sellectAllNhanVien(index + 1));
						lbldem.setText("1");
					}
				}
			}
		});
		btnthemmoi.setBounds(792, 100, 143, 34);
		getContentPane().add(btnthemmoi);

		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "Quản lý", "Nhân Viên" }));
		comboBox.setSelectedIndex(-1);
		comboBox.setBounds(604, 102, 178, 34);
		getContentPane().add(comboBox);

		JButton btntimkiem = new JButton("Tìm kiếm ");
		btntimkiem.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		btntimkiem.setIcon(new ImageIcon(QLNhanVien.class.getResource("/com/shoplaptop/icon/Search.png")));
		btntimkiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FindMaNV();
			}
		});
		btntimkiem.setBounds(459, 100, 135, 34);
		getContentPane().add(btntimkiem);

		JLabel lblNewLabel_2_1_2_1_1_1 = new JLabel("Vai trò");
		lblNewLabel_2_1_2_1_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblNewLabel_2_1_2_1_1_1.setBounds(163, 334, 90, 13);
		getContentPane().add(lblNewLabel_2_1_2_1_1_1);

		rdonhanvien = new JRadioButton("Nhân Viên ");
		rdonhanvien.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		rdonhanvien.setBounds(281, 326, 108, 21);
		getContentPane().add(rdonhanvien);

		rdoquanly = new JRadioButton("Quản Lý");
		rdoquanly.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		rdoquanly.setBounds(391, 326, 90, 21);
		getContentPane().add(rdoquanly);

		buttonGroup_1 = new ButtonGroup();

		buttonGroup_1.add(rdonhanvien);
		buttonGroup_1.add(rdoquanly);

		txtmatkhau = new JPasswordField();
		txtmatkhau.setBounds(801, 246, 154, 19);
		getContentPane().add(txtmatkhau);

		txttimkiem = new JTextField();
		txttimkiem.setBounds(10, 104, 439, 27);
		getContentPane().add(txttimkiem);
		txttimkiem.setColumns(10);

		lbldem = new JLabel("1");
		lbldem.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lbldem.setBounds(779, 541, 16, 13);
		getContentPane().add(lbldem);
		lblTo = new JLabel("to");
		lblTo.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblTo.setBounds(792, 541, 16, 13);
		getContentPane().add(lblTo);

		lblTo_1 = new JLabel("-");
		lblTo_1.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblTo_1.setBounds(818, 541, 16, 13);
		getContentPane().add(lblTo_1);

		JButton btnprev = new JButton("Trước ");
		btnprev.setFont(new Font("Times New Roman", Font.PLAIN, 17));

		btnprev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (index > 1) {
					--index;
					lbldem.setText(index + "");
					filltable(listPT((index - 1) * 3 + 1));
				}
			}
		});
		btnprev.setBounds(685, 537, 85, 21);
		getContentPane().add(btnprev);
		JButton btnnext = new JButton("Sau");
		btnnext.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		btnnext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (index < rows) {
					++index;
					lbldem.setText(index + "");
					filltable(listPT((index - 1) * 3 + 1));
				}

			}
		});
		btnnext.setBounds(844, 537, 85, 21);
		getContentPane().add(btnnext);
		lblTo_1.setText(rows + "");

		JButton btnNewButton = new JButton("Reset");
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		btnNewButton.setIcon(new ImageIcon(QLNhanVien.class.getResource("/com/shoplaptop/icon/Refresh.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearForm();
			}
		});
		btnNewButton.setBounds(20, 530, 101, 27);
		getContentPane().add(btnNewButton);

		settable(service.selectAll());
		filltable(listPT(1));
		lbldem.setText("1");

		btnchitiet = new JButton(" Chi Tiết");
		btnchitiet.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		btnchitiet.setEnabled(false);
		btnchitiet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LS_NhanVien(QLNhanVien.this).setVisible(true);
				setFormChiTiet(
						new NhanVienService().selectById(String.valueOf(tblquanlynhanvien.getValueAt(index, 0))));
			}
		});
		btnchitiet.setIcon(new ImageIcon(QLNhanVien.class.getResource("/com/shoplaptop/icon/Users.png")));
		btnchitiet.setBounds(553, 534, 122, 27);
		getContentPane().add(btnchitiet);

		btnNewButton_1 = new JButton("Xuất File");
		btnNewButton_1.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		btnNewButton_1.setIcon(new ImageIcon(QLNhanVien.class.getResource("/com/shoplaptop/icon/Print preview.png")));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<NhanVien> listnhanvien = service.selectAll();
				XuatFile(listnhanvien);
				MsgBox.alert(getContentPane(), "Xuất file thành công");
			}
		});
		btnNewButton_1.setBounds(816, 19, 135, 33);
		getContentPane().add(btnNewButton_1);

		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedItem() == "Quản lý") {
					List<NhanVien> listNV = service.selectBySQL(FindQuanLy);
					settable(listNV);
					filltable(listPT(1));
				} else {
					if (comboBox.getSelectedItem() == "Nhân Viên") {
						List<NhanVien> listNV = service.selectBySQL(FindNhanVien);
						settable(listNV);
						filltable(listPT(1));
					}
				}
			}
		});
	}

	public void filltable(List<NhanVien> list) {
		model.setRowCount(0);
		for (NhanVien nhanVien : list) {
			Object[] rows = new Object[] { nhanVien.getMaNV(), nhanVien.getHoTen(), nhanVien.getDiaChi(),
					nhanVien.getEmail(), XDate.toString(nhanVien.getNgaySinh(), "yyyy-MM-dd"),
					nhanVien.isGioiTinh() ? "Nam" : "Nữ", nhanVien.getSoDienThoai(), nhanVien.getHinh(),
					nhanVien.isVaiTro() ? "Quản Lý" : "Nhân Viên" };
			model.addRow(rows);
		}

	}

	public void setForm(NhanVien nhanVien) {
		txtmanhanvien.setText(nhanVien.getMaNV());
		txttennhanvien.setText(nhanVien.getHoTen());
		txtdiachi.setText(nhanVien.getDiaChi());
		txtemail.setText(nhanVien.getEmail());
		txtngaysinh.setText(XDate.toString(nhanVien.getNgaySinh(), "yyyy-MM-dd"));
		rdonam.setSelected(nhanVien.isGioiTinh());
		rdonu.setSelected(!nhanVien.isGioiTinh());
		txtsodienthoai.setText(nhanVien.getSoDienThoai());
		if (nhanVien.getHinh() != null) {
			lblimage.setToolTipText(nhanVien.getHinh());
			lblimage.setIcon(XImage.read(nhanVien.getHinh()));
		}
		rdoquanly.setSelected(nhanVien.isVaiTro());
		rdonhanvien.setSelected(!nhanVien.isVaiTro());
	}

	public void setForm_1(TaiKhoan taiKhoan) {
		txttendangnhap.setText(taiKhoan.getTenDangNhap());
		txtmatkhau.setText(taiKhoan.getMatKhau());
	}

	public NhanVien getForm() {
		NhanVien nhanVien = new NhanVien();
		nhanVien.setMaNV(txtmanhanvien.getText());
		nhanVien.setHoTen(txttennhanvien.getText());
		nhanVien.setDiaChi(txtdiachi.getText());
		nhanVien.setEmail(txtemail.getText());
		nhanVien.setNgaySinh(XDate.toDate(txtngaysinh.getText(), "yyyy-MM-dd"));
		nhanVien.setGioiTinh(rdonam.isSelected());
		nhanVien.setSoDienThoai(txtsodienthoai.getText());
		nhanVien.setHinh(lblimage.getToolTipText());
		return nhanVien;
	}

	public TaiKhoan getFormTaiKhoan() {
		TaiKhoan taiKhoan = new TaiKhoan();
		taiKhoan.setMaNV(txtmanhanvien.getText());
		taiKhoan.setTenDangNhap(txttendangnhap.getText());
		taiKhoan.setMatKhau(String.valueOf(txtmatkhau.getPassword()));
		taiKhoan.setVaiTro(rdoquanly.isSelected());
		return taiKhoan;
	}

	public void chooseImage() {
		try {
			JFileChooser fileChooser = new JFileChooser();
			if (fileChooser.showOpenDialog(getContentPane()) == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				XImage.save(file);
				ImageIcon icon = XImage.read(file.getName());
				lblimage.setIcon(icon);
				lblimage.setToolTipText(file.getName());
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void clearForm() {
		lblimage.setIcon(null);
		txtdiachi.setText(null);
		txtemail.setText(null);
		txtmatkhau.setText(null);
		txtmanhanvien.setText(null);
		txttendangnhap.setText(null);
		txttennhanvien.setText(null);
		txtngaysinh.setText(null);
		txtsodienthoai.setText(null);
		buttonGroup.clearSelection();
		buttonGroup_1.clearSelection();
		comboBox.setSelectedIndex(-1);
		txttimkiem.setText(null);
		settable(service.selectAll());
		filltable(listPT(1));
		lbldem.setText("1");
	}

	public void insert() {
		NhanVien nhanVien = getForm();
		TaiKhoan taiKhoan = getFormTaiKhoan();
		try {
			service.insert(nhanVien);
			dao.insert(taiKhoan);
			filltable(service.sellectAllNhanVien((index - 1) * 3 + 1));
			clearForm();
			MsgBox.alert(contentPanel, "Insert thành công");
		} catch (Exception e) {
			System.out.println(e);
			MsgBox.alert(contentPanel, "Insert thất bại");
		}
	}

	public void FindMaNV() {
		String manv_1 = txttimkiem.getText();
		List<NhanVien> listNV = service.selectBySQL(selectSQl_PhânTrang, "%" + manv_1 + "%", "%" + manv_1 + "%",
				"%" + manv_1 + "%");
		settable(listNV);
		filltable(listPT(1));
	}

	public void Update(NhanVien nhanVien) {
		try {
			MsgBox.alert(contentPanel, service.update(nhanVien));
			filltable(service.selectAll());
		} catch (Exception e) {

		}
	}

	public void delete(NhanVien nhanVien) {
		try {
			MsgBox.alert(contentPanel, service.delete(nhanVien.getMaNV()));
			filltable(service.selectAll());
		} catch (Exception e) {

		}
	}

	public boolean vali() {
		if (lblimage.getIcon() == null) {
			JOptionPane.showMessageDialog(contentPanel, "Hình nhân viên k được để trống");
			return false;
		}
		if (txtmanhanvien.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(contentPanel, "Mã nhân viên k được để trống");
			return false;
		}
		if (txttennhanvien.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(contentPanel, "Tên nhân viên k được để trống");
			return false;
		}
		if (txtdiachi.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(contentPanel, "Địa chỉ nhân viên k được để trống");
			return false;
		}
		if (txtemail.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(contentPanel, "Email nhân viên k được để trống");
			return false;
		}
		if (txtngaysinh.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(contentPanel, "Ngày sinh nhân viên k được để trống");
			return false;
		}
		if (!rdonam.isSelected() && !rdonu.isSelected()) {
			JOptionPane.showMessageDialog(contentPanel, "Bạn chưa chọn giới tính");
			return false;
		}
		if (txttendangnhap.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(contentPanel, "Tên đăng nhập k được để trống");
			return false;
		}

		if (String.valueOf(txtmatkhau.getPassword()).trim().isEmpty()) {
			JOptionPane.showMessageDialog(contentPanel, "mật khẩu k được để trống");
			return false;
		}

		if (String.valueOf(txtmatkhau.getPassword()).trim().isEmpty()) {

			JOptionPane.showMessageDialog(contentPanel, "Mật khẩu nhân viên k được để trống");
			return false;
		}
		if (txtsodienthoai.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(contentPanel, "SĐT nhân viên k được để trống");
			return false;
		}
		if (!rdonhanvien.isSelected() && !rdoquanly.isSelected()) {
			JOptionPane.showMessageDialog(contentPanel, "Bạn chưa chọn vai trò nhân viên");
			return false;
		}

		return true;
	}

	public void setFormChiTiet(NhanVien nhanVien) {
		LS_NhanVien.txtdiachi.setText(nhanVien.getDiaChi());
		LS_NhanVien.txtmanhanvien.setText(nhanVien.getMaNV());
		LS_NhanVien.txttennhanvien.setText(nhanVien.getHoTen());
		LS_NhanVien.txtemail.setText(nhanVien.getEmail());
		LS_NhanVien.txtngaysinh.setText(XDate.toString(nhanVien.getNgaySinh(), "YYYY-MM-dd"));
		LS_NhanVien.txtsodeinthoai.setText(nhanVien.getSoDienThoai());
		LS_NhanVien.rdonam.setSelected(nhanVien.isGioiTinh());
		LS_NhanVien.rdonu.setSelected(!nhanVien.isGioiTinh());
		LS_NhanVien.rdoquanly.setSelected(nhanVien.isVaiTro());
		LS_NhanVien.rdonhanvien.setSelected(!nhanVien.isVaiTro());
		LS_NhanVien.lblimage.setIcon(XImage.read(nhanVien.getHinh()));
		LS_NhanVien.lblimage.setToolTipText(nhanVien.getHinh());
	}

	public void settable(List<NhanVien> list) {
		size = (double) list.size() / 3;
		rows = (int) Math.ceil(size);
		lblTo_1.setText(rows + "");
	}

	public List<NhanVien> listPT(int index) {
		String select = "";
		if (txttimkiem.getText().trim().isEmpty() && comboBox.getSelectedIndex() == -1) {
			return service.selectBySQL(query, index, index + 2);
		}
		if (!txttimkiem.getText().trim().isEmpty() && comboBox.getSelectedIndex() == -1) {
			select = SELECTBYID;
			return service.selectBySQL(select, "%" + txttimkiem.getText() + "%", "%" + txttimkiem.getText() + "%",
					"%" + txttimkiem.getText() + "%", index, index + 2);
		}
		if (txttimkiem.getText().trim().isEmpty() && comboBox.getSelectedIndex() == 0) {
			select = FilterQuanLy;
			return service.selectBySQL(select, index, index + 2);
		}
		if (txttimkiem.getText().trim().isEmpty() && comboBox.getSelectedIndex() == 1) {
			select = FilterNhanVien;
			return service.selectBySQL(select, index, index + 2);
		}
		if (!txttimkiem.getText().trim().isEmpty() && comboBox.getSelectedIndex() == 0) {
			select = FindByNhanVien;
			return service.selectBySQL(select, "%" + txttimkiem.getText() + "%", "%" + txttimkiem.getText() + "%",
					"%" + txttimkiem.getText() + "%", index, index + 2);
		}
		if (!txttimkiem.getText().trim().isEmpty() && comboBox.getSelectedIndex() == 1) {
			select = FindByQuanLy;
			return service.selectBySQL(select, "%" + txttimkiem.getText() + "%", "%" + txttimkiem.getText() + "%",
					"%" + txttimkiem.getText() + "%", index, index + 2);
		}
		return null;
	}

	public void XuatFile(List<NhanVien> list_nhanvien) {
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel files", "xls", "xlsx");
		fileChooser.setFileFilter(filter);
		if (fileChooser.showSaveDialog(getContentPane()) == JFileChooser.APPROVE_OPTION) {
			File fileToSave = fileChooser.getSelectedFile();

			String filePath = fileToSave.getAbsolutePath();
			if (!filePath.toLowerCase().endsWith(".xlsx") && !filePath.toLowerCase().endsWith(".xls")) {
				fileToSave = new File(filePath + ".xlsx");
			}

			Workbook workbook = new XSSFWorkbook();
			Sheet sheet = workbook.createSheet("Danh sách");

			Row headerRow = sheet.createRow(0);
			headerRow.createCell(0).setCellValue("Mã nhân viên");
			headerRow.createCell(1).setCellValue("Tên nhân viên");
			headerRow.createCell(2).setCellValue("Địa chỉ");
			headerRow.createCell(3).setCellValue("Email");
			headerRow.createCell(4).setCellValue("Ngày Sinh");
			headerRow.createCell(5).setCellValue("Giới tính");
			headerRow.createCell(6).setCellValue("Số điện thoại");
			headerRow.createCell(7).setCellValue("Hình");
			headerRow.createCell(8).setCellValue("Vai Trò");

			for (int i = 0; i < list_nhanvien.size(); i++) {
				NhanVien nhanVien = list_nhanvien.get(i);
				Row row = sheet.createRow(i + 1);
				row.createCell(0).setCellValue(nhanVien.getMaNV());
				row.createCell(1).setCellValue(nhanVien.getHoTen());
				row.createCell(2).setCellValue(nhanVien.getDiaChi());
				row.createCell(3).setCellValue(nhanVien.getEmail());
				row.createCell(4).setCellValue(nhanVien.getNgaySinh());
				row.createCell(5).setCellValue(nhanVien.isGioiTinh() ? "Nam" : "Nữ");
				row.createCell(6).setCellValue(nhanVien.getSoDienThoai());
				row.createCell(7).setCellValue(nhanVien.getHinh());
				row.createCell(8).setCellValue(nhanVien.isVaiTro() ? "Quản Lý" : "Nhân Viên");
			}

			try (FileOutputStream outputStream = new FileOutputStream(fileToSave)) {
				workbook.write(outputStream);
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				workbook.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
