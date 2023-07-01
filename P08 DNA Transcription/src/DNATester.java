//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P08 DNA
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
 * Tester class, where we have tests for all the classes and methods in them
 * 
 */
public class DNATester extends Object {

  public static boolean testEnqueueDequeue() {
    LinkedQueue<Character> test = new LinkedQueue<>();


    try {
      test.dequeue();
      return false;
    } catch (NoSuchElementException e) {
    } catch (Exception e) {
      System.out.println("ERROR: Unknown exception thrown!");
      return false;
    }


    try {
      test.peek();
      return false;
    } catch (NoSuchElementException e) {
    } catch (Exception e) {
      System.out.println("ERROR: Unknown exception thrown!");
      return false;
    }

    try {
      test.enqueue('q');
      test.enqueue('w');
      test.enqueue('e');
      test.enqueue('r');
      if (!test.toString().replaceAll(" ", "").equals("qwer")) {
        System.out.println("ERROR: Problem with enqueue method!");
        return false;
      }

      if (test.dequeue() != 'q') {
        System.out.println("ERROR: Problem with dequeue method!");
        return false;
      }

      if (test.dequeue() != 'w') {
        System.out.println("ERROR: Problem with dequeue method!");
        return false;
      }

      test.enqueue('r');
      if (!test.toString().replaceAll(" ", "").equals("err")) {
        System.out.println("ERROR: Problem with enqueue method!");
        return false;
      }
    } catch (Exception e) {
      System.out.println("ERROR: Unknown exception thrown!");
      return false;
    }
    return true;
  }

  public static boolean testQueueSize() {
    LinkedQueue<Character> test = new LinkedQueue<>();

    // Checks size method on empty queue
    try {
      if (test.size() != 0) {
        System.out.println("ERROR: Problem with size method!");
        return false;
      }
    } catch (Exception e) {
      System.out.println("ERROR: Unknown exception thrown!");
      return false;
    }

    // Checks isEmpty on empty queue
    try {
      if (!test.isEmpty()) {
        System.out.println("ERROR: Problem with isEmpty method!");
        return false;
      }
    } catch (Exception e) {
      System.out.println("ERROR: Unknown exception thrown!");
      return false;
    }

    // Checks isEmpty and size methods after adding to queue
    try {
      test.enqueue('q');
      test.enqueue('w');
      test.enqueue('e');
      test.enqueue('r');
      if (test.size() != 4) {
        System.out.println("ERROR: Problem with size method!");
        return false;
      }
      if (test.isEmpty()) {
        System.out.println("ERROR: Problem with isEmpty method!");
        return false;
      }

      test.dequeue();
      test.dequeue();
      if (test.size() != 2) {
        System.out.println("ERROR: Problem with size method!");
        return false;
      }

      if (test.isEmpty()) {
        System.out.println("ERROR: Problem with isEmpty method!");
        return false;
      }

      test.enqueue('r');

      if (test.size() != 3) {
        System.out.println("ERROR: Problem with size method!");
        return false;
      }

      if (test.isEmpty()) {
        System.out.println("ERROR: Problem with isEmpty method!");
        return false;
      }
    } catch (Exception e) {
      System.out.println("ERROR: Unknown exception thrown!");
      return false;
    }
    return true;
  }

  /**
   * Tests if the transcribeDNA() method works properly
   * 
   * @return true if and only if the method works correctly
   * 
   */
  public static boolean testTranscribeDNA() {
    DNA testDNA = new DNA("CCGGCCCTCCGGTGGATCCAACCGGCCCTCCGGTGGATC");
    String mRNA = "GGCCGGGAGGCCACCUAGGUUGGCCGGGAGGCCACCUAG";
    try {
      if (!testDNA.transcribeDNA().toString().replaceAll(" ", "").equals(mRNA)) {
        return false;
      }
    } catch (Exception e) {
      System.out.println("ERROR: Unknown exception thrown!");
      return false;
    }
    return true;
  }

  /**
   * Tests if the translateDNA() method works properly
   * 
   * @return true if and only if the method works correctly
   */
  public static boolean testTranslateDNA() {
    DNA testDNA = new DNA("GGAGTCAGTTAAACCGGGACATACTGTCTTGGTAATCTCGCTAGAAAGTCT");
    try {
      if (!testDNA.translateDNA().toString().replaceAll(" ", "").equals("PQSIWPCMTEPLERSFR")) {
        return false;
      }
    } catch (Exception e) {
      System.out.println("ERROR: Unknown exception thrown!");
      return false;
    }
    return true;
  }

  public static boolean testMRNATranslate() {
    try {
      DNA testDNA = new DNA("GGAGTCAGTTAAGCGACCTACTGTCTTGGTAATCTCCGAGCTAGATCT");
      String tester =
          testDNA.mRNATranslate​(testDNA.transcribeDNA()).toString().replaceAll(" ", "");
      if (!tester.equals("PQSIRWMTEPLEARSR")) {
        return false;
      }
    } catch (Exception e) {
      System.out.println("ERROR: Unknown exception thrown!");
      return false;
    }

    try {
      DNA testDNA = new DNA("GTGCGTGTGCGTGTGCGT");
      String tester =
          testDNA.mRNATranslate​(testDNA.transcribeDNA()).toString().replaceAll(" ", "");
      if (!tester.equals("HAHAHA")) {
        return false;
      }
    } catch (Exception e) {
      System.out.println("ERROR: Unknown exception thrown!");
      return false;
    }

    try {
      DNA testDNA = new DNA("GTGCGTGTGCGTGTGCGTGTGCGTGTGCGTGTGCGT");
      String tester =
          testDNA.mRNATranslate​(testDNA.transcribeDNA()).toString().replaceAll(" ", "");
      if (!tester.equals("HAHAHAHAHAHA")) {
        return false;
      }
    } catch (Exception e) {
      System.out.println("ERROR: Unknown exception thrown!");
      return false;
    }

    return true;
  }

  /**
   * Main method, we use this to run all the test methods and output the results
   * 
   * @param args unused
   */
  public static void main(String[] args) {
    System.out.println("EnqueueDequeue: " + testEnqueueDequeue());
    
    System.out.println("QueueSize: " + testQueueSize());
    
    System.out.println("transcribeDNA: " + testTranscribeDNA());
    
    System.out.println("translateDNA: " + testTranslateDNA());
    
    System.out.println("MRNATranslate: " + testMRNATranslate());
  }

}
