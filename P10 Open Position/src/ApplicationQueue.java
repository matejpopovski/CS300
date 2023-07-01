//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P10 Open Position
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
import java.util.NoSuchElementException;

/**
 * Array-based heap implementation of a priority queue containing Applications. Guarantees the
 * min-heap invariant, so that the Application at the root should have the lowest score, and
 * children always have a higher or equal score as their parent. The root of a non-empty queue is
 * always at index 0 of this array-heap.
 */
public class ApplicationQueue implements PriorityQueueADT<Application>, Iterable<Application> {
  private Application[] queue; // array min-heap of applications representing this priority queue
  private int size; // size of this priority queue

  /**
   * Creates a new empty ApplicationQueue with the given capacity
   * 
   * @param capacity Capacity of this ApplicationQueue
   * @throws IllegalArgumentException with a descriptive error message if the capacity is not a
   *                                  positive integer
   */
  public ApplicationQueue(int capacity) throws IllegalArgumentException {
    // checking if capacity is valid or not
    if (!(capacity > 0)) {
      throw new IllegalArgumentException("ERROR: Capacity is negative!");
    }

    // initializing fields appropriately
    queue = new Application[capacity];
    size = 0;
  }

  /**
   * Checks whether this ApplicationQueue is empty
   * 
   * @return {@code true} if this ApplicationQueue is empty
   */
  @Override
  public boolean isEmpty() {
    if (size == 0)
      return true;
    return false;
  }

  /**
   * Returns the size of this ApplicationQueue
   * 
   * @return the size of this ApplicationQueue
   */
  @Override
  public int size() {
    return size;
  }

  /**
   * Adds the given Application to this ApplicationQueue and use the percolateUp() method to
   * maintain min-heap invariant of ApplicationQueue. Application should be compared using the
   * Application.compareTo() method.
   * 
   * 
   * @param o Application to add to this ApplicationQueue
   * @throws NullPointerException  if the given Application is null
   * @throws IllegalStateException with a descriptive error message if this ApplicationQueue is full
   */
  @Override
  public void enqueue(Application o) throws NullPointerException, IllegalStateException {
    // check if the application is null or not
    if (o == null) {
      throw new NullPointerException("ERROR: Application is null!");
    }

    // check if the ApplicationQueue is full or not
    if (queue.length == size) {
      throw new IllegalStateException("ERROR: The ApplicationQueue is full");
    }

    // adding the application to the queue and percolating to restore the heap condition
    queue[size] = o;
    percolateUp(size);
    size++;
  }

  /**
   * Removes and returns the Application at the root of this ApplicationQueue, i.e. the Application
   * with the lowest score.
   * 
   * @return the Application in this ApplicationQueue with the smallest score
   * @throws NoSuchElementException with a descriptive error message if this ApplicationQueue is
   *                                empty
   */
  @Override
  public Application dequeue() throws NoSuchElementException {
    // checks if ApplicationQueue is empty or not
    if (isEmpty()) {
      throw new NoSuchElementException("ERROR: The ApplicationQueue is not empty!");
    }

    // saving the lowest-scoring application
    Application lowestScore = queue[0];

    // replacing the root of the heap and percolating to restore the heap condition
    queue[0] = queue[size - 1];
    queue[size - 1] = null;
    percolateDown(0);
    size--;

    // return the lowest-scoring application
    return lowestScore;
  }

  /**
   * An implementation of percolateUp() method. Restores the min-heap invariant of the tree by
   * percolating a leaf up the tree. If the element at the given index does not violate the min-heap
   * invariant (it occurs after its parent), then this method does not modify the heap. Otherwise,
   * if there is a heap violation, swap the element with its parent and continue percolating the
   * element up the heap.
   * 
   * This method may be implemented recursively OR iteratively.
   * 
   * Feel free to add private helper methods if you need them.
   * 
   * @param i index of the element in the heap to percolate upwards
   * @throws IndexOutOfBoundsException if index is out of bounds - do not catch the exception
   */
  private void percolateUp(int i) throws IndexOutOfBoundsException {
    // implementing the min-heap percolate up algorithm to modify the heap in place
    int parent = (i - 2) / 2;
    if (i == 0) {
      return;
    }

    if (queue[i].compareTo(queue[parent]) < 0) {
      Application application = queue[parent];
      queue[parent] = queue[i];
      queue[i] = application;
      percolateUp(parent);
    } else {
      return;
    }
  }
  
  /**
   * An implementation of percolateDown() method. Restores the min-heap invariant of a given subtree
   * by percolating its root down the tree. If the element at the given index does not violate the
   * min-heap invariant (it is due before its children), then this method does not modify the heap.
   * Otherwise, if there is a heap violation, then swap the element with the correct child and
   * continue percolating the element down the heap.
   * 
   * This method may be implemented recursively OR iteratively.
   * 
   * @param i index of the element in the heap to percolate downwards
   * @throws IndexOutOfBoundsException if index is out of bounds - do not catch the exception
   */
  private void percolateDown(int i) throws IndexOutOfBoundsException {
    // implementing the min-heap percolate down algorithm to modify the heap in place
    if (i >= size) {
      throw new IndexOutOfBoundsException("ERROR: Index is out of range!");
    }

    if (2 * i + 1 > queue.length && 2 * i + 2 > queue.length) {
      return;
    }

    // changing the left child of the BST
    Application firstApplication;
    try {
      firstApplication = queue[2 * i + 1];
    } catch (Exception e) {
      firstApplication = null;
    }

    // changing the right child of the BST
    Application secondApplication;
    try {
      secondApplication = queue[2 * i + 2];
    } catch (Exception e) {
      secondApplication = null;
    }

    Application tempApplication;
    int newIndex;
    // if left child has nothing and the right child has a value
    if (firstApplication == null && secondApplication != null) {
      tempApplication = secondApplication;
      newIndex = 2 * i + 2;
    }

    // if right child has nothing and the left child has a value
    else if (firstApplication != null && secondApplication == null) {
      tempApplication = firstApplication;
      newIndex = 2 * i + 1;
    }

    // if both left and right have nothing
    else if (firstApplication == null && secondApplication == null) {
      return;
    }

    // if both left and right have a value
    else {
      if (secondApplication.compareTo(firstApplication) < 0) {
        tempApplication = secondApplication;
        newIndex = 2 * i + 2;
      }

      if (firstApplication.compareTo(secondApplication) < 0) {
        tempApplication = firstApplication;
        newIndex = 2 * i + 1;
      }

      else {
        tempApplication = firstApplication;
        newIndex = 2 * i + 1;
      }
    }

    if (queue[i] == null) {
      return;
    }

    if (queue[i].compareTo(tempApplication) > 0) {
      Application newApp = queue[i];
      queue[i] = queue[newIndex];
      queue[newIndex] = newApp;
      percolateDown(newIndex);
    } else {
      return;
    }
  }

  /**
   * Returns the Application at the root of this ApplicationQueue, i.e. the Application with the
   * lowest score.
   * 
   * @return the Application in this ApplicationQueue with the smallest score
   * @throws NoSuchElementException if this ApplicationQueue is empty
   */
  @Override
  public Application peek() throws NoSuchElementException {
    // verifying that the queue is not empty
    if (isEmpty()) {
      throw new NoSuchElementException("ERROR: ApplicationQueue is empty!");
    }

    // returning the lowest-scoring application
    return queue[0];
  }

  /**
   * Returns a String representing this ApplicationQueue, where each element (application) of the
   * queue is listed on a separate line, in order from the lowest score to the highest score.
   * 
   * This implementation is provided.
   * 
   * @see Application#toString()
   * @see ApplicationIterator
   * @return a String representing this ApplicationQueue
   */
  @Override
  public String toString() {
    StringBuilder val = new StringBuilder();

    for (Application a : this) {
      val.append(a).append("\n");
    }

    return val.toString();
  }

  /**
   * Returns an Iterator for this ApplicationQueue which proceeds from the lowest-scored to the
   * highest-scored Application in the queue.
   * 
   * This implementation is provided.
   * 
   * @see ApplicationIterator
   * @return an Iterator for this ApplicationQueue
   */
  @Override
  public Iterator<Application> iterator() {
    return new ApplicationIterator(this);
  }
  
  /**
   * Returns a deep copy of this ApplicationQueue containing all of its elements in the same order.
   * This method does not return the deepest copy, meaning that you do not need to duplicate
   * applications. Only the instance of the heap (including the array and its size) will be
   * duplicated.
   * 
   * @return a deep copy of this ApplicationQueue. The returned new application queue has the same
   *         length and size as this queue.
   */
  public ApplicationQueue deepCopy() {
    // deep copying the ApplicationQueue
    ApplicationQueue newQueue = new ApplicationQueue(queue.length);
    for (int i = 0; i < queue.length; i++) {
      if (queue[i] == null) {
        break;
      }
      newQueue.enqueue(queue[i]);
    }

    return newQueue;
  }
  
}
