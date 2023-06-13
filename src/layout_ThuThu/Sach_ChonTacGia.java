package layout_ThuThu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.TacGiaController;
import index.Table;
import model.TACGIA;
import sql.ConnectSQL;
import index.InputField;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import index.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Sach_ChonTacGia extends JDialog {

	private final JPanel contentPanel = new JPanel();
	ArrayList<TACGIA> tgL;
	ArrayList<TACGIA> tmp_tgL;
	ArrayList<TACGIA> sach_tgL;
	ArrayList<TACGIA> tmp_sach_tgL;
	Table tableSach_tgL;
	Table tableTGL;
	Sach_Them st;
	Sach_Sua ss;
	ConnectSQL c;
	TacGiaController controller;
	InputField txtTimKiem;
	
	public Sach_ChonTacGia(ConnectSQL cc,Sach_Them st,Sach_Sua ss) {
		c=cc;
		controller = new TacGiaController(c);
		this.st=st;
		this.ss=ss;
		if(ss!=null) {
			sach_tgL = ss.tgL;
		}else sach_tgL = new ArrayList<>();
		tgL = controller.xemTT();
		tmp_tgL = new ArrayList<>();
		tmp_sach_tgL = new ArrayList<>();
		
		setBounds(100, 100, 600, 425);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(321, 97, 253, 239);
		contentPanel.add(scrollPane_1);
		
		tableTGL = new Table();
		tableTGL.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_1.setViewportView(tableTGL);
		tableTGL.setModel(new DefaultTableModel(
				new Object[][] {},
				new String[] {
						"Mã tác giả","Tên tác giả"
				}
				));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 97, 222, 239);
		contentPanel.add(scrollPane);
		
		tableSach_tgL = new Table();
		tableSach_tgL.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tableSach_tgL);
		tableSach_tgL.setModel(new DefaultTableModel(
				new Object[][] {},
				new String[] {
						"Mã tác giả","Tên tác giả"
				}
				));
		
		txtTimKiem = new InputField("Tìm kiếm");
		txtTimKiem.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtTimKiem.setBounds(321, 51, 200, 43);
		contentPanel.add(txtTimKiem);
		
		JLabel lblNewLabel = new JLabel("Chọn tác giả");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.MAGENTA);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 11, 564, 24);
		contentPanel.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(96, 43, 371, 10);
		contentPanel.add(separator);
		
		JButton btnTimKiem = new JButton();
		btnTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timKiem();
			}
		});
		btnTimKiem.setIcon(new ImageIcon("Resource/Search32.png"));
		btnTimKiem.setBounds(531, 51, 43, 43);
		contentPanel.add(btnTimKiem);
		
		Button btnUpdate = new Button("");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
			}
		});
		btnUpdate.setText("O");
		btnUpdate.setBounds(242, 110, 69, 25);
		contentPanel.add(btnUpdate);
		
		Button btnUpdate_1 = new Button("Thêm");
		btnUpdate_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TacGia_Them f = new TacGia_Them(c);
				f.setVisible(true);
			}
		});
		btnUpdate_1.setText("+");
		btnUpdate_1.setBounds(242, 146, 69, 25);
		contentPanel.add(btnUpdate_1);
		
		Button btnXoa_1 = new Button("Chọn");
		btnXoa_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chon();
			}
		});
		btnXoa_1.setText("<<");
		btnXoa_1.setBounds(242, 182, 69, 25);
		contentPanel.add(btnXoa_1);
		
		Button btnXoa = new Button("Hủy");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoa();
			}
		});
		btnXoa.setText(">>");
		btnXoa.setBounds(242, 218, 69, 25);
		contentPanel.add(btnXoa);
		
		JLabel lblNewLabel_1 = new JLabel("Tác giả đã chọn");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.MAGENTA);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_1.setBounds(10, 70, 222, 24);
		contentPanel.add(lblNewLabel_1);
		
		Button btnUpdate_2 = new Button("Hủy");
		btnUpdate_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnUpdate_2.setBounds(339, 347, 104, 25);
		contentPanel.add(btnUpdate_2);
		
		Button btnUpdate_3 = new Button("Xác nhận");
		btnUpdate_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xacNhan();
			}
		});
		btnUpdate_3.setBounds(111, 347, 104, 25);
		contentPanel.add(btnUpdate_3);
		
		Button btnUpdate_3_1 = new Button("Quay lại");
		btnUpdate_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setItem();
			}
		});
		btnUpdate_3_1.setBounds(225, 347, 104, 25);
		contentPanel.add(btnUpdate_3_1);
		setItem();
	}
	
	/*
	 * Event
	 * */
	void setItem() {
		tmp_tgL.clear();
		tmp_sach_tgL.clear();
		for (int i = 0; i < sach_tgL.size(); i++) {
			tmp_sach_tgL.add(sach_tgL.get(i));
		}
		for (int i = 0; i < tgL.size(); i++) {
			tmp_tgL.add(tgL.get(i));
		}
		
		for (int i = 0; i < tmp_tgL.size(); i++) {
			for(int j = 0;j<sach_tgL.size();j++) {
				if((tmp_tgL.get(i).getMaTacGia()==(sach_tgL.get(j).getMaTacGia()))) tmp_tgL.remove(i);
			}
		}
		hienThiTable(tableSach_tgL, tmp_sach_tgL);
		hienThiTable(tableTGL, tmp_tgL);
	}
	
	
	void update() {
		tgL = controller.xemTT();
		tmp_tgL.clear();
		for (int i = 0; i < tgL.size(); i++) {
			tmp_tgL.add(tgL.get(i));
		}
		setTGL(tmp_tgL, tmp_sach_tgL);
		hienThiTable(tableTGL, tmp_tgL);
	}
	
	void hienThiTable(Table table, ArrayList<TACGIA> l) {
		table.setModel(new DefaultTableModel(
				new Object[][] {},
				new String[] {
						"Mã tác giả","Tên tác giả"
				}
				));
		DefaultTableModel mta = (DefaultTableModel)table.getModel();
		for(int i = 0;i<l.size();i++) {
			mta.addRow(new Object[] {
					l.get(i).getMaTacGia(),l.get(i).getTenTacGia()
			});
		}
	}
	
	void xacNhan() {
		int a = JOptionPane.showConfirmDialog(rootPane, "Bạn có chắc chắn?");
		if(a==0) {
			if(st!=null) {
				st.tgL = tmp_sach_tgL;
				st.s.setTacgiaL(st.tgL);
				st.txtTacGia.setText(st.s.getTenTacgiaL());
			}else {
				ss.tgL = tmp_sach_tgL;
				ss.s.setTacgiaL(ss.tgL);
				ss.txtTacGia.setText(ss.s.getTenTacgiaL());
			}
			dispose();
		}
	}
	
	void timKiem() {
		String str = txtTimKiem.getText();
		if(str.isBlank()) {
			hienThiTable(tableTGL,tmp_tgL);
		}else {
			try {
				TACGIA t = new TACGIA();
				t.setMaTacGia(Integer.parseInt(str));
				ArrayList<TACGIA> l = new ArrayList<>();
				l.add(controller.timTacGia(t));
				setTGL(l, tmp_sach_tgL);
				hienThiTable(tableSach_tgL, tmp_sach_tgL);
				hienThiTable(tableTGL,l);
			} catch (Exception e) {
				ArrayList<TACGIA> l = controller.timKiem(str);
				setTGL(l, tmp_sach_tgL);
				hienThiTable(tableSach_tgL, tmp_sach_tgL);
				hienThiTable(tableTGL,l);
			}
		}
	}
	
	void chon() {
		DefaultTableModel model = (DefaultTableModel)tableTGL.getModel(),smodel = (DefaultTableModel)tableSach_tgL.getModel();
		int i = tableTGL.getSelectedRow();
		if(i>=0) {
			TACGIA t_tmp = new TACGIA((Integer)model.getValueAt(i, 0), (String)model.getValueAt(i, 1));
			for (int j = 0; j < tmp_tgL.size(); j++) {
				if (tmp_tgL.get(j).getMaTacGia().equals(t_tmp.getMaTacGia())) {
					tmp_tgL.remove(j);
					break;
				}
			}
			model.removeRow(i);
			
			smodel.addRow(new Object[] {
					t_tmp.getMaTacGia(),t_tmp.getTenTacGia()
			});
			tmp_sach_tgL.add(t_tmp);
		}
	}
	
	void xoa() {
		DefaultTableModel model = (DefaultTableModel)tableTGL.getModel(),smodel = (DefaultTableModel)tableSach_tgL.getModel();
		int i = tableSach_tgL.getSelectedRow();
		if(i>=0) {
			TACGIA t_tmp = new TACGIA((Integer)smodel.getValueAt(i, 0), (String)smodel.getValueAt(i, 1));
			smodel.removeRow(i);
			for (int j = 0; j < tmp_sach_tgL.size(); j++) {
				if (tmp_sach_tgL.get(j).getMaTacGia().equals(t_tmp.getMaTacGia())) {
					tmp_sach_tgL.remove(j);
					break;
				}
			}
			model.addRow(new Object[] {
					t_tmp.getMaTacGia(),t_tmp.getTenTacGia()
			});
			tgL.add(t_tmp);
		}
	}
	
	void setTGL(ArrayList<TACGIA> l,ArrayList<TACGIA> sl) {
		for (int i = 0; i < l.size(); i++) {
			for(int j = 0;j<sl.size();j++) {
				if((l.get(i).getMaTacGia()==(sl.get(j).getMaTacGia()))) l.remove(i);
			}
		}
	}
}
