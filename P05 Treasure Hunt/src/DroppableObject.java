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

public class DroppableObject extends DraggableObject​ {
  private InteractiveObject target; // target of this droppable object

  public DroppableObject(String name, int x, int y, String message, InteractiveObject target,
      InteractiveObject nextClue) {
    super(name, x, y, message);
    this.target = target;
    this.setNextClue​(nextClue);
  }

  public void mouseReleased() {
    this.stopDragging();
    if (this.isOver​(this.target) && this.target.isActive()) {
      this.deactivate();
      this.target.deactivate();
      this.activateNextClue();
      System.out.println(this.message());
    }
  }

  public boolean isOver​(InteractiveObject other) {
    if (((this.getX() >= other.getX() && this.getX() <= other.getX() + other.image.width)
        && (this.getY() >= other.getY() && this.getY() <= other.getY() + other.image.height))
        || ((this.getX() + this.image.width >= other.getX()
            && this.getX() + this.image.width <= other.getX() + other.image.width)
            && (this.getY() + this.image.height >= other.getY()
                && this.getY() + this.image.height <= other.getY() + other.image.height))) {
      return true;
    }
    return false;
  }
}
