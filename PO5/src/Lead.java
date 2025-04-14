//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Lead
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
/**
 * This class implements Lead objects in team managent game
 */
public class Lead extends Agent {
  /**
   * Constructor for lead objects
   *
   * @param x init position x
   * @param y init position y
   */
  public Lead(int x, int y) {
    super(x, y);
  }

  /**
   * Draws lead objects to screen
   */
  @Override
  public void draw() {
    super.draw();
    processing.triangle((this.getX() - diameter) / 3, (this.getY() - diameter()) / 5,
        (this.getX() + diameter) / 3, (this.getY() - diameter) / 5,
        this.getX(), (this.getY() + diameter) / 3);
  }
  /**
   * Implements behavior of lead when mouse is released
   */
  @Override
  public void mouseReleased() {
    super.mouseReleased();
    if (isActive() && getTeam() != null) {
      getTeam().selectAll();
    }
  }
}
