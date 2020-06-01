package com.company;


import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GetSettings {

	public static Map<String, String> serverSetting () throws IOException {
		FileReader reader = new FileReader("C:\\IT Learn\\railwayCalculator\\src\\com\\company\\db_connection_set.txt");
		Scanner bf = new Scanner(reader);
		Map<String, String> scs = new HashMap<>();
		while (bf.hasNextLine()) {
			String s = bf.nextLine();

			if (s.contains("URL")) {
				int x = s.indexOf("\"");
				int y = s.lastIndexOf("\"");
				String URL = s.substring(x + 1, y);
				scs.put("URL", URL);
			}

			if (s.contains("USERNAME")) {
				int x = s.indexOf("\"");
				int y = s.lastIndexOf("\"");
				String USERNAME = s.substring(x + 1, y);
				scs.put("USERNAME", USERNAME);
			}

			if (s.contains("PASSWORD")) {
				int x = s.indexOf("\"");
				int y = s.lastIndexOf("\"");
				String PASSWORD = s.substring(x + 1, y);
				scs.put("PASSWORD", PASSWORD);
			}
		}
		reader.close();
		return scs;
	}
}

