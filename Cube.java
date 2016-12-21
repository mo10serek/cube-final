package rubix;
import java.util.List;
import java.util.ArrayList;

import rubix.Square.Color;

/*
* Created by: Michael balcerzak
* Created on: 4-Dec-2016
* Created for: ICS3U
* Final thing – Unit#5
* this class is the whole cube that it can be rotated on each side
*/

public class Cube extends RubixCube{

	protected Face face1 = new Face();
	protected Face face2 = new Face();
	protected Face face3 = new Face();
	protected Face face4 = new Face();
	protected Face face5 = new Face();
	protected Face face6 = new Face();
	protected Face[] faces = new Face[6];
	private boolean stopSolving = false;
	protected static boolean checkRed = true;
	protected static boolean checkBlue = true;
	protected static boolean checkGreen = true;
	protected static boolean checkYellow = true;
	protected static boolean checkWhite = true;
	protected static boolean checkOrange = true;
	private Move[] instructions = new Move[18];
	
	public enum Move {
		// this displayes all movements of the cube
		FrontClockwise("FrontClockwise"),
		FrontCounterClockwise("FrontCounterClockwise"),
		BackClockwise("BackClockwise"),
		BackCounterClockwise("BackCounterClockwise"),
		TopClockwise("TopClockwise"),
		TopCounterClockwise("TopCounterClockwise"),
		BottomClockwise("BottomClockwise"),
		BottomCounterClockwise("BottomCounterClockwise"),
		RightClockwise("RightClockwise"),
		RightCounterClockwise("RightCounterClockwise"),
		LeftClockwise("LeftClockwise"),
		LeftCounterClockwise("LeftCounterClockwise");
		
		private final String move;
		
		Move(String move){
		   this.move = move;
		}
			  
		public String setMove(){
			return this.move;
		}
	}
	
	public void PutAllTheFaces(Face[] newFaces, Face newFace1, Face newFace2 ,Face newFace3, Face newFace4, Face newFace5, Face newFace6) {
		// this function put all 5 faces on the cube
		newFaces[0] = newFace1;
		newFaces[0].PutAllTheSquares();
		newFaces[1] = newFace2;
		newFaces[1].PutAllTheSquares();
		newFaces[2] = newFace3;
		newFaces[2].PutAllTheSquares();
		newFaces[3] = newFace4;
		newFaces[3].PutAllTheSquares();
		newFaces[4] = newFace5;
		newFaces[4].PutAllTheSquares();
		newFaces[5] = newFace6;
		newFaces[5].PutAllTheSquares();
	}
	
	public void ShowTheCube() {
		// this function prints the whole cube
		System.out.println("      ||" + faces[0].squares[0].position + "||" + faces[0].squares[1].position + "||");
		System.out.println("      ||" + faces[0].squares[2].position + "||" + faces[0].squares[3].position + "||");
		System.out.println("||" + faces[1].squares[0].position + "||" + faces[1].squares[1].position + "||" + faces[2].squares[0].position + "|" + faces[2].squares[1].position + "||" + faces[3].squares[0].position + "||" + faces[3].squares[1].position + "||" + faces[4].squares[0].position + "||" + faces[4].squares[1].position + "||");
		System.out.println("||" + faces[1].squares[2].position + "||" + faces[1].squares[3].position + "|" + faces[2].squares[2].position + "||" + faces[2].squares[3].position + "||" + faces[3].squares[2].position + "||" + faces[3].squares[3].position + "||"  + faces[4].squares[2].position + "||" + faces[4].squares[3].position + "||");
		System.out.println("     ||" + faces[5].squares[0].position + "||" + faces[5].squares[1].position + "||");
		System.out.println("     ||" + faces[5].squares[2].position + "||" + faces[5].squares[3].position + "||");
	}
	
	protected boolean CheckTheCube(Square.Color enumColor) {
		// this function checks if the cube has 4 diffrent colors
		int colorCount = 0;
		boolean moreThanFour = false;
		
		for (int counter = 0; counter < 6; counter++) {
			for (int counter2 = 0; counter2 < 4; counter2++) {
				if (faces[counter].squares[counter2].color == enumColor) {
					colorCount = colorCount + 1;
				}
			}
		}	
		
		if (colorCount >= 5) {
			moreThanFour = true;
		}
		return moreThanFour;
	}
	
	public boolean CheckFinish(List<Face[]> listOfPositions) {
		// this function makes the final position of the cube
		int sameSquare = 0;
		boolean finishCube = false;
				
		for(int counter = 0; counter < listOfPositions.size(); counter++) {
			sameSquare = 0;
			for (int counter1 = 0; counter1 < 6; counter1++) {
				if ((listOfPositions.get(counter)[counter1].squares[0].color == listOfPositions.get(counter)[counter1].squares[1].color)) {
					sameSquare++;
				}
				if ((listOfPositions.get(counter)[counter1].squares[1].color == listOfPositions.get(counter)[counter1].squares[2].color)) {
					sameSquare++;
				}
				if ((listOfPositions.get(counter)[counter1].squares[2].color == listOfPositions.get(counter)[counter1].squares[3].color)) {
					sameSquare++;
				}
				if ((listOfPositions.get(counter)[counter1].squares[3].color == listOfPositions.get(counter)[counter1].squares[0].color)) {
					sameSquare++;
				}	
			}
		}
				
		if (sameSquare > 23) {
			finishCube = true;
		}
				
			return finishCube;
	}
	
	public List<Face[]> RotateCounterClockWise(List<Face[]> listOfPositions, Face[] OwnFace, int up1, int up2, int right1, int right2, int down1, int down2, int left1, int left2, int frontUpLeft, int frontUpRight, int frontDownRight, int frontDownLeft, int face1, int face2, int face3, int face4, int face5) {
		//this function rotates the cube counter clockwise
		Square.Color holdColor1;
		Square.Color holdColor2;
		Square.Color holdColor3;
		
		holdColor1 = OwnFace[face1].squares[up1].color;
		holdColor2 = OwnFace[face1].squares[up2].color;
		holdColor3 = OwnFace[face5].squares[frontUpLeft].color;
		OwnFace[face1].squares[up1].color = OwnFace[face2].squares[right1].color;
		OwnFace[face1].squares[up2].color = OwnFace[face2].squares[right2].color;
		OwnFace[face5].squares[frontUpLeft].color = OwnFace[face5].squares[frontUpRight].color;
		OwnFace[face2].squares[right1].color = OwnFace[face3].squares[down1].color;  
		OwnFace[face2].squares[right2].color = OwnFace[face3].squares[down2].color;  
		OwnFace[face5].squares[frontUpRight].color = OwnFace[face5].squares[frontDownRight].color;
		OwnFace[face3].squares[down1].color = OwnFace[face4].squares[left1].color;  
		OwnFace[face3].squares[down2].color = OwnFace[face4].squares[left2].color; 
		OwnFace[face5].squares[frontDownRight].color = OwnFace[face5].squares[frontDownLeft].color;
		OwnFace[face4].squares[left1].color = holdColor1;  
		OwnFace[face4].squares[left2].color = holdColor2; 
		OwnFace[face5].squares[frontDownLeft].color = holdColor3;
		
		//this prints the cube position
		if (stopSolving == false) {
			System.out.println("        ||" + faces[0].squares[0].color + "||" + faces[0].squares[1].color + "||");
			System.out.println("        ||" + faces[0].squares[2].color + "||" + faces[0].squares[3].color + "||");
			System.out.println("||" + faces[1].squares[0].color + "||" + faces[1].squares[1].color + "||" + faces[2].squares[0].color + "|" + faces[2].squares[1].color + "||" + faces[3].squares[0].color + "||" + faces[3].squares[1].color + "||" + faces[4].squares[0].color + "||" + faces[4].squares[1].color + "||");
			System.out.println("||" + faces[1].squares[2].color + "||" + faces[1].squares[3].color + "|" + faces[2].squares[2].color + "||" + faces[2].squares[3].color + "||" + faces[3].squares[2].color + "||" + faces[3].squares[3].color + "||"  + faces[4].squares[2].color + "||" + faces[4].squares[3].color + "||");
			System.out.println("        ||" + faces[5].squares[0].color + "||" + faces[5].squares[1].color + "||");
			System.out.println("        ||" + faces[5].squares[2].color + "||" + faces[5].squares[3].color + "||");
		}
		
		listOfPositions.add(OwnFace);
		
		return listOfPositions;
	}
	
	public List<Face[]> RotateClockWise(List<Face[]> listOfPositions, Face[] OwnFace, int up1, int up2, int left1, int left2, int down1, int down2, int right1, int right2, int frontUpLeft, int frontDownLeft, int frontDownRight, int frontUpRight, int face1, int face2, int face3, int face4, int face5) {
		//this function rotates the cube clockwise
		Square.Color holdColor1;
		Square.Color holdColor2;
		Square.Color holdColor3;
		
		holdColor1 = OwnFace[face1].squares[up1].color;//0
		holdColor2 = OwnFace[face1].squares[up2].color;
		holdColor3 = OwnFace[face5].squares[frontUpLeft].color;
		OwnFace[face1].squares[up1].color = OwnFace[face2].squares[left1].color;//3
		OwnFace[face1].squares[up2].color = OwnFace[face2].squares[left2].color;
		OwnFace[face5].squares[frontUpLeft].color = OwnFace[face5].squares[frontDownLeft].color;
		OwnFace[face2].squares[left1].color = OwnFace[face3].squares[down1].color;//5
		OwnFace[face2].squares[left2].color = OwnFace[face3].squares[down2].color;  
		OwnFace[face5].squares[frontDownLeft].color = OwnFace[face5].squares[frontDownRight].color;
		OwnFace[face3].squares[down1].color = OwnFace[face4].squares[right1].color;//1 
		OwnFace[face3].squares[down2].color = OwnFace[face4].squares[right2].color; 
		OwnFace[face5].squares[frontDownRight].color = OwnFace[face5].squares[frontUpRight].color;
		OwnFace[face4].squares[right1].color = holdColor1;  
		OwnFace[face4].squares[right2].color = holdColor2; 
		OwnFace[face5].squares[frontUpRight].color = holdColor3;
		
		//this prints the cube position
		if (stopSolving == false) {
			System.out.println("        ||" + faces[0].squares[0].color + "||" + faces[0].squares[1].color + "||");
			System.out.println("        ||" + faces[0].squares[2].color + "||" + faces[0].squares[3].color + "||");
			System.out.println("||" + faces[1].squares[0].color + "||" + faces[1].squares[1].color + "||" + faces[2].squares[0].color + "|" + faces[2].squares[1].color + "||" + faces[3].squares[0].color + "||" + faces[3].squares[1].color + "||" + faces[4].squares[0].color + "||" + faces[4].squares[1].color + "||");
			System.out.println("||" + faces[1].squares[2].color + "||" + faces[1].squares[3].color + "|" + faces[2].squares[2].color + "||" + faces[2].squares[3].color + "||" + faces[3].squares[2].color + "||" + faces[3].squares[3].color + "||"  + faces[4].squares[2].color + "||" + faces[4].squares[3].color + "||");
			System.out.println("        ||" + faces[5].squares[0].color + "||" + faces[5].squares[1].color + "||");
			System.out.println("        ||" + faces[5].squares[2].color + "||" + faces[5].squares[3].color + "||");
		}
		
		listOfPositions.add(OwnFace);
		
		return listOfPositions;
	}
	
	public void RotateFaces(int rotations, List<Face[]> listOfPositions, int bigCounter) {
		//this function is solving the cube
	
		// this if statement sees if the cube already found a solution
		if (stopSolving == false) {
		// this part is rotating the faces
		Move step;
		
		if (rotations == 1) {
			listOfPositions = RotateClockWise(listOfPositions, faces, /*face1*/2,  3, /*face2*/3, 1, /*face3*/1, 0, /*face4*/0, 2, /*face5*/ 0, 2, 3, 1, 0, 1, 5, 3, 2);
			
			step = Move.valueOf("FrontClockwise");
			instructions[bigCounter] = step;
		}  else if (rotations == 2) {
			listOfPositions = RotateCounterClockWise(listOfPositions, faces, /*face1*/2, 3, /*face2*/0, 2, /*face3*/1, 0, /*face4*/3, 1,  /*face5*/ 0, 1, 3, 2, 0, 3, 5, 1, 2); 

			step = Move.valueOf("FrontCounterClockwise");
			instructions[bigCounter] = step;
		} else if (rotations == 3) {
			listOfPositions = RotateClockWise(listOfPositions, faces, /*face1*/0, 1, /*face2*/2, 0, /*face3*/3, 2, /*face4*/1, 3, /*face5*/ 0, 2, 3, 1, 0, 1, 5, 3, 4); 			
			
			step = Move.valueOf("BackClockwise");
			instructions[bigCounter] = step;
		} else if (rotations == 4) {
			listOfPositions = RotateCounterClockWise(listOfPositions, faces, /*face1*/0, 1, /*face2*/1, 3, /*face3*/3, 2, /*face4*/2, 0, /*face5*/ 0, 1, 3, 2, 0, 3, 5, 1, 4); 	

			step = Move.valueOf("BackCounterClockwise");
			instructions[bigCounter] = step;
		}else if (rotations == 5) {
			listOfPositions = RotateClockWise(listOfPositions, faces, /*face1*/0, 1, /*face2*/0, 1, /*face3*/0, 1, /*face4*/0, 1, /*face5*/ 0, 2, 3, 1, 4, 3, 2, 1, 0); 
			
			step = Move.valueOf("TopCounterClockwise");
			instructions[bigCounter] = step;
		} else if (rotations == 6) {
			listOfPositions = RotateCounterClockWise(listOfPositions, faces, /*face1*/0, 1, /*face2*/0, 1, /*face3*/0, 1, /*face4*/0, 1, /*face5*/ 0, 1, 3, 2, 4, 1, 2, 3, 0); 
			
			step = Move.valueOf("TopClockwise");
			instructions[bigCounter] = step;
		} else if (rotations == 7) {
			listOfPositions = RotateClockWise(listOfPositions, faces, /*face1*/2, 3, /*face2*/2, 3, /*face3*/2, 3, /*face4*/2, 3, /*face5*/ 0, 2, 3, 1, 2, 1, 4, 3, 5);
			
			step = Move.valueOf("BottomCounterClockwise");
			instructions[bigCounter] = step;
		} else if (rotations == 8) {			
			listOfPositions = RotateCounterClockWise(listOfPositions, faces, /*face1*/2, 3, /*face2*/2, 3, /*face3*/2, 3, /*face4*/2, 3, /*face5*/ 0, 1, 3, 2, 2, 3, 4, 1, 5);
			
			step = Move.valueOf("BottomClockwise");
			instructions[bigCounter] = step;
		} else if (rotations == 9) {
			listOfPositions = RotateClockWise(listOfPositions, faces, /*face1*/3, 1, /*face2*/3, 1, /*face3*/3, 1, /*face4*/0, 2,  /*face5*/ 0, 2, 3, 1, 0, 2, 5, 4, 3);
			
			step = Move.valueOf("RightClockwise");
			instructions[bigCounter] = step;
		} else if (rotations == 10) {
			listOfPositions = RotateCounterClockWise(listOfPositions, faces, /*face1*/3, 1, /*face2*/0, 2, /*face3*/3, 1, /*face4*/3, 1, /*face5*/ 0, 1, 3, 2, 0, 4, 5, 2, 3);
			
			step = Move.valueOf("RightCounterClockwise");
			instructions[bigCounter] = step;
		} else if (rotations == 11) {
			listOfPositions = RotateClockWise(listOfPositions, faces, /*face1*/0, 2, /*face2*/0, 2, /*face3*/0, 2, /*face4*/3, 1, /*face5*/ 0, 2, 3, 1, 0, 2, 5, 4, 1);
			
			step = Move.valueOf("LeftCounterClockwise");
			instructions[bigCounter] = step;
		} else if (rotations == 12) {
			listOfPositions = RotateCounterClockWise(listOfPositions, faces, /*face1*/0, 2, /*face2*/3, 1, /*face3*/0, 2, /*face4*/0, 2, /*face5*/ 0, 1, 3, 2, 0, 4, 5, 2, 1);
			
			step = Move.valueOf("LeftClockwise");
			instructions[bigCounter] = step;
		}
		
		bigCounter = bigCounter + 1;
		
		// this part sees if the cube is finish
		boolean finishCube = CheckFinish(listOfPositions); 
		
		
		//this part tells the instruction of how the cube is solved
		if (finishCube == true) {
			for (int counter2 = 0; counter2 < instructions.length; counter2++) {
				System.out.println(instructions[counter2]);
				stopSolving = true;
			} 
		}
		
		//this part do recursion but only for 16 times so it will not cause overflow
			if (bigCounter > 17) {
				System.out.println("I reach more than 18 moves");
				System.out.println("");
			} else {
				RotateFaces(1, listOfPositions, bigCounter);
				listOfPositions = RotateCounterClockWise(listOfPositions, faces, /*face1*/2, 3, /*face2*/0, 2, /*face3*/1, 0, /*face4*/3, 1,  /*face5*/ 0, 1, 3, 2, 0, 3, 5, 1, 2); 
				if (stopSolving == false) {
					instructions[bigCounter] = null;
				}
				RotateFaces(2, listOfPositions, bigCounter);
				listOfPositions = RotateClockWise(listOfPositions, faces, /*face1*/2,  3, /*face2*/3, 1, /*face3*/1, 0, /*face4*/0, 2, /*face5*/ 0, 2, 3, 1, 0, 1, 5, 3, 2);
				if (stopSolving == false) {
					instructions[bigCounter] = null;
				}
				RotateFaces(3, listOfPositions, bigCounter);
				listOfPositions = RotateCounterClockWise(listOfPositions, faces, /*face1*/0, 1, /*face2*/1, 3, /*face3*/3, 2, /*face4*/2, 0, /*face5*/ 0, 1, 3, 2, 0, 3, 5, 1, 4); 	
				if (stopSolving == false) {
					instructions[bigCounter] = null;
				}
				RotateFaces(4, listOfPositions, bigCounter);
				listOfPositions = RotateClockWise(listOfPositions, faces, /*face1*/0, 1, /*face2*/2, 0, /*face3*/3, 2, /*face4*/1, 3, /*face5*/ 0, 2, 3, 1, 0, 1, 5, 3, 4); 			
				if (stopSolving == false) {
					instructions[bigCounter] = null;
				}
				RotateFaces(5, listOfPositions, bigCounter);
				listOfPositions = RotateCounterClockWise(listOfPositions, faces, /*face1*/0, 1, /*face2*/0, 1, /*face3*/0, 1, /*face4*/0, 1, /*face5*/ 0, 1, 3, 2, 4, 1, 2, 3, 0); 
				if (stopSolving == false) {
					instructions[bigCounter] = null;
				}
				RotateFaces(6, listOfPositions, bigCounter);
				listOfPositions = RotateClockWise(listOfPositions, faces, /*face1*/0, 1, /*face2*/0, 1, /*face3*/0, 1, /*face4*/0, 1, /*face5*/ 0, 2, 3, 1, 4, 3, 2, 1, 0); 
				if (stopSolving == false) {
					instructions[bigCounter] = null;
				}
				RotateFaces(7, listOfPositions, bigCounter);
				listOfPositions = RotateCounterClockWise(listOfPositions, faces, /*face1*/2, 3, /*face2*/2, 3, /*face3*/2, 3, /*face4*/2, 3, /*face5*/ 0, 1, 3, 2, 2, 3, 4, 1, 5);
				if (stopSolving == false) {
					instructions[bigCounter] = null;
				}
				RotateFaces(8, listOfPositions, bigCounter);
				listOfPositions = RotateClockWise(listOfPositions, faces, /*face1*/2, 3, /*face2*/2, 3, /*face3*/2, 3, /*face4*/2, 3, /*face5*/ 0, 2, 3, 1, 2, 1, 4, 3, 5);
				if (stopSolving == false) {
					instructions[bigCounter] = null;
				}
				RotateFaces(9, listOfPositions, bigCounter);
				listOfPositions = RotateCounterClockWise(listOfPositions, faces, /*face1*/3, 1, /*face2*/0, 2, /*face3*/3, 1, /*face4*/3, 1, /*face5*/ 0, 1, 3, 2, 0, 4, 5, 2, 3);	
				if (stopSolving == false) {
					instructions[bigCounter] = null;
				}
				RotateFaces(10, listOfPositions, bigCounter);
				listOfPositions = RotateClockWise(listOfPositions, faces, /*face1*/3, 1, /*face2*/3, 1, /*face3*/3, 1, /*face4*/0, 2,  /*face5*/ 0, 2, 3, 1, 0, 2, 5, 4, 3);
				if (stopSolving == false) {
					instructions[bigCounter] = null;
				}
				RotateFaces(11, listOfPositions,  bigCounter);
				listOfPositions = RotateCounterClockWise(listOfPositions, faces, /*face1*/0, 2, /*face2*/3, 1, /*face3*/0, 2, /*face4*/0, 2, /*face5*/ 0, 1, 3, 2, 0, 4, 5, 2, 1);
				if (stopSolving == false) {
					instructions[bigCounter] = null;
				}
				RotateFaces(12, listOfPositions,  bigCounter);
				listOfPositions = RotateClockWise(listOfPositions, faces, /*face1*/0, 2, /*face2*/0, 2, /*face3*/0, 2, /*face4*/3, 1, /*face5*/ 0, 2, 3, 1, 0, 2, 5, 4, 1);
				if (stopSolving == false) {
					instructions[bigCounter] = null;
				}
			}
		
		}
		
	}
	
}
