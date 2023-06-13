package main;

import java.awt.EventQueue;

import layout_BanDoc.*;
import layout_Main.Login;
import layout_ThuThu.MainFrame_ThuThu;
import sql.ConnectSQL;


public class Main {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login l = new Login();
					l.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
