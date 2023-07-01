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

public class InteractiveObject implements Clickable {

  protected static TreasureHunt processing;
  private final String NAME;
  protected PImage image;
  private int x;
  private int y;

  private boolean isActive;
  private boolean wasClicked;
  private String message;
  private InteractiveObject nextClue = null;


  public InteractiveObject(String name, int x, int y, String message) {

    this.message = message;
    this.NAME = name;
    this.x = x;
    this.y = y;

    this.image = processing.loadImage("images" + File.separator + name + ".png", "png");
    activate();
    this.wasClicked = false;
  }

  public InteractiveObject(String name, int x, int y, String message, InteractiveObject nextClue) {
    this(name, x, y, message);
    setNextClue(nextClue);
    this.nextClue.deactivate();
  }

  public static void setProcessing(TreasureHunt processing) {
    InteractiveObject.processing = processing;
  }

  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }

  public boolean isActive() {
    return this.isActive;
  }

  public void activate() {
    this.isActive = true;
  }

  public void move(int dx, int dy) {
    this.x += dx;
    this.y += dy;
  }

  public boolean hasName(String name) {
    return this.NAME.equals(name);
  }

  public void deactivate() {
    this.isActive = false;
  }

  public String message() {
    return this.message;
  }

  public void setNextClue(InteractiveObject nextClue) {
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
      if (nextClue != null && this.wasClicked == false) {
        this.wasClicked = true;
        activateNextClue();
      }
    }
  }

  @Override
  public void draw() {
    processing.image(this.image, this.x, this.y);
  }

  @Override
  public void mouseReleased() {
  }
}
