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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import dao.DataDAO;
import net.proteanit.sql.DbUtils;

public class View extends JFrame implements ActionListener{
	
	JFrame frame;
	JPanel searchPanel, tablePanel, menuButtonPanel, refreshButtonPanel;
	JTextField searchTextField;
	JButton searchButton, menuButton, refreshButton;
	JTable dataTable;
	JScrollPane dataScrollPane;
	
	public View () {
		
		initFrame();
		
	}
	
	public void initSearch() {
		
		searchPanel = new JPanel();
		searchPanel.setBackground(Color.CYAN);
				
		searchTextField = new JTextField();
		searchTextField.setPreferredSize(new Dimension(350, 30));
		searchButton = new JButton("Search");
		searchButton.setFocusable(false);
		searchButton.setBackground(Color.GRAY);
		searchButton.setForeground(Color.CYAN);
				
		//addActionListener Search Button
		searchButton.addActionListener(this);
		
		searchPanel.add(searchTextField);
		searchPanel.add(searchButton);
		
		add(searchPanel, BorderLayout.NORTH);
		
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
	
	public void initFooterButton() {
		
		menuButtonPanel = new JPanel();
		menuButtonPanel.setLayout(new GridLayout(1, 1));
		menuButtonPanel.setBackground(Color.CYAN);
		
		menuButton = new JButton("Menu");
		menuButton.setPreferredSize(new Dimension(500, 50));
		menuButton.setFocusable(false);
		menuButton.setBackground(Color.DARK_GRAY);
		menuButton.setForeground(Color.CYAN);
		
		menuButton.addActionListener(this);
		
		menuButtonPanel.add(menuButton);
		
		add(menuButtonPanel, BorderLayout.SOUTH);
		
	}
	
	public void initFrame() throws HeadlessException {
		
		frame = new JFrame();
		initSearch();
		initTableData();
		initFooterButton();
		setSize(500, 450);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("View Menu");
		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == searchButton) {
			setTable();
		} else if (e.getSource() == menuButton) {
			new Menu();
			this.dispose();
		}
		
	}
	
	private void setTable() {
		searchButton.addActionListener(e -> dataTable.setModel(DbUtils.resultSetToTableModel
				(new DataDAO().search(searchTextField.getText()))));
    }
	
}
