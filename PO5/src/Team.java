//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Team
// Course:   CS 300 Spring 2025
//
// Author:   Jackson Ryan
// Email:    jdryan7@wisc.edu
// Lecturer: Hobbes Legault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    N/A
// Partner Email:   N/A
// Partner Lecturer's Name: N/A
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___X Write-up states that pair programming is allowed for this assignment.
//   __N/A_ We have both read and understand the course Pair Programming Policy.
//   _N/A__ We have registered our team prior to the team registration deadline.
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons:         (identify each by name and describe how they helped)
// Online Sources:  https://stackoverflow.com/questions/4801366/convert-rgb-values-to-integer
// Helped me understand how to return a color as integer representation
//
////////////////////////////////////////////////////////////////////////////
import java.util.ArrayList;
/**
 * This class implements Team objects in Team management game
 */
public class Team {
  private int color;
  private static char idGenerator = 'A';
  private ArrayList<Agent> members;
  private final char TEAM_ID;
  /**
   * Constructor for team objects
   *
   * @param color color to set team to
   * @param agents agents to add to team
   */
  public Team(int color, ArrayList<Agent> agents) {
    if (agents == null || agents.isEmpty()) {
      throw new IllegalArgumentException("ERROR:List contains no agents");
    }
    int count = 0;
    for (int i = 0; i < agents.size(); i++) {
      if (agents.get(i) instanceof Lead) {
        count++;
      }
    }
    if (count > 1) {
      throw new IllegalStateException("ERROR:More than one lead");
    }
    this.TEAM_ID = idGenerator;
    this.color = color;
    this.members = new ArrayList<>();
    for (Agent a : agents) {
      this.members.add(a);
      a.setTeam(this);
    }
    idGenerator++;
  }
  /**
   * Gets the color represeting the team
   *
   * @return int representing rgb color of team
   */
  public int getColor() {
    return this.color;
  }
  /**
   * Gets the ID of the team
   *
   * @return char representing team ID
   */
  public char getTeamID() {
    return this.TEAM_ID;
  }
  /**
   * Gets the size of the team
   *
   * @return int representing size of team
   */
  public int getTeamSize() {
    return this.members.size();
  }
  /**
   * Determines if the team has a lead
   *
   * @return true if it does false if not
   */
  public boolean hasLead() {
    for (int i = 0; i < this.members.size(); i++) {
      if (this.members.get(i) instanceof Lead) {
        return true;
      }
    }
    return false;
  }
  /**
   * Adds a member to the team
   *
   * @throws  IllegalStateException if Agent is a lead
   */
  public void addMember(Agent a) throws IllegalStateException {
    if (this.members.contains(a)) {
      return;
    }
    if (a instanceof Lead) {
      throw new IllegalStateException("ERROR:Agent is a Lead");
    }
    this.members.add(a);
    a.setTeam(this);
  }
  /**
   * Removes a member from the team
   *
   * @return true if member was removed false if not
   */
  public boolean removeMember(Agent a) {
    if (this.members.contains(a)) {
      members.remove(a);
      a.team = null;
      return true;
    }
    return false;
  }
  /**
   * Determines if this team contains the Agent param
   *
   * @param a agent to check if the team contains it
   * @return true if it does contain it false if not
   */
  public boolean contains(Agent a) {
    return members.contains(a);
  }
  /**
   * Makes all members of the team active
   *
   */
  public void selectAll() {
    for (int i = 0; i < members.size(); i++) {
      if (!members.get(i).isActive()) {
        members.get(i).toggleActive();
      }
    }
  }
  /**
   * Determines if all members of team are active
   *
   * @return true if theyre all active false if not
   */
  public boolean isActive() {
    for (int i = 0; i < members.size(); i++) {
      if (!members.get(i).isActive()) {
        return false;
      }
    }
    return true;
  }
  /**
   * Gets centerX cord of members
   *
   * @return float of center x position
   */
  public float getCenterX() {
    if (this.members.isEmpty()) {
      return 0;
    }
    float maxX = members.get(0).getX();
    for (int i = 1; i < members.size(); i++) {
      if (members.get(i).getX() > maxX) {
        maxX = members.get(i).getX();
      }
    }
    float minX = members.get(0).getX();
    for (int i = 1; i < members.size(); i++) {
      if (members.get(i).getX() < minX) {
        minX = members.get(i).getX();
      }
    }
    float centerX = (maxX + minX) / 2.0f;
    return centerX;
  }
  /**
   * Gets centerY cord of members
   *
   * @return float of center y position
   */
  public float getCenterY() {
    if (members.isEmpty()) {
      return 0;
    }
    float maxY = members.get(0).getY();
    for (int i = 1; i < members.size(); i++) {
      if (members.get(i).getY() > maxY) {
        maxY = members.get(i).getY();
      }
    }
    float minY = members.get(0).getY();
    for (int i = 1; i < members.size(); i++) {
      if (members.get(i).getY() < minY) {
        minY = members.get(i).getY();
      }
    }
    return (maxY + minY) / 2.0f;
  }
  /**
   * Sends the members to party in formation
   *
   */
  public void sendToParty(Party p) {
    float centerX = p.getX();
    float centerY = p.getY();
    int size = members.size();
    float spacing = 23.0f;
    float leftMost = centerX - (spacing * (size - 1) / 2.0f);
    for (int i = 0; i < members.size(); i++) {
      float destX = leftMost + i * spacing;
      members.get(i).setDestination(destX, centerY);
    }
    lineUp();
  }
  /**
   * Lines the members up
   *
   */
  public void lineUp() {
    float centerX = getCenterX();
    float centerY = getCenterY();
    int size = members.size();
    float spacing = 23.0f;
    float leftMost = centerX - (spacing * (size - 1) / 2.0f);
    for (int i = 0; i < members.size(); i++) {
      float destX = leftMost + i * spacing;
      members.get(i).setDestination(destX, centerY);
    }
  }
}
