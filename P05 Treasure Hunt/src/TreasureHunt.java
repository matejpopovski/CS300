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

public class TreasureHunt extends PApplet {

  private PImage backgroundImage;
  private ArrayList<Clickable> gameObjects;

  public static void main(String[] args) {
    PApplet.main("TreasureHunt");
  }

  /**
   * Sets a size of the application display window
   */
  @Override
  public void settings() {
    size(800, 600);
  }

  /**
   * Defines an initial environment properties, loads background images and fonts, loads the clues,
   * and initializes the instance fields, as the program starts.
   */
  @Override
  public void setup() {

    this.imageMode(PApplet.CORNERS);
    this.focused = true;

    this.rectMode(PApplet.CORNERS);
    this.getSurface().setTitle("Treasure Hunt");


    this.textSize(13);
    this.textAlign(PApplet.CENTER, PApplet.CENTER);
    InteractiveObject.setProcessing(this);
    Button.setProcessing(this);
    initGame();
  }

  /**
   * innitializes game settings and list of objects
   */
  public void initGame() {

    gameObjects = new ArrayList<Clickable>();

    RestartGameButton RGB = new RestartGameButton(0, 0);
    this.gameObjects.add(RGB);

    ScreenshotButton SB = new ScreenshotButton(140, 0);
    this.gameObjects.add(SB);
    loadGameSettings("clues" + File.separator + "treasureHunt.clues");
  }

  /**
   * Operates each time the mouse is pressed
   */
  @Override
  public void mousePressed() {

    for (int i = 0; i < gameObjects.size(); i++) {
      gameObjects.get(i).mousePressed();
    }
  }

  /**
   * Adds a clickable object, that is giving its reference, to the list of game objects
   * 
   * @param clickableObject reference to an object instance of Clickable to add
   */
  public void add(Clickable clickableObject) {

    this.gameObjects.add(clickableObject);
  }

  /**
   * Updates the treasure hunt game display window. Draws the background image, draws all clickable
   * objects stored in the list of game objects, then removes all the interactive objects which are
   * no longer active.
   */
  @Override
  public void draw() {

    image(backgroundImage, 0, 0);


    for (int i = 0; i < gameObjects.size(); i++) {
      gameObjects.get(i).draw();
    }


    for (int i = 0; i < gameObjects.size(); i++) {

      if (gameObjects.get(i) instanceof InteractiveObject) {

        if (!((InteractiveObject) gameObjects.get(i)).isActive()) {
          gameObjects.remove(i);
          i--;
        }
      }
    }
  }



  /**
   * Operates each time the mouse is released
   */
  @Override
  public void mouseReleased() {
    // TODO traverse the list of gameObjects and call mouseReleased() method of each object stored
    // in the list
    for (int i = 0; i < gameObjects.size(); i++) {
      gameObjects.get(i).mouseReleased();
    }
  }

  protected InteractiveObject findObjectByName(String name) {
    for (int i = 0; i < gameObjects.size(); i++) {
      if (gameObjects.get(i) instanceof InteractiveObject) {
        if (((InteractiveObject) gameObjects.get(i)).hasName(name)) {
          return (InteractiveObject) gameObjects.get(i);
        }
      }
    }
    return null;
  }

  /**
   * This method loads a background image and prints out some introductory text, and then reads in a
   * set of interactive objects descriptions from a text file with the provided name. Those
   * represent different clues for our treasure hunt adventure game. The image is stored in
   * this.backgroundImage, and the activated interactive objects are added to the this.gameObjects
   * list.
   * 
   * @param filename - relative path of file to load, relative to current working directory
   */
  private void loadGameSettings(String filename) {
    // start reading file contents
    Scanner fin = null;
    int lineNumber = 1; // report first line in file as lineNumber 1
    try {
      fin = new Scanner(new File(filename));

      // read and store background image
      String backgroundImageFilename = fin.nextLine().trim();
      backgroundImageFilename = "images" + File.separator + backgroundImageFilename + ".png";
      backgroundImage = loadImage(backgroundImageFilename);
      lineNumber++;


      String introductoryText = fin.nextLine().trim();
      System.out.println(introductoryText);
      lineNumber++;


      while (fin.hasNextLine()) {
        String line = fin.nextLine().trim();
        if (line.length() < 1)
          continue;


        String[] parts = line.split(":");
        if (parts.length < 5) {
          continue;
        }
        InteractiveObject newObject = null;


        if (Character.toUpperCase(line.charAt(0)) == 'C')
          newObject = loadNewInteractiveObject(parts);
        else if (Character.toUpperCase(line.charAt(0)) == 'D')
          newObject = loadNewDroppableObject(parts);


        gameObjects.add(newObject);
        if (Character.isLowerCase(line.charAt(0)))
          newObject.deactivate();
        lineNumber++;
      }


    } catch (FileNotFoundException e) {
      System.out.println("WARNING: Unable to find or load file: " + filename);
    } catch (RuntimeException e) {
      System.out.println("WARNING: Problem loading file: " + filename + " line: " + lineNumber);
      e.printStackTrace();
    } finally {
      if (fin != null)
        fin.close();
    }
  }


  /**
   * This method creates and returns a new ClickableObject based on the properties specified as
   * strings within the provided parts array.
   * 
   * @param parts contains the following strings in this order: - C: indicates that a
   *              ClickableObject is being created - name: the name of the newly created interactive
   *              object - x: the starting x position (as an int) for this object - y: the starting
   *              y position (as an int) for this object - message: a string of text to display when
   *              this object is clicked - name of the object to activate (optional): activates this
   *              object when clicked
   * @return the newly created object
   */
  private InteractiveObject loadNewInteractiveObject(String[] parts) {
    // C: name: x: y: message: name of object to activate (optional)

    // parse parts
    String name = parts[1].trim();
    int x = Integer.parseInt(parts[2].trim());
    int y = Integer.parseInt(parts[3].trim());
    String message = parts[4].trim();
    InteractiveObject activate = null;
    if (parts.length > 5) {
      activate = findObjectByName(parts[5].trim());
      // create new clickable object
      if (activate != null) {
        return new InteractiveObject(name, x, y, message, activate);
      } else {
        System.out.println("WARNING: FAILED TO FIND INTERACTIVE OBJECT WITH NAME: " + name);
      }
    }
    return new InteractiveObject(name, x, y, message);
  }

  /**
   * This method creates and returns a new DroppableObject based on the properties specified as
   * strings within the provided parts array.
   * 
   * @param parts contains the following strings in this order: - D: indicates that a
   *              DroppableObject is being created - name: the name of the newly created droppable
   *              object - x: the starting x position (as an int) for this object - y: the starting
   *              y position (as an int) for this object - message: a string of text to display when
   *              this object is dropped on target - name of the object to activate (optional):
   *              activates this object when dropped on target
   * 
   * @return newly created droppable object
   */
  private DroppableObject loadNewDroppableObject(String[] parts) {



    String name = parts[1].trim();
    int x = Integer.parseInt(parts[2].trim());
    int y = Integer.parseInt(parts[3].trim());
    InteractiveObject dropTarget = findObjectByName(parts[4].trim());
    if (!(dropTarget instanceof InteractiveObject))
      dropTarget = null;
    String message = parts[5].trim();
    InteractiveObject activate = null;
    if (parts.length > 6)
      activate = findObjectByName(parts[6].trim());

    return new DroppableObject(name, x, y, message, dropTarget, activate);
  }
}
