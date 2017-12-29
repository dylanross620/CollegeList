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
	
	private JTextArea list;
	
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
		
		list = new JTextArea();
		list.setEditable(false);
		list.setRows(11);
		list.setText("College:\tNum Appearances:");
		pane.add(list);
		
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
		
		String text = "College:\tNum Appearances:";
		
		int x;
		if (collegeList.size() < 10)
			x = collegeList.size();
		else
			x = 10;
		for (int i = 0; i < x; i++) {
			text += "\n" + (i + 1) + ": " + collegeList.get(i).getName() + "\t" + collegeList.get(i).getNumAppear();
		}
		
		list.setText(text);
	}
	
	public void printFull() {
		JDialog fullList = new JDialog(this, "College List");
		fullList.setResizable(false);
		fullList.setAlwaysOnTop(true);
		
		JTextArea listFull = new JTextArea();
		listFull.setEditable(false);
		listFull.setRows(21);
		
		List<College> collegeList = main.getCollegeList();
		
		String text = "College:\tNum Appearances:";
		for (int i = 0; i < collegeList.size(); i++) {
			text += "\n" + (i + 1) + ": " + collegeList.get(i).getName() + "\t" + collegeList.get(i).getNumAppear();
		}	
		listFull.setText(text);
		
		fullList.getContentPane().add(listFull);
		fullList.pack();
		fullList.setLocationRelativeTo(null);
		fullList.setModal(true);
		fullList.setVisible(true);
	}
}
