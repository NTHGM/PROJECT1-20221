package layout_ThuThu;

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
import model.THEMUONTRA;
import model.THUTHU;
import sql.ConnectSQL;
import sql.Utility;
import index.Button;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.awt.event.ActionEvent;

public class TheMuonTra_Them extends JDialog {
	private InputField txtDatCoc;
	private InputField txtMaBanDoc;
	private InputField txtMaSach;
	private InputField txtGhiChu;
	InputField txtMaThuThu;
	JDateChooser txtNgayTra;
	Table table_Sach, table_BanDoc;
	THUTHU t;
	TheMuonTraController controller;
	SACH s;
	BANDOC b;
	SachController sC;
	boolean checkS = false;
	boolean checkBD = false;
	LocalDate ngayHienTai = java.time.LocalDate.now();
	Date ngayMuonS;
	Utility utl;
	/**
	 * Create the dialog.
	 * @param t 
	 * @param c 
	 */
	public TheMuonTra_Them(ConnectSQL c, THUTHU t) {
		setBounds(100, 100, 450, 635);
		getContentPane().setLayout(null);
		this.t=t;
		utl = new Utility();
		sC = new SachController(c);
		controller = new TheMuonTraController(c);
		s = new SACH();
		b = new BANDOC();
		int ngay = ngayHienTai.getDayOfMonth();
        int thang = ngayHienTai.getMonthValue()-1;
        int nam = ngayHienTai.getYear()-1900;
        ngayMuonS = new Date(nam, thang, ngay);
        
	/*
	 * Label
	 * */

		JLabel lblThmNhXut = new JLabel("Thêm thẻ mượn trả");
		lblThmNhXut.setHorizontalAlignment(SwingConstants.CENTER);
		lblThmNhXut.setForeground(Color.MAGENTA);
		lblThmNhXut.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblThmNhXut.setBounds(10, 11, 414, 35);
		getContentPane().add(lblThmNhXut);
	
		JSeparator separator = new JSeparator();
		separator.setBounds(57, 53, 336, 10);
		getContentPane().add(separator);
		
		Label lblNewLabel_3_2_1_1 = new Label("Ngày phải trả");
		lblNewLabel_3_2_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_2_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_3_2_1_1.setBounds(248, 372, 145, 24);
		getContentPane().add(lblNewLabel_3_2_1_1);
		
	/*
	 * Text
	 * */
		
		txtMaSach = new InputField("Mã sách");
		txtMaSach.setBounds(10, 115, 288, 47);
		getContentPane().add(txtMaSach);
		
		txtMaBanDoc = new InputField("Mã bạn đọc");
		txtMaBanDoc.setBounds(10, 250, 288, 47);
		getContentPane().add(txtMaBanDoc);
		
		txtDatCoc = new InputField("Đặt cọc");
		txtDatCoc.setBounds(10, 438, 414, 47);
		getContentPane().add(txtDatCoc);
		
		txtGhiChu = new InputField("Ghi chú");
		txtGhiChu.setBounds(10, 496, 414, 47);
		getContentPane().add(txtGhiChu);
		
		txtNgayTra = new JDateChooser();
		txtNgayTra.setBounds(249, 397, 145, 30);
		getContentPane().add(txtNgayTra);
		
		txtMaThuThu = new InputField("Mã thủ thư");
		txtMaThuThu.setText(t.getMaThuThu()+"");
		txtMaThuThu.setEditable(false);
		txtMaThuThu.setBounds(10, 57, 288, 47);
		getContentPane().add(txtMaThuThu);
		
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
		scrollPane_Sach.setBounds(10, 173, 414, 53);
		getContentPane().add(scrollPane_Sach);
		
		table_BanDoc = new Table();
		table_BanDoc.setModel(new DefaultTableModel(
				new Object[][] {
					
				}, 
				new String[] {
					"Mã bạn đọc", "Tên", "Ngày gia hạn", "Ngày hết hạn"
				}));
		ScrollPane scrollPane_BanDoc = new ScrollPane(table_BanDoc);
		scrollPane_BanDoc.setBounds(10, 308, 414, 53);
		getContentPane().add(scrollPane_BanDoc);
		
	/*
	 * Button
	 * */	
		
		Button btnThm = new Button("Thêm");
		btnThm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xacNhan();
			}
		});
		btnThm.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnThm.setBounds(57, 554, 120, 35);
		getContentPane().add(btnThm);
		
		Button btnng = new Button("Đóng");
		btnng.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnng.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnng.setBounds(248, 554, 127, 35);
		getContentPane().add(btnng);
		
		Button btnXacNhan_BanDoc = new Button("Xác nhận");
		btnXacNhan_BanDoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xacNhanBanDoc();
			}
		});
		btnXacNhan_BanDoc.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnXacNhan_BanDoc.setBounds(308, 267, 98, 24);
		getContentPane().add(btnXacNhan_BanDoc);
		
		Button btnXacNhan_Sach = new Button("Xác nhận");
		btnXacNhan_Sach.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xacNhanSach();
			}
		});
		btnXacNhan_Sach.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnXacNhan_Sach.setBounds(308, 132, 98, 24);
		getContentPane().add(btnXacNhan_Sach);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(89, 237, 301, 2);
		getContentPane().add(separator_1);
		
		InputField ngayMuon = new InputField("Ngày mượn");
		ngayMuon.setText(ngay+"/"+(thang+1)+"/"+(nam+1900));
		ngayMuon.setEditable(false);
		ngayMuon.setBounds(10, 380, 209, 47);
		getContentPane().add(ngayMuon);
			
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
	
	void xacNhanBanDoc() {
		table_BanDoc.setModel(new DefaultTableModel(
				new Object[][] {
					
				}, 
				new String[] {
					"Tên", "Ngày gia hạn", "Ngày hết hạn"
				}));
		DefaultTableModel mta = (DefaultTableModel)table_BanDoc.getModel();
		try {
			BANDOC tmp = new BANDOC();
			tmp.setMaBanDoc(Integer.parseInt(txtMaBanDoc.getText()));
			b = controller.timBanDoc(tmp);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(rootPane, "Mã bạn đọc không hợp lệ!");
		}
		if(b.getMaBanDoc()!=null) {
			mta.addRow(new Object[] {
					b.getTenBanDoc(),b.getNgayGiaHan(),b.getNgayHetHan()
			});
		}
	}
	
	void xacNhan() {
		xacNhanSach();
		xacNhanBanDoc();
		boolean check =false;
		if(s.getMaSach()==null) {
			JOptionPane.showMessageDialog(rootPane, "Chọn sách!");
		}else if(b.getMaBanDoc()==null) {
			JOptionPane.showMessageDialog(rootPane, "Chọn bạn đọc!");
		}else if(!checkS) {
			JOptionPane.showMessageDialog(rootPane, "Sách đã được mượn hết!");
		}else if(txtDatCoc.getText().isBlank()) {
			JOptionPane.showMessageDialog(rootPane, "Nhập tiền cọc!");
		}else if(txtNgayTra.getDate()==null) {
			JOptionPane.showMessageDialog(rootPane, "Chọn phải ngày trả!");
		}else if(b.getNgayHetHan()==null) {
			JOptionPane.showMessageDialog(rootPane, "Hãy gia hạn bạn đọc!");
		}else check = true;
		float tienCoc=-1;
		try {
			tienCoc = Float.parseFloat(txtDatCoc.getText());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(rootPane,"Tiền cọc không hợp lệ!");
			check =false;
		}
		if(check) {
			if(utl.checkDate(b.getNgayHetHan(), ngayMuonS)) {
				Date ngayTra = utl.setDate(txtNgayTra);
				if(utl.checkDate(ngayTra, ngayMuonS)) {
					String ghiChu = txtGhiChu.getText();
					THEMUONTRA tmt = new THEMUONTRA();
					tmt.setBanDoc(b);
					tmt.setSach(s);
					tmt.setGhiChu(ghiChu);
					tmt.setNgayMuon(ngayMuonS);
					tmt.setNgayTra(ngayTra);
					tmt.setThuThu(t);
					tmt.setTienCoc(tienCoc);
					if(controller.them(tmt)) {
						JOptionPane.showMessageDialog(rootPane,"Thêm thành công!");
						dispose();
					}else JOptionPane.showMessageDialog(rootPane,"Thêm thất bại!");
				}else JOptionPane.showMessageDialog(rootPane,"Ngày phải trả không hợp lệ!");
			}else {
				JOptionPane.showMessageDialog(rootPane,"Bạn đọc đã hết hạn!");
			}
		}
		
	}
}
