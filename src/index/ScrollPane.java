package index;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ScrollPane extends JScrollPane {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ScrollPane(Table table) {
		super(table);
		setForeground(Color.magenta);
		setBackground(Color.white);
	}
	
	public ScrollPane(JPanel panel) {
		super(panel);
		setForeground(Color.magenta);
		setBackground(Color.white);
	}

}
