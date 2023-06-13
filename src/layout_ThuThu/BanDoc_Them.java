package layout_ThuThu;

import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import controller.BanDocController;
import index.Button;
import index.ComboBox;
import index.InputField;
import index.Label;
import model.BANDOC;
import sql.ConnectSQL;
import sql.Utility;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSeparator;

public class BanDoc_Them extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private InputField txtUserName;
	private InputField txtPassword;
	private InputField txtRePassword;
	InputField txtMaBanDoc;
	ComboBox<String> comboBox;
	BanDocController controller;
	private InputField txtTen;
	private InputField txtDiaChi;
	JDateChooser chonNgaySinh;
	Utility utl;
	
	public BanDoc_Them(ConnectSQL c) {
		controller = new BanDocController(c);
		utl = new Utility();
		
		setBounds(100, 100, 500, 650);
		getContentPane().setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
	/*
	 * Label
	 * */
		Label lblNewLabel_3 = new Label("THÊM BẠN ĐỌC MỚI");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(136, 11, 185, 24);
		contentPanel.add(lblNewLabel_3);
		
		Label lblNewLabel_3_1 = new Label("Thông tin tài khoản");
		lblNewLabel_3_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1.setBounds(16, 316, 185, 24);
		contentPanel.add(lblNewLabel_3_1);
		
		Label lblNewLabel_3_2 = new Label("Thông tin chung");
		lblNewLabel_3_2.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_3_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_2.setBounds(10, 71, 185, 24);
		contentPanel.add(lblNewLabel_3_2);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(104, 46, 275, 16);
		contentPanel.add(separator);
		
		Label lblNewLabel_3_2_1 = new Label("Ngày sinh");
		lblNewLabel_3_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_2_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_3_2_1.setBounds(310, 171, 145, 24);
		contentPanel.add(lblNewLabel_3_2_1);
	/*
	 * Input
	 * */
		
		txtUserName = new InputField("Tài khoản");
		txtUserName.setBounds(16, 351, 439, 50);
		contentPanel.add(txtUserName);
		txtUserName.setColumns(10);
		
		txtPassword = new InputField("Mật khẩu");
		txtPassword.setBounds(16, 412, 439, 50);
		contentPanel.add(txtPassword);
		txtPassword.setColumns(10);
		
		txtRePassword = new InputField("Nhập lại mật khẩu");
		txtRePassword.setBounds(16, 473, 439, 50);
		contentPanel.add(txtRePassword);
		txtRePassword.setColumns(10);
		
		txtTen = new InputField("Họ và tên");
		txtTen.setColumns(10);
		txtTen.setBounds(16, 106, 284, 50);
		contentPanel.add(txtTen);
		
		txtDiaChi = new InputField("Địa chỉ");
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(16, 186, 284, 50);
		contentPanel.add(txtDiaChi);
		
		chonNgaySinh = new JDateChooser();
		chonNgaySinh.setBounds(310, 206, 145, 30);
		contentPanel.add(chonNgaySinh);
		
		comboBox = new ComboBox<>();
		comboBox.setLabelText("Giới tính");
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Nam", "Nữ", "Khác"}));
		comboBox.setSelectedIndex(-1);
		comboBox.setBounds(310, 105, 145, 53);
		contentPanel.add(comboBox);
		
	/*
	 * Button
	 * */
		
		Button btnSignin = new Button("Thêm mới");
		btnSignin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean check = false;
				if(txtTen.getText().isBlank()) {
					JOptionPane.showMessageDialog(rootPane, "Nhập tên!");
				}else if(txtDiaChi.getText().isBlank()) {
					JOptionPane.showMessageDialog(rootPane, "Nhập địa chỉ!");
				}else if(txtUserName.getText().isBlank()) {
					JOptionPane.showMessageDialog(rootPane, "Nhập tài khoản!");
				}else if(txtPassword.getText().isBlank()) {
					JOptionPane.showMessageDialog(rootPane, "Nhập mật khẩu!");
				}else if(txtRePassword.getText().isBlank()) {
					JOptionPane.showMessageDialog(rootPane, "Nhập mật khẩu lần 2!");
				}else if(chonNgaySinh.getDate()==null) {
					JOptionPane.showMessageDialog(rootPane, "Chọn ngày sinh!");
				}else if(comboBox.getSelectedIndex()==-1) {
					JOptionPane.showMessageDialog(rootPane, "Chọn giới tính!");
				}else check = true;
				if(!txtPassword.getText().equals(txtRePassword.getText())) {
					JOptionPane.showMessageDialog(rootPane, "Mật khẩu nhập lại không trùng khớp!");
					check = false;
				}
				if(check) {
					String uN = txtUserName.getText();
					String pass = txtPassword.getText();
					String ten = txtTen.getText();
					String gt = "Nam";
					if(comboBox.getSelectedIndex()==0) gt = "Nam";
					else if(comboBox.getSelectedIndex()==1) gt = "Nu";
					else if(comboBox.getSelectedIndex()==2) gt = "Khac";
					String dc = txtDiaChi.getText();
					BANDOC b;
					Date d = utl.setDate(chonNgaySinh);
					b = new BANDOC(ten, gt.trim(), dc, d, uN);
					if(controller.themBanDoc(b,pass)) {
						JOptionPane.showMessageDialog(rootPane, "Tạo thành công!");
						dispose();
					}else JOptionPane.showMessageDialog(rootPane, "Tài khoản đã tồn tại!");
				}
			}
		});
		btnSignin.setBounds(33, 561, 115, 39);
		contentPanel.add(btnSignin);
		
		Button btnReset = new Button("Làm mới");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtPassword.setText("");
				txtRePassword.setText("");
				txtUserName.setText("");
				txtDiaChi.setText("");
				comboBox.setSelectedIndex(-1);;
				txtTen.setText("");
				chonNgaySinh.setDate(null);
			}
		});
		btnReset.setBounds(176, 561, 123, 39);
		contentPanel.add(btnReset);
		
		Button btnCancel = new Button("Hủy");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(322, 561, 115, 39);
		contentPanel.add(btnCancel);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(16, 544, 439, 17);
		contentPanel.add(separator_1);
	}
}
