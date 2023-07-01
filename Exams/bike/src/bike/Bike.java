package bike;

///////////////////////// CUMULATIVE QUIZ FILE HEADER //////////////////////////
// Full Name: Matej Popovski
// Campus ID: 9083541632
// WiscEmail: popovski@wisc.edu
// (TODO: fill this out)
////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////
//
// BE CAREFUL!! This file contains TWO classes. You will need to complete the
// implementation of BOTH classes with respect to the provided requirements
// in the TODO tags for full credit.
//
// When creating new exception objects, including messages within these objects
// is optional.
////////////////////////////////////////////////////////////////////////////////

/**
 * This class models the Bike data type.
 * 
 * NOTES: You may NOT add any additional data fields to this class NOT specified in the TODO tags.
 * You may NOT add any additional methods to this class whether private or public. You may NOT need
 * to implement any tester for this class.
 * 
 */
public class Bike {
  // TODO
  // 1. Declare a private instance field of type int named cyclingCadence
  private int cyclingCadence;
  // 2. Declare a private instance field of type int named speed
  private int speed;
  // 3. Declare a protected static data field of type int named bikeCount initialized to zero
  // bikeCount represents the total number of Bike objects created by the constructor of this class
  protected static int bikeCount = 0;

  /**
   * Creates a new Bike with a given cyclingCadence and speed. We assume that the provided values of
   * cyclingCadence and speed are VALID.
   * 
   * @param cyclingCadence cadence to be assigned to this bike
   * @param speed          speed to be assigned to this bike
   */
  public Bike(int cyclingCadence, int speed) {
    // TODO
    // 4. Set the instance data fields to the provided input parameters

    this.cyclingCadence = cyclingCadence;

    this.speed = speed;


    // 5. Increments the total number of created Bike objects
    ++bikeCount;
  }

  // MAKE SURE TO SAVE YOUR SOURCE FILE (Ctrl/Cmd + S) before submitting it to Gradescope

  /**
   * Gets the speed of this bike
   * 
   * @return the speed of this bike
   */
  public int getSpeed() {
    // TODO:
    // 6. Complete the implementation of this accessor
    return this.speed;
    // default return statement added to avoid compiler errors
  }

  /**
   * Reduces the speed of this bike object by the amount passed as input. We assume that amount is
   * positive (VALID)
   * 
   * @param amount decrement amount by which to reduce the speed of this Bike object
   */
  public void brake(int amount) {
    // TODO
    // 7. Complete the implementation of this mutator
    this.speed -= amount;
  }


  /**
   * Returns a string representation of this bike. The returned string must have the following
   * format: "bike " + speed + " @ " + cadence + "rpm"
   * 
   * @Return a String representation of this bike
   */
  @Override
  public String toString() {
    // TODO
    // 8. Implement this method

    String bikeMessage = "bike " + speed + " @ " + cyclingCadence + " rpm";
    return bikeMessage; // default return statement added to
                        // avoid compiler errors. Feel free to
                        // change it.
  }

}

// MAKE SURE TO SAVE YOUR SOURCE FILE (Ctrl/Cmd + S) before submitting it to Gradescope


/**
 * This class models the BCycle data type representing rental bikes
 * 
 * NOTES: You may NOT add any additional data fields to this class NOT specified in the TODO tags.
 * You may NOT add any additional methods to this class whether private or public You may NOT need
 * to implement any tester for this class
 *
 */
class BCycle
    extends Bike /* TODO: 9. set the class BCycle to be a subclass of the super class Bike */ {

  private int id; // identifier of this bike
  private boolean isCheckedOut; // true when this bike is checked out, false otherwise


  /**
   * Creates a new BCycle with a given cycling cadence, speed, and id. When created, the BCycle
   * object is NOT checked out. We assume that all the input arguments passed as input are VALID.
   * 
   * @param cyclingCadence the cycling cadence to be assigned to this bCycle
   * @param speed          the speed to be assigned to this bCycle
   * @param id             the identifier to be assigned to this bCycle
   */
  public BCycle(int cyclingCadence, int speed, int id) {
    // TODO:
    // 10. call the constructor of the super class with the passed cyclingCadence and speed as
    // input)

    super(cyclingCadence, speed);

    // 11. Set the id of this bCycle to the id passed as input to this constructor

    this.id = id;

    // 12. Set the isCheckedOut to false (optional since the default value of a boolean is false)

    isCheckedOut = false;
  }

  // You are NOT allowed to add any additional methods to this class

  /**
   * Changes the state of this BCycle object to reflect that it has been checked back in by setting
   * its isCheckedOut data field to false.
   * 
   * @throws IllegalStateException when called on a bike that is NOT checked out.
   */
  public void checkin() throws IllegalStateException {
    // TODO:
    // 13. Complete the implementation of this method
    if (isCheckedOut == false) {
      throw new IllegalStateException("ERROR: This bike hasnt been checked out!");
    }

    isCheckedOut = false;
  }

  /**
   * Changes the state of this BCycle to reflect that it has been checked out.
   * 
   * @throws IllegalStateException when called on a bike that is already checked out.
   */
  public void checkout() throws IllegalStateException {
    // TODO:
    // 14. Complete the implementation of this method
    if (isCheckedOut == true) {

      throw new IllegalStateException("ERROR: This bike has been already checked out!");

    }

    isCheckedOut = true;

  }


  /**
   * Returns a string representation of this BCycle object. The returned string must have the
   * following format: "bike " speed + " @ " + cadence + "rpm, " + id + " (checked out: " +
   * isCheckedOut + ")"
   * 
   * @Return a String representation of this BCycle object
   */
  @Override
  public String toString() {
    // TODO:
    // 15. Complete the implementation of this method. Notice carefully that the cyclingCadence
    // is a private data field with no getter method defined in the super class Bike.
    // Think of using the toString() method implemented in the super class Bike

    String bikeMessage = super.toString() + ", " + id + " (checked out: " + isCheckedOut + ")";

    return bikeMessage;

  }

  // MAKE SURE TO SAVE YOUR SOURCE FILE (Ctrl/Cmd + S) before submitting it to Gradescope

}
