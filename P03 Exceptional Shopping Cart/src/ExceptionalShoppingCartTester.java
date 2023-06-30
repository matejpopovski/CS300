//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P03 Exceptional Shopping Cart
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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This is a class that tests the exceptional shopping chart
 */
public class ExceptionalShoppingCartTester {

  /**
   * Main method is a method that calls only 1 method, which is runAllTests(), and also returns
   * boolean
   * 
   * @param args input arguments if there are any
   */
  public static void main(String[] args) {

    System.out.print(runAllTests());

  }

  /**
   * This is just a method that calls all other test methods within this class. It checks if all the
   * tests have passed and returns boolean.
   * 
   * @return true if all tests pass, else false.
   */
  public static boolean runAllTests() {

    if (testLookupMethods() && testSaveCartSummary() && testLoadCartSummary()
        && testAddItemToMarketCatalog()) {

      return true;

    }

    return false;
  }

  /**
   * The tests for the methods lookupProductByName() and lookupProductById() which are used with
   * different arguments and checked if the proper exceptions are thrown by the method. This
   * method's first tester is for the normal case, while others are for specific exceptions
   * 
   * @return true if all tests pass, otherwise false.
   */
  public static boolean testLookupMethods() {

    String name = "Pizza";

    try {
      ExceptionalShoppingCart.lookupProductByName(name);

    } catch (NoSuchElementException e) {

      return false;

    } catch (Exception e) {

      return false;
    }

    name = "Matej";

    try {

      ExceptionalShoppingCart.lookupProductByName(name);

      return false;

    } catch (NoSuchElementException e) {

    } catch (Exception e) {

      return false;
    }

    int id = 4688;

    try {
      ExceptionalShoppingCart.lookupProductById(id);

    } catch (NoSuchElementException e) {

      return false;

    } catch (Exception e) {

      return false;
    }

    id = 0007;

    try {
      ExceptionalShoppingCart.lookupProductById(id);

      return false;

    } catch (IllegalArgumentException e) {

    } catch (NoSuchElementException e) {

      return false;

    } catch (Exception e) {

      return false;
    }

    id = 9797;

    try {
      ExceptionalShoppingCart.lookupProductById(id);

      return false;

    } catch (IllegalArgumentException e) {

      return false;

    } catch (NoSuchElementException e) {

    } catch (Exception e) {

      return false;
    }

    return true;
  }

  /**
   * Tests the method addItemToMarketCatalog() and checks if the proper exceptions are thrown by the
   * method. This method's first tester is for the normal case, while others are for specific
   * exceptions
   * 
   * @return true if all tests pass, else false.
   */
  public static boolean testAddItemToMarketCatalog() {

    String id = "4033";
    String name = "Blueberry";
    String price = "$6.89";

    try {

      ExceptionalShoppingCart.addItemToMarketCatalog(id, name, price);

    } catch (IllegalArgumentException e) {

      return false;

    } catch (Exception e) {

      return false;
    }


    id = "qwer";
    name = "Blueberry";
    price = "$6.89";

    try {
      ExceptionalShoppingCart.addItemToMarketCatalog(id, name, price);

      return false;

    } catch (IllegalArgumentException e) {

    } catch (Exception e) {

      return false;
    }

    id = "79797";
    name = "Blueberry";
    price = "$6.89";

    try {
      ExceptionalShoppingCart.addItemToMarketCatalog(id, name, price);

      return false;

    } catch (IllegalArgumentException e) {

    } catch (Exception e) {

      return false;
    }

    id = "4033";
    name = "Blueberry";
    price = "$-6.89";

    try {
      ExceptionalShoppingCart.addItemToMarketCatalog(id, name, price);

      return false;

    } catch (IllegalArgumentException e) {

    } catch (Exception e) {

      return false;
    }

    id = "4033";
    name = "Blueberry";

    try {

      ExceptionalShoppingCart.addItemToMarketCatalog(id, name, price);

      return false;

    } catch (IllegalArgumentException e) {

    } catch (Exception e) {

      return false;
    }

    id = "4033";
    name = "";
    price = "$6.89";

    try {

      ExceptionalShoppingCart.addItemToMarketCatalog(id, name, price);

      return false;

    } catch (IllegalArgumentException e) {

    } catch (Exception e) {

      return false;
    }

    id = "4033";
    name = null;
    price = "$6.89";

    try {

      ExceptionalShoppingCart.addItemToMarketCatalog(id, name, price);

      return false;

    } catch (IllegalArgumentException e) {

    } catch (Exception e) {

      return false;
    }


    return true;
  }

  /**
   * This is a test for saveCartSummary() method, that checks if the proper exceptions are being
   * thrown by the method. This method's first tester is for the normal case, while others are for
   * specific exceptions
   * 
   * @return true if all tests pass, else false.
   */
  public static boolean testSaveCartSummary() {

    File file = new File("filename.txt");

    int size = 8;

    String[] cart = new String[] {"Apple", "Blueberry", "Apple", "Blueberry", "Banana", "Blueberry",
        "Banana", "Cheese", null, null};

    try {

      ExceptionalShoppingCart.saveCartSummary(cart, size, file);

    } catch (Exception e) {


      return false;
    }


    file = new File("filename1.txt");
    size = -1;
    cart = new String[] {null, null, null, null, null, null, null, null, null, null};

    try {

      ExceptionalShoppingCart.saveCartSummary(cart, size, file);


      return false;

    } catch (IllegalArgumentException e) {

    } catch (Exception e) {

      return false;
    }


    return true;
  }

  /**
   * Tests the method loadCartSummary() and checks if the proper exceptions are being thrown by the
   * method. This method's first tester is for the normal case, while others are for specific
   * exceptions
   * 
   * @return true if all tests pass, else false.
   */
  public static boolean testLoadCartSummary() {

    File file = new File("loader.txt");

    try (PrintWriter printWriter = new PrintWriter(file)) {

      printWriter.print("( 2 ) Cheese\r\n" + "( 1 ) Blueberry\r\n" + "( 1 )\r\n"
          + "( one ) Cookie\r\n" + "( 3 ) Pizza\r\n" + "( 1 ) Onion\r\n" + "( 1 ) Grape");

    } catch (FileNotFoundException e) {

      return false;
    }

    int size = 0;
    String[] cart = new String[] {null, null, null, null, null, null, null, null, null, null};

    try {

      size = ExceptionalShoppingCart.loadCartSummary(file, cart, size);

    } catch (IllegalArgumentException e) {

      return false;

    } catch (IllegalStateException e) {

      return false;

    } catch (Exception e) {

      return false;
    }

    size = -1;
    cart = new String[] {null, null, null, null, null, null, null, null, null, null};

    try {

      size = ExceptionalShoppingCart.loadCartSummary(file, cart, size);

      return false;

    } catch (IllegalArgumentException e) {

    } catch (IllegalStateException e) {

      return false;

    } catch (Exception e) {

      return false;
    }


    size = 10;
    cart = new String[] {null, null, null, null, null, null, null, null, null, null};

    try {

      size = ExceptionalShoppingCart.loadCartSummary(file, cart, size);

      return false;

    } catch (IllegalArgumentException e) {

      return false;

    } catch (IllegalStateException e) {


    } catch (Exception e) {

      return false;
    }

    return true;
  }
}
