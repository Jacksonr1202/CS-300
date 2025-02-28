//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Event
// Course:   CS 300 Spring 2025
//
// Author:   Jackson Ryan
// Email:    jdryan7@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    N/A
// Partner Email:   N/A
// Partner Lecturer's Name: N/A
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   __N/A_ Write-up states that pair programming is allowed for this assignment.
//   __N/A_ We have both read and understand the course Pair Programming Policy.
//   _N/A__ We have registered our team prior to the team registration deadline.
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons: Lectures
// Online Sources:
// https://stackoverflow.com/questions/10162802/see-if-two-object-have-the-same-type
// Helped me understand how to determine if an object is a certain type
//
// ZyBooks
// Gave me a better understanding of using objects
//
// P04 JavaDocs
///////////////////////////////////////////////////////////////////////////////

/**
 * Class Event that creates and interacts with Event objects
 */
public class Event implements Comparable<Event> {
  private String description; //Description of the event
  private int day; //Day when the event takes place
  private int startHour; //Hour of day when the event takes place
  private int startMin; //Minute of the hour when the event takes place
  private int startTime; //Start time represented as integer
  private boolean isComplete; //A boolean representing if the event is complete

  /**
   * Constructor for Event objects
   *
   * @param description Description of the event to be created
   * @param day         The day of the month in which the event occurs
   * @param startHour   The hour of the day in which the event occurs
   * @param startMin    The minute of the day in which the event occurs
   */
  public Event(String description, int day, int startHour, int startMin) {
    if (description == null || description.trim().isEmpty() || day < 1 || day > 31 ||
        startHour < 0 || startHour > 23 || startMin < 0 || startMin > 59) {
      throw new IllegalArgumentException("One or more paramters were invalid");
    } //Makes sure that the parameters are valid
    this.description = description;
    this.day = day;
    this.startHour = startHour;
    this.startMin = startMin;
    this.isComplete = false;
    this.startTime = (startHour * 100) + startMin;
  }

  /**
   * Creates a string representation of the start time of the event
   *
   * @return a String representing the start time
   * on separate lines,and an empty string if the list of completed events is empty.
   */
  public String getStartTimeAsString() {
    StringBuilder startTimeString = new StringBuilder();
    startTimeString.append(String.format("%02d", this.startHour)).append(":")
        .append(String.format("%02d", this.startMin));
    return startTimeString.toString();
  }

  /**
   * Gets the description of the object
   *
   * @return a String of the description
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * Gets the day of the event
   *
   * @return an integer representing the day of the event
   */
  public int getDay() {
    return this.day;
  }

  /**
   * Checks if the specified event is complete
   *
   * @return true if the event is complete, false if not
   */
  public boolean isComplete() {
    return this.isComplete;
  }

  /**
   * Marks an event as complete
   */
  public void markAsComplete() {
    this.isComplete = true;
  }

  /**
   * Checks if the parameter Object o is an Event type
   * and if so checks if its equal to the instance Event
   *
   * @return true if the events are equal, false if they are not
   */
  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Event)) {
      return false;
    }

    Event eventO = (Event) o;

    if (this.day == eventO.day && this.description.equals(eventO.description)
        && this.startTime == eventO.startTime) {
      return true;
    }
    return false;
  }

  /**
   * Compares two events and returns an integer based
   * on the timing of the two events
   *
   * @return negative integer if the event time is earlier than parameter event
   * 0 if the events start at same time and positive if the event is at a later
   * time
   */
  @Override
  public int compareTo(Event otherEvent) {
    if (otherEvent == null) {
      throw new NullPointerException("Event is null");
    } else {
      return this.startTime - otherEvent.startTime;
    }
  }

  /**
   * Creates a deep copy of the specified event
   *
   * @return An event object with the same properties as the arguments passed
   * to it
   */
  public Event copy() {
    Event eventCopy = new Event(this.description, this.day, this.startHour, this.startMin);
    if (this.isComplete) {
      eventCopy.markAsComplete();
    }
    return eventCopy;
  }

  /**
   * Provided method - Returns a String representation of this Event
   *
   * @return a String representation of this event
   */
  @Override
  public String toString() {
    return this.description + " at " + this.startHour + ":" + this.startMin
        + (isComplete ? " completed on Day " + day : "");
  }
}