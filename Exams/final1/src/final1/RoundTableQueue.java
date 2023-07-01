
///////////////////////// CUMULATIVE QUIZ FILE HEADER //////////////////////////
// Full Name:Matej Popovski
// Campus ID:9083541632
// WiscEmail:popovski@wisc.edu
// (TODO: fill this out)
////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////
//
// This file contains TWO classes named RoundTableQueue and RoundTableIterator.
// You will need to complete the implementation of these two classes with
// respect to the provided requirements in the TODO tags for full credit.
//
// When creating new exception objects, including messages within these objects
// is optional.
//
// You are NOT allowed to add any data field to the provided classes
// You are NOT allowed to add any protected or public methods to the provided
// classes.
////////////////////////////////////////////////////////////////////////////////

// MAKE SURE TO SAVE your source file before uploading it to gradescope.

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class implements an array-based circular indexing queue to give turns to players to play in
 * their order of arrival to a round table.
 *
 */
public class RoundTableQueue {
  private String[] players; // array storing players taking turns in playing a card game
  private int size; // number of players sitting around the table (size of the queue)
  private int front; // index of the front of the queue

  /**
   * Creates a new array based queue with a capacity of "seatsAtTable" players. This queue should be
   * initialized to be empty (initially, both size and front must be zero). We assume that
   * seatsAtTable (the capacity of this queue) is greater than zero.
   * 
   * @param seatsAtTable the size of the array holding this queue data
   */
  public RoundTableQueue(int seatsAtTable) {
    // TODO #1: initialize all the data fields defined in the RoundTableQueue class
    this.players = new String[seatsAtTable];
    this.size = 0;
    this.front = 0;
  }

  /**
   * Checks whether there are any players sitting on this round table queue.
   * 
   * @return true when this queue contains zero players, and false otherwise.
   */
  public boolean isEmpty() {
    // TODO #2: implement this method
    return this.size == 0;

    // return false; // default return statement added to avoid compiler errors. Feel free to change
    // it.
  }


  /**
   * Gets the number of players in this queue (to be served)
   * 
   * @return the size of this queue
   */
  public int size() {
    // TODO #3: implement this method
    return this.size;

    // return -1; // default return statement added to avoid compiler errors. Feel free to change
    // it.
  }


  /**
   * Checks whether this round table queue is full.
   * 
   * @return true when this queue reached its capacity, and false otherwise.
   */
  public boolean isFull() {
    // TODO #4: implement this method
    return this.size >= this.players.length;
    // return false; // default return statement added to avoid compiler errors. Feel free to change
    // it.

  }

  /**
   * Accessor for the player that has been in this queue for the longest. This method does not add
   * or remove any players.
   * 
   * @return a reference to the player that has been in this queue the longest.
   * @throws IllegalStateException when called on an empty queue
   */
  public String peek() {
    if (isEmpty())
      throw new IllegalStateException("Cannot peek on an empty queue.");

    // TODO #5: return the player who has been in the queue for the longest
    return this.players[this.front];
    // return null; // default return statement added to avoid compiler errors. Feel free to change
    // it.
  }

  /**
   * Removes the player that has been in this queue for the longest.
   * 
   * @return a reference to the specific player that is being removed.
   * @throws NoSuchElementException when called on an empty queue
   */
  public String remove() {
    // TODO
    // #6: Throw an NoSuchElementException if remove was called on an empty queue
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    String tmp = peek(); // gets the element at the front of the queue

    // #7: Remove the element at front and decrement size
    players[front] = null;
    size--;
    // #8. Increment front by one (Recall that players is a circular indexing array!)
    front = (front + 1) % players.length;

    return tmp; // returns the removed element
  }

  // MAKE SURE TO SAVE your source file before uploading it to gradescope.

  // RoundTableQueue.toString() are completely provided to you in the following.

  /**
   * The string representation of the players in this queue should display each of the players in
   * this queue separated by a single space.
   * 
   * @return string representation of the ordered guests in this queue starting from the head
   */
  @Override
  public String toString() {
    String s = "";
    int i = front;
    while (i < front + size) {
      s += players[i % players.length] + " ";
      i++;
    }
    return s.trim();
  }

  /**
   * Checks the correctness of isEmpty, size and remove methods when called on an empty queue Note
   * that a tester method should not throw any exception. It must return by true or false.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testEmptyQueue() {
    // TODO
    // #9: Create an empty RoundQueue object with valid capacity
    RoundTableQueue queue = new RoundTableQueue(4);
    // #10: This tester must return false if any of the following properties is not satisfied.
    if (queue.isEmpty() == false || queue.size() != 0) {
      return false;
    }
    try {
      queue.remove();

    } catch (NoSuchElementException e) {
      return true;
    } catch (Exception e) {
      return false;
    }
    // A newly created serving queue must be empty and its size must be zero
    // A NoSuchElementException must be thrown when remove() is called on an empty queue
    // No other kind of exception should be thrown.

    // This tester should return true if no bugs is detected

    return false; // default return statement added to avoid compiler errors. Feel free to change
                  // it.

  }

  // MAKE SURE TO SAVE your source file before uploading it to gradescope.

  /**
   * Checks the correctness of RoundTableQueue.remove() method
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testRemove() {
    try {
      RoundTableQueue queue = new RoundTableQueue(4);

      // create a full queue
      String[] players = new String[] {"Hobbes", "Ashley", "Michelle", "Mouna"};
      queue.size = 4;
      queue.players = players;
      queue.front = 3;

      if (!queue.isFull())
        return false;

      // check the correctness remove
      String nextPlayer = queue.remove();
      if (queue.isEmpty() || queue.size() != 3 || !nextPlayer.equals("Mouna")) {
        return false;
      }

      if (!queue.toString().equals("Hobbes Ashley Michelle"))
        return false;

      if (!Arrays.deepEquals(players, new String[] {"Hobbes", "Ashley", "Michelle", null}))
        return false;

    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    return true;
  }

  /**
   * Driver method to call the tester methods
   * 
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    System.out.println("testEmptyQueue(): " + testEmptyQueue());
    System.out.println("testRemove(): " + testRemove());
  }
}



/**
 * This class implements an iterator to iterate over the elements stored in a circular array queue
 * of strings starting from its front.
 */
class RoundTableIterator implements Iterator<String> {
  private String[] data; // circular indexing array
  private int size; // size of the array data
  private int front; // front index of the serving queue
  private int index; // index used to iterate through the elements of the queue

  // You are NOT required to implement any tester for the RoundTableIterator class

  /**
   * Creates and initializes a new RoundTableIterator
   * 
   * @param data the circular-indexing array to iterate through its elements
   * @param head index of the element at the front of the circular indexing array
   * @param size number of elements stored in data
   */
  public RoundTableIterator(String[] data, int size, int front) {
    this.data = data;
    this.size = size;
    this.front = front;
    index = front;
  }

  // [HINT]: Check the provided implementation of RoundTableQueue.toString() method for hints on how
  // to implement the following hasNext and next methods

  /**
   * Checks if there are more elements in the iteration
   * 
   * @return true if there are more elements in the iteration and false otherwise
   */
  @Override
  public boolean hasNext() {
    // TODO #11: Complete the implementation of this method
    if (this.index != this.data.length) {
      return true;// default return statement added to avoid compiler errors. Feel free to change
                  // it.
    }
    return false;
  }

  /**
   * Returns the next element in the circular array and advances the iteration
   * 
   * @return the next element in this iteration
   * @throws NoSuchElementException if this iteration is exhausted
   */
  @Override
  public String next() {
    if (!hasNext()) {
      throw new NoSuchElementException("No more strings in this iteration.");
    }
    // TODO #12: Complete the implementation of this method

    return data[index++]; // default return statement added to avoid compiler errors. Feel free to
                          // change it.
  }

  // MAKE SURE TO SAVE your source file before uploading it to gradescope.
}
