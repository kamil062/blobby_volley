package gui;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.Text;
import org.jsfml.system.Vector2f;
import org.jsfml.window.Mouse;

import font.LoadFont;

/**
 * Klasa s³u¿y do obs³ugi przycisku w menu logowania
 * @author Kamil Piec
 */
public class Button {
	/** Rozmiar przycisku */
	private Vector2f size;
	/** Pozycja przycisku na ekranie */
	private Vector2f position;
	/** Tekst wyœwietlany na przycisku */
	private String text;
	/** Czy przycisk zosta³ naciœniêty */
	private boolean clicked = false;
	/** Pole przechowuje grafikê z przyciskiem */
	private RectangleShape shape;
	/** Pole przechowuje tekst wyœwietlany na przycisku */
	private Text text1;
	
	/**
	 * Konstruktor pozwalaj¹cy zainicjalizowaæ atrybuty przycisku
	 * @param text Tekst wyœwietlany na przycisku
	 * @param size Rozmiar przycisku
	 * @param position Pozycja przycisku na ekranie
	 * @param fillColor Kolor t³a przyciski
	 * @param outlineColor Kolor obramowania przycisku
	 * @param textColor Kolor tekstu
	 * @param thickness Gruboœæ obramowania przycisku
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
	 * Metoda sprawdza czy przycisk zosta³ naciœniety i modyfikuje odpowiednie pole klasy
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
	 * Metoda pozwala zmieniæ rozmiar przycisku
	 * @param size nowy rozmiar przycisku
	 */
	public void setSize(Vector2f size) { this.size = size; }

	/**
	 * Metoda pozwala pobraæ pozycjê przycisku
	 * @return Pozycja przycisku na ekranie
	 */
	public Vector2f getPosition() { return position; }

	/**
	 * Metoda pozwala zmieniæ pozycjê przycisku
	 * @param position Nowa pozycja przycisku na ekranie
	 */
	public void setPosition(Vector2f position) { this.position = position; }

	/**
	 * Metoda pozwala pobraæ tekst wyœwietlany na przycisku
	 * @return Tekst wyœwietlany na przycisku
	 */
	public String getText() { return text; }

	/**
	 * Metoda pozwala ustawiæ tekst wyœwietlany na przycisku
	 * @param text Nowy tekst wyœwietlany na przycisku
	 */
	public void setText(String text) { this.text = text; }

	/**
	 * Metoda zwraca informacjê czy przycisk zosta³ naciœniêty
	 * @return true jeœli zosta³ naciœniêty, false jeœli nie
	 */
	public boolean isClicked() { return clicked; }

	/**
	 * Metoda pozwala ustawiæ stan przycisku
	 * @param clicked Nowy stan przycisku
	 */
	public void setClicked(boolean clicked) { this.clicked = clicked; }

	/**
	 * Metoda pozwala pobraæ grafikê przycisku
	 * @return Grafika przycisku
	 */
	public RectangleShape getShape() { return shape; }

	/**
	 * Metoda pozwala ustawiæ grafikê przycisku
	 * @param shape Nowa grafika przycisku
	 */
	public void setShape(RectangleShape shape) { this.shape = shape; }
}
