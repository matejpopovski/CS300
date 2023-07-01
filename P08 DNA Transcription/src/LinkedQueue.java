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


public class LinkedQueue<T> extends Object implements QueueADT<T> {
  private int n; // The number of elements in the queue
  protected Node<T> front; // The node at the front of the queue, added LEAST recently
  protected Node<T> back; // The node at the back of the queue, added MOST recently

  @Override
  public void enqueue(T data) {
    Node<T> newNode = new Node<T>(data);
    if (n == 0) {
      front = newNode;
      back = newNode;
      n = 1;
    } else {
      back.setNext​(newNode);
      back = newNode;
      n += 1;
    }
  }

  @Override
  public T dequeue() {
    T returnNode;
    if (isEmpty()) {
      throw new NoSuchElementException("ERROR: queue is empty!");
    } else if (n == 1) {
      returnNode = front.getData();
      front = null;
      back = null;
      n -= 1;
    } else {
      returnNode = front.getData();
      front = front.getNext();
      n -= 1;
    }
    return returnNode;
  }

  @Override
  public T peek() {
    if (isEmpty()) {
      throw new NoSuchElementException("ERROR: queue is empty!");
    }
    return front.getData();
  }

  @Override
  public boolean isEmpty() {
    if (n == 0) {
      return true;
    }
    return false;
  }

  @Override
  public int size() {
    return n;
  }

  public String toString() {
    String toReturn = "";
    Node<T> node = front;
    for (int i = 0; i < n; i++) {
      toReturn += node.getData() + " ";
      node = node.getNext();
    }
    return toReturn.substring(0, toReturn.length() - 1);
  }

}
