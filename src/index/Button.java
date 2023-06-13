package index;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

public class Button extends JButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Button(String label) {
		super(label);
		setForeground(Color.MAGENTA);
		setBackground(Color.PINK);
		setFont(new Font("Tahoma", Font.BOLD, 14));
	}
}
