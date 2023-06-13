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

public class TheMuonTra_ThongTin extends JDialog {
	private InputField txtDatCoc, txtTienPhat;
	private InputField txtMaBanDoc;
	private InputField txtMaSach;
	private InputField txtGhiChu;
	InputField txtMaThuThu;
	InputField txtNgayTra;
	InputField txtNgayMuon;
	THEMUONTRA t;
	TheMuonTraController controller;
	
	
	public TheMuonTra_ThongTin(ConnectSQL c, THEMUONTRA tt) {
		controller = new TheMuonTraController(c);
		setBounds(100, 100, 450, 450);
		getContentPane().setLayout(null);
		controller = new TheMuonTraController(c);
		this.t = controller.timTheMuonTra(tt);
	
	/*
	 * Label
	 * */

		JLabel lblThmNhXut = new JLabel("Thông tin thẻ mượn trả");
		lblThmNhXut.setHorizontalAlignment(SwingConstants.CENTER);
		lblThmNhXut.setForeground(Color.MAGENTA);
		lblThmNhXut.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblThmNhXut.setBounds(10, 11, 414, 35);
		getContentPane().add(lblThmNhXut);
	
		JSeparator separator = new JSeparator();
		separator.setBounds(57, 53, 336, 10);
		getContentPane().add(separator);
		
	/*
	 * Text
	 * */
		
		txtMaSach = new InputField("Mã sách");
		txtMaSach.setText(t.getSach().getMaSach()+"");
		txtMaSach.setEditable(false);
		txtMaSach.setBounds(10, 92, 120, 47);
		getContentPane().add(txtMaSach);
		
		txtMaBanDoc = new InputField("Mã bạn đọc");
		txtMaBanDoc.setText(t.getBanDoc().getMaBanDoc()+"");
		txtMaBanDoc.setEditable(false);
		txtMaBanDoc.setBounds(140, 92, 129, 47);
		getContentPane().add(txtMaBanDoc);
		
		txtDatCoc = new InputField("Đặt cọc");
		txtDatCoc.setText(t.getTienCoc()+"");
		txtDatCoc.setEditable(false);
		txtDatCoc.setBounds(10, 151, 199, 47);
		getContentPane().add(txtDatCoc);
		
		txtGhiChu = new InputField("Ghi chú");
		txtGhiChu.setText(t.getGhiChu());
		txtGhiChu.setEditable(false);
		txtGhiChu.setBounds(10, 213, 414, 47);
		getContentPane().add(txtGhiChu);
		
		txtMaThuThu = new InputField("Mã thủ thư");
		txtMaThuThu.setText(t.getThuThu().getMaThuThu()+"");
		txtMaThuThu.setEditable(false);
		txtMaThuThu.setBounds(295, 92, 129, 47);
		getContentPane().add(txtMaThuThu);
		
		Button btnng = new Button("Đóng");
		btnng.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnng.setBounds(140, 348, 127, 35);
		getContentPane().add(btnng);
		
		txtNgayMuon = new InputField("Ngày mượn");
		txtNgayMuon.setText(t.getBieuDienNgayMuon());
		txtNgayMuon.setEditable(false);
		txtNgayMuon.setBounds(10, 279, 171, 47);
		getContentPane().add(txtNgayMuon);
		
		txtNgayTra = new InputField("Ngày phải trả");
		txtNgayTra.setText(t.getBieuDienNgayTra());
		txtNgayTra.setEditable(false);
		txtNgayTra.setBounds(244, 279, 180, 47);
		getContentPane().add(txtNgayTra);
		
		txtTienPhat = new InputField("Tiền phạt");
		txtTienPhat.setText(t.getStringTienPhat());
		txtTienPhat.setEditable(false);
		txtTienPhat.setBounds(225, 151, 199, 47);
		getContentPane().add(txtTienPhat);
		
		
	}
}
