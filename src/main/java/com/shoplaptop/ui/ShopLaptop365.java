package com.shoplaptop.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.shoplaptop.utils.Auth;
import com.shoplaptop.utils.XImage;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ShopLaptop365 extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					ShopLaptop365 frame = new ShopLaptop365();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ShopLaptop365() {
		setTitle("ShopLaptop365");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1070, 735);
		setLocationRelativeTo(null);
		setIconImage(XImage.getAppIcon());
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(0, 64, 128));
		setJMenuBar(menuBar);
		
		JMenu mnHeThong = new JMenu("Hệ thống");
		mnHeThong.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		menuBar.add(mnHeThong);
		
		JMenuItem mntmDangXuat = new JMenuItem("Đăng xuất");
		mntmDangXuat.setIcon(new ImageIcon(ShopLaptop365.class.getResource("/com/shoplaptop/icon/Log out.png")));
		mntmDangXuat.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		mnHeThong.add(mntmDangXuat);
		
		JMenuItem mntmDoiMatKhau = new JMenuItem("Đổi mật khẩu");
		mntmDoiMatKhau.setIcon(new ImageIcon(ShopLaptop365.class.getResource("/com/shoplaptop/icon/Refresh.png")));
		mntmDoiMatKhau.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		mnHeThong.add(mntmDoiMatKhau);
		
		JMenuItem mntmKetThuc = new JMenuItem("Kết thúc");
		mntmKetThuc.setIcon(new ImageIcon(ShopLaptop365.class.getResource("/com/shoplaptop/icon/Stop.png")));
		mntmKetThuc.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		mnHeThong.add(mntmKetThuc);
		
		JMenu mnQuanLy = new JMenu("Quản lý");
		mnQuanLy.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		menuBar.add(mnQuanLy);
		
		JMenuItem mntmKhachHang = new JMenuItem("Khách hàng");
		mntmKhachHang.setIcon(new ImageIcon(ShopLaptop365.class.getResource("/com/shoplaptop/icon/Users.png")));
		mntmKhachHang.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		mnQuanLy.add(mntmKhachHang);
		
		JMenuItem mntmDonHang = new JMenuItem("Đơn hàng");
		mntmDonHang.setIcon(new ImageIcon(ShopLaptop365.class.getResource("/com/shoplaptop/icon/Notes.png")));
		mntmDonHang.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		mnQuanLy.add(mntmDonHang);
		
		JMenuItem mntmHoaDon = new JMenuItem("Hóa đơn");
		mntmHoaDon.setIcon(new ImageIcon(ShopLaptop365.class.getResource("/com/shoplaptop/icon/Price list.png")));
		mntmHoaDon.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		mnQuanLy.add(mntmHoaDon);
		
		JMenuItem mntmLaptop = new JMenuItem("Laptop");
		mntmLaptop.setIcon(new ImageIcon(ShopLaptop365.class.getResource("/com/shoplaptop/icon/laptop.png")));
		mntmLaptop.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		mnQuanLy.add(mntmLaptop);
		
		JMenuItem mntmPhieuGiamGia = new JMenuItem("Phiếu giảm giá");
		mntmPhieuGiamGia.setIcon(new ImageIcon(ShopLaptop365.class.getResource("/com/shoplaptop/icon/Statistics.png")));
		mntmPhieuGiamGia.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		mnQuanLy.add(mntmPhieuGiamGia);
		
		JMenuItem mntmDotGiamGia = new JMenuItem("Đợt giảm giá");
		mntmDotGiamGia.setIcon(new ImageIcon(ShopLaptop365.class.getResource("/com/shoplaptop/icon/sale-icon.png")));
		mntmDotGiamGia.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		mnQuanLy.add(mntmDotGiamGia);
		
		JMenuItem mntmPhieuDoiTra = new JMenuItem("Phiếu đổi");
		mntmPhieuDoiTra.setIcon(new ImageIcon(ShopLaptop365.class.getResource("/com/shoplaptop/icon/phieudoi.png")));
		mntmPhieuDoiTra.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		mnQuanLy.add(mntmPhieuDoiTra);
		
		JMenuItem mntmNhanVien = new JMenuItem("Nhân viên");
		mntmNhanVien.setIcon(new ImageIcon(ShopLaptop365.class.getResource("/com/shoplaptop/icon/User.png")));
		mntmNhanVien.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		mnQuanLy.add(mntmNhanVien);
		
		JMenu mnNewMenu_2 = new JMenu("Thông tin cá nhân");
		mnNewMenu_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmXemThongTinCaNhan = new JMenuItem("Xem thông tin cá nhân");
		mntmXemThongTinCaNhan.setIcon(new ImageIcon(ShopLaptop365.class.getResource("/com/shoplaptop/icon/profile.png")));
		mntmXemThongTinCaNhan.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		mnNewMenu_2.add(mntmXemThongTinCaNhan);
		
		JMenuItem mntmCapNhatThongTinCaNhan = new JMenuItem("Cập nhật thông tin cá nhân");
		mntmCapNhatThongTinCaNhan.setIcon(new ImageIcon(ShopLaptop365.class.getResource("/com/shoplaptop/icon/Refresh.png")));
		mntmCapNhatThongTinCaNhan.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		mnNewMenu_2.add(mntmCapNhatThongTinCaNhan);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBackground(new Color(255, 255, 255));
		toolBar.setBounds(0, 10, 1056, 56);
		contentPane.add(toolBar);
		
		JButton btnDangXuat = new JButton("Đăng xuất");
		btnDangXuat.setForeground(new Color(0, 0, 0));
		btnDangXuat.setBackground(new Color(255, 255, 255));
		btnDangXuat.setHorizontalTextPosition(SwingConstants.CENTER);
		btnDangXuat.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnDangXuat.setIcon(new ImageIcon(ShopLaptop365.class.getResource("/com/shoplaptop/icon/Exit.png")));
		btnDangXuat.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		toolBar.add(btnDangXuat);
		
		JButton btnKetThuc = new JButton("Kết thúc");
		btnKetThuc.setForeground(new Color(0, 0, 0));
		btnKetThuc.setBackground(new Color(255, 255, 255));
		btnKetThuc.setIcon(new ImageIcon(ShopLaptop365.class.getResource("/com/shoplaptop/icon/Stop.png")));
		btnKetThuc.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnKetThuc.setHorizontalTextPosition(SwingConstants.CENTER);
		btnKetThuc.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		toolBar.add(btnKetThuc);
		
		JSeparator separator = new JSeparator();
		separator.setMaximumSize(new Dimension(1, 55));
		separator.setOrientation(SwingConstants.VERTICAL);
		toolBar.add(separator);
		
		JButton btnKhachHang = new JButton("Khách hàng");
		btnKhachHang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new QuanLyKhachHang().setVisible(true);
			}
		});
		btnKhachHang.setForeground(new Color(0, 0, 0));
		btnKhachHang.setBackground(new Color(255, 255, 255));
		btnKhachHang.setIcon(new ImageIcon(ShopLaptop365.class.getResource("/com/shoplaptop/icon/Users.png")));
		btnKhachHang.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnKhachHang.setHorizontalTextPosition(SwingConstants.CENTER);
		btnKhachHang.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		toolBar.add(btnKhachHang);
		
		JButton btnDonHang = new JButton("Đơn hàng");
		btnDonHang.setForeground(new Color(0, 0, 0));
		btnDonHang.setBackground(new Color(255, 255, 255));
		btnDonHang.setIcon(new ImageIcon(ShopLaptop365.class.getResource("/com/shoplaptop/icon/Notes.png")));
		btnDonHang.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnDonHang.setHorizontalTextPosition(SwingConstants.CENTER);
		btnDonHang.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		toolBar.add(btnDonHang);
		
		JButton btnHoaDon = new JButton("Hóa đơn");
		btnHoaDon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new HoaDonManager().setVisible(true);
			}
		});
		btnHoaDon.setForeground(new Color(0, 0, 0));
		btnHoaDon.setBackground(new Color(255, 255, 255));
		btnHoaDon.setIcon(new ImageIcon(ShopLaptop365.class.getResource("/com/shoplaptop/icon/Price list.png")));
		btnHoaDon.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnHoaDon.setHorizontalTextPosition(SwingConstants.CENTER);
		btnHoaDon.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		toolBar.add(btnHoaDon);
		
		JButton btnLaptop = new JButton("Laptop");
		btnLaptop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LaptopManager().setVisible(true);
			}
		});
		btnLaptop.setForeground(new Color(0, 0, 0));
		btnLaptop.setBackground(new Color(255, 255, 255));
		btnLaptop.setIcon(new ImageIcon(ShopLaptop365.class.getResource("/com/shoplaptop/icon/laptop.png")));
		btnLaptop.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnLaptop.setHorizontalTextPosition(SwingConstants.CENTER);
		btnLaptop.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		toolBar.add(btnLaptop);
		
		JButton btnPhieuGiamGia = new JButton("Phiếu giảm giá");
		btnPhieuGiamGia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PhieuGiamGiaUI().setVisible(true);
			}
		});
		btnPhieuGiamGia.setForeground(new Color(0, 0, 0));
		btnPhieuGiamGia.setBackground(new Color(255, 255, 255));
		btnPhieuGiamGia.setIcon(new ImageIcon(ShopLaptop365.class.getResource("/com/shoplaptop/icon/Statistics.png")));
		btnPhieuGiamGia.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnPhieuGiamGia.setHorizontalTextPosition(SwingConstants.CENTER);
		btnPhieuGiamGia.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		toolBar.add(btnPhieuGiamGia);
		
		JButton btnDotGiamGia = new JButton("Đợt giảm giá");
		btnDotGiamGia.setForeground(new Color(0, 0, 0));
		btnDotGiamGia.setBackground(new Color(255, 255, 255));
		btnDotGiamGia.setIcon(new ImageIcon(ShopLaptop365.class.getResource("/com/shoplaptop/icon/sale-icon.png")));
		btnDotGiamGia.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnDotGiamGia.setHorizontalTextPosition(SwingConstants.CENTER);
		btnDotGiamGia.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		toolBar.add(btnDotGiamGia);
		
		JButton btnPhieuDoi = new JButton("Phiếu đổi");
		btnPhieuDoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnPhieuDoi.setForeground(new Color(0, 0, 0));
		btnPhieuDoi.setBackground(new Color(255, 255, 255));
		btnPhieuDoi.setIcon(new ImageIcon(ShopLaptop365.class.getResource("/com/shoplaptop/icon/phieudoi.png")));
		btnPhieuDoi.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnPhieuDoi.setHorizontalTextPosition(SwingConstants.CENTER);
		btnPhieuDoi.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		toolBar.add(btnPhieuDoi);
		
		JButton btnNhanVien = new JButton("Nhân viên");
		btnNhanVien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new QLNhanVien().setVisible(true);
			}
		});
		btnNhanVien.setForeground(new Color(0, 0, 0));
		btnNhanVien.setBackground(new Color(255, 255, 255));
		btnNhanVien.setIcon(new ImageIcon(ShopLaptop365.class.getResource("/com/shoplaptop/icon/User.png")));
		btnNhanVien.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnNhanVien.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNhanVien.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		toolBar.add(btnNhanVien);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(ShopLaptop365.class.getResource("/com/shoplaptop/icon/Banner_PM.jpg")));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 76, 1036, 582);
		contentPane.add(lblNewLabel);
		
		new HelloWindows(this, true).setVisible(true);
		new Login(this, true).setVisible(true);
		if (!Auth.isManager()) {
			mntmNhanVien.setEnabled(false);
			btnNhanVien.setEnabled(false);
			
		}
		setResizable(false);
	}
}
