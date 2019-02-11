package gui;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.Text;
import org.jsfml.system.Vector2f;
import org.jsfml.window.Mouse;

import font.LoadFont;

/**
 * Metoda s�u�y do obs�ugi pola tekstowego w menu logowania
 * @author Kamil Piec
 */
public class Input {
	/** Rozmiar pola tekstowego */
	private Vector2f size;
	/** Pozycja pola tekstowego na ekranie */
	private Vector2f position;
	/** Grafika pola tekstowego */
	private RectangleShape shape;
	/** Tekst wy�wietlany w polu tekstowym */
	private String text;
	/** Czy pole tekstowe jest aktywne i mo�na na nim pisa� */
	private boolean active = false;
	/** Pole przechowuje tekst wy�wietlany w polu tekstowym */
	private Text text1;

	/**
	 * Konstruktor inicjalizuj�cy atrybuty pola tekstowego
	 * @param text Tekst wy�wietlany w polu tekstowym
	 * @param size Rozmiar pola tekstowego
	 * @param position Pozycja pola tekstowego na ekranie
	 * @param fillColor Kolor wype�nienia pola tekstowego
	 * @param outlineColor Kolor obramowania pola tekstowego
	 * @param textColor Kolor tekstu w polu tekstowym
	 * @param thickness Grubo�� obramowania pola tekstowego
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
	 * Metoda sprawdza czy pole tekstowe zosta�o naci�ni�te
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
	 * Metoda pozwala zmieni� rozmiar pola tekstowego
	 * @param size Nowy rozmiar pola tekstowego
	 */
	public void setSize(Vector2f size) { this.size = size; }
	
	/**
	 * Metoda zwraca pozycj� pola tekstowego
	 * @return Pozycja pola tekstowego
	 */
	public Vector2f getPosition() { return this.position; }
	
	/**
	 * Metoda ustawia pozycj� pola tekstowego
	 * @param position Nowa pozycja pola tekstowego
	 */
	public void setPosition(Vector2f position) { this.position = position; }
	
	/**
	 * Metoda zwraca grafik� pola tekstowego
	 * @return Grafika pola tekstowego
	 */
	public RectangleShape getShape() { return this.shape; }
	
	/**
	 * Metoda pozwala ustawi� grafik� pola tekstowego
	 * @param shape Nowa grafika pola tekstowego
	 */
	public void setShape(RectangleShape shape) { this.shape = shape; }
	
	/** 
	 * Metoda zwraca tekst wy�wietlany w polu tekstowym
	 * @return Tekst wy�wietlany w polu tekstowym
	 */
	public String getText() {return this.text; }
	
	/**
	 * Metoda pozwala ustawi� tekst wy�wietlany w polu tekstowym
	 * @param text Tekst wy�wietlany w polu tekstowym
	 */
	public void setText(String text) { this.text = text; }

	/**
	 * Metoda zwraca informacj� czy pole jest aktywne i mo�na na nim pisa� czy nie
	 * @return true je�li pole jest aktywne, false w przeciwnym wypadku
	 */
	public boolean isActive() { return active; }
	
	/**
	 * Metoda pozwala zmieni� stan pola
	 * @param active Nowy stan pola
	 */
	public void setActive(boolean active) { this.active = active; }
}
