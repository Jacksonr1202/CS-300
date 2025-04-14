//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    LinkedNode
// Course:   CS 300 Spring 2025
//
// Author:   Jackson Ryan
// Email:    jdryan7@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: N/A
// Partner Email: N/A
// Partner Lecturer's Name: N/A
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   _X__ Write-up states that pair programming is allowed for this assignment.
//   _N/A We have both read and understand the course Pair Programming Policy.
//   _N/A__ We have registered our team prior to the team registration deadline.
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons:   Hobbes(Lectures)
// Online Sources:
//
// https://www.simplilearn.com/tutorials/java-tutorial/linked-list-in-java helped me better
// understand how to implement linked nodes
///////////////////////////////////////////////////////////////////////////////

/**
 * Instantiable class representing a single node in a doubly-linked list of LakeRecords.
 */
public class LinkedNode {

  /**
   * The LakeRecord stored in this node
   */
  private LakeRecord data;

  /**
   * A reference to the previous node in this linked list
   */
  private LinkedNode prev;

  /**
   * A reference to the next node in this linked list
   */
  private LinkedNode next;

  /**
   * Creates a new LinkedNode with the given data, previous and next nodes
   *
   * @param data the LakeRecord to be contained in this node
   * @param prev a reference to the previous node in this list (may be null)
   * @param next a reference to the next node in this list (may be null)
   */
  public LinkedNode(LakeRecord data, LinkedNode prev, LinkedNode next) {
    this.data = data;
    this.prev = prev;
    this.next = next;
  }

  /**
   * Creates a new LinkedNode with the given data which is not currently linked to any other nodes
   *
   * @param data the data to be contained in this node
   */
  public LinkedNode(LakeRecord data) {
    this.data = data;
    this.prev = null;
    this.next = null;
  }

  /**
   * Accesses the LakeRecord stored in this node
   *
   * @return the LakeRecord stored in this node
   */
  public LakeRecord getLakeRecord() {
    return this.data;
  }

  /**
   * Accesses the previous node in the list
   *
   * @return a reference to the previous node in this list (may be null)
   */
  public LinkedNode getPrev() {
    return this.prev;
  }

  /**
   * Updates the previous node for this node
   *
   * @param newPrev the new previous node in this list (may be null)
   */
  public void setPrev(LinkedNode newPrev) {
    this.prev = newPrev;
  }

  /**
   * Accesses the next node in the list
   *
   * @return a reference to the next node in this list (may be null)
   */
  public LinkedNode getNext() {
    return this.next;
  }

  /**
   * Updates the next node for this node
   *
   * @param newNext the new next node in this list (may be null)
   */
  public void setNext(LinkedNode newNext) {
    this.next = newNext;
  }
}
