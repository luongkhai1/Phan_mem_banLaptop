package com.shoplaptop.ui;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.shoplaptop.dao.BienTheDAO;
import com.shoplaptop.dao.HangDAO;
import com.shoplaptop.dao.HeDieuHanhDAO;
import com.shoplaptop.dao.LaptopDAO;
import com.shoplaptop.dao.MoTaDAO;
import com.shoplaptop.dao.NhaCungCapDAO;
import com.shoplaptop.dao.PhanLoaiDAO;
import com.shoplaptop.dao.SerialNumberDAO;
import com.shoplaptop.entity.BienThe;
import com.shoplaptop.entity.Hang;
import com.shoplaptop.entity.Laptop;
import com.shoplaptop.entity.MoTa;
import com.shoplaptop.entity.NhaCungCap;
import com.shoplaptop.entity.PhanLoai;
import com.shoplaptop.entity.SerialNumber;
import com.shoplaptop.utils.Auth;
import com.shoplaptop.utils.MsgBox;
import com.shoplaptop.utils.XImage;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;

@SuppressWarnings("serial")
public class LaptopManager extends JDialog {
	private JTable tblNhaCungCap;
	private JTable tblLaptop;
	private JTextField txtFind;
	private JTextField txtTimKiem;
	private JTextField txtMaNCC;
	private JTextField txtTenNCC;
	private JTextField txtNguoiPhuTrach;
	private JTextField txtSoDienThoai;
	private JTextField txtEmail;
	private JTextField txtDiaChi;
	private DefaultTableModel modelLaptop;
	private JComboBox<Hang> cboHang;
	private JComboBox<PhanLoai> cboPhanLoai;
	private JComboBox<String> cboTrangThai;
	private JComboBox<String> cboHeDieuHanh;
	private JComboBox<String> cboGia;
	private JTable tblBienThe;
	private DefaultTableModel modelBienThe;
	private JButton btnThemBienThe;
	private List<Laptop> list = new ArrayList<Laptop>();
	private String maLaptop = "";
	public Laptop laptop = new Laptop();
	private BienThe bienThe = new BienThe();
	private int index = 1;
	public int RowCounts = -1;
	public double size = -1;

	private String selectBienTheByLaptop = "SELECT dbo.BienThe.ID, dbo.BienThe.MaBienThe, \r\n"
			+ "dbo.Laptop.MaLaptop,dbo.CPU.ID AS [ID_CPU], (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU) AS [CPU], \r\n"
			+ "dbo.RAM.ID AS [ID_RAM], (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB ' + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz') AS [RAM], \r\n"
			+ "dbo.ManHinh.ID AS [ID_ManHinh], (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - ' + dbo.ManHinh.doPhanGiai) AS [Màn hình], \r\n"
			+ "dbo.GPU.ID AS [ID_GPU], (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang) AS [GPU], \r\n"
			+ "dbo.OCung.ID AS [ID_OCung], (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB') AS [Ổ cứng], \r\n"
			+ "MauSac, \r\n"
			+ "dbo.HeDieuHanh.ID AS [ID_HeDieuHanh], (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)') AS [Hệ điều hành], \r\n"
			+ "Gia, \r\n"
			+ "Hinh, \r\n"
			+ "COUNT(SerialNumber) AS [Số lượng]  \r\n"
			+ "FROM dbo.BienThe JOIN dbo.Laptop ON Laptop.ID = BienThe.ID_Laptop LEFT JOIN dbo.Serial ON Serial.ID_BienThe = BienThe.ID JOIN dbo.CPU ON CPU.ID = BienThe.CPU JOIN dbo.RAM ON RAM.ID = BienThe.RAM JOIN dbo.ManHinh ON ManHinh.ID = BienThe.ManHinh JOIN dbo.GPU ON GPU.ID = BienThe.GPU JOIN dbo.OCung ON OCung.ID = BienThe.OCung JOIN dbo.HeDieuHanh ON HeDieuHanh.ID = BienThe.HeDieuHanh \r\n"
			+ "WHERE dbo.Laptop.MaLaptop = ? \r\n"
			+ "GROUP BY (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU),\r\n"
			+ "         (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB '\r\n"
			+ "         + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz'\r\n"
			+ "         ),\r\n"
			+ "         (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - '\r\n"
			+ "         + dbo.ManHinh.doPhanGiai\r\n"
			+ "         ),\r\n"
			+ "         (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang),\r\n"
			+ "         (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB'),\r\n"
			+ "         (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)'),\r\n"
			+ "         BienThe.ID,\r\n"
			+ "			MaBienThe,\r\n"
			+ "         MaLaptop,\r\n"
			+ "         CPU.ID,\r\n"
			+ "         RAM.ID,\r\n"
			+ "         ManHinh.ID,\r\n"
			+ "         GPU.ID,\r\n"
			+ "         OCung.ID,\r\n"
			+ "         MauSac,\r\n"
			+ "         HeDieuHanh.ID,\r\n"
			+ "		 Gia,\r\n"
			+ "		 Hinh";
	
	private String selectBT_Below_TonKho = "SELECT dbo.BienThe.ID, dbo.BienThe.MaBienThe, \r\n"
			+ "dbo.Laptop.MaLaptop,dbo.CPU.ID AS [ID_CPU], (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU) AS [CPU], \r\n"
			+ "dbo.RAM.ID AS [ID_RAM], (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB ' + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz') AS [RAM], \r\n"
			+ "dbo.ManHinh.ID AS [ID_ManHinh], (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - ' + dbo.ManHinh.doPhanGiai) AS [Màn hình], \r\n"
			+ "dbo.GPU.ID AS [ID_GPU], (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang) AS [GPU], \r\n"
			+ "dbo.OCung.ID AS [ID_OCung], (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB') AS [Ổ cứng], \r\n"
			+ "MauSac, \r\n"
			+ "dbo.HeDieuHanh.ID AS [ID_HeDieuHanh], (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)') AS [Hệ điều hành], \r\n"
			+ "Gia, \r\n"
			+ "Hinh, \r\n"
			+ "COUNT(SerialNumber) AS [Số lượng]  \r\n"
			+ "FROM dbo.BienThe JOIN dbo.Laptop ON Laptop.ID = BienThe.ID_Laptop LEFT JOIN dbo.Serial ON Serial.ID_BienThe = BienThe.ID JOIN dbo.CPU ON CPU.ID = BienThe.CPU JOIN dbo.RAM ON RAM.ID = BienThe.RAM JOIN dbo.ManHinh ON ManHinh.ID = BienThe.ManHinh JOIN dbo.GPU ON GPU.ID = BienThe.GPU JOIN dbo.OCung ON OCung.ID = BienThe.OCung JOIN dbo.HeDieuHanh ON HeDieuHanh.ID = BienThe.HeDieuHanh \r\n"
			+ "WHERE dbo.Serial.TrangThai = 1 \r\n"
			+ "GROUP BY (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU),\r\n"
			+ "         (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB '\r\n"
			+ "         + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz'\r\n"
			+ "         ),\r\n"
			+ "         (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - '\r\n"
			+ "         + dbo.ManHinh.doPhanGiai\r\n"
			+ "         ),\r\n"
			+ "         (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang),\r\n"
			+ "         (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB'),\r\n"
			+ "         (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)'),\r\n"
			+ "         dbo.BienThe.ID,\r\n"
			+ "		 MaBienThe,\r\n"
			+ "         MaLaptop,\r\n"
			+ "         CPU.ID,\r\n"
			+ "         RAM.ID,\r\n"
			+ "         ManHinh.ID,\r\n"
			+ "         GPU.ID,\r\n"
			+ "         OCung.ID,\r\n"
			+ "         MauSac,\r\n"
			+ "         HeDieuHanh.ID,\r\n"
			+ "		 Gia,\r\n"
			+ "		 Hinh\r\n"
			+ "HAVING COUNT(SerialNumber) < ?";
	
	private String selectBT_Above_TonKho = "SELECT dbo.BienThe.ID, dbo.BienThe.MaBienThe, \r\n"
			+ "dbo.Laptop.MaLaptop,dbo.CPU.ID AS [ID_CPU], (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU) AS [CPU], \r\n"
			+ "dbo.RAM.ID AS [ID_RAM], (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB ' + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz') AS [RAM], \r\n"
			+ "dbo.ManHinh.ID AS [ID_ManHinh], (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - ' + dbo.ManHinh.doPhanGiai) AS [Màn hình], \r\n"
			+ "dbo.GPU.ID AS [ID_GPU], (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang) AS [GPU], \r\n"
			+ "dbo.OCung.ID AS [ID_OCung], (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB') AS [Ổ cứng], \r\n"
			+ "MauSac, \r\n"
			+ "dbo.HeDieuHanh.ID AS [ID_HeDieuHanh], (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)') AS [Hệ điều hành], \r\n"
			+ "Gia, \r\n"
			+ "Hinh, \r\n"
			+ "COUNT(SerialNumber) AS [Số lượng]  \r\n"
			+ "FROM dbo.BienThe JOIN dbo.Laptop ON Laptop.ID = BienThe.ID_Laptop LEFT JOIN dbo.Serial ON Serial.ID_BienThe = BienThe.ID JOIN dbo.CPU ON CPU.ID = BienThe.CPU JOIN dbo.RAM ON RAM.ID = BienThe.RAM JOIN dbo.ManHinh ON ManHinh.ID = BienThe.ManHinh JOIN dbo.GPU ON GPU.ID = BienThe.GPU JOIN dbo.OCung ON OCung.ID = BienThe.OCung JOIN dbo.HeDieuHanh ON HeDieuHanh.ID = BienThe.HeDieuHanh \r\n"
			+ "WHERE dbo.Serial.TrangThai = 1 \r\n"
			+ "GROUP BY (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU),\r\n"
			+ "         (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB '\r\n"
			+ "         + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz'\r\n"
			+ "         ),\r\n"
			+ "         (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - '\r\n"
			+ "         + dbo.ManHinh.doPhanGiai\r\n"
			+ "         ),\r\n"
			+ "         (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang),\r\n"
			+ "         (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB'),\r\n"
			+ "         (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)'),\r\n"
			+ "         dbo.BienThe.ID,\r\n"
			+ "		 MaBienThe,\r\n"
			+ "         MaLaptop,\r\n"
			+ "         CPU.ID,\r\n"
			+ "         RAM.ID,\r\n"
			+ "         ManHinh.ID,\r\n"
			+ "         GPU.ID,\r\n"
			+ "         OCung.ID,\r\n"
			+ "         MauSac,\r\n"
			+ "         HeDieuHanh.ID,\r\n"
			+ "		 Gia,\r\n"
			+ "		 Hinh\r\n"
			+ "HAVING COUNT(SerialNumber) > ?";
	
	private String selectBT_TonKho = "SELECT dbo.BienThe.ID, dbo.BienThe.MaBienThe, \r\n"
			+ "dbo.Laptop.MaLaptop,dbo.CPU.ID AS [ID_CPU], (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU) AS [CPU], \r\n"
			+ "dbo.RAM.ID AS [ID_RAM], (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB ' + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz') AS [RAM], \r\n"
			+ "dbo.ManHinh.ID AS [ID_ManHinh], (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - ' + dbo.ManHinh.doPhanGiai) AS [Màn hình], \r\n"
			+ "dbo.GPU.ID AS [ID_GPU], (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang) AS [GPU], \r\n"
			+ "dbo.OCung.ID AS [ID_OCung], (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB') AS [Ổ cứng], \r\n"
			+ "MauSac, \r\n"
			+ "dbo.HeDieuHanh.ID AS [ID_HeDieuHanh], (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)') AS [Hệ điều hành], \r\n"
			+ "Gia, \r\n"
			+ "Hinh, \r\n"
			+ "COUNT(SerialNumber) AS [Số lượng]  \r\n"
			+ "FROM dbo.BienThe JOIN dbo.Laptop ON Laptop.ID = BienThe.ID_Laptop LEFT JOIN dbo.Serial ON Serial.ID_BienThe = BienThe.ID JOIN dbo.CPU ON CPU.ID = BienThe.CPU JOIN dbo.RAM ON RAM.ID = BienThe.RAM JOIN dbo.ManHinh ON ManHinh.ID = BienThe.ManHinh JOIN dbo.GPU ON GPU.ID = BienThe.GPU JOIN dbo.OCung ON OCung.ID = BienThe.OCung JOIN dbo.HeDieuHanh ON HeDieuHanh.ID = BienThe.HeDieuHanh \r\n"
			+ "WHERE dbo.Serial.TrangThai = 1 \r\n"
			+ "GROUP BY (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU),\r\n"
			+ "         (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB '\r\n"
			+ "         + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz'\r\n"
			+ "         ),\r\n"
			+ "         (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - '\r\n"
			+ "         + dbo.ManHinh.doPhanGiai\r\n"
			+ "         ),\r\n"
			+ "         (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang),\r\n"
			+ "         (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB'),\r\n"
			+ "         (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)'),\r\n"
			+ "         dbo.BienThe.ID,\r\n"
			+ "		 MaBienThe,\r\n"
			+ "         MaLaptop,\r\n"
			+ "         CPU.ID,\r\n"
			+ "         RAM.ID,\r\n"
			+ "         ManHinh.ID,\r\n"
			+ "         GPU.ID,\r\n"
			+ "         OCung.ID,\r\n"
			+ "         MauSac,\r\n"
			+ "         HeDieuHanh.ID,\r\n"
			+ "		 Gia,\r\n"
			+ "		 Hinh\r\n"
			+ "HAVING COUNT(SerialNumber) >= ? AND COUNT(SerialNumber) <= ?";
	
	private String selectBT_HetHang_TonKho = "SELECT dbo.BienThe.ID, dbo.BienThe.MaBienThe, \r\n"
			+ "dbo.Laptop.MaLaptop,dbo.CPU.ID AS [ID_CPU], (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU) AS [CPU], \r\n"
			+ "dbo.RAM.ID AS [ID_RAM], (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB ' + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz') AS [RAM], \r\n"
			+ "dbo.ManHinh.ID AS [ID_ManHinh], (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - ' + dbo.ManHinh.doPhanGiai) AS [Màn hình], \r\n"
			+ "dbo.GPU.ID AS [ID_GPU], (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang) AS [GPU], \r\n"
			+ "dbo.OCung.ID AS [ID_OCung], (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB') AS [Ổ cứng], \r\n"
			+ "MauSac, \r\n"
			+ "dbo.HeDieuHanh.ID AS [ID_HeDieuHanh], (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)') AS [Hệ điều hành], \r\n"
			+ "Gia, \r\n"
			+ "Hinh, \r\n"
			+ "COUNT(SerialNumber) AS [Số lượng]  \r\n"
			+ "FROM dbo.BienThe JOIN dbo.Laptop ON Laptop.ID = BienThe.ID_Laptop LEFT JOIN dbo.Serial ON Serial.ID_BienThe = BienThe.ID JOIN dbo.CPU ON CPU.ID = BienThe.CPU JOIN dbo.RAM ON RAM.ID = BienThe.RAM JOIN dbo.ManHinh ON ManHinh.ID = BienThe.ManHinh JOIN dbo.GPU ON GPU.ID = BienThe.GPU JOIN dbo.OCung ON OCung.ID = BienThe.OCung JOIN dbo.HeDieuHanh ON HeDieuHanh.ID = BienThe.HeDieuHanh \r\n"
			+ "GROUP BY (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU),\r\n"
			+ "         (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB '\r\n"
			+ "         + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz'\r\n"
			+ "         ),\r\n"
			+ "         (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - '\r\n"
			+ "         + dbo.ManHinh.doPhanGiai\r\n"
			+ "         ),\r\n"
			+ "         (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang),\r\n"
			+ "         (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB'),\r\n"
			+ "         (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)'),\r\n"
			+ "         dbo.BienThe.ID,\r\n"
			+ "		 MaBienThe,\r\n"
			+ "         MaLaptop,\r\n"
			+ "         CPU.ID,\r\n"
			+ "         RAM.ID,\r\n"
			+ "         ManHinh.ID,\r\n"
			+ "         GPU.ID,\r\n"
			+ "         OCung.ID,\r\n"
			+ "         MauSac,\r\n"
			+ "         HeDieuHanh.ID,\r\n"
			+ "		 Gia,\r\n"
			+ "		 Hinh\r\n"
			+ "HAVING COUNT(SerialNumber) = 0";
	
	private String filterLaptop = "SELECT * FROM\r\n"
			+ "    (SELECT ROW_NUMBER() OVER (ORDER BY MaLaptop DESC) AS rownum, Laptop.ID, MaLaptop, TenLaptop, PhanLoai.ID AS PhanLoai, PhanLoai.Tenloai, Hang.ID AS Hang, Hang.TenHang, DongLaptop.ID AS DongLaptop, DongLaptop.TenDong, NamSanXuat  FROM Laptop JOIN PhanLoai ON Laptop.PhanLoai = PhanLoai.ID JOIN DongLaptop ON Laptop.DongLaptop = DongLaptop.ID JOIN Hang ON Hang.ID = DongLaptop.Hang WHERE dbo.Hang.TenHang LIKE ? AND dbo.PhanLoai.TenLoai LIKE ?)\r\n"
			+ "    AS temp\r\n"
			+ "    WHERE rownum BETWEEN ? AND ?";
	
	private String filterlaptops = "SELECT Laptop.ID, MaLaptop, TenLaptop, PhanLoai.ID AS PhanLoai, PhanLoai.Tenloai, Hang.ID AS Hang, Hang.TenHang, DongLaptop.ID AS DongLaptop, DongLaptop.TenDong, NamSanXuat  FROM Laptop JOIN PhanLoai ON Laptop.PhanLoai = PhanLoai.ID JOIN DongLaptop ON Laptop.DongLaptop = DongLaptop.ID JOIN Hang ON Hang.ID = DongLaptop.Hang WHERE dbo.Hang.TenHang LIKE ? AND dbo.PhanLoai.TenLoai LIKE ?";
	
	private String filterBienThe = "";
	
	private String SelectHeDieuHanh = "SELECT DISTINCT OS FROM dbo.HeDieuHanh";
	
	private JLabel to;
	private JLabel from;
	private DefaultTableModel modelNhaCungCap;
	public JTabbedPane tabbedPane;
	private JButton btnNhapHang;
	private JTable tableBienThe;
	private DefaultTableModel modelBienThe_TonKho;
	private JTable tableSerial;
	private DefaultTableModel modelSerial;
	private JComboBox<String> cboTonKho;
	private JTextField txtToiThieu;
	private JTextField txtToiDa;
	private JLabel lblMaLaptop;
	private JLabel lblTenLaptop;
	private JLabel lblPhanLoai;
	private JLabel lblHang;
	private JLabel lblDongLaptop;
	private JLabel lblNamSanXuat;
	private List<BienThe> listBT = new ArrayList<BienThe>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					LaptopManager dialog = new LaptopManager();
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
	public LaptopManager() {
		setTitle("Laptop Manager");
		setBounds(100, 100, 975, 750);
		setLocationRelativeTo(null);
		setIconImage(XImage.getAppIcon());
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Laptop Manager");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(321, 10, 316, 36);
		getContentPane().add(lblNewLabel);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 60, 941, 641);
		getContentPane().add(tabbedPane);

		JLayeredPane layeredPane = new JLayeredPane();
		tabbedPane.addTab("Danh mục", null, layeredPane, null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(10, 10, 257, 390);
		layeredPane.add(panel);
		panel.setLayout(null);

		JLabel lblHangs = new JLabel("Hãng");
		lblHangs.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblHangs.setBounds(10, 10, 150, 31);
		panel.add(lblHangs);

		cboHang = new JComboBox<Hang>();
		cboHang.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		fillCboHang();
		cboHang.setSelectedIndex(-1);
		cboHang.setBounds(10, 45, 237, 31);
		panel.add(cboHang);

		JLabel lblPhanLoais = new JLabel("Phân loại");
		lblPhanLoais.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblPhanLoais.setBounds(10, 86, 150, 31);
		panel.add(lblPhanLoais);

		cboPhanLoai = new JComboBox<PhanLoai>();
		cboPhanLoai.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		fillCboPhanLoai();
		cboPhanLoai.setSelectedIndex(-1);
		cboPhanLoai.setBounds(10, 121, 237, 31);
		panel.add(cboPhanLoai);

		JLabel lblHeDieuHanh = new JLabel("Hệ điều hành");
		lblHeDieuHanh.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblHeDieuHanh.setBounds(10, 162, 150, 31);
		panel.add(lblHeDieuHanh);

		cboHeDieuHanh = new JComboBox<String>();
		cboHeDieuHanh.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		fillCboHeDieuHanh();
		cboHeDieuHanh.setSelectedIndex(-1);
		cboHeDieuHanh.setSelectedIndex(-1);
		cboHeDieuHanh.setBounds(10, 197, 237, 31);
		panel.add(cboHeDieuHanh);

		JLabel lblTrangThai = new JLabel("Trạng thái");
		lblTrangThai.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTrangThai.setBounds(10, 314, 150, 31);
		panel.add(lblTrangThai);

		cboTrangThai = new JComboBox<String>();
		cboTrangThai.setModel(new DefaultComboBoxModel<String>(new String[] { "Active", "InActive" }));
		cboTrangThai.setSelectedIndex(-1);
		cboTrangThai.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		cboTrangThai.setBounds(10, 349, 237, 31);
		panel.add(cboTrangThai);

		JLabel lblGia = new JLabel("Giá");
		lblGia.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblGia.setBounds(10, 238, 150, 31);
		panel.add(lblGia);

		cboGia = new JComboBox<String>();
		cboGia.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		cboGia.setModel(new DefaultComboBoxModel<String>(new String[] { "Dưới 10 triệu", "Từ 10 - 15 triệu",
				"Từ 15 - 20 triệu", "Từ 20 - 30 triệu", "Trên 30 triệu" }));
		cboGia.setSelectedIndex(-1);
		cboGia.setBounds(10, 273, 237, 31);
		panel.add(cboGia);

		btnThemBienThe = new JButton("Thêm biến thể");
		btnThemBienThe.setEnabled(false);
		btnThemBienThe.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnThemBienThe.setBounds(490, 10, 148, 31);
		layeredPane.add(btnThemBienThe);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(277, 118, 649, 226);
		layeredPane.add(scrollPane_2);

		modelLaptop = new DefaultTableModel();
		String[] colsLaptop = { "Mã Laptop", "Tên Laptop", "Tên Loại", "Tên Hãng", "Tên Dòng", "Năm sản xuất" };
		modelLaptop.setColumnIdentifiers(colsLaptop);

		tblLaptop = new JTable(modelLaptop);
		tblLaptop.setRowMargin(3);
		tblLaptop.setRowHeight(25);
		tblLaptop.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		scrollPane_2.setViewportView(tblLaptop);

		JButton btnResetLT = new JButton("Reset");
		btnResetLT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cboHang.setSelectedIndex(-1);
				cboPhanLoai.setSelectedIndex(-1);
				cboHeDieuHanh.setSelectedIndex(-1);
				cboGia.setSelectedIndex(-1);
				cboTrangThai.setSelectedIndex(-1);
				fillTableLaptop(new LaptopDAO().selectAllLaptop(1));
				btnThemBienThe.setEnabled(false);
				btnNhapHang.setEnabled(false);
				modelBienThe.setRowCount(0);
			}
		});
		btnResetLT.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnResetLT.setBounds(797, 10, 129, 31);
		layeredPane.add(btnResetLT);

		JButton btnThemMoi = new JButton("Thêm mới");
		btnThemMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CreateLaptop(LaptopManager.this).setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				new CreateLaptop(LaptopManager.this).setVisible(true);
				CreateLaptop.tabbedPane.setSelectedIndex(0);
			}
		});
		btnThemMoi.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnThemMoi.setBounds(364, 10, 116, 31);
		layeredPane.add(btnThemMoi);

		JLabel lblNewLabel_1_3 = new JLabel("Tìm kiếm");
		lblNewLabel_1_3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_3.setBounds(277, 26, 86, 31);
		layeredPane.add(lblNewLabel_1_3);

		txtFind = new JTextField();
		txtFind.setToolTipText("");
		txtFind.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtFind.setBounds(277, 56, 510, 31);
		layeredPane.add(txtFind);
		txtFind.setColumns(10);
		JButton btnFind = new JButton("Find");
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			//	String timKiem = txtFind.getText();
			
			}
		});
		btnFind.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnFind.setBounds(797, 56, 129, 31);
		layeredPane.add(btnFind);

		JScrollPane scrollPane_2_1 = new JScrollPane();
		scrollPane_2_1.setBounds(10, 410, 916, 194);
		layeredPane.add(scrollPane_2_1);

		modelBienThe = new DefaultTableModel();
		String[] colsBienThe = { "Mã biến thể", "Mã Laptop", "CPU", "RAM", "Màn hình", "GPU", "Ổ cứng", "Màu sắc",
				"Hệ điều hành", "Giá", "Số lượng" };
		modelBienThe.setColumnIdentifiers(colsBienThe);

		tblBienThe = new JTable(modelBienThe);
		tblBienThe.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		tblBienThe.setRowMargin(3);
		tblBienThe.setRowHeight(25);
		scrollPane_2_1.setViewportView(tblBienThe);

		btnNhapHang = new JButton("Nhập hàng");
		btnNhapHang.setEnabled(false);
		btnNhapHang.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnNhapHang.setBounds(658, 10, 129, 31);
		layeredPane.add(btnNhapHang);

		JButton btnNext = new JButton("Next");
		btnNext.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnNext.setBounds(829, 369, 97, 31);
		layeredPane.add(btnNext);

		JButton btnPrev = new JButton("Prev");
		btnPrev.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnPrev.setBounds(277, 369, 97, 31);
		layeredPane.add(btnPrev);

		to = new JLabel("1");
		to.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		to.setHorizontalAlignment(SwingConstants.CENTER);
		to.setBounds(511, 369, 52, 31);
		layeredPane.add(to);

		JLabel lblNewLabel_1_1 = new JLabel("/");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setBounds(573, 369, 52, 31);
		layeredPane.add(lblNewLabel_1_1);

		from = new JLabel("-");
		from.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		from.setHorizontalAlignment(SwingConstants.CENTER);
		from.setBounds(635, 369, 52, 31);
		layeredPane.add(from);

		JLayeredPane layeredPane_2 = new JLayeredPane();
		tabbedPane.addTab("Nhà cung cấp", null, layeredPane_2, null);

		JLabel lblMaNCC = new JLabel("Mã nhà cung cấp");
		lblMaNCC.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblMaNCC.setBounds(10, 83, 171, 30);
		layeredPane_2.add(lblMaNCC);

		JLabel lblTenNCC = new JLabel("Tên nhà cung cấp");
		lblTenNCC.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTenNCC.setBounds(10, 153, 171, 30);
		layeredPane_2.add(lblTenNCC);

		JLabel lblNguoiPhuTrach = new JLabel("Người phụ trách");
		lblNguoiPhuTrach.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNguoiPhuTrach.setBounds(10, 215, 171, 30);
		layeredPane_2.add(lblNguoiPhuTrach);

		JLabel lblSDT = new JLabel("Số điện thoại");
		lblSDT.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblSDT.setBounds(453, 83, 171, 30);
		layeredPane_2.add(lblSDT);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblEmail.setBounds(453, 153, 171, 30);
		layeredPane_2.add(lblEmail);

		JLabel lblDiaChi = new JLabel("Địa chỉ");
		lblDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblDiaChi.setBounds(453, 215, 171, 30);
		layeredPane_2.add(lblDiaChi);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 339, 916, 246);
		layeredPane_2.add(scrollPane_1);
		
		modelNhaCungCap = new DefaultTableModel();
		String [] colsNCC = {"Mã NCC","Tên NCC","Người phụ trách","Số điện thoại","Email","Địa chỉ"};
		modelNhaCungCap.setColumnIdentifiers(colsNCC);
		
		tblNhaCungCap = new JTable(modelNhaCungCap);
		tblNhaCungCap.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		TableColumnModel columnModel = tblNhaCungCap.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(80);
		columnModel.getColumn(1).setPreferredWidth(230);
		columnModel.getColumn(2).setPreferredWidth(180);
		columnModel.getColumn(3).setPreferredWidth(105);
		columnModel.getColumn(4).setPreferredWidth(150);
		columnModel.getColumn(5).setPreferredWidth(150);
		tblNhaCungCap.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tblNhaCungCap.getSelectedRow();
				setFormNCC(new NhaCungCapDAO().selectAll().get(row));
			}
		});
		tblNhaCungCap.setRowMargin(3);
		tblNhaCungCap.setRowHeight(25);
		tblNhaCungCap.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		scrollPane_1.setViewportView(tblNhaCungCap);

		JLabel lblTmKim = new JLabel("Tìm kiếm");
		lblTmKim.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTmKim.setBounds(33, 23, 171, 30);
		layeredPane_2.add(lblTmKim);

		txtTimKiem = new JTextField();
		txtTimKiem.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtTimKiem.setBounds(195, 23, 527, 30);
		layeredPane_2.add(txtTimKiem);
		txtTimKiem.setColumns(10);

		JButton btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnTimKiem.setBounds(759, 23, 135, 32);
		layeredPane_2.add(btnTimKiem);

		JButton btnThem = new JButton("Thêm");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MsgBox.alert(getContentPane(), new NhaCungCapDAO().insert(getFormNCC()));
					fillTableNCC(new NhaCungCapDAO().selectAll());
					CreateLaptop.fillCboNCC();
				} catch (Exception e2) {
					MsgBox.alert(getContentPane(), "Nhập lại TT Nhà cung cấp");
				}
			}
		});
		btnThem.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnThem.setBounds(117, 287, 135, 32);
		layeredPane_2.add(btnThem);

		JButton btnCapNhat = new JButton("Cập nhật");
		btnCapNhat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MsgBox.alert(getContentPane(), new NhaCungCapDAO().update(getFormNCC()));
					fillTableNCC(new NhaCungCapDAO().selectAll());
					CreateLaptop.fillCboNCC();
				} catch (Exception e2) {
					MsgBox.alert(getContentPane(), "Nhập lại TT Nhà cung cấp");
				}
			}
		});
		btnCapNhat.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnCapNhat.setBounds(293, 287, 135, 32);
		layeredPane_2.add(btnCapNhat);

		JButton btnXoa = new JButton("Xóa");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!Auth.isManager()) {
					MsgBox.alert(getContentPane(), "Bạn không có quyền xóa!");
				} else {
					if (MsgBox.confirm(getContentPane(), "Xác nhận xóa")) {
						MsgBox.alert(getContentPane(), new NhaCungCapDAO().delete(txtMaNCC.getText()));
						dispose();
						CreateLaptop.fillCboNCC();
					}
				}
			}
		});
		btnXoa.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnXoa.setBounds(470, 287, 135, 32);
		layeredPane_2.add(btnXoa);

		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setFormNCC(new NhaCungCap());
			}
		});
		btnReset.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnReset.setBounds(646, 287, 135, 32);
		layeredPane_2.add(btnReset);

		txtMaNCC = new JTextField();
		txtMaNCC.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtMaNCC.setBounds(179, 83, 247, 30);
		layeredPane_2.add(txtMaNCC);
		txtMaNCC.setColumns(10);

		txtTenNCC = new JTextField();
		txtTenNCC.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtTenNCC.setColumns(10);
		txtTenNCC.setBounds(179, 153, 247, 30);
		layeredPane_2.add(txtTenNCC);

		txtNguoiPhuTrach = new JTextField();
		txtNguoiPhuTrach.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtNguoiPhuTrach.setColumns(10);
		txtNguoiPhuTrach.setBounds(179, 215, 247, 30);
		layeredPane_2.add(txtNguoiPhuTrach);

		txtSoDienThoai = new JTextField();
		txtSoDienThoai.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtSoDienThoai.setColumns(10);
		txtSoDienThoai.setBounds(634, 83, 247, 30);
		layeredPane_2.add(txtSoDienThoai);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtEmail.setColumns(10);
		txtEmail.setBounds(634, 153, 247, 30);
		layeredPane_2.add(txtEmail);

		txtDiaChi = new JTextField();
		txtDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(634, 215, 247, 30);
		layeredPane_2.add(txtDiaChi);
		
		JLayeredPane layeredPane_1 = new JLayeredPane();
		tabbedPane.addTab("Tồn kho", null, layeredPane_1, null);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(10, 69, 916, 251);
		layeredPane_1.add(scrollPane_3);
		
		modelBienThe_TonKho = new DefaultTableModel();
		modelBienThe_TonKho.setColumnIdentifiers(colsBienThe);
		
		tableBienThe = new JTable(modelBienThe_TonKho);
		tableBienThe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = tableBienThe.getSelectedRow();
				String maBienThe = (String) tableBienThe.getValueAt(index, 0);
				String maLaptop = (String) tableBienThe.getValueAt(index, 1);
				setLaptop(new LaptopDAO().selectById(maLaptop));
				String selectByMaBT = "SELECT Serial.ID, ID_BienThe, TenLaptop, dbo.BienThe.MaBienThe, SerialNumber, Gia, TrangThai \r\n"
						+ "FROM dbo.Serial JOIN dbo.BienThe ON BienThe.ID = Serial.ID_BienThe JOIN dbo.Laptop ON Laptop.ID = BienThe.ID_Laptop \r\n"
						+ "WHERE TrangThai = 1 AND dbo.BienThe.MaBienThe = ?";
				fillTableSerial(new SerialNumberDAO().selectBySQL(selectByMaBT, maBienThe));
			}
		});
		tableBienThe.setRowMargin(3);
		tableBienThe.setRowHeight(25);
		tableBienThe.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		scrollPane_3.setViewportView(tableBienThe);
		
		JLabel lblNewLabel_1 = new JLabel("Lọc tồn kho");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(41, 20, 114, 30);
		layeredPane_1.add(lblNewLabel_1);
		
		cboTonKho = new JComboBox<String>();
		cboTonKho.setModel(new DefaultComboBoxModel<String>(new String[] {"Hết hàng", "Dưới mức tồn kho", "Khoảng an toàn", "Trên mức tồn kho"}));
		cboTonKho.setSelectedIndex(-1);
		cboTonKho.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		cboTonKho.setBounds(160, 20, 254, 30);
		layeredPane_1.add(cboTonKho);
		
		JButton btnExport = new JButton("Export");
		btnExport.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnExport.setBounds(833, 20, 93, 30);
		layeredPane_1.add(btnExport);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(529, 349, 395, 255);
		layeredPane_1.add(scrollPane_4);
		
		modelSerial = new DefaultTableModel();
		String [] colsSerial = {"Mã Biến thể","SerialNumber"};
		modelSerial.setColumnIdentifiers(colsSerial);
		
		tableSerial = new JTable(modelSerial);
		tblNhaCungCap.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		TableColumnModel columnModelSerial = tableSerial.getColumnModel();
		columnModelSerial.getColumn(0).setPreferredWidth(80);
		columnModelSerial.getColumn(1).setPreferredWidth(230);
		tableSerial.setRowMargin(3);
		tableSerial.setRowHeight(25);
		tableSerial.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		scrollPane_4.setViewportView(tableSerial);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 349, 496, 255);
		layeredPane_1.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblMLaptop = new JLabel("Mã Laptop");
		lblMLaptop.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblMLaptop.setBounds(10, 10, 136, 31);
		panel_1.add(lblMLaptop);
		
		JLabel lblTnLaptop = new JLabel("Tên Laptop");
		lblTnLaptop.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTnLaptop.setBounds(10, 51, 136, 31);
		panel_1.add(lblTnLaptop);
		
		JLabel lblTnLaptop_1_1 = new JLabel("Phân loại");
		lblTnLaptop_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTnLaptop_1_1.setBounds(10, 92, 136, 31);
		panel_1.add(lblTnLaptop_1_1);
		
		JLabel lblTnLaptop_1 = new JLabel("Hãng");
		lblTnLaptop_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTnLaptop_1.setBounds(10, 133, 136, 31);
		panel_1.add(lblTnLaptop_1);
		
		JLabel lblTnLaptop_1_2 = new JLabel("Dòng Laptop");
		lblTnLaptop_1_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTnLaptop_1_2.setBounds(10, 174, 136, 31);
		panel_1.add(lblTnLaptop_1_2);
		
		JLabel lblNm = new JLabel("Năm sản xuất");
		lblNm.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNm.setBounds(10, 215, 136, 31);
		panel_1.add(lblNm);
		
		lblMaLaptop = new JLabel("---");
		lblMaLaptop.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblMaLaptop.setBounds(180, 10, 306, 31);
		panel_1.add(lblMaLaptop);
		
		lblTenLaptop = new JLabel("---");
		lblTenLaptop.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTenLaptop.setBounds(180, 51, 306, 31);
		panel_1.add(lblTenLaptop);
		
		lblPhanLoai = new JLabel("---");
		lblPhanLoai.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblPhanLoai.setBounds(180, 92, 306, 31);
		panel_1.add(lblPhanLoai);
		
		lblHang = new JLabel("---");
		lblHang.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblHang.setBounds(180, 133, 306, 31);
		panel_1.add(lblHang);
		
		lblDongLaptop = new JLabel("---");
		lblDongLaptop.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblDongLaptop.setBounds(180, 174, 306, 31);
		panel_1.add(lblDongLaptop);
		
		lblNamSanXuat = new JLabel("---");
		lblNamSanXuat.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNamSanXuat.setBounds(180, 215, 306, 31);
		panel_1.add(lblNamSanXuat);
		
		JLabel lblNewLabel_1_2 = new JLabel("Tối thiểu");
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_1_2.setBounds(424, 20, 83, 30);
		layeredPane_1.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Tối đa");
		lblNewLabel_1_2_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_1_2_1.setBounds(636, 20, 83, 30);
		layeredPane_1.add(lblNewLabel_1_2_1);
		
		txtToiThieu = new JTextField();
		txtToiThieu.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtToiThieu.setText("3");
		txtToiThieu.setBounds(518, 20, 93, 30);
		layeredPane_1.add(txtToiThieu);
		txtToiThieu.setColumns(10);
		
		txtToiDa = new JTextField();
		txtToiDa.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtToiDa.setText("10");
		txtToiDa.setColumns(10);
		txtToiDa.setBounds(713, 20, 93, 30);
		layeredPane_1.add(txtToiDa);

		cboHeDieuHanh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fillTableBienThe(filterBienThe());
			}
		});
		cboGia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fillTableBienThe(filterBienThe());
			}
		});
		cboTrangThai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fillTableBienThe(filterBienThe());
			}
		});
		cboHang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setPage(filterLaptops());
				list = filterLaptop(index);
				fillTableLaptop(list);
				
			}
		});
		cboPhanLoai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setPage(filterLaptops());
				list = filterLaptop(index);
				fillTableLaptop(list);
				
			}
		});
		tblLaptop.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int rows = tblLaptop.getSelectedRow();
				maLaptop = String.valueOf(tblLaptop.getValueAt(rows, 0));
				btnThemBienThe.setEnabled(true);
				btnNhapHang.setEnabled(false);
				fillTableBienThe(new BienTheDAO().selectBySQL(selectBienTheByLaptop, maLaptop));
				laptop = new LaptopDAO().selectById(maLaptop);
				if (e.getClickCount() == 2) {
					MoTa moTa = new MoTaDAO().selectById(laptop.getMaLaptop());
					new CreateLaptop(LaptopManager.this).setVisible(true);
					CreateLaptop.tabbedPane.setSelectedIndex(0);
					CreateLaptop.setFormLaptop(laptop);
					CreateLaptop.setFormMoTa(moTa);
				}
			}
		});
		btnThemBienThe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CreateLaptop(LaptopManager.this).setVisible(true);
				CreateLaptop.tabbedPane.setSelectedIndex(1);
				laptop = new LaptopDAO().selectById(maLaptop);
				CreateLaptop.txtMaLaptop_BT.setText(laptop.getMaLaptop());
			}
		});
		tblBienThe.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int rows = tblBienThe.getSelectedRow();
				String maBienThe = String.valueOf(tblBienThe.getValueAt(rows, 0));
				bienThe = new BienTheDAO().selectById(maBienThe);
				btnNhapHang.setEnabled(true);
				if (e.getClickCount() == 2) {
					new CreateLaptop(LaptopManager.this).setVisible(true);
					CreateLaptop.tabbedPane.setSelectedIndex(1);
					CreateLaptop.setFormBienThe(bienThe);
				}
			}
		});
		btnNhapHang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CreateLaptop(LaptopManager.this).setVisible(true);
				CreateLaptop.tabbedPane.setSelectedIndex(2);
				CreateLaptop.lblMaBienThe.setText(bienThe.getMaBienThe());
				CreateLaptop.lblTenMay.setText(new LaptopDAO().selectById(bienThe.getMaLaptop()).getTenLaptop());
				CreateLaptop.lblMaDonNhap.setText(generateAutoCode());
			}
		});
		fillTableLaptop(new LaptopDAO().selectAllLaptop(index));
		setPage(new LaptopDAO().selectAll());
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (index < RowCounts) {
					++index;
					to.setText(index + "");
					fillTableLaptop(filterLaptop((index - 1) * 8 + 1));
				}
			}
		});
		btnPrev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (index > 1) {
					--index;
					to.setText(index + "");
					fillTableLaptop(filterLaptop((index - 1) * 8 + 1));
				}
			}
		});
		
		fillTableNCC(new NhaCungCapDAO().selectAll());
		
		cboTonKho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtToiDa.getText().trim().isEmpty() || txtToiThieu.getText().trim().isEmpty()) {
					MsgBox.alert(getContentPane(), "Định mức tồn kho không để trống");
				}else {
					if (cboTonKho.getSelectedItem() == "Dưới mức tồn kho") {
						try {
							int below_TonKho = Integer.valueOf(txtToiThieu.getText());
							int above_TonKho = Integer.valueOf(txtToiDa.getText());
							if (below_TonKho >= 0 && above_TonKho > below_TonKho) {
								listBT = new BienTheDAO().selectBySQL(selectBT_Below_TonKho, txtToiThieu.getText());
								fillTableBienThe_TonKho(listBT);
							}else {
								MsgBox.alert(getContentPane(), "Định mức tối đa lớn hơn định mức tối thiểu");
							}
						} catch (Exception e2) {
							MsgBox.alert(getContentPane(), "Định mức tối thiểu/ tối đa là số nguyên dương");
						}
					}else {
						if (cboTonKho.getSelectedItem() == "Khoảng an toàn") {
							try {
								int below_TonKho = Integer.valueOf(txtToiThieu.getText());
								int above_TonKho = Integer.valueOf(txtToiDa.getText());
								if (below_TonKho >= 0 && above_TonKho > below_TonKho) {
									listBT = new BienTheDAO().selectBySQL(selectBT_TonKho, txtToiThieu.getText(), txtToiDa.getText());
									fillTableBienThe_TonKho(listBT);
								}else {
									MsgBox.alert(getContentPane(), "Định mức tối đa lớn hơn định mức tối thiểu");
								}
							} catch (Exception e2) {
								MsgBox.alert(getContentPane(), "Định mức tối thiểu/ tối đa là số nguyên dương");
							}
						}else {
							if (cboTonKho.getSelectedItem() == "Trên mức tồn kho") {
								try {
									int below_TonKho = Integer.valueOf(txtToiThieu.getText());
									int above_TonKho = Integer.valueOf(txtToiDa.getText());
									if (below_TonKho >= 0 && above_TonKho > below_TonKho) {
										listBT = new BienTheDAO().selectBySQL(selectBT_Above_TonKho, txtToiDa.getText());
										fillTableBienThe_TonKho(listBT);
									}else {
										MsgBox.alert(getContentPane(), "Định mức tối đa lớn hơn định mức tối thiểu");
									}
								} catch (Exception e2) {
									MsgBox.alert(getContentPane(), "Định mức tối thiểu/ tối đa là số nguyên dương");
								}
							}
						}
					}
				}
				if (cboTonKho.getSelectedItem() == "Hết hàng") {
					listBT = new BienTheDAO().selectBySQL(selectBT_HetHang_TonKho);
					fillTableBienThe_TonKho(listBT);
				}
			}
		});
		
		txtToiThieu.getDocument().addDocumentListener(new DocumentListener() {
       
			public void insertUpdate(DocumentEvent e) {
				warn();
				
			}
			public void removeUpdate(DocumentEvent e) {
				// Throw new RuntimeException
				
			}
			public void changedUpdate(DocumentEvent e) {
				warn();
			}
			private void warn() {
				if (cboTonKho.getSelectedItem() == "Dưới mức tồn kho") {
					try {
						int below_TonKho = Integer.valueOf(txtToiThieu.getText());
						int above_TonKho = Integer.valueOf(txtToiDa.getText());
						if (below_TonKho >= 0 && above_TonKho > below_TonKho) {
							listBT = new BienTheDAO().selectBySQL(selectBT_Below_TonKho, txtToiThieu.getText());
							fillTableBienThe_TonKho(listBT);
						}else {
							MsgBox.alert(getContentPane(), "Định mức tối đa lớn hơn định mức tối thiểu");
						}
					} catch (Exception e2) {
						MsgBox.alert(getContentPane(), "Định mức tối thiểu/ tối đa là số nguyên dương");
					}
				}else {
					if (cboTonKho.getSelectedItem() == "Khoảng an toàn") {
						try {
							int below_TonKho = Integer.valueOf(txtToiThieu.getText());
							int above_TonKho = Integer.valueOf(txtToiDa.getText());
							if (below_TonKho >= 0 && above_TonKho > below_TonKho) {
								listBT = new BienTheDAO().selectBySQL(selectBT_TonKho, txtToiThieu.getText(), txtToiDa.getText());
								fillTableBienThe_TonKho(listBT);
							}else {
								MsgBox.alert(getContentPane(), "Định mức tối đa lớn hơn định mức tối thiểu");
							}
						} catch (Exception e2) {
							MsgBox.alert(getContentPane(), "Định mức tối thiểu/ tối đa là số nguyên dương");
						}
					}else {
						if (cboTonKho.getSelectedItem() == "Trên mức tồn kho") {
							try {
								int below_TonKho = Integer.valueOf(txtToiThieu.getText());
								int above_TonKho = Integer.valueOf(txtToiDa.getText());
								if (below_TonKho >= 0 && above_TonKho > below_TonKho) {
									listBT = new BienTheDAO().selectBySQL(selectBT_Above_TonKho, txtToiDa.getText());
									fillTableBienThe_TonKho(listBT);
								}else {
									MsgBox.alert(getContentPane(), "Định mức tối đa lớn hơn định mức tối thiểu");
								}
							} catch (Exception e2) {
								MsgBox.alert(getContentPane(), "Định mức tối thiểu/ tối đa là số nguyên dương");
							}
						}
					}
				}
			}
			
        });
		
		txtToiDa.getDocument().addDocumentListener(new DocumentListener() {
		       
			public void insertUpdate(DocumentEvent e) {
				warn();
				
			}
			public void removeUpdate(DocumentEvent e) {
				// Throw new RuntimeException
				
			}
			public void changedUpdate(DocumentEvent e) {
				warn();
			}
			private void warn() {
				if (cboTonKho.getSelectedItem() == "Dưới mức tồn kho") {
					try {
						int below_TonKho = Integer.valueOf(txtToiThieu.getText());
						int above_TonKho = Integer.valueOf(txtToiDa.getText());
						if (below_TonKho >= 0 && above_TonKho > below_TonKho) {
							listBT = new BienTheDAO().selectBySQL(selectBT_Below_TonKho, txtToiThieu.getText());
							fillTableBienThe_TonKho(listBT);
						}else {
							MsgBox.alert(getContentPane(), "Định mức tối đa lớn hơn định mức tối thiểu");
						}
					} catch (Exception e2) {
						MsgBox.alert(getContentPane(), "Định mức tối thiểu/ tối đa là số nguyên dương");
					}
				}else {
					if (cboTonKho.getSelectedItem() == "Khoảng an toàn") {
						try {
							int below_TonKho = Integer.valueOf(txtToiThieu.getText());
							int above_TonKho = Integer.valueOf(txtToiDa.getText());
							if (below_TonKho >= 0 && above_TonKho > below_TonKho) {
								listBT = new BienTheDAO().selectBySQL(selectBT_TonKho, txtToiThieu.getText(), txtToiDa.getText());
								fillTableBienThe_TonKho(listBT);
							}else {
								MsgBox.alert(getContentPane(), "Định mức tối đa lớn hơn định mức tối thiểu");
							}
						} catch (Exception e2) {
							MsgBox.alert(getContentPane(), "Định mức tối thiểu/ tối đa là số nguyên dương");
						}
					}else {
						if (cboTonKho.getSelectedItem() == "Trên mức tồn kho") {
							try {
								int below_TonKho = Integer.valueOf(txtToiThieu.getText());
								int above_TonKho = Integer.valueOf(txtToiDa.getText());
								if (below_TonKho >= 0 && above_TonKho > below_TonKho) {
									listBT = new BienTheDAO().selectBySQL(selectBT_Above_TonKho, txtToiDa.getText());
									fillTableBienThe_TonKho(listBT);
								}else {
									MsgBox.alert(getContentPane(), "Định mức tối đa lớn hơn định mức tối thiểu");
								}
							} catch (Exception e2) {
								MsgBox.alert(getContentPane(), "Định mức tối thiểu/ tối đa là số nguyên dương");
							}
						}
					}
				}
			}
			
        });
		
		btnExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listBT.size() != 0) {
					exportFile(listBT);
				}else {
					MsgBox.alert(getContentPane(), "List is empty");
				}
			}
		});
		
	}

	public void fillCboHang() {
		DefaultComboBoxModel<Hang> model = (DefaultComboBoxModel<Hang>) cboHang.getModel();
		model.removeAllElements();
		List<Hang> list = new HangDAO().selectAll();
		for (Hang hang : list) {
			model.addElement(hang);
		}
	}

	public void fillCboPhanLoai() {
		DefaultComboBoxModel<PhanLoai> model = (DefaultComboBoxModel<PhanLoai>) cboPhanLoai.getModel();
		model.removeAllElements();
		List<PhanLoai> list = new PhanLoaiDAO().selectAll();
		for (PhanLoai phanLoai : list) {
			model.addElement(phanLoai);
		}
	}

	public void fillCboHeDieuHanh() {
		DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) cboHeDieuHanh.getModel();
		model.removeAllElements();
		List<String> list = new HeDieuHanhDAO().selectOS(SelectHeDieuHanh);
		for (String heDieuHanh : list) {
			model.addElement(heDieuHanh);
		}
	}

	public void fillTableLaptop(List<Laptop> list) {
		modelLaptop.setRowCount(0);
		try {
			for (Laptop laptop : list) {
				Object[] rows = new Object[] { laptop.getMaLaptop(), laptop.getTenLaptop(), laptop.getTenLoai(),
						laptop.getTenHang(), laptop.getTenDong(), laptop.getNamSanXuat() };
				modelLaptop.addRow(rows);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void fillTableBienThe(List<BienThe> list) {
		modelBienThe.setRowCount(0);
		try {
			for (BienThe bienThe : list) {
				Object[] rows = new Object[] { bienThe.getMaBienThe(), bienThe.getMaLaptop(), bienThe.getCpu(),
						bienThe.getRam(), bienThe.getManHinh(), bienThe.getGpu(), bienThe.getoCung(),
						bienThe.getMauSac(), bienThe.getHeDieuHanh(), decimalFormat(bienThe.getGia()), bienThe.getSoLuong() };
				modelBienThe.addRow(rows);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public List<Laptop> filterLaptop(int index) {
		String tenHang = "";
		String tenLoai = "";
		if (cboHang.getSelectedIndex() == -1) {
			tenHang = "%%";
		} else {
			Hang hang = (Hang) cboHang.getSelectedItem();
			tenHang = hang.getTenHang();
		}
		if (cboPhanLoai.getSelectedIndex() == -1) {
			tenLoai = "%%";
		} else {
			PhanLoai phanLoai = (PhanLoai) cboPhanLoai.getSelectedItem();
			tenLoai = phanLoai.getTenLoai();
		}

		return new LaptopDAO().selectBySQL(filterLaptop, tenHang, tenLoai, index, index+7);
	}
	
	public List<Laptop> filterLaptops() {
		String tenHang = "";
		String tenLoai = "";
		if (cboHang.getSelectedIndex() == -1) {
			tenHang = "%%";
		} else {
			Hang hang = (Hang) cboHang.getSelectedItem();
			tenHang = hang.getTenHang();
		}
		if (cboPhanLoai.getSelectedIndex() == -1) {
			tenLoai = "%%";
		} else {
			PhanLoai phanLoai = (PhanLoai) cboPhanLoai.getSelectedItem();
			tenLoai = phanLoai.getTenLoai();
		}

		return new LaptopDAO().selectBySQL(filterlaptops, tenHang, tenLoai);
	}

	public List<BienThe> filterBienThe() {
		if (cboHeDieuHanh.getSelectedIndex() != -1 && cboGia.getSelectedIndex() == -1
				&& cboTrangThai.getSelectedIndex() == -1) {
			filterBienThe = "SELECT dbo.BienThe.ID, dbo.BienThe.MaBienThe, \r\n"
					+ "dbo.Laptop.MaLaptop,dbo.CPU.ID AS [ID_CPU], (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU) AS [CPU], \r\n"
					+ "dbo.RAM.ID AS [ID_RAM], (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB ' + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz') AS [RAM], \r\n"
					+ "dbo.ManHinh.ID AS [ID_ManHinh], (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - ' + dbo.ManHinh.doPhanGiai) AS [Màn hình], \r\n"
					+ "dbo.GPU.ID AS [ID_GPU], (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang) AS [GPU], \r\n"
					+ "dbo.OCung.ID AS [ID_OCung], (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB') AS [Ổ cứng], \r\n"
					+ "MauSac, \r\n"
					+ "dbo.HeDieuHanh.ID AS [ID_HeDieuHanh], (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)') AS [Hệ điều hành], \r\n"
					+ "Gia, \r\n"
					+ "Hinh, \r\n"
					+ "COUNT(SerialNumber) AS [Số lượng]  \r\n"
					+ "FROM dbo.BienThe JOIN dbo.Laptop ON Laptop.ID = BienThe.ID_Laptop LEFT JOIN dbo.Serial ON Serial.ID_BienThe = BienThe.ID JOIN dbo.CPU ON CPU.ID = BienThe.CPU JOIN dbo.RAM ON RAM.ID = BienThe.RAM JOIN dbo.ManHinh ON ManHinh.ID = BienThe.ManHinh JOIN dbo.GPU ON GPU.ID = BienThe.GPU JOIN dbo.OCung ON OCung.ID = BienThe.OCung JOIN dbo.HeDieuHanh ON HeDieuHanh.ID = BienThe.HeDieuHanh \r\n"
					+ "WHERE OS LIKE ? \r\n"
					+ "GROUP BY (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU),\r\n"
					+ "         (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB '\r\n"
					+ "         + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz'\r\n"
					+ "         ),\r\n"
					+ "         (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - '\r\n"
					+ "         + dbo.ManHinh.doPhanGiai\r\n"
					+ "         ),\r\n"
					+ "         (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang),\r\n"
					+ "         (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB'),\r\n"
					+ "         (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)'),\r\n"
					+ "			dbo.BienThe.ID,\r\n"
					+ "         MaBienThe,\r\n"
					+ "         MaLaptop,\r\n"
					+ "         CPU.ID,\r\n"
					+ "         RAM.ID,\r\n"
					+ "         ManHinh.ID,\r\n"
					+ "         GPU.ID,\r\n"
					+ "         OCung.ID,\r\n"
					+ "         MauSac,\r\n"
					+ "         HeDieuHanh.ID,\r\n"
					+ "		 Gia,\r\n"
					+ "		 Hinh";
			String heDieuHanh = (String) cboHeDieuHanh.getSelectedItem();
			return new BienTheDAO().selectBySQL(filterBienThe, heDieuHanh);
		}
		if (cboHeDieuHanh.getSelectedIndex() == -1 && cboGia.getSelectedIndex() != -1
				&& cboTrangThai.getSelectedIndex() == -1) {
			if (cboGia.getSelectedItem() == "Dưới 10 triệu") {
				filterBienThe = "SELECT dbo.BienThe.ID, dbo.BienThe.MaBienThe, \r\n"
						+ "dbo.Laptop.MaLaptop,dbo.CPU.ID AS [ID_CPU], (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU) AS [CPU], \r\n"
						+ "dbo.RAM.ID AS [ID_RAM], (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB ' + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz') AS [RAM], \r\n"
						+ "dbo.ManHinh.ID AS [ID_ManHinh], (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - ' + dbo.ManHinh.doPhanGiai) AS [Màn hình], \r\n"
						+ "dbo.GPU.ID AS [ID_GPU], (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang) AS [GPU], \r\n"
						+ "dbo.OCung.ID AS [ID_OCung], (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB') AS [Ổ cứng], \r\n"
						+ "MauSac, \r\n"
						+ "dbo.HeDieuHanh.ID AS [ID_HeDieuHanh], (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)') AS [Hệ điều hành], \r\n"
						+ "Gia, \r\n"
						+ "Hinh, \r\n"
						+ "COUNT(SerialNumber) AS [Số lượng]  \r\n"
						+ "FROM dbo.BienThe JOIN dbo.Laptop ON Laptop.ID = BienThe.ID_Laptop LEFT JOIN dbo.Serial ON Serial.ID_BienThe = BienThe.ID JOIN dbo.CPU ON CPU.ID = BienThe.CPU JOIN dbo.RAM ON RAM.ID = BienThe.RAM JOIN dbo.ManHinh ON ManHinh.ID = BienThe.ManHinh JOIN dbo.GPU ON GPU.ID = BienThe.GPU JOIN dbo.OCung ON OCung.ID = BienThe.OCung JOIN dbo.HeDieuHanh ON HeDieuHanh.ID = BienThe.HeDieuHanh \r\n"
						+ "WHERE Gia < 10000000 \r\n"
						+ "GROUP BY (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU),\r\n"
						+ "         (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB '\r\n"
						+ "         + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz'\r\n"
						+ "         ),\r\n"
						+ "         (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - '\r\n"
						+ "         + dbo.ManHinh.doPhanGiai\r\n"
						+ "         ),\r\n"
						+ "         (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang),\r\n"
						+ "         (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB'),\r\n"
						+ "         (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)'),\r\n"
						+ "			dbo.BienThe.ID,\r\n"
						+ "         MaBienThe,\r\n"
						+ "         MaLaptop,\r\n"
						+ "         CPU.ID,\r\n"
						+ "         RAM.ID,\r\n"
						+ "         ManHinh.ID,\r\n"
						+ "         GPU.ID,\r\n"
						+ "         OCung.ID,\r\n"
						+ "         MauSac,\r\n"
						+ "         HeDieuHanh.ID,\r\n"
						+ "		 Gia,\r\n"
						+ "		 Hinh";
				return new BienTheDAO().selectBySQL(filterBienThe);
			}
			if (cboGia.getSelectedItem() == "Từ 10 - 15 triệu") {
				filterBienThe = "SELECT dbo.BienThe.ID, dbo.BienThe.MaBienThe, \r\n"
						+ "dbo.Laptop.MaLaptop,dbo.CPU.ID AS [ID_CPU], (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU) AS [CPU], \r\n"
						+ "dbo.RAM.ID AS [ID_RAM], (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB ' + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz') AS [RAM], \r\n"
						+ "dbo.ManHinh.ID AS [ID_ManHinh], (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - ' + dbo.ManHinh.doPhanGiai) AS [Màn hình], \r\n"
						+ "dbo.GPU.ID AS [ID_GPU], (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang) AS [GPU], \r\n"
						+ "dbo.OCung.ID AS [ID_OCung], (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB') AS [Ổ cứng], \r\n"
						+ "MauSac, \r\n"
						+ "dbo.HeDieuHanh.ID AS [ID_HeDieuHanh], (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)') AS [Hệ điều hành], \r\n"
						+ "Gia, \r\n"
						+ "Hinh, \r\n"
						+ "COUNT(SerialNumber) AS [Số lượng]  \r\n"
						+ "FROM dbo.BienThe JOIN dbo.Laptop ON Laptop.ID = BienThe.ID_Laptop LEFT JOIN dbo.Serial ON Serial.ID_BienThe = BienThe.ID JOIN dbo.CPU ON CPU.ID = BienThe.CPU JOIN dbo.RAM ON RAM.ID = BienThe.RAM JOIN dbo.ManHinh ON ManHinh.ID = BienThe.ManHinh JOIN dbo.GPU ON GPU.ID = BienThe.GPU JOIN dbo.OCung ON OCung.ID = BienThe.OCung JOIN dbo.HeDieuHanh ON HeDieuHanh.ID = BienThe.HeDieuHanh \r\n"
						+ "WHERE Gia >= 10000000 AND Gia < 15000000 \r\n"
						+ "GROUP BY (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU),\r\n"
						+ "         (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB '\r\n"
						+ "         + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz'\r\n"
						+ "         ),\r\n"
						+ "         (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - '\r\n"
						+ "         + dbo.ManHinh.doPhanGiai\r\n"
						+ "         ),\r\n"
						+ "         (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang),\r\n"
						+ "         (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB'),\r\n"
						+ "         (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)'),\r\n"
						+ "			dbo.BienThe.ID,\r\n"
						+ "         MaBienThe,\r\n"
						+ "         MaLaptop,\r\n"
						+ "         CPU.ID,\r\n"
						+ "         RAM.ID,\r\n"
						+ "         ManHinh.ID,\r\n"
						+ "         GPU.ID,\r\n"
						+ "         OCung.ID,\r\n"
						+ "         MauSac,\r\n"
						+ "         HeDieuHanh.ID,\r\n"
						+ "		 Gia,\r\n"
						+ "		 Hinh";
				return new BienTheDAO().selectBySQL(filterBienThe);
			}
			if (cboGia.getSelectedItem() == "Từ 15 - 20 triệu") {
				filterBienThe = "SELECT dbo.BienThe.ID, dbo.BienThe.MaBienThe, \r\n"
						+ "dbo.Laptop.MaLaptop,dbo.CPU.ID AS [ID_CPU], (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU) AS [CPU], \r\n"
						+ "dbo.RAM.ID AS [ID_RAM], (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB ' + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz') AS [RAM], \r\n"
						+ "dbo.ManHinh.ID AS [ID_ManHinh], (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - ' + dbo.ManHinh.doPhanGiai) AS [Màn hình], \r\n"
						+ "dbo.GPU.ID AS [ID_GPU], (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang) AS [GPU], \r\n"
						+ "dbo.OCung.ID AS [ID_OCung], (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB') AS [Ổ cứng], \r\n"
						+ "MauSac, \r\n"
						+ "dbo.HeDieuHanh.ID AS [ID_HeDieuHanh], (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)') AS [Hệ điều hành], \r\n"
						+ "Gia, \r\n"
						+ "Hinh, \r\n"
						+ "COUNT(SerialNumber) AS [Số lượng]  \r\n"
						+ "FROM dbo.BienThe JOIN dbo.Laptop ON Laptop.ID = BienThe.ID_Laptop LEFT JOIN dbo.Serial ON Serial.ID_BienThe = BienThe.ID JOIN dbo.CPU ON CPU.ID = BienThe.CPU JOIN dbo.RAM ON RAM.ID = BienThe.RAM JOIN dbo.ManHinh ON ManHinh.ID = BienThe.ManHinh JOIN dbo.GPU ON GPU.ID = BienThe.GPU JOIN dbo.OCung ON OCung.ID = BienThe.OCung JOIN dbo.HeDieuHanh ON HeDieuHanh.ID = BienThe.HeDieuHanh \r\n"
						+ "WHERE Gia >= 15000000 AND Gia < 20000000 \r\n"
						+ "GROUP BY (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU),\r\n"
						+ "         (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB '\r\n"
						+ "         + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz'\r\n"
						+ "         ),\r\n"
						+ "         (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - '\r\n"
						+ "         + dbo.ManHinh.doPhanGiai\r\n"
						+ "         ),\r\n"
						+ "         (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang),\r\n"
						+ "         (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB'),\r\n"
						+ "         (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)'),\r\n"
						+ "			dbo.BienThe.ID,\r\n"
						+ "         MaBienThe,\r\n"
						+ "         MaLaptop,\r\n"
						+ "         CPU.ID,\r\n"
						+ "         RAM.ID,\r\n"
						+ "         ManHinh.ID,\r\n"
						+ "         GPU.ID,\r\n"
						+ "         OCung.ID,\r\n"
						+ "         MauSac,\r\n"
						+ "         HeDieuHanh.ID,\r\n"
						+ "		 Gia,\r\n"
						+ "		 Hinh";
				return new BienTheDAO().selectBySQL(filterBienThe);
			}
			if (cboGia.getSelectedItem() == "Từ 20 - 30 triệu") {
				filterBienThe = "SELECT dbo.BienThe.ID, dbo.BienThe.MaBienThe, \r\n"
						+ "dbo.Laptop.MaLaptop,dbo.CPU.ID AS [ID_CPU], (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU) AS [CPU], \r\n"
						+ "dbo.RAM.ID AS [ID_RAM], (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB ' + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz') AS [RAM], \r\n"
						+ "dbo.ManHinh.ID AS [ID_ManHinh], (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - ' + dbo.ManHinh.doPhanGiai) AS [Màn hình], \r\n"
						+ "dbo.GPU.ID AS [ID_GPU], (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang) AS [GPU], \r\n"
						+ "dbo.OCung.ID AS [ID_OCung], (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB') AS [Ổ cứng], \r\n"
						+ "MauSac, \r\n"
						+ "dbo.HeDieuHanh.ID AS [ID_HeDieuHanh], (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)') AS [Hệ điều hành], \r\n"
						+ "Gia, \r\n"
						+ "Hinh, \r\n"
						+ "COUNT(SerialNumber) AS [Số lượng]  \r\n"
						+ "FROM dbo.BienThe JOIN dbo.Laptop ON Laptop.ID = BienThe.ID_Laptop LEFT JOIN dbo.Serial ON Serial.ID_BienThe = BienThe.ID JOIN dbo.CPU ON CPU.ID = BienThe.CPU JOIN dbo.RAM ON RAM.ID = BienThe.RAM JOIN dbo.ManHinh ON ManHinh.ID = BienThe.ManHinh JOIN dbo.GPU ON GPU.ID = BienThe.GPU JOIN dbo.OCung ON OCung.ID = BienThe.OCung JOIN dbo.HeDieuHanh ON HeDieuHanh.ID = BienThe.HeDieuHanh \r\n"
						+ "WHERE Gia >= 20000000 AND Gia < 30000000 \r\n"
						+ "GROUP BY (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU),\r\n"
						+ "         (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB '\r\n"
						+ "         + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz'\r\n"
						+ "         ),\r\n"
						+ "         (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - '\r\n"
						+ "         + dbo.ManHinh.doPhanGiai\r\n"
						+ "         ),\r\n"
						+ "         (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang),\r\n"
						+ "         (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB'),\r\n"
						+ "         (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)'),\r\n"
						+ "			dbo.BienThe.ID,\r\n"
						+ "         MaBienThe,\r\n"
						+ "         MaLaptop,\r\n"
						+ "         CPU.ID,\r\n"
						+ "         RAM.ID,\r\n"
						+ "         ManHinh.ID,\r\n"
						+ "         GPU.ID,\r\n"
						+ "         OCung.ID,\r\n"
						+ "         MauSac,\r\n"
						+ "         HeDieuHanh.ID,\r\n"
						+ "		 Gia,\r\n"
						+ "		 Hinh";
				return new BienTheDAO().selectBySQL(filterBienThe);
			}
			if (cboGia.getSelectedItem() == "Trên 30 triệu") {
				filterBienThe = "SELECT dbo.BienThe.ID, dbo.BienThe.MaBienThe, \r\n"
						+ "dbo.Laptop.MaLaptop,dbo.CPU.ID AS [ID_CPU], (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU) AS [CPU], \r\n"
						+ "dbo.RAM.ID AS [ID_RAM], (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB ' + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz') AS [RAM], \r\n"
						+ "dbo.ManHinh.ID AS [ID_ManHinh], (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - ' + dbo.ManHinh.doPhanGiai) AS [Màn hình], \r\n"
						+ "dbo.GPU.ID AS [ID_GPU], (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang) AS [GPU], \r\n"
						+ "dbo.OCung.ID AS [ID_OCung], (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB') AS [Ổ cứng], \r\n"
						+ "MauSac, \r\n"
						+ "dbo.HeDieuHanh.ID AS [ID_HeDieuHanh], (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)') AS [Hệ điều hành], \r\n"
						+ "Gia, \r\n"
						+ "Hinh, \r\n"
						+ "COUNT(SerialNumber) AS [Số lượng]  \r\n"
						+ "FROM dbo.BienThe JOIN dbo.Laptop ON Laptop.ID = BienThe.ID_Laptop LEFT JOIN dbo.Serial ON Serial.ID_BienThe = BienThe.ID JOIN dbo.CPU ON CPU.ID = BienThe.CPU JOIN dbo.RAM ON RAM.ID = BienThe.RAM JOIN dbo.ManHinh ON ManHinh.ID = BienThe.ManHinh JOIN dbo.GPU ON GPU.ID = BienThe.GPU JOIN dbo.OCung ON OCung.ID = BienThe.OCung JOIN dbo.HeDieuHanh ON HeDieuHanh.ID = BienThe.HeDieuHanh \r\n"
						+ "WHERE Gia >= 30000000 \r\n"
						+ "GROUP BY (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU),\r\n"
						+ "         (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB '\r\n"
						+ "         + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz'\r\n"
						+ "         ),\r\n"
						+ "         (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - '\r\n"
						+ "         + dbo.ManHinh.doPhanGiai\r\n"
						+ "         ),\r\n"
						+ "         (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang),\r\n"
						+ "         (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB'),\r\n"
						+ "         (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)'),\r\n"
						+ "			dbo.BienThe.ID,\r\n"
						+ "         MaBienThe,\r\n"
						+ "         MaLaptop,\r\n"
						+ "         CPU.ID,\r\n"
						+ "         RAM.ID,\r\n"
						+ "         ManHinh.ID,\r\n"
						+ "         GPU.ID,\r\n"
						+ "         OCung.ID,\r\n"
						+ "         MauSac,\r\n"
						+ "         HeDieuHanh.ID,\r\n"
						+ "		 Gia,\r\n"
						+ "		 Hinh";
				return new BienTheDAO().selectBySQL(filterBienThe);
			}
		}
		if (cboHeDieuHanh.getSelectedIndex() == -1 && cboGia.getSelectedIndex() == -1
				&& cboTrangThai.getSelectedIndex() != -1) {
			if (cboTrangThai.getSelectedItem() == "Active") {
				filterBienThe = "SELECT dbo.BienThe.ID, dbo.BienThe.MaBienThe, \r\n"
						+ "dbo.Laptop.MaLaptop,dbo.CPU.ID AS [ID_CPU], (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU) AS [CPU], \r\n"
						+ "dbo.RAM.ID AS [ID_RAM], (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB ' + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz') AS [RAM], \r\n"
						+ "dbo.ManHinh.ID AS [ID_ManHinh], (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - ' + dbo.ManHinh.doPhanGiai) AS [Màn hình], \r\n"
						+ "dbo.GPU.ID AS [ID_GPU], (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang) AS [GPU], \r\n"
						+ "dbo.OCung.ID AS [ID_OCung], (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB') AS [Ổ cứng], \r\n"
						+ "MauSac, \r\n"
						+ "dbo.HeDieuHanh.ID AS [ID_HeDieuHanh], (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)') AS [Hệ điều hành], \r\n"
						+ "Gia, \r\n"
						+ "Hinh, \r\n"
						+ "COUNT(SerialNumber) AS [Số lượng]  \r\n"
						+ "FROM dbo.BienThe JOIN dbo.Laptop ON Laptop.ID = BienThe.ID_Laptop LEFT JOIN dbo.Serial ON Serial.ID_BienThe = BienThe.ID JOIN dbo.CPU ON CPU.ID = BienThe.CPU JOIN dbo.RAM ON RAM.ID = BienThe.RAM JOIN dbo.ManHinh ON ManHinh.ID = BienThe.ManHinh JOIN dbo.GPU ON GPU.ID = BienThe.GPU JOIN dbo.OCung ON OCung.ID = BienThe.OCung JOIN dbo.HeDieuHanh ON HeDieuHanh.ID = BienThe.HeDieuHanh \r\n"
						+ "WHERE TrangThai = 0 \r\n"
						+ "GROUP BY (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU),\r\n"
						+ "         (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB '\r\n"
						+ "         + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz'\r\n"
						+ "         ),\r\n"
						+ "         (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - '\r\n"
						+ "         + dbo.ManHinh.doPhanGiai\r\n"
						+ "         ),\r\n"
						+ "         (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang),\r\n"
						+ "         (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB'),\r\n"
						+ "         (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)'),\r\n"
						+ "			dbo.BienThe.ID,\r\n"
						+ "         MaBienThe,\r\n"
						+ "         MaLaptop,\r\n"
						+ "         CPU.ID,\r\n"
						+ "         RAM.ID,\r\n"
						+ "         ManHinh.ID,\r\n"
						+ "         GPU.ID,\r\n"
						+ "         OCung.ID,\r\n"
						+ "         MauSac,\r\n"
						+ "         HeDieuHanh.ID,\r\n"
						+ "		 Gia,\r\n"
						+ "		 Hinh";
				return new BienTheDAO().selectBySQL(filterBienThe);
			}
			if (cboTrangThai.getSelectedItem() == "InActive") {
				filterBienThe = "SELECT dbo.BienThe.ID, dbo.BienThe.MaBienThe, \r\n"
						+ "dbo.Laptop.MaLaptop,dbo.CPU.ID AS [ID_CPU], (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU) AS [CPU], \r\n"
						+ "dbo.RAM.ID AS [ID_RAM], (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB ' + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz') AS [RAM], \r\n"
						+ "dbo.ManHinh.ID AS [ID_ManHinh], (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - ' + dbo.ManHinh.doPhanGiai) AS [Màn hình], \r\n"
						+ "dbo.GPU.ID AS [ID_GPU], (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang) AS [GPU], \r\n"
						+ "dbo.OCung.ID AS [ID_OCung], (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB') AS [Ổ cứng], \r\n"
						+ "MauSac, \r\n"
						+ "dbo.HeDieuHanh.ID AS [ID_HeDieuHanh], (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)') AS [Hệ điều hành], \r\n"
						+ "Gia, \r\n"
						+ "Hinh, \r\n"
						+ "COUNT(SerialNumber) AS [Số lượng]  \r\n"
						+ "FROM dbo.BienThe JOIN dbo.Laptop ON Laptop.ID = BienThe.ID_Laptop LEFT JOIN dbo.Serial ON Serial.ID_BienThe = BienThe.ID JOIN dbo.CPU ON CPU.ID = BienThe.CPU JOIN dbo.RAM ON RAM.ID = BienThe.RAM JOIN dbo.ManHinh ON ManHinh.ID = BienThe.ManHinh JOIN dbo.GPU ON GPU.ID = BienThe.GPU JOIN dbo.OCung ON OCung.ID = BienThe.OCung JOIN dbo.HeDieuHanh ON HeDieuHanh.ID = BienThe.HeDieuHanh \r\n"
						+ "WHERE TrangThai = 1 \r\n"
						+ "GROUP BY (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU),\r\n"
						+ "         (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB '\r\n"
						+ "         + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz'\r\n"
						+ "         ),\r\n"
						+ "         (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - '\r\n"
						+ "         + dbo.ManHinh.doPhanGiai\r\n"
						+ "         ),\r\n"
						+ "         (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang),\r\n"
						+ "         (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB'),\r\n"
						+ "         (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)'),\r\n"
						+ "			dbo.BienThe.ID,\r\n"
						+ "         MaBienThe,\r\n"
						+ "         MaLaptop,\r\n"
						+ "         CPU.ID,\r\n"
						+ "         RAM.ID,\r\n"
						+ "         ManHinh.ID,\r\n"
						+ "         GPU.ID,\r\n"
						+ "         OCung.ID,\r\n"
						+ "         MauSac,\r\n"
						+ "         HeDieuHanh.ID,\r\n"
						+ "		 Gia,\r\n"
						+ "		 Hinh";
				return new BienTheDAO().selectBySQL(filterBienThe);
			}
		}
		if (cboHeDieuHanh.getSelectedIndex() != -1 && cboGia.getSelectedIndex() != -1
				&& cboTrangThai.getSelectedIndex() == -1) {
			String heDieuHanh = (String) cboHeDieuHanh.getSelectedItem();
			if (cboGia.getSelectedItem() == "Dưới 10 triệu") {
				filterBienThe = "SELECT dbo.BienThe.ID, dbo.BienThe.MaBienThe, \r\n"
						+ "dbo.Laptop.MaLaptop,dbo.CPU.ID AS [ID_CPU], (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU) AS [CPU], \r\n"
						+ "dbo.RAM.ID AS [ID_RAM], (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB ' + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz') AS [RAM], \r\n"
						+ "dbo.ManHinh.ID AS [ID_ManHinh], (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - ' + dbo.ManHinh.doPhanGiai) AS [Màn hình], \r\n"
						+ "dbo.GPU.ID AS [ID_GPU], (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang) AS [GPU], \r\n"
						+ "dbo.OCung.ID AS [ID_OCung], (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB') AS [Ổ cứng], \r\n"
						+ "MauSac, \r\n"
						+ "dbo.HeDieuHanh.ID AS [ID_HeDieuHanh], (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)') AS [Hệ điều hành], \r\n"
						+ "Gia, \r\n"
						+ "Hinh, \r\n"
						+ "COUNT(SerialNumber) AS [Số lượng]  \r\n"
						+ "FROM dbo.BienThe JOIN dbo.Laptop ON Laptop.ID = BienThe.ID_Laptop LEFT JOIN dbo.Serial ON Serial.ID_BienThe = BienThe.ID JOIN dbo.CPU ON CPU.ID = BienThe.CPU JOIN dbo.RAM ON RAM.ID = BienThe.RAM JOIN dbo.ManHinh ON ManHinh.ID = BienThe.ManHinh JOIN dbo.GPU ON GPU.ID = BienThe.GPU JOIN dbo.OCung ON OCung.ID = BienThe.OCung JOIN dbo.HeDieuHanh ON HeDieuHanh.ID = BienThe.HeDieuHanh \r\n"
						+ "WHERE Gia < 10000000 AND OS LIKE ? \r\n"
						+ "GROUP BY (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU),\r\n"
						+ "         (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB '\r\n"
						+ "         + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz'\r\n"
						+ "         ),\r\n"
						+ "         (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - '\r\n"
						+ "         + dbo.ManHinh.doPhanGiai\r\n"
						+ "         ),\r\n"
						+ "         (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang),\r\n"
						+ "         (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB'),\r\n"
						+ "         (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)'),\r\n"
						+ "			dbo.BienThe.ID,\r\n"
						+ "         MaBienThe,\r\n"
						+ "         MaLaptop,\r\n"
						+ "         CPU.ID,\r\n"
						+ "         RAM.ID,\r\n"
						+ "         ManHinh.ID,\r\n"
						+ "         GPU.ID,\r\n"
						+ "         OCung.ID,\r\n"
						+ "         MauSac,\r\n"
						+ "         HeDieuHanh.ID,\r\n"
						+ "		 Gia,\r\n"
						+ "		 Hinh";
				return new BienTheDAO().selectBySQL(filterBienThe, heDieuHanh);
			}
			if (cboGia.getSelectedItem() == "Từ 10 - 15 triệu") {
				filterBienThe = "SELECT dbo.BienThe.ID, dbo.BienThe.MaBienThe, \r\n"
						+ "dbo.Laptop.MaLaptop,dbo.CPU.ID AS [ID_CPU], (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU) AS [CPU], \r\n"
						+ "dbo.RAM.ID AS [ID_RAM], (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB ' + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz') AS [RAM], \r\n"
						+ "dbo.ManHinh.ID AS [ID_ManHinh], (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - ' + dbo.ManHinh.doPhanGiai) AS [Màn hình], \r\n"
						+ "dbo.GPU.ID AS [ID_GPU], (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang) AS [GPU], \r\n"
						+ "dbo.OCung.ID AS [ID_OCung], (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB') AS [Ổ cứng], \r\n"
						+ "MauSac, \r\n"
						+ "dbo.HeDieuHanh.ID AS [ID_HeDieuHanh], (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)') AS [Hệ điều hành], \r\n"
						+ "Gia, \r\n"
						+ "Hinh, \r\n"
						+ "COUNT(SerialNumber) AS [Số lượng]  \r\n"
						+ "FROM dbo.BienThe JOIN dbo.Laptop ON Laptop.ID = BienThe.ID_Laptop LEFT JOIN dbo.Serial ON Serial.ID_BienThe = BienThe.ID JOIN dbo.CPU ON CPU.ID = BienThe.CPU JOIN dbo.RAM ON RAM.ID = BienThe.RAM JOIN dbo.ManHinh ON ManHinh.ID = BienThe.ManHinh JOIN dbo.GPU ON GPU.ID = BienThe.GPU JOIN dbo.OCung ON OCung.ID = BienThe.OCung JOIN dbo.HeDieuHanh ON HeDieuHanh.ID = BienThe.HeDieuHanh \r\n"
						+ "WHERE Gia >= 10000000 AND Gia < 15000000 AND OS LIKE ? \r\n"
						+ "GROUP BY (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU),\r\n"
						+ "         (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB '\r\n"
						+ "         + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz'\r\n"
						+ "         ),\r\n"
						+ "         (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - '\r\n"
						+ "         + dbo.ManHinh.doPhanGiai\r\n"
						+ "         ),\r\n"
						+ "         (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang),\r\n"
						+ "         (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB'),\r\n"
						+ "         (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)'),\r\n"
						+ "			dbo.BienThe.ID,\r\n"
						+ "         MaBienThe,\r\n"
						+ "         MaLaptop,\r\n"
						+ "         CPU.ID,\r\n"
						+ "         RAM.ID,\r\n"
						+ "         ManHinh.ID,\r\n"
						+ "         GPU.ID,\r\n"
						+ "         OCung.ID,\r\n"
						+ "         MauSac,\r\n"
						+ "         HeDieuHanh.ID,\r\n"
						+ "		 Gia,\r\n"
						+ "		 Hinh";
				return new BienTheDAO().selectBySQL(filterBienThe, heDieuHanh);
			}
			if (cboGia.getSelectedItem() == "Từ 15 - 20 triệu") {
				filterBienThe = "SELECT dbo.BienThe.ID, dbo.BienThe.MaBienThe, \r\n"
						+ "dbo.Laptop.MaLaptop,dbo.CPU.ID AS [ID_CPU], (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU) AS [CPU], \r\n"
						+ "dbo.RAM.ID AS [ID_RAM], (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB ' + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz') AS [RAM], \r\n"
						+ "dbo.ManHinh.ID AS [ID_ManHinh], (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - ' + dbo.ManHinh.doPhanGiai) AS [Màn hình], \r\n"
						+ "dbo.GPU.ID AS [ID_GPU], (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang) AS [GPU], \r\n"
						+ "dbo.OCung.ID AS [ID_OCung], (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB') AS [Ổ cứng], \r\n"
						+ "MauSac, \r\n"
						+ "dbo.HeDieuHanh.ID AS [ID_HeDieuHanh], (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)') AS [Hệ điều hành], \r\n"
						+ "Gia, \r\n"
						+ "Hinh, \r\n"
						+ "COUNT(SerialNumber) AS [Số lượng]  \r\n"
						+ "FROM dbo.BienThe JOIN dbo.Laptop ON Laptop.ID = BienThe.ID_Laptop LEFT JOIN dbo.Serial ON Serial.ID_BienThe = BienThe.ID JOIN dbo.CPU ON CPU.ID = BienThe.CPU JOIN dbo.RAM ON RAM.ID = BienThe.RAM JOIN dbo.ManHinh ON ManHinh.ID = BienThe.ManHinh JOIN dbo.GPU ON GPU.ID = BienThe.GPU JOIN dbo.OCung ON OCung.ID = BienThe.OCung JOIN dbo.HeDieuHanh ON HeDieuHanh.ID = BienThe.HeDieuHanh \r\n"
						+ "WHERE Gia >= 15000000 AND Gia < 20000000 AND OS LIKE ? \r\n"
						+ "GROUP BY (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU),\r\n"
						+ "         (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB '\r\n"
						+ "         + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz'\r\n"
						+ "         ),\r\n"
						+ "         (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - '\r\n"
						+ "         + dbo.ManHinh.doPhanGiai\r\n"
						+ "         ),\r\n"
						+ "         (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang),\r\n"
						+ "         (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB'),\r\n"
						+ "         (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)'),\r\n"
						+ "			dbo.BienThe.ID,\r\n"
						+ "         MaBienThe,\r\n"
						+ "         MaLaptop,\r\n"
						+ "         CPU.ID,\r\n"
						+ "         RAM.ID,\r\n"
						+ "         ManHinh.ID,\r\n"
						+ "         GPU.ID,\r\n"
						+ "         OCung.ID,\r\n"
						+ "         MauSac,\r\n"
						+ "         HeDieuHanh.ID,\r\n"
						+ "		 Gia,\r\n"
						+ "		 Hinh";
				return new BienTheDAO().selectBySQL(filterBienThe, heDieuHanh);
			}
			if (cboGia.getSelectedItem() == "Từ 20 - 30 triệu") {
				filterBienThe = "SELECT dbo.BienThe.ID, dbo.BienThe.MaBienThe, \r\n"
						+ "dbo.Laptop.MaLaptop,dbo.CPU.ID AS [ID_CPU], (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU) AS [CPU], \r\n"
						+ "dbo.RAM.ID AS [ID_RAM], (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB ' + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz') AS [RAM], \r\n"
						+ "dbo.ManHinh.ID AS [ID_ManHinh], (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - ' + dbo.ManHinh.doPhanGiai) AS [Màn hình], \r\n"
						+ "dbo.GPU.ID AS [ID_GPU], (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang) AS [GPU], \r\n"
						+ "dbo.OCung.ID AS [ID_OCung], (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB') AS [Ổ cứng], \r\n"
						+ "MauSac, \r\n"
						+ "dbo.HeDieuHanh.ID AS [ID_HeDieuHanh], (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)') AS [Hệ điều hành], \r\n"
						+ "Gia, \r\n"
						+ "Hinh, \r\n"
						+ "COUNT(SerialNumber) AS [Số lượng]  \r\n"
						+ "FROM dbo.BienThe JOIN dbo.Laptop ON Laptop.ID = BienThe.ID_Laptop LEFT JOIN dbo.Serial ON Serial.ID_BienThe = BienThe.ID JOIN dbo.CPU ON CPU.ID = BienThe.CPU JOIN dbo.RAM ON RAM.ID = BienThe.RAM JOIN dbo.ManHinh ON ManHinh.ID = BienThe.ManHinh JOIN dbo.GPU ON GPU.ID = BienThe.GPU JOIN dbo.OCung ON OCung.ID = BienThe.OCung JOIN dbo.HeDieuHanh ON HeDieuHanh.ID = BienThe.HeDieuHanh \r\n"
						+ "WHERE Gia >= 20000000 AND Gia < 30000000 AND OS LIKE ? \r\n"
						+ "GROUP BY (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU),\r\n"
						+ "         (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB '\r\n"
						+ "         + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz'\r\n"
						+ "         ),\r\n"
						+ "         (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - '\r\n"
						+ "         + dbo.ManHinh.doPhanGiai\r\n"
						+ "         ),\r\n"
						+ "         (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang),\r\n"
						+ "         (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB'),\r\n"
						+ "         (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)'),\r\n"
						+ "			dbo.BienThe.ID,\r\n"
						+ "         MaBienThe,\r\n"
						+ "         MaLaptop,\r\n"
						+ "         CPU.ID,\r\n"
						+ "         RAM.ID,\r\n"
						+ "         ManHinh.ID,\r\n"
						+ "         GPU.ID,\r\n"
						+ "         OCung.ID,\r\n"
						+ "         MauSac,\r\n"
						+ "         HeDieuHanh.ID,\r\n"
						+ "		 Gia,\r\n"
						+ "		 Hinh";
				return new BienTheDAO().selectBySQL(filterBienThe, heDieuHanh);
			}
			if (cboGia.getSelectedItem() == "Trên 30 triệu") {
				filterBienThe = "SELECT dbo.BienThe.ID, dbo.BienThe.MaBienThe, \r\n"
						+ "dbo.Laptop.MaLaptop,dbo.CPU.ID AS [ID_CPU], (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU) AS [CPU], \r\n"
						+ "dbo.RAM.ID AS [ID_RAM], (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB ' + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz') AS [RAM], \r\n"
						+ "dbo.ManHinh.ID AS [ID_ManHinh], (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - ' + dbo.ManHinh.doPhanGiai) AS [Màn hình], \r\n"
						+ "dbo.GPU.ID AS [ID_GPU], (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang) AS [GPU], \r\n"
						+ "dbo.OCung.ID AS [ID_OCung], (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB') AS [Ổ cứng], \r\n"
						+ "MauSac, \r\n"
						+ "dbo.HeDieuHanh.ID AS [ID_HeDieuHanh], (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)') AS [Hệ điều hành], \r\n"
						+ "Gia, \r\n"
						+ "Hinh, \r\n"
						+ "COUNT(SerialNumber) AS [Số lượng]  \r\n"
						+ "FROM dbo.BienThe JOIN dbo.Laptop ON Laptop.ID = BienThe.ID_Laptop LEFT JOIN dbo.Serial ON Serial.ID_BienThe = BienThe.ID JOIN dbo.CPU ON CPU.ID = BienThe.CPU JOIN dbo.RAM ON RAM.ID = BienThe.RAM JOIN dbo.ManHinh ON ManHinh.ID = BienThe.ManHinh JOIN dbo.GPU ON GPU.ID = BienThe.GPU JOIN dbo.OCung ON OCung.ID = BienThe.OCung JOIN dbo.HeDieuHanh ON HeDieuHanh.ID = BienThe.HeDieuHanh \r\n"
						+ "WHERE Gia >= 30000000 AND OS LIKE ? \r\n"
						+ "GROUP BY (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU),\r\n"
						+ "         (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB '\r\n"
						+ "         + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz'\r\n"
						+ "         ),\r\n"
						+ "         (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - '\r\n"
						+ "         + dbo.ManHinh.doPhanGiai\r\n"
						+ "         ),\r\n"
						+ "         (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang),\r\n"
						+ "         (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB'),\r\n"
						+ "         (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)'),\r\n"
						+ "			dbo.BienThe.ID,\r\n"
						+ "         MaBienThe,\r\n"
						+ "         MaLaptop,\r\n"
						+ "         CPU.ID,\r\n"
						+ "         RAM.ID,\r\n"
						+ "         ManHinh.ID,\r\n"
						+ "         GPU.ID,\r\n"
						+ "         OCung.ID,\r\n"
						+ "         MauSac,\r\n"
						+ "         HeDieuHanh.ID,\r\n"
						+ "		 Gia,\r\n"
						+ "		 Hinh";
				return new BienTheDAO().selectBySQL(filterBienThe, heDieuHanh);
			}
		}
		if (cboHeDieuHanh.getSelectedIndex() == -1 && cboGia.getSelectedIndex() != -1
				&& cboTrangThai.getSelectedIndex() != -1) {
			if (cboGia.getSelectedItem() == "Dưới 10 triệu") {
				if (cboTrangThai.getSelectedItem() == "Active") {
					filterBienThe = "SELECT dbo.BienThe.ID, dbo.BienThe.MaBienThe, \r\n"
							+ "dbo.Laptop.MaLaptop,dbo.CPU.ID AS [ID_CPU], (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU) AS [CPU], \r\n"
							+ "dbo.RAM.ID AS [ID_RAM], (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB ' + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz') AS [RAM], \r\n"
							+ "dbo.ManHinh.ID AS [ID_ManHinh], (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - ' + dbo.ManHinh.doPhanGiai) AS [Màn hình], \r\n"
							+ "dbo.GPU.ID AS [ID_GPU], (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang) AS [GPU], \r\n"
							+ "dbo.OCung.ID AS [ID_OCung], (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB') AS [Ổ cứng], \r\n"
							+ "MauSac, \r\n"
							+ "dbo.HeDieuHanh.ID AS [ID_HeDieuHanh], (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)') AS [Hệ điều hành], \r\n"
							+ "Gia, \r\n"
							+ "Hinh, \r\n"
							+ "COUNT(SerialNumber) AS [Số lượng]  \r\n"
							+ "FROM dbo.BienThe JOIN dbo.Laptop ON Laptop.ID = BienThe.ID_Laptop LEFT JOIN dbo.Serial ON Serial.ID_BienThe = BienThe.ID JOIN dbo.CPU ON CPU.ID = BienThe.CPU JOIN dbo.RAM ON RAM.ID = BienThe.RAM JOIN dbo.ManHinh ON ManHinh.ID = BienThe.ManHinh JOIN dbo.GPU ON GPU.ID = BienThe.GPU JOIN dbo.OCung ON OCung.ID = BienThe.OCung JOIN dbo.HeDieuHanh ON HeDieuHanh.ID = BienThe.HeDieuHanh \r\n"
							+ "WHERE Gia < 10000000 AND TrangThai = 0 \r\n"
							+ "GROUP BY (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU),\r\n"
							+ "         (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB '\r\n"
							+ "         + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz'\r\n"
							+ "         ),\r\n"
							+ "         (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - '\r\n"
							+ "         + dbo.ManHinh.doPhanGiai\r\n"
							+ "         ),\r\n"
							+ "         (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang),\r\n"
							+ "         (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB'),\r\n"
							+ "         (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)'),\r\n"
							+ "			dbo.BienThe.ID,\r\n"
							+ "         MaBienThe,\r\n"
							+ "         MaLaptop,\r\n"
							+ "         CPU.ID,\r\n"
							+ "         RAM.ID,\r\n"
							+ "         ManHinh.ID,\r\n"
							+ "         GPU.ID,\r\n"
							+ "         OCung.ID,\r\n"
							+ "         MauSac,\r\n"
							+ "         HeDieuHanh.ID,\r\n"
							+ "		 Gia,\r\n"
							+ "		 Hinh";
					return new BienTheDAO().selectBySQL(filterBienThe);
				}
				if (cboTrangThai.getSelectedItem() == "InActive") {
					filterBienThe = "SELECT dbo.BienThe.ID, dbo.BienThe.MaBienThe, \r\n"
							+ "dbo.Laptop.MaLaptop,dbo.CPU.ID AS [ID_CPU], (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU) AS [CPU], \r\n"
							+ "dbo.RAM.ID AS [ID_RAM], (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB ' + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz') AS [RAM], \r\n"
							+ "dbo.ManHinh.ID AS [ID_ManHinh], (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - ' + dbo.ManHinh.doPhanGiai) AS [Màn hình], \r\n"
							+ "dbo.GPU.ID AS [ID_GPU], (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang) AS [GPU], \r\n"
							+ "dbo.OCung.ID AS [ID_OCung], (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB') AS [Ổ cứng], \r\n"
							+ "MauSac, \r\n"
							+ "dbo.HeDieuHanh.ID AS [ID_HeDieuHanh], (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)') AS [Hệ điều hành], \r\n"
							+ "Gia, \r\n"
							+ "Hinh, \r\n"
							+ "COUNT(SerialNumber) AS [Số lượng]  \r\n"
							+ "FROM dbo.BienThe JOIN dbo.Laptop ON Laptop.ID = BienThe.ID_Laptop LEFT JOIN dbo.Serial ON Serial.ID_BienThe = BienThe.ID JOIN dbo.CPU ON CPU.ID = BienThe.CPU JOIN dbo.RAM ON RAM.ID = BienThe.RAM JOIN dbo.ManHinh ON ManHinh.ID = BienThe.ManHinh JOIN dbo.GPU ON GPU.ID = BienThe.GPU JOIN dbo.OCung ON OCung.ID = BienThe.OCung JOIN dbo.HeDieuHanh ON HeDieuHanh.ID = BienThe.HeDieuHanh \r\n"
							+ "WHERE Gia < 10000000 AND TrangThai = 1 \r\n"
							+ "GROUP BY (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU),\r\n"
							+ "         (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB '\r\n"
							+ "         + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz'\r\n"
							+ "         ),\r\n"
							+ "         (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - '\r\n"
							+ "         + dbo.ManHinh.doPhanGiai\r\n"
							+ "         ),\r\n"
							+ "         (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang),\r\n"
							+ "         (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB'),\r\n"
							+ "         (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)'),\r\n"
							+ "			dbo.BienThe.ID,\r\n"
							+ "         MaBienThe,\r\n"
							+ "         MaLaptop,\r\n"
							+ "         CPU.ID,\r\n"
							+ "         RAM.ID,\r\n"
							+ "         ManHinh.ID,\r\n"
							+ "         GPU.ID,\r\n"
							+ "         OCung.ID,\r\n"
							+ "         MauSac,\r\n"
							+ "         HeDieuHanh.ID,\r\n"
							+ "		 Gia,\r\n"
							+ "		 Hinh";
					return new BienTheDAO().selectBySQL(filterBienThe);
				}
			}
			if (cboGia.getSelectedItem() == "Từ 10 - 15 triệu") {
				if (cboTrangThai.getSelectedItem() == "Active") {
					filterBienThe = "SELECT dbo.BienThe.ID, dbo.BienThe.MaBienThe, \r\n"
							+ "dbo.Laptop.MaLaptop,dbo.CPU.ID AS [ID_CPU], (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU) AS [CPU], \r\n"
							+ "dbo.RAM.ID AS [ID_RAM], (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB ' + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz') AS [RAM], \r\n"
							+ "dbo.ManHinh.ID AS [ID_ManHinh], (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - ' + dbo.ManHinh.doPhanGiai) AS [Màn hình], \r\n"
							+ "dbo.GPU.ID AS [ID_GPU], (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang) AS [GPU], \r\n"
							+ "dbo.OCung.ID AS [ID_OCung], (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB') AS [Ổ cứng], \r\n"
							+ "MauSac, \r\n"
							+ "dbo.HeDieuHanh.ID AS [ID_HeDieuHanh], (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)') AS [Hệ điều hành], \r\n"
							+ "Gia, \r\n"
							+ "Hinh, \r\n"
							+ "COUNT(SerialNumber) AS [Số lượng]  \r\n"
							+ "FROM dbo.BienThe JOIN dbo.Laptop ON Laptop.ID = BienThe.ID_Laptop LEFT JOIN dbo.Serial ON Serial.ID_BienThe = BienThe.ID JOIN dbo.CPU ON CPU.ID = BienThe.CPU JOIN dbo.RAM ON RAM.ID = BienThe.RAM JOIN dbo.ManHinh ON ManHinh.ID = BienThe.ManHinh JOIN dbo.GPU ON GPU.ID = BienThe.GPU JOIN dbo.OCung ON OCung.ID = BienThe.OCung JOIN dbo.HeDieuHanh ON HeDieuHanh.ID = BienThe.HeDieuHanh \r\n"
							+ "WHERE Gia >= 10000000 AND Gia < 15000000 AND TrangThai = 0 \r\n"
							+ "GROUP BY (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU),\r\n"
							+ "         (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB '\r\n"
							+ "         + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz'\r\n"
							+ "         ),\r\n"
							+ "         (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - '\r\n"
							+ "         + dbo.ManHinh.doPhanGiai\r\n"
							+ "         ),\r\n"
							+ "         (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang),\r\n"
							+ "         (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB'),\r\n"
							+ "         (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)'),\r\n"
							+ "			dbo.BienThe.ID,\r\n"
							+ "         MaBienThe,\r\n"
							+ "         MaLaptop,\r\n"
							+ "         CPU.ID,\r\n"
							+ "         RAM.ID,\r\n"
							+ "         ManHinh.ID,\r\n"
							+ "         GPU.ID,\r\n"
							+ "         OCung.ID,\r\n"
							+ "         MauSac,\r\n"
							+ "         HeDieuHanh.ID,\r\n"
							+ "		 Gia,\r\n"
							+ "		 Hinh";
					return new BienTheDAO().selectBySQL(filterBienThe);
				}
				if (cboTrangThai.getSelectedItem() == "InActive") {
					filterBienThe = "SELECT dbo.BienThe.ID, dbo.BienThe.MaBienThe, \r\n"
							+ "dbo.Laptop.MaLaptop,dbo.CPU.ID AS [ID_CPU], (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU) AS [CPU], \r\n"
							+ "dbo.RAM.ID AS [ID_RAM], (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB ' + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz') AS [RAM], \r\n"
							+ "dbo.ManHinh.ID AS [ID_ManHinh], (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - ' + dbo.ManHinh.doPhanGiai) AS [Màn hình], \r\n"
							+ "dbo.GPU.ID AS [ID_GPU], (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang) AS [GPU], \r\n"
							+ "dbo.OCung.ID AS [ID_OCung], (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB') AS [Ổ cứng], \r\n"
							+ "MauSac, \r\n"
							+ "dbo.HeDieuHanh.ID AS [ID_HeDieuHanh], (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)') AS [Hệ điều hành], \r\n"
							+ "Gia, \r\n"
							+ "Hinh, \r\n"
							+ "COUNT(SerialNumber) AS [Số lượng]  \r\n"
							+ "FROM dbo.BienThe JOIN dbo.Laptop ON Laptop.ID = BienThe.ID_Laptop LEFT JOIN dbo.Serial ON Serial.ID_BienThe = BienThe.ID JOIN dbo.CPU ON CPU.ID = BienThe.CPU JOIN dbo.RAM ON RAM.ID = BienThe.RAM JOIN dbo.ManHinh ON ManHinh.ID = BienThe.ManHinh JOIN dbo.GPU ON GPU.ID = BienThe.GPU JOIN dbo.OCung ON OCung.ID = BienThe.OCung JOIN dbo.HeDieuHanh ON HeDieuHanh.ID = BienThe.HeDieuHanh \r\n"
							+ "WHERE Gia >= 10000000 AND Gia < 15000000 AND TrangThai = 1 \r\n"
							+ "GROUP BY (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU),\r\n"
							+ "         (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB '\r\n"
							+ "         + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz'\r\n"
							+ "         ),\r\n"
							+ "         (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - '\r\n"
							+ "         + dbo.ManHinh.doPhanGiai\r\n"
							+ "         ),\r\n"
							+ "         (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang),\r\n"
							+ "         (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB'),\r\n"
							+ "         (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)'),\r\n"
							+ "			dbo.BienThe.ID,\r\n"
							+ "         MaBienThe,\r\n"
							+ "         MaLaptop,\r\n"
							+ "         CPU.ID,\r\n"
							+ "         RAM.ID,\r\n"
							+ "         ManHinh.ID,\r\n"
							+ "         GPU.ID,\r\n"
							+ "         OCung.ID,\r\n"
							+ "         MauSac,\r\n"
							+ "         HeDieuHanh.ID,\r\n"
							+ "		 Gia,\r\n"
							+ "		 Hinh";
					return new BienTheDAO().selectBySQL(filterBienThe);
				}
			}
			if (cboGia.getSelectedItem() == "Từ 15 - 20 triệu") {
				if (cboTrangThai.getSelectedItem() == "Active") {
					filterBienThe = "SELECT dbo.BienThe.ID, dbo.BienThe.MaBienThe, \r\n"
							+ "dbo.Laptop.MaLaptop,dbo.CPU.ID AS [ID_CPU], (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU) AS [CPU], \r\n"
							+ "dbo.RAM.ID AS [ID_RAM], (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB ' + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz') AS [RAM], \r\n"
							+ "dbo.ManHinh.ID AS [ID_ManHinh], (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - ' + dbo.ManHinh.doPhanGiai) AS [Màn hình], \r\n"
							+ "dbo.GPU.ID AS [ID_GPU], (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang) AS [GPU], \r\n"
							+ "dbo.OCung.ID AS [ID_OCung], (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB') AS [Ổ cứng], \r\n"
							+ "MauSac, \r\n"
							+ "dbo.HeDieuHanh.ID AS [ID_HeDieuHanh], (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)') AS [Hệ điều hành], \r\n"
							+ "Gia, \r\n"
							+ "Hinh, \r\n"
							+ "COUNT(SerialNumber) AS [Số lượng]  \r\n"
							+ "FROM dbo.BienThe JOIN dbo.Laptop ON Laptop.ID = BienThe.ID_Laptop LEFT JOIN dbo.Serial ON Serial.ID_BienThe = BienThe.ID JOIN dbo.CPU ON CPU.ID = BienThe.CPU JOIN dbo.RAM ON RAM.ID = BienThe.RAM JOIN dbo.ManHinh ON ManHinh.ID = BienThe.ManHinh JOIN dbo.GPU ON GPU.ID = BienThe.GPU JOIN dbo.OCung ON OCung.ID = BienThe.OCung JOIN dbo.HeDieuHanh ON HeDieuHanh.ID = BienThe.HeDieuHanh \r\n"
							+ "WHERE Gia >= 15000000 AND Gia < 20000000 AND TrangThai = 0 \r\n"
							+ "GROUP BY (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU),\r\n"
							+ "         (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB '\r\n"
							+ "         + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz'\r\n"
							+ "         ),\r\n"
							+ "         (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - '\r\n"
							+ "         + dbo.ManHinh.doPhanGiai\r\n"
							+ "         ),\r\n"
							+ "         (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang),\r\n"
							+ "         (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB'),\r\n"
							+ "         (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)'),\r\n"
							+ "			dbo.BienThe.ID,\r\n"
							+ "         MaBienThe,\r\n"
							+ "         MaLaptop,\r\n"
							+ "         CPU.ID,\r\n"
							+ "         RAM.ID,\r\n"
							+ "         ManHinh.ID,\r\n"
							+ "         GPU.ID,\r\n"
							+ "         OCung.ID,\r\n"
							+ "         MauSac,\r\n"
							+ "         HeDieuHanh.ID,\r\n"
							+ "		 Gia,\r\n"
							+ "		 Hinh";
					return new BienTheDAO().selectBySQL(filterBienThe);
				}
				if (cboTrangThai.getSelectedItem() == "InActive") {
					filterBienThe = "SELECT dbo.BienThe.ID, dbo.BienThe.MaBienThe, \r\n"
							+ "dbo.Laptop.MaLaptop,dbo.CPU.ID AS [ID_CPU], (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU) AS [CPU], \r\n"
							+ "dbo.RAM.ID AS [ID_RAM], (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB ' + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz') AS [RAM], \r\n"
							+ "dbo.ManHinh.ID AS [ID_ManHinh], (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - ' + dbo.ManHinh.doPhanGiai) AS [Màn hình], \r\n"
							+ "dbo.GPU.ID AS [ID_GPU], (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang) AS [GPU], \r\n"
							+ "dbo.OCung.ID AS [ID_OCung], (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB') AS [Ổ cứng], \r\n"
							+ "MauSac, \r\n"
							+ "dbo.HeDieuHanh.ID AS [ID_HeDieuHanh], (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)') AS [Hệ điều hành], \r\n"
							+ "Gia, \r\n"
							+ "Hinh, \r\n"
							+ "COUNT(SerialNumber) AS [Số lượng]  \r\n"
							+ "FROM dbo.BienThe JOIN dbo.Laptop ON Laptop.ID = BienThe.ID_Laptop LEFT JOIN dbo.Serial ON Serial.ID_BienThe = BienThe.ID JOIN dbo.CPU ON CPU.ID = BienThe.CPU JOIN dbo.RAM ON RAM.ID = BienThe.RAM JOIN dbo.ManHinh ON ManHinh.ID = BienThe.ManHinh JOIN dbo.GPU ON GPU.ID = BienThe.GPU JOIN dbo.OCung ON OCung.ID = BienThe.OCung JOIN dbo.HeDieuHanh ON HeDieuHanh.ID = BienThe.HeDieuHanh \r\n"
							+ "WHERE Gia >= 15000000 AND Gia < 20000000 AND TrangThai = 1 \r\n"
							+ "GROUP BY (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU),\r\n"
							+ "         (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB '\r\n"
							+ "         + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz'\r\n"
							+ "         ),\r\n"
							+ "         (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - '\r\n"
							+ "         + dbo.ManHinh.doPhanGiai\r\n"
							+ "         ),\r\n"
							+ "         (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang),\r\n"
							+ "         (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB'),\r\n"
							+ "         (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)'),\r\n"
							+ "			dbo.BienThe.ID,\r\n"
							+ "         MaBienThe,\r\n"
							+ "         MaLaptop,\r\n"
							+ "         CPU.ID,\r\n"
							+ "         RAM.ID,\r\n"
							+ "         ManHinh.ID,\r\n"
							+ "         GPU.ID,\r\n"
							+ "         OCung.ID,\r\n"
							+ "         MauSac,\r\n"
							+ "         HeDieuHanh.ID,\r\n"
							+ "		 Gia,\r\n"
							+ "		 Hinh";
					return new BienTheDAO().selectBySQL(filterBienThe);
				}
			}
			if (cboGia.getSelectedItem() == "Từ 20 - 30 triệu") {
				if (cboTrangThai.getSelectedItem() == "Active") {
					filterBienThe = "SELECT dbo.BienThe.ID, dbo.BienThe.MaBienThe, \r\n"
							+ "dbo.Laptop.MaLaptop,dbo.CPU.ID AS [ID_CPU], (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU) AS [CPU], \r\n"
							+ "dbo.RAM.ID AS [ID_RAM], (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB ' + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz') AS [RAM], \r\n"
							+ "dbo.ManHinh.ID AS [ID_ManHinh], (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - ' + dbo.ManHinh.doPhanGiai) AS [Màn hình], \r\n"
							+ "dbo.GPU.ID AS [ID_GPU], (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang) AS [GPU], \r\n"
							+ "dbo.OCung.ID AS [ID_OCung], (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB') AS [Ổ cứng], \r\n"
							+ "MauSac, \r\n"
							+ "dbo.HeDieuHanh.ID AS [ID_HeDieuHanh], (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)') AS [Hệ điều hành], \r\n"
							+ "Gia, \r\n"
							+ "Hinh, \r\n"
							+ "COUNT(SerialNumber) AS [Số lượng]  \r\n"
							+ "FROM dbo.BienThe JOIN dbo.Laptop ON Laptop.ID = BienThe.ID_Laptop LEFT JOIN dbo.Serial ON Serial.ID_BienThe = BienThe.ID JOIN dbo.CPU ON CPU.ID = BienThe.CPU JOIN dbo.RAM ON RAM.ID = BienThe.RAM JOIN dbo.ManHinh ON ManHinh.ID = BienThe.ManHinh JOIN dbo.GPU ON GPU.ID = BienThe.GPU JOIN dbo.OCung ON OCung.ID = BienThe.OCung JOIN dbo.HeDieuHanh ON HeDieuHanh.ID = BienThe.HeDieuHanh \r\n"
							+ "WHERE Gia >= 20000000 AND Gia < 30000000 AND TrangThai = 0 \r\n"
							+ "GROUP BY (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU),\r\n"
							+ "         (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB '\r\n"
							+ "         + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz'\r\n"
							+ "         ),\r\n"
							+ "         (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - '\r\n"
							+ "         + dbo.ManHinh.doPhanGiai\r\n"
							+ "         ),\r\n"
							+ "         (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang),\r\n"
							+ "         (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB'),\r\n"
							+ "         (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)'),\r\n"
							+ "			dbo.BienThe.ID,\r\n"
							+ "         MaBienThe,\r\n"
							+ "         MaLaptop,\r\n"
							+ "         CPU.ID,\r\n"
							+ "         RAM.ID,\r\n"
							+ "         ManHinh.ID,\r\n"
							+ "         GPU.ID,\r\n"
							+ "         OCung.ID,\r\n"
							+ "         MauSac,\r\n"
							+ "         HeDieuHanh.ID,\r\n"
							+ "		 Gia,\r\n"
							+ "		 Hinh";
					return new BienTheDAO().selectBySQL(filterBienThe);
				}
				if (cboTrangThai.getSelectedItem() == "InActive") {
					filterBienThe = "SELECT dbo.BienThe.ID, dbo.BienThe.MaBienThe, \r\n"
							+ "dbo.Laptop.MaLaptop,dbo.CPU.ID AS [ID_CPU], (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU) AS [CPU], \r\n"
							+ "dbo.RAM.ID AS [ID_RAM], (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB ' + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz') AS [RAM], \r\n"
							+ "dbo.ManHinh.ID AS [ID_ManHinh], (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - ' + dbo.ManHinh.doPhanGiai) AS [Màn hình], \r\n"
							+ "dbo.GPU.ID AS [ID_GPU], (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang) AS [GPU], \r\n"
							+ "dbo.OCung.ID AS [ID_OCung], (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB') AS [Ổ cứng], \r\n"
							+ "MauSac, \r\n"
							+ "dbo.HeDieuHanh.ID AS [ID_HeDieuHanh], (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)') AS [Hệ điều hành], \r\n"
							+ "Gia, \r\n"
							+ "Hinh, \r\n"
							+ "COUNT(SerialNumber) AS [Số lượng]  \r\n"
							+ "FROM dbo.BienThe JOIN dbo.Laptop ON Laptop.ID = BienThe.ID_Laptop LEFT JOIN dbo.Serial ON Serial.ID_BienThe = BienThe.ID JOIN dbo.CPU ON CPU.ID = BienThe.CPU JOIN dbo.RAM ON RAM.ID = BienThe.RAM JOIN dbo.ManHinh ON ManHinh.ID = BienThe.ManHinh JOIN dbo.GPU ON GPU.ID = BienThe.GPU JOIN dbo.OCung ON OCung.ID = BienThe.OCung JOIN dbo.HeDieuHanh ON HeDieuHanh.ID = BienThe.HeDieuHanh \r\n"
							+ "WHERE Gia >= 20000000 AND Gia < 30000000 AND TrangThai = 1 \r\n"
							+ "GROUP BY (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU),\r\n"
							+ "         (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB '\r\n"
							+ "         + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz'\r\n"
							+ "         ),\r\n"
							+ "         (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - '\r\n"
							+ "         + dbo.ManHinh.doPhanGiai\r\n"
							+ "         ),\r\n"
							+ "         (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang),\r\n"
							+ "         (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB'),\r\n"
							+ "         (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)'),\r\n"
							+ "			dbo.BienThe.ID,\r\n"
							+ "         MaBienThe,\r\n"
							+ "         MaLaptop,\r\n"
							+ "         CPU.ID,\r\n"
							+ "         RAM.ID,\r\n"
							+ "         ManHinh.ID,\r\n"
							+ "         GPU.ID,\r\n"
							+ "         OCung.ID,\r\n"
							+ "         MauSac,\r\n"
							+ "         HeDieuHanh.ID,\r\n"
							+ "		 Gia,\r\n"
							+ "		 Hinh";
					return new BienTheDAO().selectBySQL(filterBienThe);
				}
			}
			if (cboGia.getSelectedItem() == "Trên 30 triệu") {
				if (cboTrangThai.getSelectedItem() == "Active") {
					filterBienThe = "SELECT dbo.BienThe.ID, dbo.BienThe.MaBienThe, \r\n"
							+ "dbo.Laptop.MaLaptop,dbo.CPU.ID AS [ID_CPU], (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU) AS [CPU], \r\n"
							+ "dbo.RAM.ID AS [ID_RAM], (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB ' + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz') AS [RAM], \r\n"
							+ "dbo.ManHinh.ID AS [ID_ManHinh], (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - ' + dbo.ManHinh.doPhanGiai) AS [Màn hình], \r\n"
							+ "dbo.GPU.ID AS [ID_GPU], (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang) AS [GPU], \r\n"
							+ "dbo.OCung.ID AS [ID_OCung], (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB') AS [Ổ cứng], \r\n"
							+ "MauSac, \r\n"
							+ "dbo.HeDieuHanh.ID AS [ID_HeDieuHanh], (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)') AS [Hệ điều hành], \r\n"
							+ "Gia, \r\n"
							+ "Hinh, \r\n"
							+ "COUNT(SerialNumber) AS [Số lượng]  \r\n"
							+ "FROM dbo.BienThe JOIN dbo.Laptop ON Laptop.ID = BienThe.ID_Laptop LEFT JOIN dbo.Serial ON Serial.ID_BienThe = BienThe.ID JOIN dbo.CPU ON CPU.ID = BienThe.CPU JOIN dbo.RAM ON RAM.ID = BienThe.RAM JOIN dbo.ManHinh ON ManHinh.ID = BienThe.ManHinh JOIN dbo.GPU ON GPU.ID = BienThe.GPU JOIN dbo.OCung ON OCung.ID = BienThe.OCung JOIN dbo.HeDieuHanh ON HeDieuHanh.ID = BienThe.HeDieuHanh \r\n"
							+ "WHERE Gia >= 30000000 AND TrangThai = 0 \r\n"
							+ "GROUP BY (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU),\r\n"
							+ "         (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB '\r\n"
							+ "         + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz'\r\n"
							+ "         ),\r\n"
							+ "         (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - '\r\n"
							+ "         + dbo.ManHinh.doPhanGiai\r\n"
							+ "         ),\r\n"
							+ "         (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang),\r\n"
							+ "         (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB'),\r\n"
							+ "         (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)'),\r\n"
							+ "			dbo.BienThe.ID,\r\n"
							+ "         MaBienThe,\r\n"
							+ "         MaLaptop,\r\n"
							+ "         CPU.ID,\r\n"
							+ "         RAM.ID,\r\n"
							+ "         ManHinh.ID,\r\n"
							+ "         GPU.ID,\r\n"
							+ "         OCung.ID,\r\n"
							+ "         MauSac,\r\n"
							+ "         HeDieuHanh.ID,\r\n"
							+ "		 Gia,\r\n"
							+ "		 Hinh";
					return new BienTheDAO().selectBySQL(filterBienThe);
				}
				if (cboTrangThai.getSelectedItem() == "InActive") {
					filterBienThe = "SELECT dbo.BienThe.ID, dbo.BienThe.MaBienThe, \r\n"
							+ "dbo.Laptop.MaLaptop,dbo.CPU.ID AS [ID_CPU], (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU) AS [CPU], \r\n"
							+ "dbo.RAM.ID AS [ID_RAM], (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB ' + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz') AS [RAM], \r\n"
							+ "dbo.ManHinh.ID AS [ID_ManHinh], (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - ' + dbo.ManHinh.doPhanGiai) AS [Màn hình], \r\n"
							+ "dbo.GPU.ID AS [ID_GPU], (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang) AS [GPU], \r\n"
							+ "dbo.OCung.ID AS [ID_OCung], (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB') AS [Ổ cứng], \r\n"
							+ "MauSac, \r\n"
							+ "dbo.HeDieuHanh.ID AS [ID_HeDieuHanh], (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)') AS [Hệ điều hành], \r\n"
							+ "Gia, \r\n"
							+ "Hinh, \r\n"
							+ "COUNT(SerialNumber) AS [Số lượng]  \r\n"
							+ "FROM dbo.BienThe JOIN dbo.Laptop ON Laptop.ID = BienThe.ID_Laptop LEFT JOIN dbo.Serial ON Serial.ID_BienThe = BienThe.ID JOIN dbo.CPU ON CPU.ID = BienThe.CPU JOIN dbo.RAM ON RAM.ID = BienThe.RAM JOIN dbo.ManHinh ON ManHinh.ID = BienThe.ManHinh JOIN dbo.GPU ON GPU.ID = BienThe.GPU JOIN dbo.OCung ON OCung.ID = BienThe.OCung JOIN dbo.HeDieuHanh ON HeDieuHanh.ID = BienThe.HeDieuHanh \r\n"
							+ "WHERE Gia >= 30000000 AND TrangThai = 1 \r\n"
							+ "GROUP BY (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU),\r\n"
							+ "         (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB '\r\n"
							+ "         + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz'\r\n"
							+ "         ),\r\n"
							+ "         (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - '\r\n"
							+ "         + dbo.ManHinh.doPhanGiai\r\n"
							+ "         ),\r\n"
							+ "         (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang),\r\n"
							+ "         (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB'),\r\n"
							+ "         (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)'),\r\n"
							+ "			dbo.BienThe.ID,\r\n"
							+ "         MaBienThe,\r\n"
							+ "         MaLaptop,\r\n"
							+ "         CPU.ID,\r\n"
							+ "         RAM.ID,\r\n"
							+ "         ManHinh.ID,\r\n"
							+ "         GPU.ID,\r\n"
							+ "         OCung.ID,\r\n"
							+ "         MauSac,\r\n"
							+ "         HeDieuHanh.ID,\r\n"
							+ "		 Gia,\r\n"
							+ "		 Hinh";
					return new BienTheDAO().selectBySQL(filterBienThe);
				}
			}
		}
		if (cboHeDieuHanh.getSelectedIndex() != -1 && cboGia.getSelectedIndex() == -1
				&& cboTrangThai.getSelectedIndex() != -1) {
			String heDieuHanh = (String) cboHeDieuHanh.getSelectedItem();
			if (cboTrangThai.getSelectedItem() == "Active") {
				filterBienThe = "SELECT dbo.BienThe.ID, dbo.BienThe.MaBienThe, \r\n"
						+ "dbo.Laptop.MaLaptop,dbo.CPU.ID AS [ID_CPU], (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU) AS [CPU], \r\n"
						+ "dbo.RAM.ID AS [ID_RAM], (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB ' + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz') AS [RAM], \r\n"
						+ "dbo.ManHinh.ID AS [ID_ManHinh], (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - ' + dbo.ManHinh.doPhanGiai) AS [Màn hình], \r\n"
						+ "dbo.GPU.ID AS [ID_GPU], (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang) AS [GPU], \r\n"
						+ "dbo.OCung.ID AS [ID_OCung], (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB') AS [Ổ cứng], \r\n"
						+ "MauSac, \r\n"
						+ "dbo.HeDieuHanh.ID AS [ID_HeDieuHanh], (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)') AS [Hệ điều hành], \r\n"
						+ "Gia, \r\n"
						+ "Hinh, \r\n"
						+ "COUNT(SerialNumber) AS [Số lượng]  \r\n"
						+ "FROM dbo.BienThe JOIN dbo.Laptop ON Laptop.ID = BienThe.ID_Laptop LEFT JOIN dbo.Serial ON Serial.ID_BienThe = BienThe.ID JOIN dbo.CPU ON CPU.ID = BienThe.CPU JOIN dbo.RAM ON RAM.ID = BienThe.RAM JOIN dbo.ManHinh ON ManHinh.ID = BienThe.ManHinh JOIN dbo.GPU ON GPU.ID = BienThe.GPU JOIN dbo.OCung ON OCung.ID = BienThe.OCung JOIN dbo.HeDieuHanh ON HeDieuHanh.ID = BienThe.HeDieuHanh \r\n"
						+ "WHERE OS LIKE ? AND TrangThai = 0 \r\n"
						+ "GROUP BY (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU),\r\n"
						+ "         (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB '\r\n"
						+ "         + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz'\r\n"
						+ "         ),\r\n"
						+ "         (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - '\r\n"
						+ "         + dbo.ManHinh.doPhanGiai\r\n"
						+ "         ),\r\n"
						+ "         (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang),\r\n"
						+ "         (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB'),\r\n"
						+ "         (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)'),\r\n"
						+ "			dbo.BienThe.ID,\r\n"
						+ "         MaBienThe,\r\n"
						+ "         MaLaptop,\r\n"
						+ "         CPU.ID,\r\n"
						+ "         RAM.ID,\r\n"
						+ "         ManHinh.ID,\r\n"
						+ "         GPU.ID,\r\n"
						+ "         OCung.ID,\r\n"
						+ "         MauSac,\r\n"
						+ "         HeDieuHanh.ID,\r\n"
						+ "		 Gia,\r\n"
						+ "		 Hinh";
				return new BienTheDAO().selectBySQL(filterBienThe, heDieuHanh);
			}
			if (cboTrangThai.getSelectedItem() == "InActive") {
				filterBienThe = "SELECT dbo.BienThe.ID, dbo.BienThe.MaBienThe, \r\n"
						+ "dbo.Laptop.MaLaptop,dbo.CPU.ID AS [ID_CPU], (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU) AS [CPU], \r\n"
						+ "dbo.RAM.ID AS [ID_RAM], (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB ' + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz') AS [RAM], \r\n"
						+ "dbo.ManHinh.ID AS [ID_ManHinh], (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - ' + dbo.ManHinh.doPhanGiai) AS [Màn hình], \r\n"
						+ "dbo.GPU.ID AS [ID_GPU], (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang) AS [GPU], \r\n"
						+ "dbo.OCung.ID AS [ID_OCung], (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB') AS [Ổ cứng], \r\n"
						+ "MauSac, \r\n"
						+ "dbo.HeDieuHanh.ID AS [ID_HeDieuHanh], (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)') AS [Hệ điều hành], \r\n"
						+ "Gia, \r\n"
						+ "Hinh, \r\n"
						+ "COUNT(SerialNumber) AS [Số lượng]  \r\n"
						+ "FROM dbo.BienThe JOIN dbo.Laptop ON Laptop.ID = BienThe.ID_Laptop LEFT JOIN dbo.Serial ON Serial.ID_BienThe = BienThe.ID JOIN dbo.CPU ON CPU.ID = BienThe.CPU JOIN dbo.RAM ON RAM.ID = BienThe.RAM JOIN dbo.ManHinh ON ManHinh.ID = BienThe.ManHinh JOIN dbo.GPU ON GPU.ID = BienThe.GPU JOIN dbo.OCung ON OCung.ID = BienThe.OCung JOIN dbo.HeDieuHanh ON HeDieuHanh.ID = BienThe.HeDieuHanh \r\n"
						+ "WHERE OS LIKE ? AND TrangThai = 1 \r\n"
						+ "GROUP BY (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU),\r\n"
						+ "         (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB '\r\n"
						+ "         + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz'\r\n"
						+ "         ),\r\n"
						+ "         (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - '\r\n"
						+ "         + dbo.ManHinh.doPhanGiai\r\n"
						+ "         ),\r\n"
						+ "         (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang),\r\n"
						+ "         (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB'),\r\n"
						+ "         (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)'),\r\n"
						+ "			dbo.BienThe.ID,\r\n"
						+ "         MaBienThe,\r\n"
						+ "         MaLaptop,\r\n"
						+ "         CPU.ID,\r\n"
						+ "         RAM.ID,\r\n"
						+ "         ManHinh.ID,\r\n"
						+ "         GPU.ID,\r\n"
						+ "         OCung.ID,\r\n"
						+ "         MauSac,\r\n"
						+ "         HeDieuHanh.ID,\r\n"
						+ "		 Gia,\r\n"
						+ "		 Hinh";
				return new BienTheDAO().selectBySQL(filterBienThe, heDieuHanh);
			}
		}
		if (cboHeDieuHanh.getSelectedIndex() != -1 && cboGia.getSelectedIndex() != -1
				&& cboTrangThai.getSelectedIndex() != -1) {
			String heDieuHanh = (String) cboHeDieuHanh.getSelectedItem();
			if (cboGia.getSelectedItem() == "Dưới 10 triệu") {
				if (cboTrangThai.getSelectedItem() == "Active") {
					filterBienThe = "SELECT dbo.BienThe.ID, dbo.BienThe.MaBienThe, \r\n"
							+ "dbo.Laptop.MaLaptop,dbo.CPU.ID AS [ID_CPU], (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU) AS [CPU], \r\n"
							+ "dbo.RAM.ID AS [ID_RAM], (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB ' + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz') AS [RAM], \r\n"
							+ "dbo.ManHinh.ID AS [ID_ManHinh], (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - ' + dbo.ManHinh.doPhanGiai) AS [Màn hình], \r\n"
							+ "dbo.GPU.ID AS [ID_GPU], (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang) AS [GPU], \r\n"
							+ "dbo.OCung.ID AS [ID_OCung], (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB') AS [Ổ cứng], \r\n"
							+ "MauSac, \r\n"
							+ "dbo.HeDieuHanh.ID AS [ID_HeDieuHanh], (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)') AS [Hệ điều hành], \r\n"
							+ "Gia, \r\n"
							+ "Hinh, \r\n"
							+ "COUNT(SerialNumber) AS [Số lượng]  \r\n"
							+ "FROM dbo.BienThe JOIN dbo.Laptop ON Laptop.ID = BienThe.ID_Laptop LEFT JOIN dbo.Serial ON Serial.ID_BienThe = BienThe.ID JOIN dbo.CPU ON CPU.ID = BienThe.CPU JOIN dbo.RAM ON RAM.ID = BienThe.RAM JOIN dbo.ManHinh ON ManHinh.ID = BienThe.ManHinh JOIN dbo.GPU ON GPU.ID = BienThe.GPU JOIN dbo.OCung ON OCung.ID = BienThe.OCung JOIN dbo.HeDieuHanh ON HeDieuHanh.ID = BienThe.HeDieuHanh \r\n"
							+ "WHERE OS LIKE ? AND Gia < 10000000 AND TrangThai = 0 \r\n"
							+ "GROUP BY (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU),\r\n"
							+ "         (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB '\r\n"
							+ "         + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz'\r\n"
							+ "         ),\r\n"
							+ "         (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - '\r\n"
							+ "         + dbo.ManHinh.doPhanGiai\r\n"
							+ "         ),\r\n"
							+ "         (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang),\r\n"
							+ "         (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB'),\r\n"
							+ "         (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)'),\r\n"
							+ "			dbo.BienThe.ID,\r\n"
							+ "         MaBienThe,\r\n"
							+ "         MaLaptop,\r\n"
							+ "         CPU.ID,\r\n"
							+ "         RAM.ID,\r\n"
							+ "         ManHinh.ID,\r\n"
							+ "         GPU.ID,\r\n"
							+ "         OCung.ID,\r\n"
							+ "         MauSac,\r\n"
							+ "         HeDieuHanh.ID,\r\n"
							+ "		 Gia,\r\n"
							+ "		 Hinh";
					return new BienTheDAO().selectBySQL(filterBienThe, heDieuHanh);
				}
				if (cboTrangThai.getSelectedItem() == "InActive") {
					filterBienThe = "SELECT dbo.BienThe.ID, dbo.BienThe.MaBienThe, \r\n"
							+ "dbo.Laptop.MaLaptop,dbo.CPU.ID AS [ID_CPU], (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU) AS [CPU], \r\n"
							+ "dbo.RAM.ID AS [ID_RAM], (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB ' + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz') AS [RAM], \r\n"
							+ "dbo.ManHinh.ID AS [ID_ManHinh], (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - ' + dbo.ManHinh.doPhanGiai) AS [Màn hình], \r\n"
							+ "dbo.GPU.ID AS [ID_GPU], (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang) AS [GPU], \r\n"
							+ "dbo.OCung.ID AS [ID_OCung], (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB') AS [Ổ cứng], \r\n"
							+ "MauSac, \r\n"
							+ "dbo.HeDieuHanh.ID AS [ID_HeDieuHanh], (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)') AS [Hệ điều hành], \r\n"
							+ "Gia, \r\n"
							+ "Hinh, \r\n"
							+ "COUNT(SerialNumber) AS [Số lượng]  \r\n"
							+ "FROM dbo.BienThe JOIN dbo.Laptop ON Laptop.ID = BienThe.ID_Laptop LEFT JOIN dbo.Serial ON Serial.ID_BienThe = BienThe.ID JOIN dbo.CPU ON CPU.ID = BienThe.CPU JOIN dbo.RAM ON RAM.ID = BienThe.RAM JOIN dbo.ManHinh ON ManHinh.ID = BienThe.ManHinh JOIN dbo.GPU ON GPU.ID = BienThe.GPU JOIN dbo.OCung ON OCung.ID = BienThe.OCung JOIN dbo.HeDieuHanh ON HeDieuHanh.ID = BienThe.HeDieuHanh \r\n"
							+ "WHERE OS LIKE ? AND Gia < 10000000 AND TrangThai = 1 \r\n"
							+ "GROUP BY (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU),\r\n"
							+ "         (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB '\r\n"
							+ "         + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz'\r\n"
							+ "         ),\r\n"
							+ "         (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - '\r\n"
							+ "         + dbo.ManHinh.doPhanGiai\r\n"
							+ "         ),\r\n"
							+ "         (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang),\r\n"
							+ "         (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB'),\r\n"
							+ "         (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)'),\r\n"
							+ "			dbo.BienThe.ID,\r\n"
							+ "         MaBienThe,\r\n"
							+ "         MaLaptop,\r\n"
							+ "         CPU.ID,\r\n"
							+ "         RAM.ID,\r\n"
							+ "         ManHinh.ID,\r\n"
							+ "         GPU.ID,\r\n"
							+ "         OCung.ID,\r\n"
							+ "         MauSac,\r\n"
							+ "         HeDieuHanh.ID,\r\n"
							+ "		 Gia,\r\n"
							+ "		 Hinh";
					return new BienTheDAO().selectBySQL(filterBienThe, heDieuHanh);
				}
			}
			if (cboGia.getSelectedItem() == "Từ 10 - 15 triệu") {
				if (cboTrangThai.getSelectedItem() == "Active") {
					filterBienThe = "SELECT dbo.BienThe.ID, dbo.BienThe.MaBienThe, \r\n"
							+ "dbo.Laptop.MaLaptop,dbo.CPU.ID AS [ID_CPU], (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU) AS [CPU], \r\n"
							+ "dbo.RAM.ID AS [ID_RAM], (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB ' + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz') AS [RAM], \r\n"
							+ "dbo.ManHinh.ID AS [ID_ManHinh], (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - ' + dbo.ManHinh.doPhanGiai) AS [Màn hình], \r\n"
							+ "dbo.GPU.ID AS [ID_GPU], (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang) AS [GPU], \r\n"
							+ "dbo.OCung.ID AS [ID_OCung], (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB') AS [Ổ cứng], \r\n"
							+ "MauSac, \r\n"
							+ "dbo.HeDieuHanh.ID AS [ID_HeDieuHanh], (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)') AS [Hệ điều hành], \r\n"
							+ "Gia, \r\n"
							+ "Hinh, \r\n"
							+ "COUNT(SerialNumber) AS [Số lượng]  \r\n"
							+ "FROM dbo.BienThe JOIN dbo.Laptop ON Laptop.ID = BienThe.ID_Laptop LEFT JOIN dbo.Serial ON Serial.ID_BienThe = BienThe.ID JOIN dbo.CPU ON CPU.ID = BienThe.CPU JOIN dbo.RAM ON RAM.ID = BienThe.RAM JOIN dbo.ManHinh ON ManHinh.ID = BienThe.ManHinh JOIN dbo.GPU ON GPU.ID = BienThe.GPU JOIN dbo.OCung ON OCung.ID = BienThe.OCung JOIN dbo.HeDieuHanh ON HeDieuHanh.ID = BienThe.HeDieuHanh \r\n"
							+ "WHERE OS LIKE ? AND Gia >= 10000000 AND Gia < 15000000 AND TrangThai = 0 \r\n"
							+ "GROUP BY (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU),\r\n"
							+ "         (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB '\r\n"
							+ "         + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz'\r\n"
							+ "         ),\r\n"
							+ "         (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - '\r\n"
							+ "         + dbo.ManHinh.doPhanGiai\r\n"
							+ "         ),\r\n"
							+ "         (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang),\r\n"
							+ "         (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB'),\r\n"
							+ "         (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)'),\r\n"
							+ "			dbo.BienThe.ID,\r\n"
							+ "         MaBienThe,\r\n"
							+ "         MaLaptop,\r\n"
							+ "         CPU.ID,\r\n"
							+ "         RAM.ID,\r\n"
							+ "         ManHinh.ID,\r\n"
							+ "         GPU.ID,\r\n"
							+ "         OCung.ID,\r\n"
							+ "         MauSac,\r\n"
							+ "         HeDieuHanh.ID,\r\n"
							+ "		 Gia,\r\n"
							+ "		 Hinh";
					return new BienTheDAO().selectBySQL(filterBienThe, heDieuHanh);
				}
				if (cboTrangThai.getSelectedItem() == "InActive") {
					filterBienThe = "SELECT dbo.BienThe.ID, dbo.BienThe.MaBienThe, \r\n"
							+ "dbo.Laptop.MaLaptop,dbo.CPU.ID AS [ID_CPU], (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU) AS [CPU], \r\n"
							+ "dbo.RAM.ID AS [ID_RAM], (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB ' + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz') AS [RAM], \r\n"
							+ "dbo.ManHinh.ID AS [ID_ManHinh], (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - ' + dbo.ManHinh.doPhanGiai) AS [Màn hình], \r\n"
							+ "dbo.GPU.ID AS [ID_GPU], (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang) AS [GPU], \r\n"
							+ "dbo.OCung.ID AS [ID_OCung], (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB') AS [Ổ cứng], \r\n"
							+ "MauSac, \r\n"
							+ "dbo.HeDieuHanh.ID AS [ID_HeDieuHanh], (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)') AS [Hệ điều hành], \r\n"
							+ "Gia, \r\n"
							+ "Hinh, \r\n"
							+ "COUNT(SerialNumber) AS [Số lượng]  \r\n"
							+ "FROM dbo.BienThe JOIN dbo.Laptop ON Laptop.ID = BienThe.ID_Laptop LEFT JOIN dbo.Serial ON Serial.ID_BienThe = BienThe.ID JOIN dbo.CPU ON CPU.ID = BienThe.CPU JOIN dbo.RAM ON RAM.ID = BienThe.RAM JOIN dbo.ManHinh ON ManHinh.ID = BienThe.ManHinh JOIN dbo.GPU ON GPU.ID = BienThe.GPU JOIN dbo.OCung ON OCung.ID = BienThe.OCung JOIN dbo.HeDieuHanh ON HeDieuHanh.ID = BienThe.HeDieuHanh \r\n"
							+ "WHERE OS LIKE ? AND Gia >= 10000000 AND Gia < 15000000 AND TrangThai = 1 \r\n"
							+ "GROUP BY (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU),\r\n"
							+ "         (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB '\r\n"
							+ "         + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz'\r\n"
							+ "         ),\r\n"
							+ "         (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - '\r\n"
							+ "         + dbo.ManHinh.doPhanGiai\r\n"
							+ "         ),\r\n"
							+ "         (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang),\r\n"
							+ "         (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB'),\r\n"
							+ "         (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)'),\r\n"
							+ "			dbo.BienThe.ID,\r\n"
							+ "         MaBienThe,\r\n"
							+ "         MaLaptop,\r\n"
							+ "         CPU.ID,\r\n"
							+ "         RAM.ID,\r\n"
							+ "         ManHinh.ID,\r\n"
							+ "         GPU.ID,\r\n"
							+ "         OCung.ID,\r\n"
							+ "         MauSac,\r\n"
							+ "         HeDieuHanh.ID,\r\n"
							+ "		 Gia,\r\n"
							+ "		 Hinh";
					return new BienTheDAO().selectBySQL(filterBienThe, heDieuHanh);
				}
			}
			if (cboGia.getSelectedItem() == "Từ 15 - 20 triệu") {
				if (cboTrangThai.getSelectedItem() == "Active") {
					filterBienThe = "SELECT dbo.BienThe.ID, dbo.BienThe.MaBienThe, \r\n"
							+ "dbo.Laptop.MaLaptop,dbo.CPU.ID AS [ID_CPU], (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU) AS [CPU], \r\n"
							+ "dbo.RAM.ID AS [ID_RAM], (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB ' + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz') AS [RAM], \r\n"
							+ "dbo.ManHinh.ID AS [ID_ManHinh], (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - ' + dbo.ManHinh.doPhanGiai) AS [Màn hình], \r\n"
							+ "dbo.GPU.ID AS [ID_GPU], (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang) AS [GPU], \r\n"
							+ "dbo.OCung.ID AS [ID_OCung], (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB') AS [Ổ cứng], \r\n"
							+ "MauSac, \r\n"
							+ "dbo.HeDieuHanh.ID AS [ID_HeDieuHanh], (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)') AS [Hệ điều hành], \r\n"
							+ "Gia, \r\n"
							+ "Hinh, \r\n"
							+ "COUNT(SerialNumber) AS [Số lượng]  \r\n"
							+ "FROM dbo.BienThe JOIN dbo.Laptop ON Laptop.ID = BienThe.ID_Laptop LEFT JOIN dbo.Serial ON Serial.ID_BienThe = BienThe.ID JOIN dbo.CPU ON CPU.ID = BienThe.CPU JOIN dbo.RAM ON RAM.ID = BienThe.RAM JOIN dbo.ManHinh ON ManHinh.ID = BienThe.ManHinh JOIN dbo.GPU ON GPU.ID = BienThe.GPU JOIN dbo.OCung ON OCung.ID = BienThe.OCung JOIN dbo.HeDieuHanh ON HeDieuHanh.ID = BienThe.HeDieuHanh \r\n"
							+ "WHERE OS LIKE ? AND Gia >= 15000000 AND Gia < 20000000 AND TrangThai = 0 \r\n"
							+ "GROUP BY (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU),\r\n"
							+ "         (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB '\r\n"
							+ "         + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz'\r\n"
							+ "         ),\r\n"
							+ "         (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - '\r\n"
							+ "         + dbo.ManHinh.doPhanGiai\r\n"
							+ "         ),\r\n"
							+ "         (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang),\r\n"
							+ "         (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB'),\r\n"
							+ "         (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)'),\r\n"
							+ "			dbo.BienThe.ID,\r\n"
							+ "         MaBienThe,\r\n"
							+ "         MaLaptop,\r\n"
							+ "         CPU.ID,\r\n"
							+ "         RAM.ID,\r\n"
							+ "         ManHinh.ID,\r\n"
							+ "         GPU.ID,\r\n"
							+ "         OCung.ID,\r\n"
							+ "         MauSac,\r\n"
							+ "         HeDieuHanh.ID,\r\n"
							+ "		 Gia,\r\n"
							+ "		 Hinh";
					return new BienTheDAO().selectBySQL(filterBienThe, heDieuHanh);
				}
				if (cboTrangThai.getSelectedItem() == "InActive") {
					filterBienThe = "SELECT dbo.BienThe.ID, dbo.BienThe.MaBienThe, \r\n"
							+ "dbo.Laptop.MaLaptop,dbo.CPU.ID AS [ID_CPU], (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU) AS [CPU], \r\n"
							+ "dbo.RAM.ID AS [ID_RAM], (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB ' + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz') AS [RAM], \r\n"
							+ "dbo.ManHinh.ID AS [ID_ManHinh], (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - ' + dbo.ManHinh.doPhanGiai) AS [Màn hình], \r\n"
							+ "dbo.GPU.ID AS [ID_GPU], (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang) AS [GPU], \r\n"
							+ "dbo.OCung.ID AS [ID_OCung], (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB') AS [Ổ cứng], \r\n"
							+ "MauSac, \r\n"
							+ "dbo.HeDieuHanh.ID AS [ID_HeDieuHanh], (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)') AS [Hệ điều hành], \r\n"
							+ "Gia, \r\n"
							+ "Hinh, \r\n"
							+ "COUNT(SerialNumber) AS [Số lượng]  \r\n"
							+ "FROM dbo.BienThe JOIN dbo.Laptop ON Laptop.ID = BienThe.ID_Laptop LEFT JOIN dbo.Serial ON Serial.ID_BienThe = BienThe.ID JOIN dbo.CPU ON CPU.ID = BienThe.CPU JOIN dbo.RAM ON RAM.ID = BienThe.RAM JOIN dbo.ManHinh ON ManHinh.ID = BienThe.ManHinh JOIN dbo.GPU ON GPU.ID = BienThe.GPU JOIN dbo.OCung ON OCung.ID = BienThe.OCung JOIN dbo.HeDieuHanh ON HeDieuHanh.ID = BienThe.HeDieuHanh \r\n"
							+ "WHERE OS LIKE ? AND Gia >= 15000000 AND Gia < 20000000 AND TrangThai = 1 \r\n"
							+ "GROUP BY (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU),\r\n"
							+ "         (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB '\r\n"
							+ "         + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz'\r\n"
							+ "         ),\r\n"
							+ "         (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - '\r\n"
							+ "         + dbo.ManHinh.doPhanGiai\r\n"
							+ "         ),\r\n"
							+ "         (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang),\r\n"
							+ "         (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB'),\r\n"
							+ "         (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)'),\r\n"
							+ "			dbo.BienThe.ID,\r\n"
							+ "         MaBienThe,\r\n"
							+ "         MaLaptop,\r\n"
							+ "         CPU.ID,\r\n"
							+ "         RAM.ID,\r\n"
							+ "         ManHinh.ID,\r\n"
							+ "         GPU.ID,\r\n"
							+ "         OCung.ID,\r\n"
							+ "         MauSac,\r\n"
							+ "         HeDieuHanh.ID,\r\n"
							+ "		 Gia,\r\n"
							+ "		 Hinh";
					return new BienTheDAO().selectBySQL(filterBienThe, heDieuHanh);
				}
			}
			if (cboGia.getSelectedItem() == "Từ 20 - 30 triệu") {
				if (cboTrangThai.getSelectedItem() == "Active") {
					filterBienThe = "SELECT dbo.BienThe.ID, dbo.BienThe.MaBienThe, \r\n"
							+ "dbo.Laptop.MaLaptop,dbo.CPU.ID AS [ID_CPU], (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU) AS [CPU], \r\n"
							+ "dbo.RAM.ID AS [ID_RAM], (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB ' + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz') AS [RAM], \r\n"
							+ "dbo.ManHinh.ID AS [ID_ManHinh], (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - ' + dbo.ManHinh.doPhanGiai) AS [Màn hình], \r\n"
							+ "dbo.GPU.ID AS [ID_GPU], (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang) AS [GPU], \r\n"
							+ "dbo.OCung.ID AS [ID_OCung], (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB') AS [Ổ cứng], \r\n"
							+ "MauSac, \r\n"
							+ "dbo.HeDieuHanh.ID AS [ID_HeDieuHanh], (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)') AS [Hệ điều hành], \r\n"
							+ "Gia, \r\n"
							+ "Hinh, \r\n"
							+ "COUNT(SerialNumber) AS [Số lượng]  \r\n"
							+ "FROM dbo.BienThe JOIN dbo.Laptop ON Laptop.ID = BienThe.ID_Laptop LEFT JOIN dbo.Serial ON Serial.ID_BienThe = BienThe.ID JOIN dbo.CPU ON CPU.ID = BienThe.CPU JOIN dbo.RAM ON RAM.ID = BienThe.RAM JOIN dbo.ManHinh ON ManHinh.ID = BienThe.ManHinh JOIN dbo.GPU ON GPU.ID = BienThe.GPU JOIN dbo.OCung ON OCung.ID = BienThe.OCung JOIN dbo.HeDieuHanh ON HeDieuHanh.ID = BienThe.HeDieuHanh \r\n"
							+ "WHERE OS LIKE ? AND Gia >= 20000000 AND Gia < 30000000 AND TrangThai = 0 \r\n"
							+ "GROUP BY (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU),\r\n"
							+ "         (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB '\r\n"
							+ "         + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz'\r\n"
							+ "         ),\r\n"
							+ "         (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - '\r\n"
							+ "         + dbo.ManHinh.doPhanGiai\r\n"
							+ "         ),\r\n"
							+ "         (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang),\r\n"
							+ "         (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB'),\r\n"
							+ "         (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)'),\r\n"
							+ "			dbo.BienThe.ID,\r\n"
							+ "         MaBienThe,\r\n"
							+ "         MaLaptop,\r\n"
							+ "         CPU.ID,\r\n"
							+ "         RAM.ID,\r\n"
							+ "         ManHinh.ID,\r\n"
							+ "         GPU.ID,\r\n"
							+ "         OCung.ID,\r\n"
							+ "         MauSac,\r\n"
							+ "         HeDieuHanh.ID,\r\n"
							+ "		 Gia,\r\n"
							+ "		 Hinh";
					return new BienTheDAO().selectBySQL(filterBienThe, heDieuHanh);
				}
				if (cboTrangThai.getSelectedItem() == "InActive") {
					filterBienThe = "SELECT dbo.BienThe.ID, dbo.BienThe.MaBienThe, \r\n"
							+ "dbo.Laptop.MaLaptop,dbo.CPU.ID AS [ID_CPU], (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU) AS [CPU], \r\n"
							+ "dbo.RAM.ID AS [ID_RAM], (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB ' + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz') AS [RAM], \r\n"
							+ "dbo.ManHinh.ID AS [ID_ManHinh], (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - ' + dbo.ManHinh.doPhanGiai) AS [Màn hình], \r\n"
							+ "dbo.GPU.ID AS [ID_GPU], (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang) AS [GPU], \r\n"
							+ "dbo.OCung.ID AS [ID_OCung], (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB') AS [Ổ cứng], \r\n"
							+ "MauSac, \r\n"
							+ "dbo.HeDieuHanh.ID AS [ID_HeDieuHanh], (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)') AS [Hệ điều hành], \r\n"
							+ "Gia, \r\n"
							+ "Hinh, \r\n"
							+ "COUNT(SerialNumber) AS [Số lượng]  \r\n"
							+ "FROM dbo.BienThe JOIN dbo.Laptop ON Laptop.ID = BienThe.ID_Laptop LEFT JOIN dbo.Serial ON Serial.ID_BienThe = BienThe.ID JOIN dbo.CPU ON CPU.ID = BienThe.CPU JOIN dbo.RAM ON RAM.ID = BienThe.RAM JOIN dbo.ManHinh ON ManHinh.ID = BienThe.ManHinh JOIN dbo.GPU ON GPU.ID = BienThe.GPU JOIN dbo.OCung ON OCung.ID = BienThe.OCung JOIN dbo.HeDieuHanh ON HeDieuHanh.ID = BienThe.HeDieuHanh \r\n"
							+ "WHERE OS LIKE ? AND Gia >= 20000000 AND Gia < 30000000 AND TrangThai = 1 \r\n"
							+ "GROUP BY (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU),\r\n"
							+ "         (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB '\r\n"
							+ "         + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz'\r\n"
							+ "         ),\r\n"
							+ "         (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - '\r\n"
							+ "         + dbo.ManHinh.doPhanGiai\r\n"
							+ "         ),\r\n"
							+ "         (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang),\r\n"
							+ "         (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB'),\r\n"
							+ "         (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)'),\r\n"
							+ "			dbo.BienThe.ID,\r\n"
							+ "         MaBienThe,\r\n"
							+ "         MaLaptop,\r\n"
							+ "         CPU.ID,\r\n"
							+ "         RAM.ID,\r\n"
							+ "         ManHinh.ID,\r\n"
							+ "         GPU.ID,\r\n"
							+ "         OCung.ID,\r\n"
							+ "         MauSac,\r\n"
							+ "         HeDieuHanh.ID,\r\n"
							+ "		 Gia,\r\n"
							+ "		 Hinh";
					return new BienTheDAO().selectBySQL(filterBienThe, heDieuHanh);
				}
			}
			if (cboGia.getSelectedItem() == "Trên 30 triệu") {
				if (cboTrangThai.getSelectedItem() == "Active") {
					filterBienThe = "SELECT dbo.BienThe.ID, dbo.BienThe.MaBienThe, \r\n"
							+ "dbo.Laptop.MaLaptop,dbo.CPU.ID AS [ID_CPU], (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU) AS [CPU], \r\n"
							+ "dbo.RAM.ID AS [ID_RAM], (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB ' + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz') AS [RAM], \r\n"
							+ "dbo.ManHinh.ID AS [ID_ManHinh], (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - ' + dbo.ManHinh.doPhanGiai) AS [Màn hình], \r\n"
							+ "dbo.GPU.ID AS [ID_GPU], (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang) AS [GPU], \r\n"
							+ "dbo.OCung.ID AS [ID_OCung], (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB') AS [Ổ cứng], \r\n"
							+ "MauSac, \r\n"
							+ "dbo.HeDieuHanh.ID AS [ID_HeDieuHanh], (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)') AS [Hệ điều hành], \r\n"
							+ "Gia, \r\n"
							+ "Hinh, \r\n"
							+ "COUNT(SerialNumber) AS [Số lượng]  \r\n"
							+ "FROM dbo.BienThe JOIN dbo.Laptop ON Laptop.ID = BienThe.ID_Laptop LEFT JOIN dbo.Serial ON Serial.ID_BienThe = BienThe.ID JOIN dbo.CPU ON CPU.ID = BienThe.CPU JOIN dbo.RAM ON RAM.ID = BienThe.RAM JOIN dbo.ManHinh ON ManHinh.ID = BienThe.ManHinh JOIN dbo.GPU ON GPU.ID = BienThe.GPU JOIN dbo.OCung ON OCung.ID = BienThe.OCung JOIN dbo.HeDieuHanh ON HeDieuHanh.ID = BienThe.HeDieuHanh \r\n"
							+ "WHERE OS LIKE ? AND Gia >= 30000000 AND TrangThai = 0 \r\n"
							+ "GROUP BY (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU),\r\n"
							+ "         (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB '\r\n"
							+ "         + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz'\r\n"
							+ "         ),\r\n"
							+ "         (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - '\r\n"
							+ "         + dbo.ManHinh.doPhanGiai\r\n"
							+ "         ),\r\n"
							+ "         (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang),\r\n"
							+ "         (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB'),\r\n"
							+ "         (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)'),\r\n"
							+ "			dbo.BienThe.ID,\r\n"
							+ "         MaBienThe,\r\n"
							+ "         MaLaptop,\r\n"
							+ "         CPU.ID,\r\n"
							+ "         RAM.ID,\r\n"
							+ "         ManHinh.ID,\r\n"
							+ "         GPU.ID,\r\n"
							+ "         OCung.ID,\r\n"
							+ "         MauSac,\r\n"
							+ "         HeDieuHanh.ID,\r\n"
							+ "		 Gia,\r\n"
							+ "		 Hinh";
					return new BienTheDAO().selectBySQL(filterBienThe, heDieuHanh);
				}
				if (cboTrangThai.getSelectedItem() == "InActive") {
					filterBienThe = "SELECT dbo.BienThe.ID, dbo.BienThe.MaBienThe, \r\n"
							+ "dbo.Laptop.MaLaptop,dbo.CPU.ID AS [ID_CPU], (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU) AS [CPU], \r\n"
							+ "dbo.RAM.ID AS [ID_RAM], (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB ' + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz') AS [RAM], \r\n"
							+ "dbo.ManHinh.ID AS [ID_ManHinh], (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - ' + dbo.ManHinh.doPhanGiai) AS [Màn hình], \r\n"
							+ "dbo.GPU.ID AS [ID_GPU], (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang) AS [GPU], \r\n"
							+ "dbo.OCung.ID AS [ID_OCung], (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB') AS [Ổ cứng], \r\n"
							+ "MauSac, \r\n"
							+ "dbo.HeDieuHanh.ID AS [ID_HeDieuHanh], (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)') AS [Hệ điều hành], \r\n"
							+ "Gia, \r\n"
							+ "Hinh, \r\n"
							+ "COUNT(SerialNumber) AS [Số lượng]  \r\n"
							+ "FROM dbo.BienThe JOIN dbo.Laptop ON Laptop.ID = BienThe.ID_Laptop LEFT JOIN dbo.Serial ON Serial.ID_BienThe = BienThe.ID JOIN dbo.CPU ON CPU.ID = BienThe.CPU JOIN dbo.RAM ON RAM.ID = BienThe.RAM JOIN dbo.ManHinh ON ManHinh.ID = BienThe.ManHinh JOIN dbo.GPU ON GPU.ID = BienThe.GPU JOIN dbo.OCung ON OCung.ID = BienThe.OCung JOIN dbo.HeDieuHanh ON HeDieuHanh.ID = BienThe.HeDieuHanh \r\n"
							+ "WHERE OS LIKE ? AND Gia >= 30000000 AND TrangThai = 1 \r\n"
							+ "GROUP BY (dbo.CPU.hangCPU + ' ' + dbo.CPU.loaiCPU),\r\n"
							+ "         (dbo.RAM.loaiRAM + ' - ' + CAST(dbo.RAM.dungLuong AS VARCHAR(10)) + ' GB '\r\n"
							+ "         + CAST(dbo.RAM.tocDoRAM AS VARCHAR(10)) + ' MHz'\r\n"
							+ "         ),\r\n"
							+ "         (dbo.ManHinh.congNgheManHinh + ' - ' + CAST(dbo.ManHinh.kichThuocManHinh AS VARCHAR(10)) + ' - '\r\n"
							+ "         + dbo.ManHinh.doPhanGiai\r\n"
							+ "         ),\r\n"
							+ "         (dbo.GPU.loaiCard + ' - ' + dbo.GPU.hang),\r\n"
							+ "         (N'Ổ ' + dbo.OCung.kieuOCung + ' - ' + CAST(dbo.OCung.dungLuong AS VARCHAR(10)) + ' GB'),\r\n"
							+ "         (dbo.HeDieuHanh.os + ' ' + dbo.HeDieuHanh.versions + ' ' + CAST(dbo.HeDieuHanh.kieu AS VARCHAR(10)) + '(bit)'),\r\n"
							+ "			dbo.BienThe.ID,\r\n"
							+ "         MaBienThe,\r\n"
							+ "         MaLaptop,\r\n"
							+ "         CPU.ID,\r\n"
							+ "         RAM.ID,\r\n"
							+ "         ManHinh.ID,\r\n"
							+ "         GPU.ID,\r\n"
							+ "         OCung.ID,\r\n"
							+ "         MauSac,\r\n"
							+ "         HeDieuHanh.ID,\r\n"
							+ "		 Gia,\r\n"
							+ "		 Hinh";
					return new BienTheDAO().selectBySQL(filterBienThe, heDieuHanh);
				}
			}
		}
		return new BienTheDAO().selectBySQL(selectBienTheByLaptop, maLaptop);
	}
	
	public void setPage(List<Laptop> list) {
		to.setText("1");
		size = (double) list.size() / 8;
		if (size == 0) {
			RowCounts = 1;
		} else {
			RowCounts = (int) Math.ceil(size);
		}
		from.setText(RowCounts + "");
	}
	
	public NhaCungCap getFormNCC() {
		NhaCungCap nhaCungCap = new NhaCungCap();
		nhaCungCap.setMaNCC(txtMaNCC.getText());
		nhaCungCap.setTenNCC(txtTenNCC.getText());
		nhaCungCap.setNguoiPhuTrach(txtNguoiPhuTrach.getText());
		nhaCungCap.setSoDienThoai(txtSoDienThoai.getText());
		nhaCungCap.setEmail(txtEmail.getText());
		nhaCungCap.setDiaChi(txtDiaChi.getText());
		return nhaCungCap;
	}
	
	public void setFormNCC(NhaCungCap nhaCungCap) {
		txtMaNCC.setText(nhaCungCap.getMaNCC());
		txtTenNCC.setText(nhaCungCap.getTenNCC());
		txtNguoiPhuTrach.setText(nhaCungCap.getNguoiPhuTrach());
		txtSoDienThoai.setText(nhaCungCap.getSoDienThoai());
		txtEmail.setText(nhaCungCap.getEmail());
		txtDiaChi.setText(nhaCungCap.getDiaChi());
	}
	
	public void fillTableNCC(List<NhaCungCap> list) {
		modelNhaCungCap.setRowCount(0);
		try {
			for (NhaCungCap nhaCungCap : list) {
				Object[] rows = new Object[] {
					nhaCungCap.getMaNCC(),
					nhaCungCap.getTenNCC(),
					nhaCungCap.getNguoiPhuTrach(),
					nhaCungCap.getSoDienThoai(),
					nhaCungCap.getEmail(),
					nhaCungCap.getDiaChi()
				};
				modelNhaCungCap.addRow(rows);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public String generateAutoCode() {
        String uppercaseLetters = "DN";
        String numbers = "0123456789";
        
        Random random = new Random();
        StringBuilder codeBuilder = new StringBuilder();
        
        codeBuilder.append(uppercaseLetters);
        
        for (int i = 0; i < 8; i++) {
			int randomIndex = random.nextInt(numbers.length());
			codeBuilder.append(numbers.charAt(randomIndex));
		}
        
        return codeBuilder.toString();
    }
	
	public static String decimalFormat(BigDecimal number) {        
        DecimalFormat decimalFormat = new DecimalFormat("0.####################");
        String formattedNumber = decimalFormat.format(number);
        return formattedNumber;
	}
	
	public void fillTableBienThe_TonKho(List<BienThe> list) {
		modelBienThe_TonKho.setRowCount(0);
		try {
			for (BienThe bienThe : list) {
				Object[] rows = new Object[] { bienThe.getMaBienThe(), bienThe.getMaLaptop(), bienThe.getCpu(),
						bienThe.getRam(), bienThe.getManHinh(), bienThe.getGpu(), bienThe.getoCung(),
						bienThe.getMauSac(), bienThe.getHeDieuHanh(), decimalFormat(bienThe.getGia()), bienThe.getSoLuong() };
				modelBienThe_TonKho.addRow(rows);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void fillTableSerial(List<SerialNumber> list) {
		modelSerial.setRowCount(0);
		try {
			for (SerialNumber serialNumber : list) {
				Object[] rows = new Object[] { 
					serialNumber.getMaBienThe(),
					serialNumber.getSerialNumber()
				};
				modelSerial.addRow(rows);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void setLaptop(Laptop laptop) {
		lblMaLaptop.setText(laptop.getMaLaptop());
		lblTenLaptop.setText(laptop.getTenLaptop());
		lblPhanLoai.setText(laptop.getTenLoai());
		lblHang.setText(laptop.getTenHang());
		lblDongLaptop.setText(laptop.getTenDong());
		lblNamSanXuat.setText(laptop.getNamSanXuat()+"");
	}
	
	public void exportFile(List<BienThe> listBT_TonKho) {
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
            headerRow.createCell(0).setCellValue("Mã biến thể");
            headerRow.createCell(1).setCellValue("Mã Laptop");
            headerRow.createCell(2).setCellValue("CPU");
            headerRow.createCell(3).setCellValue("RAM");
            headerRow.createCell(4).setCellValue("Màn hình");
            headerRow.createCell(5).setCellValue("GPU");
            headerRow.createCell(6).setCellValue("Ổ cứng");
            headerRow.createCell(7).setCellValue("Màu sắc");
            headerRow.createCell(8).setCellValue("Hệ điều hành");
            headerRow.createCell(9).setCellValue("Giá");
            headerRow.createCell(10).setCellValue("Số lượng");

            for (int i = 0; i < listBT_TonKho.size(); i++) {
                BienThe bienThe = listBT_TonKho.get(i);
                Row row = sheet.createRow(i + 1);
                row.createCell(0).setCellValue(bienThe.getMaBienThe());
                row.createCell(1).setCellValue(bienThe.getMaLaptop());
                row.createCell(2).setCellValue(bienThe.getCpu());
                row.createCell(3).setCellValue(bienThe.getRam());
                row.createCell(4).setCellValue(bienThe.getManHinh());
                row.createCell(5).setCellValue(bienThe.getGpu());
                row.createCell(6).setCellValue(bienThe.getoCung());
                row.createCell(7).setCellValue(bienThe.getMauSac());
                row.createCell(8).setCellValue(bienThe.getHeDieuHanh());
                row.createCell(9).setCellValue(decimalFormat(bienThe.getGia()));
                row.createCell(10).setCellValue(bienThe.getSoLuong());
            }

            try (FileOutputStream outputStream = new FileOutputStream(fileToSave)) { 
                workbook.write(outputStream); 
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                workbook.close();
                MsgBox.alert(getContentPane(), "Export file success");
            } catch (IOException e) {
                e.printStackTrace();
            }
            
        }
	}
}
