//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P09 Art Gallery
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
import java.util.ArrayList;

/**
 * This class checks the correctness of the implementation of the methods defined in the class
 * ArtworkGallery.
 * 
 * @author Matej Popovski
 *
 */

public class ArtGalleryTester {

  /**
   * Checks whether the implementation of both compareTo() and equals() methods defined in the
   * Artwork class are working properly.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testArtworkCompareToEquals() {
    // TODO complete the implementation of this method
    Artwork first = new Artwork("first", 2017, 2000.0);
    Artwork second = new Artwork("Second", 1998, 2000.0);

    if (!(first.compareTo(second) > 0)) {
      return false;
    }


    first = new Artwork("first", 2002, 700.0);
    second = new Artwork("Second", 2002, 690.0);
    if (!(second.compareTo(first) < 0)) {
      return false;
    }

    first = new Artwork("abcd", 2005, 2000.0);
    second = new Artwork("acde", 2005, 2000.0);
    if (!(first.compareTo(second) < 0)) {
      return false;
    }

    first = new Artwork("abcd", 2007, 2000.0);
    second = new Artwork("abcd", 2007, 2000.0);
    if (!(first.compareTo(second) == 0)) {
      return false;
    }


    return true;
  }

  public static boolean isValid(ArtGallery artGallery, Artwork name) {
    if (artGallery.lookup(name.getName(), name.getYear(), name.getCost())) {
      return true;
    }

    return false;
  }

  /**
   * Checks the correctness of the implementation of both addArtwork() and toString() methods
   * implemented in the ArtworkGallery class. This unit test considers at least the following
   * scenarios. (1) Create a new empty ArtworkGallery, and check that its size is 0, it is empty,
   * and that its string representation is an empty string "". (2) try adding one artwork and then
   * check that the addArtwork() method call returns true, the tree is not empty, its size is 1, and
   * the .toString() called on the tree returns the expected output. (3) Try adding another artwork
   * which is smaller that the artwork at the root, (4) Try adding a third artwork which is greater
   * than the one at the root, (5) Try adding at least two further artwork such that one must be
   * added at the left subtree, and the other at the right subtree. For all the above scenarios, and
   * more, double check each time that size() method returns the expected value, the add method call
   * returns true, and that the .toString() method returns the expected string representation of the
   * contents of the binary search tree in an increasing order from the smallest to the greatest
   * artwork with respect to year, cost, and then name. (6) Try adding a artwork already stored in
   * the tree. Make sure that the addArtwork() method call returned false, and that the size of the
   * tree did not change.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAddArtworkToStringSize() {
    Artwork first = new Artwork("MAT", 2006, 400.0);
    Artwork second = new Artwork("POP", 1899, 150.0);
    Artwork third = new Artwork("POP", 1986, 150.0);
    Artwork fourth = new Artwork("POP", 1982, 150.0);
    Artwork fifth = new Artwork("POP", 1983, 150.0);
    ArtGallery artGallery = new ArtGallery();

    try {
      artGallery.addArtwork(first);
      artGallery.addArtwork(second);
      artGallery.addArtwork(third);
      artGallery.addArtwork(fourth);
      artGallery.addArtwork(fifth);
    } catch (Exception e) {
      return false;
    }

    if (!((isValid(artGallery, first) && isValid(artGallery, second) && isValid(artGallery, third)
        && isValid(artGallery, fourth) && isValid(artGallery, fifth)))) {
      return false;
    }

    // if (!(artGallery.lookup("MAT", 2006, 400.0) && artGallery.lookup("POP", 1899, 150.0)
    // && artGallery.lookup("POP", 1986, 150.0) && artGallery.lookup("POP", 1982, 150.0)
    // && artGallery.lookup("POP", 1983, 150.0))) {
    // return false;
    // }

    if (artGallery.size() != 5) {
      return false;
    }

    artGallery = new ArtGallery();
    artGallery.addArtwork(first);
    artGallery.addArtwork(second);
    String checker = "[(Name: POP) (Year: 1899) (Cost: $150.0)]\n"
        + "[(Name: MAT) (Year: 2006) (Cost: $400.0)]\n";
    if (!artGallery.toString().equals(checker)) {
      return false;
    }


    // TODO complete the implementation of this method
    return true; // Default return statement added to resolve compiler errors
  }

  /**
   * This method checks mainly for the correctness of the ArtworkGallery.lookup() method. It must
   * consider at least the following test scenarios. (1) Create a new ArtworkGallery. Then, check
   * that calling the lookup() method on an empty ArtworkGallery returns false. (2) Consider a
   * ArtworkGallery of height 3 which lookup at least 5 artwork. Then, try to call lookup() method
   * to search for the artwork having a match at the root of the tree. (3) Then, search for a
   * artwork at the right and left subtrees at different levels considering successful and
   * unsuccessful search operations. Make sure that the lookup() method returns the expected output
   * for every method call.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testLookup() {
    ArtGallery artGallery = new ArtGallery();
    if (artGallery.lookup("MAT", 2002, 3000.0)) {
      return false;
    }

    Artwork first = new Artwork("MAT", 2002, 3000.0);
    Artwork second = new Artwork("POPO", 2000, 1500.0);
    Artwork third = new Artwork("POPO", 1986, 1500.0);
    Artwork fourth = new Artwork("POPO", 1982, 1500.0);
    Artwork fifth = new Artwork("POPO", 1983, 1500.0);


    artGallery.addArtwork(first);
    artGallery.addArtwork(second);
    artGallery.addArtwork(third);
    artGallery.addArtwork(fourth);
    artGallery.addArtwork(fifth);

    if (!artGallery.lookup("MAT", 2002, 3000.0)) {
      return false;
    }
    if (!artGallery.lookup("POPO", 2000, 1500.0)) {
      return false;
    }
    if (!artGallery.lookup("POPO", 1986, 1500.0)) {
      return false;
    }
    if (!artGallery.lookup("POPO", 1982, 1500.0)) {
      return false;
    }
    if (!artGallery.lookup("POPO", 1983, 1500.0)) {
      return false;
    }
    if (artGallery.lookup("POPO", 1911, 1500.0)) {
      return false;
    }
    if (artGallery.lookup("POPO", 1981, 10.0)) {
      return false;
    }
    if (artGallery.lookup("POPOVSKI", 1981, 1500.0)) {
      return false;
    }
    if (artGallery.lookup("POPOVSKI", 1911, 100.0)) {
      return false;
    }


    return true; // Default return statement added to resolve compiler errors
  }

  /**
   * Checks for the correctness of ArtworkGallery.height() method. This test must consider several
   * scenarios such as, (1) ensures that the height of an empty artwork tree is zero. (2) ensures
   * that the height of a tree which consists of only one node is 1. (3) ensures that the height of
   * a ArtworkGallery with the following structure for instance, is 4. (*) / \ (*) (*) \ / \ (*) (*)
   * (*) / (*)
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testHeight() {
    // TODO complete the implementation of this method
    ArtGallery artGallery = new ArtGallery();
    if (artGallery.height() != 0) {
      return false;
    }
    Artwork first = new Artwork("MAT", 1991, 3000.0);
    artGallery.addArtwork(first);

    if (artGallery.height() != 1) {
      return false;
    }

    Artwork second = new Artwork("POP", 1981, 1500.0);
    Artwork third = new Artwork("POP", 1986, 1500.0);
    Artwork fourth = new Artwork("POP", 1982, 1500.0);
    Artwork fifth = new Artwork("POP", 1983, 1500.0);
    Artwork sixth = new Artwork("POP", 1979, 1500.0);
    Artwork seventh = new Artwork("POP", 1987, 1500.0);

    artGallery.addArtwork(second);
    artGallery.addArtwork(third);
    artGallery.addArtwork(fourth);
    artGallery.addArtwork(fifth);
    artGallery.addArtwork(sixth);
    artGallery.addArtwork(seventh);

    if (artGallery.height() != 5) {
      return false;
    }


    return true;
  }

  /**
   * Checks for the correctness of ArtworkGallery.getBestArtwork() method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testGetBestArtwork() {
    ArtGallery artGallery = new ArtGallery();

    if (artGallery.getBestArtwork() != null) {
      return false;
    }

    Artwork second = new Artwork("POP", 1981, 2500.0);
    artGallery.addArtwork(second);

    if (artGallery.getBestArtwork() != second) {
      return false;
    }

    Artwork third = new Artwork("POP", 1986, 1500.0);
    Artwork fourth = new Artwork("POP", 1982, 1500.0);
    Artwork fifth = new Artwork("POP", 1983, 1500.0);
    Artwork sixth = new Artwork("POP", 1979, 1500.0);
    Artwork seventh = new Artwork("POP", 1987, 1500.0);


    artGallery.addArtwork(third);
    artGallery.addArtwork(fourth);
    artGallery.addArtwork(fifth);
    artGallery.addArtwork(sixth);
    artGallery.addArtwork(seventh);

    if (artGallery.getBestArtwork() != seventh) {
      return false;
    }


    return true; // Default return statement added to resolve compiler errors
  }


  /**
   * Checks for the correctness of ArtworkGallery.lookupAll() method. This test must consider at
   * least 3 test scenarios. (1) Ensures that the ArtworkGallery.lookupAll() method returns an empty
   * arraylist when called on an empty tree. (2) Ensures that the ArtworkGallery.lookupAll() method
   * returns an array list which contains all the artwork satisfying the search criteria of year and
   * cost, when called on a non empty artwork tree with one match, and two matches and more. Vary
   * your search criteria such that the lookupAll() method must check in left and right subtrees.
   * (3) Ensures that the ArtworkGallery.lookupAll() method returns an empty arraylist when called
   * on a non-empty artwork tree with no search results found.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testLookupAll() {
    ArtGallery artGallery = new ArtGallery();
    ArrayList<Artwork> returnTo = new ArrayList<Artwork>();
    if (artGallery.lookupAll(1978, 1000) != null) {
      return false;
    }
    Artwork second = new Artwork("POP", 1981, 750.0);
    Artwork third = new Artwork("POP", 1986, 750.0);
    Artwork fourth = new Artwork("POP", 1982, 750.0);
    Artwork fifth = new Artwork("POP", 1983, 750.0);
    artGallery.addArtwork(second);
    artGallery.addArtwork(third);
    artGallery.addArtwork(fourth);
    artGallery.addArtwork(fifth);
    if (!artGallery.lookupAll(2000, 7268.0).equals(returnTo)) {
      return false;
    }
    returnTo.add(third);
    if (!artGallery.lookupAll(1986, 750.0).equals(returnTo)) {
      return false;
    }

    Artwork sixth = new Artwork("Matej", 1986, 750.0);
    artGallery.addArtwork(sixth);
    returnTo.add(sixth);
    if (!artGallery.lookupAll(1986, 750.0).equals(returnTo)) {
      return false;
    }

    Artwork seventh = new Artwork("Popovski", 1986, 750.0);
    artGallery.addArtwork(seventh);
    returnTo.add(seventh);
    if (!artGallery.lookupAll(1986, 750.0).equals(returnTo)) {
      return false;
    }


    return true; // Default return statement added to resolve compiler errors
  }

  /**
   * Checks for the correctness of ArtworkGallery.buyArtwork() method. This test must consider at
   * least 3 test scenarios. (1) Buying artwork that is at leaf node (2) Buying artwork at non-leaf
   * node (3) ensures that the ArtworkGallery.buyArtwork() method throws a NoSuchElementException
   * when called on an artwork that is not present in the BST
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testBuyArtwork() {
    // TODO complete the implementation of this method
    Artwork first = new Artwork("mate", 1991, 600.0);
    Artwork second = new Artwork("mate", 1981, 600.0);

    ArtGallery artGallery = new ArtGallery();
    artGallery.addArtwork(first);
    artGallery.addArtwork(second);
    artGallery.buyArtwork("mate", 1981, 600.0);
    if (artGallery.lookup("mate", 1981, 600.0)) {
      return false;
    }
    artGallery.addArtwork(second);

    Artwork third = new Artwork("popo", 1985, 600.0);
    Artwork fourth = new Artwork("mate", 1984, 600.0);
    Artwork fifth = new Artwork("mate", 1983, 600.0);
    Artwork sixth = new Artwork("mate", 1975, 600.0);
    artGallery.addArtwork(third);
    artGallery.addArtwork(fourth);
    artGallery.addArtwork(fifth);
    artGallery.addArtwork(sixth);
    artGallery.buyArtwork("popo", 1985, 600.0);
    if (artGallery.lookup("mate", 1985, 600.0)) {
      return false;
    }

    try {
      artGallery.buyArtwork("mp", 1911, 60.0);
      return false;
    } catch (NoSuchElementException e) {

    } catch (Exception e) {
      return false;
    }

    return true; // Default return statement added to resolve compiler errors
  }

  /**
   * Returns false if any of the tester methods defined in this tester class fails.
   * 
   * @return false if any of the tester methods defined in this tester class fails, and true if all
   *         tests pass
   */
  public static boolean runAllTests() {
    if (testArtworkCompareToEquals() && testAddArtworkToStringSize() && testLookup() && testHeight()
        && testGetBestArtwork() && testLookupAll() && testBuyArtwork()) {
      return true;
    }

    return false; // Default return statement added to resolve compiler errors
  }

  /**
   * Calls all the test methods
   * 
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    System.out.println("testArtworkCompareToEquals(): " + testArtworkCompareToEquals());
    System.out.println("testAddArtworkToStringSize(): " + testAddArtworkToStringSize());
    System.out.println("testLookup(): " + testLookup());
    System.out.println("testHeight(): " + testHeight());
    System.out.println("testGetBestArtwork(): " + testGetBestArtwork());
    System.out.println("testLookupAll(): " + testLookupAll());
    System.out.println("testBuyArtwork(): " + testBuyArtwork());
    System.out.println("runAllTests(): " + runAllTests());
  }

}
