//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P04 Access Control
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

import java.util.NoSuchElementException;

/**
 * This class tests the AccessControl class
 */
public class AccessControlTester {

  /**
   * Main method that runs runAllTests() method.
   * 
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    System.out.println(runAllTests());
  }

  /**
   * Runs all the tests within this class
   *
   * @return true if all tests pass, false if any of the tests fail
   */
  public static boolean runAllTests() {
    if (!(testUserConstructorAndMethods() && testAccessControlIsValidLoginNotValidUser()
        && testAddUserWithNoAdminPowers() && testAddRemoveUserWithAdminPowers())) {
      return false;
    }

    return true;
  }

  /**
   * runs tests for the user constructor method and other methods within the class
   *
   * @return true if all tests pass, false if any tests fail
   */
  public static boolean testUserConstructorAndMethods() {
    User matej = new User("matej", "password", false);


    if (!(matej.getUsername().equals("matej") && matej.getIsAdmin() == false
        && matej.isValidLogin("password") == true)) {
      return false;
    }

    matej.setPassword("matej123");
    if (!(matej.isValidLogin("matej123"))) {
      return false;
    }


    matej.setIsAdmin(true);
    if (!(matej.getIsAdmin() == true)) {
      return false;
    }


    return true;
  }

  /**
   * runs tests for the access control isValidLogin Method
   *
   * @return true if all tests pass, false if any tests fail
   */
  public static boolean testAccessControlIsValidLoginNotValidUser() {
    AccessControl controll = new AccessControl();

    controll.setCurrentUser("admin");
    controll.addUser("rokiroki");
    controll.addUser("matejco");
    controll.addUser("Arnold");

    controll.setCurrentUser("rokiroki");
    controll.changePassword("password");


    if (AccessControl.isValidLogin("rokiroki", "password") != true) {

      System.out.println("ERROR: invalid login!");

      return false;
    } // ERROR: invalid login


    if (AccessControl.isValidLogin("matej", "password") != false) {

      System.out.println("ERROR: invalid login!");

      return false;
    }


    if (AccessControl.isValidLogin(null, null) != false) {

      System.out.println("ERROR: invalid login!");

      return false;
    }

    controll.setCurrentUser("matejco");
    controll.changePassword("beast");


    if (AccessControl.isValidLogin("matejco", "beast") != true) {

      System.out.println("ERROR: invalid login!");

      return false;
    }

    controll.setCurrentUser("Arnold");
    controll.changePassword("biceps");

    if (AccessControl.isValidLogin("Arnold", "biceps") != true) {

      System.out.println("ERROR: invalid login!");
      return false;
    }


    return true;
  }

  /**
   * Runs tests for the access control addUser metod
   *
   * @return true if all tests pass, false if any of the tests fail.
   */
  public static boolean testAddUserWithNoAdminPowers() {

    AccessControl controll = new AccessControl();
    controll.setCurrentUser("admin");
    controll.addUser("Putin", false);
    controll.setCurrentUser("Putin");

    if (controll.addUser("77777") != false) {
      return false;
    }
    if (AccessControl.isValidLogin("77777", "changeme") != false) {
      return false;
    }


    return true;
  }

  /**
   * runs tests for the methods: access control addUser and removeUser()
   *
   * @return true if all tests pass, false if any of the tests fail
   */
  public static boolean testAddRemoveUserWithAdminPowers() {
    AccessControl controll = new AccessControl();
    controll.setCurrentUser("admin");
    controll.addUser("Popovski", true);
    controll.setCurrentUser("Popovski");

    if (controll.addUser("Makedonija", false) != true) {
      return false;
    }

    if (controll.removeUser("Makedonija") != true) {
      return false;
    }

    controll.setCurrentUser("admin");

    try {

      controll.addUser("rokiroki");

      return false;

    } catch (IllegalArgumentException e) {

    } catch (Exception e) {

      return false;
    }


    try {
      controll.addUser("bye");

      return false;

    } catch (IllegalArgumentException e) {

    } catch (Exception e) {

      return false;
    }


    try {
      controll.removeUser("qwepriuqpwoeiru");

      return false;

    } catch (NoSuchElementException e) {

    } catch (Exception e) {

      return false;
    }

    return true;
  }
}
