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
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.zip.DataFormatException;
import java.util.Scanner;


/**
 * This class represents the functionalities of a shopping cart.
 */
public class ExceptionalShoppingCart {

  // Define final parameters (constants)
  private static final double TAX_RATE = 0.05; // sales tax

  // a perfect-size two-dimensional array that stores the list of available items in a given market
  // MarketItems[i][0] refers to a String representation of the item key (unique identifier)
  // MarketItems[i][1] refers the item name
  // MarketItems[i][2] a String representation of the unit price of the item in dollars
  private static String[][] marketItems =
      new String[][] {{"4390", "Apple", "$1.59"}, {"4046", "Avocado", "$0.59"},
          {"4011", "Banana", "$0.49"}, {"4500", "Beef", "$3.79"}, {"4033", "Blueberry", "$6.89"},
          {"4129", "Broccoli", "$1.79"}, {"4131", "Butter", "$4.59"}, {"4017", "Carrot", "$1.19"},
          {"3240", "Cereal", "$3.69"}, {"3560", "Cheese", "$3.49"}, {"3294", "Chicken", "$5.09"},
          {"4071", "Chocolate", "$3.19"}, {"4363", "Cookie", "$9.5"}, {"4232", "Cucumber", "$0.79"},
          {"3033", "Eggs", "$3.09"}, {"4770", "Grape", "$2.29"}, {"3553", "Ice Cream", "$5.39"},
          {"3117", "Milk", "$2.09"}, {"3437", "Mushroom", "$1.79"}, {"4663", "Onion", "$0.79"},
          {"4030", "Pepper", "$1.99"}, {"3890", "Pizza", "$11.5"}, {"4139", "Potato", "$0.69"},
          {"3044", "Spinach", "$3.09"}, {"4688", "Tomato", "$1.79"}, null, null, null, null};

  /**
   * Creates a deep copy of the market catalog
   * 
   * @return Returns a deep copy of the market catalog 2D array of strings
   */
  public static String[][] getCopyOfMarketItems() {
    String[][] copy = new String[marketItems.length][];
    for (int i = 0; i < marketItems.length; i++) {
      if (marketItems[i] != null) {
        copy[i] = new String[marketItems[i].length];
        for (int j = 0; j < marketItems[i].length; j++)
          copy[i][j] = marketItems[i][j];
      }
    }
    return copy;
  }

  /**
   * Returns a string representation of the item whose name is provided as input
   * 
   * @param name name of the item to find
   * @return "itemId name itemPrice" if an item with the provided name was found
   * @throws NoSuchElementException with descriptive error message if no match found for given name
   */
  public static String lookupProductByName(String name) throws NoSuchElementException {

    String s = "No match found";
    for (int i = 0; i < marketItems.length; i++) {
      if (marketItems[i] != null && name.equals(marketItems[i][1])) {
        return marketItems[i][0] + " " + marketItems[i][1] + " " + marketItems[i][2];
      }
    }

    // throws NoSuchElementException with descriptive error message if no match found

    throw new NoSuchElementException("ERROR: " + s);

  }


  /**
   * Returns a string representation of the item whose id is provided as input
   * 
   * @param key id of the item to find
   * @return "itemId name itemPrice" if an item with the provided name was found
   * @throws NoSuchElementException   with descriptive error message if no match found for given
   *                                  key.
   * @throws IllegalArgumentException with descriptive error message if key is not in the right
   *                                  format.
   */
  public static String lookupProductById(int key)
      throws IllegalArgumentException, NoSuchElementException {

    if (key < 1000 || key > 9999) {
      // throws IllegalArgumentException with descriptive error message if key is not a 4-digits
      // int.

      throw new IllegalArgumentException("ERROR: The key is not 4 digits");

    }

    String s = "No match found";
    for (int i = 0; i < marketItems.length; i++) {
      if (marketItems[i] != null) {
        if (marketItems[i][0].equals(String.valueOf(key)))
          return marketItems[i][0] + " " + marketItems[i][1] + " " + marketItems[i][2];
      }
    }

    // throws NoSuchElementException with descriptive error message if no match found

    throw new NoSuchElementException("ERROR: " + s);

  }

  /**
   * Returns the index of the first null position that can be used to add new market items returns
   * the length of MarketItems if no available null position is found
   * 
   * @return returns an available position to add new market items or the length of market items if
   *         no available positions are found
   */
  private static int indexOfInsertionPos() {
    for (int i = 0; i < marketItems.length; i++) {
      if (marketItems[i] == null)
        return i;
    }
    return marketItems.length;
  }

  /**
   * add a new item to market items array, expand the capacity of marketitems if it is full when
   * trying to add new item, use indexofInsertionPos() to find the position to add
   * 
   * @param id    id of the item to add
   * @param name  name of the item to add
   * @param price price of the item to add
   * @throws IllegalArgumentException with descriptive error message if id or price is not in right
   *                                  format, and if name is empty or null.
   */
  public static void addItemToMarketCatalog(String id, String name, String price)
      throws IllegalArgumentException {
    // throws IllegalArgumentException with descriptive error message if id is not parsable to
    // 4-digits
    // int, if name is null or if empty string, and also price not parsable to double
    double temp;
    int tempId;
    try {
      // check if ID is parsable to an int.
      tempId = Integer.parseInt(id);
    } catch (Exception e) {
      throw new IllegalArgumentException("ERROR: ID is not parsable to an int");
    }

    if (tempId < 1000 || tempId > 9999) {
      // throws IllegalArgumentException with descriptive error message if key is not a 4-digits
      // int.

      throw new IllegalArgumentException("ERROR: The key is not 4 digits");

    }


    try {

      temp = Double.parseDouble(price.substring(1));

    } catch (Exception e) {

      // throws exception if price is not parsable to double.

      throw new IllegalArgumentException("ERROR: price is not parsable to a double");
    }

    if (temp < 0) {

      // throws exception if price is negative.

      throw new IllegalArgumentException("ERROR: The price is lower than 0");
    }

    if (price.charAt(0) != '$') {

      // throws exception if price does not start with '$'

      throw new IllegalArgumentException("ERROR: The price does not start with '$'");

    }


    if (name == null || name.isEmpty()) {
      // throws IllegalArgumentException if name is empty or null.

      throw new IllegalArgumentException("ERROR: The String is null or empty.");

    }



    int next = indexOfInsertionPos();
    if (next == marketItems.length) {
      System.out.println("The catalog is Full ! No further items can be added!");
    } else {
      marketItems[next] = new String[] {id, name, price};
    }
  }

  /**
   * Returns the price in dollars (a double value) of a market item given its name. If no match was
   * found in the market catalog, this method returns -1.0
   * 
   * @param name name of the item to get the price
   * @return the price of the item
   * @throws NoSuchElementException with descriptive error message if price cannot be found
   */
  public static double getProductPrice(String name) throws NoSuchElementException {
    double price = -1.0;
    for (int i = 0; i < marketItems.length; i++) {
      if (marketItems[i] != null && name.equals(marketItems[i][1])) {
        return Double.valueOf(marketItems[i][2].substring(1));
      }
    }

    // throws NoSuchElementException with descriptive error message if price not found

    throw new NoSuchElementException(
        "ERROR: The given product is not found in the market catalog.");
  }

  /**
   * Appends an item to a given cart (appends means adding to the end). If the cart is already full
   * (meaning its size equals its length), IllegalStateException wil be thrown.
   * 
   * @param item the name of the product to be added to the cart
   * @param cart an array of strings which contains the names of items in the cart
   * @param size the number of items in the cart
   * @return the size of the oversize array cart after trying to add item to the cart.
   * @throws IllegalArgumentException with descriptive error message if size is less than 0.
   * @throws IllegalStateException    with descriptive error message if the cart is full.
   */
  public static int addItemToCart(String item, String[] cart, int size)
      throws IllegalArgumentException, IllegalStateException {
    // throws IllegalArgumentException with descriptive error message if size is less than zero
    if (size < 0) {

      throw new IllegalArgumentException("ERROR: Size of the cart is less than 0.");

    }
    // throws IllegalStateException with descriptive error message if this cart is full
    if (size == cart.length) {

      throw new IllegalStateException("ERROR: Cart is full, no more items can be added.");

    }
    cart[size] = item;

    size++;

    return size;
  }

  /**
   * Returns the number of occurrences of a given item within a cart. This method must not make any
   * changes to the contents of the cart.
   * 
   * @param item the name of the item to search
   * @param cart an array of strings which contains the names of items in the cart
   * @param size the number of items in the cart
   * @return the number of occurrences of item (exact match) within the oversize array cart. Zero or
   *         more occurrences of item can be present in the cart.
   * @throws IllegalArgumentException with descriptive error message if size is less than 0.
   */
  public static int nbOccurrences(String item, String[] cart, int size)
      throws IllegalArgumentException {
    // throws IllegalArgumentException with descriptive error message if size is less than zero
    if (size < 0) {

      throw new IllegalArgumentException("ERROR: Size of the cart is less than 0.");

    }

    int count = 0;

    for (int i = 0; i < size; i++) {

      if (cart[i].equals(item)) {

        count++;
      }
    }
    return count;
  }

  /**
   * Checks whether a cart contains at least one occurrence of a given item. This method must not
   * make any changes to the contents of the cart.
   * 
   * @param item the name of the item to search
   * @param cart an array of strings which contains the names of items in the cart
   * @param size the number of items in the cart
   * @return Returns true if there is a match (exact match) of item within the provided cart, and
   *         false otherwise.
   * @throws IllegalArgumentException with descriptive error message if the size is less than 0.
   */
  public static boolean contains(String item, String[] cart, int size)
      throws IllegalArgumentException {
    // throws IllegalArgumentException with descriptive error message if size is less than zero
    if (size < 0) {

      throw new IllegalArgumentException("ERROR: Size of the cart is less than 0.");
    }

    for (int i = 0; i < size; i++) {
      if (cart[i].equals(item)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Removes one occurrence of item from a given cart.
   * 
   * @param item the name of the item to remove
   * @param cart an array of strings which contains the names of items in the cart
   * @param size the number of items in the cart
   * @return Returns the size of the oversize array cart after trying to remove item from the cart.
   * @throws IllegalArgumentException with descriptive error message if the size is less than zero.
   * @throws NoSuchElementException   descriptive error message if cannot be found in the cart.
   */
  public static int removeItem(String[] cart, String item, int size)

      throws IllegalArgumentException, NoSuchElementException {
    // throws IllegalArgumentException with descriptive error message if size is less than zero

    if (size < 0) {

      throw new IllegalArgumentException("ERROR: Size of the cart is less than 0.");
    }

    for (int i = 0; i < size; i++) {

      if (cart[i].equals(item)) {

        cart[i] = cart[size - 1];

        cart[size - 1] = null;

        return size - 1;
      }
    }
    // throws NoSuchElementException with descriptive error message if item not found in the cart

    throw new NoSuchElementException("ERROR: Item was not found in the cart.");

  }


  /**
   * Removes all items from a given cart. The array cart must be empty (contains only null
   * references) after this method returns.
   * 
   * @param cart an array of strings which contains the names of items in the cart
   * @param size the number of items in the cart
   * @return Returns the size of the cart after removing all its items.
   * @throws IllegalArgumentException with descriptive error message if the size of the cart is less
   *                                  than zero.
   * @throws NullPointerException     descriptive error message if the cart is null.
   */
  public static int emptyCart(String[] cart, int size)

      throws IllegalArgumentException, NullPointerException {
    // throws IllegalArgumentException with descriptive error message if size is less than zero
    if (size < 0) {

      throw new IllegalArgumentException("ERROR: Size of the cart is less than 0.");

    }

    // throws NullPointerException with descriptive error message if cart is null
    if (cart == null) {

      throw new NullPointerException("ERROR: The cart is null.");

    }

    for (int i = 0; i < cart.length; i++) {
      cart[i] = null;
    }

    return 0;
  }


  /**
   * This method returns the total value in dollars of the cart. All products in the market are
   * taxable (subject to TAX_RATE).
   * 
   * @param cart an array of strings which contains the names of items in the cart
   * @param size the number of items in the cart
   * @return Returns the total value in dollars of the cart accounting taxes.
   * @throws IllegalArgumentException with descriptive error message if the size of the cart is less
   *                                  than zero.
   */
  public static double checkout(String[] cart, int size) throws IllegalArgumentException {
    // throws IllegalArgumentException with descriptive error message if size is less than zero
    if (size < 0) {

      throw new IllegalArgumentException("ERROR: Size of the cart is less than 0.");

    }
    double total = 0.0;

    for (int i = 0; i < size; i++) {

      total += getProductPrice(cart[i]) * TAX_RATE;

    }
    return total;
  }

  /**
   * Returns a string representation of the summary of the contents of a given cart. The format of
   * the returned string contains a set of lines where each line contains the number of occurrences
   * of a given item, between spaces and parentheses, followed by one space followed by the name of
   * a unique item in the cart. ( #occurrences ) name1 ( #occurrences ) name2 etc.
   * 
   * @param cart an array of strings which contains the names of items in the cart
   * @param size the number of items in the cart
   * @return Returns a string representation of the summary of the contents of the cart
   * @throws IllegalArgumentException with descriptive error message if the size of the cart is less
   *                                  than zero.
   */
  public static String getCartSummary(String[] cart, int size) throws IllegalArgumentException {
    // throws IllegalArgumentException with descriptive error message if size is less than zero
    if (size < 0) {

      throw new IllegalArgumentException("ERROR: Size of the cart is less than 0.");

    }
    String s = "";

    for (int i = 0; i < size; i++) {

      if (!contains(cart[i], cart, i)) {

        s = s + "( " + nbOccurrences(cart[i], cart, size) + " ) " + cart[i] + "\n";

      }
    }
    return s.trim();
  }


  /**
   * Save the cart summary to a file.
   * 
   * @param cart an array of strings which contains the names of items in the cart
   * @param size the number of items in the cart
   * @param file the file to save the cart summary
   * @throws IllegalArgumentException with descriptive error message if the size of the cart is less
   *                                  than 0.
   */
  public static void saveCartSummary(String[] cart, int size, File file)

      throws IllegalArgumentException {

    // throws IllegalArgumentException with descriptive error message if size is less than zero
    if (size < 0) {

      throw new IllegalArgumentException("ERROR: Size of the cart is less than 0.");

    }

    PrintWriter writer = null;
    try {
      writer = new PrintWriter(file); // sets printwriter to write to file.
      writer.write(getCartSummary(cart, size)); // gets cart summary from method and writes to file

    } catch (IOException e) {

      e.printStackTrace(); // catches exception if file not found and IOException was thrown.

    } finally {

      writer.close();

    }
    // Use finally block to close any resource used to write the cart summary into file

  }


  /**
   * Parse one line of cart summary and add nbOccurrences of item to cart correct formatting for
   * line:"( " + nbOccurrences + " ) " + itemName delimiter: one space (multiple spaces: wrong
   * formatting)
   * 
   * @param line a line of the cart summary to be parsed into one item to be added
   * @param cart an array of strings which contains the names of items in the cart
   * @param size the number of items in the cart
   * @return Returns the size of the cart after adding items to the cart.
   * @throws DataFormatException      with descriptive error message if wrong formatting (including
   *                                  nbOccurrences not parsable to a positive integer less or equal
   *                                  to 10)
   * @throws IllegalArgumentException with descriptive error message if itemName not found in
   *                                  marketItems
   * @throws IllegalStateException    with descriptive error message if cart reaches its capacity.
   */
  protected static int parseCartSummaryLine(String line, String[] cart, int size)
      throws DataFormatException, IllegalArgumentException, IllegalStateException {

    Scanner scnr = new Scanner(line);

    String item;

    String temp;

    int index;

    int quantity = 0;

    try {

      line = line.trim(); // gets rid of leading and ending whitespace

      index = line.indexOf(')'); // finds index of closing bracket.

      temp = line.substring(1, index);

      temp = temp.trim();
      quantity = Integer.parseInt(temp);// store number in temp string, remove whitespace and parse.

      if (quantity > 10) {

        // throws exception if quantity greater than 10.

        throw new DataFormatException(

            "ERROR: Not parsable to a positive int smaller or equal to ten");
      }
      item = line.substring(index + 1, line.length());

      item = item.trim();

      if (item.isEmpty()) {

        // throws DataFormatException with descriptive error message if wrong formatting (including

        throw new DataFormatException("ERROR: The inputted line has wrong formatting.");
      }
    } catch (Exception e) {

      throw new DataFormatException("ERROR: The inputted line has wrong formatting.");

    }

    try {

      lookupProductByName(item);

    } catch (Exception e) {

      // throws IllegalArgumentException with descriptive error message if itemName not found in
      // marketItems

      throw new IllegalArgumentException("ERROR: The item cannot be found in the market catalog.");
    }


    for (int i = 0; i < quantity; ++i) {

      if (size == cart.length) {

        // throws IllegalStateException with descriptive error message if cart reaches its capacity.
        throw new IllegalStateException("ERROR: The cart has reached its maximum capacity. ");
      }
      cart[size] = item;
      ++size;
    }

    return size;
  }

  /**
   * Load the cart summary from the file. For each line of summary, add nbOccurrences of item to
   * cart. Must call parseCartSummaryLine to operate
   * 
   * @param file file to load the cart summary from
   * @param cart an array of strings which contains the names of items in the cart
   * @param size the number of items in the cart
   * @return Returns the size of the cart after adding items to the cart
   */
  public static int loadCartSummary(File file, String[] cart, int size)

      throws IllegalArgumentException, IllegalStateException {

    // This method MUST call parseCartSummaryLine to operate (to parse each line in file)

    if (size < 0) {

      // if size is less than 0, throws illegalargumentexception.

      throw new IllegalArgumentException("ERROR: Size of the cart is less than 0");
    }

    String line;

    Scanner scnr = null;

    try {

      scnr = new Scanner(file); // declares scanner to read from file

      while (scnr.hasNextLine()) {// if scanner has next line executes following code.

        if (size == cart.length) {
          // throws exception if cart is full.

          throw new IllegalStateException("ERROR: The cart is full.");
        }

        line = scnr.nextLine();

        try {
          size = parseCartSummaryLine(line, cart, size);

        } catch (Exception e) {
          // skips line if exception is thrown by parseCartSummaryLine().

          continue;

        }
      }
    } catch (FileNotFoundException e) {

      // prints stack trace if file not found

      e.printStackTrace();

      return size;

    } finally {

      if (scnr != null) {

        scnr.close(); // closes scanner.

      }
    }

    return size; // default return statement added to avoid compiler errors
  }
}
