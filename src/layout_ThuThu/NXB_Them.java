package layout_ThuThu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.NXBController;
import index.InputField;
import model.NXB;
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

public class NXB_Them extends JDialog {

	private final JPanel contentPanel = new JPanel();
	InputField txtTimKiem;
	InputField txtTimKiem_1;
	NXBController controller;
	
	public NXB_Them(ConnectSQL c) {
		controller = new NXBController(c);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		
		setBounds(100, 100, 450, 250);
		txtTimKiem = new InputField("Tên nhà xuất bản");
		txtTimKiem.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtTimKiem.setBounds(10, 57, 414, 45);
		getContentPane().add(txtTimKiem);
		
		txtTimKiem_1 = new InputField("Địa chỉ");
		txtTimKiem_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtTimKiem_1.setBounds(10, 113, 414, 45);
		getContentPane().add(txtTimKiem_1);
		
		JLabel lblThmNhXut = new JLabel("Thêm nhà xuất bản");
		lblThmNhXut.setHorizontalAlignment(SwingConstants.CENTER);
		lblThmNhXut.setForeground(Color.MAGENTA);
		lblThmNhXut.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblThmNhXut.setBounds(0, 11, 434, 35);
		getContentPane().add(lblThmNhXut);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(68, 46, 299, 10);
		getContentPane().add(separator);
		
		Button btnXacNhan = new Button("Xác nhận");
		btnXacNhan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xacNhan();
			}
		});
		btnXacNhan.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnXacNhan.setBounds(10, 178, 120, 25);
		getContentPane().add(btnXacNhan);
		
		Button btnLammoi = new Button("Làm mới");
		btnLammoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtTimKiem.setText("");
				txtTimKiem_1.setText("");
			}
		});
		btnLammoi.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnLammoi.setBounds(297, 178, 127, 25);
		getContentPane().add(btnLammoi);
		
		Button btnĐong = new Button("Đóng");
		btnĐong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnĐong.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnĐong.setBounds(151, 178, 125, 25);
		getContentPane().add(btnĐong);
		
	}
	void xacNhan() {
		boolean check = false;
		if(txtTimKiem.getText().isBlank()) {
			JOptionPane.showMessageDialog(rootPane, "Nhập tên nhà xuất bản!");
		}else if(txtTimKiem_1.getText().isBlank()) {
			JOptionPane.showMessageDialog(rootPane, "Nhập địa chỉ nhà xuất bản!");
		}else check = true;
		
		if(check) {
			String ten = txtTimKiem.getText();
			String dc = txtTimKiem_1.getText();
			NXB n = new NXB();
			n.setTenNXB(ten);
			n.setDiaChi(dc);
			if(controller.themNXB(n)) {
				JOptionPane.showMessageDialog(rootPane, "Thêm thành công!");
				dispose();
			}else JOptionPane.showMessageDialog(rootPane, "Thêm thất bại!");
		}
	}
}
