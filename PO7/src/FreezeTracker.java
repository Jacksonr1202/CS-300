//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    FreezeTracker
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
// https://www.w3schools.com/java/java_iterator.asp helped me understand iterators
// and how to implement for loops
// https://codegym.cc/groups/posts/integermax_value-in-java-with-examples
// how to get the max value of integer
///////////////////////////////////////////////////////////////////////////////

import java.util.ArrayList;
import java.util.Iterator;

/**
 * A doubly-linked list implementation for managing freeze-thaw records of Lake Mendota. Implements
 * ListADT and Iterable, providing operations for adding, removing, merging, and analyzing freeze
 * data.
 */
public class FreezeTracker implements ListADT<LakeRecord>, Iterable<LakeRecord> {
  /**
   * Pointer to head of the linked list.
   */
  private LinkedNode head; //Head pointer of list
  /**
   * Pointer to tail of the linked list.
   */
  private LinkedNode tail; //Tail pointer of list
  /**
   * Number of elements in the list.
   */
  private int size; //Represent amount of record in list
  /**
   * Whether to traverse the list is reverse-chronological order.
   */
  private boolean reversed; //Determines if the list is reversed

  /**
   * Constructs an empty FreezeTracker.
   */
  public FreezeTracker() {
    this.head = null;
    this.reversed = false;
    this.tail = null;
    this.size = 0;
  }

  /**
   * Constructs a FreezeTracker and initializes it with an ArrayList of LakeRecords. This
   * constructor processes the provided dataset. After this process, the linked list will contain
   * exactly one cleaned and merged record per winter.
   *
   * @param records The list of LakeRecord objects read from FreezeData.csv. This list may contain
   *                missing or duplicate entries, which are handled during initialization.
   */
  public FreezeTracker(ArrayList<LakeRecord> records) {
    for (LakeRecord rec : records) {
      add(rec);
      //Iterate through record in array and add them
    }
    removeIncompleteRecords();
    updateDurations();
    mergeWinters();
  }

  /**
   * Returns the number of records in the list.
   *
   * @return The size of the list.
   */
  @Override
  public int size() {
    return this.size;
  }

  /**
   * Checks if the list is empty.
   *
   * @return True if the list is empty, false otherwise.
   */
  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Clears all records from the list.
   */
  @Override
  public void clear() {
    this.head = null;
    this.tail = null;
    this.size = 0;
  }

  /**
   * Specifies which direction the list should be traversed in the future
   *
   * @param reversed whether to traverse the list backwards
   */
  public void setReversed(boolean reversed) {
    this.reversed = reversed;
  }

  /**
   * Getter method for head
   *
   * @return head of the linked list
   */
  public LinkedNode getHead() {
    return this.head;
  }

  /**
   * Getter method for tail
   *
   * @return tail of the linked list
   */
  public LinkedNode getTail() {
    return this.tail;
  }


  /**
   * Appends a new freeze record to the end of the linked list in O(1) time.
   *
   * <br><br> Note: This method is closely related to the learning objectives of the assignment, and so we'll
   * pay special attention to it during manual grading. Be sure to leave comments explaining
   * each algorithmic step you use!
   *
   * @param record The record to add.
   */
  @Override
  public void add(LakeRecord record) {
    LinkedNode newNode = new LinkedNode(record);
    if (this.size == 0) {
      this.head = newNode;
      this.tail = newNode;
      this.size++;
      return;
      //Empty lists should add new node as tail and head
    }
    tail.setNext(newNode);
    newNode.setPrev(tail);
    tail = newNode;
    size++;
  }

  /**
   * Removes the given node from the linked list in O(1) time. Note: this method does not
   * verify that the given node is a member of the list, and should only be used as a helper
   * function inside the FreezeTracker class.
   *
   * <br><br> Note: This method is closely related to the learning objectives of the assignment, and so we'll
   * pay special attention to it during manual grading. Be sure to leave comments explaining
   * each algorithmic step you use!
   *
   * @param node the node to be removed
   * @throws IllegalArgumentException if node is null
   */
  private void removeNode(LinkedNode node) {
    if (node == null) {
      throw new IllegalArgumentException("ERROR:Node is null");
    }
    if (node == head && node == tail) {
      head = null;
      tail = null;
      size = 0;
      return;
      //if only one record must deal with it accordingly
    }
    if (node == tail) {
      tail = tail.getPrev();
      tail.setNext(null);
      size--;
      return;
    }
    if (this.head.equals(node)) {
      head = head.getNext();
      head.setPrev(null);
      size--;
      return;
    }
    LinkedNode b = node.getPrev();
    LinkedNode a = node.getNext();
    b.setNext(a);
    a.setPrev(b);
    size--;
  }

  /**
   * Removes the first node in the list that contains the given record.
   *
   * <br><br> Note: This method is closely related to the learning objectives of the assignment, and so we'll
   * pay special attention to it during manual grading. Be sure to leave comments explaining
   * each algorithmic step you use!
   *
   * @param record the record to be removed
   * @return boolean indicating whether the record was found in the list
   */
  @Override
  public boolean remove(LakeRecord record) {
    LinkedNode n = find(record);
    try {
      removeNode(n);
      return true;
    } catch (IllegalArgumentException e) {
      return false;
    }
  }

  /**
   * Finds the given record in the list
   *
   * @param record the LakeRecord to search for
   * @return The first LinkedNode containing the given record, or null if none exists
   */
  public LinkedNode find(LakeRecord record) {
    LinkedNode currNode = head;
    while (currNode != null) {
      if (currNode.getLakeRecord().equals(record)) {
        return currNode;
      }
      currNode = currNode.getNext();
      //Check if the current node is the target and if not move to next node
    }
    return null;
  }

  /**
   * Returns the LakeRecord at index i in the list, using zero-indexing.
   *
   * @param i a non-negative integer
   * @return The LakeRecord at the given index
   * @throws IndexOutOfBoundsException if i is negative or greater than size()-1
   */
  public LakeRecord get(int i) {
    if (i < 0 || i > this.size() - 1) {
      throw new IndexOutOfBoundsException("ERROR:Index out of bounds");
    }
    LinkedNode currNode = this.head;
    for (int j = 0; j < i; j++) {
      currNode = currNode.getNext();
      //Iterate according to i parameter to return desired record
    }
    return currNode.getLakeRecord();
  }

  /**
   * Provides an iterator for traversal. The direction of traversal is head-to-tail
   * if this.reversed is false, and tail-to-head otherwise.
   *
   * @return An iterator traversing the list.
   */
  @Override
  public Iterator<LakeRecord> iterator() {
    if (this.reversed) {
      return new IteratorBwd(this.tail);
    } else {
      return new IteratorFwd(this.head);
    }
  }


  /**
   * Removes all nodes with missing freeze or thaw dates
   */
  public void removeIncompleteRecords() {
    LinkedNode curr = head;
    while (curr != null) {
      LinkedNode next = curr.getNext();
      if (!curr.getLakeRecord().hasCompleteData()) {
        removeNode(curr);
      }
      curr = next;
      //Iterate and remove records that are incomplete
    }
  }

  /**
   * Fixes all LakeRecords contained in this list with incorrect durations
   * (Hint: LakeRecord already has a method for this!)
   */
  public void updateDurations() {
    LinkedNode curr = head;
    while (curr != null) {
      curr.getLakeRecord().updateDuration();
      curr = curr.getNext();
    }
  }

  /**
   * Merges consecutive nodes containing records from the same winter. The merged node contains
   * a single record with the earliest freeze date, the latest thaw date, and the total number of
   * days of ice cover. Note that merging discards the middle thaw and freeze dates, and so this
   * method should only be used after calling updateDurations().
   *
   * <br><br> Note: This method is closely related to the learning objectives of the assignment, and so we'll
   * pay special attention to it during manual grading. Be sure to leave comments explaining
   * each algorithmic step you use!
   */
  public void mergeWinters() {
    LinkedNode curr = head;
    while (curr != null) {
      LinkedNode next = curr.getNext();
      //Create a node represent the node after the current node
      while (next != null
          && curr.getLakeRecord().getWinter().equals(next.getLakeRecord().getWinter())) {
        curr.getLakeRecord().mergeWith(next.getLakeRecord());
        removeNode(next);
        next = curr.getNext();
        //Keeps iterating and merging until winters are not the same and current
        //node is updated to the next node. Removes the next node so the next node updates
        //to the correct node
      }
      curr = next;
    }
  }

  /**
   * Returns a new linked list containing all the records falling between year1 and
   * year 2, inclusive. The returned list should not contain any references to nodes
   * or records from the original list, and the relative ordering of nodes should not change.
   *
   * @param year1 minimum allowable year for the new list
   * @param year2 maximum allowable year for the new list
   * @return a new, filtered linked list covering the given range of years.
   */
  public FreezeTracker filterByYear(int year1, int year2) {
    FreezeTracker filtList = new FreezeTracker();
    for (LakeRecord rec : this) {
      if (rec.getYear() >= year1 && rec.getYear() <= year2) {
        filtList.add(rec.copy());
      }
      //If year is within bounds record copy is added
    }
    return filtList;
  }

  /**
   * Returns a new linked list containing all of the records from the given year. The returned
   * list should not contain any references to nodes or records from the original list, and the
   * relative ordering of nodes should not change.
   *
   * @param year the single year covered by the new list
   * @return a new linked list containing only nodes from the given year
   */
  public FreezeTracker filterByYear(int year) {
    return filterByYear(year, year);
  }

  /**
   * Returns a new linked list containing all of the records whose total days of ice cover are
   * between low and high, inclusive. The returned list should not contain any references to
   * nodes or records from the original list, and the relative ordering of nodes should not change.
   *
   * @param low  The minimum allowed duration for the new list
   * @param high The maximum allowed duration for the new list
   * @return a new list containing only records with duration in the given range
   */
  public FreezeTracker filterByDuration(int low, int high) {
    FreezeTracker filtList = new FreezeTracker();
    for (LakeRecord rec : this) {
      if (rec.getDaysOfIceCover() >= low && rec.getDaysOfIceCover() <= high) {
        filtList.add(rec.copy());
      }
      //If record duration is within bounds copy is added
    }
    return filtList;
  }


  /**
   * Finds the latest date at which the lake thawed.
   *
   * @return The date of the latest thaw, e.g. "April 15"
   */
  public String getLatestThaw() {
    String late = null;
    if (size == 0) {
      return late;
    }
    for (LakeRecord rec : this) {
      if (rec.getThawDate() != null) {
        if (late == null || Date.compareDates(rec.getThawDate(), late) > 0) {
          late = rec.getThawDate();
        }
      }
      //Makes sure the record for thatDate is not null and if date is later
      //than the current latest it is updated
    }
    return late;
  }

  /**
   * Finds the earliest date at which the lake froze.
   *
   * @return The day of the earliest freeze, e.g. "December 2"
   */
  public String getEarliestFreeze() {
    String early = null;
    if (size == 0) {
      return early;
    }
    for (LakeRecord rec : this) {
      if (rec.getFreezeDate() != null) {
        if (early == null || Date.compareDates(rec.getFreezeDate(), early) < 0) {
          early = rec.getFreezeDate();
        }
      }
      //Makes sure freeze date record is not null and if it is earlier
      //than the current earliest it is updated
    }
    return early;
  }

  /**
   * Finds the average (arithmetic mean) number of days of ice cover across the entire list
   *
   * @return The average number of days of ice cover across all nodes, or 0 if list is empty.
   */
  public float getAverageFreezeDuration() {
    if (size == 0) {
      return 0;
    }
    float sum = 0;
    for (LakeRecord rec : this) {
      sum += rec.getDaysOfIceCover();
      //Iterating and continually updating the sum
    }
    return sum / size;
  }

  /**
   * Finds the maximum number of days of ice cover across the entire list
   *
   * @return The maximum number of days of ice cover across all nodes, or 0 if the list is empty.
   */
  public int getMaxFreezeDuration() {
    if (size == 0) {
      return 0;
    }
    int maxDays = 0;
    for (LakeRecord rec : this) {
      if (rec.getDaysOfIceCover() > maxDays) {
        maxDays = rec.getDaysOfIceCover();
      }
      //If the days of ice cover of the current iteration
      //is greater than the current max it is updated
    }
    return maxDays;
  }

  /**
   * Finds the minimum number of days of ice cover across the entire list
   *
   * @return The minimum number of days of ice cover across all nodes, or 0 if the list is empty.
   */
  public int getMinFreezeDuration() {
    if (size == 0) {
      return 0;
    }
    int minDays = Integer.MAX_VALUE;
    for (LakeRecord rec : this) {
      if (rec.getDaysOfIceCover() < minDays) {
        minDays = rec.getDaysOfIceCover();
      }
      //If the days of ice cover of the current iteration
      //is less than the current mininum it is updated
    }
    return minDays;
  }

  /**
   * Creates a string representation of the tracker with each node on a new line.
   * The order of the nodes depends on whether the string is currently reversed.
   *
   * @return a String representation of the list
   */
  public String toString() {
    StringBuilder s = new StringBuilder();
    for (LakeRecord rec : this) {
      s.append(rec.toString()).append("\n");
      //Iterates through the records and appends them to the
      //StringBuilder with a new line
    }
    return s.toString();
  }
}
