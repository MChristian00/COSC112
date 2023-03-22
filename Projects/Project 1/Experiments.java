import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Experiments {

  static int[][] exp = new int[256][256];
  static int callCounter = 0;
  static int currentValue;
  /**
     * @param both encrypt and decrypt list of chars
     * @return mapped freqs
     */
    public static void findFreqOfChar(List<Character> ogList, List<Character> encList) {
      callCounter++;
        for(int i = 0; i < ogList.size(); i++){
            currentValue = exp[(int) ogList.get(i)][(int) encList.get(i)];
            currentValue = (currentValue/100) + 1;
            exp[(int) ogList.get(i)][(int) encList.get(i)] = (currentValue*100)/callCounter;
        }
    }


    public static void populateTable() {
        for(int i=0; i<256; i++){
          for(int j=0; j<256; j++){
            if(i == 0 && j == 0){
              continue;
            }
            if(i==0){
                exp[i][j] = j-1;
                exp[j][i] = j-1;
            }
            }
          }
          
        }

    public static void writeToFile(List<Character> ogList, List<Character> encList) throws IOException{
        File f = new File("./Table.txt");
        if(!f.exists()) {
          f.createNewFile();
        }

        FileWriter fw = new FileWriter(f.getAbsoluteFile());

        BufferedWriter bw = new BufferedWriter(fw);
        
        
          for(int i=0; i<256; i++){
            for(int j=0; j<256; j++){
              bw.write(exp[i][j]);
              
              }
            }

      bw.close();
      fw.close();

    }

  

  public static void main(String[] args) throws IOException {
    List<Character> ogText = new ArrayList<>() {
            {
                add('');
                add('');

            }
        };
    List<Character> encText = new ArrayList<>() {
            {
                add('');
                add('');
            }
        };

      Experiments.populateTable();
      Experiments.findFreqOfChar(ogText, encText);
      Experiments.findFreqOfChar(ogText, encText);
      Experiments.findFreqOfChar(ogText, encText);
      Experiments.writeToFile(ogText, encText);
      for(int i=0; i<40; i++){
          for(int j=0; j<40; j++){
            System.out.printf("%d ",Experiments.exp[i][j]);
          } System.out.println();}  
  }
}