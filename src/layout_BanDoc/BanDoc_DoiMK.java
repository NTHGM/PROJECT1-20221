package layout_BanDoc;

import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import controller.AccountController;
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
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSeparator;

public class BanDoc_DoiMK extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	BanDocController ac;
	AccountController controller;
	BANDOC b;
	InputField txtUserName;
	InputField txtPass;
	InputField txtNewPass;
	InputField txtNewPass0;
	
	public BanDoc_DoiMK(ConnectSQL c, BANDOC b) {
		this.b = b;
		ac = new BanDocController(c);
		controller = new AccountController(c);
		
		setBounds(100, 100, 500, 500);
		getContentPane().setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
	/*
	 * Label
	 * */
		Label lblNewLabel_3 = new Label("THAY ĐỔI MẬT KHẨU BẠN ĐỌC");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(104, 11, 275, 24);
		contentPanel.add(lblNewLabel_3);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(104, 46, 275, 16);
		contentPanel.add(separator);
		
		
	/*
	 * Button
	 * */
		
		Button btnSignin = new Button("Xác nhận");
		btnSignin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xacNhan();
			}
		});
		btnSignin.setBounds(89, 412, 115, 39);
		contentPanel.add(btnSignin);
		
		Button btnCancel = new Button("Huỷ");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(280, 412, 115, 39);
		contentPanel.add(btnCancel);
		
		Label lblNewLabel_3_1 = new Label("Thông tin tài khoản");
		lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_3_1.setBounds(10, 65, 185, 24);
		contentPanel.add(lblNewLabel_3_1);
		
		txtUserName = new InputField("Tài khoản");
		txtUserName.setEditable(false);
		txtUserName.setText(b.getUserName());
		txtUserName.setColumns(10);
		txtUserName.setBounds(10, 100, 439, 50);
		contentPanel.add(txtUserName);
		
		txtPass = new InputField("Nhập mật khẩu cũ");
		txtPass.setColumns(10);
		txtPass.setBounds(10, 168, 439, 50);
		contentPanel.add(txtPass);
		
		txtNewPass = new InputField("Mật khẩu mới");
		txtNewPass.setColumns(10);
		txtNewPass.setBounds(10, 241, 439, 50);
		contentPanel.add(txtNewPass);
		
		txtNewPass0 = new InputField("Nhập lại mật khẩu mới");
		txtNewPass0.setColumns(10);
		txtNewPass0.setBounds(10, 306, 439, 50);
		contentPanel.add(txtNewPass0);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(104, 379, 275, 16);
		contentPanel.add(separator_1);
	}
	void xacNhan() {
		String uN = txtUserName.getText();
		String passCu = txtPass.getText();
		BANDOC tmp = controller.checkLoginBanDoc(uN, passCu);
		if(tmp.getMaBanDoc()!=null) {
			int a = JOptionPane.showConfirmDialog(rootPane, "Bạn có chắc đổi mật khẩu?");
			if(a==0) {
				if(txtNewPass.getText().equals(txtNewPass0.getText())) {
					if(controller.doiMK(uN, txtNewPass.getText().trim())) JOptionPane.showMessageDialog(rootPane, "Đổi thành công!");
					else JOptionPane.showMessageDialog(rootPane, "Đổi thất bại!");
				}else JOptionPane.showMessageDialog(rootPane, "Mật khẩu nhập lại chưa đúng!");
			}
		}else JOptionPane.showMessageDialog(rootPane, "Mật khẩu cũ không chính xác!");
	}
}
