package test2;
///////////////////////// CUMULATIVE QUIZ FILE HEADER //////////////////////////
//Full Name: Matej Popovski
//Campus ID: 9083541632
//WiscEmail: popovski@wisc.edu
//   (TODO: fill this out)
////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////
//
//BE CAREFUL!! This file contains TWO classes. You will need to make
//changes to BOTH classes for full credit.
//
////////////////////////////////////////////////////////////////////////////////

//MAKE SURE TO SAVE your source file before uploading it to gradescope.

import java.util.ArrayList;

/**
* This class contains various Skeins (loosely wound lengths of yarn) and offers utility methods for 
* analyzing the contents of the CraftBin.
* 
* NOTE: You may NOT add any additional data fields to this class. You may add private
* helper methods if needed.
*/
public class CraftBin {
// instance fields
private ArrayList<Skein> yarn;
private final int WEIGHT_LIMIT;

/**
* Creates a new CraftBin object
* 
* @param weightLimit the maximum weight in grams that this CraftBin can hold
*/
public CraftBin(int weightLimit) {
// TODO: complete the implementation to initialize all data fields
  this.yarn = new ArrayList <Skein>();

WEIGHT_LIMIT = weightLimit; // change this
}

/**
* Adds a Skein to this craft bin, if it will fit.
* 
* @param toAdd the Skein to add to the CraftBin
* @throws IllegalStateException if adding the Skein will cause the CraftBin to exceed its
* weight limit
*/
public void addSkein(Skein toAdd) throws IllegalStateException{
// TODO: complete the implementation per the Javadoc comment above
  if(toAdd.getWeight()+getTotalWeight()>WEIGHT_LIMIT){
    throw new IllegalStateException ("ERROR: CraftBin exceeds its weight limit");
  }
  
  
  this.yarn.add(toAdd);
}

/**
* Calculates the current total weight of all skeins in this craft bin
* 
* @return the current total weight of all Skeins in this CraftBin
*/
public int getTotalWeight() {
// TODO: complete the implementation per the Javadoc comment above
  int temp = 0;
  for(int i =0 ; i < yarn.size(); i++) {
    temp = temp + yarn.get(i).getWeight();
  }
  
  
return temp; // change this
}

/**
* Removes and returns the skein with the SMALLEST weight currently in the craft bin.
* 
* For full credit, this method MUST call the sortBin() method defined below.
* 
* @return the lowest-weight skein from the craft bin
* @throws IllegalStateException if the craft bin is empty
*/
public Skein removeLightest() throws IllegalStateException{
// TODO: complete the implementation per the Javadoc comment above
  if(yarn.isEmpty()) {
    throw new IllegalStateException("ERROR: Array is empty.");
  }
  this.sortBin();
  Skein temp2 = yarn.get(0);
  yarn.remove(0);
  
  
return temp2; // change this
}

//MAKE SURE TO SAVE your source file before uploading it to gradescope.

/**
* Private helper method; uses Collections.sort to sort the craft bin's contents. After sorting,
* skeins are sorted by increasing weight.
* TODO: UNCOMMENT this method AFTER implementing the Comparable interface in the Skein class.
*/
private void sortBin() {
  java.util.Collections.sort(this.yarn);
}

////////////////////////////////////////////////////////////////////////////////
/////////   PROVIDED TESTER METHODS. MODIFY AS YOU LIKE! NOT GRADED.   /////////
/////////         GRADED PORTION CONTINUES BELOW MAIN METHOD.          /////////
////////////////////////////////////////////////////////////////////////////////

@Override
public String toString() {
String result = "";
for (Skein s : this.yarn) {
result += s.getManufacturer()+": "+s.getWeight()+"g\n";
}
return result.substring(0,result.length()-1);
}

public static void main(String[] args) {
CraftBin c = new CraftBin(600);

Skein s1 = new Skein("Red Heart", 250);
Skein s2 = new Skein("Knit Picks", 175);
Skein s3 = new Skein("Rowan", 250);

try {
c.addSkein(s1);
} catch (Exception e) {
System.out.println("Error: adding Red Heart should not throw an exception!");
}
try {
c.addSkein(s2);
} catch (Exception e) {
System.out.println("Error: adding Knit Picks should not throw an exception!");
}
try {
c.addSkein(s3); 
System.out.println("Error: adding Rowan did not throw an exception, but should have.");
} catch (IllegalStateException e) {
// good, do nothing
} catch (Exception e) {
System.out.println("Error: adding Rowan threw the wrong kind of exception!");
}

System.out.println(c); // should not include Rowan

System.out.println("Lightest: "+c.removeLightest().getManufacturer());  // should be Knit Picks
System.out.println(c); // should be only Red Heart

try {
c.addSkein(s3);  // should work
} catch (Exception e) {
System.out.println("Error: adding Rowan after removing Knit Picks should have worked.");
}
System.out.println(c);
}

////////////////////////////////////////////////////////////////////////////////
/////////   END PROVIDED TESTER METHODS. CONTINUE IN THE CLASS BELOW.   ////////
////////////////////////////////////////////////////////////////////////////////

}

//MAKE SURE TO SAVE your source file before uploading it to gradescope.

/**
* This class models Skein objects which can be added to a CraftBin. Each object contains
* its manufacturer and weight in grams, and is sortable by increasing weight.
* 
* TODO: modify this class to implement the Comparable interface, such that a Skein can
* be compared to another Skein WITHOUT type casting or instanceof.
*/
class Skein implements Comparable <Skein>{
private String manufacturer;
private int weightInGrams;


/**
* Create a new Skein object
* 
* @param manufacturer
* @param weightInGrams
*/
public Skein(String manufacturer, int weightInGrams) throws IllegalStateException {
// if the weight in grams provided is 0 or negative, throw an IllegalStateException
  if(weightInGrams == 0 || weightInGrams < 0) {
    throw new IllegalStateException("ERROR: Weight is zero or negative");
  }
  
  
this.manufacturer = manufacturer;
this.weightInGrams = weightInGrams;
// TODO: complete the implementation to initialize all data fields and throw exceptions as
// detailed above
}

// TODO: add any methods required by the Comparable interface
public int compareTo(Skein skein) {
  
  if(this.weightInGrams > skein.getWeight()) {
    return 1;
  }
  if(this.weightInGrams < skein.getWeight()) {
    return -1;
  }
  return 0;
  
  
}
// Skeins are compared with respect to their weights; a skein with weight 500 is "greater than"
// a skein with weight 75. Two Skeins are equal if their weights are equal.

////////////////////////////////////////////////////////////////////////////////
////////// ACCESSOR METHODS BELOW THIS LINE DO NOT NEED TO BE MODIFIED /////////
////////////////////////////////////////////////////////////////////////////////

public String getManufacturer() { return this.manufacturer; }
public int getWeight() { return this.weightInGrams; }

//MAKE SURE TO SAVE your source file before uploading it to gradescope.
}