import java.util.*;

public class Main {

  static int[][] exp = new int[256][256];

  /**
     * @param both encrypt and decrypt list of chars
     * @return mapped freqs
     */
    public static void findFreqOfChar(List<Character> ofList, List<Character> encList) {
        for(int i = 0; i < ofList.size(); i++){
            exp[(int) ofList.get(i)][(int) encList.get(i)] += 1;
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

  

  public static void main(String[] args) {
    List<Character> ogText = new ArrayList() {
            {
                add('');
                add('');

            }
        };
    List<Character> encText = new ArrayList() {
            {
                add(' ');
                add('');
            }
        };

      Main.populateTable();
      Main.findFreqOfChar(ogText, encText);
      Main.findFreqOfChar(ogText, encText);
      for(int i=0; i<256; i++){
          for(int j=0; j<256; j++){
            System.out.printf("%d ",Main.exp[i][j]);
          } System.out.println();}  
  }
}