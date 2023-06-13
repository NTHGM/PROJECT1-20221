package layout_ThuThu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import controller.SachController;
import index.Button;
import index.InputField;
import index.ScrollPane;
import index.Table;
import model.NXB;
import model.SACH;
import sql.ConnectSQL;

public class Sach_Panel extends JPanel {
	private static final long serialVersionUID = 1L;
	Table table;
	ConnectSQL c;
	InputField txtTimKiem;
	ArrayList<SACH> list;
	SachController controller;
	
	/**
	 * Create the panel.
	 */
	public Sach_Panel(ConnectSQL n) {
		c=n;
		controller = new SachController(c);
		setLayout(null);
		setBounds(100, 100, 800, 500);
		
	/*
	 * Label
	 * */
		JSeparator separator = new JSeparator();
		separator.setBounds(212, 45, 400, 10);
		add(separator);
		
		JLabel lblNewLabel = new JLabel("QUẢN LÝ SÁCH");
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
				new Object[][] {},
				new String[] {
						"Mã sách","Tên sách","Tác giả","Nhà xuất bản","Thể loại"
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
		
		Button btnThem = new Button("");
		btnThem.setText("Thêm sách mới");
		btnThem.setBounds(10, 190, 192, 35);
		add(btnThem);
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sach_Them themSach = new Sach_Them(c);
				themSach.setVisible(true);
			}
		});
		
		Button btnChinhSua = new Button("Chỉnh sửa");
		btnChinhSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chinhSua();
			}
		});
		btnChinhSua.setBounds(10, 236, 192, 35);
		add(btnChinhSua);
		
		Button btnXoa = new Button("");
		btnXoa.setText("Xóa");
		btnXoa.setBounds(10, 284, 192, 35);
		add(btnXoa);
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoa();
			}
		});
		
		Button btnChiTiet = new Button("Thông tin chi tiết");
		btnChiTiet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chiTiet();
			}
		});
		btnChiTiet.setBounds(10, 329, 192, 35);
		add(btnChiTiet);
		
		Button btnThemQuyen = new Button("Thêm quyển");
		btnThemQuyen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				themQuyen();
			}
		});
		btnThemQuyen.setBounds(10, 375, 192, 35);
		add(btnThemQuyen);
		
		JButton btnTimKiem = new JButton();
		btnTimKiem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				timKiem();
			}
		});
		btnTimKiem.setIcon(new ImageIcon("Resource/Search32.png"));
		btnTimKiem.setBounds(752, 74, 38, 35);
		add(btnTimKiem);
		update();
	}
	
	
	/*
	 * Event
	 * */
	void update() {
		list = controller.xemTT();
		hienThiTable(list);
	}
	
	void hienThiTable(ArrayList<SACH> l) {
		table.setModel(new DefaultTableModel(
				new Object[][] {},
				new String[] {
						"Mã sách","Tên sách","Tác giả","Nhà xuất bản","Thể loại"
				}
				));
		DefaultTableModel mta = (DefaultTableModel)table.getModel();
		for (int i = 0; i < l.size(); i++) {
			SACH s = l.get(i);
			mta.addRow(new Object[] {
					s.getMaSach(),s.getTenSach(),s.getTenTacgiaL(),s.getTenNXB(),s.getTenTheLoai()
			});
			
		}
		
	}
	
	void chinhSua() {
		DefaultTableModel mta = (DefaultTableModel) table.getModel();
		int i = table.getSelectedRow();
		if(i>=0) {
			int j = (int)mta.getValueAt(i, 0);
			SACH s = new SACH();
			s.setMaSach(j);
			Sach_Sua f = new Sach_Sua(c,s);
			f.setVisible(true);
		}else JOptionPane.showMessageDialog(getRootPane(), "Chọn sách cần sửa!");
		
	}
	
	void xoa() {
		DefaultTableModel mta = (DefaultTableModel) table.getModel();
		int i = table.getSelectedRow();
		if(i>=0) {
			int a = JOptionPane.showConfirmDialog(getRootPane(), "Xóa sách sẽ không thể hoàn tác\nBạn có chắc chắn?");
			if(a==0) {
				Integer ma = (int)mta.getValueAt(i, 0);
				SACH s= new SACH();
				s.setMaSach(ma);
				controller.xoaSach_TheMuonTra(s);
				controller.xoaTS_maSach(s);
				controller.xoaSach(s);
				JOptionPane.showMessageDialog(getRootPane(), "Xóa thành công!");
				update();
			}
		}else JOptionPane.showMessageDialog(getRootPane(), "Chọn sách cần xóa!");
	}
	
	void chiTiet() {
		DefaultTableModel mta = (DefaultTableModel) table.getModel();
		int i = table.getSelectedRow();
		if(i>=0) {
			Integer ma = (int)mta.getValueAt(i, 0);
			SACH s= new SACH();
			s.setMaSach(ma);
			Sach_ThongTin f = new Sach_ThongTin(c,s);
			f.setVisible(true);
		}else JOptionPane.showMessageDialog(getRootPane(), "Chọn sách cần xem chi tiết!");
	}
	
	void timKiem() {
		String str = txtTimKiem.getText();
		if(str.isBlank()) {
			hienThiTable(list);
		}else {
			try {
				SACH t = new SACH();
				t.setMaSach(Integer.parseInt(str));
				ArrayList<SACH> l = new ArrayList<>();
				l.add(controller.timSach(t));
				hienThiTable(l);
			} catch (Exception e) {
				ArrayList<SACH> l = controller.timKiem(str);
				hienThiTable(l);
			}
		}
	}
	
	void themQuyen() {
		DefaultTableModel mta = (DefaultTableModel) table.getModel();
		int i = table.getSelectedRow();
		if(i>=0) {
			int j = (int)mta.getValueAt(i, 0);
			SACH s = new SACH();
			s.setMaSach(j);
			Sach_ThemQuyen f = new Sach_ThemQuyen(c,s);
			f.setVisible(true);
		}else JOptionPane.showMessageDialog(getRootPane(), "Chọn sách cần thêm quyển!");
	}
}
