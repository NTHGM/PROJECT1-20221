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

public class Sach_Sua extends JDialog {

	private final JPanel contentPanel = new JPanel();

	private InputField ten;
	private InputField namxb;
	private InputField sl;
	InputField txtTacGia;
	InputField theLoaiBoxSelect;
	InputField nxbBoxSelect;
	ArrayList<TACGIA> tgL;
	THELOAI theLoai;
	NXB nxb;
	SachController controller;
	SACH s;
	ConnectSQL c;
	
	public Sach_Sua(ConnectSQL c,SACH ss) {
		this.c=c;
		controller = new SachController(c);
		s=controller.timSach(ss);
		
		tgL = s.getTacgiaL();
		nxb = s.getNXB();
		theLoai = s.getTheLoai();
		
		s=controller.timSach(ss);
		setLocationRelativeTo(null);
		setBounds(100, 100, 700, 450);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
	/*
	 * Label
	 * */
		Label lblNewLabel = new Label("Chỉnh sửa sách");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(171, 11, 359, 36);
		contentPanel.add(lblNewLabel);
		
	/*
	 * Text
	 * */	
	
		ten = new InputField("Tên sách(*)");
		ten.setBounds(10, 58, 332, 55);
		ten.setText(s.getTenSach());
		contentPanel.add(ten);
		ten.setColumns(10);
		
		namxb = new InputField("Năm xuất bản(*)");
		namxb.setText(""+s.getNamXuatBan());
		namxb.setBounds(364, 58, 166, 55);
		contentPanel.add(namxb);
		
		sl = new InputField("Số lượng quyển(*)");
		sl.setText(""+s.getSolg());
		sl.setEditable(false);
		sl.setBounds(546, 58, 128, 55);
		contentPanel.add(sl);
		
	/*
	 * Button
	 * */
		
		JButton btnTG = new JButton();
		btnTG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				themTG();
			}
		});
		btnTG.setBounds(474, 137, 48, 48);
		btnTG.setIcon(new ImageIcon("Resource/add48red.png"));
		contentPanel.add(btnTG);
		
		Button btnThem = new Button("Xác nhận");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean check = false;
				if(ten.getText().isBlank()) {
					JOptionPane.showMessageDialog(rootPane, "Nhập tên sách!");
				}else if(namxb.getText().isBlank()) {
					JOptionPane.showMessageDialog(rootPane, "Nhập năm xuất bản!");
				}else if(nxbBoxSelect.getText().isBlank()) {
					JOptionPane.showMessageDialog(rootPane, "Thêm nhà xuất bản!");
				}else if(theLoaiBoxSelect.getText().isBlank()) {
					JOptionPane.showMessageDialog(rootPane, "Thêm thể loại!");
				}else if(txtTacGia.getText().isBlank()) {
					JOptionPane.showMessageDialog(rootPane, "Thêm tác giả!");
				}else check = true;
				int namxban=-1;
				int solg=-1;
				try {
					namxban = Integer.parseInt(namxb.getText());
					if(namxban<=0) {
						JOptionPane.showMessageDialog(rootPane, "Năm không hợp lệ!");
						check = false;
					}
				} catch (Exception e2) {
					check = false;
					JOptionPane.showMessageDialog(rootPane, "Năm không hợp lệ!");
				}
				
				if(check) {
					int a = JOptionPane.showConfirmDialog(rootPane, "Sửa sẽ không thể quay lại\nBạn có chắc chắn?");
					if(a==0) {
						String tenS = ten.getText();
						SACH sach = new SACH(tenS, namxban, theLoai, nxb, tgL, s.getSolg());
						sach.setMaSach(s.getMaSach());
						controller.xoaTS_maSach(sach);
						controller.suaS(sach);
						controller.themTS(sach);
						JOptionPane.showMessageDialog(rootPane, "Sửa thành công!");
						dispose();
					}
				}
			}
		});
		btnThem.setBounds(175, 364, 141, 36);
		contentPanel.add(btnThem);
		
		Button btnNewButton = new Button("Huỷ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		}) ;
		btnNewButton.setBounds(424, 364, 141, 36);
		contentPanel.add(btnNewButton);
		
		theLoaiBoxSelect= new InputField("Thể loại(*)");
		theLoaiBoxSelect.setText(s.getTenTheLoai());
		theLoaiBoxSelect.setEditable(false);
		theLoaiBoxSelect.setBounds(10, 203, 456, 55);
		contentPanel.add(theLoaiBoxSelect);
		
		nxbBoxSelect = new InputField("Nhà xuất bản(*)");
		nxbBoxSelect.setText(s.getTenNXB());
		nxbBoxSelect.setEditable(false);
		nxbBoxSelect.setBounds(10, 275, 456, 55);
		contentPanel.add(nxbBoxSelect);
		
		JButton btnThemTheLoai = new JButton("");
		btnThemTheLoai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				themTheLoai();
			}
		});
		btnThemTheLoai.setIcon(new ImageIcon("Resource/add48yellow.png"));
		btnThemTheLoai.setBounds(474, 203, 48, 48);
		contentPanel.add(btnThemTheLoai);
		
		JButton btnThemNXB = new JButton("");
		btnThemNXB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				themNXB();
			}
		});
		btnThemNXB.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnThemNXB.setBounds(474, 274, 48, 48);
		btnThemNXB.setIcon(new ImageIcon("Resource/add48.png"));
		contentPanel.add(btnThemNXB);
		
		txtTacGia = new InputField("Tác giả(*)");
		txtTacGia.setText(s.getTenTacgiaL());
		txtTacGia.setEditable(false);
		txtTacGia.setColumns(10);
		txtTacGia.setBounds(10, 137, 454, 55);
		contentPanel.add(txtTacGia);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(138, 45, 500, 11);
		contentPanel.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(61, 352, 572, 11);
		contentPanel.add(separator_1);
		
	}

	void themTG() {
		Sach_ChonTacGia f = new Sach_ChonTacGia(c, null, this);
		f.setVisible(true);
	}
	
	void themTheLoai() {
		Sach_ChonTheLoai f = new Sach_ChonTheLoai(c, null, this);
		f.setVisible(true);
	}
	void themNXB() {
		Sach_ChonNXB f = new Sach_ChonNXB(c, null,this);
		f.setVisible(true);
	}
}
