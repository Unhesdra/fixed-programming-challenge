package de.exxcellent.challenge;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CsvTableReader implements TableReader {

    private final String filePath;
    private final String[] columns;
    private final Integer[] columnIndexes = new Integer[2];
    private final Map<String, List<Integer>> convertedMap;

    public CsvTableReader(String filePath, String[] columns) {
        this.filePath = filePath;
        this.columns = columns;
        this.convertedMap =  new HashMap<>();
    }

    @Override
    public Map<String, List<Integer>> convertFileToMap() {
        try {
            File csvFile = new File(filePath);
            Scanner csvScanner = new Scanner(csvFile);
            String columnsTitles = csvScanner.nextLine();
            getColumnIndexes(columnsTitles);
            while(csvScanner.hasNextLine()) {
                getRecordsFromLine(csvScanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return convertedMap;
    }

    private void getRecordsFromLine(String line) {
        int columnIndex = 1;
        List<Integer> values = new ArrayList<>();

        try(Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(",");
            String key = rowScanner.next();
            while(rowScanner.hasNext()) {
                String value = rowScanner.next();
                if(columnIndex == columnIndexes[0] || columnIndex == columnIndexes[1]) {
                    values.add(Integer.parseInt(value));
                }
                columnIndex++;
            }
            convertedMap.put(key, values);
        }
    }

    private void getColumnIndexes(String columnTitles) {
        int columnIndex = 0;
        int counter = 0;
        try(Scanner titlesScanner = new Scanner(columnTitles)) {
            titlesScanner.useDelimiter(",");
            while(titlesScanner.hasNext()) {
                String columnTitle = titlesScanner.next();
                for (String column : columns) {
                    if (columnTitle.equalsIgnoreCase(column)) {
                        columnIndexes[counter] = columnIndex;
                        counter++;
                    }
                }
                if(counter > 1) {
                    break;
                }
                columnIndex++;
            }
        }
    }
}
