package gui;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.Text;
import org.jsfml.system.Vector2f;
import org.jsfml.window.Mouse;

import font.LoadFont;

/**
 * Metoda s³u¿y do obs³ugi pola tekstowego w menu logowania
 * @author Kamil Piec
 */
public class Input {
	/** Rozmiar pola tekstowego */
	private Vector2f size;
	/** Pozycja pola tekstowego na ekranie */
	private Vector2f position;
	/** Grafika pola tekstowego */
	private RectangleShape shape;
	/** Tekst wyœwietlany w polu tekstowym */
	private String text;
	/** Czy pole tekstowe jest aktywne i mo¿na na nim pisaæ */
	private boolean active = false;
	/** Pole przechowuje tekst wyœwietlany w polu tekstowym */
	private Text text1;

	/**
	 * Konstruktor inicjalizuj¹cy atrybuty pola tekstowego
	 * @param text Tekst wyœwietlany w polu tekstowym
	 * @param size Rozmiar pola tekstowego
	 * @param position Pozycja pola tekstowego na ekranie
	 * @param fillColor Kolor wype³nienia pola tekstowego
	 * @param outlineColor Kolor obramowania pola tekstowego
	 * @param textColor Kolor tekstu w polu tekstowym
	 * @param thickness Gruboœæ obramowania pola tekstowego
	 */
	Input(String text, Vector2f size, Vector2f position, Color fillColor, Color outlineColor, Color textColor, int thickness){
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
	 * Metoda rysuje pole tekstowe na ekranie
	 */
	public void draw(){
		text1.setString(text);
		Window.getWindow().draw(shape);
		Window.getWindow().draw(text1);
	}
	
	/**
	 * Metoda sprawdza czy pole tekstowe zosta³o naciœniête
	 */
	public void checkClick(){
		FloatRect inputBoundingBox = shape.getGlobalBounds();
		if(Window.mouseButtonReleased){
			if(inputBoundingBox.contains(Mouse.getPosition(Window.getWindow()).x, Mouse.getPosition(Window.getWindow()).y))
				active = true;
			else
				active = false;
		}
	}
	
	/**
	 * Metoda zwraca rozmiar pola tekstowego
	 * @return Rozmiar pola tekstowego
	 */
	public Vector2f getSize() { return this.size;}
	
	/**
	 * Metoda pozwala zmieniæ rozmiar pola tekstowego
	 * @param size Nowy rozmiar pola tekstowego
	 */
	public void setSize(Vector2f size) { this.size = size; }
	
	/**
	 * Metoda zwraca pozycjê pola tekstowego
	 * @return Pozycja pola tekstowego
	 */
	public Vector2f getPosition() { return this.position; }
	
	/**
	 * Metoda ustawia pozycjê pola tekstowego
	 * @param position Nowa pozycja pola tekstowego
	 */
	public void setPosition(Vector2f position) { this.position = position; }
	
	/**
	 * Metoda zwraca grafikê pola tekstowego
	 * @return Grafika pola tekstowego
	 */
	public RectangleShape getShape() { return this.shape; }
	
	/**
	 * Metoda pozwala ustawiæ grafikê pola tekstowego
	 * @param shape Nowa grafika pola tekstowego
	 */
	public void setShape(RectangleShape shape) { this.shape = shape; }
	
	/** 
	 * Metoda zwraca tekst wyœwietlany w polu tekstowym
	 * @return Tekst wyœwietlany w polu tekstowym
	 */
	public String getText() {return this.text; }
	
	/**
	 * Metoda pozwala ustawiæ tekst wyœwietlany w polu tekstowym
	 * @param text Tekst wyœwietlany w polu tekstowym
	 */
	public void setText(String text) { this.text = text; }

	/**
	 * Metoda zwraca informacjê czy pole jest aktywne i mo¿na na nim pisaæ czy nie
	 * @return true jeœli pole jest aktywne, false w przeciwnym wypadku
	 */
	public boolean isActive() { return active; }
	
	/**
	 * Metoda pozwala zmieniæ stan pola
	 * @param active Nowy stan pola
	 */
	public void setActive(boolean active) { this.active = active; }
}
