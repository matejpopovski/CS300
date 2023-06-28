//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P02 Water Fountain
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

import java.util.Random;
import java.io.File;
import processing.core.PImage;

public class Fountain {

  /**
   * This is the main method. Here we call only one function, which is Utility.runApplication();
   * Utility.runApplication() starts the application. This function automatically starts other
   * methods, such as setup() or draw()
   *
   */
  public static void main(String[] args) {

    Utility.runApplication();

  }

  private static Random randGen;

  private static PImage fountainImage;

  private static int positionX;

  private static int positionY;

  private static Droplet droplets[];

  private static int startColor;

  private static int endColor;

  /**
   * This is a method that is called by the class Utility. This method loads an image from a folder
   * in the package, and also defines the coordinates for the implemented image. It is supposed to
   * be called only once to build a new object of array to store the reference of droplets. The
   * method also defines starting and ending color, which are specific mixtures of colors that are
   * later used in other methods. And lastly this method includes two test methods, which are
   * specific for the exercise and are checking certain cases.
   *
   */
  public static void setup() {

    if (testUpdateDroplet()) {
      System.out.println("Problem detected: Your method testUpdateDroplet failed to return "
          + "the expected output");
    }

    if (testRemoveOldDroplets()) {
      System.out.println("Problem detected: Your method testRemoveOldDroplets failed to return "
          + "the expected output");
    }

    randGen = new Random();

    positionX = Utility.width() / 2;
    positionY = Utility.height() / 2;

    // load the image of the fountain
    fountainImage = Utility.loadImage("images" + File.separator + "fountain.png");

    startColor = Utility.color(23, 141, 235);

    endColor = Utility.color(23, 200, 255);

    droplets = new Droplet[800];

  }

  /**
   * This method colors the background, displays the image, and draws new droplets by calling
   * createNewDroplets(int numOfNewDroplets) and updates each non-null droplet by calling
   * updateDroplet()
   *
   */
  public static void draw() {

    Utility.background(Utility.color(253, 245, 230));

    Utility.image(fountainImage, positionX, positionY);

    createNewDroplets(10);

    for (int i = 0; i < droplets.length; i++) {

      if (droplets[i] != null) {

        updateDroplet(i);

      }
    }
  }

  /**
   * This method updates the position, acceleration, and velocity, as well as updates the colour,
   * transparency and age of an existing specified droplet passed by its index This method also
   * removes the old droplets
   *
   * @param index The index of the specified droplet in the array
   */
  private static void updateDroplet(int index) {

    Utility.fill(droplets[index].getColor(), droplets[index].getTransparency()); // ne sum siguren

    Utility.circle(droplets[index].getPositionX(), droplets[index].getPositionY(),
        droplets[index].getSize());

    droplets[index].setVelocityY(droplets[index].getVelocityY() + 0.3f);

    droplets[index].setPositionX(droplets[index].getPositionX() + droplets[index].getVelocityX());

    droplets[index].setPositionY(droplets[index].getPositionY() + droplets[index].getVelocityY());

    droplets[index].setAge(droplets[index].getAge() + 1);

    removeOldDroplets(80);

  }

  /**
   * This method creates a specfied number of new droplets at the possition of the null elements of
   * the array. Upon creating new droplet, the position on the x axis, position on the y axis, the
   * size, colour, transparency, age, and velocity of each droplet are defined randomly within a
   * hard-coded range of values.
   *
   * @param numOfNewDroplets The number of nwe droplets to be created
   */
  private static void createNewDroplets(int numOfNewDroplets) {

    int minX = Fountain.positionX - 3;

    int minY = Fountain.positionY - 3;

    int count = 0;

    for (int i = 0; count < numOfNewDroplets; i++) {

      if (i == droplets.length) {
        break;
      }


      if (droplets[i] == null) {

        float positionX = minX + randGen.nextFloat() * 6.0f;

        float positionY = minY + randGen.nextFloat() * 6.0f;

        float size = randGen.nextInt(8) + 4;

        int color = Utility.lerpColor(Fountain.startColor, Fountain.endColor, randGen.nextFloat());

        float velocityX = -1 + randGen.nextFloat() * (2);

        float velocityY = -10 + randGen.nextFloat() * (5);

        int age = randGen.nextInt(41);

        int transparency = randGen.nextInt(96) + 32;

        droplets[i] = new Droplet();

        droplets[i].setPositionX(positionX);

        droplets[i].setPositionY(positionY);

        droplets[i].setSize(size);

        droplets[i].setColor(color);

        droplets[i].setVelocityX(velocityX);

        droplets[i].setVelocityY(velocityY);

        droplets[i].setAge(age);

        droplets[i].setTransparency(transparency);

        count = count + 1;

      }
    }
  }

  /**
   * This method removes old droplets (droplets older than the specified maxAge, sets null on the
   * old droplet's positions in the array
   *
   * @param maxAge The maximum age the droplet is allowed to have before removing it
   */
  public static void removeOldDroplets(int maxAge) {

    for (int i = 0; i < droplets.length; i++) {

      if (droplets[i] == null) {
        continue;
      }

      else if (droplets[i].getAge() > maxAge) {
        droplets[i] = null;
      }

    }
  }

  /**
   * This method sets up the mouse's position
   */
  public static void mousePressed() {

    Fountain.positionX = Utility.mouseX();

    Fountain.positionY = Utility.mouseY();


  }

  /**
   * This method saves a screenshot (an image) of the curent state of the GUI in the package on a
   * pressed key of the specified char as input. This method is not case-sensitive For example
   * pressing either key of 's' and 'S' does the same fucntionality
   *
   * @param character The char which if pressed saves a screenshot
   */
  public static void keyPressed(char character) {

    if (character == 's' || character == 'S') {

      Utility.save("screenshot.png");
    }
  }

  /**
   * This tester initializes the droplets array to hold at least three droplets. Creates a single
   * droplet at position (3,3) with velocity (-1,-2). Then checks whether calling updateDroplet() on
   * this droplet’s index correctly results in changing its position to (2.0, 1.3).
   *
   * @return true when no defect is found, and false otherwise
   */
  private static boolean testUpdateDroplet() {

    droplets = new Droplet[3];

    droplets[0] = new Droplet();

    droplets[0].setPositionX(3.0f);

    droplets[0].setPositionY(3.0f);

    droplets[0].setVelocityX(-1.0f);

    droplets[0].setVelocityY(-2.0f);

    updateDroplet(0);

    if (Math.abs(droplets[0].getPositionX() - 1.0f) < 0.001) {
      return true;
    }

    if (Math.abs(droplets[0].getPositionY() - 2.0f) < 0.001) {
      return true;
    }

    return false;
  }

  /**
   * This tester initializes the droplets array to hold at least three droplets. Calls
   * removeOldDroplets(6) on an array with three droplets (two of which have ages over six and
   * another that does not). Then checks whether the old droplets were removed and the young droplet
   * was left alone.
   *
   * @return true when no defect is found, and false otherwise
   */
  private static boolean testRemoveOldDroplets() {

    droplets = new Droplet[3];

    droplets[0] = new Droplet();

    droplets[1] = new Droplet();

    droplets[2] = new Droplet();

    droplets[0].setAge(7);

    droplets[1].setAge(79);

    droplets[2].setAge(5);

    removeOldDroplets(6);

    if (droplets[0] != null || droplets[1] != null || droplets[2] == null) {
      return true;
    }

    return false;
  }
}
