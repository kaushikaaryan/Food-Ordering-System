package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class MainMenu extends JPanel {
	JLabel picLabel, title;
	JButton button;
	private Panel panel_1;
	private Panel panel_2;
	static JFrame frame;

	public void createAndShowGUI() throws IOException {
		JPanel panel = new JPanel(new BorderLayout());
		Image image = ImageIO.read(this.getClass().getResource("/food_logo.png"));
		Image imageScaled = image.getScaledInstance(600, 600, Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(imageScaled);
		picLabel = new JLabel(imageIcon);
		Box right = Box.createVerticalBox();
		panel_1 = new Panel();
		title = new JLabel("Food Restaurant");
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		title.setAlignmentY(0.0f);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 20));
		title.setForeground(Color.BLACK);

		// Button, with filler
		button = new JButton("Order Food >>");
		panel_1.add(button);
		button.setAlignmentX(Component.LEFT_ALIGNMENT);

		panel.add(picLabel, BorderLayout.CENTER);
		panel.add(right, BorderLayout.SOUTH);
		right.add(title);
		right.add(panel_1);
		add(panel);

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FoodMenu food;
				try {
					food = new FoodMenu();
					food.createAndShowGUI();
					food.setVisible(true);
					setVisible(false);
					frame.dispose();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}

	public static void main(String args[]) throws IOException {
		MainMenu main = new MainMenu();
		main.createAndShowGUI();
		frame = new JFrame();
		frame.setTitle("Aaryan Food Ordering System");
		frame.getContentPane().add(main);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
