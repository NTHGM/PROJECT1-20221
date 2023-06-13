package layout_ThuThu;

import java.awt.Color;
import java.awt.Font;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import index.Button;
import index.InputField;
import index.ScrollPane;
import index.Table;
import model.BANDOC;
import model.NXB;
import sql.ConnectSQL;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import controller.BanDocController;

public class BanDoc_Panel extends JPanel {

	/**
	 * Create the panel.
	 */
	private static final long serialVersionUID = 1L;
	Table table;

	ConnectSQL c;
	InputField txtTimKiem;
	BanDocController controller;
	ArrayList<BANDOC> list;
	/**
	 * Create the panel.
	 */
	public BanDoc_Panel(ConnectSQL n) {
		c=n;
		controller = new BanDocController(c);
		setLayout(null);
		setBounds(100, 100, 800, 500);
		
	/*
	 * Label
	 * */
		JSeparator separator = new JSeparator();
		separator.setBounds(212, 45, 400, 10);
		add(separator);
		
		JLabel lblNewLabel = new JLabel("QUẢN LÝ BẠN ĐỌC");
		lblNewLabel.setForeground(Color.magenta);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 10, 790, 35);
		add(lblNewLabel);
		
	/*
	 * Input
	 * */
		txtTimKiem = new InputField("Tìm kiếm");
		txtTimKiem.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtTimKiem.setBounds(212, 66, 523, 45);
		add(txtTimKiem);
		
	/*
	 * TABLE
	 */
		table = new Table();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"MÃ BẠN ĐỌC","TÊN","NGÀY GIA HẠN","NGÀY HẾT HẠN"
			}
		));
		
		ScrollPane scrollPane = new ScrollPane(table);
		scrollPane.setBounds(212, 136, 578, 353);
		add(scrollPane);
		
	/*
	 * BUTTON
	 * */
		
		Button btnUpdate = new Button("Làm mới");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
			}
		});
		
		btnUpdate.setBounds(10, 144, 192, 35);
		add(btnUpdate);
		
		Button btnThem = new Button("Thêm mới");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BanDoc_Them banDoc = new BanDoc_Them(c);
				banDoc.setVisible(true);
			}
		});
		btnThem.setBounds(10, 190, 192, 35);
		add(btnThem);
		
		
		Button btnChinhSua = new Button("Chỉnh sửa");
		btnChinhSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chinhSua();
			}
		});
		
		btnChinhSua.setBounds(10, 236, 192, 35);
		add(btnChinhSua);
		
		Button btnXoa = new Button("Xóa");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoa();
			}
		});
		btnXoa.setBounds(10, 284, 192, 35);
		add(btnXoa);
		
		Button btnChiTiet = new Button("Thông tin chi tiết");
		btnChiTiet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chiTiet();
			}
		});
		btnChiTiet.setBounds(10, 329, 192, 35);
		add(btnChiTiet);
		
		JButton btnTimKiem = new JButton();
		btnTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timKiem();
			}
		});
		btnTimKiem.setIcon(new ImageIcon("Resource/Search32.png"));
		btnTimKiem.setBounds(752, 74, 38, 35);
		add(btnTimKiem);
		
		Button btnGiaHan = new Button("Gia hạn");
		btnGiaHan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				giaHan();
			}
		});
		btnGiaHan.setBounds(10, 375, 192, 35);
		add(btnGiaHan);
		update();
	}
	
	
	/*
	 * Event
	 * */
	void update() {
		list = controller.xemtt();
		hienThiTable(list);
	}
	void hienThiTable(ArrayList<BANDOC> l) {
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"MÃ BẠN ĐỌC","TÊN","NGÀY GIA HẠN","NGÀY HẾT HẠN"
				}
			));
		DefaultTableModel mta =(DefaultTableModel)table.getModel();
		for(int i =0;i<l.size();i++) {
			mta.addRow(new Object[] {
					l.get(i).getMaBanDoc(),l.get(i).getTenBanDoc(),l.get(i).getBieudienNgayGiaHan(),l.get(i).getBieudienNgayHetHan()
			});
		}			
	}
	
	void chinhSua() {
		DefaultTableModel mta = (DefaultTableModel)table.getModel();
		int i =table.getSelectedRow();
		if(i>=0) {
			int str = (int) mta.getValueAt(i, 0);
			BANDOC b = new BANDOC();
			b.setMaBanDoc(str);
			BanDoc_ChinhSua f = new BanDoc_ChinhSua(c,b);
			f.setVisible(true);
		}else JOptionPane.showMessageDialog(this, "Chọn bạn đọc cần sửa!");
	}
	
	void xoa() {
		DefaultTableModel mta = (DefaultTableModel)table.getModel();
		int i =table.getSelectedRow();
		if(i>=0) {
			int a = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn xóa?");
			if(a==0) {
				int str = (int) mta.getValueAt(i, 0);
				BANDOC b = new BANDOC();
				b.setMaBanDoc(str);
				b.setUserName(controller.timBanDoc(b).getUserName());
				controller.xoaBanDoc(b);
				JOptionPane.showMessageDialog(this, "Xóa thành công!");
				update();
			}
		}else JOptionPane.showMessageDialog(this, "Chọn bạn đọc cần xóa!");
	}
	
	void chiTiet() {
		DefaultTableModel mta = (DefaultTableModel)table.getModel();
		int i =table.getSelectedRow();
		if(i>=0) {	
			int str = (int) mta.getValueAt(i, 0);
			BANDOC b = new BANDOC();
			b.setMaBanDoc(str);
			BanDoc_ThongTin f = new BanDoc_ThongTin(c,b);
			f.setVisible(true);
		}else JOptionPane.showMessageDialog(this, "Chọn bạn đọc cần xem!");
	}
	
	void giaHan() {
		DefaultTableModel mta = (DefaultTableModel)table.getModel();
		int i =table.getSelectedRow();
		if(i>=0) {	
			int str = (int) mta.getValueAt(i, 0);
			BANDOC b = new BANDOC();
			b.setMaBanDoc(str);
			BanDoc_GiaHan f = new BanDoc_GiaHan(c, b);
			f.setVisible(true);
		}else JOptionPane.showMessageDialog(this, "Chọn bạn đọc cần gia hạn!");
	}
	
	void timKiem() {
		String str = txtTimKiem.getText();
		if(str.isBlank()) {
			hienThiTable(list);
		}else {
			try {
				BANDOC t = new BANDOC();
				t.setMaBanDoc(Integer.parseInt(str));
				ArrayList<BANDOC> l = new ArrayList<>();
				l.add(controller.timBanDoc(t));
				hienThiTable(l);
			} catch (Exception e) {
				ArrayList<BANDOC> l = controller.timKiem(str);
				hienThiTable(l);
			}
		}		
	}
}
