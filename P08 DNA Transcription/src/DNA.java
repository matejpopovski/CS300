//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P08 DNA
// Course: CS 300 Spring 2022
//
// Author: Matej Popovski
// Email: popovski@wisc.edu
// Lecturer: Mouna Kacem
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: NONE
// Online Sources: NONE
//
///////////////////////////////////////////////////////////////////////////////

public class DNA extends Object {
  protected static String[][] mRNAtoProteinMap =
      {{"UUU", "F"}, {"UUC", "F"}, {"UUA", "L"}, {"UUG", "L"}, {"UCU", "S"}, {"UCC", "S"},
          {"UCA", "S"}, {"UCG", "S"}, {"UAU", "Y"}, {"UAC", "Y"}, {"UAA", "STOP"}, {"UAG", "STOP"},
          {"UGU", "C"}, {"UGC", "C"}, {"UGA", "STOP"}, {"UGG", "W"}, {"CUU", "L"}, {"CUC", "L"},
          {"CUA", "L"}, {"CUG", "L"}, {"CCU", "P"}, {"CCC", "P"}, {"CCA", "P"}, {"CCG", "P"},
          {"CAU", "H"}, {"CAC", "H"}, {"CAA", "Q"}, {"CAG", "Q"}, {"CGU", "R"}, {"CGC", "R"},
          {"CGA", "R"}, {"CGG", "R"}, {"AUU", "I"}, {"AUC", "I"}, {"AUA", "I"}, {"AUG", "M"},
          {"ACU", "T"}, {"ACC", "T"}, {"ACA", "T"}, {"ACG", "T"}, {"AAU", "N"}, {"AAC", "N"},
          {"AAA", "K"}, {"AAG", "K"}, {"AGU", "S"}, {"AGC", "S"}, {"AGA", "R"}, {"AGG", "R"},
          {"GUU", "V"}, {"GUC", "V"}, {"GUA", "V"}, {"GUG", "V"}, {"GCU", "A"}, {"GCC", "A"},
          {"GCA", "A"}, {"GCG", "A"}, {"GAU", "D"}, {"GAC", "D"}, {"GAA", "E"}, {"GAG", "E"},
          {"GGU", "G"}, {"GGC", "G"}, {"GGA", "G"}, {"GGG", "G"}};
  protected LinkedQueue<Character> DNA;

  public DNA(String sequence) {
    DNA = new LinkedQueue<>();
    for (char ch : sequence.toCharArray()) {
      DNA.enqueue(ch);
    }
  }

  public LinkedQueue<Character> transcribeDNA() {
    LinkedQueue<Character> mRNA = new LinkedQueue<>();
    for (int i = 0; i < DNA.size(); i++) {
      char ch = DNA.dequeue();
      switch (ch) {
        case 'A':
          mRNA.enqueue('U');
          break;
        case 'T':
          mRNA.enqueue('A');
          break;
        case 'C':
          mRNA.enqueue('G');
          break;
        case 'G':
          mRNA.enqueue('C');
          break;
      }
      DNA.enqueue(ch);
    }
    return mRNA;
  }

  public LinkedQueue<String> translateDNA() {
    return mRNATranslate​(transcribeDNA());
  }
  
  public LinkedQueue<String> mRNATranslate​(LinkedQueue<Character> mRNA) {
    LinkedQueue<String> aminoAcid = new LinkedQueue<>();
    outer: while (mRNA.size() > 2) {
      String rna = "" + mRNA.dequeue() + mRNA.dequeue() + mRNA.dequeue();
      for (int i = 0; i < mRNAtoProteinMap.length; i++) {
        if (rna.equals(mRNAtoProteinMap[i][0])) {
          if (mRNAtoProteinMap[i][1].equals("STOP")) {
            break outer;
          }
          aminoAcid.enqueue(mRNAtoProteinMap[i][1]);
        }
      }
    }
    return aminoAcid;
  }

}
