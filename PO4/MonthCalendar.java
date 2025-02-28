//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    MonthCalendar
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
// Online Sources:
// https://stackoverflow.com/questions/10227201/initialize-an-array-of-arraylist
// Helped me understand that you have to type cast when initializing an array
// of lists
//
// P04 JavaDocs
///////////////////////////////////////////////////////////////////////////////

import java.time.DateTimeException;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Class MonthCalendar that creates and manages Calendars for
 * specified months
 */
public class MonthCalendar {
  private ArrayList<Event> completedEvents; //List of completed events
  private int daysCount; //Number of days in the month
  private ArrayList<Event>[] events; //Array of lists for events on each day of month
  private final YearMonth MONTH; //Month to be used for the calendar

  /**
   * Constructor for MonthCalendar objects. Creates
   * a calendar for the specified month and year
   */
  public MonthCalendar(int year, int month) throws DateTimeException {
    if (year < 1000 || year > 9999 || month < 1 || month > 12) {
      throw new DateTimeException("Invalid year or month");
      //Makes sure there are valid parameters
    }
    this.MONTH = YearMonth.of(year, month);
    this.daysCount = this.MONTH.lengthOfMonth();
    this.events = (ArrayList<Event>[]) new ArrayList[this.daysCount];
    for (int i = 0; i < this.events.length; i++) {
      this.events[i] = new ArrayList<Event>();
      //Creates a ArrayList of events for each day
    }
    this.completedEvents = new ArrayList<>();
  }

  /**
   * gets the name of the month of the calendar
   *
   * @return a String representing the name of the month
   */
  public String getMonthName() {
    return this.MONTH.getMonth().name();
  }

  /**
   * gets the number of the month from 1-12 based
   * on the month name
   *
   * @return integer value of the month of the
   * calendar
   */
  public int getMonthNumber() {
    return this.MONTH.getMonthValue();
  }

  /**
   * Gets the amount of days in the month
   *
   * @return an integer representing the amount of
   * days in the month
   */
  public int getDaysCount() {
    return this.daysCount;
  }

  /**
   * Adds an event with intended properties to
   * to a list representing events for a specified
   * day of the month.
   *
   * @param day         day of the month to add to
   * @param description description of the event
   * @param startHour   the hour of the day in which
   *                    the event starts at
   * @param startMin    the minute of the hour when
   *                    the event starts
   * @return true if the event was added successfully
   * or false if was not added because of invalid
   * inputs
   * on separate lines,and an empty string if the list of completed events is empty.
   */
  public boolean addEvent(int day, String description, int startHour, int startMin) {
    if (day < 1 || day > this.daysCount) {
      return false;
    }
    Event newEvent;
    try {
      newEvent = new Event(description, day, startHour, startMin);
    } catch (IllegalArgumentException exception) {
      return false;
    }
    ArrayList<Event> dayEvents = this.events[day - 1];
    for (Event event : dayEvents) {
      if (event.equals(newEvent)) {
        return false;
        //Makes sure were not adding a duplicate
      }
    }
    int i = 0;
    for (; i < dayEvents.size(); i++) {
      Event currentEvent = dayEvents.get(i);
      int comparison = newEvent.compareTo(currentEvent);
      if (comparison < 0) {
        break;
        //Determines where to add the Event
      }
    }
    this.events[day - 1].add(i, newEvent);
    return true;
  }

  /**
   * Removes an event that matches the input parameters
   * or throws exceptions if invalid inputs
   *
   * @throws IllegalArgumentException if parameters do not satisfy
   *                                  the requirements to create an event object or if the day input
   *                                  is out of the range for the specified month
   * @throws NoSuchElementException   if the parameters do not match
   *                                  with any event of the calendar for that day
   */
  public void cancelEvent(String description, int day, int startHour, int startMin) {
    if (day > this.daysCount) {
      throw new IllegalArgumentException("ERROR:Invalid day argument");
    }
    try {
      Event eventCopy = new Event(description, day, startHour, startMin);
      for (int i = 0; i < this.events[day - 1].size(); i++) {
        if (this.events[day - 1].get(i).equals(eventCopy)) {
          this.events[day - 1].remove(i);
          return;
          //Finds the matching event and removes it
        }
      }
      throw new NoSuchElementException("ERROR:This event was not found");
    } catch (IllegalArgumentException exception) {
      throw new IllegalArgumentException(exception.getMessage());
    }
  }

  /**
   * Marks an event object as complete and removes it from
   * the calendar
   *
   * @throws IllegalArgumentException if the event is null
   *                                  or if the day is out of range for the specified month
   */
  public void markEventAsComplete(Event e) {
    if (e == null || e.getDay() < 1 || e.getDay() > this.daysCount) {
      throw new IllegalArgumentException("ERROR:Invalid inputs");
    }
    int eventDay = e.getDay() - 1;
    for (int i = 0; i < this.events[eventDay].size(); i++) {
      if (this.events[eventDay].get(i).equals(e)) {
        this.events[eventDay].get(i).markAsComplete();
        this.events[eventDay].remove(i);
        this.completedEvents.add(0, e);
        return;
        //Finds the event matching the argument and marks it as complete
      }
    }
    throw new NoSuchElementException("ERROR:This event does not exist");
  }

  /**
   * Creates a deep copy of an Array of lists for the
   * specified calendar
   *
   * @return an array of event lists
   */
  public ArrayList<Event>[] getEvents() {
    ArrayList<Event>[] eventsCopy = (ArrayList<Event>[]) new ArrayList[this.daysCount];
    for (int i = 0; i < this.daysCount; i++) {
      eventsCopy[i] = new ArrayList<Event>();
      for (int j = 0; j < this.events[i].size(); j++) {
        eventsCopy[i].add(this.events[i].get(j));
        //Iterates through each day and adds events from each day
      }
    }
    return eventsCopy;
  }

  /**
   * Creates deep copies of the events in the completedEvents
   * ArrayList and returns them in a list of events
   *
   * @return an ArrayList of completed events
   */
  public ArrayList<Event> getCompletedEvents() {
    ArrayList<Event> completedEventsCopy = new ArrayList<>();
    for (int i = 0; i < this.completedEvents.size(); i++) {
      completedEventsCopy.add(this.completedEvents.get(i).copy());
      //Gets every completed event and adds it to the list
    }
    return completedEventsCopy;
  }

  /**
   * Removes all the events in the completed events list
   */
  public void clearAllCompletedEvents() {
    for (int i = this.completedEvents.size() - 1; i >= 0; i--) {
      this.completedEvents.remove(i);
      //Iterates backwards and removes all events
    }
  }

  /**
   * Provided method -- Returns a String representation of all completed events
   *
   * @return a String containing all completed events with their completed days
   * on separate lines,and an empty string if the list of completed events is empty.
   */
  public String getCompletedEventsAsString() {
    String retval = "";
    for (Event e : completedEvents) {
      retval += e.toString() + "\n";
    }
    return retval.strip();
  }

  /**
   * Provided method -- Returns a String representation of all uncompleted events.
   * <p>
   * Events scheduled on the same day must be in the increasing order.
   *
   * @return a String representation of All the events stored in the events list,
   * and an empty string if the list of events is empty.
   */
  @Override
  public String toString() {
    String retval = "";
    for (int i = 0; i < events.length; i++) {
      if (!events[i].isEmpty()) {
        retval += "Events for Day " + (i + 1) + ":\n";
        for (Event e : events[i]) {
          retval += e.toString() + "\n";
        }
      }
    }
    return retval.strip();
  }
}
