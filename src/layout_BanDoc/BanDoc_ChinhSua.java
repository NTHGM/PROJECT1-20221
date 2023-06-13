package layout_BanDoc;

import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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

import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSeparator;

public class BanDoc_ChinhSua extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private InputField txtUserName;
	InputField txtMaBanDoc;
	ComboBox<String> comboBox;
	BanDocController controller;
	private InputField txtTen;
	private InputField txtDiaChi;
	JDateChooser chonNgaySinh;
	Utility utl;
	BANDOC b;
	BanDoc_Panel bp;
	
	public BanDoc_ChinhSua(ConnectSQL c, BANDOC b,BanDoc_Panel bp) {
		this.b=b;
		controller = new BanDocController(c);
		utl = new Utility();
		this.bp=bp;
		setBounds(100, 100, 500, 550);
		getContentPane().setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
	/*
	 * Label
	 * */
		Label lblNewLabel_3 = new Label("CHỈNH SỬA BẠN ĐỌC");
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
		txtUserName.setEditable(false);
		txtUserName.setText(b.getUserName());
		txtUserName.setBounds(16, 351, 439, 50);
		contentPanel.add(txtUserName);
		txtUserName.setColumns(10);
		
		txtTen = new InputField("Họ và tên");
		txtTen.setText(b.getTenBanDoc());
		txtTen.setColumns(10);
		txtTen.setBounds(16, 106, 284, 50);
		contentPanel.add(txtTen);
		
		txtDiaChi = new InputField("Địa chỉ");
		txtDiaChi.setText(b.getDiaChi());
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(16, 186, 284, 50);
		contentPanel.add(txtDiaChi);
		
		chonNgaySinh = new JDateChooser();
		chonNgaySinh.setDate(b.getNgaySinh());
		chonNgaySinh.setBounds(310, 206, 145, 30);
		contentPanel.add(chonNgaySinh);
		
		comboBox = new ComboBox<>();
		comboBox.setLabelText("Giới tính");
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Nam", "Nữ", "Khác"}));
		comboBox.setSelectedIndex(b.getGioiTinhIndex());
		comboBox.setBounds(310, 105, 145, 53);
		contentPanel.add(comboBox);
		
	/*
	 * Button
	 * */
		
		Button btnSignin = new Button("Chỉnh sửa");
		btnSignin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xacNhan();
			}
		});
		btnSignin.setBounds(85, 440, 115, 39);
		contentPanel.add(btnSignin);
		
		Button btnCancel = new Button("Thoát");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(276, 440, 115, 39);
		contentPanel.add(btnCancel);
		
		txtMaBanDoc = new InputField("Mã bạn đọc");
		txtMaBanDoc.setEditable(false);
		txtMaBanDoc.setText(b.getMaBanDoc()+"");
		txtMaBanDoc.setColumns(10);
		txtMaBanDoc.setBounds(16, 260, 439, 50);
		contentPanel.add(txtMaBanDoc);
	}
	
	void xacNhan() {
		boolean check = false;
		if(txtTen.getText().isBlank()) {
			JOptionPane.showMessageDialog(rootPane, "Nhập tên!");
		}else if(txtDiaChi.getText().isBlank()) {
			JOptionPane.showMessageDialog(rootPane, "Nhập địa chỉ!");
		}else if(txtUserName.getText().isBlank()) {
			JOptionPane.showMessageDialog(rootPane, "Nhập tài khoản!");
		}else if(chonNgaySinh.getDate()==null) {
			JOptionPane.showMessageDialog(rootPane, "Chọn ngày sinh!");
		}else if(comboBox.getSelectedIndex()==-1) {
			JOptionPane.showMessageDialog(rootPane, "Chọn giới tính!");
		}else check = true;
		
		if(check) {
			int a = JOptionPane.showConfirmDialog(rootPane, "Bạn có chắc sửa?");
			if(a==0) {
			String uN = txtUserName.getText();
			String ten = txtTen.getText();
			String gt = "Nam";
			if(comboBox.getSelectedIndex()==0) gt = "Nam";
			else if(comboBox.getSelectedIndex()==1) gt = "Nu";
			else if(comboBox.getSelectedIndex()==2) gt = "Khac";
			String dc = txtDiaChi.getText();
			Date d = utl.setDate(chonNgaySinh);
			b.setDiaChi(dc);
			b.setNgaySinh(d);
			b.setGioiTinh(gt);
			b.setTenBanDoc(ten);
			if(controller.suaBanDoc(b)) {
				JOptionPane.showMessageDialog(rootPane, "Sửa thành công!");
				bp.mf.account = controller.timBanDoc(b);
				bp.update();
				dispose();
			}else JOptionPane.showMessageDialog(rootPane, "Sửa thất bại!");
			}
		}
	}
}
