//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P01 Shopping Cart
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
 * This contains testing methods for the ShoppingCart class.
 * 
 * @author
 * @author //TODO add your name here when you contribute.
 */
public class ShoppingCart {

  private static final double TAX_RATE = 0.05; // sales tax

  // MarketItems[i][0] refers to a String representation of the item identifiers
  // MarketItems[i][1] refers the item name. Item names are also unique
  // MarketItems[i][2] a String representation of the unit price
  // of the item in dollars
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
   * This method returns a string representation of the item we search for, whose name is provided
   * as input if a match was found.
   *
   * @param name The name of the item to check
   * @return A string representation of the item to search including the details about it located +1
   *         and -1 index of the item, if not found return "No match found"
   *
   */
  public static String lookupProductByName(String name) {

    String match = "No match found";

    if (name == null) {
      return match;
    }

    for (int i = 0; i < marketItems.length; i++) {
      if (marketItems[i] != null) {
        if (marketItems[i][1].equals(name)) {
          match = marketItems[i][0] + " " + marketItems[i][1] + " " + marketItems[i][2];
        }
      }
    }

    return match;
  }

  /**
   * This method returns a string representation of the item whose id is provided as input if a
   * match was found.
   *
   * @param id The identifier of the product or item to search
   * @return A string representation of the item to search including the details about it located +1
   *         and -1 index of the item, if not found return "No match found"
   */
  public static String lookupProductById(int id) {

    String match = "No match found";

    if (id < 0) {
      return match;
    }

    for (int i = 0; i < marketItems.length; i++) {
      if (marketItems[i] != null) {
        if (marketItems[i][0].equals(String.valueOf(id))) {
          match = marketItems[i][0] + " " + marketItems[i][1] + " " + marketItems[i][2];
        }
      }
    }

    return match;
  }

  /**
   * This method searches for an match item and returns the item whose index is +1 of the given item
   * and converts it from string to double. If no match was found in the array, this method returns
   * -1.0
   *
   * @param name The name of the item to search
   * @return a double containing the item with following index, else -1.0
   */
  public static double getProductPrice(String name) {

    double price = -1.0;

    if (name == null) {
      return price;
    }

    for (int i = 0; i < marketItems.length; i++) {
      if (marketItems[i] != null) {
        if (marketItems[i][1].equals(name)) {
          price = Double.valueOf(marketItems[i][2].substring(1));
        }
      }
    }

    return price;
  }

  /**
   * This method returns a deep copy of an input array
   *
   * @return a String[][] contrianing the deep copy of array
   */
  // public static String[][] getCopyOfMarketItems(){

  // String[][] marketItemsCopy
  // = new String[marketItems.length][marketItems[0].length];

  // for(int i = 0; i < marketItems.length; i++){
  // if(marketItems[i] != null){
  // marketItemsCopy[i][0] = marketItems[i][0];
  // marketItemsCopy[i][1] = marketItems[i][1];
  // marketItemsCopy[i][2] = marketItems[i][2];
  // }else{
  // marketItemsCopy[i][0] = null;
  // marketItemsCopy[i][1] = null;
  // marketItemsCopy[i][2] = null;
  // }
  // }
  // return marketItemsCopy;
  // }


  /**
   * This method returns a deep copy of an input array
   *
   * @return a String[][] contrianing the deep copy of array
   */
  public static String[][] getCopyOfMarketItems() {

    String[][] marketItemsCopy = new String[marketItems.length][];


    for (int i = 0; i < marketItems.length; i++) {
      if (marketItems[i] == null) {
        marketItemsCopy[i] = null;
        continue;
      }
      for (int j = 0; j < marketItems[i].length; j++) {
        marketItemsCopy[i] = marketItems[i];
      }
    }


    return marketItemsCopy;
  }



  /**
   * This method appends an element to a oversized array (adds to the end). If the array is already
   * full (meaning its size equals its length), the element will not be appended to the array.
   *
   * @param item The element to be added to the array
   * @param cart An array of strings which contains all the elements
   * @param size The size of the oversized array
   * @return the size of the oversize array after trying to add element to it, and returns the same
   *         of size if no change to the contents of the array has been made if it is full.
   */
  public static int addItemToCart(String item, String[] cart, int size) {

    if (size == cart.length) {
      return size;
    }

    cart[size] = item;
    size++;

    return size;
  }


  /**
   * This method returns the number of occurences of a given element within an array. This method
   * does not make any changed to the content of the array
   * 
   * @param item The name of the elements to search
   * @param cart An array of strings which contains the names of elements
   * @param size The number of elements in the array
   * @return the number of occurrences of item (exact match) within the oversize array. Zero or more
   *         occurrences of the element can be present in the array.
   *
   */
  public static int nbOccurrences(String item, String[] cart, int size) {

    int counter = 0;

    if (item == null || size == 0) {
      return counter;
    }


    for (int i = 0; i < size; i++) {
      if (cart[i] != null) {
        if (cart[i].equals(item)) {
          counter++;
        }
      }
    }

    return counter;
  }


  /**
   * This methods checks whether an array contains at least one occurrence of a given item. This
   * method must not make any changes to the contents of the array.
   * 
   * @param item The name of the element to search
   * @param cart An array of strings which contains the names of elements
   * @param size The number of elements in the cart
   * @return true if there is a match (exact match) of element within the array and false otherwise.
   */
  public static boolean contains(String item, String[] cart, int size) {

    boolean containsItem = false;

    if (item == null || size == 0) {
      return containsItem;
    }

    for (int i = 0; i < size; i++) {
      if (cart[i] != null) {
        if (cart[i].equals(item)) {
          containsItem = true;
          break;
        }
      }
    }

    return containsItem;
  }


  /**
   * This method returns the total value in dollars of the array (cart). All products in the market
   * are taxable (subject to TAX_RATE).
   * 
   * @param cart An array of strings which contains the names of elements
   * @param size The number of items in the cart
   * @return The total value in dollars of the cart accounting taxes.
   */
  public static double checkout(String[] cart, int size) {

    double total = 0.0;

    if (size == 0) {
      return total;
    }

    for (int i = 0; i < size; i++) {

      total = total + getProductPrice(cart[i]) * (1 + TAX_RATE);
    }

    return total;
  }


  /**
   * This is a helper method that searches for an element in an array and returns the index of its
   * first occurence in the array, returns -1 if the item is not found;
   * 
   * @param item The name of the element to remove
   * @param cart An array of strings which contains the names of elements
   * @param size The number of elements in the cart
   * @return The index of the first occurence of an element in an oversize array
   *
   */
  private static int findIndexOfFirstOccurence(String[] cart, String item, int size) {

    for (int i = 0; i < size; i++) {
      if (cart[i] == item) {
        return i;
      }
    }

    return -1;
  }


  /**
   * This method removes one occurrence of item from a given array. If no match with item was found
   * in the array, the method returns the same value of input size, without making any change to the
   * contents of the array.
   * 
   * @param item The name of the element to remove
   * @param cart An array of strings which contains the names of elements
   * @param size The number of elements in the cart
   * @return the size of the oversize array after trying to remove item it.
   */
  public static int removeItem(String[] cart, String item, int size) {

    int indexOfFirstOccurance = -1;

    if (item == null || size == 0) {
      return size;
    }

    if (!contains(item, cart, size)) {
      return size;
    }

    if (nbOccurrences(item, cart, size) >= 1) {

      indexOfFirstOccurance = findIndexOfFirstOccurence(cart, item, size);

      for (int i = indexOfFirstOccurance; i < size - 1; i++) {
        cart[i] = cart[i + 1];
      }

      cart[size - 1] = null;

      size--;
    }

    return size;
  }


  /**
   * This method removes all elements from a given array. The array must be empty (contains only
   * null references) after this method returns.
   * 
   * @param cart An array of strings which contains the names of elements
   * @param size The number of elements in the array
   * @return The size of the cart after removing all its elements
   */
  public static int emptyCart(String[] cart, int size) {

    for (int i = 0; i <= size; i++) {
      cart[i] = null;
    }

    size = 0;

    return size;
  }


  /**
   * This method returns a string representation of the summary of the contents of a given array.
   * This method does not change the content of the array The format of the returned string contains
   * a set of lines where each line contains the number of occurrences of a given item, between
   * parentheses, followed by one space followed by the name of a unique item in the array.
   * (#occurrences) name1 (#occurrences) name2 etc.
   * 
   * @param cart An array of strings which contains the names of elements
   * @param size The number of elements in the array
   * @return a string representation of the summary of the contents of the cart
   *
   */
  public static String getCartSummary(String[] cart, int size) {

    if (size == 0 || cart == null) {
      return "";
    }

    String[] itemsInTheCard = new String[size];
    String[] occurancesOfItems = new String[size];
    String cartSummary = "";
    int newIndex = 0;

    for (int i = 0; i < size; i++) {
      if (cart[i] != null) {
        if (!contains(cart[i], itemsInTheCard, itemsInTheCard.length)) {
          itemsInTheCard[newIndex] = cart[i];
          occurancesOfItems[newIndex] = String.valueOf(nbOccurrences(cart[i], cart, size));
          newIndex++;
        }
      }
    }

    for (int i = 0; i < size; i++) {
      if (occurancesOfItems[i] != null) {
        cartSummary =
            cartSummary + "(" + occurancesOfItems[i] + ")" + " " + itemsInTheCard[i] + "\n";
      }
    }

    return cartSummary;
  }

}
