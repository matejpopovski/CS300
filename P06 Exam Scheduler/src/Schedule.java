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
 * This is a Schedule class which holds the Schedle constructor and accessors
 * 
 */
public class Schedule {

  private Room[] rooms;
  private Course[] courses;
  private int[] assignments;

  /**
   * This is the constructor for Schedule class. It instantiates rooms, courses, and assignments.
   * 
   * @param rooms   Array of rooms
   * @param courses Array of courses
   * 
   */
  public Schedule(Room[] rooms, Course[] courses) {
    this.rooms = rooms;
    this.courses = courses;
    this.assignments = new int[courses.length];
    for (int i = 0; i < assignments.length; i++) {
      this.assignments[i] = -1;
    }
  }

  /**
   * This is the constructior for Schedule class with assignements field. It instantiates rooms,
   * courses, and assignments.
   * 
   * @param rooms       Array of rooms
   * @param courses     Array of courses
   * @param assignments Array of assignements
   * 
   */
  private Schedule(Room[] rooms, Course[] courses, int[] assignments) {
    this.rooms = rooms;
    this.courses = courses;
    this.assignments = assignments;
  }

  /**
   * Gets the number of Rooms from the rooms array
   * 
   * @return Length of rooms array
   * 
   */
  public int getNumRooms() {
    return this.rooms.length;
  }

  /**
   * Gets Room with the specific index in the array
   * 
   * @param index position at what room the user is looking for
   * @throws IndexOutOfBoundsException when index is invalid
   * @return Returns specific Room object
   * 
   */
  public Room getRoom(int index) {
    try {
      return this.rooms[index];
    } catch (IndexOutOfBoundsException e) {
      throw new IndexOutOfBoundsException("ERROR: Index is Invalid");
    }
  }

  /**
   * Gets the number of courses
   * 
   * @return number of courses
   * 
   */
  public int getNumCourses() {
    return this.courses.length;
  }

  /**
   * Gets the specific course at the given index
   * 
   * @param index position at which specific course is located
   * @throws IndexOutOfBoundsException if index is invalid
   * @return returns specified course
   * 
   */
  public Course getCourse(int index) {
    try {
      return this.courses[index];
    } catch (IndexOutOfBoundsException e) {
      throw new IndexOutOfBoundsException("ERROR: Index is Invalid");
    }
  }

  /**
   * Checks wheather a value is already assigned
   * 
   * @param index position in assignments array to check for assignment
   * @return true if assigned, false otherwise
   * 
   */
  public boolean isAssigned(int index) {
    return this.assignments[index] != -1;
  }

  /**
   * Gets element from assignment array at the specified index
   * 
   * @param index index to get assignment of
   * @throws IllegalArgumentException if index is invalid
   * @return Assignment in question
   * 
   */
  public Room getAssignment(int index) {
    try {
      if (this.assignments[index] == -1) {
        throw new IllegalArgumentException("ERROR: Course has not been assigned a room");
      }
      return this.rooms[this.assignments[index]];
    } catch (IndexOutOfBoundsException e) {
      throw new IndexOutOfBoundsException("ERROR: The Index is Invalid");
    }
  }

  /**
   * Checks to see if the method is complete
   * 
   * @return true if complete, false otherwise
   * 
   */
  public boolean isComplete() {
    for (int i = 0; i < assignments.length; i++) {
      if (this.assignments[i] == -1) {
        return false;
      }
    }
    return true;
  }

  /**
   * Assigns the course array with indexes
   * 
   * @param index1 Course index
   * @param index2 Room index
   * @throws IllegalArgumentException  when course has already been assigned to a room or doesnt
   *                                   have capacity
   * @throws IndexOutOfBoundsException when index is invalid
   * @return Final schedule result
   * 
   */
  public Schedule assignCourse(int index1, int index2) {
    try {
      if (isAssigned(index1)
          || this.rooms[index2].getCapacity() < this.courses[index1].getNumStudents()) {
        throw new IllegalArgumentException(
            "ERROR: Course has been assigned a room or It Doesnt have Enough Place");
      }
      Room[] newRooms = Arrays.copyOf(this.rooms, rooms.length);
      Course[] newCourses = Arrays.copyOf(this.courses, courses.length);
      int[] newAssignments = Arrays.copyOf(this.assignments, assignments.length);
      newAssignments[index1] = index2;
      newRooms[index2] = newRooms[index2].reduceCapacity(courses[index1].getNumStudents());
      return new Schedule(newRooms, newCourses, newAssignments);
    } catch (IndexOutOfBoundsException e) {
      throw new IndexOutOfBoundsException("ERROR: The Index is Invalid");
    }
  }

  /**
   * Overrides toString method that formats into the correct string representation
   * 
   * @return String with correct string representation
   * 
   */
  @Override
  public String toString() {
    String stringRepresentation = "{";
    for (int i = 0; i < courses.length; i++) {
      if (assignments[i] == -1) {
        stringRepresentation += this.courses[i].getName() + ": Unassigned, ";
      } else {
        stringRepresentation +=
            this.courses[i].getName() + ": " + this.rooms[assignments[i]].getLocation() + ", ";
      }
    }
    stringRepresentation =
        stringRepresentation.substring(0, stringRepresentation.lastIndexOf(", "));
    return stringRepresentation + "}";
  }
}
