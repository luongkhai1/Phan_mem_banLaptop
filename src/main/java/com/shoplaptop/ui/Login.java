package com.shoplaptop.ui;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.shoplaptop.dao.TaiKhoanDAO;
import com.shoplaptop.entity.TaiKhoan;
import com.shoplaptop.utils.Auth;
import com.shoplaptop.utils.MsgBox;
import com.shoplaptop.utils.XImage;

import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class Login extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtTenDangNhap;
	private JPasswordField txtMatKhau;
	private TaiKhoanDAO dao = new TaiKhoanDAO();

	/**
	 * Create the dialog.
	 */

	public Login(ShopLaptop365 owner, boolean modal) {
		super(owner, modal);
		setTitle("ShopLaptop365 - Đăng nhập");
		setBounds(100, 100, 700, 390);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		contentPanel.setBounds(259, 0, 427, 275);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JLabel lblMaNV = new JLabel("Tên đăng nhập");
		lblMaNV.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMaNV.setBounds(20, 55, 165, 34);
		contentPanel.add(lblMaNV);
		
		JLabel lblMatKhau = new JLabel("Mật khẩu");
		lblMatKhau.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMatKhau.setBounds(20, 153, 165, 34);
		contentPanel.add(lblMatKhau);
		
		txtTenDangNhap = new JTextField();
		txtTenDangNhap.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtTenDangNhap.setBounds(20, 102, 334, 34);
		contentPanel.add(txtTenDangNhap);
		txtTenDangNhap.setColumns(10);
		
		txtMatKhau = new JPasswordField();
		txtMatKhau.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtMatKhau.setBounds(20, 197, 334, 34);
		contentPanel.add(txtMatKhau);
		setIconImage(XImage.getAppIcon());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(281, 274, 352, 79);
			getContentPane().add(buttonPane);
			buttonPane.setLayout(null);
			{
				JButton btnDangNhap = new JButton("Đăng nhập");
				btnDangNhap.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String tenDangNhap = txtTenDangNhap.getText();
						String password = new String(txtMatKhau.getPassword());
						TaiKhoan taiKhoan = dao.selectById(tenDangNhap);
						if (taiKhoan == null) {
							MsgBox.alert(getContentPane(),"Sai tên đăng nhập");
						}else {
							if (!password.equals(taiKhoan.getMatKhau())) {
								MsgBox.alert(getContentPane(),"Sai mật khẩu");
							}else {
								Auth.user = taiKhoan;
								dispose();
							}
						}
					}
				});
				btnDangNhap.setVerticalTextPosition(SwingConstants.BOTTOM);
				btnDangNhap.setHorizontalTextPosition(SwingConstants.CENTER);
				btnDangNhap.setFont(new Font("Times New Roman", Font.BOLD, 22));
				btnDangNhap.setBounds(27, 10, 139, 59);
				btnDangNhap.setActionCommand("OK");
				buttonPane.add(btnDangNhap);
				getRootPane().setDefaultButton(btnDangNhap);
			}
			{
				JButton btnKetThuc = new JButton("Kết thúc");
				btnKetThuc.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (MsgBox.confirm(getContentPane(), "Bạn muốn kết thúc ứng dụng?")) {
							System.exit(0);
						}
						
					}
				});
				btnKetThuc.setVerticalTextPosition(SwingConstants.BOTTOM);
				btnKetThuc.setHorizontalTextPosition(SwingConstants.CENTER);
				btnKetThuc.setFont(new Font("Times New Roman", Font.BOLD, 22));
				btnKetThuc.setBounds(188, 10, 139, 59);
				btnKetThuc.setActionCommand("Cancel");
				buttonPane.add(btnKetThuc);
			}
		}
		
		JLabel lblImage = new JLabel("");
		lblImage.setBackground(new Color(255, 255, 255));
		lblImage.setOpaque(true);
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		lblImage.setIcon(new ImageIcon(Login.class.getResource("/com/shoplaptop/icon/login2.jpg")));
		lblImage.setBounds(25, 29, 235, 298);
		getContentPane().add(lblImage);
		
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	
	}
}
