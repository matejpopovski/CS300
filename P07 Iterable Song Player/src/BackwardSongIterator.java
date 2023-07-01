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

public class BackwardSongIterator implements Iterator<Song> {
  private LinkedNode<Song> next;

  /**
   * Creates a new iterator which iterates through songs in back/tail to front/head order
   * 
   * @param last - reference to the tail of a doubly linked list of songs
   */
  public BackwardSongIterator(LinkedNode<Song> last) {
    this.next = last;
  }

  /**
   * Checks whether there are more songs to return in the reverse order
   *
   * @return true if there are more songs to return in the reverse order
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
    next = next.getPrev();
    return temp.getData();
  }

}
