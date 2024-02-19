package dev.blue.ide;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileParser {
	public static List<String> readLines(File file) {
		BufferedReader reader = null;
		List<String> lines = new ArrayList<String>();
		String currentLine = null;

        try {
        	reader = new BufferedReader(new FileReader(file));
			while ((currentLine = reader.readLine()) != null) {
			    lines.add(currentLine);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return lines;
	}
}
