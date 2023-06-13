package layout_ThuThu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.SachController;
import model.SACH;
import sql.ConnectSQL;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import index.Button;
import index.InputField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Sach_ThemQuyen extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	InputField txtTimKiem_2;
	SACH s;
	SachController controller;
	

	/**
	 * Create the dialog.
	 */
	public Sach_ThemQuyen(ConnectSQL c,SACH ss) {
		controller = new SachController(c);
		s = controller.timSach(ss);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Thêm số lượng");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.MAGENTA);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 11, 416, 24);
		contentPanel.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(20, 37, 392, 10);
		contentPanel.add(separator);
		
		InputField txtTimKiem = new InputField("Mã sách");
		txtTimKiem.setEditable(false);
		txtTimKiem.setText(s.getMaSach()+"");
		txtTimKiem.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtTimKiem.setBounds(10, 58, 416, 43);
		contentPanel.add(txtTimKiem);
		
		InputField txtTimKiem_1 = new InputField("Tên sách");
		txtTimKiem_1.setEditable(false);
		txtTimKiem_1.setText(s.getTenSach());
		txtTimKiem_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtTimKiem_1.setBounds(10, 107, 416, 43);
		contentPanel.add(txtTimKiem_1);
		
		txtTimKiem_2 = new InputField("Số lượng");
		txtTimKiem_2.setText("0");
		txtTimKiem_2.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtTimKiem_2.setBounds(10, 156, 416, 43);
		contentPanel.add(txtTimKiem_2);
		
		Button btnUpdate_1 = new Button("Thêm");
		btnUpdate_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				them();
			}
		});
		btnUpdate_1.setBounds(70, 225, 98, 25);
		contentPanel.add(btnUpdate_1);
		
		Button btnXoa = new Button("Hủy");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnXoa.setBounds(259, 225, 98, 25);
		contentPanel.add(btnXoa);
		
	}
	
	void them() {
		boolean check = false;
		if(txtTimKiem_2.getText().isBlank()) {
			JOptionPane.showMessageDialog(rootPane, "Nhập số muốn thêm!");
		}else check = true;
		int a = 0;
		try {
			a = Integer.parseInt(txtTimKiem_2.getText());
			if(a<0) {
				JOptionPane.showMessageDialog(rootPane, "Số lượng không hợp lệ!");
				check = false;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(rootPane, "Số lượng không hợp lệ!");
			check = false;
		}
		if(check) {
			s.setSolg(s.getSolg()+a);
			controller.themSl(s);
			JOptionPane.showMessageDialog(rootPane, "Thêm thành công!");
			dispose();
		}
	}
}
