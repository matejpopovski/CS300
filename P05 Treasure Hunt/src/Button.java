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

/**
 * this class implements a graphic clickable Button
 * 
 *
 */
public class Button implements Clickable {

  private static final int HEIGHT = 25;}
  private static final int WIDTH = 140;

  protected static PApplet processing;

  protected String label;
  private int x;
  private int y;

  /**
   * creates a new button at a given position within a pApplet object
   * 
   * @param label label to be assigned to this button
   * @param x     x-position where this button will be added to the display window
   * @param y     y-position where this button will be added to the display window
   */
  public Button(String label, int x, int y) {
    this.label = label;
    this.x = x;
    this.y = y;

  }


  /**
   * draws this button to display window
   */
  @Override
  public void draw() {
    processing.stroke(0);
    if (isMouseOver()) {
      
      processing.fill(processing.color(0, 100, 205));
    } else { 
      processing.fill(processing.color(153, 205, 255));
    }
  
    processing.rect(x, y, x + WIDTH, y + HEIGHT);
    if (isMouseOver()) {
      processing.fill(255); 
    } else {
      processing.fill(0); 
    }
    
    processing.text(label, x + WIDTH / 2.0f, y + HEIGHT / 2.0f);
  }

  /**
   * implements the default behavior of this button when the mouse is pressed. This method must be
   * overridden in the sub-classes to implement a specific behavior if needed.
   */
  @Override
  public void mousePressed() {
    if (isMouseOver())
      System.out.println("A button was pressed.");
  }



  /**
   * checks whether the mouse is over this button
   * 
   * @return true if the mouse is over this button, and false otherwise.
   */
  @Override
  public boolean isMouseOver() {
    if ((processing.mouseX >= this.x && processing.mouseX <= this.x + WIDTH)
        && (processing.mouseY >= this.y && processing.mouseY <= this.y + HEIGHT)) {
      return true;
    }
    return false;  
  }

  /**
   * Sets the PApplet display window where this button is displayed, and handled
   * 
   * @param processing the processing to set
   */
  public static void setProcessing(PApplet processing) {
    Button.processing = processing;
  }

  /**
   * implements the default behavior of this button when the mouse is released. This method must be
   * overridden in the sub-classes to implement a specific behavior if needed.
   */
  @Override
  public void mouseReleased() {
  }