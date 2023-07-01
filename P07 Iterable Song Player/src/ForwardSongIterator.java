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

/**
 * @author Matej Popovski
 *
 */
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardSongIterator implements Iterator<Song> {
  private LinkedNode<Song> next;

  /**
   * Makes a new iterator which iterates through songs in front/head to back/tail order
   * 
   * @param first - reference to the head of a doubly linked list of songs
   */
  public ForwardSongIterator(LinkedNode<Song> first) {
    this.next = first;

  }

  /**
   * Checks if there are more songs to return
   *
   * @return true if there are more songs to return
   */
  @Override
  public boolean hasNext() {
    return next != null;
  }

  /**
   * Returns the next song in the iteration
   *
   * @throws java.util.NoSuchElementException - with a descriptive error message saying if there are
   *                                          no more songs to return in the reverse order
   */
  @Override
  public Song next() {

    if (!(hasNext())) {
      throw new NoSuchElementException("ERROR: No element found!");
    }

    LinkedNode<Song> temp = next;
    next = next.getNext();
    return temp.getData();
  }

}
