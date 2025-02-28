//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Calendar Tester
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
//   _N/A__ Write-up states that pair programming is allowed for this assignment.
//   _N/A__ We have both read and understand the course Pair Programming Policy.
//   _N/A__ We have registered our team prior to the team registration deadline.
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons: Lectures
// Online Sources: Zybooks
//
//
///////////////////////////////////////////////////////////////////////////////

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Utility class that defines tester methods for p04 Monthly Calendar.
 */
public class CalendarTester {
  /**
   * Ensures the correctness of the constructor and getter methods defined in the Event class when
   * no exception is expected to be thrown.
   *
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   * detected
   */
  public static boolean testEventConstructorAndGettersValidBehavior() {
    Event event1 = new Event("Football", 1, 0, 0);
    if (!event1.getDescription().equals("Football") || event1.getDay() != 1 || !event1.getStartTimeAsString().equals("00:00")) {
      return false;
    }
    Event event2 = new Event("Soccer", 31, 23, 59);
    if (!event2.getDescription().equals("Soccer") || event2.getDay() != 31 || !event2.getStartTimeAsString().equals("23:59")) {
      return false;
    }
    return true;
  }

  /**
   * Ensures the correctness of the constructor of the Event class when it is passed invalid
   * inputs.
   *
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   * detected
   */
  public static boolean testEventConstructorThrowingExceptions() {
    try {
      Event event2 = new Event(null, 3, 6, 35);
      return false;
    } catch (IllegalArgumentException exception) {
      System.out.println("Passed");
    }
    try {
      Event event1 = new Event("   ", 3, 6, 35);
      return false;
    } catch (IllegalArgumentException exception) {
      System.out.println("Passed");
    }
    try {
      Event event1 = new Event("Football", 0, 6, 35);
      return false;
    } catch (IllegalArgumentException exception) {
      System.out.println("Passed");
    }
    try {
      Event event1 = new Event("Football", 32, 6, 35);
      return false;
    } catch (IllegalArgumentException exception) {
      System.out.println("Passed");
    }
    try {
      Event event1 = new Event("Football", 1, 24, 35);
      return false;
    } catch (IllegalArgumentException exception) {
      System.out.println("Passed");
    }
    try {
      Event event1 = new Event("Football", 1, -1, 35);
      return false;
    } catch (IllegalArgumentException exception) {
      System.out.println("Passed");
    }
    try {
      Event event1 = new Event("Football", 1, 6, -1);
      return false;
    } catch (IllegalArgumentException exception) {
      System.out.println("Passed");
    }
    try {
      Event event1 = new Event("Football", 1, 6, 60);
      return false;
    } catch (IllegalArgumentException exception) {
      System.out.println("Passed");
    }
    return true;
  }

  /**
   * Ensures the correctness of the Event.compareTo() method.
   *
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   * detected
   */
  public static boolean testEventCompareTo() {
    Event e1 = new Event("Doctor", 2, 5, 25);
    Event e2 = new Event("Dentist", 2, 7, 25);
    if (!(e1.compareTo(e2) < 0) || !(e2.compareTo(e1) > 0) || !(e1.compareTo(e1) == 0)) {
      return false;
    }
    Event e3 = new Event("Doctor", 2, 7, 25);
    Event e4 = new Event("Dentist", 2, 7, 25);
    if (e3.compareTo(e4) == 0 && e4.compareTo(e3) == 0) {
      return true;
    }
    return false;
  }

  /**
   * Ensures the correctness of the Event.equals() method.
   *
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   * detected
   */
  public static boolean testEventEquals() {
    Event e1 = new Event("Football", 1, 3, 25);
    Event e2 = new Event("Football", 1, 3, 25);
    if (!(e1.equals(e2))) {
      return false;
    }
    if (e1.equals(e1.toString())) {
      return false;
    }
    if (e1.equals(null)) {
      return false;
    }
    Event e3 = new Event("Basketball", 1, 3, 25);
    Event e4 = new Event("Baseball", 1, 3, 25);
    if (e3.equals(e4)) {
      return false;
    }
    return true;
  }

  /**
   * Ensures the correctness of the MonthCalendar.addEvent() method when valid inputs are provided
   * and no duplicate event exists for the day, ensuring the event is added correctly to the
   * specified day's list.
   *
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   * detected
   */
  public static boolean testSuccessfulAddEvent() {
    // TODO:
    // 1. Create a MonthCalendar instance for a valid month and year.
    MonthCalendar newCalendar = new MonthCalendar(2025, 2);
    // 2. Add multiple events on the same day with different descriptions and different start times.
    boolean add1 = newCalendar.addEvent(5, "Football", 8, 25);
    System.out.println(add1);
    boolean add2 = newCalendar.addEvent(5, "Soccer", 7, 35);
    System.out.println(add2);
    boolean add3 = newCalendar.addEvent(5, "Doctor", 5, 30);
    System.out.println(add3);
    // 3. Verify that addEvent() returns true when called with valid inputs and no duplicate event
    // exists in the day's event list.
    if (!add1 || !add2 || !add3) {
      return false;
    }
    System.out.println("Good 1");
    // 4. Use getEvents() to verify:
    //    - Events of the same day are sorted in ascending order based on Event.compareTo().
    //    - If two events have the same start time (and different descriptions) , they retain
    //    their order of addition.
    ArrayList<Event>[] deepCopy = newCalendar.getEvents();
    if (!((deepCopy[4].get(0).compareTo(deepCopy[4].get(1)) < 0) && (deepCopy[4].get(1).compareTo(deepCopy[4].get(2)) < 0))) {
      return false;
    }
    System.out.println("Good 2");
    newCalendar.addEvent(5, "Basketball", 7, 35);
    deepCopy = newCalendar.getEvents();
    String target1 = deepCopy[4].get(1).getDescription();
    String target2 = deepCopy[4].get(2).getDescription();
    System.out.println(target1);
    System.out.println(target2);
    System.out.println(deepCopy[4].get(3).getDescription());
    if (target1.equals("Soccer") && target2.equals("Basketball")) {
      return true;
    }
    return false;
  }

  /**
   * Ensures the correctness of the MonthCalendar.addEvent() method when called with invalid inputs or
   * an attempt to add a duplicate event.
   *
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   * detected
   */
  public static boolean testUnsuccessfulAddEvents() {
    MonthCalendar newCalendar = new MonthCalendar(2024, 7);
    boolean eventBadDay = newCalendar.addEvent(-1, "Hockey", 6, 30);
    if (eventBadDay) {
      return false;
    }
    boolean eventNullDesc = newCalendar.addEvent(3, null, 6, 30);
    if (eventNullDesc) {
      return false;
    }
    boolean eventEmptyDesc = newCalendar.addEvent(3, "   ", 6, 30);
    if (eventEmptyDesc) {
      return false;
    }
    boolean eventBadHour = newCalendar.addEvent(3, "Hockey", -1, 30);
    if (eventBadHour) {
      return false;
    }
    boolean eventBadMin = newCalendar.addEvent(3, "Hockey", 6, 100);
    if (eventBadMin) {
      return false;
    }
    newCalendar.addEvent(3, "Hockey", 6, 30);
    boolean equalEvents = newCalendar.addEvent(3, "Hockey", 6, 30);
    ArrayList<Event>[] eventsCopy = newCalendar.getEvents();
    if (eventsCopy[2].size() == 1 && !equalEvents) {
      return true;
    }
    return false;
  }

  /**
   * Ensures the correctness of the MonthCalendar.cancelEvent() method when no exceptions are
   * expected.
   *
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   * detected
   */
  public static boolean testCancelEventValid() {
    // TODO verify (using the getEvents() method) that events are removed correctly on the correct day
    MonthCalendar newCalendar = new MonthCalendar(2026, 6);
    newCalendar.addEvent(6, "Math", 2, 30);
    newCalendar.addEvent(6, "English", 3, 30);
    newCalendar.addEvent(6, "Science", 4, 30);
    try {
      newCalendar.cancelEvent("English", 6, 3, 30);
      ArrayList<Event>[] deepCopy = newCalendar.getEvents();
      ArrayList<Event> eventsForDay = deepCopy[5];
      if (eventsForDay.get(0).getDescription().equals("Math") && eventsForDay.get(1).getDescription().equals("Science")) {
        return true;
      }
    } catch (Exception exception) {
      return false;
    }
    return false;
  }

  /**
   * Ensures the correctness of the MonthCalendar.cancelEvent() method when exceptions are
   * expected.
   *
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   * detected
   */
  public static boolean testCancelEventExceptions() {
    MonthCalendar newCalendar = new MonthCalendar(2026, 6);
    newCalendar.addEvent(6, "Math", 2, 30);
    newCalendar.addEvent(6, "English", 3, 30);
    newCalendar.addEvent(6, "Science", 4, 30);
    try {
      newCalendar.cancelEvent("Gym", 3, 5, 25);
      return false;
    } catch (IllegalArgumentException exception) {
      return false;
    } catch (NoSuchElementException exception) {
      System.out.println("Good");
    }
    try {
      newCalendar.cancelEvent("Math", -1, 2, 30);
      return false;
    } catch (IllegalArgumentException exception) {
      System.out.println("Good");
    } catch (NoSuchElementException exception) {
      return false;
    }
    try {
      newCalendar.cancelEvent("Math", 6, -5, 100);
      return false;
    } catch (IllegalArgumentException exception) {
      return true;
    } catch (NoSuchElementException exception) {
      return false;
    }

  }


  /**
   * Main method to run the tester methods
   *
   * @param args list of input arguments if any
   */
  public static void main(String[] args) {
    System.out.println("-----------------------------------------------------------");
    System.out.println("testEventConstructorAndGettersValidBehavior: " + (
        testEventConstructorAndGettersValidBehavior() ?
            "Pass" :
            "Failed!"));
    System.out.println("-----------------------------------------------------------");
    System.out.println("testEventConstructorThrowingExceptions: " + (
        testEventConstructorThrowingExceptions() ?
            "Pass" :
            "Failed!"));
    System.out.println("-----------------------------------------------------------");
    System.out.println("testEventCompareTo: " + (
        testEventCompareTo() ? "Pass" : "Failed!"));

    System.out.println("-----------------------------------------------------------");
    System.out.println("testEventEquals: " + (
        testEventEquals() ? "Pass" : "Failed!"));

    System.out.println("-----------------------------------------------------------");

    System.out.println("testSuccessfulAddEvent: " + (testSuccessfulAddEvent() ? "Pass" : "Failed!"));
    System.out.println("-----------------------------------------------------------");
    System.out.println("testUnsuccessfulAddEvents: " + (testUnsuccessfulAddEvents() ? "Pass" : "Failed!"));
    System.out.println("-----------------------------------------------------------");

    System.out.println("testCancelEventValid(): " + (testCancelEventValid() ? "Pass" : "Failed!"));
    System.out.println("-----------------------------------------------------------");
    System.out.println(
        "testDeleteExceptions: " + (testCancelEventExceptions() ? "Pass" : "Failed!"));
    System.out.println("-----------------------------------------------------------");
  }

}
