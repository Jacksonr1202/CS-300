//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Team Tester
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
 * A short tester class for verifying some of the Agent and Team behaviors in P05.
 */
public class TeamTester {

  /**
   * Verifies that an Agent’s initial position is set correctly upon creation.
   * <p>
   * This test should:
   * - Create two agents at different (x,y) coordinates
   * - Verify that their getX() and getY() methods return the expected values
   * - Verify that their initial positions match the coordinates provided to their constructors
   *
   * @return true if both agents are created with correct coordinates; false otherwise
   */
  public static boolean testAgentInitialPosition() {
    Agent newAgent = new Agent(25, 100);
    Agent newAgent2 = new Agent(50, 150);
    if (newAgent.getX() != 25 || newAgent.getY() != 100) {
      return false;
    }
    if (newAgent2.getX() != 50 || newAgent2.getY() != 150) {
      return false;
    }
    return true;
  }

  /**
   * Verifies that an Agent moves correctly when given a destination.
   * <p>
   * This test should:
   * - Create an agent at a known position
   * - Set a destination that is at least 10 pixels away in both x and y directions
   * - Call the move() method once
   * - Verify that the agent has moved closer to the destination but has not reached it
   * - Verify that the movement follows the expected trajectory
   *
   * @return true if agent movement behavior is correct; false otherwise
   */
  public static boolean testAgentMovement() {
    Agent newAgent3 = new Agent(10, 10);
    newAgent3.setDestination(20, 20);
    newAgent3.move();
    if (newAgent3.getX() >= 20 || newAgent3.getX() <= 10) {
      return false;
    }
    if (newAgent3.getY() >= 20 || newAgent3.getY() <= 10) {
      return false;
    }
    System.out.println("X:" + newAgent3.getX() + " Y:" + newAgent3.getY());
    if (newAgent3.getX() + 0.1 < (10 + (3 / Math.sqrt(2)))) {
      return false;
    }
    if (newAgent3.getY() + 0.1 < (10 + (3 / Math.sqrt(2)))) {
      return false;
    }
    return true;
  }

  /**
   * Verifies that an Agent without a destination remains stationary.
   * <p>
   * This test should:
   * - Create an agent at a specific position
   * - Record its initial position
   * - Call the move() method
   * - Verify that the agent’s position has not changed
   *
   * @return true if agent remains stationary when no destination is set; false otherwise
   */
  public static boolean testAgentStationary() {
    Agent newAgent4 = new Agent(50, 50);
    float initPosX = newAgent4.getX();
    float initPosy = newAgent4.getY();
    newAgent4.move();
    if (newAgent4.getX() != initPosX || newAgent4.getY() != initPosy) {
      return false;
    }
    return true;
  }

  /**
   * Verifies that creating a Team with multiple Leads throws an IllegalArgumentException.
   * <p>
   * This test should:
   * - Create an ArrayList of Agents that includes multiple Lead instances
   * - Attempt to create a Team with this ArrayList
   * - Verify that an IllegalStateException is thrown
   *
   * @return true if the correct exception is thrown; false otherwise
   */
  public static boolean testMultipleLeadsException() {
    ArrayList<Agent> agents = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      agents.add(new Lead(i * 10, i * 20));
    }
    try {
      Team newTeam = new Team(256, agents);
      return false;
    } catch (IllegalStateException exception) {
      return true;
    }
  }

  /**
   * Verifies behavior around empty teams.
   * <p>
   * This test should:
   * - Create an empty ArrayList
   * - Attempt to create a Team with this ArrayList
   * - Verify that an IllegalArgumentException is thrown
   * - Add at least one Agent to the ArrayList and create a valid team
   * - Remove all agents from the team
   * - Verify that the Team's size is now zero
   *
   * @return
   */
  public static boolean testEmptyTeam() {
    ArrayList<Agent> agents2 = new ArrayList<>();
    try {
      Team newTeam2 = new Team(256, agents2);
      return false;
    } catch (IllegalArgumentException exception) {
      System.out.println("Good");
    }
    agents2.add(new Agent(70, 120));
    Team newTeam3 = new Team(256, agents2);
    newTeam3.removeMember(agents2.get(0));
    if (newTeam3.getTeamSize() == 0) {
      return true;
    }
    return false;
  }

  /**
   * Verifies that a Team can be created successfully with exactly one Lead.
   * <p>
   * This test should:
   * - Create an ArrayList with one Lead and multiple regular Agents
   * - Create a Team with this ArrayList
   * - Verify that the Team is created successfully
   * - Verify that the Team size matches the ArrayList size
   * - Verify that all Agents are properly added to the Team
   * - Verify that the hasLead method correctly reports that this team has a Lead
   *
   * @return true if Team creation succeeds with correct composition; false otherwise
   */
  public static boolean testValidTeamCreation() {
    try {
      ArrayList<Agent> agents = new ArrayList<>();
      for (int i = 0; i < 8; i++) {
        if (i == 4) {
          agents.add(new Lead(50, 100));
          continue;
        }
        agents.add(new Agent(i * 15, i * 10));
      }
      Team newTeam = new Team(256, agents);
      if (newTeam.getTeamSize() != agents.size() || !newTeam.hasLead()) {
        return false;
      }
      for (int i = 0; i < agents.size(); i++) {
        if (!newTeam.contains(agents.get(i))) {
          return false;
        }
      }
    } catch (Exception exception) {
      return false;
    }
    return true;
  }

  /**
   * Verifies that a new Agent can be added to an existing Team.
   * <p>
   * This test should:
   * - Create a valid Team with one Lead and at least one Agent
   * - Create a new Agent
   * - Add the new Agent to the Team using addAgent()
   * - Verify that the Team size has increased
   * - Verify that the new Agent is now a member of the Team
   *
   * @return true if Agent is successfully added to Team; false otherwise
   */
  public static boolean testAddAgentToTeam() {
    ArrayList<Agent> newAgents = new ArrayList<>();
    newAgents.add(new Agent(65, 95));
    newAgents.add(new Lead(123, 197));
    Team newTeam;
    try {
      newTeam = new Team(256, newAgents);
      System.out.println("Team created");
    } catch (Exception exception) {
      return false;
    }
    Agent newAgent = new Agent(23, 77);
    newTeam.addMember(newAgent);
    if (newTeam.getTeamSize() != 3) {
      return false;
    }
    System.out.println("Team size increased");
    if (newTeam.contains(newAgent)) {
      return true;
    }
    return false;
  }

  /**
   * Verifies that Team’s center coordinates are calculated correctly.
   * <p>
   * This test should:
   * - Create a Team with at least three Agents at known positions
   * - Calculate the expected center coordinates manually
   * - Compare the expected values with getCenterX() and getCenterY() results
   * - Verify that adding a new Agent updates the center coordinates correctly
   *
   * @return true if center coordinates are calculated correctly; false otherwise
   */
  public static boolean testTeamCenter() {
    ArrayList<Agent> agents = new ArrayList<>();
    agents.add(new Agent(30, 30));
    agents.add(new Agent(70, 70));
    agents.add(new Agent(130, 130));
    Team newTeam = new Team(256, agents);
    float expectedCenter = 80;
    if (newTeam.getCenterX() != expectedCenter || newTeam.getCenterY() != expectedCenter) {
      return false;
    }
    newTeam.addMember(new Agent(150, 150));
    expectedCenter = 90;
    if (newTeam.getCenterX() != expectedCenter || newTeam.getCenterY() != expectedCenter) {
      return false;
    }
    return true;
  }

  /**
   * Runs all tests and displays results
   *
   * @param args unused
   */
  public static void main(String[] args) {
    System.out.println("-----------------------------------------------------------");
    System.out.println("testAgentInitialPosition: " + (testAgentInitialPosition() ? "Pass" : "Failed!"));
    System.out.println("-----------------------------------------------------------");
    System.out.println("testAgentMovement: " + (testAgentMovement() ? "Pass" : "Failed!"));
    System.out.println("-----------------------------------------------------------");
    System.out.println("testAgentStationary: " + (testAgentStationary() ? "Pass" : "Failed!"));
    System.out.println("-----------------------------------------------------------");
    System.out.println("testMultipleLeadsException: " + (testMultipleLeadsException() ? "Pass" : "Failed!"));
    System.out.println("-----------------------------------------------------------");
    System.out.println("testEmptyTeam: " + (testEmptyTeam() ? "Pass" : "Failed!"));
    System.out.println("-----------------------------------------------------------");
    System.out.println("testValidTeamCreation: " + (testValidTeamCreation() ? "Pass" : "Failed!"));
    System.out.println("-----------------------------------------------------------");
    System.out.println("testAddAgentToTeam: " + (testAddAgentToTeam() ? "Pass" : "Failed!"));
    System.out.println("-----------------------------------------------------------");
    System.out.println("testTeamCenter: " + (testTeamCenter() ? "Pass" : "Failed!"));
    System.out.println("-----------------------------------------------------------");
  }

}
