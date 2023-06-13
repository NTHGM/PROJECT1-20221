package layout_ThuThu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.TacGiaController;
import index.InputField;
import model.TACGIA;
import model.THELOAI;
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

public class TacGia_Sua extends JDialog {

	private final JPanel contentPanel = new JPanel();
	InputField txtTimKiem;
	InputField txtTimKiem_1;
	TacGiaController controller;
	TACGIA t;
	
	public TacGia_Sua(ConnectSQL c,TACGIA tt) {
		controller = new TacGiaController(c);
		t=controller.timTacGia(tt);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		setBounds(100, 100, 450, 250);
		
		txtTimKiem = new InputField("Mã tác giả");
		txtTimKiem.setText(t.getMaTacGia()+"");
		txtTimKiem.setEditable(false);
		txtTimKiem.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtTimKiem.setBounds(10, 57, 414, 45);
		getContentPane().add(txtTimKiem);
		
		txtTimKiem_1 = new InputField("Tên tác giả");
		txtTimKiem_1.setText(t.getTenTacGia());
		txtTimKiem_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtTimKiem_1.setBounds(10, 113, 414, 45);
		getContentPane().add(txtTimKiem_1);
		
		JLabel lblThmNhXut = new JLabel("Sửa tác giả");
		lblThmNhXut.setHorizontalAlignment(SwingConstants.CENTER);
		lblThmNhXut.setForeground(Color.MAGENTA);
		lblThmNhXut.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblThmNhXut.setBounds(0, 11, 434, 35);
		getContentPane().add(lblThmNhXut);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(68, 46, 299, 10);
		getContentPane().add(separator);
		
		Button btnXN = new Button("Xác nhận");
		btnXN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xacNhan();
			}
		});
		btnXN.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnXN.setBounds(10, 178, 120, 25);
		getContentPane().add(btnXN);
		
		Button btnD = new Button("Hủy");
		btnD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnD.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnD.setBounds(297, 178, 127, 25);
		getContentPane().add(btnD);
		
		Button btnQ = new Button("Quay lại");
		btnQ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtTimKiem_1.setText(t.getTenTacGia());
			}
		});
		btnQ.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnQ.setBounds(151, 178, 125, 25);
		getContentPane().add(btnQ);
		
	}
	void xacNhan() {
		boolean check = false;
		if(txtTimKiem_1.getText().isBlank()) {
			JOptionPane.showMessageDialog(rootPane, "Tên trống!");
		}else check = true;
		
		if(check) {
			int a = JOptionPane.showConfirmDialog(rootPane, "Bạn có chắc muốn sửa?");
			if(a==0) {
				String ten = txtTimKiem_1.getText();
				TACGIA tl = new TACGIA(t.getMaTacGia(),ten);
				if(controller.suaTG(tl)) {
					JOptionPane.showMessageDialog(rootPane, "Sửa thành công!");
					dispose();
				}else JOptionPane.showMessageDialog(rootPane, "Sửa thất bại!");
			}
		}
	}
}
