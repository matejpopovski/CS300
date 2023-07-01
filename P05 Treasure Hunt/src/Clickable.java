//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Clickable
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
public interface Clickable {
  public void draw(); // draws this Clickable object to the screen

  public void mousePressed(); // defines the behavior of this Clickable object
                              // each time the mouse is pressed

  public void mouseReleased(); // defines the behavior of this Clickable object
  // each time the mouse is released

  public boolean isMouseOver(); // returns true if the mouse is over this clickable object

}
