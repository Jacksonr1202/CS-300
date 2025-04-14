//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    FreezeTracker Tester
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
// Online Sources:  N/a
//
///////////////////////////////////////////////////////////////////////////////

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Tester class for FreezeTracker functionality.
 */
public class FreezeTrackerTester {

  /**
   * Tests adding records to an empty FreezeTracker and the end of a non-empty FreezeTracker.
   * <p>
   * Ensure that size has been updated correctly, that the first and last winters are correct,
   * and that all added records are present in the correct locations in the list.
   *
   * @return true if all cases pass, false otherwise.
   */
  public static boolean testAdd() {
    FreezeTracker tracker = new FreezeTracker();
    LakeRecord record = new LakeRecord("2019", "December 2", "February 13", 30);
    tracker.add(record);
    if (!(tracker.size() == 1 && tracker.getHead().getLakeRecord().equals(record)
        && tracker.getTail().getLakeRecord().equals(record))) {
      return false;
    }
    LakeRecord record2 = new LakeRecord("2020", "December 4", "February 18", 28);
    tracker.add(record2);
    if (!(tracker.size() == 2 && tracker.getHead().getLakeRecord().equals(record)
        && tracker.getTail().getLakeRecord().equals(record2))) {
      return false;
    }
    LakeRecord record3 = new LakeRecord("2021", "December 7", "February 25", 25);
    tracker.add(record3);
    if (!(tracker.size() == 3 && tracker.getHead().getLakeRecord().equals(record)
        && tracker.getTail().getLakeRecord().equals(record3)
        && tracker.getHead().getNext().getLakeRecord().equals(record2))) {
      return false;
    }
    return true;
  }

  /**
   * Tests removing records from different positions (beginning, middle, end). Your initial
   * FreezeTracker list should contain AT LEAST five records; none of these tests will clear
   * out the list (that's a different test, below).
   * <p>
   * Verify both return values and the list state.
   *
   * @return true if all cases pass, false otherwise.
   */
  public static boolean testRemove() {
    FreezeTracker tracker = new FreezeTracker();
    LakeRecord record1 = new LakeRecord("2013", "Dec 5", "Feb 5", 25);
    LakeRecord record2 = new LakeRecord("2014", "Dec 5", "Feb 5", 25);
    LakeRecord record3 = new LakeRecord("2015", "Dec 5", "Feb 5", 25);
    LakeRecord record4 = new LakeRecord("2016", "Dec 5", "Feb 5", 25);
    LakeRecord record5 = new LakeRecord("2017", "Dec 5", "Feb 5", 25);
    tracker.add(record1);
    tracker.add(record2);
    tracker.add(record3);
    tracker.add(record4);
    tracker.add(record5);
    if (!tracker.remove(record3)) {
      return false;
    }
    if (tracker.size() != 4) {
      return false;
    }
    if (!tracker.getHead().getNext().getNext().getLakeRecord().equals(record4)) {
      return false;
    }
    if (!tracker.getTail().getPrev().getLakeRecord().equals(record4)) {
      return false;
    }

    return true;
  }

  /**
   * Tests removing the ONLY value from a FreezeTracker.
   * <p>
   * Ensure all accessor methods behave correctly after removing it.
   *
   * @return true if all cases pass, false otherwise.
   */
  public static boolean testRemoveOnly() {
    FreezeTracker track = new FreezeTracker();
    LakeRecord rec = new LakeRecord("2015-2016", "Dec 5", "Feb 8", 65);
    track.add(rec);

    boolean remove = track.remove(rec);
    if (!remove) {
      return false;
    }
    if (track.size() != 0 || !track.isEmpty() || track.getHead() != null || track.getTail() != null) {
      return false;
    }
    return true;
  }

  /**
   * Tests removing a record from FreezeTracker which is not present in the list.
   * <p>
   * Verify both the return value and the list state.
   *
   * @return true if all cases pass, false otherwise.
   */
  public static boolean testRemoveDoesNotExist() {
    FreezeTracker track = new FreezeTracker();
    LakeRecord rec1 = new LakeRecord("2013-2014", "Dec 5", "Feb 5", 60);
    LakeRecord rec2 = new LakeRecord("2014-2015", "Dec 7", "Feb 8", 62);
    LakeRecord rec3 = new LakeRecord("2015-2016", "Dec 10", "Feb 15", 66);
    track.add(rec1);
    track.add(rec2);
    track.add(rec3);
    LakeRecord notInList = new LakeRecord("1999-2000", "Dec 1", "Feb 2", 50);
    if (track.remove(notInList) || track.size() != 3) {
      return false;
    }
    return true;
  }

  /**
   * Tests iterators (forward and backward). For full credit, this test MUST contain at
   * least one enhanced for loop with each type of iterator.
   *
   * @return true if all cases pass, false otherwise.
   */
  public static boolean testIterators() {
    FreezeTracker tracker = new FreezeTracker();
    LakeRecord rec1 = new LakeRecord("2001-2002", "Dec 5", "Feb 5", 60);
    LakeRecord rec2 = new LakeRecord("2002-2003", "Dec 7", "Feb 8", 62);
    LakeRecord rec3 = new LakeRecord("2003-2004", "Dec 10", "Feb 15", 66);
    tracker.add(rec1);
    tracker.add(rec2);
    tracker.add(rec3);
    tracker.setReversed(false);
    StringBuilder forward = new StringBuilder();
    for (LakeRecord lr : tracker) {
      forward.append(lr.getWinter()).append(" ");
    }
    if (!forward.toString().equals("2001-2002 2002-2003 2003-2004 ")) {
      return false;
    }
    tracker.setReversed(true);
    StringBuilder backward = new StringBuilder();
    for (LakeRecord lr : tracker) {
      backward.append(lr.getWinter()).append(" ");
    }
    if (!backward.toString().equals("2003-2004 2002-2003 2001-2002 ")) {
      return false;
    }
    return true;
  }

  /**
   * Tests merging multiple freeze records (provided!) for the same winter.
   * <p>
   * Add these records to a FreezeTracker and verify that merging them results in a list
   * with a single record with the correct values.
   *
   * @return true if all cases pass, false otherwise.
   */
  public static boolean testMergeWinters() {
    LakeRecord freeze1 = new LakeRecord("2019-2020", "December 1", "January 15", 45);
    LakeRecord freeze2 = new LakeRecord("2019-2020", "January 20", "March 10", 50);
    LakeRecord freeze3 = new LakeRecord("2019-2020", "March 15", "April 5", 20);
    FreezeTracker track = new FreezeTracker();

    track.add(freeze1);
    track.add(freeze2);
    track.add(freeze3);
    track.mergeWinters();
    if (track.size() != 1) {
      return false;
    }
    LakeRecord merged = track.getHead().getLakeRecord();
    if (!"December 1".equals(merged.getFreezeDate())) {
      return false;
    }
    if (!"April 5".equals(merged.getThawDate())) {
      return false;
    }
    if (merged.getDaysOfIceCover() != (45 + 50 + 20)) {
      return false;
    }
    return true;
  }

  /**
   * Tests cleaning the dataset (removing incomplete records). Create a FreezeTracker with
   * some valid and invalid records, and verify that all of the invalid records are removed
   * (and none of the valid ones).
   *
   * @return true if all cases pass, false otherwise.
   */
  public static boolean testCleanData() {
    FreezeTracker track = new FreezeTracker();
    LakeRecord missingDuration = new LakeRecord("2001-2002", "December 15", "February 1", LakeRecord.MISSING);
    LakeRecord missingFreeze = new LakeRecord("2002-2003", null, "February 10", 65);
    LakeRecord missingThaw = new LakeRecord("2003-2004", "December 10", null, 70);
    LakeRecord missingAll = new LakeRecord("2004-2005", null, null, LakeRecord.MISSING);
    LakeRecord valid = new LakeRecord("2000-2001", "December 5", "February 5", 60);
    track.add(valid);
    track.add(missingDuration);
    track.add(missingFreeze);
    track.add(missingThaw);
    track.add(missingAll);

    track.removeIncompleteRecords();
    if (track.size() != 2) {
      return false;
    }
    track.updateDurations();
    LinkedNode n = track.getHead();
    if (n == null) {
      return false;
    }
    LakeRecord firstRec = n.getLakeRecord();
    LinkedNode secondNode = n.getNext();
    if (secondNode == null) {
      return false;
    }
    LakeRecord secondRec = secondNode.getLakeRecord();
    boolean firstValid = firstRec.equals(valid);
    boolean firstMissing = firstRec.equals(missingDuration);
    boolean secondValid = secondRec.equals(valid);
    boolean secondMissing = secondRec.equals(missingDuration);
    if (!((firstValid && secondMissing) || (firstMissing && secondValid))) {
      return false;
    }
    LakeRecord missingRec;
    if (firstMissing) {
      missingRec = firstRec;
    } else {
      missingRec = secondRec;
    }
    if (missingRec.getDaysOfIceCover() == LakeRecord.MISSING) {
      return false;
    }
    return true;
  }

  /**
   * Tests computing the average freeze duration.
   *
   * @return true if all cases pass, false otherwise.
   */
  public static boolean testAverageFreezeDuration() {
    FreezeTracker track = new FreezeTracker();
    LakeRecord r1 = new LakeRecord("2010-2011", "December 6", "February 21", 75);
    LakeRecord r2 = new LakeRecord("2011-2012", "December 11", "February 17", 75);
    LakeRecord r3 = new LakeRecord("2012-2013", "December 15", "February 11", 80);
    track.add(r1);
    track.add(r2);
    track.add(r3);

    float expected = 76.6667f;
    float actual = track.getAverageFreezeDuration();
    if (Math.abs(expected - actual) > 0.001) {
      return false;
    }
    return true;
  }

  /**
   * Tests finding the maximum number of days of ice cover in a single winter.
   *
   * @return true if all cases pass, false otherwise.
   */
  public static boolean testMaxFreezeDuration() {
    FreezeTracker track = new FreezeTracker();
    LakeRecord r1 = new LakeRecord("2010-2011", "December 6", "February 21", 77);
    LakeRecord r2 = new LakeRecord("2011-2012", "December 11", "February 17", 78);
    LakeRecord r3 = new LakeRecord("2012-2013", "December 15", "February 11", 58);
    track.add(r1);
    track.add(r2);
    track.add(r3);
    if (track.getMaxFreezeDuration() != 78) {
      return false;
    }
    return true;
  }

  /**
   * Tests finding the minimum number of days of ice cover in a single winter.
   *
   * @return true if all cases pass, false otherwise.
   */
  public static boolean testMinFreezeDuration() {
    FreezeTracker track = new FreezeTracker();
    LakeRecord r1 = new LakeRecord("2010-2011", "December 6", "February 21", 77);
    LakeRecord r2 = new LakeRecord("2011-2012", "December 11", "February 17", 78);
    LakeRecord r3 = new LakeRecord("2012-2013", "December 15", "February 11", 58);
    track.add(r1);
    track.add(r2);
    track.add(r3);
    if (track.getMinFreezeDuration() != 58) {
      return false;
    }
    return true;
  }

  /**
   * Tests finding the earliest freeze.
   *
   * @return true if all cases pass, false otherwise.
   */
  public static boolean testGetEarliestFreeze() {
    FreezeTracker track = new FreezeTracker();
    LakeRecord r1 = new LakeRecord("2010-2011", "December 6", "February 21", 77);
    LakeRecord r2 = new LakeRecord("2011-2012", "December 11", "February 17", 78);
    LakeRecord r3 = new LakeRecord("2012-2013", "December 15", "February 11", 58);
    track.add(r1);
    track.add(r2);
    track.add(r3);
    if (!"December 6".equals(track.getEarliestFreeze())) {
      return false;
    }
    return true;
  }

  /**
   * Tests finding the latest thaw.
   *
   * @return true if all cases pass, false otherwise.
   */
  public static boolean testGetLatestThaw() {
    FreezeTracker track = new FreezeTracker();
    LakeRecord r1 = new LakeRecord("2010-2011", "December 6", "February 21", 77);
    LakeRecord r2 = new LakeRecord("2011-2012", "December 11", "February 17", 78);
    LakeRecord r3 = new LakeRecord("2012-2013", "December 15", "February 11", 58);
    track.add(r1);
    track.add(r2);
    track.add(r3);
    if (!"February 21".equals(track.getLatestThaw())) {
      return false;
    }
    return true;
  }

  /**
   * Tests filtering freeze records by a range of years.
   * <p>
   * Ensure that only records between the specified years (inclusive) are present in the result.
   *
   * @return true if all cases pass, false otherwise.
   */
  public static boolean testFilterByYear() {
    FreezeTracker track = new FreezeTracker();
    LakeRecord r1 = new LakeRecord("2010-2011", "December 6", "February 21", 77);
    LakeRecord r2 = new LakeRecord("2011-2012", "December 11", "February 17", 78);
    LakeRecord r3 = new LakeRecord("2015-2016", "December 15", "February 11", 58);
    track.add(r1);
    track.add(r2);
    track.add(r3);
    FreezeTracker filter = track.filterByYear(2010, 2011);
    if (filter.size() != 2) {
      return false;
    }
    FreezeTracker filter2 = track.filterByYear(2015);
    if (filter2.size() != 1 || !filter2.get(0).equals(r3)) {
      return false;
    }
    return true;
  }

  /**
   * Tests filtering freeze records by a range of ice cover duration values.
   * <p>
   * Ensure that only records within the duration range are included in the filtered list.
   *
   * @return true if all cases pass, false otherwise.
   */
  public static boolean testFilterByDuration() {
    FreezeTracker track = new FreezeTracker();
    LakeRecord r1 = new LakeRecord("2010-2011", "December 6", "February 21", 77);
    LakeRecord r2 = new LakeRecord("2011-2012", "December 11", "February 17", 78);
    LakeRecord r3 = new LakeRecord("2012-2013", "December 15", "February 11", 58);
    track.add(r1);
    track.add(r2);
    track.add(r3);
    FreezeTracker filter = track.filterByDuration(58, 77);
    if (filter.size() != 2 || !filter.get(0).equals(r1) || !filter.get(1).equals(r3)) {
      return false;
    }
    FreezeTracker empty = track.filterByDuration(300, 400);
    if (empty.size() != 0) {
      return false;
    }
    return true;
  }

  /**
   * Main Method to Launch the tester methods.
   *
   * @param args list of inputs if any.
   */
  public static void main(String[] args) {
    System.out.println("Running tests:");
    System.out.println("testAdd(): " + (testAdd() ? "PASSED" : "FAILED"));
    System.out.println("testRemove(): " + (testRemove() ? "PASSED" : "FAILED"));
    System.out.println("testRemoveOnly(): " + (testRemoveOnly() ? "PASSED" : "FAILED"));
    System.out.println("testRemoveDoesNotExist(): " + (testRemoveDoesNotExist() ? "PASSED" : "FAILED"));
    System.out.println("testIterators(): " + (testIterators() ? "PASSED" : "FAILED"));
    System.out.println("testMergeWinters(): " + (testMergeWinters() ? "PASSED" : "FAILED"));
    System.out.println("testCleanData(): " + (testCleanData() ? "PASSED" : "FAILED"));
    System.out.println("testAverageFreezeDuration(): " + (testAverageFreezeDuration() ? "PASSED" : "FAILED"));
    System.out.println("testMaxFreezeDuration(): " + (testMaxFreezeDuration() ? "PASSED" : "FAILED"));
    System.out.println("testMinFreezeDuration(): " + (testMinFreezeDuration() ? "PASSED" : "FAILED"));
    System.out.println("testGetEarliestFreeze(): " + (testGetEarliestFreeze() ? "PASSED" : "FAILED"));
    System.out.println("testGetLatestThaw(): " + (testGetLatestThaw() ? "PASSED" : "FAILED"));
    System.out.println("testFilterByYear(): " + (testFilterByYear() ? "PASSED" : "FAILED"));
    System.out.println("testFilterByDuration(): " + (testFilterByDuration() ? "PASSED" : "FAILED"));

    boolean allTestsPassed = testAdd() && testRemove() && testRemoveOnly()
        && testRemoveDoesNotExist() && testIterators() && testMergeWinters() && testCleanData()
        && testAverageFreezeDuration() && testMaxFreezeDuration() && testMinFreezeDuration()
        && testGetEarliestFreeze() && testGetLatestThaw()
        && testFilterByYear() && testFilterByDuration();
    System.out.println("ALL TESTS: " + (allTestsPassed ? "PASSED" : "FAILED"));
  }
}
