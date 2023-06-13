package index;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Table extends JTable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public DefaultTableModel model;
	public Table() {
		model = new DefaultTableModel();
		setModel(model);
		setBackground(Color.white);
		setForeground(Color.black);
		setSelectionBackground(Color.magenta);
		setGridColor(Color.magenta);
		setSelectionForeground(Color.white);
		setFont(new Font("Tahoma", Font.PLAIN, 16));
		setRowHeight(30);
		setAutoCreateRowSorter(true);
	}
}
