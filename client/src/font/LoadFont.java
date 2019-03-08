package font;

import java.io.IOException;
import java.nio.file.Paths;

import org.jsfml.graphics.Font;

/**
 * Klasa s�u�y do �adowania czcionki z pliki i do jej obs�ugi
 * @author Kamil Piec
 */
public class LoadFont {
	/** Pole przechowuj�ce czcionk� */
	private static Font font = new Font();
	
	/**
	 * Metoda �aduj� czcionk� z pliku i przypisuje j� do odpowiedniego pola
	 */
	public static void Load(){
		try{
			font.loadFromFile(Paths.get("src/font/sansation.ttf"));
		} catch(IOException e) {
		    e.printStackTrace();
		}
	}

	/**
	 * Metoda zwraca za�adowan� czcionk�
	 * @return Za�adowana z dysku czcionka
	 */
	public static Font getFont() {
		return font;
	}
}
