package layout_ThuThu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.TheLoaiController;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import index.InputField;
import model.THELOAI;
import sql.ConnectSQL;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import index.Button;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TheLoai_Them extends JDialog {

	private final JPanel contentPanel = new JPanel();
	InputField ten;
	TheLoaiController controller;
	
	public TheLoai_Them(ConnectSQL c) {
		controller = new TheLoaiController(c);
		setBounds(100, 100, 450, 225);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		ten = new InputField("Tên thể loại mới");
		ten.setFont(new Font("Tahoma", Font.BOLD, 10));
		
		JLabel lblThmNhXut = new JLabel("Thêm thể loại mới");
		lblThmNhXut.setHorizontalAlignment(SwingConstants.CENTER);
		lblThmNhXut.setForeground(Color.MAGENTA);
		lblThmNhXut.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		JSeparator separator = new JSeparator();
		
		Button btnThm = new Button("Thêm");
		btnThm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean check = false;
				if(ten.getText().isBlank()) {
					JOptionPane.showMessageDialog(rootPane, "Nhập tên thể loại!");
				}else check = true;
				if(check) {
					String tenS = ten.getText();
					THELOAI t = new THELOAI(null, tenS);
					if(controller.themTheLoai(t)) {
						JOptionPane.showMessageDialog(rootPane, "Thêm thành công!");
						dispose();
					}else JOptionPane.showMessageDialog(rootPane, "Thêm thất bại!");
				}
			}
		});
		btnThm.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		Button btnLmMi = new Button("Làm mới");
		btnLmMi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ten.setText("");
			}
		});
		btnLmMi.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		Button btnng = new Button("Đóng");
		btnng.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnng.setFont(new Font("Tahoma", Font.BOLD, 12));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnThm, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnLmMi, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnng, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
					.addGap(16))
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap(78, Short.MAX_VALUE)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 299, GroupLayout.PREFERRED_SIZE)
					.addGap(57))
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(ten, GroupLayout.PREFERRED_SIZE, 414, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(lblThmNhXut, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(lblThmNhXut, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(ten, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addGap(31)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnThm, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnLmMi, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnng, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(50))
		);
		contentPanel.setLayout(gl_contentPanel);
	}
}
