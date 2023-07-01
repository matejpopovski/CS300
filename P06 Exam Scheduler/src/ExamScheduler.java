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
 * This class schedules the exams based on specific input. This class contains recursive methods
 */
public class ExamScheduler {

  /**
   * Finds a schedule from an array
   * 
   * @param rooms   array of rooms
   * @param courses array of courses
   * @return
   */
  public static Schedule findSchedule(Room[] rooms, Course[] courses) {

    return findScheduleHelper(new Schedule(rooms, courses), 0);
  }

  /**
   * Checks to see if the schedule with specific index is complete
   * 
   * @param schedule Schedule to check for completion
   * @param index    index at which to check
   * @throws IllegalStateException when schedule is not complete or valid
   * @return completed schedule
   */
  private static Schedule findScheduleHelper(Schedule schedule, int index) {
    if (index == schedule.getNumCourses()) {
      if (schedule.isComplete()) {
        return schedule;

      }
      throw new IllegalStateException("ERROR: Schedule not complete");

    }
    if (schedule.isAssigned(index))
      return findScheduleHelper(schedule, index + 1);

    else {
      for (int i = 0; i < schedule.getNumRooms(); i++) {
        try {
          Schedule newSchedule = schedule.assignCourse(index, i);

          return findScheduleHelper(newSchedule, index + 1);
        } catch (Exception e) {

        }
      }
      throw new IllegalStateException("ERROR: Schedule not valid");
    }
  }

  /**
   * Finds all schedules
   * 
   * @param rooms   Array of rooms
   * @param courses Array of courses
   * @throws IllegalStateException when schedule is not complete
   * @return the array of schedules
   */

  public static ArrayList<Schedule> findAllSchedules(Room[] rooms, Course[] courses) {
    return findAllSchedulesHelper(new Schedule(rooms, courses), 0);
  }

  private static ArrayList<Schedule> findAllSchedulesHelper(Schedule schedule, int index) {


    ArrayList<Schedule> schedules = new ArrayList<Schedule>();

    if (index == schedule.getNumCourses()) {
      if (schedule.isComplete()) {
        schedules.add(schedule);

        return schedules;
      }
      throw new IllegalStateException("ERROR: Schedule not complete");
    }
    if (schedule.isAssigned(index)) {

      schedules.addAll(findAllSchedulesHelper(schedule, index + 1));
      return schedules;

    } else {
      for (int i = 0; i < schedule.getNumRooms(); i++) {
        try {

          Schedule newSchedule = schedule.assignCourse(index, i);

          schedules.addAll(findAllSchedulesHelper(newSchedule, index + 1));
        } catch (Exception e) {

        }
      }
      return schedules;
    }
  }
}
