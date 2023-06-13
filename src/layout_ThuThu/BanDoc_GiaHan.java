package layout_ThuThu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.BanDocController;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import index.InputField;
import model.BANDOC;
import sql.ConnectSQL;
import sql.Utility;
import index.Button;
import com.toedter.calendar.JDateChooser;
import index.Label;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.awt.event.ActionEvent;

public class BanDoc_GiaHan extends JDialog {

	private final JPanel contentPanel = new JPanel();

	BanDocController controller;
	BANDOC b;
	JDateChooser chonHan;
	Utility utl;
	
	public BanDoc_GiaHan(ConnectSQL c,BANDOC bb) {
		utl = new Utility();
		controller= new BanDocController(c);
		b=controller.timBanDoc(bb);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblGiaHn = new JLabel("Gia hạn");
		lblGiaHn.setHorizontalAlignment(SwingConstants.CENTER);
		lblGiaHn.setForeground(Color.MAGENTA);
		lblGiaHn.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblGiaHn.setBounds(0, 11, 434, 35);
		contentPanel.add(lblGiaHn);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(68, 46, 299, 10);
		contentPanel.add(separator);
		
		InputField txtTimKiem = new InputField("Mã bạn đọc");
		txtTimKiem.setText(""+b.getMaBanDoc());
		txtTimKiem.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtTimKiem.setEditable(false);
		txtTimKiem.setBounds(10, 57, 414, 45);
		contentPanel.add(txtTimKiem);
		
		InputField txtTimKiem_1 = new InputField("Tên bạn đọc");
		txtTimKiem_1.setText(b.getTenBanDoc());
		txtTimKiem_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtTimKiem_1.setBounds(10, 113, 414, 45);
		txtTimKiem_1.setEditable(false);
		contentPanel.add(txtTimKiem_1);
		
		Button btnXN = new Button("Xác nhận");
		btnXN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xacNhan();
			}
		});
		btnXN.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnXN.setBounds(67, 225, 120, 25);
		contentPanel.add(btnXN);
		
		Button btnD = new Button("Hủy");
		btnD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnD.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnD.setBounds(236, 225, 127, 25);
		contentPanel.add(btnD);
		
		chonHan = new JDateChooser();
		chonHan.setBounds(174, 169, 250, 30);
		contentPanel.add(chonHan);
		
		Label lblNewLabel_3_2_1 = new Label("Hạn mới");
		lblNewLabel_3_2_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_3_2_1.setBounds(10, 169, 154, 30);
		contentPanel.add(lblNewLabel_3_2_1);
	}
	
	void xacNhan() {
		boolean check = false;
		if(chonHan.getDate()==null) {
			JOptionPane.showMessageDialog(rootPane, "Chọn hạn mới!");
		}else check = true;
		if(check) {
			int a = JOptionPane.showConfirmDialog(rootPane, "Bạn chắc chắn gia hạn?");
			if(a==0) {
				LocalDate ngay = java.time.LocalDate.now();
	            int n = ngay.getDayOfMonth();
	            int thang = ngay.getMonthValue()-1;
	            int nam = ngay.getYear()-1900;
	            Date ngh = new Date(nam, thang, n);
	            Date nhh = utl.setDate(chonHan);
	            
	            if(utl.checkDate(nhh, ngh)) {
	            	b.setNgayGiaHan(ngh);
	            	b.setNgayHetHan(nhh);
	            	controller.giaHan(b);
	            	JOptionPane.showMessageDialog(rootPane, "Gia hạn thành công!");
	            	dispose();
	            }else JOptionPane.showMessageDialog(rootPane, "Ngày hết hạn mới không hợp lệ!");
	            
			}
			
		}
	}
}
