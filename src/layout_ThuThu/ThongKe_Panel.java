package layout_ThuThu;

import javax.swing.JPanel;
import index.Label;
import sql.ConnectSQL;

import javax.swing.JSeparator;

import controller.ThongKeController;
import index.InputField;
import javax.swing.JLabel;
import java.awt.Font;

public class ThongKe_Panel extends JPanel {

	/**
	 * 
	 */
	ThongKeController controller;
	private static final long serialVersionUID = 1L;
	
	public ThongKe_Panel(ConnectSQL c) {
		controller = new ThongKeController(c);
		setLayout(null);
		setBounds(100, 100, 800, 500);
		
	/*
	 * Label
	 * */
		
		Label lblNewLabel = new Label("Thống kê");
		lblNewLabel.setBounds(20, 11, 770, 20);
		add(lblNewLabel);
		JSeparator separator = new JSeparator();
		separator.setBounds(163, 42, 499, 20);
		add(separator);
		
		JLabel lblNewLabel_2_1 = new JLabel("Bạn đọc");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_1.setBounds(20, 161, 82, 27);
		add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Mượn trả sách");
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_1_1.setBounds(20, 334, 126, 27);
		add(lblNewLabel_2_1_1);
		
	/*
	 * Information
	 * */
		InputField txtSach = new InputField("Tổng sách");
		txtSach.setEditable(false);
		txtSach.setText(""+controller.tongSoSach());
		txtSach.setColumns(10);
		txtSach.setBounds(20, 83, 160, 47);
		add(txtSach);
		
		InputField txtTg = new InputField("");
		txtTg.setLabel("Số tác giả");
		txtTg.setText(""+controller.tongSoTacGia());
		txtTg.setEditable(false);
		txtTg.setColumns(10);
		txtTg.setBounds(190, 83, 135, 47);
		add(txtTg);
		
		InputField txtNxb = new InputField("");
		txtNxb.setLabel("Số nhà xuất bản");
		txtNxb.setText(""+controller.tongSoNXB());
		txtNxb.setEditable(false);
		txtNxb.setColumns(10);
		txtNxb.setBounds(335, 83, 126, 47);
		add(txtNxb);
		
		InputField txtTl = new InputField("");
		txtTl.setLabel("Số thể loại");
		txtTl.setText(""+controller.tongSoTheLoai());
		txtTl.setEditable(false);
		txtTl.setColumns(10);
		txtTl.setBounds(471, 83, 135, 47);
		add(txtTl);	
		
		InputField txtBanDoc = new InputField("");
		txtBanDoc.setLabel("Tổng số bạn đọc");
		txtBanDoc.setText(""+controller.tongSoBanDoc());
		txtBanDoc.setEditable(false);
		txtBanDoc.setColumns(10);
		txtBanDoc.setBounds(20, 199, 160, 47);
		add(txtBanDoc);
		
		InputField txtDuoi18 = new InputField("");
		txtDuoi18.setLabel("Dưới 18 tuổi");
		txtDuoi18.setText(""+controller.tongDuoi18());
		txtDuoi18.setEditable(false);
		txtDuoi18.setColumns(10);
		txtDuoi18.setBounds(190, 199, 160, 47);
		add(txtDuoi18);
		
		InputField txtTren = new InputField("");
		txtTren.setLabel("Từ 18 tuổi");
		txtTren.setText(""+controller.tong18());
		txtTren.setEditable(false);
		txtTren.setColumns(10);
		txtTren.setBounds(373, 199, 160, 47);
		add(txtTren);
		
		InputField txtMuontra = new InputField("");
		txtMuontra.setLabel("Số phiếu mượn trả ");
		txtMuontra.setEditable(false);
		txtMuontra.setText(""+controller.tongMuonTra());
		txtMuontra.setColumns(10);
		txtMuontra.setBounds(20, 372, 160, 47);
		add(txtMuontra);
		
		InputField txtDatra = new InputField("");
		txtDatra.setLabel("Đã trả");
		txtDatra.setText(""+controller.tongDaTra());
		txtDatra.setEditable(false);
		txtDatra.setColumns(10);
		txtDatra.setBounds(190, 372, 120, 47);
		add(txtDatra);
		
		InputField txtDangMuon = new InputField("");
		txtDangMuon.setLabel("Đang mượn");
		txtDangMuon.setText(""+controller.tongDangMuon());
		txtDangMuon.setEditable(false);
		txtDangMuon.setColumns(10);
		txtDangMuon.setBounds(335, 372, 126, 47);
		add(txtDangMuon);
		
		InputField txtQuaHan = new InputField("");
		txtQuaHan.setLabel("Quá hạn trả");
		txtQuaHan.setText(""+controller.tongQuaHan());
		txtQuaHan.setEditable(false);
		txtQuaHan.setColumns(10);
		txtQuaHan.setBounds(471, 372, 135, 47);
		add(txtQuaHan);
		
		JLabel lblNewLabel_2_1_2 = new JLabel("Sách");
		lblNewLabel_2_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_1_2.setBounds(20, 42, 82, 27);
		add(lblNewLabel_2_1_2);
		
		InputField txtNam = new InputField("");
		txtNam.setLabel("Nam");
		txtNam.setText(""+controller.tongSoNam());
		txtNam.setEditable(false);
		txtNam.setColumns(10);
		txtNam.setBounds(20, 263, 160, 47);
		add(txtNam);
		
		InputField txtNu = new InputField("");
		txtNu.setLabel("Nữ");
		txtNu.setText(""+controller.tongSoNu());
		txtNu.setEditable(false);
		txtNu.setColumns(10);
		txtNu.setBounds(190, 263, 160, 47);
		add(txtNu);
		
		InputField txtKhac = new InputField("");
		txtKhac.setLabel("Khác");
		txtKhac.setText(""+controller.tongSoKhac());
		txtKhac.setEditable(false);
		txtKhac.setColumns(10);
		txtKhac.setBounds(373, 263, 160, 47);
		add(txtKhac);

	}
}
