///////////////////////// CUMULATIVE QUIZ FILE HEADER //////////////////////////
// Full Name: Matej Popovski
// Campus ID: 9083541632
// WiscEmail: popovski@wisc.edu
////////////////////////////////////////////////////////////////////////////////
package exam1;
import java.util.Arrays;
import java.util.NoSuchElementException;

////////////////////////////////////////////////////////////////////////////////
//
// This file contains ONE class. You will need to complete the implementation
// this class with respect to the provided requirements in the TODO tags for
// full credit.
//
// When creating new exception objects, including messages within these objects
// is optional.
////////////////////////////////////////////////////////////////////////////////

/**
 * This class implements methods to store and manage the list of courses in a department. The course
 * numbers are stored in an oversize array defined by a reference to an array which stores course
 * numbers of type integers and an int variable which keep track of the size of the array.
 * 
 */
public class CourseList {

  /**
   * Removes the course number stored at the end of the oversize array defined by courses and size.
   * The array courses contains non null references from index 0 to index size-1. All references in
   * the range of indexes size .. courses.length-1 are null.
   * 
   * @param courses an array which stores course numbers
   * @param size    size of the courses array
   * @return the size of the oversize array after removing the last course number (stored at the end
   *         of the oversize array).
   * @throws IllegalArgumentException if courses is null or if size is negative ( < 0)
   * @throws NoSuchElementException   if the oversize array is empty
   */
  public static int removeLast(Integer[] courses, int size)
      throws IllegalArgumentException, NoSuchElementException {
    // TODO
    // 1. throw an IllegalArgumentException if courses is null or size < 0
    if (courses == null || size < 0) {
      throw new IllegalStateException();
    }

    // 2. throw a NoSuchElementException if the provided oversize array is empty
    if (size == 0) {
      throw new NoSuchElementException();
    }


    // 3. removes the course number at the end of the courses array
    courses[size - 1] = null;
    size--;


    // 4. return size
    return size;
    // default return statement added to avoid compiler errors. Feel free to change it.
  }

  /**
   * Gets the portion of undergraduate level courses in the provided course list defined by courses
   * and size. An undergraduate level course has a number less to 400 ( < 400)
   * 
   * @param courses an array which stores Integers representing valid course numbers
   * @param size    number of course numbers stored in courses
   * @return the portion (between 0.0 and 1.0) of undergraduate level courses among all the course
   *         numbers stored in the array courses.
   */
  public static double getUndergraduateCoursesPortion(Integer[] courses, int size) {
    // 5. TODO implement this method

    int count = 0;
    for (int i = 0; i < size; i++) {
      // courses[i]
      if (Integer.compare(400, courses[i]) > 0) {
        count++;
      }

    }
    return count / size; // default return statement added to avoid compiler errors. Feel free to
                         // change it.
  }

  /**
   * Checks the correctness of the implementation of the CourseList.getUndergraduateCoursesPortion()
   * method
   * 
   * @return true if the method passes this test and false otherwise
   */
  public static boolean testGetUndergraduateCoursesPortion() {
    // Define the test scenario
    // create a non-empty oversize array of integers
    Integer[] courseNumbers = new Integer[] {220, 407, 435, 300, 240, 635, null, null, null};
    int size = 6;
    double expectedPortion = 0.5; // expected output
    try {
      // call getUndergratuateCoursesPortion() and check whether it returns the expected output
      double portion = getUndergraduateCoursesPortion(courseNumbers, size);
      if (Math.abs(portion - expectedPortion) > 0.01) // compare portion and expectedPortion
        return false;
    } catch (Exception e) { // catches any unexpected exception
      return false;
    }
    return true; // no bugs detected by this tester
  }

  /**
   * Checks whether the CourseList.removeLast method works as expected when called to remove the
   * last course number from a non-empty oversize array of Integers
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testSuccessfulCourseNumberRemoval() {
    // create an non-empty oversize array
    Integer[] courseNumbers = new Integer[] {320, 502, 220, 300, 240, 110, null, null, null};
    int size = 6;
    try {
      // try to remove the last course number
      size = removeLast(courseNumbers, size);
      // expected content of courseNumbers array after removeLast() is called
      Integer[] expectedNumbers = new Integer[] {320, 502, 220, 300, 240, null, null, null, null};
      // check whether the size and the content of the array are correct
      if (size != 5)
        return false;
      if (!Arrays.deepEquals(courseNumbers, expectedNumbers))
        return false;
    } catch (Exception e) {
      return false; // no exception is expected to be thrown
    }

    return true; // no bugs detected by this tester

  }

  /**
   * Checks whether the the CourseList.removeLast() method throws a NoSuchElementException without
   * making any change to the contents of the oversize array provided as input parameter or to its
   * size when passed an empty oversize array.
   * 
   * Note that a tester method should not throw any exception. It must return by true or false.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testRemoveLastEmptyCourseList() {
    // TODO
    // 1. Create an empty oversize array of Integers which can hold up to 10 integers
    Integer[] courseNumbers =
        new Integer[] {null, null, null, null, null, null, null, null, null, null};
    int size = 0;
    // 2. Try to call CourseList.removeLast() passing its the reference to the created array and its
    // size
    try {
      size = CourseList.removeLast(courseNumbers, size);

      Integer[] expectedNumbers =
          new Integer[] {null, null, null, null, null, null, null, null, null, null};

      if (size != 0)
        return false;
      if (!Arrays.deepEquals(courseNumbers, expectedNumbers))
        return false;
      // 3. Expected behavior: A NoSuchElementException should be thrown
      // If no exception was thrown return false.
      // If an exception other than NoSuchElementException was thrown, return false.
    } catch (NoSuchElementException except) {
      return true;
    } catch (Exception e) {
      return false;
    }

    return true; // no bugs detected by this tester

    // 4. If you detect any changes to the contents or the size of the oversize array return false
    // You can use Arrays.deepEquals() method to check whether there have been any changes to the
    // contents of the array

    // 5. This tester returns true if no bugs are detected

    // default return statement added to avoid compiler errors. Feel free to change
    // it.
  }

  /**
   * Main method to call the test methods
   * 
   * @param args - input arguments if any
   */
  public static void main(String[] args) {
    System.out
        .println("testGetUndergraduateCoursesPortion(): " + testGetUndergraduateCoursesPortion());
    System.out
        .println("testSuccessfulCourseNumberRemoval(): " + testSuccessfulCourseNumberRemoval());
    System.out.println("testRemoveLastEmptyCourseList(): " + testRemoveLastEmptyCourseList());
  }
}
