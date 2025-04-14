//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Iterator Forward
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
// Online Sources:  (identify each by URL and describe how it helped)
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Creates an iterator for a linkedList that starts from
 * its head
 */
public class IteratorFwd implements Iterator<LakeRecord> {
  private LinkedNode current; //The current node of the iterator

  /**
   * Construct the iterator
   *
   * @param start the node to start from
   */
  public IteratorFwd(LinkedNode start) {
    this.current = start;
  }

  /**
   * Determines if the iterator has a next node
   *
   * @return false if current is null
   * and true if current is not null
   */
  public boolean hasNext() {
    return current != null;
  }

  /**
   * Determines the next node and moves
   * the current node to the next node
   *
   * @return the next record
   * @throws NoSuchElementException if iterator has no next
   *                                record
   */
  public LakeRecord next() {
    if (hasNext()) {
      LakeRecord rec = this.current.getLakeRecord();
      this.current = current.getNext();
      return rec;
    } else {
      throw new NoSuchElementException("No next element");
    }
  }
}
