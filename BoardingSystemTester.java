//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P10 Airplane Boarding System
// Course: CS 300 Fall 2023
//
// Author: Charlie Cheng
// Email: cheng274@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: NONE
// Partner Email: NONE
// Partner Lecturer's Name: NONE
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understand the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: Angus He, debugging the percolateDown method;
// Bo Brown, debugging the enqueue method;
// Yang Shengyuan, how to start with testers;
// Online Sources: Zybook percolate up and percolate down pseudo code guidance:
// https://learn.zybooks.com/zybook/WISCCOMPSCI300Fall2023/chapter/13/section/3
//
///////////////////////////////////////////////////////////////////////////////

// TODO FILE HEADER COMES HERE

import java.util.NoSuchElementException;

/**
 * This is a Utility class which implements tester methods to ensure the correctness of the
 * implementation of the main operations defined in cs300 fall 2023 p10 Airplane Boarding System.
 *
 */
public class BoardingSystemTester {

  /**
   * Ensures the correctness of Passenger.compareTo() method when called to compare two Passenger
   * objects having different boarding groups.
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testPassengerCompareToDifferentGroup() {
    // TODO complete the implementation of this tester method
    // [HINT] You can consider at least two Passenger objects, and ensure at least
    // the following:
    // p1.compareTo(p2) < 0, if p1 has a boarding group less than the boarding group
    // of p2.
    // p2.compareTo(p1) > 0
    // where p1, and p2 are references to Passenger objects with different boarding
    // groups.
    // Recall that we defined three boarding groups A, B, and C such that A < B < C.
    Passenger p1 = new Passenger("p1", Group.A, true);
    Passenger p2 = new Passenger("p2", Group.B, true);
    Passenger.resetPassengerOrder();
    if (p1.compareTo(p2) >= 0) {
      return false;
    }

    if (p2.compareTo(p1) <= 0) {
      return false;
    }

    Passenger p3 = new Passenger("p1", Group.B, true);
    Passenger p4 = new Passenger("p2", Group.A, true);

    if (p3.compareTo(p4) <= 0) {
      return false;
    }

    if (p4.compareTo(p3) >= 0) {
      return false;
    }

    return true; // default return statement
  }

  /**
   * Ensures the correctness of Passenger.compareTo() method when called to compare two Passenger
   * objects having the same boarding group, and different arrival orders.
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testPassengerCompareToSameGroupDifferentArrival() {
    // TODO complete the implementation of this tester method
    // [Hint] You can consider at least two Passenger objects having the SAME
    // boarding group, and
    // ensure at least the following:
    // p1.compareTo(p2) < 0, if p1.ARRIVAL_ORDER is less than p2.ARRIVAL_ORDER
    // p2.compareTo(p1) > 0
    Passenger.resetPassengerOrder();
    Passenger p1 = new Passenger("p1", Group.A, true);
    Passenger p2 = new Passenger("p1", Group.A, true);

    if (p1.compareTo(p2) >= 0) {
      return false;
    }

    if (p2.compareTo(p1) <= 0) {
      return false;
    }

    return true; // default return statement
  }

  /**
   * Ensures two passengers having the SAME boarding group and with the SAME order of arrival are
   * equal (compareTo should return 0).
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testPassengerCompareToSameGroupSameArrival() {
    // TODO complete the implementation of this tester method
    // Even though this case will not be possible in your priority queue, it is
    // required for testing
    // the full functionality of the compareTo() method.
    // [Hint] You can use the resetPassengerOrder() to create equivalent passengers.
    Passenger.resetPassengerOrder();
    Passenger p1 = new Passenger("p1", Group.A, true);
    Passenger.resetPassengerOrder();
    Passenger p2 = new Passenger("p2", Group.A, true);
    Passenger.resetPassengerOrder();

    if (p1.compareTo(p2) != 0) {
      return false;
    }

    return true; // default return statement
  }

  /**
   * Ensures the correctness of the constructor for BoardingQueue class.
   * 
   * This tester should implement at least the following test cases:
   *
   * - Calling the constructor of the BoardingQueue class with an invalid capacity should throw an
   * IllegalArgumentException - Calling the constructor or the BoardingQueue class with a valid
   * capacity should not throw any errors, and should result in a new BoardingQueue object which is
   * empty, has size 0, a capacity equal to the capacity that was passed as a parameter, and the
   * heap array contains only null references.
   *
   * @return true if the constructor of the BoardingQueue functions properly, false otherwise
   */
  public static boolean testBoardingQueueConstructor() {
    // TODO complete the implementation of this tester method
    // [HINT] you can get a copy of the heap array by calling
    // BoardingQueue.toArray() method
    Passenger.resetPassengerOrder();

    try {
      BoardingQueue newQueue = new BoardingQueue(-1);
      return false;
    } catch (IllegalArgumentException e) {

    }
    BoardingQueue newQueue = new BoardingQueue(3);

    if (newQueue.isEmpty() == false) {
      return false;
    }

    if (newQueue.size() != 0) {
      return false;
    }

    if (newQueue.capacity() != 3) {
      return false;
    }

    return true; // default return statement
  }

  /**
   * Tests the functionality of BoardingQueue.peekBest() method by calling peekBest on an empty
   * queue and verifying it throws a NoSuchElementException.
   * 
   * @return true if BoardingQueue.peekBest() verifies a correct functionality, false otherwise
   */
  public static boolean testPeekBestEmptyQueue() {
    // TODO complete the implementation of this tester method
    BoardingQueue newQueue = new BoardingQueue(3);

    try {
      newQueue.peekBest();
      return false;
    } catch (NullPointerException e) {

    }

    return true; // default return statement
  }

  /**
   * Tests the functionality of BoardingQueue.peekBest() method by calling peekBest on a non-empty
   * queue and ensures it
   * 
   * 1) returns the Passenger having the highest priority (the minimum), and 2) does not remove that
   * Passenger from the boarding queue.
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testPeekBestNonEmptyQueue() {
    // TODO complete the implementation of this tester method
    BoardingQueue newQueue = new BoardingQueue(3);
    Passenger p1 = new Passenger("p1", Group.A, true);
    newQueue.enqueue(p1);
    Passenger p2 = new Passenger("p2", Group.B, true);
    newQueue.enqueue(p2);
    Passenger p3 = new Passenger("p3", Group.C, true);
    newQueue.enqueue(p3);

    if (!newQueue.peekBest().equals(p1)) {
      return false;
    }

    return true; // default return statement
  }

  /**
   * Tests the functionality of the BoardingQueue.enqueue() method by calling enqueue() on an empty
   * queue and ensuring the method 1) adds the Passenger and 2) increments the size.
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testEnqueueToEmptyQueue() {
    // TODO complete the implementation of this tester method
    BoardingQueue newQueue = new BoardingQueue(3);
    Passenger p1 = new Passenger("p1", Group.A, true);
    newQueue.enqueue(p1);

    if (newQueue.isEmpty() == true) {
      return false;
    }

    if (newQueue.size() != 1) {
      return false;
    }

    return true;
  }

  /**
   * Tests the functionality of the BoardingQueue.enqueue() method by calling enqueue() on a
   * non-empty queue and ensuring it
   * 
   * 1) inserts the Passenger at the proper position of the heap, increments the size by one, and
   * returns true, if the queue was not already full.
   * 
   * 2) returns false, without making any changes to the size of the queue or the array heap, if the
   * method is called on a full queue.
   * 
   * Try adding at least 5 Passengers.
   * 
   * @return true if tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testEnqueueToNonEmptyQueue() {
    // TODO complete the implementation of this tester method
    // [HINT] you can get a copy of the heap array by calling
    // BoardingQueue.toArray() method
    BoardingQueue newQueue = new BoardingQueue(5);
    Passenger p1 = new Passenger("p1", Group.C, true);
    newQueue.enqueue(p1);
    Passenger p2 = new Passenger("p2", Group.C, true);
    newQueue.enqueue(p2);

    if (!newQueue.toArray()[0].equals(p1)) {
      return false;
    }
    if (!newQueue.toArray()[1].equals(p2)) {
      return false;
    }

    Passenger p3 = new Passenger("p3", Group.B, true);
    newQueue.enqueue(p3);

    if (!newQueue.toArray()[0].equals(p3)) {
      return false;
    }
    if (!newQueue.toArray()[1].equals(p2)) {
      return false;
    }

    if (!newQueue.toArray()[2].equals(p1)) {
      return false;
    }

    Passenger p4 = new Passenger("p4", Group.B, true);
    newQueue.enqueue(p4);
    if (!newQueue.toArray()[0].equals(p3)) {
      return false;
    }

    if (!newQueue.toArray()[1].equals(p4)) {
      return false;
    }

    if (!newQueue.toArray()[2].equals(p1)) {
      return false;
    }

    if (!newQueue.toArray()[3].equals(p2)) {
      return false;
    }

    Passenger p5 = new Passenger("p5", Group.C, true);
    newQueue.enqueue(p5);
    if (!newQueue.toArray()[0].equals(p3)) {
      return false;
    }

    if (!newQueue.toArray()[1].equals(p4)) {
      return false;
    }

    if (!newQueue.toArray()[2].equals(p1)) {
      return false;
    }

    if (!newQueue.toArray()[3].equals(p2)) {
      return false;
    }

    if (!newQueue.toArray()[4].equals(p5)) {
      return false;
    }

    if (newQueue.size() != 5) {
      return false;
    }

    Passenger p6 = new Passenger("p6", Group.C, true);

    if (newQueue.enqueue(p6) != false) {
      return false;
    }

    return true; // default return statement
  }

  /**
   * Tests the functionality of BoardingQueue.dequeue() method by calling dequeue() on an empty
   * queue and ensures a NoSuchElementException is thrown in that case.
   * 
   * @return true if tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testDequeueEmpty() {
    // TODO complete the implementation of this tester method
    BoardingQueue newQueue = new BoardingQueue(5);

    try {
      newQueue.dequeue();
      return false;
    } catch (NoSuchElementException e) {

    }

    return true; // default return statement
  }

  /**
   * Tests the functionality of BoardingQueue.dequeue() method by calling dequeue() on a queue of
   * size one and ensures the method call returns the correct Passenger, size is zero, and the array
   * heap contains null references, only.
   * 
   * @return true if tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testDequeueBoardingQueueSizeOne() {
    // TODO complete the implementation of this tester method
    // [HINT] you can get a copy of the heap array by calling
    // BoardingQueue.toArray() method
    BoardingQueue newQueue = new BoardingQueue(5);
    Passenger p1 = new Passenger("p1", Group.A, true);
    newQueue.enqueue(p1);
    if (!newQueue.dequeue().equals(p1)) {
      return false;
    }

    if (newQueue.size() != 0) {
      return false;
    }

    Passenger[] actualArray = newQueue.toArray();

    for (int i = 0; i < 5; i++) {
      if (actualArray[i] != null) {
        return false;
      }
    }

    return true; // default return statement added to resolve compiler errors
  }

  /**
   * Tests the functionality of BoardingQueue.dequeue() when called on a non-empty boarding queue.
   * 
   * This tests ensures the dequeue() method removes, and returns the passenger with the highest
   * boarding priority in the queue, the size of the queue must be decremented by one, and the
   * contents of the heap array is as expected.
   * 
   * @return true if PriorityCareAdmissions.dequeue() returns the correct Passenger each time it is
   *         called and size is appropriately decremented, false otherwise
   */
  public static boolean testDequeueBoardingQueueNonEmpty() {
    // TODO complete the implementation of this tester method
    // [HINT] Try considering calling dequeue from a boarding queue whose size is at
    // least 6.
    // Consider cases where percolate-down recurses on left and right.
    // You can call dequeue multiple times to cover multiple operational cases of
    // percolate down
    // method (for instance percolate down can reach the bottom level of the heap or
    // not)
    BoardingQueue newQueue = new BoardingQueue(7);
    Passenger p1 = new Passenger("p1", Group.A, true);
    newQueue.enqueue(p1);
    Passenger p2 = new Passenger("p2", Group.A, true);
    newQueue.enqueue(p2);
    Passenger p3 = new Passenger("p3", Group.B, true);
    newQueue.enqueue(p3);
    Passenger p4 = new Passenger("p4", Group.B, true);
    newQueue.enqueue(p4);
    Passenger p5 = new Passenger("p5", Group.C, true);
    newQueue.enqueue(p5);

    if (!newQueue.dequeue().equals(p1)) {
      return false;
    }

    if (!newQueue.toArray()[0].equals(p2)) {
      return false;
    }

    if (!newQueue.toArray()[1].equals(p4)) {
      return false;
    }

    if (!newQueue.toArray()[2].equals(p3)) {
      return false;
    }

    if (!newQueue.toArray()[3].equals(p5)) {
      return false;
    }

    if (!newQueue.dequeue().equals(p2)) {
      return false;
    }

    if (!newQueue.toArray()[0].equals(p3)) {
      return false;
    }
    if (!newQueue.toArray()[1].equals(p4)) {
      return false;
    }
    if (!newQueue.toArray()[2].equals(p5)) {
      return false;
    }

    return true; // default return statement
  }

  /**
   * Tests the functionality of the clear() method. Should implement at least the following
   * scenarios:
   * 
   * - clear can be called on an empty queue with no errors.
   * 
   * - clear can be called on a non-empty queue with no errors.
   * 
   * After calling clear(), this tester ensures that the queue is empty, meaning its size is zero
   * and its heap array contains only null references.
   *
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testBoardingQueueClear() {
    // TODO complete the implementation of this tester method
    BoardingQueue newQueue = new BoardingQueue(7);

    newQueue.clear();

    Passenger p1 = new Passenger("p1", Group.A, true);
    newQueue.enqueue(p1);
    Passenger p2 = new Passenger("p2", Group.A, true);
    newQueue.enqueue(p2);
    Passenger p3 = new Passenger("p3", Group.B, true);
    newQueue.enqueue(p3);
    Passenger p4 = new Passenger("p4", Group.B, true);
    newQueue.enqueue(p4);
    Passenger p5 = new Passenger("p5", Group.C, true);
    newQueue.enqueue(p5);

    newQueue.clear();

    if (newQueue.isEmpty() == false) {
      return false;
    }

    if (newQueue.size() != 0) {
      return false;
    }

    Passenger[] actualArray = newQueue.toArray();

    for (int i = 0; i < 7; i++) {
      if (actualArray[i] != null) {
        return false;
      }
    }

    return true; // default return statement
  }

  /**
   * Main method to run this tester class.
   * 
   * @param args list of input arguments if any
   */
  public static void main(String[] args) {
    System.out.println("testPassengerCompareToDifferentGroup: "
        + (testPassengerCompareToDifferentGroup() ? "Pass" : "Failed!"));
    System.out.println("testPassengerCompareToSameGroupDifferentArrival: "
        + (testPassengerCompareToSameGroupDifferentArrival() ? "Pass" : "Failed!"));
    System.out.println("testPassengerCompareToSameGroupSameArrival: "
        + (testPassengerCompareToSameGroupSameArrival() ? "Pass" : "Failed!"));
    System.out.println(
        "testBoardingQueueConstructor: " + (testBoardingQueueConstructor() ? "Pass" : "Failed!"));
    System.out
        .println("testPeekBestEmptyQueue: " + (testPeekBestEmptyQueue() ? "Pass" : "Failed!"));
    System.out.println(
        "testPeekBestNonEmptyQueue: " + (testPeekBestNonEmptyQueue() ? "Pass" : "Failed!"));
    System.out
        .println("testEnqueueToEmptyQueue: " + (testEnqueueToEmptyQueue() ? "Pass" : "Failed!"));
    System.out.println(
        "testEnqueueToNonEmptyQueue: " + (testEnqueueToNonEmptyQueue() ? "Pass" : "Failed!"));
    System.out.println("testDequeueEmpty: " + (testDequeueEmpty() ? "Pass" : "Failed!"));
    System.out.println("testDequeueBoardingQueueSizeOne: "
        + (testDequeueBoardingQueueSizeOne() ? "Pass" : "Failed!"));
    System.out.println("testDequeueBoardingQueueNonEmpty: "
        + (testDequeueBoardingQueueNonEmpty() ? "Pass" : "Failed!"));
    System.out
        .println("testBoardingQueueClear: " + (testBoardingQueueClear() ? "Pass" : "Failed!"));
  }

}
