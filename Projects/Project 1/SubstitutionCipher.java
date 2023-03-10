package Projects.Project 1;

import java.util.*;

public class SubstitutionCipher extends Cipher{
    
Random rand = new Random(256);
char[] randChars = new char[256];

@Override
public List<Character> encrypt(List<Character> cleartext){
    // create random array of chars using char(rand number)
    // loop through cleartext and get each charValue(char in cleartext) using foreach and retrieve 
    //that value from random array and add it to string decryptedMsg

    for(int i=255; i>0; i--){
        randChars[i] = (char) rand.nextInt(i);
    }
    
    for(char randChar : randChars){
        System.out.print(randChar);
    }

}
public List<Character> decrypt(List<Character> cleartext){}

public static void main(String[] args){
for (int i = 0; i < 256; i ++){
  System.out.println((char) i);
}
}
}
