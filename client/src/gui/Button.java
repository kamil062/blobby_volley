package gui;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.Text;
import org.jsfml.system.Vector2f;
import org.jsfml.window.Mouse;

import font.LoadFont;

/**
 * Klasa s�u�y do obs�ugi przycisku w menu logowania
 * @author Kamil Piec
 */
public class Button {
	/** Rozmiar przycisku */
	private Vector2f size;
	/** Pozycja przycisku na ekranie */
	private Vector2f position;
	/** Tekst wy�wietlany na przycisku */
	private String text;
	/** Czy przycisk zosta� naci�ni�ty */
	private boolean clicked = false;
	/** Pole przechowuje grafik� z przyciskiem */
	private RectangleShape shape;
	/** Pole przechowuje tekst wy�wietlany na przycisku */
	private Text text1;
	
	/**
	 * Konstruktor pozwalaj�cy zainicjalizowa� atrybuty przycisku
	 * @param text Tekst wy�wietlany na przycisku
	 * @param size Rozmiar przycisku
	 * @param position Pozycja przycisku na ekranie
	 * @param fillColor Kolor t�a przyciski
	 * @param outlineColor Kolor obramowania przycisku
	 * @param textColor Kolor tekstu
	 * @param thickness Grubo�� obramowania przycisku
	 */
	Button(String text, Vector2f size, Vector2f position, Color fillColor, Color outlineColor, Color textColor, int thickness){
		setSize(size);
		setPosition(position);
		setText(text);
		
		text1 = new Text(text, LoadFont.getFont());
		text1.setColor(textColor);
		text1.setPosition(position);
		text1.setCharacterSize((int)(size.y - 10));
		
		shape = new RectangleShape(size);
		shape.setPosition(position);
		shape.setFillColor(fillColor);
		shape.setOutlineColor(outlineColor);
		shape.setOutlineThickness(thickness);
	}

	/**
	 * Metoda rysuje gotowy przycisk na ekranie
	 */
	public void draw(){ 
		text1.setString(text);
		text1.setPosition(position.x + (size.x - text1.getLocalBounds().width) / 2, position.y);
		Window.getWindow().draw(shape);
		Window.getWindow().draw(text1); 
	}
	
	/**
	 * Metoda sprawdza czy przycisk zosta� naci�niety i modyfikuje odpowiednie pole klasy
	 */
	public void checkClick(){
		FloatRect buttonBoundingBox = shape.getGlobalBounds();
		clicked = false;
		if(Window.mouseButtonReleased)
			if(buttonBoundingBox.contains(Mouse.getPosition(Window.getWindow()).x, Mouse.getPosition(Window.getWindow()).y)){
				clicked = true;
				Window.mouseButtonReleased = false;
			}
	}
	
	/**
	 * Metoda zwraca rozmiar przycisku
	 * @return Rozmiar przycisku
	 */
	public Vector2f getSize() { return size; }

	/**
	 * Metoda pozwala zmieni� rozmiar przycisku
	 * @param size nowy rozmiar przycisku
	 */
	public void setSize(Vector2f size) { this.size = size; }

	/**
	 * Metoda pozwala pobra� pozycj� przycisku
	 * @return Pozycja przycisku na ekranie
	 */
	public Vector2f getPosition() { return position; }

	/**
	 * Metoda pozwala zmieni� pozycj� przycisku
	 * @param position Nowa pozycja przycisku na ekranie
	 */
	public void setPosition(Vector2f position) { this.position = position; }

	/**
	 * Metoda pozwala pobra� tekst wy�wietlany na przycisku
	 * @return Tekst wy�wietlany na przycisku
	 */
	public String getText() { return text; }

	/**
	 * Metoda pozwala ustawi� tekst wy�wietlany na przycisku
	 * @param text Nowy tekst wy�wietlany na przycisku
	 */
	public void setText(String text) { this.text = text; }

	/**
	 * Metoda zwraca informacj� czy przycisk zosta� naci�ni�ty
	 * @return true je�li zosta� naci�ni�ty, false je�li nie
	 */
	public boolean isClicked() { return clicked; }

	/**
	 * Metoda pozwala ustawi� stan przycisku
	 * @param clicked Nowy stan przycisku
	 */
	public void setClicked(boolean clicked) { this.clicked = clicked; }

	/**
	 * Metoda pozwala pobra� grafik� przycisku
	 * @return Grafika przycisku
	 */
	public RectangleShape getShape() { return shape; }

	/**
	 * Metoda pozwala ustawi� grafik� przycisku
	 * @param shape Nowa grafika przycisku
	 */
	public void setShape(RectangleShape shape) { this.shape = shape; }
}
