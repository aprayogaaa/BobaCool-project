package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.DataDAO;

public class Insert extends JFrame implements ActionListener{
	
	JFrame frame;
	JPanel mainInsertPanel, insertPanel, footerPanel;
	JLabel nameLabel, priceLabel, stockLabel;
	JTextField nameTextField, priceTextField, stockTextField;
	JButton saveButton, cancelButton;
	
	public Insert() {
		initFrame();
	}
	
	public void insertData() {
		
		mainInsertPanel = new JPanel();
		mainInsertPanel.setBackground(Color.CYAN);
		
		insertPanel = new JPanel();
		insertPanel.setLayout(new GridLayout(3, 2, 0, 30));
		insertPanel.setBackground(Color.CYAN);
		
		nameLabel = new JLabel("Name:");
		nameTextField = new JTextField();
		nameTextField.setPreferredSize(new Dimension(200, 50));
		priceLabel = new JLabel("Price:");
		priceTextField = new JTextField();
		priceTextField.setPreferredSize(new Dimension(200, 50));
		stockLabel = new JLabel("Stock:");
		stockTextField = new JTextField();
		stockTextField.setPreferredSize(new Dimension(200, 50));
		
		insertPanel.add(nameLabel);
		insertPanel.add(nameTextField);
		insertPanel.add(priceLabel);
		insertPanel.add(priceTextField);
		insertPanel.add(stockLabel);
		insertPanel.add(stockTextField);
		
		mainInsertPanel.add(insertPanel, BorderLayout.CENTER);

		add(mainInsertPanel);
		
	}
	
	public void initFooterButton() {
		
		mainInsertPanel = new JPanel();
		mainInsertPanel.setLayout(new GridLayout(1, 2, 0, 50));

		saveButton = new JButton("Save");
		saveButton.setPreferredSize(new Dimension(75, 75));
		saveButton.setFocusable(false);
		saveButton.setBackground(Color.DARK_GRAY);
		saveButton.setForeground(Color.CYAN);
		
		cancelButton = new JButton("Cancel");
		cancelButton.setFocusable(false);
		cancelButton.setBackground(Color.LIGHT_GRAY);
		cancelButton.setForeground(Color.CYAN);
		
		mainInsertPanel.add(saveButton);
		mainInsertPanel.add(cancelButton);
		
		//addActionListener Footer Button
		saveButton.addActionListener(this);
		cancelButton.addActionListener(this);
		
		add(mainInsertPanel, BorderLayout.SOUTH);
		
	}

	public void initFrame() throws HeadlessException {
		
		frame = new JFrame();
		insertData();
		initFooterButton();
		setSize(500, 450);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("Insert Menu");
		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == saveButton) {
			
			String name = nameTextField.getText();
			Integer price;
			try {
				price = Integer.parseInt(priceTextField.getText());
			} catch (Exception e2) {
				price = -1;
			}
			Integer stock;
			try {
				stock = Integer.parseInt(stockTextField.getText());
			} catch (Exception e2) {
				stock = -1;
			}
			
			if (validInput(name, price, stock)) {
				
				DataDAO dataDAO = new DataDAO();
				dataDAO.insertNewData(name, price, stock);
				
			}
			
		} else if (e.getSource() == cancelButton) {
			new Menu();
			this.dispose();
		}	
	}
	
	public boolean validInput(String name, Integer price, Integer stock) {
		if (!name.isEmpty()) {
			if (price >= 0 ) {
				if (stock >= 0) {
					JOptionPane.showMessageDialog(null, "Successfully inserted", "Message", JOptionPane.INFORMATION_MESSAGE);
					return true;
				}
			}
		}
		
		JOptionPane.showMessageDialog(null, "Fill out the required fields!", "Warning", JOptionPane.WARNING_MESSAGE);
		return false;
	}
	
}
