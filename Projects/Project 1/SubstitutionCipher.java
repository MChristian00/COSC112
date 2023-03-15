// package Projects.Project 1;

import java.util.*;

public class SubstitutionCipher extends Cipher {

    public SubstitutionCipher(long key) {
        super(key);
        // TODO Auto-generated constructor stub
    }

    Random rand = new Random(256);
    List<Character> randChars = new ArrayList<>();

    @Override
    // public List<Character> encrypt(List<Character> cleartext){
    public List<Character> encrypt(List<Character> clearText) {
        // create random array of chars using char(rand number)
        // loop through cleartext and get each charValue(char in cleartext) using
        // foreach and retrieve
        // that value from random array and add it to string decryptedMsg

        for (int i = 255; i > 0; i--) {
            randChars[i] = (char) rand.nextInt(i);
        }

        for (char randChar : clearText) {
            System.out.print(randChar);
        }
        return clearText;
    }
    public List<Character> decrypt(List<Character> encryptedText){
        return encryptedText;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 256; i++) {
            System.out.println((char) i);
        }
        SubstitutionCipher s = new SubstitutionCipher(1);
        s.encrypt(List);
    }
}
