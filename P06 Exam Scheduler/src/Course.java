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
 * This is a class that holds the name and number of students per course
 * 
 */
public class Course {
  private String name;
  private int numStudents;

  /**
   * Constructor for the Course object
   * 
   * @param name        Name of the course
   * @param numStudents Number of students in the course
   * @throws IllegalArgumentException if the number of students is negative
   * 
   */
  public Course(String name, int numStudents) {
    if (numStudents < 0) {
      throw new IllegalArgumentException("ERROR: Invalid number of students.");
    }
    this.name = name;
    this.numStudents = numStudents;
  }

  /**
   * This method gets the name of the course
   * 
   * @return name of specified course
   * 
   */
  public String getName() {
    return this.name;
  }

  /**
   * This method gets number of students in the course
   * 
   * @return number of students in specified course
   * 
   */
  public int getNumStudents() {
    return this.numStudents;
  }
}
