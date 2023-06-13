package layout_BanDoc;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import index.InputField;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import com.toedter.calendar.JDateChooser;

import controller.SachController;
import controller.TheMuonTraController;
import index.Label;
import index.ScrollPane;
import index.Table;
import model.BANDOC;
import model.SACH;
import sql.ConnectSQL;
import index.Button;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TheMuonTra_MuonSach extends JDialog {
	private InputField txtMaSach;
	InputField txtBanDoc;
	JDateChooser txtNgayTra;
	Table table_Sach;
	ConnectSQL c;
	TheMuonTraController controller;
	SachController sC;
	BANDOC b;
	boolean checkS=false;
	SACH s;
	/**
	 * Create the dialog.
	 */
	public TheMuonTra_MuonSach(ConnectSQL cc,BANDOC b) {
		c=cc;
		s = new SACH();
		this.b=b;
		sC = new SachController(c);
		controller = new TheMuonTraController(c);
		setBounds(100, 100, 450, 425);
		getContentPane().setLayout(null);
		
	
	/*
	 * Label
	 * */

		JLabel lblThmNhXut = new JLabel("Mượn sách");
		lblThmNhXut.setHorizontalAlignment(SwingConstants.CENTER);
		lblThmNhXut.setForeground(Color.MAGENTA);
		lblThmNhXut.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblThmNhXut.setBounds(10, 11, 414, 35);
		getContentPane().add(lblThmNhXut);
	
		JSeparator separator = new JSeparator();
		separator.setBounds(57, 53, 336, 10);
		getContentPane().add(separator);
		
		Label lblNewLabel_3_2_1_1 = new Label("Ngày trả");
		lblNewLabel_3_2_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_2_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_3_2_1_1.setBounds(248, 242, 145, 24);
		getContentPane().add(lblNewLabel_3_2_1_1);
		
	/*
	 * Text
	 * */
		
		txtMaSach = new InputField("Mã sách");
		txtMaSach.setBounds(10, 92, 120, 47);
		getContentPane().add(txtMaSach);
		
		txtNgayTra = new JDateChooser();
		txtNgayTra.setBounds(248, 277, 145, 30);
		getContentPane().add(txtNgayTra);
		
		txtBanDoc = new InputField("Mã bạn đọc");
		txtBanDoc.setText(b.getMaBanDoc()+"");
		txtBanDoc.setEditable(false);
		txtBanDoc.setBounds(154, 92, 129, 47);
		getContentPane().add(txtBanDoc);
		
	/*
	 * Table 
	 * */
		table_Sach = new Table();
		table_Sach.setModel(new DefaultTableModel(
				new Object[][] {
					
				}, 
				new String[] {
					"Mã sách", "Tên sách ", "Năm xuất bản", "Thể loại", "Nhà xuất bản", "Trạng thái"
				}));
		
		ScrollPane scrollPane_Sach = new ScrollPane(table_Sach);
		scrollPane_Sach.setBounds(10, 150, 414, 81);
		getContentPane().add(scrollPane_Sach);
		
		
	/*
	 * Button
	 * */	
		
		Button btnThm = new Button("Mượn sách");
		btnThm.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnThm.setBounds(57, 318, 120, 45);
		getContentPane().add(btnThm);
		
		Button btnng = new Button("Đóng");
		btnng.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnng.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnng.setBounds(241, 318, 127, 45);
		getContentPane().add(btnng);
		
		Button btnXacNhan_Sach = new Button("Xác nhận");
		btnXacNhan_Sach.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xacNhanSach();
			}
		});
		btnXacNhan_Sach.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnXacNhan_Sach.setBounds(312, 109, 98, 24);
		getContentPane().add(btnXacNhan_Sach);
		
		InputField txtMaSach_1 = new InputField("Mã sách");
		txtMaSach_1.setEditable(false);
		txtMaSach_1.setBounds(10, 260, 228, 47);
		getContentPane().add(txtMaSach_1);
		
		
	}
	
	void xacNhanSach() {
		table_Sach.setModel(new DefaultTableModel(
				new Object[][] {
					
				}, 
				new String[] {
					"Tên sách ", "Năm xuất bản", "Thể loại", "Nhà xuất bản", "Tác giả","Trạng thái"
				}));
		DefaultTableModel mta = (DefaultTableModel)table_Sach.getModel();
		boolean check = true;
		SACH tmp = new SACH();
		try {
			tmp.setMaSach(Integer.parseInt(txtMaSach.getText()));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(rootPane, "Mã sách không hợp lệ!");
			check=false;
		}
		if(check) {
			s = controller.timSach(tmp);
			if(s.getMaSach()!=null) {
				int x = s.getSolg()-sC.soSachMuon(s);
				String str = "Hết";
				if(x>0) {
					checkS = true;
					str = "Còn";
				}
				mta.addRow(new Object[] {
					s.getTenSach(),s.getNamXuatBan(),s.getTenTheLoai(),s.getTenNXB(),s.getTenTacgiaL(),str
				});
			}
		}
	}
}