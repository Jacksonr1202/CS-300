//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Agent
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

import java.io.File;

import processing.core.PImage;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.Random;
/**
 * This class implements the agent object in TeamManagement
 */
public class Agent implements Clickable {
  private float destX; //X cord Agent is moving to
  private float destY; //Y cord Agent is moving to
  protected static int diameter = 20; //Diameter of Agent
  private boolean isActive;//Activy of agent
  protected boolean isDragging; //Is agent being dragged?
  private int oldMouseX; //Previous X cord of mouse
  private int oldMouseY; //Previous Y cord of mouse
  private float originalX; //Stores the original X cord when mouse is pressed
  private float originalY; //Stores the original Y cord when mouse is pressed
  protected static processing.core.PApplet processing; //Refers to application window
  protected Team team; //The agents team
  private float xPos; //X pos of the agent(Center)
  private float yPos; //Y pos of the agent(Center

  /**
   * Constructs an Agent object with its
   * position(xPos,yPos) set to the input parameters
   *
   * @param x X cord to set Agent to
   * @param y Y cord to set Agent to
   */
  public Agent(int x, int y) {
    this.xPos = x;
    this.yPos = y;
    this.destX = -1;
    this.destY = -1;
    this.isActive = false;
    this.isDragging = false;
  }

  /**
   * Initiates the PApplet reference
   *
   * @param processing PApplet reference
   */
  public static void setProcessing(processing.core.PApplet processing) {
    Agent.processing = processing;
  }

  public static int diameter() {
    return diameter;
  }

  /**
   * Determines if Agent is active or not
   *
   * @return true if Agent is active, false if not.
   */
  public boolean isActive() {
    return this.isActive;
  }
  /**
   * Determines if Agent moving or not
   *
   * @return true if Agent is moving false if not
   */
  protected boolean isMoving() {
    return (this.destX >= 0 && this.destY >= 0);
  }
  /**
   * Returns agent x position
   *
   * @return float representing the x position
   */
  public float getX() {
    return this.xPos;
  }
  /**
   * Returns agent y position
   *
   * @return float representing the y position
   */
  public float getY() {
    return this.yPos;
  }
  /**
   * Gets team object of this agent
   *
   * @return Agent object representing this agents team
   */
  public Team getTeam() {
    return this.team;
  }
  /**
   * Returns int representing the color of the agent
   *
   * @return int representing color of agent
   */
  protected int getColor() {
    if (this.isActive) {
      return processing.color(0, 255, 0);
    } else if (this.team != null) {
      return this.team.getColor();
    } else {
      return processing.color(255, 255, 0);
    }
  }
  /**
   * Toggles the isActive boolean of agent
   *
   */
  public void toggleActive() {
    this.isActive = !this.isActive;
  }

  public void setTeam(Team t) {
    if (this.team == t) {
      return;
    }
    if (this.team != null) {
      this.team.removeMember(this);
    }
    this.team = t;
  }
  /**
   * Sets destination
   *
   * @param x value to set destX to
   * @param y value to set destY to
   */
  public void setDestination(float x, float y) {
    this.destX = x;
    this.destY = y;
    this.isActive = false;
  }
  /**
   * Draws the agent object to screen
   *
   */
  public void draw() {
    if (this.isDragging) {
      drag();
    } else {
      move();
    }
    processing.fill(this.getColor());
    processing.circle(this.xPos, this.yPos, Agent.diameter());
  }
  /**
   * Starts dragging object
   *
   */
  protected void startDragging() {
    this.isDragging = true;
    this.oldMouseX = processing.mouseX;
    this.oldMouseY = processing.mouseY;
    this.originalX = xPos;
    this.originalY = yPos;
  }
  /**
   * Stops dragging agent
   *
   */
  protected void stopDragging() {
    this.isDragging = false;
  }
  /**
   * Drags the agent
   *
   */
  protected void drag() {
    if (!isDragging) {
      return;
    }
    int mouseDx = processing.mouseX - oldMouseX;
    int mouseDy = processing.mouseY - oldMouseY;
    this.xPos += mouseDx;
    this.yPos += mouseDy;
    this.oldMouseX = processing.mouseX;
    this.oldMouseY = processing.mouseY;
  }
  /**
   * Moves the agent towards its destination
   *
   * @return float representing the x position
   */
  protected void move() {
    if (this.destX < 0 || this.destY < 0) {
      return;
    }
    float totalDistance = getDistance(this.destX - this.xPos, this.destY - this.yPos);
    if (totalDistance <= 3) {
      this.xPos = this.destX;
      this.yPos = this.destY;
      this.destX = -1;
      this.destY = -1;
    } else {
      this.xPos += (3 * ((this.destX - this.xPos) / totalDistance));
      this.yPos += (3 * ((this.destY - this.yPos) / totalDistance));
    }
  }
  /**
   * Starts dragging if mouse is clicked and is not moving
   *
   * @return float representing the x position
   */
  public void mousePressed() {
    if (!isMoving()) {
      originalX = xPos;
      originalY = yPos;
      startDragging();
    }
  }
  /**
   * Implements behavior when the mouse is released
   *
   */
  public void mouseReleased() {
    if (this.isDragging) {
      stopDragging();
      if (xPos == originalX && yPos == originalY) {
        this.isActive = true;
      }
      originalY = -1;
      originalX = -1;
    }
  }
  /**
   * Gets total distance
   *
   */
  private float getDistance(float dx, float dy) {
    return (float) Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
  }
  /**
   * Determines if the mouse is over the agent
   *
   * @return true if over, false if not
   */
  public boolean isMouseOver() {
    float posRangeX = this.xPos + (float) diameter / 2;
    float negRangeX = this.xPos - (float) diameter / 2;
    float posRangeY = this.yPos + (float) diameter / 2;
    float negRangeY = this.yPos - (float) diameter / 2;
    boolean inRangeX = (processing.mouseX <= posRangeX && processing.mouseX >= negRangeX);
    boolean inRangeY = (processing.mouseY <= posRangeY && processing.mouseY >= negRangeY);
    return inRangeX && inRangeY;
  }
}
