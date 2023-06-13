package layout_ThuThu;

import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import controller.BanDocController;
import controller.TheMuonTraController;
import index.Button;
import index.ComboBox;
import index.InputField;
import index.Label;
import index.ScrollPane;
import index.Table;
import model.BANDOC;
import model.THEMUONTRA;
import sql.ConnectSQL;
import sql.Utility;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;

public class BanDoc_ThongTin extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	InputField txtMaBanDoc;
	InputField txtUsername;
	BanDocController aC;
	TheMuonTraController tC;
	ArrayList<THEMUONTRA> list;
	BANDOC b;
	private InputField txtTen;
	private InputField txtDiaChi, txtNgaySinh,txtGioiTinh, txtNgayGiaHan, txtNgayHetHan;
	Utility utl;
	Table table;
	
	public BanDoc_ThongTin(ConnectSQL c,BANDOC bb) {
		aC = new BanDocController(c);
		tC = new TheMuonTraController(c);
		b = aC.timBanDoc(bb);
		list = tC.xemDS_BanDoc(b);
		utl = new Utility();
		
		setBounds(100, 100, 650, 700);
		getContentPane().setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
	/*
	 * Label
	 * */
		Label lblNewLabel_3 = new Label("THÔNG TIN BẠN ĐỌC");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(231, 11, 185, 24);
		contentPanel.add(lblNewLabel_3);
		
		Label lblNewLabel_3_2 = new Label("Thông tin chung");
		lblNewLabel_3_2.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_3_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_2.setBounds(10, 71, 185, 24);
		contentPanel.add(lblNewLabel_3_2);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(199, 46, 275, 16);
		contentPanel.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 584, 614, 17);
		contentPanel.add(separator_1);
		
		Label lblNewLabel_3_2_1 = new Label("Lịch sử mượn trả");
		lblNewLabel_3_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_2_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_3_2_1.setBounds(10, 320, 185, 24);
		contentPanel.add(lblNewLabel_3_2_1);
		
	/*
	 * Table
	 * */	
	
		table = new Table();
		table.setModel(new DefaultTableModel(
				new Object[][] {},
				new String[] {
						"Mã mượn trả","Tên sách","Ngày mượn","Ngày trả","Trạng thái"
				}
				));
		ScrollPane scrollPane = new ScrollPane(table);
		scrollPane.setBounds(16, 359, 608, 214);
		contentPanel.add(scrollPane);
		
	/*
	 * Text
	 * */	
		
		txtTen = new InputField("Họ và tên");
		txtTen.setText(b.getTenBanDoc());
		txtTen.setEditable(false);
		txtTen.setColumns(10);
		txtTen.setBounds(16, 106, 284, 50);
		contentPanel.add(txtTen);
		
		txtDiaChi = new InputField("Địa chỉ");
		txtDiaChi.setText(b.getDiaChi());
		txtDiaChi.setEditable(false);
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(20, 186, 435, 50);
		contentPanel.add(txtDiaChi);
		
		txtMaBanDoc = new InputField("Mã bạn đọc");
		txtMaBanDoc.setText(""+b.getMaBanDoc());
		txtMaBanDoc.setEditable(false);
		txtMaBanDoc.setColumns(10);
		txtMaBanDoc.setBounds(468, 186, 156, 50);
		contentPanel.add(txtMaBanDoc);
		
		txtUsername = new InputField("Tài khoản");
		txtUsername.setText(b.getUserName());
		txtUsername.setEditable(false);
		txtUsername.setColumns(10);
		txtUsername.setBounds(16, 247, 284, 50);
		contentPanel.add(txtUsername);
		
		txtNgaySinh = new InputField("Ngày sinh");
		txtNgaySinh.setText(b.getBieudienNgaySinh());
		txtNgaySinh.setEditable(false);
		txtNgaySinh.setColumns(10);
		txtNgaySinh.setBounds(468, 106, 156, 50);
		contentPanel.add(txtNgaySinh);
		

		txtGioiTinh = new InputField("Giới tính");
		String gt = "Nam";
		if(b.getGioiTinhIndex()==1) gt = "Nữ";
		else if(b.getGioiTinhIndex()==2) gt = "Khác";
		txtGioiTinh.setText(gt);
		txtGioiTinh.setEditable(false);
		txtGioiTinh.setColumns(10);
		txtGioiTinh.setBounds(318, 106, 137, 50);
		contentPanel.add(txtGioiTinh);
		
		txtNgayGiaHan = new InputField("Ngày gia hạn");
		txtNgayGiaHan.setText(b.getBieudienNgayGiaHan());
		txtNgayGiaHan.setEditable(false);
		txtNgayGiaHan.setColumns(10);
		txtNgayGiaHan.setBounds(318, 247, 136, 50);
		contentPanel.add(txtNgayGiaHan);
		
		txtNgayHetHan = new InputField("Ngày hết hạn");
		txtNgayHetHan.setText(b.getBieudienNgayHetHan());
		txtNgayHetHan.setEditable(false);
		txtNgayHetHan.setColumns(10);
		txtNgayHetHan.setBounds(468, 247, 156, 50);
		contentPanel.add(txtNgayHetHan);
	
	/*
	 * Button
	 * */	
		
		Button btnCancel = new Button("Close");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(264, 611, 115, 39);
		contentPanel.add(btnCancel);
		hienThiTable(list);
	}
	void hienThiTable(ArrayList<THEMUONTRA> l) {
		table.setModel(new DefaultTableModel(
				new Object[][] {},
				new String[] {
						"Mã mượn trả","Tên sách","Ngày mượn","Ngày phải trả","Trạng thái"
				}
				));
		DefaultTableModel mta = (DefaultTableModel)table.getModel();
		for (int i = 0; i < l.size(); i++) {
			mta.addRow(new Object[] {
					l.get(i).getMaMuonTra(),l.get(i).getSach().getTenSach(),l.get(i).getBieuDienNgayMuon(),l.get(i).getBieuDienNgayTra(),
					l.get(i).getTrangThai()
			});
		}
	}
}
