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

// TODO Add a complete File Header Here

/**
 * This class models Passenger objects ready to board an airplane.
 *
 */
public class Passenger implements Comparable<Passenger> { // TODO Define the Passenger class to
                                                          // implement the Comparable
                                                          // interface.
  // A Passenger MUST be compared to another Passenger, ONLY.

  // Data fields
  private static int orderGen = 1; // generator of arrival orders of passengers
  private String name; // name of this passenger
  private final int ARRIVAL_ORDER; // arrival order of this passenger
  private Group group; // boarding group assigned to this passenger
  private boolean isCheckedIn; // indicates whether this passenger was checked in before boarding
                               // the airplane

  // CONSTRUCTOR - PROVIDED
  /**
   * Constructs a new Passenger given their name, boarding group, and checkedin status
   * 
   * @param name        name to be assigned to this Passenger
   * @param group       boarding group to be assigned to this Passenger
   * @param isCheckedIn indicates whether this passenger was checked in, or not
   * @throws IllegalArgumentException if name is null or blank or if group is null
   */
  public Passenger(String name, Group group, boolean isCheckedIn) {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("name is null or blank!");
    }

    if (group == null) {
      throw new IllegalArgumentException("boarding group is null!");
    }

    this.name = name;
    this.group = group;
    this.isCheckedIn = isCheckedIn;
    this.ARRIVAL_ORDER = nextOrder();
  }

  // nextOrder() PROVIDED
  /**
   * Generates and returns the arrival order to be assigned to the next Passenger object to be
   * created
   * 
   * @return the next generated order
   */
  private static int nextOrder() {
    return orderGen++;
  }

  // resetPassengerOrder() PROVIDED
  /**
   * Resets the arrival order generated to 1. This method can be used for testing purposes, or to
   * reset the system.
   */
  protected static void resetPassengerOrder() {
    orderGen = 1;
  }

  // name() PROVIDED
  /**
   * Gets the name of this passenger
   * 
   * @return the name of this passenger
   */
  public String name() {
    return name;
  }

  // isCheckedIn() PROVIDED
  /**
   * Determines whether this passenger was checked in before boarding the airplane
   * 
   * @return true if this passenger was checked in
   */
  public boolean isCheckedIn() {
    return this.isCheckedIn;
  }

  // toString() PROVIDED
  /**
   * Returns a String representation of this Passenger in the following format:
   * 
   * <name> from Group <group>
   * 
   * @return a String representation of this passenger
   */
  public String toString() {
    return this.name + " from Group " + this.group;
  }

  /**
   * Indicates whether some other object is "equal to" this Passenger.
   * 
   * @param obj the reference object with which to compare.
   * @return true if obj is an instance of Passenger and has the exact same name, group, and arrival
   *         order as this Passenger, otherwise return false.
   */
  @Override
  public boolean equals(Object obj) {
    // TODO Complete the implementation of the Passenger.equals() method with
    // respect to the details
    // provided in its javadocs style method header
    Passenger passObj = (Passenger) obj;
    if (this.name.equals(passObj.name) && this.group.equals(passObj.group)
        && this.ARRIVAL_ORDER == passObj.ARRIVAL_ORDER) {
      return true;
    }

    return false; // default return statement
  }

  // TODO Add and implement the Passenger.compareTo() method as part of
  // implementation of the
  // Comparable interface.
  // Passenger.compareTo() method MUST take an input parameter of type Passenger.
  //
  // Passengers are compared with respect to the increasing order of their
  // boarding groups.
  // You can use Group.compareTo() method to compare boarding group constants.
  //
  // For instance, if we consider two Passengers p1 and p2 such as:
  // the boarding group of p1 is Group.A
  // the boarding group of p2 is Group.B
  // Then, we the following statements are true:
  // p1.compareTo(p2) < 0
  // p2.compareTo(p1) > 0

  // If two passengers have the same boarding groups, they are compared with
  // respect to the
  // increasing order of their arrival orders. If we consider two Passengers p1
  // and p2 such as:
  // Both p1 and p2 have the boarding group Group.B, for instance
  // the arrival order of p1 is 3
  // the arrival order of p2 is 7
  // Then, we the following statements are true:
  // p1.compareTo(p2) < 0
  // p2.compareTo(p1) > 0
  //
  // The Passenger.compareTo method returns:
  // (*) zero if this Passenger and the other input Passenger have the same
  // boarding group and the same
  // arrival order.
  // (*) a negative integer if this Passenger is less than the other input
  // Passenger and,
  // (*) a positive integer if this Passenger is greater than the other input
  // Passenger.
  public int compareTo(Passenger other) {
    int groupDiff = this.group.compareTo(other.group);
    if (groupDiff != 0) {
      return groupDiff;
    }
    int orderDiff = this.ARRIVAL_ORDER - other.ARRIVAL_ORDER;
    if (orderDiff != 0) {
      return orderDiff;
    }

    return 0;
  }

}
