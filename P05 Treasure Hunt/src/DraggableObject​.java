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

public class DraggableObject​ extends InteractiveObject {
  private boolean isDragging;
  private int oldMouseX;
  private int oldMouseY;

  public DraggableObject​(String name, int x, int y) {
    super(name, x, y, "Drag me");
    this.isDragging = false;
  }

  public DraggableObject​(java.lang.String name, int x, int y, String message) {
    super(name, x, y, message);
    this.isDragging = false;
  }

  public boolean isDragging() {
    return this.isDragging;
  }

  public void startDragging() {
    this.isDragging = true;
    this.oldMouseX = processing.mouseX;
    this.oldMouseY = processing.mouseY;
  }

  public void stopDragging() {
    this.isDragging = false;
  }

  @Override
  public void draw() {
    if (this.isDragging()) {
      this.move​(processing.mouseX - this.getX(), processing.mouseY - this.getY());
    }
    processing.image(this.image, this.getX(), this.getY());
  }

  @Override
  public void mousePressed() {
    if (this.isMouseOver()) {
      this.startDragging();
    }
  }

  @Override
  public void mouseReleased() {
    this.stopDragging();
  }
}
