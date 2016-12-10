package rubix;

/*
* Created by: Michael balcerzak
* Created on: 4-Dec-2016
* Created for: ICS3U
* Final thing – Unit#5
* this class is the square that has its own color and position
*/

public class Square {
	
	public enum Color { 
		// this displays all the colors on each cube
		  red("red"),
		  green("green"),
		  orange("orange"),
		  blue("blue"),
		  white("white"),
		  yellow("yellow");

		  private final String color;
		  
		  Color(String color){
			  this.color = color;
		  }
		  
		  public String setPlanet(){
			  return color;
		  }
		  
	}
	
	public Color color;
	public int position;
}
