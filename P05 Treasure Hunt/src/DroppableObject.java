//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P05 treasure hunt
// Course: CS 300 Spring 2022
//
// Author: Matej Popovski
// Email: popovski@wisc.edu
// Lecturer: Mouna Kacem
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: NONE
// Online Sources: NONE
//
///////////////////////////////////////////////////////////////////////////////

/**
 * @author Matej Popovski
 */


import processing.core.PApplet;
import processing.core.PImage;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DroppableObject extends DraggableObject {
  private InteractiveObject target;

  public DroppableObject(String name, int x, int y, String message, InteractiveObject target,
      InteractiveObject nextClue) {
    super(name, x, y, message);
    this.setNextClue(nextClue);
    this.target = target;

  }



  public boolean isOver(InteractiveObject other) {
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

  public void mouseReleased() {
    this.stopDragging();
    if (this.isOver(this.target) && this.target.isActive()) {
      this.deactivate();
      this.activateNextClue();
      this.target.deactivate();

      System.out.println(this.message());
    }
  }
}
