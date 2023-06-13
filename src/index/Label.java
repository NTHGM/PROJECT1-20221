package index;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Label extends JLabel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Label(String label) {
		super(label);
		setFont(new Font("Tahoma", Font.BOLD, 16));
		setForeground(Color.magenta);
		setHorizontalAlignment(SwingConstants.CENTER);
	}
}
