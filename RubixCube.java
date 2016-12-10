package rubix;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/*
* Created by: Michael balcerzak
* Created on: 4-Dec-2016
* Created for: ICS3U
* Final thing – Unit#5
* it solves the 2by2by2 rubixCube you input
*/

public class RubixCube {
	
	public static void main(String[] args) throws IOException {
		// this function is where the user controls the cube
		InputStreamReader r = new InputStreamReader(System.in); 
		BufferedReader br = new BufferedReader(r);
		
		Cube rubixCube = new Cube();		
		rubixCube.PutAllTheFaces(rubixCube.faces, rubixCube.face1, rubixCube.face2, rubixCube.face3, rubixCube.face4, rubixCube.face5, rubixCube.face6);
		
		int counter3 = 0;
		for (int counter = 0; counter < 6; counter++) {
			for (int counter2 = 0; counter2 < 4; counter2++) {
				counter3++;
				rubixCube.faces[counter].squares[counter2].position = counter3;
			}
		}
		
		rubixCube.ShowTheCube();
		
		System.out.println("here is the cube. First you need to place the colors you want us to solve. Then we will solve the cube but we will go for a maxmim of 4 moves only. we will also give you diffrent posibilites of solving it");
		
		while((rubixCube.checkRed == true) || (rubixCube.checkBlue == true) || (rubixCube.checkGreen == true) || (rubixCube.checkYellow == true) || (rubixCube.checkWhite == true) || (rubixCube.checkOrange == true)) {
			String sideColor;
			for (int counter = 0; counter < 6; counter++) {
				for (int counter2 = 0; counter2 < 4; counter2++) {
					System.out.println("put the color on the cube in position " + rubixCube.faces[counter].squares[counter2].position);
					sideColor = br.readLine();
					rubixCube.faces[counter].squares[counter2].color = Square.Color.valueOf(sideColor);
				}
			}
		
			rubixCube.checkRed = rubixCube.CheckTheCube(Square.Color.valueOf("red"));
			rubixCube.checkBlue = rubixCube.CheckTheCube(Square.Color.valueOf("blue"));
			rubixCube.checkGreen = rubixCube.CheckTheCube(Square.Color.valueOf("green"));
			rubixCube.checkYellow = rubixCube.CheckTheCube(Square.Color.valueOf("yellow"));
			rubixCube.checkWhite = rubixCube.CheckTheCube(Square.Color.valueOf("white"));
			rubixCube.checkOrange = rubixCube.CheckTheCube(Square.Color.valueOf("orange"));
		
			if ((rubixCube.checkRed == true) || (rubixCube.checkBlue == true) || (rubixCube.checkGreen == true) || (rubixCube.checkYellow == true) || (rubixCube.checkWhite == true) || (rubixCube.checkOrange == true)) {
				System.out.println("there are more than 4 colors. Please put the colors again");
			} else {
				
				List<Face[]> listOfPositions = new ArrayList<Face[]>();
				int bigCounter = 0;
				List<Cube.Move> instructions = new ArrayList<Cube.Move>();
				
				rubixCube.recrusionPart(listOfPositions, bigCounter);
			}
		}


	}

}