package de.exxcellent.challenge;

/**
 * The main class of the solution. This class outputs the minimal spread of two different
 * csv files, namely weather.csv and football.csv.
 *
 * @author Rafael Teixeira <teixeirarc@hotmail.com>
 * @version 0.1
 */
public final class App {

    /**
     * This is the main entry method of your program.
     * @param args The CLI arguments passed
     */
	
    public static void main(String... args) {

		String[] columnTitles = {"MxT", "MnT", "Goals", "Goals Allowed"};

		TableReader tableReader = new CsvTableReader("football.csv", columnTitles);
		var fileToMap = tableReader.convertFileToMap();

		SpreadCalculator spreadCalculator = new SpreadCalculator(fileToMap);
		System.out.printf("Minimum spread: %s%n", spreadCalculator.getMinimumSpread());
    }
}