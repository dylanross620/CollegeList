package com.ross.college_list;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Window extends JFrame{
	
	private Main main;
	
	private JTextArea nameList, numList;
	
	public Window(Main main) {
		super("College List");
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.main = main;
		
		setSize(900, 500);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container pane = getContentPane();
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
		
		JPanel listPanel = new JPanel();
		nameList = new JTextArea();
		nameList.setEditable(false);
		nameList.setRows(11);
		nameList.setColumns(20);
		nameList.setText("College:");
		nameList.setLineWrap(true);
		listPanel.add(nameList);
		numList = new JTextArea();
		numList.setEditable(false);
		numList.setRows(11);
		numList.setText("Num Appearances:");
		numList.setColumns(numList.getText().length() + 1);
		numList.setLineWrap(true);
		listPanel.add(numList);
		pane.add(listPanel);
		
		JButton allButton = new JButton("Show All");
		allButton.addActionListener((e) -> printFull());
		JPanel allPanel = new JPanel();
		allPanel.add(allButton);
		pane.add(allPanel);
		
		JPanel collegePane = new JPanel();
		collegePane.setLayout(new FlowLayout());
		JTextField nameField = new JTextField("College Name");
		nameField.setColumns(10);
		nameField.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				if (nameField.getText().equals("College Name")) 
					nameField.setText("");
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				
				
			}});
		collegePane.add(nameField);
		JButton addButton = new JButton("Add");
		addButton.addActionListener((e) -> {main.add(nameField.getText()); nameField.setText("");});
		collegePane.add(addButton);
		JButton removeButton = new JButton("Remove");
		removeButton.addActionListener((e) -> {main.remove(nameField.getText()); nameField.setText("");});
		collegePane.add(removeButton);
		pane.add(collegePane);
		
		JPanel savePane = new JPanel();
		JButton saveButton = new JButton("Save");
		saveButton.addActionListener((e) -> main.save());
		savePane.add(saveButton);
		JButton openButton = new JButton("Open");
		openButton.addActionListener((e) -> main.load());
		savePane.add(openButton);
		pane.add(savePane);
		
		pack();
		setLocationRelativeTo(null);
	}
	
	public void printList() {
		List<College> collegeList = main.getCollegeList();
		
		String nameText = "College:";
		String numText = "Num Appearances:";
		
		int x;
		if (collegeList.size() < 10)
			x = collegeList.size();
		else
			x = 10;
		for (int i = 0; i < x; i++) {
			String nameAdd = "\n" + (i + 1) + ": " + collegeList.get(i).getName();
			nameText += nameAdd;
			numText += "\n" + collegeList.get(i).getNumAppear();
			for (int n = 0; n < (int) nameAdd.length() / (nameList.getColumns() - 1); n++)
				numText += "\n";
		}
		
		nameList.setText(nameText);
		numList.setText(numText);
	}
	
	public void printFull() {
		JDialog fullList = new JDialog(this, "College List");
		fullList.setResizable(false);
		fullList.setAlwaysOnTop(true);
		
		JPanel listPane = new JPanel();
		
		JTextArea namesFull = new JTextArea();
		namesFull.setEditable(false);
		namesFull.setRows(21);
		namesFull.setColumns(20);
		namesFull.setLineWrap(true);
		
		JTextArea numsFull = new JTextArea();
		numsFull.setEditable(false);
		numsFull.setRows(21);
		numsFull.setColumns("Num Appearances:".length() + 1);
		numsFull.setLineWrap(true);
		
		List<College> collegeList = main.getCollegeList();
		
		String nameText = "College:";
		String numText = "Num Appearances:";
		for (int i = 0; i < collegeList.size(); i++) {
			String nameAdd = "\n" + (i + 1) + ": " + collegeList.get(i).getName();
			nameText += nameAdd;
			numText += "\n" + collegeList.get(i).getNumAppear();
			for (int n = 0; n < (int) nameAdd.length() / (nameList.getColumns() - 1); n++)
				numText += "\n";
		}
		namesFull.setText(nameText);
		numsFull.setText(numText);
		
		listPane.add(namesFull);
		listPane.add(numsFull);
		
		fullList.getContentPane().add(listPane);
		fullList.pack();
		fullList.setLocationRelativeTo(null);
		fullList.setModal(true);
		fullList.setVisible(true);
	}
}
