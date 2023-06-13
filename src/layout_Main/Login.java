package layout_Main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AccountController;
import index.InputField;
import index.PasswordField;
import layout_BanDoc.MainFrame_BanDoc;
import layout_ThuThu.BanDoc_Them;
import layout_ThuThu.MainFrame_ThuThu;
import model.BANDOC;
import model.THUTHU;
import sql.ConnectSQL;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import index.Button;
import index.ComboBox;
import javax.swing.DefaultComboBoxModel;

public class Login extends JFrame {

	private final JPanel contentPanel = new JPanel();
	private ComboBox<String> vaitro;

	InputField txtTaikhoan;
	PasswordField txtMatkhau;
	MainFrame_BanDoc bF;
	private MainFrame_ThuThu tF;
	AccountController controller;
	ConnectSQL c;
	
	BANDOC b;
	public BANDOC getB() {
		return b;
	}

	public void setB(BANDOC b) {
		this.b = b;
	}

	public THUTHU getT() {
		return t;
	}

	public void setT(THUTHU t) {
		this.t = t;
	}
	THUTHU t;
	
	public Login() {
		c=new ConnectSQL();
		controller = new AccountController(c);
		
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		
		vaitro = new ComboBox<>();
		vaitro.setModel(new DefaultComboBoxModel(new String[] {"Thủ thư", "Bạn đọc"}));
		vaitro.setSelectedIndex(-1);
		vaitro.setLabelText("Bạn là?");
		vaitro.setBounds(10, 57, 160, 36);
		getContentPane().add(vaitro);
		
		txtTaikhoan = new InputField("Tài khoản");
		txtTaikhoan.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtTaikhoan.setBounds(10, 104, 414, 45);
		getContentPane().add(txtTaikhoan);
		
		txtMatkhau = new PasswordField("Mật khẩu");
		txtMatkhau.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtMatkhau.setBounds(10, 160, 414, 45);
		getContentPane().add(txtMatkhau);
		
		JLabel lblT = new JLabel("Đăng nhập");
		lblT.setHorizontalAlignment(SwingConstants.CENTER);
		lblT.setForeground(Color.MAGENTA);
		lblT.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblT.setBounds(0, 11, 434, 35);
		getContentPane().add(lblT);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(68, 46, 299, 10);
		getContentPane().add(separator);
		
		Button btnDangNhap = new Button("Đăng nhập");
		btnDangNhap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dangnhap();
			}
		});
		btnDangNhap.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnDangNhap.setBounds(10, 225, 120, 25);
		getContentPane().add(btnDangNhap);
		
		Button btnHuy = new Button("Hủy");
		btnHuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnHuy.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnHuy.setBounds(297, 225, 127, 25);
		getContentPane().add(btnHuy);
		
		Button btnDangKi = new Button("Đăng ký");
		btnDangKi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dangKy();
			}
		});
		btnDangKi.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnDangKi.setBounds(151, 225, 125, 25);
		getContentPane().add(btnDangKi);
	}
	
	void dangnhap() {
		String uN = txtTaikhoan.getText();
		String pass = txtMatkhau.getText();
		int i = vaitro.getSelectedIndex();
		boolean check = false;
		if(uN.isBlank()) {
			JOptionPane.showMessageDialog(rootPane, "Tài khoản trống!");
		}else if(i==-1){
			JOptionPane.showMessageDialog(rootPane, "Hãy chọn người dùng!");
		}else check = true;
		boolean checkL = false;
		if(check) {
			switch(i) {
			case 1:
				b = new BANDOC();
				b = controller.checkLoginBanDoc(uN, pass);
				if (b.getMaBanDoc()!=null) {
					checkL = true;
				}
				break;
			case 0: 
				t = new THUTHU();
				t = controller.checkLoginThuThu(uN, pass); 
				if(t.getMaThuThu()!=null) {
					checkL=true;
				}
				break;
			}
			if(checkL) {
				mainFrame(i);
				reset();
				dispose();
			} else JOptionPane.showMessageDialog(rootPane, "Tài khoản hoặc mật khẩu không đúng!!");
		}
		
	}
	void mainFrame(int i) {
		switch(i) {
		case 1:
			bF = new MainFrame_BanDoc(this, c);
			bF.setVisible(true);
			break;
		case 0:
			settF(new MainFrame_ThuThu(this, c));
			gettF().setVisible(true);
			break;
		}
		
	}
	void dangKy() {
		BanDoc_Them f = new BanDoc_Them(c);
		f.setVisible(true);
	}

	public MainFrame_ThuThu gettF() {
		return tF;
	}

	public void settF(MainFrame_ThuThu tF) {
		this.tF = tF;
	}

	public void reset() {
		txtMatkhau.setText("");
		txtTaikhoan.setText("");
	}
}

