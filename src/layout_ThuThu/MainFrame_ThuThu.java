package layout_ThuThu;

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
import layout_ThuThu.ThongKe_Panel;
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

public class MainFrame_ThuThu extends JFrame {
	
	private static final long serialVersionUID = 1L;
	public THUTHU account;
	SQLCommand sql;
	Utility utl;
	JTabbedPane tab;
	
	private TacGia_Panel tabTacGia;
	private BanDoc_Panel tabBanDoc;
	private NXB_Panel tabNXB;
	private Sach_Panel tabSach;
	private TheLoai_Panel tabTheLoai;
	private TheMuonTra_Panel tabTheMuonTra;
	ThongKe_Panel thongKe;
	ConnectSQL c;
	JTabbedPane menu;
	Login l;
	
	
	public MainFrame_ThuThu(Login l,ConnectSQL cc) {
		c=cc;
		this.l=l;
		account = l.getT();
		sql = new SQLCommand();
		utl = new Utility();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(100, 100, 1050, 700);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		getContentPane().setLayout(null);
		
		tab = new JTabbedPane(JTabbedPane.TOP);
		tab.setBounds(10, 116, 1014, 530);
		getContentPane().add(tab);
		setTitle("Quản lý thư viện");
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1024, 32);
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
	 */
		
		JMenu menuQuanLy = new JMenu("Quản lý");
		menuQuanLy.setFont(new Font("Tahoma", Font.PLAIN, 12));
		menuBar.add(menuQuanLy);
		
		JMenuItem menuNXB = new JMenuItem("Nhà xuất bản");
		menuNXB.setFont(new Font("Tahoma", Font.PLAIN, 12));
		menuNXB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addTabNXB();
			}
		});
		menuQuanLy.add(menuNXB);
		
		JMenuItem menuSach = new JMenuItem("Sách");
		menuSach.setFont(new Font("Tahoma", Font.PLAIN, 12));
		menuSach.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addTabSach();
			}
		});
		menuQuanLy.add(menuSach);
		
		JSeparator separator_1_1 = new JSeparator();
		menuQuanLy.add(separator_1_1);
		
		JMenuItem menuTacGia = new JMenuItem("Tác giả");
		menuTacGia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addTabTacGia();
			}
		});
		menuTacGia.setFont(new Font("Tahoma", Font.PLAIN, 12));
		menuQuanLy.add(menuTacGia);
		JSeparator separator_3 = new JSeparator();
		menuQuanLy.add(separator_3);
		
		JMenuItem menuTheLoai = new JMenuItem("Thể loại");
		menuTheLoai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addTabTheLoai();
			}
		});
		menuTheLoai.setFont(new Font("Tahoma", Font.PLAIN, 12));
		menuQuanLy.add(menuTheLoai);
		JSeparator separator_4 = new JSeparator();
		menuQuanLy.add(separator_4);
		
		JMenuItem menuTheMuonTra = new JMenuItem("Thẻ mượn trả");
		menuTheMuonTra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addTabTheMuonTra();
			}
		});
		menuTheMuonTra.setFont(new Font("Tahoma", Font.PLAIN, 12));
		menuQuanLy.add(menuTheMuonTra);
		
		JMenuItem menuBanDoc = new JMenuItem("Bạn đọc");
		menuBanDoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addTabBanDoc();
			}
		});
		menuBanDoc.setFont(new Font("Tahoma", Font.PLAIN, 12));
		menuQuanLy.add(menuBanDoc);
		
		JMenu TroGiup = new JMenu("Trợ giúp");
		TroGiup.setFont(new Font("Tahoma", Font.PLAIN, 12));
		menuBar.add(TroGiup);
		
		JMenuItem NoiDung = new JMenuItem("Thống kê");
		NoiDung.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addTabThongKe();
			}
		});
		NoiDung.setFont(new Font("Tahoma", Font.PLAIN, 12));
		TroGiup.add(NoiDung);
		NoiDung.setIcon(new ImageIcon("otherResource/NoiDung16.png"));
		
		JSeparator separator_2 = new JSeparator();
		TroGiup.add(separator_2);
		
		JMenuItem GioiThieu = new JMenuItem("Hướng dẫn");
		GioiThieu.setFont(new Font("Tahoma", Font.PLAIN, 12));
		TroGiup.add(GioiThieu);
		
		
		/*
		 * Tool Bar
		 */
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(0, 29, 1024, 76);
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
			
			JButton thongKeButton = new JButton("Thống kê");
			thongKeButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					addTabThongKe();
				}
			});
			thongKeButton.setIcon(new ImageIcon("Resource/ThongKe48.png"));
			toolBar.add(thongKeButton);
			
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
			tab.addTab("Tác giả", new ImageIcon(), tabTacGia, "Tác giả");
		}
		tab.setSelectedComponent(tabTacGia);
	}
	public void addTabNXB() {
		if(tabNXB == null) {
			tabNXB = new NXB_Panel(c);
			tab.addTab("Nhà xuất bản", new ImageIcon(), tabNXB, "Nhà xuất bản");
		}
		tab.setSelectedComponent(tabNXB);
	}
	public void addTabTheLoai() {
		if(tabTheLoai == null) {
			tabTheLoai = new TheLoai_Panel(c);
			tab.addTab("Thể loại", new ImageIcon(), tabTheLoai, "Thể loại");
		}
		tab.setSelectedComponent(tabTheLoai);
	}
	public void addTabSach() {
		if(tabSach == null) {
			tabSach = new Sach_Panel(c);
			tab.addTab("Sách", new ImageIcon(), tabSach, "Sách");
		}
		tab.setSelectedComponent(tabSach);
	}
	public void addTabBanDoc() {
		if(tabBanDoc == null) {
			tabBanDoc = new BanDoc_Panel(c);
			tab.addTab("Bạn đọc", new ImageIcon(), tabBanDoc, "Bạn đọc");
		}
		tab.setSelectedComponent(tabBanDoc);
	}
	public void addTabTheMuonTra() {
		if(tabTheMuonTra == null) {
			tabTheMuonTra = new TheMuonTra_Panel(c, account);
			tab.addTab("Thẻ mượn trả", new ImageIcon(), tabTheMuonTra, "Thẻ mượn trả");
		}
		tab.setSelectedComponent(tabTheMuonTra);
	}
	public void addTabThongKe() {
		if(thongKe == null) {
			thongKe = new ThongKe_Panel(c);
			tab.addTab("Thống kê", new ImageIcon(), thongKe, "Thống kê");
		}else {
			tab.remove(thongKe);
			thongKe = new ThongKe_Panel(c);
			tab.addTab("Thống kê", new ImageIcon(), thongKe, "Thống kê");
		}
		tab.setSelectedComponent(thongKe);
	}

}
