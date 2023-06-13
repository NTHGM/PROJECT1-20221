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

import index.Button;
import index.InputField;
import index.ScrollPane;
import index.Table;
import model.TACGIA;
import sql.ConnectSQL;
import javax.swing.table.DefaultTableModel;

import controller.TacGiaController;

import javax.swing.ListSelectionModel;

public class TacGia_Panel extends JPanel {

	private static final long serialVersionUID = 1L;
	Table table;
	ConnectSQL c;
	InputField txtTimKiem;
	TacGiaController controller;
	ArrayList<TACGIA> list;
	/**
	 * Create the panel.
	 */
	public TacGia_Panel(ConnectSQL n) {
		c=n;
		controller = new TacGiaController(c);
		setLayout(null);
		setBounds(100, 100, 800, 500);
		
	/*
	 * Label
	 * */
		JSeparator separator = new JSeparator();
		separator.setBounds(212, 45, 400, 10);
		add(separator);
		
		JLabel lblNewLabel = new JLabel("QUẢN LÝ TÁC GIẢ");
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
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFont(new Font("Tahoma", Font.PLAIN, 16));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Mã tác giả", "Tên tác giả","Số sách"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false
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
		
		Button btnThem = new Button("Thêm mới");
		btnThem.setBounds(10, 190, 192, 35);
		add(btnThem);
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				them();
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
	void update() {
		list = controller.xemTT();
		hienThiTable(list);
	}
	void hienThiTable(ArrayList<TACGIA> l) {
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Mã tác giả", "Tên tác giả","Số sách"
				}
			) {
				boolean[] columnEditables = new boolean[] {
					false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
		DefaultTableModel mta = (DefaultTableModel) table.getModel();
		for (int i = 0; i < l.size(); i++) {
			TACGIA t = l.get(i);
			mta.addRow(new Object[] {
					t.getMaTacGia(),t.getTenTacGia(),t.getSl()
			});
		}
		
	}
	void chinhSua() {
		DefaultTableModel mta = (DefaultTableModel) table.getModel();
		int i = table.getSelectedRow();
		if(i>=0) {
			int ma = (int)mta.getValueAt(i, 0);
			TACGIA t = new TACGIA(ma, null);
			TacGia_Sua f = new TacGia_Sua(c, t);
			f.setVisible(true);
		}else JOptionPane.showMessageDialog(this, "Chọn tác giả muốn sửa!");
	}
	
	void them() {
		TacGia_Them f = new TacGia_Them(c);
		f.setVisible(true);
	}
	
	void xoa() {
		DefaultTableModel mta = (DefaultTableModel) table.getModel();
		int i = table.getSelectedRow();
		if(i>=0) {
			int ma = (int)mta.getValueAt(i, 0);
			TACGIA t = new TACGIA(ma, null);
			int a = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa?\n Xóa tác giả này ở thông tin sách!");
			if(a==0) {
				controller.xoaTS_maTacGia(t);
				controller.xoaTG(t);
				JOptionPane.showMessageDialog(this, "Xóa thành công!");
				update();
			}
		}else JOptionPane.showMessageDialog(this, "Chọn tác giả muốn xóa!");
	}
	
	void timKiem() {
		String str = txtTimKiem.getText();
		if(str.isBlank()) {
			hienThiTable(list);
		}else {
			try {
				TACGIA t = new TACGIA();
				t.setMaTacGia(Integer.parseInt(str));
				ArrayList<TACGIA> l = new ArrayList<>();
				l.add(controller.timTacGia(t));
				hienThiTable(l);
			} catch (Exception e) {
				ArrayList<TACGIA> l = controller.timKiem(str);
				hienThiTable(l);
			}
		}
	}
}
