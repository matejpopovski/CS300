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

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * This class represents the functionalities of a access control page
 */
public class AccessControl {

  private static ArrayList<User> users;

  private User currentUser;

  private static final String DEFAULT_PASSWORD = "changeme";

  /**
   * A no-argument constructor
   * 
   * Creates an instance of the access control and intializes arraylist users if null and current
   * user to null
   *
   */
  public AccessControl() {
    User admin = new User("admin", "root", true);

    if (users == null) {

      users = new ArrayList<User>();

      users.add(admin);
    }
    currentUser = null;
  }

  /**
   * Report whether a given username/password pair is a valid login
   *
   * @param password password connected to given user that need to be checked.
   * @param username username that needs to be checked within aray
   * @return true if username and connected password can be found in users arraylist, false
   *         otherwise
   */
  public static boolean isValidLogin(String username, String password) {

    if (username == null || password == null) {

      return false;
    }


    for (int i = 0; i < users.size(); ++i) {

      if (users.get(i).getUsername().equals(username) && users.get(i).isValidLogin(password)) {

        return true;
      }
    }
    return false;
  }

  /**
   * Log out the current user by changing current user to null
   *
   */
  public void logout() {
    currentUser = null;
  }

  /**
   * Change the current password of the current user
   *
   * @param newPassword string value to which the new password will be changed to
   */
  public void changePassword(String newPassword) {

    currentUser.setPassword(newPassword);
  }

  /**
   * Create a new user (non-admin)
   * 
   * @param username The username that will be set for the new instance of user
   * @throws IllegalArgumentException if username is null or its length is less than 5
   * @return true if user was successfully created otherwise false
   */
  public boolean addUser(String username) throws IllegalArgumentException {

    if (currentUser == null || currentUser.getIsAdmin() == false) {

      return false;
    }


    if (username == null || username.length() < 5) {

      throw new IllegalArgumentException("ERROR: The username is null or less than 5");
    }


    for (int i = 0; i < users.size(); ++i) {

      if (users.get(i).getUsername().equals(username)) {

        throw new IllegalArgumentException("ERROR: Taken username");

      }
    }

    User newUser = new User(username, DEFAULT_PASSWORD, false);

    users.add(newUser);

    return true;
  }

  /**
   * Create a new user and specify their admin status
   * 
   * @param username The username that will be set for the new instance of user
   * @param isAdmin  Determines if the given user has admin privileges
   * @throws IllegalArgumentException if username is null or length is less than 5
   * @return true if user was successfully added otherwise false
   */
  public boolean addUser(String username, boolean isAdmin) throws IllegalArgumentException {
    if (currentUser == null || currentUser.getIsAdmin() == false) {
      return false;
    }


    if (username == null || username.length() < 5) {

      throw new IllegalArgumentException("ERROR: The username is null or less than 5");
    }


    for (int i = 0; i < users.size(); ++i) {
      if (users.get(i).getUsername().equals(username)) {

        throw new IllegalArgumentException("ERROR: Taken username");

      }
    }

    User newUser = new User(username, DEFAULT_PASSWORD, isAdmin);

    users.add(newUser);

    return true;
  }

  /**
   * Remove a user Removes a user from the users arraylist
   * 
   * @param username The username of the user that will be removed from users arraylist
   * @throws NoSuchElementException if username does not exist
   * @return true if user was successfully removed otherwise false
   */
  public boolean removeUser(String username) throws NoSuchElementException {
    if (currentUser == null || currentUser.getIsAdmin() == false) {
      return false;

    }

    for (int i = 0; i < users.size(); ++i) {

      if (users.get(i).getUsername().equals(username)) {

        users.remove(i);

        return true;

      }
    }

    throw new NoSuchElementException("Error: User doesnt exist");
  }

  /**
   * A mutator you can use to write tests without simulating user input
   * 
   * @param username username of the user that needs to be set to current user
   */
  public void setCurrentUser(String username) {

    for (int i = 0; i < users.size(); ++i) {

      if (users.get(i).getUsername().equals(username)) {

        currentUser = users.get(i);
      }
    }
  }


  /**
   * Give a user admin privileges
   * 
   * @param username The username of the user that will be given admin privileges
   * @throws NoSuchElementException if username does not exist
   * @return true if user was successfully given admin privileges otherise false
   */
  public boolean giveAdmin(String username) throws NoSuchElementException {
    if (currentUser == null || currentUser.getIsAdmin() == false) {
      return false;
    }


    for (int i = 0; i < users.size(); ++i) {

      if (users.get(i).getUsername().equals(username)) {

        users.get(i).setIsAdmin(true);
        return true;

      }
    }

    throw new NoSuchElementException("Error: User doesnt exist");

  }

  /**
   * Takes a user's admin privileges.
   * 
   * @param username The username of the user that will be whose admin privileges will be taken
   * @throws NoSuchElementException if the username does not exist
   * @return true if users admin privileges were successfully taken otherwise false
   */
  public boolean takeAdmin(String username) {
    if (currentUser == null || currentUser.getIsAdmin() == false) {
      return false;
    }

    for (int i = 0; i < users.size(); ++i) {

      if (users.get(i).getUsername().equals(username)) {
        users.get(i).setIsAdmin(false);
        return true;

      }
    }

    throw new NoSuchElementException("Error: User doesnt exist");
  }

  /**
   * Reset the password of a user
   * 
   * @param username The username of the user that will have its password reset
   * @throws NoSuchElementException if username does not exist
   * @return true if user's password was reset otherwise false
   */

  public boolean resetPassword(String username) throws NoSuchElementException {

    if (currentUser == null || currentUser.getIsAdmin() == false) {
      return false;
    }


    for (int i = 0; i < users.size(); ++i) {
      if (users.get(i).getUsername().equals(username)) {

        users.get(i).setPassword(DEFAULT_PASSWORD);;
        return true;
      }

    }

    throw new NoSuchElementException("Error: User doesnt exist");
  }


  public static void main(String[] args) {

  }

}
