
package gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

public class FoodMenu {
	static private JFrame frame;
	static private JButton backButton, orderButton;
	static private JTextField textField;
	static private GridBagConstraints gbc;
	private JTable table;
	DefaultTableModel dtm;
	Double[] price;
	Double[] priceDrinks;
	Double[] priceDesserts;
	double totalPrice = 0;
	double p1, p2, p3, p4, p5, p6, p7, p8, p9;
	double d1, d2, d3, d4, d5;
	double de1, de2;

	private JSpinner[] numSpinner;
	static private JLabel[] foodLabel;
	static private JLabel[] foodImage;
	private String[] file;

	private JSpinner[] numSpinnerDrinks;
	static private JLabel[] drinkLabel;
	static private JLabel[] drinkImage;
	private String[] fileDrinks;

	private JSpinner[] numSpinnerDesserts;
	static private JLabel[] dessertLabel;
	static private JLabel[] dessertImage;
	private String[] fileDesserts;

	private static final int ELEMENTS = 9;
	private static final int DRINK_ELEMENTS = 5;
	private static final int DESSERT_ELEMENTS = 2;

	double total = 0;
	double food1, food2, food3, food4, food5, food6, food7, food8, food9;
	double drink1, drink2, drink3, drink4, drink5;
	double pr, pr1;

	double totalForFoods;
	double totalForDrinks;
	double totalForDesserts;

	void createAndShowGUI() throws IOException {
		frame = new JFrame("Main Menu ");
		frame.setBounds(100, 100, 800, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		JLabel lblFoodOrdered = new JLabel("Food Ordered");
		lblFoodOrdered.setBounds(529, 11, 61, 14);

		frame.getContentPane().add(lblFoodOrdered);

		table = new JTable();
		backButton = new JButton();
		orderButton = new JButton();
		dtm = new DefaultTableModel(0, 0);
		final String header[] = new String[] { "Item", "Qty", "Price", "Spinner" };
		dtm.setColumnIdentifiers(header);
		dtm.addRow(header);
		table = new JTable();
		table.setModel(dtm);
		table.setBounds(540, 31, 1, 1); // int x, int y, int width, int height
		table.setSize(245, 300); // width,height
		table.getColumnModel().getColumn(0).setPreferredWidth(80);
		table.getColumnModel().getColumn(1).setPreferredWidth(30);
		table.getColumnModel().getColumn(2).setPreferredWidth(30);
		table.getColumnModel().getColumn(3).setMinWidth(0); // hide spinner
															// column
		table.getColumnModel().getColumn(3).setMaxWidth(0); // hide spinner
															// column
		table.setShowGrid(false); // remove cell boarder
		frame.getContentPane().add(table);
		JLabel lblTotal = new JLabel("Total  : ");
		lblTotal.setBounds(540, 340, 46, 14);
		frame.getContentPane().add(lblTotal);
		textField = new JTextField();
		textField.setBounds(585, 340, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		orderButton = new JButton("Order");
		orderButton.setBounds(540, 385, 89, 23);
		frame.getContentPane().add(orderButton);
		backButton = new JButton("Back");
		backButton.setBounds(640, 385, 89, 23);
		frame.getContentPane().add(backButton);
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		addIt(tabbedPane, "Foods");
		addIt1(tabbedPane, "Drinks");
		addIt2(tabbedPane, "Desserts");
		tabbedPane.setBounds(18, 11, 500, 450);
		frame.getContentPane().add(tabbedPane);
		frame.setVisible(true);

		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MainMenu menu = new MainMenu();
					menu.main(header);
					// menu.createAndShowGUI();
					menu.setVisible(true);
					// setVisible(false);
					frame.dispose();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		orderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "You not ordered anything yet");
				} else {
					try {
						OrderMenu order = new OrderMenu();
						order.main(header);
						order.setVisible(true);
						setVisible(false);
						frame.dispose();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}

		});
	}

	void addIt(JTabbedPane tabbedPane, String text) throws IOException {
		JPanel panel = new JPanel(new GridBagLayout());
		gbc = new GridBagConstraints(); // getting constraints for the specified
										// component
		gbc.insets = new Insets(10, 0, 0, 0);
		foodImage = new JLabel[ELEMENTS];
		foodLabel = new JLabel[ELEMENTS];
		numSpinner = new JSpinner[ELEMENTS];
		file = new String[ELEMENTS];
		price = new Double[ELEMENTS];

		file[0] = new String("/dal.jpeg");
		file[1] = new String("/kadaipaneer.jpeg");
		file[2] = new String("/butterpaneer.jpeg");
		file[3] = new String("/paneerchilli.jpeg");
		file[4] = new String("/vegbiryani.jpeg");
		file[5] = new String("/tawaroti.jpeg");
		file[6] = new String("/tandooriroti.jpeg");
		file[7] = new String("/jeerarice.jpeg");
		file[8] = new String("/amritsarinaan.jpeg");
		foodLabel[0] = new JLabel("Yellow Dal");
		foodLabel[1] = new JLabel("Kadai Paneer");
		foodLabel[2] = new JLabel("Butter Paner");
		foodLabel[3] = new JLabel("Paneeer Chilly");
		foodLabel[4] = new JLabel("Veg Biryani");
		foodLabel[5] = new JLabel("Tawa Roti");
		foodLabel[6] = new JLabel("Tandoori Roti");
		foodLabel[7] = new JLabel("Jeera Rice");
		foodLabel[8] = new JLabel("Amritsari Naan Chana");
		price[0] = 30.50;
		price[1] = 40.50;
		price[2] = 30.70;
		price[3] = 40.50;
		price[4] = 50.50;
		price[5] = 40.00;
		price[6] = 30.50;
		price[7] = 60.50;
		price[8] = 60.50;
		for (int i = 0; i < ELEMENTS; i++) {
			
			System.out.print(file[i]);	
			try {
			
			Image image = ImageIO.read(this.getClass().getResource(file[i]));
			Image imageScaled = image.getScaledInstance(80, 95, Image.SCALE_SMOOTH);
//			Image image = ImageIO.read(file[i]);
//			Image imageScaled = image.getScaledInstance(80, 95, Image.SCALE_SMOOTH);
			ImageIcon imageIcon = new ImageIcon(imageScaled);
			SpinnerNumberModel spnummodel = new SpinnerNumberModel(0, 0, 10, 1); // value,minimum,maximum,stepSize
			numSpinner[i] = new JSpinner(spnummodel);
			numSpinner[i].addChangeListener(listener);
			foodImage[i] = new JLabel(imageIcon);
			}catch(Exception e) {
				System.out.print(e);
			}
		}
		gbc.gridx = 0; // gridx 0 represent the most left
		for (int i = 0; i < ELEMENTS; i++) {
			if (i % 3 == 0) {
				gbc.gridy += 2;
				gbc.gridx = 0;
			}
			panel.add(foodImage[i], gbc);
			gbc.gridy++; // gridy---> add one row,for foodLabel
			panel.add(foodLabel[i], gbc);
			gbc.gridy--; // remove the row
			gbc.gridx++; // move to next column
			panel.add(numSpinner[i], gbc);
			gbc.gridx++; // move to next column
			tabbedPane.addTab(text, panel);
		}
	}

	void addIt2(JTabbedPane tabbedPane, String text) throws IOException {
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		dessertImage = new JLabel[DESSERT_ELEMENTS];
		dessertLabel = new JLabel[DESSERT_ELEMENTS];
		numSpinnerDesserts = new JSpinner[DESSERT_ELEMENTS];
		priceDesserts = new Double[DESSERT_ELEMENTS];

		fileDesserts = new String[DESSERT_ELEMENTS];
		fileDesserts[0] = new String("/strawberrycake.jpeg");
		fileDesserts[1] = new String("/chocolatecake.jpeg");

		dessertLabel[0] = new JLabel("Strawberry Cake");
		dessertLabel[1] = new JLabel("Chocolate Cake");

		priceDesserts[0] = 20.50;
		priceDesserts[1] = 30.00;

		for (int i = 0; i < DESSERT_ELEMENTS; i++) {
			Image image = ImageIO.read(this.getClass().getResource(fileDesserts[i]));
			Image imageScaled = image.getScaledInstance(80, 95, Image.SCALE_SMOOTH);
			ImageIcon imageIcon = new ImageIcon(imageScaled);
			SpinnerNumberModel spnummodel = new SpinnerNumberModel(0, 0, 10, 1); // value,minimum,maximum,stepSize
			numSpinnerDesserts[i] = new JSpinner(spnummodel);
			numSpinnerDesserts[i].addChangeListener(listenerForDesserts);
			dessertImage[i] = new JLabel(imageIcon);
		}
		gbc.gridx = 0; // gridx 0 represent the most left
		gbc.insets = new Insets(10, 5, 0, 0); // top,left,bottom,right
		for (int i = 0; i < DESSERT_ELEMENTS; i++) {
			if (i % 3 == 0) {
				gbc.gridx = 0;
				gbc.gridy += 2;
			}
			panel.add(dessertImage[i], gbc);
			gbc.gridy++; // gridy---> add one row,for foodLabel
			panel.add(dessertLabel[i], gbc);
			gbc.gridy--; // remove the row
			gbc.gridx++; // move to next column
			panel.add(numSpinnerDesserts[i], gbc);
			gbc.gridx++; // move to next column
			tabbedPane.addTab(text, panel);
		}

	}

	void addIt1(JTabbedPane tabbedPane, String text) throws IOException {
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		drinkImage = new JLabel[DRINK_ELEMENTS];
		drinkLabel = new JLabel[DRINK_ELEMENTS];
		numSpinnerDrinks = new JSpinner[DRINK_ELEMENTS];
		priceDrinks = new Double[DRINK_ELEMENTS];

		fileDrinks = new String[DRINK_ELEMENTS];
		fileDrinks[0] = new String("/redbeer.jpeg");
		fileDrinks[1] = new String("/chocolateshake.jpeg");
		fileDrinks[2] = new String("/kitkatshake.jpeg");
		fileDrinks[3] = new String("/lemonsoda.jpeg");
		fileDrinks[4] = new String("/aerateddrink.jpeg");

		drinkLabel[0] = new JLabel("Red Beer");
		drinkLabel[1] = new JLabel("Chocolate Shake");
		drinkLabel[2] = new JLabel("Kitkat Shake");
		drinkLabel[3] = new JLabel("Lemon Soda");
		drinkLabel[4] = new JLabel("Aerated Drinks");

		priceDrinks[0] = 30.50;
		priceDrinks[1] = 40.50;
		priceDrinks[2] = 30.00;
		priceDrinks[3] = 50.00;
		priceDrinks[4] = 30.00;

		for (int i = 0; i < DRINK_ELEMENTS; i++) {
			Image image = ImageIO.read(this.getClass().getResource(fileDrinks[i]));
			Image imageScaled = image.getScaledInstance(80, 95, Image.SCALE_SMOOTH);
			ImageIcon imageIcon = new ImageIcon(imageScaled);
			SpinnerNumberModel spnummodel = new SpinnerNumberModel(0, 0, 10, 1); // value,minimum,maximum,stepSize
			numSpinnerDrinks[i] = new JSpinner(spnummodel);
			numSpinnerDrinks[i].addChangeListener(listenerForDrinks);
			drinkImage[i] = new JLabel(imageIcon);
		}
		gbc.gridx = 0; // gridx 0 represent the most left
		gbc.insets = new Insets(10, 5, 0, 0); // top,left,bottom,right
		for (int i = 0; i < DRINK_ELEMENTS; i++) {
			if (i % 3 == 0) {
				gbc.gridx = 0;
				gbc.gridy += 2;
			}
			panel.add(drinkImage[i], gbc);
			gbc.gridy++; // gridy---> add one row,for foodLabel
			panel.add(drinkLabel[i], gbc);
			gbc.gridy--; // remove the row
			gbc.gridx++; // move to next column
			panel.add(numSpinnerDrinks[i], gbc);
			gbc.gridx++; // move to next column
			tabbedPane.addTab(text, panel);

		}
	}

	ChangeListener listener = new ChangeListener() {
		public void stateChanged(ChangeEvent e) {

			final int quantity = (int) ((JSpinner) e.getSource()).getValue();
			final int rows = table.getRowCount();
			for (int row = 0; row < rows; row++) {
				if (dtm.getValueAt(row, 3) == e.getSource()) {
					if (dtm.getValueAt(row, 0).equals("Yellow Dal")) {
						dtm.setValueAt(quantity, row, 1); // obj, row, column
						dtm.setValueAt(p1 * quantity, row, 2);
						food1 = p1 * quantity;

					} else if (dtm.getValueAt(row, 0).equals("Kadai Paneer")) {

						dtm.setValueAt(quantity, row, 1);
						dtm.setValueAt(p2 * quantity, row, 2);
						food2 = p2 * quantity;
					} else if (dtm.getValueAt(row, 0).equals("Butter Paner")) {

						dtm.setValueAt(quantity, row, 1);
						dtm.setValueAt(p3 * quantity, row, 2);
						food3 = p3 * quantity;
					} else if (dtm.getValueAt(row, 0).equals("Paneeer Chilly")) {

						dtm.setValueAt(quantity, row, 1);
						dtm.setValueAt(p4 * quantity, row, 2);
						food4 = p4 * quantity;
					} else if (dtm.getValueAt(row, 0).equals("Veg Biryani")) {

						dtm.setValueAt(quantity, row, 1);
						dtm.setValueAt(p5 * quantity, row, 2);
						food5 = p5 * quantity;
					} else if (dtm.getValueAt(row, 0).equals("Tawa Roti")) {

						dtm.setValueAt(quantity, row, 1);
						dtm.setValueAt(p6 * quantity, row, 2);
						food6 = p6 * quantity;
					} else if (dtm.getValueAt(row, 0).equals("Tandoori Roti")) {

						dtm.setValueAt(quantity, row, 1); // obj, row,
															// column
						dtm.setValueAt(p7 * quantity, row, 2);
						food7 = p7 * quantity;
					} else if (dtm.getValueAt(row, 0).equals("Jeera Rice")) {

						dtm.setValueAt(quantity, row, 1); // obj, row,
															// column
						dtm.setValueAt(p8 * quantity, row, 2);
						food8 = p8 * quantity;
					} else if (dtm.getValueAt(row, 0).equals("Amritsari Naan Chana")) {

						dtm.setValueAt(quantity, row, 1); // obj, row,
															// column
						dtm.setValueAt(p9 * quantity, row, 2);
						food9 = p9 * quantity;
					}

					if (quantity == 0) {
						dtm.removeRow(row);
					}
					totalForFoods = food1 + food2 + food3 + food4 + food5 + food6 + food7 + food8 + food9;
					total = totalForFoods + totalForDrinks + totalForDesserts;
					textField.setText(total + "");
					return;
				}
			}

			// there was no row with this JSpinner, so we have to add it
			for (int i = 0; i < ELEMENTS; i++) {
				// looking for the "clicked" JSpinner
				if (numSpinner[i] == e.getSource()) {
					totalPrice = price[i];
					switch (foodLabel[i].getText()) {
					case "Yellow Dal":
						p1 = 30.50;
						food1 = p1*quantity;
						break;
					case "Kadai Paneer":
						p2 = 40.50;
						food2 = p2*quantity;
						break;
					case "Butter Paner":
						p3 = 30.70;
						food3 = p3*quantity;
						break;
					case "Paneeer Chilly":
						p4 = 40.50;
						food4 = p4*quantity;
						break;
					case "Veg Biryani":
						p5 = 50.50;
						food5 = p5*quantity;
						break;
					case "Tawa Roti":
						p6 = 40.00;
						food6 = p6*quantity;
						break;
					case "Tandoori Roti":
						p7 = 30.50;
						food7 = p7*quantity;
						break;
					case "Jeera Rice":
						p8 = 60.50;
						food8 = p8*quantity;
						break;
					case "Amritsari Naan Chana":
						p9 = 60.50;
						food9 = p9*quantity;
						break;
					}
					totalForFoods = food1 + food2 + food3 + food4 + food5 + food6 + food7 + food8 + food9;
					total = totalForFoods + totalForDrinks + totalForDesserts;
					textField.setText(total + "");
					dtm.addRow(new Object[] { foodLabel[i].getText(), quantity, totalPrice, numSpinner[i] });
					return;
				}

			}
		}
	};

	ChangeListener listenerForDesserts = new ChangeListener() {
		public void stateChanged(ChangeEvent e) {

			final int quantity = (int) ((JSpinner) e.getSource()).getValue();
			final int rows = table.getRowCount();

			for (int row = 0; row < rows; row++) {
				if (dtm.getValueAt(row, 3) == e.getSource()) {
					if (dtm.getValueAt(row, 0).equals("Strawberry Cake")) {
						dtm.setValueAt(quantity, row, 1); // obj, row,
						pr = de1 * quantity; // column
						dtm.setValueAt(de1 * quantity, row, 2);
					} else if (dtm.getValueAt(row, 0).equals("Chocolate Cake")) {
						dtm.setValueAt(quantity, row, 1); // obj, row, // column
						dtm.setValueAt(de2 * quantity, row, 2);
						pr1 = de2 * quantity;
					}
					if (quantity == 0) {
						dtm.removeRow(row);
					}
					totalForDesserts = pr + pr1;
					total = totalForFoods + totalForDrinks + totalForDesserts;
					textField.setText(total + "");
					return;
				}
			}

			// there was no row with this JSpinner, so we have to add it
			for (int i = 0; i < DESSERT_ELEMENTS; i++) {
				// looking for the "clicked" JSpinner
				if (numSpinnerDesserts[i] == e.getSource()) {
					totalPrice = priceDesserts[i];
					switch (dessertLabel[i].getText()) {
					case "Strawberry Cake":
						de1 = 20.50;
						pr = de1*quantity;
						break;
					case "Chocolate Cake":
						de2 = 30.00;
						pr1 = de2*quantity;
						break;
					}
					totalForDesserts = pr + pr1;
					total = totalForFoods + totalForDrinks + totalForDesserts;
					textField.setText(total + "");
					dtm.addRow(new Object[] { dessertLabel[i].getText(), quantity, totalPrice, numSpinnerDesserts[i] });
					return;
				}

			}
		}

	};

	ChangeListener listenerForDrinks = new ChangeListener() {
		public void stateChanged(ChangeEvent e) {

			final int quantity = (int) ((JSpinner) e.getSource()).getValue();
			final int rows = table.getRowCount();
			for (int row = 0; row < rows; row++) {
				if (dtm.getValueAt(row, 3) == e.getSource()) {
					if (dtm.getValueAt(row, 0).equals("Red Beer")) {
						dtm.setValueAt(quantity, row, 1);
						dtm.setValueAt(d1 * quantity, row, 2);
						drink1 = d1 * quantity;

					} else if (dtm.getValueAt(row, 0).equals("Chocolate Shake")) {
						dtm.setValueAt(quantity, row, 1); // obj, row,
															// column
						dtm.setValueAt(d2 * quantity, row, 2);
						drink2 = d2 * quantity;

					} else if (dtm.getValueAt(row, 0).equals("Kitkat Shake")) {

						dtm.setValueAt(quantity, row, 1);
						dtm.setValueAt(d3 * quantity, row, 2);
						drink3 = d3 * quantity;

					} else if (dtm.getValueAt(row, 0).equals("Lemon Soda")) {

						dtm.setValueAt(quantity, row, 1);
						dtm.setValueAt(d4 * quantity, row, 2);
						drink4 = d4 * quantity;

					} else if (dtm.getValueAt(row, 0).equals("Aerated Drinks")) {

						dtm.setValueAt(quantity, row, 1); // obj, row,
															// column
						dtm.setValueAt(d5 * quantity, row, 2);
						drink5 = d5 * quantity;

					}
					if (quantity == 0) {
						dtm.removeRow(row);
					}
					totalForDrinks = drink1 + drink2 + drink3 + drink4 + drink5;
					total = totalForFoods + totalForDrinks + totalForDesserts;
					textField.setText(total + "");

					return;
				}
			}

			// there was no row with this JSpinner, so we have to add it
			for (int i = 0; i < DRINK_ELEMENTS; i++) {
				// looking for the "clicked" JSpinner
				if (numSpinnerDrinks[i] == e.getSource()) {
					totalPrice = priceDrinks[i];
					switch (drinkLabel[i].getText()) {
					case "Red Beer":
						d1 = 30.50;
						drink1 = d1*quantity;
						break;
					case "Chocolate Shake":
						d2 = 40.50;
						drink2 = d2*quantity;
						break;
					case "Kitkat Shake":
						d3 = 30.00;
						drink3 = d3*quantity;
						break;
					case "Lemon Soda":
						d4 = 50.00;
						drink4 = d4*quantity;
						break;
					case "Aerated Drinks":
						d5 = 30.00;
						drink5 = d5*quantity;
						break;
					}
					totalForDrinks = drink1 + drink2 + drink3 + drink4 + drink5;
					total = totalForFoods + totalForDrinks + totalForDesserts;
					textField.setText(total + "");
					dtm.addRow(new Object[] { drinkLabel[i].getText(), quantity, totalPrice, numSpinnerDrinks[i] });
					return;
				}

			}

		}
	};

	public void setVisible(boolean b) throws IOException {
	}
}
