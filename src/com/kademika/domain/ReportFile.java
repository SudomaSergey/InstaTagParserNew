package com.kademika.domain;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ReportFile {
	
	public ReportFile(ArrayList<Tag> tagsList){
		try {
			writeToFile(tagsList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void writeToFile(ArrayList<Tag> tagsList) throws IOException {
		String date = getCurrentTime();
		File file = new File("tags_" + date + ".txt");

	    Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "UTF-8"));

	    for(Tag tag : tagsList){
	    	out.write(tag.toString());
	    }
	    
	    out.close();
		
	}

	private String getCurrentTime() {
		   DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd_HH-mm");
		   String date = dateFormat.format(new Date());
		   String dateStr = date. toString();
		   return dateStr;
	}
	
	

}
