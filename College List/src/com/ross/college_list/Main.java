package com.ross.college_list;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

public class Main {

	private List<College> collegeList = new ArrayList<College>();
	private Window win;
	
	public static void main(String[] args) {
		Main main = new Main();
		main.init();
	}
	
	public void add(String name) {
		if (name.equals(""))
			return;
		boolean contained = false;
		int index = -1;
		
		for (int i = 0; i < collegeList.size(); i++) {
			if (collegeList.get(i).getName().equals(name)) {
				contained = true;
				index = i;
				break;
			}
		}
		
		if (contained)
			collegeList.get(index).addAppear();
		else
			collegeList.add(new College(name));
		
		collegeList.sort((College e1, College e2) -> e2.getNumAppear() - e1.getNumAppear());
		win.printList();
	}
	
	public List<College> getCollegeList() {
		return collegeList;
	}

	public void remove(String name) {
		boolean contained = false;
		int index = -1;
		for (int i = 0; i < collegeList.size(); i++) {
			if (collegeList.get(i).getName().equals(name)) {
				contained = true;
				index = i;
				break;
			}
		}
		
		if (contained)
			collegeList.remove(index);
		
		win.printList();
	}
	
	public void init() {
		win = new Window(this);
	}
	
	public void load() {
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(null);
		fc.setFileFilter(new Filter());
		
		FileInputStream in = null;
		if (fc.showOpenDialog(win) == JFileChooser.APPROVE_OPTION) {
			try {
				in = new FileInputStream(fc.getSelectedFile());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		else {
			//System.out.println("Error loading or loading cancelled by user");
			return;
		}
		
		try {
			ObjectInputStream objIn = new ObjectInputStream(in);
			collegeList = (ArrayList<College>) objIn.readObject();
			objIn.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		win.printList();
	}
	
	public void save() {
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(null);
		
		FileOutputStream out = null;
		if (fc.showSaveDialog(win) == JFileChooser.APPROVE_OPTION) {
			try {
				String filename = fc.getSelectedFile().toString();
				if (!filename.endsWith(".clg"))
					filename += ".clg";
				out = new FileOutputStream(filename);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		else {
			//System.out.println("Error saving or save cancelled by user");
			return;
		}
		
		try {
			ObjectOutputStream objOut = new ObjectOutputStream(out);
			objOut.writeObject(collegeList);
			objOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
