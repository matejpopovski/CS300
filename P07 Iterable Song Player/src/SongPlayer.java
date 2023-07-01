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
import java.util.NoSuchElementException;
import java.util.Iterator;

/**
 * @author Matej Popovski
 *
 */
public class SongPlayer implements Iterable<Song> {
  private int size; // size of the list
  private LinkedNode<Song> head; // head of this doubly linked list
  private LinkedNode<Song> tail; // tail of this doubly linked list
  private boolean playingBackward;

  /**
   * Makes new instance of song player which contains zero song and set by default to play songs in
   * the forward directon. Implementing this constructor is optional since it will be added by
   * default by the compiler.
   */
  public SongPlayer() {
    head = null;
    tail = null;
    size = 0;
  }

  /**
   * Adds Song as last Song
   * 
   * @param oneSong - the song that is going to be added to the tail of this doubly linked list of
   *                songs
   */
  public void addLast(Song oneSong) {
    LinkedNode<Song> songNode = new LinkedNode<Song>(tail, oneSong, null);
    if (this.isEmpty()) {
      this.head = songNode;
      this.tail = songNode;
      size++;
      return;
    }
    if (tail != null) {
      this.tail.setNext(songNode);
    }
    this.tail = songNode;
    size++;
  }

  /**
   * add Song as first Song
   * 
   * @param oneSong - the song that is going to be added to the head of this doubly linked list of
   *                songs
   *
   * @throws NullPointerException - with a descriptive error message if the passed oneSong is null
   */
  public void addFirst(Song oneSong) {
    try {
      LinkedNode<Song> songNode = new LinkedNode<>(null, oneSong, head);
      if (this.isEmpty()) {
        this.head = songNode;
        this.tail = songNode;
        size++;
        return;
      }
      if (head != null) {
        this.head.setPrev(songNode);
      }
      this.head = songNode;
      size++;
    } catch (IllegalArgumentException e) {
      throw new NullPointerException(e.getMessage());
    }

  }

  /**
   * adds Song at a given position/order within this collection of songs
   * 
   * @param index   - the given index where the new song will be added
   * @param oneSong - the song that is going to be added
   *
   * @throws NullPointerException      with a descriptive error message if the passed oneSong is
   *                                   null
   * @throws IndexOutOfBoundsException with a descriptive error message if index is out of the 0 ..
   *                                   size() range
   */
  public void add(int index, Song oneSong) {
    if (index < 0 || index > size()) {
      throw new IndexOutOfBoundsException("Index is invalid");
    } else if (size == 0) {
      head = tail = new LinkedNode<Song>(null, oneSong, null);
      size++;
      return;
    } else if (index == 0) {
      addFirst(oneSong);
      return;
    }
    if (index == size) {
      addLast(oneSong);
      return;
    }
    LinkedNode<Song> prev = head;
    for (int i = 0; i < index - 1; i++) {
      prev = prev.getNext();
    }
    LinkedNode<Song> next = prev.getNext();
    LinkedNode<Song> newNode = new LinkedNode<Song>(prev, oneSong, next);
    prev.setNext(newNode);
    next.setPrev(newNode);
    size++;

  }

  /**
   * Returns the first Song in this list.
   *
   * @return the Song at the head of this list
   *
   * @throws java.util.NoSuchElementException with a descriptive error message if this list is empty
   */
  public Song getFirst() {
    if (size == 0) {
      throw new NoSuchElementException("ERROR: List is empty");
    }
    return head.getData();
  }

  /**
   * Returns the last Song in this list.
   *
   * @return the Song at the tail of this list
   *
   * @throws java.util.NoSuchElementException - with a descriptive error message if this list is
   *                                          empty
   */
  public Song getLast() {
    if (size == 0) {
      throw new NoSuchElementException("ERROR: List is empty");
    }
    return tail.getData();
  }

  /**
   * Returns the song at the specified position in this list.
   * 
   * @param index - index of the song to return
   *
   * @return the song at the specified position in this list
   *
   * @throws IndexOutOfBoundsException - with a descriptive error message if index is out of the 0
   *                                   .. size()-1 range
   */
  public Song get(int index) {
    if (index < 0 || index > size()) {
      throw new IndexOutOfBoundsException("ERROR: Index is invalid");
    }
    LinkedNode<Song> prev = head;
    for (int i = 0; i <= index; i++) {
      prev = prev.getNext();
    }
    return prev.getData();
  }

  /**
   * Removes and returns the first song from this list.
   *
   * @return the first song from this list
   *
   * @throws java.util.NoSuchElementException - with a descriptive error message if this list is
   *                                          empty
   */
  public Song removeFirst() {
    if (size == 0) {
      throw new NoSuchElementException("ERROR: List is empty");
    }
    Song temp;
    try {
      temp = head.getData();
    } catch (NullPointerException e) {
      return null;
    }
    if (size == 1) {
      this.head = this.tail = null;
      size = 0;
      return temp;
    }
    head = head.getNext();
    try {
      head.setPrev(null);
    } catch (NullPointerException e) {

    }
    size--;
    return temp;
  }

  /**
   * Removes and returns the last song from this list.
   *
   * @return the last song from this list
   *
   * @throws java.util.NoSuchElementException - with a descriptive error message if this list is
   *                                          empty
   */
  public Song removeLast() {
    if (size == 0) {
      throw new NoSuchElementException("ERROR: List is empty");
    }
    Song temp;
    try {
      temp = tail.getData();
    } catch (NullPointerException e) {
      return null;
    }
    if (size == 1) {
      this.tail = null;
      this.head = null;
      size = 0;
      return temp;
    }
    tail = tail.getPrev();
    try {
      tail.setNext(null);
    } catch (NullPointerException e) {
    }
    size--;
    return temp;
  }

  /**
   * Removes the song at the specified position in this list and returns the song that was removed
   * from the list. The order of precedence of the other songs in the list should not be modified.
   * 
   * @param index - the index of the song to be removed
   *
   * @return the song previously at the specified position
   *
   * @throws IndexOutOfBoundsException - with a descriptive error message if index is out of the 0
   *                                   .. size()-1 range
   */
  public Song remove(int index) {
    if (index < 0 || index > size() - 1) {
      throw new IndexOutOfBoundsException("ERROR: Index is invalid");
    }

    LinkedNode<Song> curr = head;
    for (int i = 0; i < index; i++) {
      curr = curr.getNext();
    }
    try {
      curr.getPrev().setNext(curr.getNext());
    } catch (NullPointerException e) {
    }
    try {
      curr.getNext().setPrev(curr.getPrev());
    } catch (NullPointerException e) {
    }
    size--;
    return curr.getData();
  }

  /**
   * Removes all of the songs from this list. The list will be empty after this call returns.
   */
  public void clear() {
    head = null;
    tail = null;
    size = 0;
  }

  /**
   * Returns true if this list contains a match with the specified song. More formally, returns true
   * if and only if this list contains at least one song e such that Objects.equals(o, e).
   * 
   * @param o - song whose presence in this list is to be tested
   *
   * @return true if this list contains the specified song
   */
  public boolean contains(Song o) {
    LinkedNode<Song> curr = head;
    for (int i = 0; i < size(); i++) {
      if (curr.getData().equals(o)) {
        return true;
      }
      curr = curr.getNext();

    }
    return false;
  }



  /**
   * Returns true if this list is empty.
   *
   * @return true if this list is empty
   */
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Returns an iterator to iterate through the songs in this list with respect to current playing
   * direction of this song player (either in the forward or in the backward direction)
   *
   * @return an Iterator to traverse the list of songs in this SongPlayer with respect to the
   *         current playing direction specified by the playingBackward data field.
   */
  @Override
  public java.util.Iterator<Song> iterator() {
    if (this.playingBackward) {
      return new BackwardSongIterator(tail);
    } else {
      return new ForwardSongIterator(head);
    }
  }

  /**
   * Returns the number of songs in this list.
   *
   * @return the number of songs in this list
   */
  public int size() {
    return this.size;
  }



  /**
   * Plays the songs in this song player in the current playing direction. This method MUST be
   * implemented using an enhanced for-each loop.
   *
   * @return a String representation of the songs in this song player. String representations of
   *         each song are separated by a newline. If this song player is empty, this method returns
   *         an empty string.
   */
  public String play() {
    String playString = "";
    for (Song song : this) {
      playString += song;
    }
    return playString;
  }

  /**
   * Mutator of the playingDirection of this song player. It switches the playing direction by
   * setting playingBackward to its opposite value.
   */
  public void switchPlayingDirection() {
    this.playingBackward = !this.playingBackward;
  }

}
