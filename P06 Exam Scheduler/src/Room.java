//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P06 Exam Scheduler
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
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

/**
 * This is a Room class that holds the Room constructor and accessors
 * 
 */
public class Room {
  private String location;
  private int capacity;

  /**
   * This is a constructor for the Rooms. Instantiates the location and capacity for a room object
   * 
   * @param location location to add to object Room
   * @param capacity capacity to add to object Room
   * 
   */
  public Room(String location, int capacity) {
    if (capacity < 0) {
      throw new IllegalArgumentException("ERROR: Invalid number of students.");
    }
    this.location = location;
    this.capacity = capacity;
  }

  /**
   * Gets the location of a Room
   * 
   * @return location of the room
   * 
   */
  public String getLocation() {
    return this.location;
  }

  /**
   * Gets the capacity of a Room
   * 
   * @return capacity of a room
   * 
   */
  public int getCapacity() {
    return this.capacity;
  }

  /**
   * Reduces the capacity with a specific number
   * 
   * 
   * @param capacity number to reduce to
   * @throws IllegalArgumentException when capacity is greater than actual capacity
   * @return new Room object with reduced capacity
   * 
   */
  public Room reduceCapacity(int capacity) {
    if (capacity > this.capacity) {
      throw new IllegalArgumentException(
          "ERROR: Capacity to decrease is greater than actual capacity");
    }
    return new Room(this.location, this.capacity - capacity);
  }
}
