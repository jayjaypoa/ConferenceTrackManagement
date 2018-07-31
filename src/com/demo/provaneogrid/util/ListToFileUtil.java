package com.demo.provaneogrid.util;

import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.charset.Charset;

public class ListToFileUtil {

	public static List<String> getListFromFile(String fileName)  {
	    List<String> lines = new ArrayList<>();
	    
	    Path file = Paths.get(fileName);
		try {
			lines = Files.readAllLines(file, Charset.defaultCharset());
		} catch (IOException e) {
			System.err.println("Error: " + e.getMessage());
		}
	    
	    return lines;
	}
	
}
