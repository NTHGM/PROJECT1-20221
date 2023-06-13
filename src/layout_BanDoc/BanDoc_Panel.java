package layout_BanDoc;

import java.awt.Color;
import java.awt.Font;

import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import index.Button;
import index.Table;
import model.BANDOC;
import sql.ConnectSQL;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import controller.BanDocController;
import index.Label;
import index.InputField;
import index.ScrollPane;

public class BanDoc_Panel extends JPanel {

	/**
	 * Create the panel.
	 */
	private static final long serialVersionUID = 1L;
	Table table;
	
	ConnectSQL c;
	BanDocController controller;
	ArrayList<BANDOC> list;
	BANDOC b;
	MainFrame_BanDoc mf;
	
	InputField txtNgaySinh;
	InputField txtGioiTinh;
	InputField txtTen;
	InputField txtDiaChi;
	/**
	 * Create the panel.
	 * @param account 
	 */
	public BanDoc_Panel(ConnectSQL n, BANDOC account,MainFrame_BanDoc mf) {
		c=n;
		b=account;
		this.mf=mf;
		controller = new BanDocController(c);
		setLayout(null);
		setBounds(100, 100, 840, 551);
		
	/*
	 * Label
	 * */
		JSeparator separator = new JSeparator();
		separator.setBounds(212, 45, 400, 10);
		add(separator);
		
		JLabel lblNewLabel = new JLabel("THÔNG TIN BẠN ĐỌC");
		lblNewLabel.setForeground(Color.magenta);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 10, 790, 35);
		add(lblNewLabel);
		
	/*
	 * Text
	 */
		
		txtTen = new InputField("Họ và tên");
		txtTen.setEditable(false);
		txtTen.setText("");
		txtTen.setColumns(10);
		txtTen.setBounds(212, 186, 400, 50);
		add(txtTen);
		
		txtGioiTinh = new InputField("Giới tính");
		txtGioiTinh.setText("");
		txtGioiTinh.setEditable(false);
		txtGioiTinh.setColumns(10);
		txtGioiTinh.setBounds(618, 186, 137, 50);
		add(txtGioiTinh);
		
		txtNgaySinh = new InputField("Ngày sinh");
		txtNgaySinh.setText("");
		txtNgaySinh.setEditable(false);
		txtNgaySinh.setColumns(10);
		txtNgaySinh.setBounds(212, 247, 284, 50);
		add(txtNgaySinh);
		
		txtDiaChi = new InputField("Địa chỉ");
		txtDiaChi.setText("");
		txtDiaChi.setEditable(false);
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(212, 331, 543, 50);
		add(txtDiaChi);
		
		InputField txtMaBanDoc = new InputField("Mã bạn đọc");
		txtMaBanDoc.setText(b.getMaBanDoc()+"");
		txtMaBanDoc.setEditable(false);
		txtMaBanDoc.setColumns(10);
		txtMaBanDoc.setBounds(530, 113, 225, 50);
		add(txtMaBanDoc);
		
		InputField txtUsername = new InputField("Username");
		txtUsername.setText(b.getUserName());
		txtUsername.setEditable(false);
		txtUsername.setColumns(10);
		txtUsername.setBounds(212, 113, 284, 50);
		add(txtUsername);
		
		InputField txtNgayGiaHan = new InputField("Ngày gia hạn");
		txtNgayGiaHan.setText(b.getBieudienNgayGiaHan());
		txtNgayGiaHan.setEditable(false);
		txtNgayGiaHan.setColumns(10);
		txtNgayGiaHan.setBounds(212, 412, 225, 50);
		add(txtNgayGiaHan);
		
		InputField txtNgayHetHan = new InputField("Ngày hết hạn");
		txtNgayHetHan.setText(b.getBieudienNgayHetHan());
		txtNgayHetHan.setEditable(false);
		txtNgayHetHan.setColumns(10);
		txtNgayHetHan.setBounds(530, 412, 225, 50);
		add(txtNgayHetHan);
		
	
		
	/*
	 * BUTTON
	 * */
		
		Button btnUpdate = new Button("Chỉnh sửa");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sua();
			}
		});
		
		btnUpdate.setBounds(10, 144, 192, 35);
		add(btnUpdate);
		
		Button btnGiaHan = new Button("Đổi mật khẩu");
		btnGiaHan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BanDoc_DoiMK f = new BanDoc_DoiMK(c,b);
				f.setVisible(true);
			}
		});
		btnGiaHan.setBounds(10, 198, 192, 35);
		add(btnGiaHan);
		
		Button btnGiaHan_1 = new Button("Gia hạn tài khoản");
		btnGiaHan_1.setBounds(10, 252, 192, 35);
		add(btnGiaHan_1);
		update();
	}
	
	
	/*
	 * Event
	 * */
	void sua() {
		BanDoc_ChinhSua f = new BanDoc_ChinhSua(c, b,this);
		f.setVisible(true);
	}
	void update() {
		this.b = mf.account;
		txtTen.setText(b.getTenBanDoc());
		String gt = "Nam";
		if(b.getGioiTinhIndex()==1) gt = "Nữ";
		else if(b.getGioiTinhIndex()==2) gt = "Khác";
		txtGioiTinh.setText(gt);
		txtNgaySinh.setText(b.getBieudienNgaySinh());
		txtDiaChi.setText(b.getDiaChi());
	}
}
