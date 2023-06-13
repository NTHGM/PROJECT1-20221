package layout_ThuThu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.SachController;
import controller.TheMuonTraController;
import index.Button;
import index.ComboBox;
import index.InputField;
import index.Label;
import model.*;
import sql.ConnectSQL;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JSeparator;

public class Sach_ThongTin extends JDialog {

	private final JPanel contentPanel = new JPanel();

	private InputField ten;
	private InputField namxb;
	private InputField sl;
	InputField txtTacGia;
	InputField theLoaiBoxSelect;
	InputField nxbBoxSelect;
	
	SachController controller;
	
	SACH s;
	
	public Sach_ThongTin(ConnectSQL c,SACH ss) {
		controller = new SachController(c);
		s=controller.timSach(ss);
		s.setSoSachMuon(controller.soSachMuon(s));
		
		setLocationRelativeTo(null);
		setBounds(100, 100, 700, 450);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
	/*
	 * Label
	 * */
		Label lblNewLabel = new Label("Thông tin sách");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(171, 11, 359, 36);
		contentPanel.add(lblNewLabel);
		
	/*
	 * Text
	 * */	
	
		ten = new InputField("Tên sách(*)");
		ten.setEditable(false);
		ten.setText(s.getTenSach());
		ten.setBounds(10, 58, 255, 55);
		contentPanel.add(ten);
		ten.setColumns(10);
		
		namxb = new InputField("Năm xuất bản(*)");
		namxb.setEditable(false);
		namxb.setText(s.getNamXuatBan()+"");
		namxb.setBounds(10, 216, 166, 55);
		contentPanel.add(namxb);
		
		sl = new InputField("Số lượng quyển(*)");
		sl.setEditable(false);
		sl.setText(s.getSolg()+"");
		sl.setBounds(10, 286, 194, 55);
		contentPanel.add(sl);
		
		Button btnNewButton = new Button("Close");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		}) ;
		btnNewButton.setBounds(272, 365, 141, 36);
		contentPanel.add(btnNewButton);
		
		theLoaiBoxSelect= new InputField("Thể loại(*)");
		theLoaiBoxSelect.setEditable(false);
		theLoaiBoxSelect.setText(s.getTenTheLoai());
		theLoaiBoxSelect.setBounds(480, 58, 194, 55);
		contentPanel.add(theLoaiBoxSelect);
		
		nxbBoxSelect = new InputField("Nhà xuất bản(*)");
		nxbBoxSelect.setEditable(false);
		nxbBoxSelect.setText(s.getTenNXB());
		nxbBoxSelect.setBounds(186, 216, 488, 55);
		contentPanel.add(nxbBoxSelect);
		
		txtTacGia = new InputField("Tác giả(*)");
		txtTacGia.setEditable(false);
		txtTacGia.setText(s.getTenTacgiaL());
		txtTacGia.setColumns(10);
		txtTacGia.setBounds(10, 137, 664, 55);
		contentPanel.add(txtTacGia);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(138, 45, 500, 11);
		contentPanel.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(61, 352, 572, 11);
		contentPanel.add(separator_1);
		
		InputField theLoaiBoxSelect_1 = new InputField("Mã sách(*)");
		theLoaiBoxSelect_1.setEditable(false);
		theLoaiBoxSelect_1.setText(s.getMaSach()+"");
		theLoaiBoxSelect_1.setBounds(271, 58, 194, 55);
		contentPanel.add(theLoaiBoxSelect_1);
		
		InputField sl_1 = new InputField("Số lượng quyển hiện có(*)");
		sl_1.setEditable(false);
		sl_1.setText(""+(s.getSolg()-s.getSoSachMuon()));
		sl_1.setBounds(226, 286, 222, 55);
		contentPanel.add(sl_1);
		
	}

	
}
