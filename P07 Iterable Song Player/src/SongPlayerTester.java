//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P07 Iterable Song Player
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

import java.util.Iterator;
import java.util.NoSuchElementException;;

/**
 * @author Matej Popovski
 *
 */

/**
 * This class represents unit test methods to check the correctness of the classes Song, LinkedNode,
 * SongPlayer ForwardSongIterator and BackwardSongIterator
 *
 */
public class SongPlayerTester {
  /**
   * 
   * This method tests the Song constructor, the acessor method, overridden
   * method toString() and equal() method defined in the Song class
   * 
   * @return true when this test confirms a correct functionality, and false otherwise
   */
  public static boolean testSong() {
    return false;
  }

  /**
   * This method tests the use of the LinkedNode constructor, the accessor method, and the
   * mutator method defined in the LinkedCart class.
   * 
   * @return true when this test confirms a correct functionality, and false otherwise
   */
  public static boolean testLinkedNode() {
    return false;
  }

  /**
   * This method checks for the correctness of the methods addFirst(), addLast() and add(int index) method in
   * SongPlayer class
   * 
   * @return true when this test confirms a correct functionality, and false otherwise
   */
  public static boolean testSongPlayerAdd() {
    return false;
  }

  /**
   * This method checks for the correctness of getFirst(), getLast() and get(int index) method in
   * SongPlayer class
   * 
   * @return true when this test confirms a correct functionality, and false otherwise
   */
  public static boolean testSongPlayerGet() {
    return false;
  }

  /**
   * This method checks for the correctness of removeFirst(), removeLast() and remove(int index)
   * method in SongPlayer class
   * 
   * @return true when this test confirms a correct functionality, and false otherwise
   */
  public static boolean testSongPlayerRemove() {
    return false;
  }

  /**
   * This method checks for the correctness of iterator(), switchPlayingDirection() and String
   * play() method in SongPlayer class
   * 
   * @return true when this test confirms a correct functionality, and false otherwise
   */
  public static boolean testSongPlayerIterator() {
    return false;
  }

  /**
   * This method checks for the correctness of contains(Object song), clear(), isEmpty()and size()
   * method in SongPlayer class
   * 
   * @return true when this test confirms a correct functionality, and false otherwise
   */
  public static boolean testSongPlayerCommonMethod() {
    return false;
  }

  /**
   * This method checks for the correctness of ForwardSongIterator class
   * 
   * @return true when this test confirms a correct functionality, and false otherwise
   */
  public static boolean testForwardSongIterator() {
    return false;
  }

  /**
   * This method checks for the correctness of BackwardSongIterator class
   * 
   * @return true when this test confirms a correct functionality, and false otherwise
   */
  public static boolean testBackwardSongIterator() {
    return false;
  }

  /**
   * This method calls all the test methods defined and implemented in your SongPlayerTester class.
   * 
   * @return true if all the test methods defined in this class pass, and false otherwise.
   */
  public static boolean runAllTests() {
    return false;
  }

  /**
   * Main method defined in this SongPlayerTester class, 
   * 
   * @param args input arguments if any.
   */
  public static void main(String[] args) {
    Song song = new Song("Prva Pesna", "Matej Popovski", "2:09");
    SongPlayer player = new SongPlayer();
    Song song2 = new Song("Vtora Pesna", "Matej Popovski", "1:07");
    Song song3 = new Song("Treta Pesna", "BMatej Popovski", "7:07");
    Song song4 = new Song("Cetvrta Pesna", "Matej Popovski", "4:07");
    player.addFirst(song);
    player.addFirst(song2);
    player.addLast(song3);
    player.add(1, song4);
    System.out.println(player.play());


  }
}
