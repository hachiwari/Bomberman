package dev.game.bomberman.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Klasa implementujaca przydatne metody.
 * @author Tomasz Kurek
 *
 */
public class Utils {
	
	/**
	 * Metoda odpowiadajaca za wczytanie pliku z podanej sciezki.
	 * @param path Sciezka do pliku
	 * @return Zawartosc pliku
	 */
	public static String loadFileAsString(String path) {
		StringBuilder builder = new StringBuilder();
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
			String line;
			while((line = reader.readLine()) != null){
				builder.append(line + "\n");
			}
			reader.close();
		} catch(IOException e) {
			 e.printStackTrace();
		}
		
		return builder.toString();
	}
	
	/**
	 * Metoda odpowiadajaca za konwersje ze Stringa do Integera.
	 * @param number String
	 * @return Integer
	 */
	public static int parseToInt(String number) {
		try {
			return Integer.parseInt(number);
		} catch(NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * Metoda odpowiadajaca za konwersje ze Integera do Stringa.
	 * @param number Integer
	 * @return String
	 */
	public static String parseToString(int number) {
		try {
			return Integer.toString(number);
		} catch(NumberFormatException e) {
			e.printStackTrace();
			return "";
		}
	}
}
