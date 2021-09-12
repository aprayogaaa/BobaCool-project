package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Menu extends JFrame implements ActionListener{
	
	JFrame frame;
	JPanel mainMenuPanel, titlePanel, imagePanel, menuPanel;
	ImageIcon imageIcon;
	JLabel titleLabel, imageLabel;
	JButton insertButton, viewButton, updateButton, deleteButton;
	
	public Menu() {
		initFrame();
	}
	
	public void initMenu() {
		
		mainMenuPanel = new JPanel();
		mainMenuPanel.setLayout(new BorderLayout());
		mainMenuPanel.setBackground(Color.CYAN);
		
		titlePanel = new JPanel();
		titlePanel.setBackground(Color.CYAN);
		
		titleLabel = new JLabel("Welcome to BobaCool");
		titleLabel.setForeground(Color.GRAY);
		titleLabel.setFont(new Font("MV Boli", Font.PLAIN, 40));
		
		imagePanel = new JPanel();
		imagePanel.setBackground(Color.CYAN);
		
		imageIcon = new ImageIcon("boba.png");
		imageLabel = new JLabel();
		imageLabel.setIcon(imageIcon);
		
		menuPanel = new JPanel();
		menuPanel.setLayout(new GridLayout(2, 2, 20, 20));
		menuPanel.setBackground(Color.CYAN);
		
		insertButton = new JButton("Insert Data");
		insertButton.setPreferredSize(new Dimension(75, 75));
		insertButton.setFocusable(false);
		insertButton.setBackground(Color.GRAY);
		insertButton.setForeground(Color.CYAN);
		insertButton.setFont(new Font("MV Boli", Font.PLAIN, 20));
		
		viewButton = new JButton("View Data");
		viewButton.setFocusable(false);
		viewButton.setBackground(Color.GRAY);
		viewButton.setForeground(Color.CYAN);
		viewButton.setFont(new Font("MV Boli", Font.PLAIN, 20));
		
		updateButton = new JButton("Update Data");
		updateButton.setFocusable(false);
		updateButton.setBackground(Color.GRAY);
		updateButton.setForeground(Color.CYAN);
		updateButton.setFont(new Font("MV Boli", Font.PLAIN, 20));
		
		deleteButton = new JButton("Delete Data");
		deleteButton.setFocusable(false);
		deleteButton.setBackground(Color.GRAY);
		deleteButton.setForeground(Color.CYAN);
		deleteButton.setFont(new Font("MV Boli", Font.PLAIN, 20));
		
		titlePanel.add(titleLabel);
		imagePanel.add(imageLabel);
		menuPanel.add(insertButton);
		menuPanel.add(viewButton);
		menuPanel.add(updateButton);
		menuPanel.add(deleteButton);
		
		mainMenuPanel.add(titlePanel, BorderLayout.NORTH);
		mainMenuPanel.add(imagePanel, BorderLayout.CENTER);
		mainMenuPanel.add(menuPanel, BorderLayout.SOUTH);
		
		//addActionListener Menu Button
		insertButton.addActionListener(this);
		viewButton.addActionListener(this);
		updateButton.addActionListener(this);
		deleteButton.addActionListener(this);
		
		add(mainMenuPanel);
		
	}
		
	public void initFrame() throws HeadlessException {
		
		frame = new JFrame();
		initMenu();
		setSize(500, 450);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("BobaCool Menu");
		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == insertButton) {
			new Insert();
			this.dispose();
		} else if (e.getSource() == viewButton) {
			new View();
			this.dispose();
		} else if (e.getSource() == updateButton) {
			new Update();
			this.dispose();
		} else if (e.getSource() == deleteButton) {
			new Delete();
			this.dispose();
		}
		
	}

}
