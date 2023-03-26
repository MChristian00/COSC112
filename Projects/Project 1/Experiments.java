import java.io.PrintWriter;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Experiments {

  static int[][] table = new int[256][256];
  static List<Character> unShuffledChars = new ArrayList<>();
  static List<Character> shuffledChars = new ArrayList<>();
  static int callCounter = 0;
  static int currentValue;

  public static List<Character> generateUnShuffledArray() {
    for (int i = 0; i < 256; i++) {
      unShuffledChars.add((char) i);
    }
    return unShuffledChars;
  }

  /**
   * @param key
   * @return shuffledChars an array of randomly placed characters
   */
  public static List<Character> generateShuffledArray(long key) {
    Random rand = new Random(key);
    List<Character> copyArray = unShuffledChars;
    while (copyArray.size() != 0) {
      int randCharIndex = rand.nextInt(copyArray.size());
      char randChar = (char) randCharIndex;
      shuffledChars.add(randChar);
      copyArray.remove(randCharIndex);
    }
    return shuffledChars;
  }

  /**
   * @param key
   */
  public static void populateArrays(long key) {
    generateUnShuffledArray();
    generateShuffledArray(key);
  }

  /**
   * @param key
   */
  public static void findFreqOfCharMapping(long key) {
    if (callCounter == 0) {
      generateUnShuffledArray();
    }
    callCounter++;
    for (int i = 0; i < unShuffledChars.size() - 1; i++) {
      currentValue = table[(int) unShuffledChars.get(i)][(int) shuffledChars.get(i)];
      currentValue = (currentValue / 100) + 1;
      table[(int) unShuffledChars.get(i)][(int) shuffledChars.get(i)] = (currentValue * 100) / callCounter;
    }
  }

  public static void addHeaders() {
    for (int i = 0; i < 256; i++) {
      for (int j = 0; j < 256; j++) {
        if (i == 0 && j == 0) {
          continue;
        }
        if (i == 0 || j == 1) {
          table[i][j] = j - 1;
          table[j][i] = j - 1;
        }
      }
    }
  }

  /**
   * 
   */
  public static void writeToFile() throws IOException {
    File f = new File("./Table.txt");
    PrintWriter pw = new PrintWriter(f);

    if (!f.exists()) {
      f.createNewFile();
    }

    for (int i = 0; i < 256; i++) {
      for (int j = 0; j < 256; j++) {
        String s = String.format("%-7d", table[i][j]);
        pw.write(s);
      }
      pw.write("\n");
    }
    pw.close();
  }

  public static void description() {
    String summary = """
        I first generated two Linked lists, unShuffledChars and shuffledChars using a call to their corresponding methods from the constructor. I assigned all the first 256 characters to the unShuffledChars (starting at 0 to 255). To generate the shuffled characters array (shuffledChars), I used a user-defined random key to generate a random number that I used to get a character from a copy list (copyArray) of the unShuffledChars. I then added the character to unShuffledChars and deleted it from the copyArray. This generates two equal-sized lists of characters that I use in encryption and decryption.

        To encrypt, I took the given list of characters and looped through the characters getting their corresponding index from the unShuffledChars and then using that index to get the random character at the same index from shuffledChars. For decrypting, I got the index of each character in the encryption text and used it to find the original character at the same index from the unShuffledChars list.
          """;

    System.out.print(summary);
  }

  public static void main(String[] args) throws IOException {
    Experiments.addHeaders();
    for (int i = 1; i <= 50; i++) {
      Experiments.populateArrays(i); // Problem with this part
      Experiments.findFreqOfCharMapping(i);
    }
    Experiments.writeToFile();
    Experiments.description();
  }
}