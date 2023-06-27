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

import java.util.Arrays;

/**
 * This contains testing methods for the ShoppingCart class.
 * 
 * @author Matej Popovski
 */
public class ShoppingCartTester {

  /**
   * This main method runs the selected tests.
   *
   * @param args unused
   */
  public static void main(String[] args) {

    System.out.println("testLookupMethods(): " + testLookupMethods());
    System.out.println("testGetProductPrice(): " + testGetProductPrice());
    System.out.println(
        "testAddItemToCartContainsNbOccurrences(): " + testAddItemToCartContainsNbOccurrences());
    System.out.println("testRemoveItem(): " + testRemoveItem());
    System.out.println("testCheckoutGetCartSummary(): " + testCheckoutGetCartSummary());
    System.out.println("runAllTests(): " + runAllTests());
  }


  /**
   * Checks whether ShoppingCart.lookupProductByName() and ShoppingCart.lookupProductById() methods
   * work as expected. Returns true when this test verifies a correct functionality, and false
   * otherwise
   */
  public static boolean testLookupMethods() {


    /*
     * Test 1 - The item to find is at index 0 of the marketItems array
     */
    String expectedOutput = "4390 Apple $1.59";

    if (!ShoppingCart.lookupProductByName("Apple").equals(expectedOutput)) {
      System.out.println("Problem detected: Your lookupProductByName() method "
          + "failed to return the expected output when passed Apple as input");
      return false;
    }

    if (!ShoppingCart.lookupProductById(4390).equals(expectedOutput)) {
      System.out.println("Problem detected: Your lookupProductById() method "
          + "failed to return the expected output when passed the id " + "of Apple as input");
      return false;
    }



    /*
     * Test 2 - The item to find is at the last non-null position of the marketItems array
     */
    String lastNonNullPossitionId = "";
    String lastNonNullPossitionName = "";
    String lastNonNullPossitionPrice = "";

    String[][] marketItemsCopy = ShoppingCart.getCopyOfMarketItems();
    int length = marketItemsCopy.length;

    for (int i = marketItemsCopy.length - 1; i >= 0; i--) {
      if (marketItemsCopy[i] != null) {
        lastNonNullPossitionId = marketItemsCopy[i][0];
        lastNonNullPossitionName = marketItemsCopy[i][1];
        lastNonNullPossitionPrice = marketItemsCopy[i][2];

        break;
      }
    }

    expectedOutput =
        lastNonNullPossitionId + " " + lastNonNullPossitionName + " " + lastNonNullPossitionPrice;

    if (!ShoppingCart.lookupProductByName(lastNonNullPossitionName).equals(expectedOutput)) {
      System.out.println("Problem detected: Your lookupProductByName() method "
          + "failed to return the expected output when passed the last non-null position of the "
          + "array as input");
      return false;
    }

    if (!ShoppingCart.lookupProductById(Integer.valueOf(lastNonNullPossitionId))
        .equals(expectedOutput)) {
      System.out.println("Problem detected: Your lookupProductById() method "
          + "failed to return the expected output when passed the last non-null position of the "
          + "array as input");
      return false;
    }



    /*
     * Test 3 - The item to find is at an arbitrary position of the middle of the marketItems array
     */
    String randomIdFromTheMiddleArray = "";
    String randomNameFromTheMiddleArray = "";
    String randomPriceFromTheMiddleArray = "";

    int middleOfTheArray = marketItemsCopy.length / 2;

    if (marketItemsCopy[middleOfTheArray] != null) {
      randomIdFromTheMiddleArray = marketItemsCopy[middleOfTheArray][0];
      randomNameFromTheMiddleArray = marketItemsCopy[middleOfTheArray][1];
      randomPriceFromTheMiddleArray = marketItemsCopy[middleOfTheArray][2];
    } else {
      randomIdFromTheMiddleArray = marketItemsCopy[middleOfTheArray + 1][0];
      randomNameFromTheMiddleArray = marketItemsCopy[middleOfTheArray + 1][1];
      randomPriceFromTheMiddleArray = marketItemsCopy[middleOfTheArray + 1][2];
    }

    expectedOutput = randomIdFromTheMiddleArray + " " + randomNameFromTheMiddleArray + " "
        + randomPriceFromTheMiddleArray;

    if (!ShoppingCart.lookupProductByName(randomNameFromTheMiddleArray).equals(expectedOutput)) {
      System.out.println("Problem detected: Your lookupProductByName() method "
          + "failed to return the expected output when passed the middle index position of the "
          + "array as input");
      return false;
    }

    if (!ShoppingCart.lookupProductById(Integer.valueOf(randomIdFromTheMiddleArray))
        .equals(expectedOutput)) {
      System.out.println("Problem detected: Your lookupProductById() method "
          + "failed to return the expected output when passed the middle index position of the "
          + "array as input");
      return false;
    }


    /*
     * Test 4 - The item to find is not found in the market
     */
    expectedOutput = "No match found";
    if (!ShoppingCart.lookupProductByName("NOT FOUND").equals(expectedOutput)) {
      System.out.println("Problem detected: Your lookupProductByName() method "
          + "failed to return the expected output when passed the name of "
          + "a product not found in the market.");
      return false;
    }
    if (!ShoppingCart.lookupProductById(1000).equals(expectedOutput)) {
      System.out.println("Problem detected: Your lookupProductById() method "
          + "failed to return the expected output when passed the identifier"
          + "of a product not found in the market.");
      return false;
    }


    /*
     * Test 5 - The item to find is not found in the market due to passed undefined argument
     */
    expectedOutput = "No match found";
    if (!ShoppingCart.lookupProductByName(null).equals(expectedOutput)) {
      System.out.println("Problem detected: Your lookupProductByName() method "
          + "failed to return the expected output when passed the name of "
          + "a product not found in the market.");
      return false;
    }
    if (!ShoppingCart.lookupProductById(-1).equals(expectedOutput)) {
      System.out.println("Problem detected: Your lookupProductById() method "
          + "failed to return the expected output when passed the identifier"
          + "of a product not found in the market.");
      return false;
    }


    return true;
  }


  /**
   * Checks the correctness of ShoppingCart.getProductPrice() method
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testGetProductPrice() {

    /*
     * Test 1 - Get the price for the item at index 0 of the marketItems array
     */
    double expectedPrice = 1.59;

    if (Math.abs(ShoppingCart.getProductPrice("Apple") - expectedPrice) > 0.001) {
      return false;
    }


    /*
     * Test 2 - Get the price for the item at the last non-null position of the marketItems array
     */
    String lastNonNullPossitionName = "";
    String lastNonNullPossitionPrice = "";

    String[][] marketItemsCopy = ShoppingCart.getCopyOfMarketItems();
    int length = marketItemsCopy.length;

    for (int i = marketItemsCopy.length - 1; i >= 0; i--) {
      if (marketItemsCopy[i] != null) {
        lastNonNullPossitionName = marketItemsCopy[i][1];
        lastNonNullPossitionPrice = marketItemsCopy[i][2];
        break;
      }
    }

    expectedPrice = Double.valueOf(lastNonNullPossitionPrice.substring(1));

    if (Math.abs(ShoppingCart.getProductPrice(lastNonNullPossitionName) - expectedPrice) > 0.001) {
      return false;
    }


    /*
     * Test 3 - Get the price for the item at an arbitrary position of the middle of the marketItems
     * array
     */
    String randomNameFromTheMiddleArray = "";
    String randomPriceFromTheMiddleArray = "";

    int middleOfTheArray = marketItemsCopy.length / 2;

    if (marketItemsCopy[middleOfTheArray] != null) {
      randomNameFromTheMiddleArray = marketItemsCopy[middleOfTheArray][1];
      randomPriceFromTheMiddleArray = marketItemsCopy[middleOfTheArray][2];
    } else {
      randomNameFromTheMiddleArray = marketItemsCopy[middleOfTheArray + 1][1];
      randomPriceFromTheMiddleArray = marketItemsCopy[middleOfTheArray + 1][2];
    }

    expectedPrice = Double.valueOf(randomPriceFromTheMiddleArray.substring(1));

    if (Math
        .abs(ShoppingCart.getProductPrice(randomNameFromTheMiddleArray) - expectedPrice) > 0.001) {
      return false;
    }

    /*
     * Test 4 - Get the price for the item which could not be found in the market
     */
    expectedPrice = -1.0;
    if (Math.abs(ShoppingCart.getProductPrice("NOT FOUND") - expectedPrice) > 0.001) {
      return false;
    }

    /*
     * Test 5 - Get the price for the item which could not be found in the market due to passed
     * undefined argument
     */
    expectedPrice = -1.0;
    if (Math.abs(ShoppingCart.getProductPrice(null) - expectedPrice) > 0.001) {
      return false;
    }


    return true;
  }

  /**
   * This tester method checks the correctness of addItemToCart, contains, and nbOccurrences methods
   * defined in the ShoppingCart class
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAddItemToCartContainsNbOccurrences() {

    /*
     * Test 1 - Adding an item to an empty array
     */
    String[] cart1 = new String[10];
    int size1 = 0;

    String[] cart1Expected =
        new String[] {"Banana", null, null, null, null, null, null, null, null, null};
    int size1Expected = 1;

    int size1Actual = ShoppingCart.addItemToCart("Banana", cart1, size1);

    if (size1Actual != size1Expected) {
      System.out
          .println("Problem detected: After adding only one item to the array the size was not "
              + "incremented.");
      return false;
    }

    if (!Arrays.equals(cart1, cart1Expected)) {
      System.out
          .println("Problem detected: After adding only one item to the array the new item was not "
              + "correctly added at the end of the array.");
      return false;
    }

    int nbOccurrences1Actual = ShoppingCart.nbOccurrences("Banana", cart1, size1);
    int nbOccurrencies1Expected = 0;

    if (nbOccurrences1Actual != nbOccurrencies1Expected) {
      System.out.println(
          "Problem detected: In an empty array (array of size 0) the number of occurencies "
              + "should be 0, which was not.");
      return false;
    }

    int nbOccurrences2Actual = ShoppingCart.nbOccurrences("Banana", cart1, size1Actual);
    int nbOccurrencies2Expected = 1;

    if (nbOccurrences2Actual != nbOccurrencies2Expected) {
      System.out.println(
          "Problem detected: After adding only one item to an empty array (array of size 0) the "
              + "number of occurencies of that item should be 1, which was not.");
      return false;
    }

    boolean containsItem1Actual = ShoppingCart.contains("Banana", cart1, size1);
    boolean containsItem1Expected = false;
    if (containsItem1Actual != containsItem1Expected) {
      System.out.println(
          "Problem detected: In an empty array, checking whether it contains an item should "
              + "return false, which did not.");
      return false;
    }

    boolean containsItem2Actual = ShoppingCart.contains("Banana", cart1, size1Actual);
    boolean containsItem2Expected = true;
    if (containsItem2Actual != containsItem2Expected) {
      System.out.println(
          "Problem detected: After adding only one item to an empty array (array of size 0,"
              + " checking whether it contains the item should return true, which did not.");
      return false;
    }



    /*
     * Test 2 - Adding an item to a full array, returns the same size of the oversized array
     * (unchanged)
     */
    String[] cart2 = new String[] {"Milk", "Apple", "Banana", "Pizza"};
    int size2 = 4;

    String[] cart2Expected = new String[] {"Milk", "Apple", "Banana", "Pizza"};
    int size2Expected = 4;

    int size2Actual = ShoppingCart.addItemToCart("Eggs", cart2, size2);

    if (size2Actual != size2Expected) {
      System.out
          .println("Problem detected: After adding only one item to the full array the size did not"
              + " remain the same");
      return false;
    }

    if (!Arrays.equals(cart2, cart2Expected)) {
      System.out.println(
          "Problem detected: After adding only one item to the full array the array did not"
              + " remain the same.");
      return false;
    }

    int nbOccurrences3Actual = ShoppingCart.nbOccurrences("Apple", cart2, size2Actual);
    int nbOccurrencies3Expected = 1;

    if (nbOccurrences3Actual != nbOccurrencies3Expected) {
      System.out
          .println("Problem detected: After adding only one item to the full array the number of"
              + " occurencies should remain unchanged, which was not.");
      return false;
    }



    /*
     * Test 3 - Adding successfully an item to a non-empty array
     */
    String[] cart3 = new String[] {"Milk", "Apple", "Banana", "Pizza", null, null};
    int size3 = 4;

    String[] cart3Expected = new String[] {"Milk", "Apple", "Banana", "Pizza", "Apple", null};
    int size3Expected = 5;

    int size3Actual = ShoppingCart.addItemToCart("Apple", cart3, size3);

    if (size3Actual != size3Expected) {
      System.out.println(
          "Problem detected: After adding only one item to the array the size did not incremented "
              + "by 1");
      return false;
    }

    if (!Arrays.equals(cart3, cart3Expected)) {
      System.out
          .println("Problem detected: After adding only one item to the array the new item was not "
              + "correctly added at the end of the array cart.");
      return false;
    }

    int nbOccurrences4Actual = ShoppingCart.nbOccurrences("Apple", cart3, size3Actual);
    int nbOccurrencies4Expected = 2;

    if (nbOccurrences4Actual != nbOccurrencies4Expected) {
      System.out.println(
          "Problem detected: After adding only one item to the array the number of occurencies"
              + " of that item should increment, which was not.");
      return false;
    }



    /*
     * Test 4 - Adding successfully multiple items to an array
     */
    String[] cart4 = new String[] {"Milk", "Apple", "Pizza", "Pizza", null, null};
    int size4 = 4;

    String[] cart4Expected = new String[] {"Milk", "Apple", "Pizza", "Pizza", "Pizza", "Pizza"};
    int size4Expected = 6;

    int size4Actual = ShoppingCart.addItemToCart("Pizza", cart4, size4);
    size4Actual = ShoppingCart.addItemToCart("Pizza", cart4, size4Actual);

    if (size4Actual != size4Expected) {
      System.out
          .println("Problem detected: After adding multiple items to the array the size did not"
              + " incremented correctly.");
      return false;
    }

    if (!Arrays.equals(cart4, cart4Expected)) {
      System.out.println(
          "Problem detected: After adding multiple items to the array the new items ware not"
              + " correctly added at the end of the array.");
      return false;
    }

    int nbOccurrences5Actual = ShoppingCart.nbOccurrences("Pizza", cart4, size4Actual);
    int nbOccurrencies5Expected = 4;

    if (nbOccurrences4Actual != nbOccurrencies4Expected) {
      System.out.println(
          "Problem detected: After adding multiple items to the array the number of occurencies "
              + "of that item should increment correctly, which was not.");
      return false;
    }

    boolean containsItem3Actual = ShoppingCart.contains("Pizza", cart4, size4);
    boolean containsItem3Expected = true;
    if (containsItem3Actual != containsItem3Expected) {
      System.out.println(
          "Problem detected: After adding multiple items to the array, checking whether it "
              + "contains an item that already existed before in the array should return true, "
              + "which did not.");
      return false;
    }

    boolean containsItem4Actual = ShoppingCart.contains("Pizza", cart4, size4Actual);
    boolean containsItem4Expected = true;
    if (containsItem4Actual != containsItem4Expected) {
      System.out
          .println("Problem detected: After adding multiple items to the array, checking whether it"
              + " contains the item added again in the array, should return true, which did not.");
      return false;
    }


    return true;
  }



  /**
   * This tester method checks the correctness of removeItem() method
   *
   *
   * defined in the ShoppingCart class
   */
  public static boolean testRemoveItem() {

    /*
     * Test 1 - Removing an item stored at index 0 of a non-empty array
     */
    String[] cart1 = new String[] {"Milk", "Apple", "Pizza", "Pizza", "Apple", null, null};
    int size1 = 5;
    int size1Expected = size1 - 1;

    int size1Actual = ShoppingCart.removeItem(cart1, "Apple", size1);
    if (size1Actual != size1Expected) {
      System.out.println("Problem detected: After removing an item "
          + "from the array the size did not decremented correctly.");
      return false;
    }

    for (int i = 0; i < size1; i++) {
      if (cart1[i] != null) {

        if (cart1[i].equals("Apple")) {
          if (ShoppingCart.nbOccurrences(cart1[i], cart1, size1) - 1 == ShoppingCart
              .nbOccurrences(cart1[i], cart1, size1Actual)) {
            System.out.println("Problem detected: After removing "
                + "an item stored at index 0 from the array the number of "
                + "occurencies of the removed element did not decreased by 1.");
            return false;
          }
        } else {
          if (ShoppingCart.nbOccurrences(cart1[i], cart1, size1) != ShoppingCart
              .nbOccurrences(cart1[i], cart1, size1Actual)) {
            System.out.println("Problem detected: After removing "
                + "an item stored at index zero from the array, the number "
                + "of occurencies for the rest of the elements did not remain " + "the same.");
            return false;
          }
        }
      }
    }


    /*
     * Test 2 - Removing an item whose first occurrence is stored at index size-1 (last index
     * position) of a non-empty array
     */

    String[] cart2 = new String[] {"Milk", "Apple", "Pizza", null, null};
    int size2 = 3;
    int size2Expected = size2 - 1;

    int size2Actual = ShoppingCart.removeItem(cart2, "Pizza", size2);
    if (size2Actual != size2Expected) {
      System.out.println("Problem detected: After removing an item whose "
          + "first occurrence is stored at index size-1 (last index position) of a "
          + "non-empty array, the size did not decremented correctly.");
      return false;
    }

    for (int i = 0; i < size2; i++) {
      if (cart2[i] != null) {

        if (cart2[i].equals("Pizza")) {
          if (ShoppingCart.nbOccurrences(cart2[i], cart2, size2) - 1 == ShoppingCart
              .nbOccurrences(cart2[i], cart2, size2Actual)) {
            System.out.println("Problem detected: After removing an item whose"
                + "first occurrence is stored at index size-1 (last index position) "
                + "from the array, the number of occurencies of the removed element "
                + "did not decreased by 1.");
            return false;
          }
        } else {
          if (ShoppingCart.nbOccurrences(cart2[i], cart2, size2) != ShoppingCart
              .nbOccurrences(cart2[i], cart2, size2Actual)) {
            System.out.println("Problem detected: After removing an item whose "
                + "first occurrence is stored at index size-1 (last index position) "
                + "from the array, the number of occurencies for the rest of the elements"
                + "did not remain the same.");
            return false;
          }
        }
      }
    }


    /*
     * Test 3 - Removing an item whose first occurrence is stored at an arbitrary (middle) position
     * within a non-empty array from (from 1 .. size-2)
     */
    String[] cart3 = new String[] {"Milk", "Apple", "Pizza", "Banana", "Pizza", null, null};
    int size3 = 5;
    int size3Expected = size3 - 1;

    int size3Actual = ShoppingCart.removeItem(cart3, cart3[(size3 - 2) / 2], size3);
    if (size3Actual != size3Expected) {
      System.out.println("Problem detected: After removing an item whose "
          + "first occurrence is stored at an arbitrary (middle) position within "
          + "a non-empty array from (from 1 .. size-2) the size did not decremented "
          + "correctly.");
      return false;
    }

    for (int i = 0; i < size3; i++) {
      if (cart3[i] != null) {

        if (cart3[i].equals("Pizza")) {
          if (ShoppingCart.nbOccurrences(cart3[i], cart3, size3) - 1 == ShoppingCart
              .nbOccurrences(cart3[i], cart3, size3Actual)) {
            System.out.println("Problem detected: After removing an item whose "
                + "first occurrence is stored at an arbitrary (middle) position within "
                + "a non-empty array from (from 1 .. size-2) "
                + "from the array, the number of occurencies of the removed element "
                + "did not decreased by 1.");
            return false;
          }
        } else {
          if (ShoppingCart.nbOccurrences(cart3[i], cart3, size3) != ShoppingCart
              .nbOccurrences(cart3[i], cart3, size3Actual)) {
            System.out.println("Problem detected: After removing an item whose "
                + "first occurrence is stored at an arbitrary (middle) position within "
                + "a non-empty array from (from 1 .. size-2) "
                + "from the array, the number of occurencies for the rest of the elements"
                + "did not remain the same.");
            return false;
          }
        }
      }
    }


    /*
     * Test 4 - Removing an item from an empty array (whose size is zero)
     */
    String[] cart4 = new String[] {null, null, null};
    int size4 = 0;
    int size4Expected = 0;

    int size4Actual = ShoppingCart.removeItem(cart4, "Apple", size4);
    if (size4Actual != size4Expected) {
      System.out.println("Problem detected: After removing an item "
          + "from an empty array the size did not remain the same.");
      return false;
    }

    for (int i = 0; i < size4; i++) {
      if (cart4[i] != null) {

        if (cart4[i].equals("Apple")) {
          if (ShoppingCart.nbOccurrences(cart4[i], cart4, size4) - 1 == ShoppingCart
              .nbOccurrences(cart4[i], cart4, size4Actual)) {
            System.out.println(
                "Problem detected: After removing " + "an item from an empty array, the number of "
                    + "occurencies of all elements should remain unchanged (zero).");
            return false;
          }
        } else {
          if (ShoppingCart.nbOccurrences(cart4[i], cart4, size4) != ShoppingCart
              .nbOccurrences(cart4[i], cart4, size4Actual)) {
            System.out.println(
                "Problem detected: After removing " + "an item from an empty array, the number "
                    + "of occurencies of all elements should remain " + "unchanged (zero).");
            return false;
          }
        }
      }
    }

    /*
     * Test 5 - Removing a non-existing item from the array
     */
    String[] cart5 = new String[] {"Milk", "Apple", "Pizza", "Banana", "Pizza", null, null};
    int size5 = 5;
    int size5Expected = size5;

    int size5Actual = ShoppingCart.removeItem(cart5, "NON EXISTING", size5);
    if (size5Actual != size5Expected) {
      System.out.println("Problem detected: Trying to remove a non existing item "
          + "from an array should not change change the array size, which was not the case here.");
      return false;
    }

    for (int i = 0; i < size5; i++) {
      if (cart5[i] != null) {

        if (cart5[i].equals("NON EXISTING")) {
          if (ShoppingCart.nbOccurrences(cart5[i], cart5, size5) - 1 == ShoppingCart
              .nbOccurrences(cart5[i], cart5, size5Actual)) {
            System.out.println("Problem detected: Trying to remove "
                + "a non existing item from an array, the number of "
                + "occurencies of all elements should remain unchanged.");
            return false;
          }
        } else {
          if (ShoppingCart.nbOccurrences(cart5[i], cart5, size5) != ShoppingCart
              .nbOccurrences(cart5[i], cart5, size5Actual)) {
            System.out.println("Problem detected: Trying to remove "
                + "a non existing item from an array, the number "
                + "of occurencies of all elements should remain " + "unchanged.");
            return false;
          }
        }
      }
    }

    return true;
  }


  /**
   * This tester method checks the correctness of checkout and getCartSummary() methods defined in
   * the ShoppingCart class
   */
  public static boolean testCheckoutGetCartSummary() {

    /*
     * Test 1 - Calling ShoppingCart.getCartSummary() on an empty cart
     */
    String[] cart1 = new String[7];
    int size1 = 0;

    String actual1 = ShoppingCart.getCartSummary(cart1, size1);
    String expected1 = "";
    if (!actual1.equals(expected1)) {
      System.out.println("Problem detected: The returned cart "
          + "summary of an empty cart should an empty string");
      return false;
    }

    double actual1Checkout = ShoppingCart.checkout(cart1, size1);
    double expected1Checkout = 0.0;
    if (Math.abs(actual1Checkout - expected1Checkout) > 0.001) {
      System.out.println("Problem detected: The checkout price to pay "
          + "in dollars of an empty cart should be 0, which was not the case here.");
      return false;
    }


    /*
     * Test 2 - Calling ShoppingCart.getCartSummary() on a cart which contains non-duplicate
     * (unique) items.
     */
    String[] cart2 = new String[] {"Milk", "Apple", "Banana", "Pizza", null, null};
    int size2 = 4;

    String actual2 = ShoppingCart.getCartSummary(cart2, size2);
    String expected2 = "(1) Milk\n" + "(1) Apple\n" + "(1) Banana\n" + "(1) Pizza\n";
    if (!actual2.equals(expected2)) {
      System.out.println(
          "Problem detected: The returned cart " + "summary of a cart which contains non-duplicate "
              + "(unique) items does not return the correct string " + "format");
      return false;
    }

    double actual2Checkout = ShoppingCart.checkout(cart2, size2);
    double expected2Checkout = 16.453;
    if (Math.abs(actual2Checkout - expected2Checkout) > 0.001) {
      System.out.println("Problem detected: The checkout price to pay "
          + "in dollars of a cart which contains non-duplicate (unique) "
          + "items was not correct.");
      return false;
    }


    /*
     * Test 3 - Calling ShoppingCart.getCartSummary() on a cart which contains items with multiple
     * occurrences at different positions of the oversized array.
     */
    String[] cart3 = new String[] {"Tomato", "Milk", "Milk", "Eggs", "Tomato", "Onion", "Eggs",
        "Milk", "Banana", null, null};
    int size3 = 9;

    String actual3 = ShoppingCart.getCartSummary(cart3, size3);
    String expected3 =
        "(2) Tomato\n" + "(3) Milk\n" + "(2) Eggs\n" + "(1) Onion\n" + "(1) Banana\n";
    if (!actual3.equals(expected3)) {
      return false;
    }

    double actual3Checkout = ShoppingCart.checkout(cart3, size3);
    double expected3Checkout = 18.175;
    if (Math.abs(actual3Checkout - expected3Checkout) > 0.001) {
      System.out.println("Problem detected: The checkout price to pay "
          + "in dollars of a cart which contains multiple occurances at different "
          + "positions of the oversized array.");
      return false;
    }

    return true;
  }


  /**
   * This tester runs all the tester methods defined in this tester class. For instance, if this
   * tester class defines three tester methods named test1(), test2() and test3(), it will return
   * test1() && test2() && test3() Returns false if any of the tester methods fails, and true if all
   * the tests are passed.
   */
  public static boolean runAllTests() {

    if (testLookupMethods() && testGetProductPrice() && testAddItemToCartContainsNbOccurrences()
        && testRemoveItem() && testCheckoutGetCartSummary()) {
      return true;
    }

    return false;
  }



}
