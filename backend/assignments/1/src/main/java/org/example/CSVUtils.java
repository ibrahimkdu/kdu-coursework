package org.example;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVUtils {

  private CSVUtils()
  {

  }
    public static ArrayList<String[]> parseCSV(String file, boolean skipHeader) throws IOException {
        ArrayList<String[]> data = new ArrayList<>();
        CSVReader reader = new CSVReaderBuilder(new FileReader(file)).withSkipLines(skipHeader ? 1 : 0).build();
        String[] line;
        while ((line = reader.readNext()) != null) {
            data.add(line);
        }
        return data;
    }
}
