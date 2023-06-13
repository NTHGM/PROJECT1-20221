package layout_ThuThu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import index.InputField;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import com.toedter.calendar.JDateChooser;

import controller.TheMuonTraController;
import index.Label;
import model.THEMUONTRA;
import sql.ConnectSQL;
import index.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TheMuonTra_TraSach extends JDialog {
	private InputField txtMaBanDoc;
	private InputField txtMaSach;
	private InputField txtGhiChu, txtGhiChu_new;
	InputField txtMaThuThu;
	InputField txtTienPhat;
	InputField txtNgayMuon, txtNgayTra;
	TheMuonTraController controller;
	THEMUONTRA t;
	/**
	 * Create the dialog.
	 * @param t 
	 * @param c 
	 */
	public TheMuonTra_TraSach(ConnectSQL c, THEMUONTRA tt) {
		setBounds(100, 100, 450, 450);
		getContentPane().setLayout(null);
		controller = new TheMuonTraController(c);
		t = controller.timTheMuonTra(tt);
	
	/*
	 * Label
	 * */

		JLabel lblThmNhXut = new JLabel("Trả Sách");
		lblThmNhXut.setHorizontalAlignment(SwingConstants.CENTER);
		lblThmNhXut.setForeground(Color.MAGENTA);
		lblThmNhXut.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblThmNhXut.setBounds(10, 11, 414, 35);
		getContentPane().add(lblThmNhXut);
	
		JSeparator separator = new JSeparator();
		separator.setBounds(57, 53, 336, 10);
		getContentPane().add(separator);
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(78, 344, 292, 10);
		getContentPane().add(separator_1);
		
		
	/*
	 * Text
	 * */
		
		txtMaSach = new InputField("Mã sách");
		txtMaSach.setText(t.getSach().getMaSach()+"");
		txtMaSach.setEditable(false);
		txtMaSach.setBounds(10, 92, 120, 47);
		getContentPane().add(txtMaSach);
		
		txtMaBanDoc = new InputField("Mã bạn đọc");
		txtMaBanDoc.setText(""+t.getBanDoc().getMaBanDoc());
		txtMaBanDoc.setEditable(false);
		txtMaBanDoc.setBounds(140, 92, 129, 47);
		getContentPane().add(txtMaBanDoc);
		
		txtGhiChu = new InputField("Ghi chú");
		txtGhiChu.setText(t.getGhiChu());
		txtGhiChu.setEditable(false);
		txtGhiChu.setBounds(10, 150, 414, 47);
		getContentPane().add(txtGhiChu);
		
		txtMaThuThu = new InputField("Mã thủ thư");
		txtMaThuThu.setText(t.getThuThu().getMaThuThu()+"");
		txtMaThuThu.setEditable(false);
		txtMaThuThu.setBounds(295, 92, 129, 47);
		getContentPane().add(txtMaThuThu);
		
		txtNgayTra = new InputField("Ngày phải trả");
		txtNgayTra.setText(t.getBieuDienNgayTra());
		txtNgayTra.setEditable(false);
		txtNgayTra.setBounds(253, 208, 171, 47);
		getContentPane().add(txtNgayTra);
		
		txtGhiChu_new = new InputField("Ghi chú mới");
		txtGhiChu_new.setBounds(10, 272, 213, 47);
		getContentPane().add(txtGhiChu_new);
		
		txtTienPhat = new InputField("Tiền phạt");
		txtTienPhat.setBounds(253, 272, 171, 47);
		getContentPane().add(txtTienPhat);
		
		txtNgayMuon = new InputField("Ngày mượn");
		txtNgayMuon.setText(t.getBieuDienNgayMuon());
		txtNgayMuon.setEditable(false);
		txtNgayMuon.setBounds(10, 208, 171, 47);
		getContentPane().add(txtNgayMuon);
		
	/*
	 * Button
	 * */
		
		Button btnng = new Button("Xác nhận trả");
		btnng.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xacNhan();
			}
		});
		btnng.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnng.setBounds(57, 365, 127, 35);
		getContentPane().add(btnng);
		
		Button btnThoat = new Button("Đóng");
		btnThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnThoat.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnThoat.setBounds(266, 365, 127, 35);
		getContentPane().add(btnThoat);
		
		
	}
	
	void xacNhan() {
		float tienPhat = 0;
		if(!txtTienPhat.getText().isBlank()) {
			boolean check =true;
			try {
				tienPhat = Float.parseFloat(txtTienPhat.getText());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(rootPane, "Tiền phạt không hợp lệ!");
				check = false;
			}
			if(check) {
				t.setGhiChu(txtGhiChu.getText());
				t.setTienPhat(tienPhat);
				if (controller.traSach(t)) {
					JOptionPane.showMessageDialog(rootPane, "Trả thành công!");
					dispose();
				}else JOptionPane.showMessageDialog(rootPane, "Trả thất bại!");
			}
		}else {
			t.setGhiChu(txtGhiChu.getText());
			t.setTienPhat(tienPhat);
			if (controller.traSach(t)) {
				JOptionPane.showMessageDialog(rootPane, "Trả thành công!");
				dispose();
			}else JOptionPane.showMessageDialog(rootPane, "Trả thất bại!");
		}
		
	}
}
