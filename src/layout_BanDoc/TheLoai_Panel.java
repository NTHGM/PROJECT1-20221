package layout_BanDoc;

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

import index.Button;
import index.InputField;
import index.ScrollPane;
import index.Table;
import model.TACGIA;
import model.THELOAI;
import sql.ConnectSQL;


import javax.swing.table.DefaultTableModel;

import controller.TheLoaiController;

public class TheLoai_Panel extends JPanel {

	private static final long serialVersionUID = 1L;
	Table table;
	
	ConnectSQL c;
	InputField txtTimKiem;
	TheLoaiController controller;
	ArrayList<THELOAI> list;
	/**
	 * Create the panel.
	 */
	public TheLoai_Panel(ConnectSQL n) {
		c=n;
		controller = new TheLoaiController(c);
		setLayout(null);
		setBounds(100, 100, 800, 500);
		
	/*
	 * Label
	 * */
		JSeparator separator = new JSeparator();
		separator.setBounds(212, 45, 400, 10);
		add(separator);
		
		JLabel lblNewLabel = new JLabel("THÔNG TIN THỂ LOẠI");
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
	
	
	

}
