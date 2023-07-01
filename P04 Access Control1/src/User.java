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

/**
 * This class represents the functionalities of a user in accesscontrol
 */
public class User {

  private final String USERNAME; // The name of the user

  private String password; // The password of the user

  private boolean isAdmin; // Whether or not the user has Admin powers

  /**
   * creates a new user with the given password and admin status
   * 
   * Creates an instance of the user class with initialized fields with the given username, password
   * and admin status.
   *
   * @param username username id of the user
   * @param password password of the user
   * @param isAdmin  boolean for admin privileges
   */
  public User(String username, String password, boolean isAdmin) {
    USERNAME = username;
    this.password = password;
    this.isAdmin = isAdmin;
  }

  /**
   * Report whether the password is correct
   *
   * @param password password of the user used to login
   * @return true if password is correct, false otherwise
   */
  public boolean isValidLogin(String password) {
    if (password.equals(this.password)) {
      return true;
    }
    return false;
  }

  /**
   * Getter, which returns the name of the user
   *
   * @return USERNAME username of the given user instance
   */
  public String getUsername() {
    return USERNAME;
  }

  /**
   * Getter, which reports whether the user is an admin
   *
   * @return isAdmin boolean value representing admin privileges.
   */
  public boolean getIsAdmin() {
    return isAdmin;
  }


  /**
   * Setter method for a password
   *
   * @param password password that needs to be set
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Setter method for isAdmin
   *
   * @param isAdmin boolean value isAdmin will be set to
   */
  public void setIsAdmin(boolean isAdmin) {
    this.isAdmin = isAdmin;
  }


}
