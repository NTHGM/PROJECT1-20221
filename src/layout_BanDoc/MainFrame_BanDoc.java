package layout_BanDoc;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

import controller.BanDocController;
import controller.NXBController;
import controller.SachController;
import controller.TacGiaController;
import controller.TheLoaiController;
import controller.TheMuonTraController;
import index.Button;
import index.ScrollPane;
import index.Table;
import layout_Main.Login;

import model.BANDOC;
import model.NXB;
import model.SACH;
import model.TACGIA;
import model.THELOAI;
import model.THEMUONTRA;
import model.THUTHU;
import sql.ConnectSQL;
import sql.SQLCommand;
import sql.Utility;

public class MainFrame_BanDoc extends JFrame {
	
	private static final long serialVersionUID = 1L;
	public BANDOC account;
	SQLCommand sql;
	Utility utl;
	JTabbedPane tab;
	Login l;
	private TacGia_Panel tabTacGia;
	private BanDoc_Panel banDoc;
	private NXB_Panel tabNXB;
	private Sach_Panel tabSach;
	private TheLoai_Panel tabTheLoai;
	private TheMuonTra_Panel tabTheMuonTra;
	ConnectSQL c;
	JTabbedPane menu;
	
	
	public MainFrame_BanDoc(Login l,ConnectSQL cc) {
		sql = new SQLCommand();
		utl = new Utility();
		this.l=l;
		account = l.getB();
		this.c=cc;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(100, 100, 900, 700);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		getContentPane().setLayout(null);
		
		tab = new JTabbedPane(JTabbedPane.TOP);
		tab.setBounds(10, 116, 870, 530);
		getContentPane().add(tab);
		setTitle("Thư viện");
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 900, 32);
		getContentPane().add(menuBar);
		
		JMenu HeThong = new JMenu("Hệ thống");
		HeThong.setFont(new Font("Tahoma", Font.BOLD, 12));
		menuBar.add(HeThong);
		
		JMenuItem DangXuat = new JMenuItem("Đăng xuất");
		DangXuat.setFont(new Font("Tahoma", Font.PLAIN, 12));
		DangXuat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				l.setVisible(true);
				dispose();
			}
		});
		DangXuat.setIcon(new ImageIcon("otherResource/LogOut16.png"));
		HeThong.add(DangXuat);
		
		JSeparator separator = new JSeparator();
		HeThong.add(separator);
		
		JMenuItem Thoat = new JMenuItem("Thoát");
		Thoat.setFont(new Font("Tahoma", Font.PLAIN, 12));
		Thoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		HeThong.add(Thoat);
		Thoat.setIcon(new ImageIcon("otherResource/Thoat16.png"));
		
	/*
	 * SUPPORT
		
		/*
		 * Tool Bar
		 */
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(0, 29, 884, 76);
		getContentPane().add(toolBar);
			
			JButton dangXuatButton = new JButton("Đăng xuất");
			dangXuatButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					l.setVisible(true);
					dispose();
				}
			});
			dangXuatButton.setIcon(new ImageIcon("Resource/LogOut48.png"));
			toolBar.add(dangXuatButton);
			
			JButton tacGiaButton = new JButton("Tác giả");
			tacGiaButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					addTabTacGia();
				}
			});
			tacGiaButton.setIcon(new ImageIcon("Resource/TacGia48.png"));
			toolBar.add(tacGiaButton);
			
			JButton nxbButton = new JButton("NXB");
			nxbButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					addTabNXB();
				}
			});
			nxbButton.setIcon(new ImageIcon("Resource/NXB48.png"));
			toolBar.add(nxbButton);
			
			JButton theLoaiButton = new JButton("Thể loại");
			theLoaiButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					addTabTheLoai();
				}
			});
			theLoaiButton.setIcon(new ImageIcon("Resource/TheLoai48.png"));
			toolBar.add(theLoaiButton);
			
			JButton sachButton = new JButton("Sách");
			sachButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					addTabSach();
				}
			});
			sachButton.setIcon(new ImageIcon("Resource/Sach48.png"));
			toolBar.add(sachButton);
			
			JButton banDocButton = new JButton("Bạn đọc");
			banDocButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					addTabBanDoc();
				}
			});
			banDocButton.setIcon(new ImageIcon("Resource/BanDoc48.png"));
			toolBar.add(banDocButton);
			
			JButton theMuonTraButton = new JButton("Thẻ mượn trả");
			theMuonTraButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					addTabTheMuonTra();
				}
			});
			theMuonTraButton.setIcon(new ImageIcon("Resource/The48.png"));
			toolBar.add(theMuonTraButton);
			
			JButton thoatButton = new JButton("Thoát");
			thoatButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			thoatButton.setIcon(new ImageIcon("Resource/Thoat48.png"));
			toolBar.add(thoatButton);
			
	}
	
	/*
	 * Events
	 * */	
	public void addTabTacGia() {
		if(tabTacGia == null) {
			tabTacGia = new TacGia_Panel(c);
			tab.add(tabTacGia);
		}
		tab.setSelectedComponent(tabTacGia);
	}
	public void addTabNXB() {
		if(tabNXB == null) {
			tabNXB = new NXB_Panel(c);
			tab.add(tabNXB);
		}
		tab.setSelectedComponent(tabNXB);
	}
	public void addTabTheLoai() {
		if(tabTheLoai == null) {
			tabTheLoai = new TheLoai_Panel(c);
			tab.add(tabTheLoai);
		}
		tab.setSelectedComponent(tabTheLoai);
	}
	public void addTabSach() {
		if(tabSach == null) {
			tabSach = new Sach_Panel(c);
			tab.add(tabSach);
		}
		tab.setSelectedComponent(tabSach);
	}
	public void addTabBanDoc() {
		if(banDoc == null) {
			banDoc = new BanDoc_Panel(c,account,this);
			tab.add(banDoc);
		}
		tab.setSelectedComponent(banDoc);
	}
	public void addTabTheMuonTra() {
		if(tabTheMuonTra == null) {
			tabTheMuonTra = new TheMuonTra_Panel(c,account);
			tab.add(tabTheMuonTra);
		}
		tab.setSelectedComponent(tabTheMuonTra);
	}

}
