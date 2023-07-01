//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Testers for ExamScheduler, Schedule, Room, and Courses
// Course: CS 300 Spring 2022
//
// Author: Yash Sancheti
// Email: ysancheti@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: Benjamin Wirch
// Partner Email: bwirch@wisc.edu
// Partner Lecturer's Name: Mouna Kacem
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// X Write-up states that pair programming is allowed for this assignment.
// X We have both read and understand the course Pair Programming Policy.
// X We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: NONE
// Online Sources: NONE
//
///////////////////////////////////////////////////////////////////////////////

/**
 * @author yashsancheti
 * @author benjaminwirch
 *
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

/**
 * This is the tester methods for all the Exam Scheduler Methods
 * 
 */
public class ExamSchedulerTester {

  /**
   * Checks the method Course() for any possible errors
   * 
   * @return true if working properly, false otherwise
   */
  public static boolean testCourse() {
    {
      String testName = "ECON777";
      int testNumStudents = 20;
      try {
        Course testCourse = new Course(testName, testNumStudents);
        if (!testCourse.getName().equals(testName)) {
          System.out.println("ERROR: getName() method has an error.");
          return false;
        }
        if (testCourse.getNumStudents() != testNumStudents) {
          System.out.println("ERROR: getNumStudents() method has an error.");
          return false;
        }
      } catch (Exception e) {
        System.out.println("ERROR: Invalid exception thrown!");
        return false;
      }
    }
    // Method throwing testing
    {
      String testName = "ECON777";
      int testNumStudents = -107;
      try {
        Course testCourse = new Course(testName, testNumStudents);
      } catch (IllegalArgumentException e) {
      } catch (Exception e) {
        System.out.println("ERROR: Invalid exception thrown!");
        return false;
      }
    }
    return true;
  }

  /**
   * This test Checks Room() for any possible errors
   * 
   * @return true if working properly, false otherwise
   */
  public static boolean testRoom() {
    // Tests constructors
    {
      String testLocation = "AY 210";
      int testCapacity = 150;
      try {
        Room testRoom = new Room(testLocation, testCapacity);
        if (!testRoom.getLocation().equals(testLocation)) {
          System.out.println("ERROR: getName() method has an error.");
          return false;
        }
        if (testRoom.getCapacity() != testCapacity) {
          System.out.println("ERROR: getNumStudents() method has an error.");
          return false;
        }
      } catch (Exception e) {
        System.out.println("ERROR: Invalid exception thrown!");
        return false;
      }
    }

    // checks if valid exceptions are thrown
    {
      String testLocation = "ENGL100";
      int testCapacity = -100;

      // Method throwing testing
      {
        try {
          Room testRoom = new Room(testLocation, testCapacity);
        } catch (IllegalArgumentException e) {
        } catch (Exception e) {
          System.out.println("ERROR: Invalid exception thrown!");
          return false;
        }
      }
    }

    // checks if Room.reduceCapacity works
    {
      final String location = "ENGL 100";
      final int capacity = 100;
      try {
        Room room = new Room(location, capacity);
        Room newRoom = room.reduceCapacity(25);
        if (newRoom == room) {
          System.out.println("ERROR: Room.reduceCapacity() failed to create a new Room object");
          return false;
        }
        if (newRoom.getCapacity() != (capacity - 25)) {
          System.out.println("ERROR: Room.reduceCapacity() does not work as expected");
          return false;
        }
      } catch (Exception e) {
        System.out.println("ERROR: Invalid exception thrown!");
        return false;
      }
    }
    return true;
  }

  /**
   * This method Checks the scheduleAccessors() method for any possible errors
   * 
   * @return true if working properly, false otherwise
   */
  public static boolean testScheduleAccessors() {
    // Test the getNumRooms() and the getNumCourses() methods
    {

      int testRoomSize = 100;
      int testCourseSize = 50;
      Room[] testRooms = new Room[testRoomSize];
      Course[] testCourses = new Course[testCourseSize];

      try {
        Schedule testSchedule = new Schedule(testRooms, testCourses);

        if (testSchedule.getNumRooms() != testRoomSize) {
          return false;
        }
        if (testSchedule.getNumCourses() != testCourseSize) {
          return false;
        }
      } catch (Exception e) {
        System.out.println("ERROR: Invalid exception thrown!");
        return false;
      }
    }

    {
      // Tests getRoom() and getCourse() methods
      int testRoomSize = 100;
      int testCourseSize = 60;
      int testRoomIndex = 70;
      int testCourseIndex = 40;
      Room[] testRooms = new Room[testRoomSize];
      Course[] testCourses = new Course[testCourseSize];

      testRooms[testRoomIndex] = new Room("AG 210", 2000);
      testCourses[testCourseIndex] = new Course("AG 211", 1150);

      try {
        Schedule schedule = new Schedule(testRooms, testCourses);
        Room newRoom = schedule.getRoom(testRoomIndex);
        if ((newRoom.getCapacity() != testRooms[testRoomIndex].getCapacity())
            || !(newRoom.getLocation().equals(testRooms[testRoomIndex].getLocation()))) {
          System.out.println("ERROR: Schedule.getRoom() does not work as expected");
          return false;
        }
        Course newCourse = schedule.getCourse(testCourseIndex);
        if (newCourse.getNumStudents() != testCourses[testCourseIndex].getNumStudents()
            || !(newCourse.getName().equals(testCourses[testCourseIndex].getName()))) {
          System.out.println("ERROR: Schedule.getCourse() does not work as expected");
          return false;
        }
      } catch (Exception e) {
        System.out.println("ERROR: Invalid exception thrown!");
        return false;
      }
    }

    // Tests isAssigned and isComplete Method
    {
      Course[] testCourses =
          new Course[] {new Course("AG217", 15), new Course("AG211", 90), new Course("AG212", 70)};
      Room[] testRooms =
          new Room[] {new Room("AG 150", 75), new Room("AG 151", 95), new Room("AG 152", 20)};
      Schedule testSchedule = ExamScheduler.findSchedule(testRooms, testCourses);
      if (!testSchedule.isComplete()) {
        System.out.println("ERROR: Schedule.isComplete() does not work as expected");
        return false;
      }
      if (!testSchedule.isAssigned(0)) {
        System.out.println("ERROR: Schedule.isAssigned() does not work as expected");
        return false;
      }
    }
    return true;
  }

  /**
   * This method Checks the assignCourse() method for any possible errors
   * 
   * @return true if working properly, false otherwise
   */
  public static boolean testAssignCourse() {
    {
      Course[] testCourses =
          new Course[] {new Course("AG217", 15), new Course("AG211", 90), new Course("AG212", 70)};
      Room[] testRooms =
          new Room[] {new Room("AG 150", 75), new Room("AG 151", 95), new Room("AG 152", 20)};
      Schedule schedule = new Schedule(testRooms, testCourses);
      Schedule newSchedule = schedule.assignCourse(0, 2);
      if (!newSchedule.isAssigned(0)) {
        System.out.println("ERROR: Schedule.assignCourse() failed!");
        return false;
      }
    }

    // Tests for IllegalArgumentException
    {
      Course[] testCourses =
          new Course[] {new Course("AG217", 15), new Course("AG211", 90), new Course("AG212", 70)};
      Room[] testRooms =
          new Room[] {new Room("AG 150", 75), new Room("AG 151", 95), new Room("AG 152", 20)};
      Schedule schedule = new Schedule(testRooms, testCourses);
      try {
        Schedule newSchedule = schedule.assignCourse(1, 2);
        return false;
      } catch (IllegalArgumentException e) {
      } catch (Exception e) {
        System.out.println("ERROR: Invalid exception thrown!");
        return false;
      }
    }

    // Tests for IndexOutOfBoundsException
    {
      Course[] testCourses =
          new Course[] {new Course("AG217", 15), new Course("AG211", 90), new Course("AG212", 70)};
      Room[] testRooms =
          new Room[] {new Room("AG 150", 75), new Room("AG 151", 95), new Room("AG 152", 20)};
      Schedule schedule = new Schedule(testRooms, testCourses);
      try {
        Schedule newSchedule = schedule.assignCourse(100, 200);
        return false;
      } catch (IndexOutOfBoundsException e) {
      } catch (Exception e) {
        System.out.println("ERROR: Invalid exception thrown!");
        return false;
      }
    }
    return true;
  }

  /**
   * This method Checks the findSchedule() method for any possible errors
   * 
   * @return true if working properly, false otherwise
   */
  public static boolean testFindSchedule() {
    {
      Course[] testCourses =
          new Course[] {new Course("AG217", 15), new Course("AG211", 90), new Course("AG212", 70)};
      Room[] testRooms =
          new Room[] {new Room("AG 150", 75), new Room("AG 151", 95), new Room("AG 152", 20)};
      String expectedSchedule = "{AG217: AG 152, AG211: AG 151, AG212: AG 150}";
      Schedule schedule = ExamScheduler.findSchedule(testRooms, testCourses);;
      if (!schedule.toString().equals(expectedSchedule)) {
        return false;
      }
    }

    {
      Course[] testCourses =
          new Course[] {new Course("AG217", 15), new Course("AG211", 100), new Course("AG212", 70)};
      Room[] testRooms =
          new Room[] {new Room("AG 150", 75), new Room("AG 151", 95), new Room("AG 152", 20)};
      try {
        Schedule schedule = ExamScheduler.findSchedule(testRooms, testCourses);
        return false;
      } catch (IllegalStateException e) {
      } catch (Exception e) {
        System.out.println("ERROR: Invalid exception thrown!");
        return false;
      }
      return true;
    }
  }

  /**
   * This method Checks the findAllSchedules() method for any possible errors
   * 
   * @return true if working properly, false otherwise
   */
  public static boolean testFindAllSchedules() {
    // 1. Valid Course
    {
      Course[] testCourses =
          new Course[] {new Course("AG217", 15), new Course("AG211", 80), new Course("AG212", 70)};
      Room[] testRooms =
          new Room[] {new Room("AG 150", 75), new Room("AG 151", 100), new Room("AG 152", 15)};

      ArrayList<Schedule> schedules = ExamScheduler.findAllSchedules(testRooms, testCourses);
      String expectedSchedule1 = "{AG217: AG 151, AG211: AG 151, AG212: AG 150}";
      String expectedSchedule2 = "{AG217: AG 152, AG211: AG 151, AG212: AG 150}";
      for (Schedule schedule : schedules) {
        String strings = schedule.toString();
        if (!(strings.equals(expectedSchedule1) || strings.equals(expectedSchedule2))) {
          return false;
        }
      }
    }
    return true;
  }



  public static void main(String[] args) {
    System.out.println(testCourse());
    System.out.println(testRoom());
    System.out.println(testScheduleAccessors());
    System.out.println(testAssignCourse());
    System.out.println(testFindSchedule());
    System.out.println(testFindAllSchedules());
  }
}
