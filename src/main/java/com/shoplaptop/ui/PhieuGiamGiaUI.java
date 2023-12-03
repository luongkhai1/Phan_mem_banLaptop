package com.shoplaptop.ui;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import com.shoplaptop.dao.PhieuGiamGiaDAO;
import com.shoplaptop.entity.PhieuGiamGia;
import com.shoplaptop.utils.MsgBox;
import com.shoplaptop.utils.XDate;
import com.shoplaptop.utils.XImage;

import javax.swing.border.BevelBorder;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultComboBoxModel;

@SuppressWarnings("serial")
public class PhieuGiamGiaUI extends JDialog {
	private JTextField txtTimKiem;
	private JTextField txtMaPhieu;
	private JTextField txtTenPhieu;
	private JTextField txtHan;
	private JTextField txtSoLuong;
	private JTextField txtGiaGiam;
	private JTextField txtDieuKien;
	private DefaultTableModel tblModel;
	List<PhieuGiamGia> list = new ArrayList<>();
	PhieuGiamGia pgg = new PhieuGiamGia();
	PhieuGiamGiaDAO pggd = new PhieuGiamGiaDAO();
	private JLabel to;
	private JLabel from;
	private double size = -1;
	private int index = 1;
	private int Rowcount = -1;
	private JTable tblquanli;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					PhieuGiamGiaUI dialog = new PhieuGiamGiaUI();
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
	
	public PhieuGiamGiaUI() {
		setTitle("ShopLaptop365");
		setBounds(100, 100, 975, 675);
		getContentPane().setLayout(null);
		setLocationRelativeTo(this);
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 143, 574, 459);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		
		
		JButton btnPrev = new JButton("Trước ");
		btnPrev.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnPrev.setBounds(110, 402, 85, 32);
		panel.add(btnPrev);
		
		to = new JLabel("1");
		to.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		to.setBounds(233, 412, 11, 13);
		panel.add(to);
		
		JLabel lblNewLabel_2 = new JLabel("/");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(272, 412, 11, 13);
		panel.add(lblNewLabel_2);
		
		JButton btnNext = new JButton("Sau");
		btnNext.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnNext.setBounds(368, 402, 85, 32);
		panel.add(btnNext);
		
		from = new JLabel("-1");
		from.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		from.setBounds(310, 412, 23, 13);
		panel.add(from);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 554, 371);
		panel.add(scrollPane);
		
		
		tblModel = new DefaultTableModel();
		String [] col = new String[] {
			"ID","Mã Phiếu Giảm","Tên Phiếu Giảm","Hạn Sử Dụng","Số Lượng","Giá Giảm","Điều Kiện"	
		};
		tblModel.setColumnIdentifiers(col);
		
		tblquanli = new JTable(tblModel) ;
		tblquanli.setRowMargin(3);
		tblquanli.setRowHeight(25);
		tblquanli.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		tblquanli.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				   showindex(tblquanli.getSelectedRow());
			}
		});
		scrollPane.setViewportView(tblquanli);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(604, 143, 347, 459);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("Mã Phiếu Giảm");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_4.setBounds(10, 0, 133, 33);
		panel_1.add(lblNewLabel_4);
		
		txtMaPhieu = new JTextField();
		txtMaPhieu.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtMaPhieu.setBounds(10, 30, 184, 37);
		panel_1.add(txtMaPhieu);
		txtMaPhieu.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Tên Phiếu Giảm");
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_5.setBounds(10, 77, 121, 23);
		panel_1.add(lblNewLabel_5);
		
		txtTenPhieu = new JTextField();
		txtTenPhieu.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtTenPhieu.setBounds(10, 98, 184, 38);
		panel_1.add(txtTenPhieu);
		txtTenPhieu.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Hạn Sử Dụng");
		lblNewLabel_6.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_6.setBounds(10, 144, 104, 28);
		panel_1.add(lblNewLabel_6);
		
		txtHan = new JTextField();
		txtHan.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtHan.setBounds(10, 172, 184, 37);
		panel_1.add(txtHan);
		txtHan.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Số Lượng");
		lblNewLabel_7.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_7.setBounds(10, 228, 104, 20);
		panel_1.add(lblNewLabel_7);
		
		txtSoLuong = new JTextField();
		txtSoLuong.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtSoLuong.setBounds(10, 249, 184, 38);
		panel_1.add(txtSoLuong);
		txtSoLuong.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Giá Giảm");
		lblNewLabel_8.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_8.setBounds(10, 308, 104, 19);
		panel_1.add(lblNewLabel_8);
		
		txtGiaGiam = new JTextField();
		txtGiaGiam.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtGiaGiam.setBounds(10, 326, 184, 38);
		panel_1.add(txtGiaGiam);
		txtGiaGiam.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("Điều Kiện Giảm");
		lblNewLabel_9.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_9.setBounds(10, 384, 121, 28);
		panel_1.add(lblNewLabel_9);
		
		txtDieuKien = new JTextField();
		txtDieuKien.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtDieuKien.setBounds(10, 411, 184, 38);
		panel_1.add(txtDieuKien);
		txtDieuKien.setColumns(10);
		
		JButton btnThem = new JButton("Tạo ");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 if(MsgBox.confirm(getContentPane(), "Xác nhận thêm ?")){
			            if(check()) {
			                pggd.ADDAO(getModel());
			                fillTable(pggd.getALLDAO());
			                MsgBox.alert(getContentPane(), "Thêm thành công");
			            } else {
			                MsgBox.alert(getContentPane(), "Thêm Thất Bại");
			            }
			        }
			}
		});
		btnThem.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnThem.setIcon(new ImageIcon(PhieuGiamGiaUI.class.getResource("/com/shoplaptop/icon/add2.png")));
		btnThem.setBounds(204, 27, 133, 38);
		panel_1.add(btnThem);
		
		JButton btnSua = new JButton("Sửa");
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(MsgBox.confirm(getContentPane(), "Xác nhận cập nhật ?")){
		            
	                pggd.UPDATEDAO(getModel());
	                fillTable(pggd.getALLDAO());
	                MsgBox.alert(getContentPane(), "Cập nhật thành công");
	            
	        } else {
	            MsgBox.alert(getContentPane(), "Cập nhật thất bại");
	        }
			}
		});
		btnSua.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnSua.setIcon(new ImageIcon(PhieuGiamGiaUI.class.getResource("/com/shoplaptop/icon/update2.png")));
		btnSua.setBounds(204, 98, 133, 38);
		panel_1.add(btnSua);
		
		JButton btnXoa = new JButton("Xóa");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			           if(MsgBox.confirm(getContentPane(), "Xác nhận xóa ?")){
			               pggd.XOADAO(txtMaPhieu.getText());
			               fillTable(pggd.getALLDAO());
			               reset();
			               MsgBox.alert(getContentPane(), "Xóa thành công");
			           }
			        
			}
		});
		btnXoa.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnXoa.setIcon(new ImageIcon(PhieuGiamGiaUI.class.getResource("/com/shoplaptop/icon/delete2.png")));
		btnXoa.setBounds(204, 169, 133, 38);
		panel_1.add(btnXoa);
		
		JButton btnMoi = new JButton("Mới");
		btnMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		btnMoi.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnMoi.setIcon(new ImageIcon(PhieuGiamGiaUI.class.getResource("/com/shoplaptop/icon/reset.png")));
		btnMoi.setBounds(204, 246, 133, 38);
		panel_1.add(btnMoi);
		
		JButton btnThoat = new JButton("Thoát");
		btnThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnThoat.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnThoat.setIcon(new ImageIcon(PhieuGiamGiaUI.class.getResource("/com/shoplaptop/icon/exit2.png")));
		btnThoat.setBounds(204, 323, 133, 38);
		panel_1.add(btnThoat);
		
		JLabel lblNewLabel = new JLabel("Tìm Kiếm");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel.setBounds(10, 101, 100, 23);
		getContentPane().add(lblNewLabel);
		
		txtTimKiem = new JTextField();
		txtTimKiem.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtTimKiem.setBounds(120, 101, 299, 32);
		getContentPane().add(txtTimKiem);
		txtTimKiem.setColumns(10);
		
		JButton btnTimKiem = new JButton("Tìm Kiếm");
		btnTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(findByMaPG(txtTimKiem.getText())==null){
		            MsgBox.alert(getContentPane(), "Không tìm thấy phiếu giảm giá");
		        } else {
		            list.add(findByMaPG(txtTimKiem.getText()));
		            tblModel.setRowCount(0);
		            Object [] row = new Object[]{
		                findByMaPG(txtTimKiem.getText()).getID(),
		                findByMaPG(txtTimKiem.getText()).getMaPG(),
		                findByMaPG(txtTimKiem.getText()).getTenPhieu(),
		                findByMaPG(txtTimKiem.getText()).getHan(),
		                findByMaPG(txtTimKiem.getText()).getSoLuong(),
		                findByMaPG(txtTimKiem.getText()).getGiaGiam(),
		                findByMaPG(txtTimKiem.getText()).getDieuKienGiam()
		            };
		            tblModel.addRow(row);
		            MsgBox.alert(getContentPane(), "Tìm thấy kết quả");
		        }
			}
		});
		btnTimKiem.setIcon(new ImageIcon(PhieuGiamGiaUI.class.getResource("/com/shoplaptop/icon/search2.png")));
		btnTimKiem.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnTimKiem.setBounds(429, 96, 155, 37);
		getContentPane().add(btnTimKiem);
		
		JLabel lblNewLabel_1 = new JLabel("Lọc");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(614, 100, 45, 25);
		getContentPane().add(lblNewLabel_1);
		
		JComboBox<String> cboLoc = new JComboBox<String>();
		cboLoc.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		cboLoc.setModel(new DefaultComboBoxModel<String>(new String[] {"Phiếu Còn Hạn", "Phiếu Hết Hạn"}));
		cboLoc.setBounds(669, 104, 282, 21);
		getContentPane().add(cboLoc);
		setPage(pggd.getALLDAO());
		fillTable((ArrayList<PhieuGiamGia>) pggd.selectAllPhieu(index) );
		setIconImage(XImage.getAppIcon());
		
		
		btnPrev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (index > 1) {
					--index;
					to.setText(index+"");
					fillTable((ArrayList<PhieuGiamGia>) pggd.selectAllPhieu((index - 1)*5 + 1));
				}
        	
			}
		});

		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (index < Rowcount) {
					++index;
					to.setText(index+"");
					fillTable((ArrayList<PhieuGiamGia>) pggd.selectAllPhieu((index - 1)*5 + 1));
				}
			}
		});
		
		cboLoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 ArrayList<PhieuGiamGia> list = new ArrayList<>();
			        if(cboLoc.getSelectedItem()== "Phiếu Còn Hạn"){
			            list = pggd.getALLDAOLOCCONHAN();
			            setPage(list);
			            fillTable((ArrayList<PhieuGiamGia>)pggd.selectPhieuConHan(index));
			            
			        } else {
			            if(cboLoc.getSelectedItem()=="Phiếu Hết Hạn"){
			                list = pggd.getALLDAOLOCHETHAN();
			                setPage(list);
			                fillTable((ArrayList<PhieuGiamGia>)pggd.selectPhieuHetHan(index));
			            }
			        }
			}
		});
	}
	 public void setPage(List<PhieuGiamGia> list) {
			to.setText("1");
			size = (double) list.size() / 5;
			if (size == 0) {
				Rowcount = 1;
			} else {
				Rowcount = (int) Math.ceil(size);
			}
			from.setText(Rowcount + "");
		}
	public void fillTable(ArrayList<PhieuGiamGia> list){
        tblModel.setRowCount(0);
        for(PhieuGiamGia pgg : list){
            Object [] row = new Object[]{
                pgg.getID(),
                pgg.getMaPG(),
                pgg.getTenPhieu(),
                XDate.toString(pgg.getHan(), "yyyy-MM-dd HH:mm:ss"),
                pgg.getSoLuong(),
                decimalFormat(pgg.getGiaGiam().multiply(BigDecimal.valueOf(100))) + " %",
                decimalFormat(pgg.getDieuKienGiam())
            };
            tblModel.addRow(row);
        }
    }
	public static String decimalFormat(BigDecimal number) {        
        DecimalFormat decimalFormat = new DecimalFormat("0.####################");
        String formattedNumber = decimalFormat.format(number);
        return formattedNumber;
	}
	public void showindex(int chon){
        txtMaPhieu.setText(tblquanli.getValueAt(chon, 1).toString());
        txtTenPhieu.setText(tblquanli.getValueAt(chon, 2).toString());
        txtHan.setText(tblquanli.getValueAt(chon, 3).toString());
        txtSoLuong.setText(tblquanli.getValueAt(chon, 4).toString());
        txtGiaGiam.setText(tblquanli.getValueAt(chon, 5).toString());
        txtDieuKien.setText(tblquanli.getValueAt(chon, 6).toString());
    }
     public PhieuGiamGia getModel(){
        PhieuGiamGia pgg = new PhieuGiamGia();
        pgg.setMaPG(txtMaPhieu.getText());
        pgg.setTenPhieu(txtTenPhieu.getText());
        pgg.setHan(XDate.toDate(txtHan.getText(), "yyyy-MM-dd HH:mm:ss"));
        pgg.setSoLuong(Integer.parseInt(txtSoLuong.getText()));
        pgg.setGiaGiam(BigDecimal.valueOf(Double.parseDouble(getGiaGiam(txtGiaGiam.getText()))).divide(BigDecimal.valueOf(100)));
        pgg.setDieuKienGiam(BigDecimal.valueOf(Double.parseDouble(txtDieuKien.getText())));
        return pgg;
    }
     public String getGiaGiam(String GiaGiam) {
    	 
         String[] parts = GiaGiam.split("\\D", 2);
         String firstPart = parts[0];
         return firstPart;
	}
     public PhieuGiamGia findByMaPG(String MaPG){
         list = pggd.getALLDAO();
         for(PhieuGiamGia pgg : list){
             if(MaPG.equalsIgnoreCase(pgg.getMaPG())){
                 return pgg;
             }
         }
         return null;
     }
     public boolean check(){
         String MaPG = txtMaPhieu.getText();
         String TenPhieu = txtTenPhieu.getText();
         String Han = txtHan.getText();
         String SoLuong = txtSoLuong.getText();
         String GiaGiam = txtGiaGiam.getText();
         String dieuKien = txtDieuKien.getText();
         if(MaPG.trim().isEmpty()){
             MsgBox.alert(this, "Không được bỏ trống mã phiếu giảm giá");
             return false;
         }  
         if(TenPhieu.trim().isEmpty()){
             MsgBox.alert(this, "Không được bỏ trống tên phiếu giảm giá");
             return false;
         }  
         if(Han.trim().isEmpty()){
             MsgBox.alert(this, "Không được bỏ trống hạn sử dụng");
             return false;
         }  
         if(SoLuong.trim().isEmpty()){
             MsgBox.alert(this, "Không được bỏ trống số lượng");
             return false;
         }  
         if(GiaGiam.trim().isEmpty()){
             MsgBox.alert(this, "Không được bỏ trống giá giảm");
             return false;
         }
         if(dieuKien.trim().isEmpty()) {
         	MsgBox.alert(this, "Không được bỏ trống điều kiện giảm");
         	return false;
         }
         return true;
     }
      public void reset(){
          
         txtMaPhieu.setText(null);
         txtTenPhieu.setText(null);
         txtHan.setText(null);
         txtSoLuong.setText(null);
         txtGiaGiam.setText(null);
         txtDieuKien.setText(null);
     }
}