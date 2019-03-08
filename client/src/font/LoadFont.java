package font;

import java.io.IOException;
import java.nio.file.Paths;

import org.jsfml.graphics.Font;

/**
 * Klasa s³u¿y do ³adowania czcionki z pliki i do jej obs³ugi
 * @author Kamil Piec
 */
public class LoadFont {
	/** Pole przechowuj¹ce czcionkê */
	private static Font font = new Font();
	
	/**
	 * Metoda ³adujê czcionkê z pliku i przypisuje j¹ do odpowiedniego pola
	 */
	public static void Load(){
		try{
			font.loadFromFile(Paths.get("src/font/sansation.ttf"));
		} catch(IOException e) {
		    e.printStackTrace();
		}
	}

	/**
	 * Metoda zwraca za³adowan¹ czcionkê
	 * @return Za³adowana z dysku czcionka
	 */
	public static Font getFont() {
		return font;
	}
}
