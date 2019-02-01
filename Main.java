//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Swim Simulation Project 5
// Files:           Main.java, SwimSimulation.java, Fish.java, Food.java, Hook.java, SimObject.java
// Course:          (CS300, Fall 2017)
//
// Author:          (Brennan Fife)
// Email:           (bfife@wisc.edu)
// Lecturer's Name: (Gary Dahl)
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates 
// strangers, etc do.  If you received no outside help from either type of 
// source, then please explicitly indicate NONE.
//
// Persons:         (NONE)
// Online Sources:  (NONE)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
public class Main {
	private static SwimSimulation sim;
	public static void main(String[] args) {
		Utility.startSimulation();		
	}

	public static void setup(Data data)	{
		sim = new SwimSimulation(data.processing);
	}

	public static void update(Data data) {
		sim.update();
	}

	public static void fillTank(char[][] tank, char water) {
		for (int i = 0; i < tank.length; i++) {
		    for (int j = 0; j < tank[i].length; j++) {
			   	   tank[i][j] = water;
		    } 
		}
	}

	public static void renderTank(char[][] tank) {
		for (int i = 0; i < tank.length; i++) {
			for (int j = 0; j < tank[i].length; j++) {
				System.out.print(tank[i][j]);
			}
			System.out.println();
		}
	}

	public static void onClick(Data data, int mouseX, int mouseY) {
		sim.handleClick(mouseX, mouseY);
	}
}