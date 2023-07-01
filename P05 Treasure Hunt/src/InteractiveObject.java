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

public class InteractiveObject implements Clickable {
  // reference to the PApplet where this object will be drawn
  protected static TreasureHunt processing;
  private final String NAME; // name of this interactive object
  protected PImage image; // reference to the image of this object
  private int x; // x-position of this interactive object in the screen
  private int y; // y-position of this interactive object in the screen
  // Note that the position (x,y) of the interactive object is the position
  // of the upper-left corner of its image (and NOT the center of the image).
  private boolean isActive; // tells whether or not this object is active
  private boolean wasClicked; // tells whether this object was already clicked
  private String message; // message to be displayed when this object is clicked
  private InteractiveObject nextClue = null; // Object to be activated when this object is
  // clicked the first time, if any

  public InteractiveObject(String name, int x, int y, String message) {
    this.NAME = name;
    this.x = x;
    this.y = y;
    this.message = message;
    this.image = processing.loadImage("images" + File.separator + name + ".png", "png");
    activate();
    this.wasClicked = false;
  }

  public InteractiveObject(String name, int x, int y, String message, InteractiveObject nextClue) {
    this(name, x, y, message);
    setNextClue​(nextClue);
    this.nextClue.deactivate();
  }

  public static void setProcessing​(TreasureHunt processing) {
    InteractiveObject.processing = processing;
  }

  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }

  public void move​(int dx, int dy) {
    this.x += dx;
    this.y += dy;
  }

  public boolean hasName​(String name) {
    return this.NAME.equals(name);
  }

  public boolean isActive() {
    return this.isActive;
  }

  public void activate() {
    this.isActive = true;
  }

  public void deactivate() {
    this.isActive = false;
  }

  public String message() {
    return this.message;
  }

  public void setNextClue​(InteractiveObject nextClue) {
    this.nextClue = nextClue;
  }

  protected void activateNextClue() {
    if (this.nextClue == null) {
      throw new NoSuchElementException("nextClue is null");
    }
    processing.add(this.nextClue);
    this.nextClue.activate();
  }

  @Override
  public void draw() {
    processing.image(this.image, this.x, this.y);
  }

  @Override
  public boolean isMouseOver() {
    if ((processing.mouseX >= this.getX() && processing.mouseX <= this.getX() + image.width)
        && (processing.mouseY >= this.getY() && processing.mouseY <= this.getY() + image.height)) {
      return true;
    }
    return false;
  }

  @Override
  public void mousePressed() {
    if (isMouseOver()) {
      System.out.println(this.message);
      if(nextClue != null && this.wasClicked == false) {
        this.wasClicked = true;
        activateNextClue();
      }
    }
  }

  @Override
  public void mouseReleased() {
  }
}
