package rubix;

/*
* Created by: Michael balcerzak
* Created on: 4-Dec-2016
* Created for: ICS3U
* Final thing – Unit#5
* this class is the face of the cube that has 4 squares
*/

public class Face {

	public Square square1 = new Square();
	public Square square2 = new Square();
	public Square square3 = new Square();
	public Square square4 = new Square();
	public Square[] squares = new Square[4];
	
	public void PutAllTheSquares() {
		// this function put all 4 squares on the face
		squares[0] = square1;
		squares[1] = square2;
		squares[2] = square3;
		squares[3] = square4;
		
	}
	
}
