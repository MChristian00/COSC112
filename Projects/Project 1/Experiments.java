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
      char randChar = (char) rand.nextInt(copyArray.size());
      shuffledChars.add(randChar);
      copyArray.remove(randChar);
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
  public static void findFreqOfChar(long key) {
    generateUnShuffledArray();
    callCounter++;
    for (int i = 0; i < unShuffledChars.size(); i++) {
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
        if (i == 0) {
          table[i][j] = j - 1;
          table[j][i] = j - 1;
        }
      }
    }
  }

  public static void writeToFile() throws IOException {
    File f = new File("./Table.txt");
    PrintWriter pw = new PrintWriter(f);

    if (!f.exists()) {
      f.createNewFile();
    }

    for (int i = 0; i < 256; i++) {
      for (int j = 0; j < 256; j++) {
        pw.write(table[i][j] + "     ");
      }
      pw.write("\n");
    }
    pw.close();
  }

  public static void main(String[] args) throws IOException {
    // Experiments a = new Experiments(3);
    Experiments.populateArrays(12);
    Experiments.addHeaders();
    Experiments.findFreqOfChar(12);
    Experiments.findFreqOfChar(12);
    // Experiments.findFreqOfChar(5);
    Experiments.writeToFile();
    for (int i = 0; i < 256; i++) {
      for (int j = 0; j < 256; j++) {
        System.out.printf("%d ", Experiments.table[i][j]);
      }
      System.out.println();
    }
  }
}