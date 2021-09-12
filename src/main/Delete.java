package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import dao.DataDAO;

public class Delete extends JFrame implements ActionListener{

	JFrame frame;
	JPanel mainDeletePanel, tablePanel, deletePanel;
	JButton saveButton, cancelButton;
	JLabel codeLabel;
	JTextField codeTextField;
	JTable dataTable;
	JScrollPane dataScrollPane;
	
	public Delete() {
		
		initFrame();
	}
	
	public void initTableData() {
		
		tablePanel = new JPanel(); 
		tablePanel.setBackground(Color.CYAN);
		
		DataDAO dataDAO = new DataDAO();
		Vector<Object> column = new Vector<>();
		
		column.add("Code");
		column.add("Name");
		column.add("Price");
		column.add("Stock");
		
		dataTable = new JTable(dataDAO.getData(), column);
		dataTable.setEnabled(false);
		
		dataScrollPane = new JScrollPane(dataTable);
		dataScrollPane.setPreferredSize(new Dimension(450, 200));
		
		tablePanel.add(dataScrollPane);
		
		add(tablePanel, BorderLayout.CENTER);
		
	}
	
	public void initDeleteData() {
		
		deletePanel = new JPanel();
		deletePanel.setBackground(Color.CYAN);
		
		codeLabel = new JLabel("Code:");
		codeTextField = new JTextField();
		codeTextField.setPreferredSize(new Dimension(200, 50));
		
		deletePanel.add(codeLabel);
		deletePanel.add(codeTextField);
		
		add(deletePanel, BorderLayout.NORTH);
		
	}
	
	public void initFooterButton() {
		
		mainDeletePanel = new JPanel();
		mainDeletePanel.setLayout(new GridLayout(1, 2, 0, 50));

		saveButton = new JButton("Save");
		saveButton.setPreferredSize(new Dimension(75, 75));
		saveButton.setFocusable(false);
		saveButton.setBackground(Color.DARK_GRAY);
		saveButton.setForeground(Color.CYAN);
		
		cancelButton = new JButton("Cancel");
		cancelButton.setFocusable(false);
		cancelButton.setBackground(Color.LIGHT_GRAY);
		cancelButton.setForeground(Color.CYAN);
		
		mainDeletePanel.add(saveButton);
		mainDeletePanel.add(cancelButton);
		
		//addActionListener Footer Button
		saveButton.addActionListener(this);
		cancelButton.addActionListener(this);
		
		add(mainDeletePanel, BorderLayout.SOUTH);
		
	}
	
	public void initFrame() throws HeadlessException {
		
		frame = new JFrame();
		initTableData();
		initDeleteData();
		initFooterButton();
		setSize(500, 450);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("Delete Menu");
		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == saveButton) {
			
			String code = codeTextField.getText();
			
			if (validInput(code)) {
				
				DataDAO dataDAO = new DataDAO();
				dataDAO.deleteData(code);
				
			}
			
		} else if (e.getSource() == cancelButton) {
			new Menu();
			this.dispose();
		}
		
	}
	
	public boolean validInput(String code) {
		if (!code.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Successfully deleted!", "Message", JOptionPane.INFORMATION_MESSAGE);
			return true;
		}
		
		JOptionPane.showMessageDialog(null, "Please, fill the code!", "Warning", JOptionPane.WARNING_MESSAGE);
		return false;
		
	}

}
