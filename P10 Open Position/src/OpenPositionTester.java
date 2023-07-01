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
 * This class implements unit test methods to check the correctness of Application,
 * ApplicationIterator, ApplicationQueue and OpenPosition classes in the assignment.
 *
 */
public class OpenPositionTester {
  /**
   * This method tests and makes use of the Application constructor, getter methods, toString() and
   * compareTo() methods.
   * 
   * @return true when this test verifies the functionality, and false otherwise
   */
  public static boolean testApplication() {
    // create an Application with valid input
    try {
      Application application = new Application("Matej", "popovski@wisc.edu", 90);
    } catch (Exception e) {
      return false;
    }

    try {
      Application application = new Application("Matej", "popovski@wisc.edu", 0);
    } catch (Exception e) {
      return false;
    }

    try {
      Application application = new Application("Matej", "popovski@wisc.edu", 100);
    } catch (Exception e) {
      return false;
    }

    // create an Application with invalid input:
    try {
      // blank name
      Application application = new Application("", "popovski@wisc.edu", 90);
      return false;
    } catch (IllegalArgumentException e) {

    } catch (Exception e) {
      return false;
    }


    // null email
    try {
      Application application = new Application("Matej", null, 90);
      return false;
    } catch (IllegalArgumentException e) {

    } catch (Exception e) {
      return false;
    }


    // no @ email
    try {
      Application application = new Application("Matej", "popovskiwisc.edu", 90);
      return false;
    } catch (IllegalArgumentException e) {

    } catch (Exception e) {
      return false;
    }

    // too many @ email
    try {
      Application application = new Application("Matej", "p@pop@wisc@edu", 90);
      return false;
    } catch (IllegalArgumentException e) {

    } catch (Exception e) {
      return false;
    }

    // invalid score
    try {
      Application application = new Application("Matej", "popowi@wisc.edu", -90);
      return false;
    } catch (IllegalArgumentException e) {

    } catch (Exception e) {
      return false;
    }

    try {
      Application application = new Application("Matej", "popovski@wisc.edu", 110);
      return false;
    } catch (IllegalArgumentException e) {

    } catch (Exception e) {
      return false;
    }


    // verify getters
    Application application = new Application("Matej", "popovski@wisc.edu", 90);

    if (!application.getName().equals("Matej"))
      return false;

    if (!application.getEmail().equals("popovski@wisc.edu"))
      return false;

    if (application.getScore() != 90)
      return false;


    // verify compareTo
    try {
      Application application2 = new Application("Matej", "popovski@wisc.edu", 80);
      Application application3 = new Application("Matej", "popovski@wisc.edu", 90);
      Application application4 = new Application("Matej", "popovski@wisc.edu", 95);

      if (!(application.compareTo(application4) < 0))
        return false;

      if (!(application.compareTo(application2) > 0))
        return false;

      if (!(application.compareTo(application3) == 0))
        return false;

    } catch (Exception e) {
      return false;
    }

    // verify toString
    String expectedApplication = "Matej:popovski@wisc.edu:90";
    if (!application.toString().equals(expectedApplication))
      return false;
    return true;
  }

  /**
   * This method tests and makes use of the ApplicationIterator class.
   * 
   * @return true when this test verifies the functionality, and false otherwise
   */
  public static boolean testApplicationIterator() {
    // create an ApplicationQueue with capacity at least 3
    // and at least 3 Applications with different scores
    ApplicationQueue queue = new ApplicationQueue(3);
    Application application1 = new Application("Matej", "popovsk@wisc.edu", 80);
    Application application2 = new Application("Klay", "klay@wisc.edu", 75);
    Application application3 = new Application("Thompson", "klay@wisc.edu", 70);

    // add those Applications to the queue
    queue.enqueue(application1);
    queue.enqueue(application2);
    queue.enqueue(application3);

    // verify that iterating through the queue gives you the applications in order of
    // INCREASING score
    Iterator<Application> iter = queue.iterator();
    if (iter.next().compareTo(application3) != 0)
      return false;
    if (iter.next().compareTo(application2) != 0)
      return false;
    if (iter.next().compareTo(application1) != 0)
      return false;

    return true;
  }

  /**
   * This method tests and makes use of the enqueue() and dequeue() methods in the ApplicationQueue
   * class.
   * 
   * @return true when this test verifies the functionality, and false otherwise
   */
  public static boolean testEnqueueDequeue() {
    // create an ApplicationQueue with capacity 3
    // and at least 4 Applications with different scores
    ApplicationQueue queue = new ApplicationQueue(3);
    Application application1 = new Application("Matej", "popovski@wisc.edu", 85);
    Application application2 = new Application("Klay", "klay@wisc.edu", 80);
    Application application3 = new Application("Thompson", "thompson@wisc.edu", 75);
    Application application4 = new Application("Jordan", "jordan@wisc.edu", 70);

    // enqueue an invalid value (null)
    try {
      queue.enqueue(null);
      return false;
    } catch (NullPointerException e) {

    } catch (Exception e) {
      return false;
    }

    // enqueue one valid application
    try {
      queue.enqueue(application1);
    } catch (NullPointerException e) {
      return false;
    } catch (Exception e) {
      return false;
    }

    // enqueue two more valid applications
    try {
      queue.enqueue(application2);
      queue.enqueue(application3);
    } catch (NullPointerException e) {
      return false;
    } catch (Exception e) {
      return false;
    }

    // enqueue one more application (exceeds capacity)
    try {
      queue.enqueue(application4);
    } catch (IllegalStateException e) {

    } catch (Exception e) {
      return false;
    }

    // dequeue one application (should be lowest score)
    try {
      if (queue.dequeue().compareTo(application3) != 0)
        return false;
    } catch (Exception e) {
      return false;
    }

    // dequeue all applications
    try {
      if (queue.dequeue().compareTo(application2) != 0)
        return false;
      if (queue.dequeue().compareTo(application1) != 0)
        return false;
    } catch (Exception e) {
      return false;
    }

    // dequeue from an empty queue
    try {
      queue.dequeue();
      return false;
    } catch (NoSuchElementException e) {

    } catch (Exception e) {
      return false;
    }

    return true;
  }

  /**
   * This method tests and makes use of the common methods (isEmpty(), size(), peek()) in the
   * ApplicationQueue class.
   * 
   * @return true when this test verifies the functionality, and false otherwise
   */
  public static boolean testCommonMethods() {
    // create an ApplicationQueue with 0 capacity (should fail)
    try {
      ApplicationQueue incorrectQueue = new ApplicationQueue(0);
      return false;
    } catch (IllegalArgumentException e) {

    } catch (Exception e) {
      return false;
    }

    // create an ApplicationQueue with capacity 3
    // and at least 3 Applications with different scores
    ApplicationQueue queue = new ApplicationQueue(3);
    Application application1 = new Application("Matej", "popovski@wisc.edu", 85);
    Application application2 = new Application("Klay", "klay@wisc.edu", 80);
    Application application3 = new Application("Thompson", "thompson@wisc.edu", 75);

    // verify the methods' behaviors on an empty queue
    if (!queue.isEmpty())
      return false;
    if (queue.size() != 0)
      return false;
    try {
      queue.peek();
      return false;
    } catch (NoSuchElementException e) {

    } catch (Exception e) {
      return false;
    }

    // add one Application and verify the methods' behaviors
    queue.enqueue(application1);
    if (queue.isEmpty())
      return false;
    if (queue.size() != 1)
      return false;
    if (queue.peek().compareTo(application1) != 0)
      return false;

    // add the rest of the Applications and verify the methods' behaviors
    queue.enqueue(application2);
    queue.enqueue(application3);
    if (queue.isEmpty())
      return false;
    if (queue.size() != 3)
      return false;
    if (queue.peek().compareTo(application3) != 0)
      return false;

    return true;
  }

  /**
   * This method tests and makes use of OpenPosition class.
   * 
   * @return true when this test verifies the functionality, and false otherwise
   */
  public static boolean testOpenPosition() {
    // create an OpenPosition with 0 capacity (should fail)
    try {
      OpenPosition invalidOpenPosition = new OpenPosition("Position", 0);
      return false;
    } catch (IllegalArgumentException e) {

    } catch (Exception e) {
      return false;
    }


    // create an OpenPosition with capacity 3
    // and at least 5 Applications with different scores
    OpenPosition validOP = new OpenPosition("Position", 3);
    Application application1 = new Application("Matej", "popovski@wisc.edu", 85);
    Application application2 = new Application("Klay", "klay@wisc.edu", 80);
    Application application3 = new Application("Thompson", "thompson@wisc.edu", 75);
    Application application4 = new Application("Jordan", "jordan@wisc.edu", 70);
    Application application5 = new Application("Lebron", "lebron@wisc.edu", 65);

    // verify that the 3 MIDDLE-scoring Applications can be added
    // don't use the highest and lowest scoring applications YET
    if (!validOP.add(application2))
      return false;
    if (!validOP.add(application3))
      return false;
    if (!validOP.add(application4))
      return false;


    // verify that getApplications returns the correct value for your input
    String expectedApplications = "Jordan:jordan@wisc.edu:70\n" + "Thompson:thompson@wisc.edu:75\n"
        + "Klay:klay@wisc.edu:80\n";
    if (!validOP.getApplications().equals(expectedApplications))
      return false;

    // verify that the result of getTotalScore is the sum of all 3 Application scores
    if (validOP.getTotalScore() != 225)
      return false;

    // verify that the lowest-scoring application is NOT added to the OpenPosition
    if (validOP.add(application5))
      return false;
    if (!validOP.getApplications().equals(expectedApplications))
      return false;

    // verify that the highest-scoring application IS added to the OpenPosition
    if (!validOP.add(application1))
      return false;

    // verify that getApplications has changed correctly
    expectedApplications = "Thompson:thompson@wisc.edu:75\n" + "Klay:klay@wisc.edu:80\n"
        + "Matej:popovski@wisc.edu:85\n";
    if (!validOP.getApplications().equals(expectedApplications))
      return false;

    // verify that the result of getTotalScore has changed correctly
    if (validOP.getTotalScore() != 240)
      return false;

    return true;
  }

  /**
   * This method calls all the test methods defined and implemented in your OpenPositionTester
   * class.
   * 
   * @return true if all the test methods defined in this class pass, and false otherwise.
   */
  public static boolean runAllTests() {
    if (testApplication() && testApplicationIterator() && testEnqueueDequeue()
        && testCommonMethods() && testOpenPosition()) {
      return true;
    }

    return false;
  }

  /**
   * Driver method defined in this OpenPositionTester class
   * 
   * @param args input arguments if any.
   */
  public static void main(String[] args) {
    System.out.println("Application: " + testApplication());
    System.out.println("ApplicationIterator: " + testApplicationIterator());
    System.out.println("Enqueue and Dequeue: " + testEnqueueDequeue());
    System.out.println("Common Methods: " + testCommonMethods());
    System.out.println("OpenPosition: " + testOpenPosition());
    System.out.println("runAllTests(): " + runAllTests());
  }
}
