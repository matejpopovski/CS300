//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Treasure Hunt
// Course: CS 300 Spring 2022
//
// Author: Yash Sancheti
// Email: ysancheti@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: Benjamin Wirch
// Partner Email: bwirch@wisc.edu
// Partner Lecturer's Name: Mouna Kacem
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// X Write-up states that pair programming is allowed for this assignment.
// X We have both read and understand the course Pair Programming Policy.
// X We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: NONE
// Online Sources: NONE
//
///////////////////////////////////////////////////////////////////////////////

/**
 * @author yashsancheti
 * @author benjaminwirch
 *
 */

import processing.core.PApplet;
import processing.core.PImage;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RestartGameButton extends Button {
  public RestartGameButton(int x, int y) {
    super("Restart Game", x, y);
  }

  @Override
  public void mousePressed() {
    if (this.isMouseOver()) {
      ((TreasureHunt) Button.processing).initGame();
    }
  }
}

