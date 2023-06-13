package index;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;

import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;


public class ComboBox<E> extends JComboBox<E> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String labelText = "Label";
	private boolean mouseOver;
	public ComboBox() {
		setBackground(Color.white);
		setBorder(new EmptyBorder(15, 3, 5, 3));
		setUI(new ComboUI(this));
	}
	
	private class ComboUI extends BasicComboBoxUI {
		
		private final Animator animator;
		private boolean animateHinText = true;
		private float location;
		private boolean show;
		private ComboBox<E> comboBox;
		
		public ComboUI(ComboBox<E> comboBox) {
			this.comboBox = comboBox;
			
			addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					mouseOver = true;
					repaint();
				}
				@Override
				public void mouseExited(MouseEvent e) {
					mouseOver = false;
					repaint();
				}
			});
			
			addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					showing(false);
				}
				
				@Override
				public void focusLost(FocusEvent e) {
					showing(true);
				}
			});
			
			addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					if(!isFocusOwner()) {
						if(getSelectedIndex() == -1) {
							showing(true);
						}
						else {
							showing(false);
						}
					}
				}
			});
			
			addPopupMenuListener(new PopupMenuListener() {	
				@Override
				public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
					arrowButton.setBackground(new Color(200,200,200));
				}
				
				@Override
				public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
					arrowButton.setBackground(new Color(150,150,150));
				}
				
				@Override
				public void popupMenuCanceled(PopupMenuEvent e) {
					arrowButton.setBackground(new Color(150,150,150));
				}
			});
			
			TimingTarget target = new TimingTargetAdapter() {
				@Override
				public void begin() {
					animateHinText = getSelectedIndex() == -1;
				}
				@Override
				public void timingEvent(float fraction) {
					location=fraction;
					repaint();
				}
			};
			animator = new Animator(300, target);
			animator.setResolution(0);
			animator.setAcceleration(0.5f);
			animator.setDeceleration(0.5f);
		}
		
		@Override
		public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus) {
			
		}
		
		@Override
		protected JButton createArrowButton() {
			return new ArrowButton();
		};
		
		protected ComboPopup createPopup() {
			@SuppressWarnings("unchecked")
			BasicComboPopup pop = new BasicComboPopup((ComboBox<Object>)comboBox) {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				protected JScrollPane createScroller() {
					list.setFixedCellHeight(30);
					JScrollPane scroll = new JScrollPane(list);
					scroll.setBackground(Color.white);
					JScrollBar sb = new JScrollBar();
					sb.setUnitIncrement(30);
					sb.setForeground(new Color(180,180,180));
					scroll.setVerticalScrollBar(sb);
					return scroll;
				}
			};
			pop.setBorder(new LineBorder(new Color(200,200,200), 1));
			return pop;
		};
		
		@Override
		public void paint(Graphics g, JComponent c) {
			super.paint(g, c);
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
			int width = getWidth();
			int height = getHeight();
			if(mouseOver) {
				g2.setColor(Color.magenta);
			}
			else {
				g2.setColor(new Color(150,150,150));
			}
			g2.fillRect(2, height-1, width-4, 1);
			createHintText(g2);
			createLineStyle(g2);
			g2.dispose();
		}
	
		private void createHintText(Graphics2D g2) {
			Insets in = getInsets();
			g2.setColor(new Color(150,150,150));
			FontMetrics ft = g2.getFontMetrics();
			Rectangle2D r2 = ft.getStringBounds(comboBox.getLabelText(), g2);
			double height = getHeight() - in.top - in.bottom;
			double textY = (height - r2.getHeight())/2;
			double size;
			if(animateHinText) {
				if(show) {
					size = 18*(1-location);
				}
				else {
					size = 18*location;
				}
			}
			else {
				size = 18;
			}
			g2.drawString(comboBox.getLabelText(), in.right, (int)(in.top + textY + ft.getAscent() - size));
		}
		
		private void createLineStyle(Graphics2D g2) {
			if(isFocusOwner()) {
				double width = getWidth() - 4;
				int height = getHeight();
				g2.setColor(Color.magenta);
				double size;
				if(show) {
					size = width*(1-location);
				}
				else {
					size = width*location;
				}
				double x = (width - size)/2;
				g2.fillRect((int)(x+2), height-2, (int)(size), 2);
			}
		}
		
		private void showing(boolean action) {
			if(animator.isRunning()) {
				animator.stop();
			}
			else {
				location = 1;
			}
			animator.setStartFraction(1f - location);
			show=action;
			location = 1f - location;
			animator.start();
		}
		
		public class ArrowButton extends JButton {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			public ArrowButton() {
				setContentAreaFilled(false);
				setBorder(new EmptyBorder(5,5,5,5));
				setBackground(new Color(150,150,150));
			}
			
			@Override
			public void paint(Graphics g) {
				super.paint(g);
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				int width = getWidth();
				int height = getHeight();
				int size = 10;
				int x = (width-size)/2;
				int y = (height-size)/2 + 2;
				int px[] = {x, x+size, x+size/2};
				int py[] = {y, y, y+size};
				g2.setColor(getBackground());
				g2.fillPolygon(px, py, px.length);
				g2.dispose();
			}
		}
	}
	
	/*
	 * Getter & Setter
	 * */
	public String getLabelText() {
		return labelText;
	}
	public void setLabelText(String labelText) {
		this.labelText = labelText;
	}
	public boolean isMouseOver() {
		return mouseOver;
	}
	public void setMouseOver(boolean mouseOver) {
		this.mouseOver = mouseOver;
	}
}
