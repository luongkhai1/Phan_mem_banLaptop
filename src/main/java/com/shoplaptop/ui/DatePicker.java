package com.shoplaptop.ui;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;

import com.toedter.calendar.JDateChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class DatePicker extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DatePicker dialog = new DatePicker();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	private JLabel lblNewLabel;
	private JDateChooser dateChooser;
	

	/**
	 * Create the dialog.
	 */
	public DatePicker() {
		getContentPane().setLayout(null);
		setBounds(100, 100, 583, 500);
		getContentPane().setLayout(null);
		
		lblNewLabel = new JLabel("DATEPICKER");
		lblNewLabel.setBounds(108, 11, 301, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Date");
		lblNewLabel_1.setBounds(129, 108, 45, 14);
		getContentPane().add(lblNewLabel_1);
		
		dateChooser = new JDateChooser();
		dateChooser.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		dateChooser.setBounds(217, 92, 200, 30);
		getContentPane().add(dateChooser);

	}

}