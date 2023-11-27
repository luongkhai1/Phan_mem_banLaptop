package com.shoplaptop.ui;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JProgressBar;

@SuppressWarnings("serial")
public class HelloWindows extends JDialog {

	private JProgressBar pgbLoading;

	/**
	 * Create the dialog.
	 */
	public HelloWindows(ShopLaptop365 laptop365, boolean modal) {
		super(laptop365,modal);
		setUndecorated(true);
		setBounds(100, 100, 905, 600);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 905, 555);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblTitle = new JLabel("ShopLaptop365");
		lblTitle.setForeground(new Color(255, 255, 255));
		lblTitle.setBackground(new Color(0, 0, 64));
		lblTitle.setOpaque(true);
		lblTitle.setFont(new Font("Times New Roman", Font.PLAIN, 60));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(233, 210, 439, 65);
		panel.add(lblTitle);
		
		JLabel lbl2 = new JLabel("");
		lbl2.setOpaque(true);
		lbl2.setBackground(new Color(15, 84, 176));
		lbl2.setHorizontalAlignment(SwingConstants.CENTER);
		lbl2.setBounds(0, 0, 905, 210);
		panel.add(lbl2);
		
		JLabel lbl1 = new JLabel("");
		lbl1.setOpaque(true);
		lbl1.setBackground(new Color(157, 157, 206));
		lbl1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl1.setBounds(0, 210, 233, 345);
		panel.add(lbl1);
		
		JLabel lbl3 = new JLabel("");
		lbl3.setBackground(new Color(157, 157, 206));
		lbl3.setOpaque(true);
		lbl3.setHorizontalAlignment(SwingConstants.CENTER);
		lbl3.setBounds(672, 210, 233, 345);
		panel.add(lbl3);
		
		JLabel lblNewLabel_2 = new JLabel("Welcome");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 65));
		lblNewLabel_2.setBackground(new Color(157, 157, 206));
		lblNewLabel_2.setOpaque(true);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(233, 274, 439, 281);
		panel.add(lblNewLabel_2);
		
		pgbLoading = new JProgressBar();
		pgbLoading.setOpaque(true);
		pgbLoading.setStringPainted(true);
		pgbLoading.setForeground(new Color(0, 39, 79));
		pgbLoading.setBounds(0, 554, 905, 46);
		getContentPane().add(pgbLoading);
		initProgressBar();
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
	
	public void initProgressBar() {
		new Timer(200, new ActionListener() {
			private int value;
			public void actionPerformed(ActionEvent e) {
				value  = pgbLoading.getValue();
				if (value < pgbLoading.getMaximum()) {
					pgbLoading.setValue(value+10);
				}else {
					dispose();
				}
			}
		}).start();
	}
}
