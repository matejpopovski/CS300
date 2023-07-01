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

public class DraggableObject extends InteractiveObject {

  private int oldMouseX;
  private int oldMouseY;
  private boolean isDragging;

  public DraggableObject(String name, int x, int y) {
    super(name, x, y, "Drag me");
    this.isDragging = false;
  }

  public DraggableObject(java.lang.String name, int x, int y, String message) {
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
      this.move(processing.mouseX - this.getX(), processing.mouseY - this.getY()); 
    }
    processing.image(this.image, this.getX(), this.getY());
  }

  @Override
  public void mouseReleased() {
    this.stopDragging();
  }

  @Override
  public void mousePressed() {
    if (this.isMouseOver()) {
      this.startDragging();
    }
  }

}
