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

public class Update extends JFrame implements ActionListener {
	
	JFrame frame;
	JPanel mainUpdatePanel, updatePanel;
	JLabel codeLabel, nameLabel, priceLabel, stockLabel;
	JTextField codeTextField, nameTextField, priceTextField, stockTextField;
	JButton saveButton, cancelButton;
	
	public Update() {
		
		initFrame();
		
	}
	
	public void updateData() {
		
		mainUpdatePanel = new JPanel();
		mainUpdatePanel.setBackground(Color.CYAN);
		
		updatePanel = new JPanel();
		updatePanel.setLayout(new GridLayout(4, 2, 0, 30));
		updatePanel.setBackground(Color.CYAN);
		
		codeLabel = new JLabel("Code:");
		codeTextField = new JTextField();
		codeTextField.setPreferredSize(new Dimension(200, 50));
		nameLabel = new JLabel("Name:");
		nameTextField = new JTextField();
		nameTextField.setPreferredSize(new Dimension(200, 50));
		priceLabel = new JLabel("Price:");
		priceTextField = new JTextField();
		priceTextField.setPreferredSize(new Dimension(200, 50));
		stockLabel = new JLabel("Stock:");
		stockTextField = new JTextField();
		stockTextField.setPreferredSize(new Dimension(200, 50));
		
		updatePanel.add(codeLabel);
		updatePanel.add(codeTextField);
		updatePanel.add(nameLabel);
		updatePanel.add(nameTextField);
		updatePanel.add(priceLabel);
		updatePanel.add(priceTextField);
		updatePanel.add(stockLabel);
		updatePanel.add(stockTextField);
		
		mainUpdatePanel.add(updatePanel, BorderLayout.CENTER);

		add(mainUpdatePanel);
		
	}
	
	public void initFooterButton() {
		
		mainUpdatePanel = new JPanel();
		mainUpdatePanel.setLayout(new GridLayout(1, 2, 0, 50));

		saveButton = new JButton("Save");
		saveButton.setPreferredSize(new Dimension(75, 75));
		saveButton.setFocusable(false);
		saveButton.setBackground(Color.DARK_GRAY);
		saveButton.setForeground(Color.CYAN);
		
		cancelButton = new JButton("Cancel");
		cancelButton.setFocusable(false);
		cancelButton.setBackground(Color.LIGHT_GRAY);
		cancelButton.setForeground(Color.CYAN);
		
		mainUpdatePanel.add(saveButton);
		mainUpdatePanel.add(cancelButton);
		
		//addActionListener Footer Button
		saveButton.addActionListener(this);
		cancelButton.addActionListener(this);
		
		add(mainUpdatePanel, BorderLayout.SOUTH);
		
	}
	
	public void initFrame() throws HeadlessException {
		
		frame = new JFrame();
		updateData();
		initFooterButton();
		setSize(500, 450);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("Update Menu");
		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == saveButton) {
			
			String code = codeTextField.getText();
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
				dataDAO.updateData(code, name, price, stock);
				
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
					JOptionPane.showMessageDialog(null, "Successfully updated", "Message", JOptionPane.INFORMATION_MESSAGE);
					return true;
				}
			}
		}
		
		JOptionPane.showMessageDialog(null, "Fill out the required fields!", "Warning", JOptionPane.WARNING_MESSAGE);
		return false;
	}
	
}
