package layout_BanDoc;

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
import layout_ThuThu.TheMuonTra_ThongTin;
import model.BANDOC;
import model.THEMUONTRA;
import model.THUTHU;
import sql.ConnectSQL;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import controller.TheMuonTraController;

public class TheMuonTra_Panel extends JPanel {

	/**
	 * Create the panel.
	 */
	private static final long serialVersionUID = 1L;
	Table table;
	ConnectSQL c;
	InputField txtTimKiem;
	ArrayList<THEMUONTRA> list;
	TheMuonTraController controller;
	BANDOC b;
	/**
	 * Create the panel.
	 */
	public TheMuonTra_Panel(ConnectSQL n,BANDOC b) {
		c=n;
		this.b=b;
		controller = new TheMuonTraController(c);
		setLayout(null);
		setBounds(100, 100, 800, 500);
	/*
	 * Label
	 * */
		JSeparator separator = new JSeparator();
		separator.setBounds(212, 45, 400, 10);
		add(separator);
		
		JLabel lblNewLabel = new JLabel("THÔNG TIN THẺ MƯỢN TRẢ");
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
				"MÃ MƯỢN TRẢ","TÊN SÁCH","NGÀY MƯỢN","TRẠNG THÁI"
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
		
		
		
		Button btnChiTiet = new Button("Thông tin chi tiết");
		btnChiTiet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chiTiet();
			}
		});
		btnChiTiet.setBounds(10, 190, 192, 35);
		add(btnChiTiet);
		
		JButton btnTimKiem = new JButton();
		btnTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timKiem();
			}
		});
		
		btnTimKiem.setIcon(new ImageIcon("otherResource/Search32.png"));
		btnTimKiem.setBounds(752, 74, 38, 35);
		add(btnTimKiem);
		
		Button btnUpdate_1 = new Button("Gia hạn sách");
		btnUpdate_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnUpdate_1.setBounds(10, 236, 192, 35);
		add(btnUpdate_1);
		
		Button btnUpdate_1_1 = new Button("Mượn sách");
		btnUpdate_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TheMuonTra_MuonSach f = new TheMuonTra_MuonSach(c,b);
				f.setVisible(true);
			}
		});
		btnUpdate_1_1.setBounds(10, 282, 192, 35);
		add(btnUpdate_1_1);
		
		
		update();
	}
	void update() {
		list = controller.xemDS_BanDoc(b);
		hienThiTable(list);
	}
	
	void hienThiTable(ArrayList<THEMUONTRA> l) {
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Mã mượn trả","Tên sách","Ngày mượn","Ngày phải trả","Trạng thái"
				}
			));
		DefaultTableModel mta = (DefaultTableModel)table.getModel();
		for (int i = 0; i < l.size(); i++) {
			THEMUONTRA t = l.get(i);
			mta.addRow(new Object[] {
					t.getMaMuonTra(),t.getSach().getTenSach(),t.getBieuDienNgayMuon(),t.getBieuDienNgayTra(),t.getTrangThai()
			});
		}
	}
	
	void chiTiet() {
		DefaultTableModel mta = (DefaultTableModel)table.getModel();
		int i = table.getSelectedRow();
		if(i>=0) {
			Integer ma = (int)mta.getValueAt(i, 0);
			THEMUONTRA t = new THEMUONTRA();
			t.setMaMuonTra(ma);
			TheMuonTra_ThongTin f = new TheMuonTra_ThongTin(c, t);
			f.setVisible(true);
		}else JOptionPane.showMessageDialog(getRootPane(), "Chọn thẻ cần xem!");
	}
	
	
	void timKiem() {
		String str = txtTimKiem.getText();
		if(str.isBlank()) {
			hienThiTable(list);
		}else {
			try {
				THEMUONTRA t = new THEMUONTRA();
				t.setMaMuonTra(Integer.parseInt(str));
				ArrayList<THEMUONTRA> l = new ArrayList<>();
				l.add(controller.timTheMuonTra(t));
				hienThiTable(l);
			} catch (Exception e) {
				ArrayList<THEMUONTRA> l = controller.timKiem(str);
				hienThiTable(l);
			}
		}
	}
}
