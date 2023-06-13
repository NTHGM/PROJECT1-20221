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
import sql.ConnectSQL;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import index.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TacGia_Them extends JDialog {

	private final JPanel contentPanel = new JPanel();
	InputField ten;
	TacGiaController controller;
	
	public TacGia_Them(ConnectSQL c) {
		controller = new TacGiaController(c);
		setBounds(100, 100, 450, 200);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		ten = new InputField("Tên tác giả mới");
		ten.setFont(new Font("Tahoma", Font.BOLD, 10));
		ten.setBounds(10, 57, 414, 45);
		contentPanel.add(ten);
		
		JLabel lblThmNhXut = new JLabel("Thêm tác giả mới");
		lblThmNhXut.setHorizontalAlignment(SwingConstants.CENTER);
		lblThmNhXut.setForeground(Color.MAGENTA);
		lblThmNhXut.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblThmNhXut.setBounds(0, 11, 434, 35);
		contentPanel.add(lblThmNhXut);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(68, 46, 299, 10);
		contentPanel.add(separator);
		
		Button btnThm = new Button("Thêm");
		btnThm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean check = false;
				if(ten.getText().isBlank()) {
					JOptionPane.showMessageDialog(rootPane, "Nhập tên tác giả!");
				}else check = true;
				
				if(check) {
					String tenS = ten.getText();
					TACGIA t = new TACGIA();
					t.setTenTacGia(tenS);
					if(controller.themTG(t)) {
						JOptionPane.showMessageDialog(rootPane, "Thêm thành công!");
						dispose();
					}else JOptionPane.showMessageDialog(rootPane, "Thêm thất bại!");
				}
			}
		});
		btnThm.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnThm.setBounds(10, 124, 120, 25);
		contentPanel.add(btnThm);
		
		Button btnLmMi = new Button("Làm mới");
		btnLmMi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ten.setText("");
			}
		});
		btnLmMi.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnLmMi.setBounds(149, 124, 125, 25);
		contentPanel.add(btnLmMi);
		
		Button btnng = new Button("Hủy");
		btnng.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnng.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnng.setBounds(297, 124, 127, 25);
		contentPanel.add(btnng);
	}
}
