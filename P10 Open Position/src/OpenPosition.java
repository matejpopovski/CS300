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
/**
 * A application handler of an open position using priority queue. Only saves a new Application when
 * the queue is not full, or when it can replace older, lower-scored ones with its higher scores.
 */
import java.util.Iterator;

public class OpenPosition {
  private String positionName;
  private ApplicationQueue applications; // the priority queue of all applications
  private int capacity; // the number of vacancies

  /**
   * Creates a new open position with the given capacity
   * 
   * @param capacity the number of vacancies of this position
   * @throws IllegalArgumentException with a descriptive error message if the capacity is not a
   *                                  positive integer
   */
  public OpenPosition(String positionName, int capacity) throws IllegalArgumentException {
    if (capacity <= 0) {
      throw new IllegalArgumentException("ERROR: The capacity is not a positive integer!");
    }

    this.positionName = positionName;
    this.capacity = capacity;
    this.applications = new ApplicationQueue(capacity);
  }

  public String getPositionName() {
    return this.positionName;
  }

  /**
   * Tries to add the given Application to the priority queue of this position. return False when
   * the new Application has a lower score than the lowest-scored Application in the queue.
   * 
   * @return Whether the given Application was added successfully
   */
  public boolean add(Application application) {
    // if the queue is full, determine whether this application has a higher score than
    // the current lowest-scoring application; if not, do not add it
    if (applications.size() == capacity) {
      if (application.compareTo(applications.peek()) <= 0) {
        return false;
      } else {
        applications.dequeue();
        applications.enqueue(application);
        return true;
      }
    }
    // if there is room in the queue OR this application has a higher score than the current
    // lowest-scoring application, add it to the queue
    else {
      applications.enqueue(application);
      return true;
    }
  }

  /**
   * Returns the list of Applications in the priority queue.
   * 
   * @return The list of Applications in the priority queue, in increasing order of the scores.
   */
  public String getApplications() {
    return applications.toString();
  }

  /**
   * Returns the total score of Applications in the priority queue.
   * 
   * @return The total score of Applications in the priority queue.
   */
  public int getTotalScore() {
    // calculating the total score of all applications currently in the queue
    int score = 0;
    Iterator<Application> iter = applications.iterator();
    for (int i = 0; i < applications.size(); i++) {
      score += iter.next().getScore();
    }
    return score;
  }
}
