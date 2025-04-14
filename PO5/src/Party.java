//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Party
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
import processing.core.PImage;

/**
 * Implements party objects in team management game
 */
public class Party implements Clickable {
  private processing.core.PImage image;
  private static TeamManagementSystem tms;
  private float x;
  private float y;

  /**
   * Constructor for party objects
   *
   * @param x x pos of party
   * @param y y pos of party
   * @param image image of party obj
   */
  public Party(int x, int y, processing.core.PImage image) {
    this.x = x;
    this.y = y;
    this.image = image;
  }

  /**
   * Sets the reference to the app of use
   *
   * @param processing application in use
   */
  public static void setProcessing(TeamManagementSystem processing) {
    Party.tms = processing;
  }

  /**
   * Gets x pos of party obj
   *
   * @return float of x pos
   */
  public float getX() {
    return this.x;
  }

  /**
   * Gets y pos of party obj
   *
   * @return y pos of obj
   */
  public float getY() {
    return this.y;
  }

  /**
   * Draws object to screen
   */
  public void draw() {
    tms.image(image, x, y);
  }

  /**
   * Required for interface but left blank
   */
  public void mousePressed() {

  }

  /**
   * Determines behavior when mouse is released
   */
  public void mouseReleased() {
    if (this.isMouseOver()) {
      Team team = tms.getActiveTeam();
      if (team != null) {
        team.sendToParty(this);
      }
    }
  }
  /**
   * Determines if mouse is over the party
   */
  public boolean isMouseOver() {
    float posRangeX = x + image.width;
    float negRangeX = x - image.width;
    float posRangeY = y + image.width;
    float negRangeY = y - image.width;
    boolean inRangeX = (tms.mouseX <= posRangeX && tms.mouseX >= negRangeX);
    boolean inRangeY = (tms.mouseY <= posRangeY && tms.mouseY >= negRangeY);
    return inRangeX && inRangeY;
  }
}
