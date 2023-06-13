package layout_ThuThu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import index.ScrollPane;
import index.Table;
import model.NXB;
import model.THELOAI;
import sql.ConnectSQL;
import index.InputField;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import index.Button;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import controller.NXBController;
import controller.TheLoaiController;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Sach_ChonTheLoai extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	TheLoaiController controller;
	ArrayList<THELOAI> list;
	InputField txtTimKiem;
	Table table;
	THELOAI t;
	Sach_Them st;
	Sach_Sua ss;
	ConnectSQL c;
	
	/**
	 * Create the dialog.
	 */
	public Sach_ChonTheLoai(ConnectSQL c,Sach_Them st,Sach_Sua ss) {
		this.st =st;
		this.ss=ss;
		this.c=c;
		controller = new TheLoaiController(c);
		setBounds(100, 100, 450, 420);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		txtTimKiem = new InputField("Tìm kiếm");
		txtTimKiem.setBounds(8, 66, 363, 43);
		txtTimKiem.setFont(new Font("Tahoma", Font.BOLD, 10));
		contentPanel.add(txtTimKiem);
		
		JLabel lblNewLabel = new JLabel("Chọn nhà xuất bản");
		lblNewLabel.setBounds(8, 19, 416, 24);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.MAGENTA);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		contentPanel.add(lblNewLabel);
		
		Button btnUpdate = new Button("Làm mới");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
			}
		});
		btnUpdate.setBounds(8, 345, 98, 25);
		contentPanel.add(btnUpdate);
		
		Button btnXoa = new Button("Hủy");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnXoa.setBounds(326, 345, 98, 25);
		contentPanel.add(btnXoa);
		
		JButton btnTimKiem = new JButton();
		btnTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timKiem();
			}
		});
		btnTimKiem.setIcon(new ImageIcon("Resource/Search32.png"));
		btnTimKiem.setBounds(381, 66, 43, 43);
		contentPanel.add(btnTimKiem);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(8, 119, 416, 215);
		contentPanel.add(scrollPane);
		
		table = new Table();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Mã thể loại","Tên thể loại","Số sách"
			}
		));
		scrollPane.setViewportView(table);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(18, 45, 392, 10);
		contentPanel.add(separator);
		
		Button btnXoa_1 = new Button("Chọn");
		btnXoa_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chon();
			}
		});
		btnXoa_1.setBounds(218, 345, 98, 25);
		contentPanel.add(btnXoa_1);
		
		Button btnUpdate_1 = new Button("Thêm");
		btnUpdate_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				them();
			}
		});
		btnUpdate_1.setBounds(116, 345, 98, 25);
		contentPanel.add(btnUpdate_1);
		
		update();
	}
	
	void update() {
		list = controller.xemTT();
		hienThiTable(list);
	}
	
	void hienThiTable(ArrayList<THELOAI> l) {
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Mã thể loại", "Tên thể loại","Số sách"
				}
			) {
				boolean[] columnEditables = new boolean[] {
					false, false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
		DefaultTableModel mta = (DefaultTableModel) table.getModel();
		for (int i = 0; i < l.size(); i++) {
			THELOAI t = l.get(i);
			mta.addRow(new Object[] {
					t.getMaTheLoai(),t.getTenTheLoai(),t.getSl()
			});
		}
	}
	
	void timKiem() {
		String str = txtTimKiem.getText();
		if(str.isBlank()) {
			hienThiTable(list);
		}else {
			try {
				THELOAI t = new THELOAI();
				t.setMaTheLoai(Integer.parseInt(str));
				ArrayList<THELOAI> l = new ArrayList<>();
				l.add(controller.timTheLoai(t));
				hienThiTable(l);
			} catch (Exception e) {
				ArrayList<THELOAI> l = controller.timKiem(str);
				hienThiTable(l);
			}
		}
	}
	
	void chon() {
		DefaultTableModel mta = (DefaultTableModel) table.getModel();
		int i  = table.getSelectedRow();
		if(i>=0) {
				int j = (Integer) mta.getValueAt(i, 0);
				t = new THELOAI(j, null);
				t = controller.timTheLoai(t);
				if(st!=null) {
					st.theLoai = t;
					st.theLoaiBoxSelect.setText(t.getTenTheLoai());
				}else {
					ss.theLoai = t;
					ss.theLoaiBoxSelect.setText(t.getTenTheLoai());
				}
				dispose();
				
		}else JOptionPane.showMessageDialog(this, "Chọn thể loại!");
	}
	
	void them() {
		TheLoai_Them f = new TheLoai_Them(c);
		f.setVisible(true);
	}
}
