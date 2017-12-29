package com.ross.college_list;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class Filter extends FileFilter{

		@Override
		public boolean accept(File file) {
			return file.toString().endsWith(".clg");
		}

		@Override
		public String getDescription() {
			return "College lists (*.clg)";
		};
}
