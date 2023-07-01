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

public class Node<T> extends Object {
  private T data;
  private Node<T> next;

  public Node(T data) {
    this.data = data;
  }

  public Node(T data, Node<T> next) {
    this.data = data;
    this.next = next;
  }

  public T getData() {
    return data;
  }

  public Node<T> getNext() {
    return next;
  }

  public void setNext​(Node<T> next) {
    this.next = next;
  }

}
