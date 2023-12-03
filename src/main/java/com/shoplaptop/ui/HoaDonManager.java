package com.shoplaptop.ui;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.IElement;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.shoplaptop.dao.DotGiamGiaDAO;
import com.shoplaptop.dao.HinhThucThanhToanDAO;
import com.shoplaptop.dao.HinhThucVanChuyenDAO;
import com.shoplaptop.dao.KhachHangDAO;
import com.shoplaptop.dao.PhieuGiamGiaDAO;
import com.shoplaptop.dao.SerialNumberDAO;
import com.shoplaptop.entity.CTHoaDon;
import com.shoplaptop.entity.DotGiamGia;
import com.shoplaptop.entity.HinhThucThanhToan;
import com.shoplaptop.entity.HinhThucVanChuyen;
import com.shoplaptop.entity.HoaDon;
import com.shoplaptop.entity.KhachHang;
import com.shoplaptop.entity.PhieuGiamGia;
import com.shoplaptop.entity.SerialNumber;
import com.shoplaptop.utils.MsgBox;
import com.shoplaptop.utils.XImage;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

@SuppressWarnings("serial")
public class HoaDonManager extends JDialog {
	private JTextField txtTongTien;
	private JTextField txtSerialNumber;
	public static JTextField txtSoDienThoai;
	private JTable tblHoaDon_CXN;
	private JTable tblCTHoaDon;
	private JComboBox<HinhThucVanChuyen> cboHinhThucVanChuyen;
	private JComboBox<HinhThucThanhToan> cboHinhThucThanhToan;
	private JLabel lblGiaVC;
	private DefaultTableModel modelCTHoaDon;
	private List<HoaDon> HOA_DON_REPO = new ArrayList<HoaDon>();
	private List<HoaDon> Hoa_Don_Wait = new ArrayList<HoaDon>();
	private List<HoaDon> Hoa_Don_Success = new ArrayList<HoaDon>();
	private List<CTHoaDon> HDCT_REPO = new ArrayList<CTHoaDon>();
	private List<SerialNumber> SAN_PHAM_REPO = new SerialNumberDAO().selectAll();
	private JTextField txtMaPhieuGG;
	private JTextField txtDotGiamGia;
	private JTextField txtTienGiam;
	private JTextField txtThanhTien;
	private JButton btnAddSP;
	private BigDecimal tongTiens = BigDecimal.valueOf(Double.valueOf(0));
	private BigDecimal tienGiamPhieus = BigDecimal.valueOf(Double.valueOf(0));
	private BigDecimal tienGiamDots = BigDecimal.valueOf(Double.valueOf(0));
	private BigDecimal thanhTiens = BigDecimal.valueOf(Double.valueOf(0));
	private static JLabel lblMaNV;
	private JLabel lblMaKH;
	private JLabel lblHoten;
	private JLabel lblDiaChi;
	private JButton btnXoaSP;
	private JButton btnHuyHD;
	private JButton btnXacNhan;
	private JButton btnXuatHoaDon;
	private JTable tblHoaDon_DXN;
	private JButton btnNhapKH;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					HoaDonManager dialog = new HoaDonManager();
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
	public HoaDonManager() {
		setBounds(100, 100, 975, 750);
		setLocationRelativeTo(null);
		setTitle("Tạo hóa đơn");
		getContentPane().setLayout(null);
		setIconImage(XImage.getAppIcon());

		JButton btnTaoHD = new JButton("Tạo hóa đơn");
		btnTaoHD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HoaDon hoaDon = new HoaDon();
				hoaDon.setMaHD(generateAutoCode());
				hoaDon.setTrangThai("Chờ thanh toán");
				if (lblMaKH.getText() != "---") {
					hoaDon.setMaKH(lblMaKH.getText());
					
				} else {
					KhachHang khachHang = new KhachHang();
					khachHang.setMaKH(generateAutoCodeKH());
					khachHang.setHoTen(khachHang.getMaKH());
					khachHang.setSoDienThoai(generateAutoCodeSDT());
					khachHang.setDiaChi("Đ/c không được cung cấp");
					new KhachHangDAO().insertKH(khachHang);
					hoaDon.setMaKH(khachHang.getMaKH());
					
				}
				
				HOA_DON_REPO.add(hoaDon);
				
				Hoa_Don_Wait = HOA_DON_REPO.stream().filter(hd -> hd.getTrangThai().equals("Chờ thanh toán"))
						.collect(Collectors.toList());
				
				loadHoaDon_Wait(Hoa_Don_Wait);

			}
		});
		btnTaoHD.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnTaoHD.setBounds(158, 21, 135, 37);
		getContentPane().add(btnTaoHD);

		btnXuatHoaDon = new JButton("Xuất hóa đơn");
		btnXuatHoaDon.setEnabled(false);
		btnXuatHoaDon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = tblHoaDon_DXN.getSelectedRow();
				String maHD = (String) tblHoaDon_DXN.getValueAt(index, 0);
				HoaDon hoaDon = HOA_DON_REPO.stream().filter(hd -> hd.getTrangThai().equals("Đã thanh toán") && hd.getMaHD().equals(maHD))
						.collect(Collectors.toList()).get(0);
				List<CTHoaDon> list = HDCT_REPO.stream().filter(ct -> ct.getMaHD().equals(maHD))
						.collect(Collectors.toList());
				ExportHD_PDF(list, hoaDon);
				MsgBox.alert(getContentPane(), "Xuất hóa đơn thành công");
				
				hoaDon.setTrangThai("Hoàn thành");
				
				Hoa_Don_Success = HOA_DON_REPO.stream().filter(hd -> hd.getTrangThai().equals("Đã thanh toán"))
						.collect(Collectors.toList());
				
				loadHoaDon_Success(Hoa_Don_Success);
			}
		});
		btnXuatHoaDon.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnXuatHoaDon.setBounds(425, 21, 149, 37);
		getContentPane().add(btnXuatHoaDon);

		JPanel panel = new JPanel();
		panel.setBorder(
				new TitledBorder(null, "Kh\u00E1ch h\u00E0ng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(587, 70, 364, 214);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Mã KH         :");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel.setBounds(10, 96, 108, 30);
		panel.add(lblNewLabel);

		JLabel lblHTn = new JLabel("Họ tên          :");
		lblHTn.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblHTn.setBounds(10, 136, 108, 30);
		panel.add(lblHTn);

		JLabel lblNewLabel_1_1_1 = new JLabel("Địa chỉ          :");
		lblNewLabel_1_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_1_1_1.setBounds(10, 176, 108, 30);
		panel.add(lblNewLabel_1_1_1);

		lblMaKH = new JLabel("---");
		lblMaKH.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblMaKH.setBounds(128, 96, 226, 30);
		panel.add(lblMaKH);

		lblHoten = new JLabel("---");
		lblHoten.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblHoten.setBounds(128, 136, 226, 30);
		panel.add(lblHoten);

		lblDiaChi = new JLabel("---");
		lblDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblDiaChi.setBounds(128, 176, 226, 30);
		panel.add(lblDiaChi);

		JLabel lblNewLabel_1_1 = new JLabel("Số điện thoại :");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(10, 56, 108, 30);
		panel.add(lblNewLabel_1_1);

		txtSoDienThoai = new JTextField();
		txtSoDienThoai.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtSoDienThoai.setBounds(128, 56, 226, 30);
		panel.add(txtSoDienThoai);
		txtSoDienThoai.setColumns(10);

		btnNhapKH = new JButton("Nhập KH");
		btnNhapKH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String soDienThoai = txtSoDienThoai.getText();
				if (new KhachHangDAO().selectBySoDienThoai(soDienThoai) != null) {
					lblMaKH.setText(new KhachHangDAO().selectBySoDienThoai(soDienThoai).getMaKH());
					lblHoten.setText(new KhachHangDAO().selectBySoDienThoai(soDienThoai).getHoTen());
					lblDiaChi.setText(new KhachHangDAO().selectBySoDienThoai(soDienThoai).getDiaChi());
				} else {
					if (MsgBox.confirm(getContentPane(),
							"Chưa có thông tin khách hàng. Bạn muốn thêm thông tin khách hàng ?")) {
						new ThemKhachHangJDialog().setVisible(true);
						ThemKhachHangJDialog.txtMaKH.setText(generateAutoCodeKH());
						if (soDienThoai.trim().isEmpty()) {
							ThemKhachHangJDialog.txtSDT.setText(generateAutoCodeSDT());
						}else {
							ThemKhachHangJDialog.txtSDT.setText(txtSoDienThoai.getText());
						}
					}
				}
			}
		});
		btnNhapKH.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnNhapKH.setBounds(246, 10, 108, 36);
		panel.add(btnNhapKH);

		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetForm();
			}
		});
		btnReset.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnReset.setBounds(128, 10, 108, 36);
		panel.add(btnReset);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"H\u00ECnh th\u1EE9c v\u1EADn chuy\u1EC3n", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		panel_1.setBounds(587, 294, 364, 150);
		getContentPane().add(panel_1);

		JLabel lblHnhThcVn = new JLabel("Hình thức vận chuyển :");
		lblHnhThcVn.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblHnhThcVn.setBounds(10, 33, 227, 30);
		panel_1.add(lblHnhThcVn);

		JLabel lblGi = new JLabel("Giá :");
		lblGi.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblGi.setBounds(10, 113, 66, 23);
		panel_1.add(lblGi);

		lblGiaVC = new JLabel("---");
		lblGiaVC.setBounds(86, 113, 151, 23);
		panel_1.add(lblGiaVC);
		lblGiaVC.setFont(new Font("Times New Roman", Font.PLAIN, 18));

		cboHinhThucVanChuyen = new JComboBox<HinhThucVanChuyen>();
		cboHinhThucVanChuyen.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		fillCboHinhThucVanChuyen();
		cboHinhThucVanChuyen.setSelectedIndex(-1);
		cboHinhThucVanChuyen.setBounds(10, 73, 344, 30);
		panel_1.add(cboHinhThucVanChuyen);

		JLabel lblVn = new JLabel("VNĐ");
		lblVn.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblVn.setBounds(246, 113, 108, 23);
		panel_1.add(lblVn);

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"H\u00ECnh th\u1EE9c thanh to\u00E1n", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		panel_1_1.setBounds(587, 454, 364, 127);
		getContentPane().add(panel_1_1);

		JLabel lblHnhThcThanh = new JLabel("Hình thức thanh toán :");
		lblHnhThcThanh.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblHnhThcThanh.setBounds(10, 33, 227, 30);
		panel_1_1.add(lblHnhThcThanh);

		cboHinhThucThanhToan = new JComboBox<HinhThucThanhToan>();
		cboHinhThucThanhToan.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		fillCboHinhThucThanhToan();
		cboHinhThucThanhToan.setSelectedIndex(-1);
		cboHinhThucThanhToan.setBounds(10, 73, 344, 30);
		panel_1_1.add(cboHinhThucThanhToan);

		JLabel lblNewLabel_2 = new JLabel("Tổng tiền");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(587, 591, 107, 29);
		getContentPane().add(lblNewLabel_2);

		txtTongTien = new JTextField();
		txtTongTien.setText("0");
		txtTongTien.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtTongTien.setEditable(false);
		txtTongTien.setBounds(705, 591, 246, 29);
		getContentPane().add(txtTongTien);
		txtTongTien.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Mã NV         :");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(596, 28, 108, 30);
		getContentPane().add(lblNewLabel_3);

		lblMaNV = new JLabel("---");
		lblMaNV.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblMaNV.setBounds(714, 28, 226, 30);
		getContentPane().add(lblMaNV);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 70, 564, 163);
		getContentPane().add(scrollPane);

		tblHoaDon_CXN = new JTable();
		tblHoaDon_CXN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = tblHoaDon_CXN.getSelectedRow();
				String maHoaDon = tblHoaDon_CXN.getValueAt(selectedRow, 0).toString();
				loadCTHoaDon(maHoaDon);
				btnAddSP.setEnabled(true);
				txtSerialNumber.setText(null);
				btnHuyHD.setEnabled(true);
				btnXacNhan.setEnabled(true);
				btnXoaSP.setEnabled(false);
				BigDecimal tongTien = BigDecimal.valueOf(0);

				for (CTHoaDon ctHoaDon : HDCT_REPO.stream().filter(ct -> ct.getMaHD().equals(maHoaDon)).toList()) {
					tongTien = tongTien.add(ctHoaDon.getGia());
				}

				tongTiens = tongTien;
				txtTongTien.setText(decimalFormat(tongTiens));
				
				

				for (HoaDon hoaDon : HOA_DON_REPO.stream()
						.filter(hd -> hd.getTrangThai().equals("Chờ thanh toán") && hd.getMaHD().equals(maHoaDon))
						.collect(Collectors.toList())) {
					txtSoDienThoai.setText(new KhachHangDAO().selectByMaKH(hoaDon.getMaKH()).getSoDienThoai());
					lblMaKH.setText(new KhachHangDAO().selectByMaKH(hoaDon.getMaKH()).getMaKH());
					lblHoten.setText(new KhachHangDAO().selectByMaKH(hoaDon.getMaKH()).getHoTen());
					lblDiaChi.setText(new KhachHangDAO().selectByMaKH(hoaDon.getMaKH()).getDiaChi());

				}
			}
		});
		tblHoaDon_CXN.setRowMargin(3);
		tblHoaDon_CXN.setRowHeight(25);
		tblHoaDon_CXN.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		scrollPane.setViewportView(tblHoaDon_CXN);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 322, 564, 150);
		getContentPane().add(scrollPane_1);

		modelCTHoaDon = new DefaultTableModel();
		String[] colsCTHoaDon = { "Tên Laptop", "SerialNumber", "Đơn giá" };
		modelCTHoaDon.setColumnIdentifiers(colsCTHoaDon);

		tblCTHoaDon = new JTable(modelCTHoaDon);
		tblCTHoaDon.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		tblCTHoaDon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnXoaSP.setEnabled(true);
			}
		});
		tblCTHoaDon.setRowMargin(3);
		tblCTHoaDon.setRowHeight(25);
		scrollPane_1.setViewportView(tblCTHoaDon);

		JLabel lblNewLabel_3_1 = new JLabel("SerialNumber");
		lblNewLabel_3_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_3_1.setBounds(10, 243, 108, 30);
		getContentPane().add(lblNewLabel_3_1);

		txtSerialNumber = new JTextField();
		txtSerialNumber.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtSerialNumber.setColumns(10);
		txtSerialNumber.setBounds(10, 283, 254, 29);
		getContentPane().add(txtSerialNumber);

		btnAddSP = new JButton("Thêm vào hóa đơn");
		btnAddSP.setEnabled(false);
		btnAddSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String serialNumber = txtSerialNumber.getText();
					SerialNumber sp = SAN_PHAM_REPO.stream().filter(s -> s.getSerialNumber().equals(serialNumber))
							.findFirst().orElse(null);

					int selectedHoaDon = tblHoaDon_CXN.getSelectedRow();
					String maHoaDon = tblHoaDon_CXN.getValueAt(selectedHoaDon, 0).toString();

					CTHoaDon hdct = new CTHoaDon();
					hdct.setMaHD(maHoaDon);
					hdct.setTenLaptop(sp.getTenLaptop());
					hdct.setSerialNumber(serialNumber);
					hdct.setGia(sp.getGia());

					HDCT_REPO.add(hdct);
					loadCTHoaDon(maHoaDon);

					BigDecimal tongTien = BigDecimal.valueOf(0);

					for (CTHoaDon ctHoaDon : HDCT_REPO.stream().filter(ct -> ct.getMaHD().equals(maHoaDon)).toList()) {
						tongTien = tongTien.add(ctHoaDon.getGia());

					}
					
					tongTiens = tongTien;
					txtTongTien.setText(decimalFormat(tongTiens));
					txtSerialNumber.setText(null);
					
					DotGiamGia dotGiamGia = new DotGiamGiaDAO().selectDGG(tongTiens);
					if (dotGiamGia != null) {
						txtDotGiamGia.setText(dotGiamGia.getTen() + " - " + dotGiamGia.getGiaGiam());
						tienGiamDots = dotGiamGia.getGiaGiam();
					}
					
					txtTienGiam.setText(decimalFormat(tienGiamDots.add(tienGiamPhieus)));
					
					if (lblGiaVC.getText() != "---") {
						thanhTiens = tongTiens.subtract(tienGiamDots.add(tienGiamPhieus)).add(BigDecimal.valueOf(Double.valueOf(lblGiaVC.getText())));
					}else {
						thanhTiens = tongTiens.subtract(tienGiamDots.add(tienGiamPhieus));
					}
					txtThanhTien.setText(decimalFormat(thanhTiens));
					
				} catch (Exception e2) {
					MsgBox.alert(getContentPane(), "Nhập lại SerialNumber");
				}
			}
		});
		btnAddSP.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnAddSP.setBounds(389, 260, 183, 36);
		getContentPane().add(btnAddSP);

		JLabel lblNewLabel_2_1 = new JLabel("Phiếu giảm giá");
		lblNewLabel_2_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_2_1.setBounds(10, 632, 124, 29);
		getContentPane().add(lblNewLabel_2_1);

		txtMaPhieuGG = new JTextField();
		txtMaPhieuGG.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtMaPhieuGG.setColumns(10);
		txtMaPhieuGG.setBounds(10, 671, 246, 29);
		getContentPane().add(txtMaPhieuGG);

		JButton btnNewButton_2_1 = new JButton("Áp dụng");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String maPhieuGG = txtMaPhieuGG.getText();
				try {
					PhieuGiamGia phieuGiamGia = new PhieuGiamGiaDAO().selectById(maPhieuGG);
					if (BigDecimal.valueOf(Double.valueOf(txtTongTien.getText()))
							.compareTo(phieuGiamGia.getDieuKienGiam()) == 1) {
						tienGiamPhieus = phieuGiamGia.getGiaGiam();
						txtTienGiam.setText(decimalFormat(tienGiamPhieus.add(tienGiamDots)));
						if (lblGiaVC.getText() != "---") {
							thanhTiens = tongTiens.subtract(tienGiamDots.add(tienGiamPhieus)).add(BigDecimal.valueOf(Double.valueOf(lblGiaVC.getText())));
						}else {
							thanhTiens = tongTiens.subtract(tienGiamDots.add(tienGiamPhieus));
						}
						txtThanhTien.setText(decimalFormat(thanhTiens));
					} else {
						MsgBox.alert(getContentPane(), "Hóa đơn chưa đủ điều kiện áp phiếu");
					}
				} catch (Exception e2) {
					MsgBox.alert(getContentPane(), "Mã phiếu giảm không hợp lệ!");
				}
			}
		});
		btnNewButton_2_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnNewButton_2_1.setBounds(144, 632, 112, 29);
		getContentPane().add(btnNewButton_2_1);

		JLabel lblNewLabel_2_1_1 = new JLabel("Đợt giảm giá");
		lblNewLabel_2_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_2_1_1.setBounds(271, 632, 124, 29);
		getContentPane().add(lblNewLabel_2_1_1);

		txtDotGiamGia = new JTextField();
		txtDotGiamGia.setEditable(false);
		txtDotGiamGia.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtDotGiamGia.setColumns(10);
		txtDotGiamGia.setBounds(271, 671, 246, 29);
		getContentPane().add(txtDotGiamGia);

		JLabel lblNewLabel_2_2 = new JLabel("Tiền giảm");
		lblNewLabel_2_2.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_2_2.setBounds(587, 630, 107, 29);
		getContentPane().add(lblNewLabel_2_2);

		txtTienGiam = new JTextField();
		txtTienGiam.setText("0");
		txtTienGiam.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtTienGiam.setEditable(false);
		txtTienGiam.setColumns(10);
		txtTienGiam.setBounds(705, 630, 246, 29);
		getContentPane().add(txtTienGiam);

		JLabel lblNewLabel_2_3 = new JLabel("Thành tiền");
		lblNewLabel_2_3.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_2_3.setBounds(587, 671, 107, 29);
		getContentPane().add(lblNewLabel_2_3);

		txtThanhTien = new JTextField();
		txtThanhTien.setText("0");
		txtThanhTien.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtThanhTien.setEditable(false);
		txtThanhTien.setColumns(10);
		txtThanhTien.setBounds(705, 671, 246, 29);
		getContentPane().add(txtThanhTien);

		btnHuyHD = new JButton("Hủy hóa đơn");
		btnHuyHD.setEnabled(false);
		btnHuyHD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = tblHoaDon_CXN.getSelectedRow();
				String maHD = (String) tblHoaDon_CXN.getValueAt(index, 0);
				HoaDon hoaDon = HOA_DON_REPO.stream().filter(hd -> hd.getMaHD().equals(maHD))
						.collect(Collectors.toList()).get(0);
				hoaDon.setTrangThai("Đã Hủy");
				Hoa_Don_Wait = HOA_DON_REPO.stream().filter(hd -> hd.getTrangThai().equals("Chờ thanh toán"))
						.collect(Collectors.toList());
				loadHoaDon_Wait(Hoa_Don_Wait);
			}
		});
		btnHuyHD.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnHuyHD.setBounds(10, 21, 140, 37);
		getContentPane().add(btnHuyHD);

		btnXacNhan = new JButton("Xác nhận");
		btnXacNhan.setEnabled(false);
		btnXacNhan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = tblHoaDon_CXN.getSelectedRow();
				String maHD = (String) tblHoaDon_CXN.getValueAt(index, 0);
				HoaDon hoaDon = HOA_DON_REPO.stream().filter(hd -> hd.getTrangThai().equals("Chờ thanh toán") && hd.getMaHD().equals(maHD))
						.collect(Collectors.toList()).get(0);
				if (HDCT_REPO.stream().filter(ct -> ct.getMaHD().equals(hoaDon.getMaHD())).collect(Collectors.toList())
						.size() != 0) {
					try {
						BigDecimal tienNhan = BigDecimal
								.valueOf(Double.valueOf(MsgBox.prompt(getContentPane(), "Tiền nhận :")));
						BigDecimal tienThua = tienNhan.subtract(thanhTiens);
						MsgBox.alert(getContentPane(), "Tiền thừa: " + decimalFormat(tienThua));
						hoaDon.setTrangThai("Đã thanh toán");
						
						Hoa_Don_Wait = HOA_DON_REPO.stream().filter(hd -> hd.getTrangThai().equals("Chờ thanh toán"))
								.collect(Collectors.toList());
						
						Hoa_Don_Success = HOA_DON_REPO.stream().filter(hd -> hd.getTrangThai().equals("Đã thanh toán"))
								.collect(Collectors.toList());
						
						loadHoaDon_Wait(Hoa_Don_Wait);
						
						loadHoaDon_Success(Hoa_Don_Success);
						
						HinhThucThanhToan hinhThucThanhToan = (HinhThucThanhToan) cboHinhThucThanhToan.getSelectedItem();
						hoaDon.setId_HinhThucThanhToan(hinhThucThanhToan.getId());
						
					} catch (NumberFormatException e2) {
						MsgBox.alert(getContentPane(), "Vui lòng nhập lại tiền!");
					}catch (NullPointerException e2) {
						MsgBox.alert(getContentPane(), "Vui lòng chọn hình thức vận chuyển");
					}
				} else {
					MsgBox.alert(getContentPane(), "Hóa đơn chưa có sản phẩm!");
				}
			}
		});
		btnXacNhan.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnXacNhan.setBounds(297, 21, 124, 37);
		getContentPane().add(btnXacNhan);

		btnXoaSP = new JButton("Xóa SP");
		btnXoaSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = tblCTHoaDon.getSelectedRow();
				int index2 = tblHoaDon_CXN.getSelectedRow();
				String maHoaDon = (String) tblHoaDon_CXN.getValueAt(index2, 0);
				List<CTHoaDon> list = HDCT_REPO.stream().filter(ct -> ct.getMaHD().equals(maHoaDon))
						.collect(Collectors.toList());
				CTHoaDon itemToRemove = list.get(index);
				HDCT_REPO.remove(itemToRemove);
				loadCTHoaDon(maHoaDon);
				
				BigDecimal tongTien = BigDecimal.valueOf(0);

				for (CTHoaDon ctHoaDon : HDCT_REPO.stream().filter(ct -> ct.getMaHD().equals(maHoaDon)).toList()) {
					tongTien = tongTien.add(ctHoaDon.getGia());
				}
				
				tongTiens = tongTien;
				txtTongTien.setText(decimalFormat(tongTiens));
				txtSerialNumber.setText(null);
				
				DotGiamGia dotGiamGia = new DotGiamGiaDAO().selectDGG(tongTiens);
				if (dotGiamGia != null) {
					txtDotGiamGia.setText(dotGiamGia.getTen() + " - " + dotGiamGia.getGiaGiam());
					tienGiamDots = dotGiamGia.getGiaGiam();
				}
				
				txtTienGiam.setText(decimalFormat(tienGiamDots.add(tienGiamPhieus)));
				
				if (lblGiaVC.getText() != "---") {
					thanhTiens = tongTiens.subtract(tienGiamDots.add(tienGiamPhieus)).add(BigDecimal.valueOf(Double.valueOf(lblGiaVC.getText())));
				}else {
					thanhTiens = tongTiens.subtract(tienGiamDots.add(tienGiamPhieus));
				}
				txtThanhTien.setText(decimalFormat(thanhTiens));

			}
		});
		btnXoaSP.setEnabled(false);
		btnXoaSP.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnXoaSP.setBounds(274, 260, 108, 36);
		getContentPane().add(btnXoaSP);

		JScrollPane scrollPane_1_1 = new JScrollPane();
		scrollPane_1_1.setBounds(10, 482, 564, 138);
		getContentPane().add(scrollPane_1_1);

		tblHoaDon_DXN = new JTable();
		tblHoaDon_DXN.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		tblHoaDon_DXN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnXuatHoaDon.setEnabled(true);
				btnXoaSP.setEnabled(false);
				int selectedRow = tblHoaDon_DXN.getSelectedRow();
				String maHoaDon = tblHoaDon_DXN.getValueAt(selectedRow, 0).toString();
				loadCTHoaDon(maHoaDon);
				
				btnAddSP.setEnabled(false);
			}
		});
		scrollPane_1_1.setViewportView(tblHoaDon_DXN);

		cboHinhThucVanChuyen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					HinhThucVanChuyen hinhThucVanChuyen = (HinhThucVanChuyen) cboHinhThucVanChuyen.getSelectedItem();
					lblGiaVC.setText(decimalFormat(hinhThucVanChuyen.getGiaVC()));

					int selectedHoaDon = tblHoaDon_CXN.getSelectedRow();
					String maHoaDon = tblHoaDon_CXN.getValueAt(selectedHoaDon, 0).toString();
					BigDecimal tongTien = BigDecimal.valueOf(0);

					for (CTHoaDon ctHoaDon : HDCT_REPO.stream().filter(ct -> ct.getMaHD().equals(maHoaDon)).toList()) {
						tongTien = tongTien.add(ctHoaDon.getGia());
					}

					tongTiens = tongTien;
					txtTongTien.setText(decimalFormat(tongTiens));
					
					if (lblGiaVC.getText() != "---") {
						thanhTiens = tongTiens.subtract(tienGiamDots.add(tienGiamPhieus)).add(BigDecimal.valueOf(Double.valueOf(lblGiaVC.getText())));
					}else {
						thanhTiens = tongTiens.subtract(tienGiamDots.add(tienGiamPhieus));
					}
					txtThanhTien.setText(decimalFormat(thanhTiens));

				} catch (Exception e2) {
					MsgBox.alert(getContentPane(), "Chọn hóa đơn");
				}
			}
		});

	}

	public void fillCboHinhThucVanChuyen() {
		DefaultComboBoxModel<HinhThucVanChuyen> model = (DefaultComboBoxModel<HinhThucVanChuyen>) cboHinhThucVanChuyen
				.getModel();
		model.removeAllElements();
		List<HinhThucVanChuyen> list = new HinhThucVanChuyenDAO().selectAll();
		for (HinhThucVanChuyen hinhThucVanChuyen : list) {
			model.addElement(hinhThucVanChuyen);
		}
	}

	public void fillCboHinhThucThanhToan() {
		DefaultComboBoxModel<HinhThucThanhToan> model = (DefaultComboBoxModel<HinhThucThanhToan>) cboHinhThucThanhToan
				.getModel();
		model.removeAllElements();
		List<HinhThucThanhToan> list = new HinhThucThanhToanDAO().selectAll();
		for (HinhThucThanhToan hinhThucThanhToan : list) {
			model.addElement(hinhThucThanhToan);
		}
	}

	public static String decimalFormat(BigDecimal number) {
		DecimalFormat decimalFormat = new DecimalFormat("0.####################");
		String formattedNumber = decimalFormat.format(number);
		return formattedNumber;
	}

	public void loadHoaDon_Wait(List<HoaDon> hd_Wait) {
		DefaultTableModel model = (DefaultTableModel) tblHoaDon_CXN.getModel();
		model.setColumnCount(0);
		model.addColumn("Mã hóa đơn");
		model.addColumn("Họ tên KH");
		model.addColumn("Trạng thái");
		model.setRowCount(0);

		for (HoaDon hoaDon : hd_Wait) {
			Object[] row = new Object[] { hoaDon.getMaHD(),
					new KhachHangDAO().selectByMaKH(hoaDon.getMaKH()).getHoTen(), hoaDon.getTrangThai() };
			model.addRow(row);
		}
	}

	public void loadHoaDon_Success(List<HoaDon> hd_Success) {
		DefaultTableModel model = (DefaultTableModel) tblHoaDon_DXN.getModel();
		model.setColumnCount(0);
		model.addColumn("Mã hóa đơn");
		model.addColumn("Họ tên KH");
		model.addColumn("Trạng thái");
		model.setRowCount(0);

		for (HoaDon hoaDon : hd_Success) {
			Object[] row = new Object[] { hoaDon.getMaHD(),
					new KhachHangDAO().selectByMaKH(hoaDon.getMaKH()).getHoTen(), hoaDon.getTrangThai() };
			model.addRow(row);
		}
	}

	public String generateAutoCode() {
		String uppercaseLetters = "HD";
		String numbers = "0123456789";

		Random random = new Random();
		StringBuilder codeBuilder = new StringBuilder();

		codeBuilder.append(uppercaseLetters);

		for (int i = 0; i < 5; i++) {
			int randomIndex = random.nextInt(numbers.length());
			codeBuilder.append(numbers.charAt(randomIndex));
		}

		for (int i = 0; i < 5; i++) {
			int randomIndex = random.nextInt(numbers.length());
			codeBuilder.append(numbers.charAt(randomIndex));
		}

		return codeBuilder.toString();
	}

	public String generateAutoCodeKH() {
		String uppercaseLetters = "KH";
		String numbers = "0123456789";

		Random random = new Random();
		StringBuilder codeBuilder = new StringBuilder();

		codeBuilder.append(uppercaseLetters);

		for (int i = 0; i < 3; i++) {
			int randomIndex = random.nextInt(numbers.length());
			codeBuilder.append(numbers.charAt(randomIndex));
		}
		
		for (int i = 0; i < 3; i++) {
			int randomIndex = random.nextInt(numbers.length());
			codeBuilder.append(numbers.charAt(randomIndex));
		}

		return codeBuilder.toString();
	}
	
	public String generateAutoCodeSDT() {
		String uppercaseLetters = "+84";
		String numbers = "0123456789";

		Random random = new Random();
		StringBuilder codeBuilder = new StringBuilder();

		codeBuilder.append(uppercaseLetters);

		for (int i = 0; i < 9; i++) {
			int randomIndex = random.nextInt(numbers.length());
			codeBuilder.append(numbers.charAt(randomIndex));
		}

		return codeBuilder.toString();
	}

	public void loadCTHoaDon(String maHoaDon) {
		List<CTHoaDon> listChiTiet = HDCT_REPO.stream().filter(ct -> ct.getMaHD().equals(maHoaDon))
				.collect(Collectors.toList());

		DefaultTableModel model = (DefaultTableModel) tblCTHoaDon.getModel();
		model.setColumnCount(0);
		model.addColumn("Tên Laptop");
		model.addColumn("SerialNumber");
		model.addColumn("Đơn giá");

		model.setRowCount(0);
		for (CTHoaDon chiTiet : listChiTiet) {
			Object[] row = new Object[] { chiTiet.getTenLaptop(), chiTiet.getSerialNumber(),
					decimalFormat(chiTiet.getGia()) };

			model.addRow(row);
		}

	}

	public void resetForm() {
		txtSoDienThoai.setText(null);
		lblMaKH.setText("---");
		lblHoten.setText("---");
		lblDiaChi.setText("---");
		cboHinhThucThanhToan.setSelectedIndex(-1);
		txtMaPhieuGG.setText(null);
		txtSerialNumber.setText(null);
	}
	
	public static void removeBorder(Table table) {
        for (IElement iElement : table.getChildren()) {
            ((Cell)iElement).setBorder(Border.NO_BORDER);
        }
    }
    
    public static void ExportHD_PDF(List<CTHoaDon> list, HoaDon hoaDon) {
        try {
            PdfWriter writer = new PdfWriter("D:/ExportFile/PDF/"+hoaDon.getMaHD()+".pdf");

            PdfDocument pdf = new PdfDocument(writer);

            Document document = new Document(pdf);

            PdfFont font_simple = PdfFontFactory.createFont("C:/Users/nguye/Downloads/Font/Bo-Font-Full/Unicode/times.ttf", "Identity-H", true);
            PdfFont font_bold = PdfFontFactory.createFont("C:/Users/nguye/Downloads/Font/Bo-Font-Full/Unicode/timesbd.ttf", "Identity-H", true);

            float [] pointColumn_Info_SHOP_Widths = {270F, 270F};        
            Table table_Info_SHOP = new Table(UnitValue.createPointArray(pointColumn_Info_SHOP_Widths)); 
            
            table_Info_SHOP.addCell(new Paragraph("Shop Laptop 365").setFont(font_bold).setFontSize(24).setTextAlignment(TextAlignment.CENTER)); 
            table_Info_SHOP.addCell(new Paragraph(""));
            
            Cell cell = new Cell();
            cell.add(new Image(ImageDataFactory.create("D:/PRO1041/PRO1041_Project_1/ExcelReaders/src/main/java/com/exportfile/365_1.png")).setHorizontalAlignment(HorizontalAlignment.CENTER));
            
            table_Info_SHOP.addCell(cell); 
            table_Info_SHOP.addCell(new Paragraph("Hóa đơn bán hàng\nLaptop").setFont(font_bold).setFontSize(20).setTextAlignment(TextAlignment.CENTER)); 

            table_Info_SHOP.addCell(new Paragraph("Địa chỉ : Số 13 Trịnh Văn Bô").setFont(font_bold).setFontSize(16).setMargins(0, 0, 0, 30)); 
            table_Info_SHOP.addCell(new Paragraph("")); 
            
            table_Info_SHOP.addCell(new Paragraph("SĐT : 0387172021").setFont(font_bold).setFontSize(16).setMargins(0, 0, 0, 30)); 
            table_Info_SHOP.addCell(new Paragraph("")); 
            
            removeBorder(table_Info_SHOP);
            table_Info_SHOP.setBackgroundColor(new DeviceRgb(153, 209, 211));
            
            document.add(table_Info_SHOP);
            
            document.add(new Paragraph("\n"));
            
            Paragraph maHD = new Paragraph();
            maHD.add("Mã Hóa đơn : ");
            Text text_maHD = new Text(hoaDon.getMaHD());
            text_maHD.setFont(font_simple);
            maHD.add(text_maHD);
            maHD.setFont(font_bold);
            maHD.setFontSize(13);
            document.add(maHD);
            
            Paragraph tenKH = new Paragraph();
            tenKH.add("Tên Khách hàng : ");
            Text text = new Text(new KhachHangDAO().selectByMaKH(hoaDon.getMaKH()).getHoTen());
            text.setFont(font_simple);
            tenKH.add(text);
            tenKH.setFont(font_bold);
            tenKH.setFontSize(13);
            document.add(tenKH);
            
            Paragraph sdt = new Paragraph();
            sdt.add("Số điện thoại : ");
            Text sdts = new Text(new KhachHangDAO().selectByMaKH(hoaDon.getMaKH()).getSoDienThoai());
            sdts.setFont(font_simple);
            sdt.add(sdts);
            sdt.setFont(font_bold);
            sdt.setFontSize(13);
            document.add(sdt);
            
            Paragraph diaChi = new Paragraph();
            diaChi.add("Địa chỉ : ");
            Text diaChis = new Text(new KhachHangDAO().selectByMaKH(hoaDon.getMaKH()).getDiaChi());
            diaChis.setFont(font_simple);
            diaChi.add(diaChis);
            diaChi.setFont(font_bold);
            diaChi.setFontSize(13);
            document.add(diaChi);
            
            document.add(new Paragraph("\n"));
            
            float [] pointColumnWidths = {10F, 180F, 180F, 180F};        
            Table table = new Table(UnitValue.createPointArray(pointColumnWidths)); 

            table.addCell(new Paragraph("STT").setFont(font_bold).setFontSize(13));
            table.addCell(new Paragraph("Tên sản phẩm").setFont(font_bold).setFontSize(13)); 
            table.addCell(new Paragraph("SerialNumber").setFont(font_bold).setFontSize(13)); 
            table.addCell(new Paragraph("Giá").setFont(font_bold).setFontSize(13)); 
            int i = 0;
            for (CTHoaDon ctHoaDon : list) {
            	++i;
            	table.addCell(new Paragraph(i+"").setFont(font_simple));
            	table.addCell(new Paragraph(ctHoaDon.getTenLaptop()).setFont(font_simple).setFontSize(13)); 
                table.addCell(new Paragraph(ctHoaDon.getSerialNumber()).setFont(font_simple).setFontSize(13)); 
                table.addCell(new Paragraph(decimalFormat(ctHoaDon.getGia())).setFont(font_simple).setFontSize(13)); 
			}

            document.add(table);
            
            Paragraph tongTien = new Paragraph();
            Text tongTienText = new Text("Tổng tiền : ").setFont(font_bold).setFontSize(16);
            tongTien.add(tongTienText);
            Text text2 = new Text(hoaDon.getTongTien()+"").setFont(font_simple).setFontSize(13);
            tongTien.add(text2);
            tongTien.setTextAlignment(TextAlignment.RIGHT);
            tongTien.setMarginRight(38F);
            document.add(tongTien);

            @SuppressWarnings("deprecation")
			String day = String.valueOf(new Date().getDate())+ " ";
            @SuppressWarnings("deprecation")
			String month = String .valueOf(new Date().getMonth() + 1) + " ";
            @SuppressWarnings("deprecation")
			String year = String.valueOf(new Date().getYear()+1900) + " ";
            
            Paragraph now = new Paragraph();
            Text days = new Text("Ngày ").setFont(font_simple).setFontSize(16);
            now.add(days);
            now.add(day);
            Text months = new Text("tháng ").setFont(font_simple).setFontSize(16);
            now.add(months);
            now.add(month);
            Text years = new Text("năm ").setFont(font_simple).setFontSize(16);
            now.add(years);
            now.add(year);
            now.setTextAlignment(TextAlignment.RIGHT);
            now.setMarginRight(30F);
            now.setFontSize(16);
            document.add(now);
            
            float [] pointColumnChuKy = {270F, 270F};        
            Table table_ChuKy = new Table(UnitValue.createPointArray(pointColumnChuKy)); 

            table_ChuKy.addCell(new Paragraph("Khách hàng").setFont(font_bold).setFontSize(16).setTextAlignment(TextAlignment.CENTER));
            table_ChuKy.addCell(new Paragraph("Người bán hàng").setFont(font_bold).setFontSize(16).setTextAlignment(TextAlignment.CENTER));
            
            table_ChuKy.addCell(new Paragraph("\n").setFont(font_bold).setFontSize(16).setTextAlignment(TextAlignment.CENTER));
            table_ChuKy.addCell(new Paragraph("\n").setFont(font_bold).setFontSize(16).setTextAlignment(TextAlignment.CENTER));
            
            table_ChuKy.addCell(new Paragraph("\n").setFont(font_bold).setFontSize(16).setTextAlignment(TextAlignment.CENTER));
            table_ChuKy.addCell(new Paragraph("\n").setFont(font_bold).setFontSize(16).setTextAlignment(TextAlignment.CENTER));
            
            table_ChuKy.addCell(new Paragraph(new KhachHangDAO().selectByMaKH(hoaDon.getMaKH()).getHoTen()).setFont(font_simple).setFontSize(13).setTextAlignment(TextAlignment.CENTER));
            table_ChuKy.addCell(new Paragraph(lblMaNV.getText()).setFont(font_simple).setFontSize(13).setTextAlignment(TextAlignment.CENTER));
            
            removeBorder(table_ChuKy);
            
            document.add(table_ChuKy);
            
            IEventHandler footerHandler = new IEventHandler() {
                @Override
                public void handleEvent(Event event) {
                    PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
                    PdfDocument pdfDoc = docEvent.getDocument();
                    PdfPage page = docEvent.getPage();

                    PdfCanvas pdfCanvas = new PdfCanvas(page.newContentStreamBefore(), page.getResources(), pdfDoc);

                    @SuppressWarnings("resource")
					Canvas canvas = new Canvas(pdfCanvas, pdfDoc, page.getPageSize());
                    Paragraph p = new Paragraph("Xin cảm ơn quý khách!").setFont(font_simple);
                    canvas.showTextAligned(p, 297, 20, TextAlignment.CENTER);
                }
            };

            pdf.addEventHandler(PdfDocumentEvent.END_PAGE, footerHandler);
                                    
            document.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
