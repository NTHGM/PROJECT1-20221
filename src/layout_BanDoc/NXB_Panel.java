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
import javax.swing.table.DefaultTableModel;

import controller.NXBController;
import index.Button;
import index.InputField;
import index.ScrollPane;
import index.Table;
import model.NXB;
import sql.ConnectSQL;

public class NXB_Panel extends JPanel {

	private static final long serialVersionUID = 1L;
	Table table;
	private int indexOfChucVu;
	ConnectSQL c;
	InputField txtTimKiem;
	NXBController controller;
	ArrayList<NXB> list;
	/**
	 * Create the panel.
	 */
	public NXB_Panel(ConnectSQL n) {
		c=n;
		controller = new NXBController(c);
		setLayout(null);
		setBounds(100, 100, 800, 500);
		
	/*
	 * Label
	 * */
		JSeparator separator = new JSeparator();
		separator.setBounds(212, 45, 400, 10);
		add(separator);
		
		JLabel lblNewLabel = new JLabel("THÔNG TIN NHÀ XUẤT BẢN");
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
		
		ScrollPane scrollPane = new ScrollPane(table);
		scrollPane.setBounds(212, 136, 578, 353);
		table.setModel(new DefaultTableModel(
				new Object[][] {},
				new String[] {
						"Mã nhà xuất bản","Tên nhà xuất bản","Địa chỉ"
				}
				));
		add(scrollPane);
		
	/*
	 * BUTTON
	 * */
		
		Button btnUpdate = new Button("Làm mới");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update(null);
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
		btnTimKiem.setIcon(new ImageIcon("otherResource/Search32.png"));
		btnTimKiem.setBounds(752, 74, 38, 35);
		add(btnTimKiem);
		update();
	}
	void update() {
		list = controller.xemTT();
		hienThiTable(list);
	}
	
	void hienThiTable(ArrayList<NXB> l) {
		table.setModel(new DefaultTableModel(
				new Object[][] {},
				new String[] {
						"Mã nhà xuất bản","Tên nhà xuất bản","Địa chỉ","Số sách"
				}
		));
		DefaultTableModel mta = (DefaultTableModel) table.getModel();
		for(int i = 0;i<l.size();i++) {
			mta.addRow(new Object[] {
					l.get(i).getMaNXB(),l.get(i).getTenNXB(),l.get(i).getDiaChi(),l.get(i).getSl()
			});
		}
	}
	
	void timKiem() {
		String str = txtTimKiem.getText();
		if(str.isBlank()) {
			hienThiTable(list);
		}else {
			ArrayList<NXB> l = controller.timKiem(str);
			hienThiTable(l);
		}			
	}
}
