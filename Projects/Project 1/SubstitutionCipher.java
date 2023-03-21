// package Projects.Project 1;

import java.util.*;

public class SubstitutionCipher extends Cipher {
  
    List<Character> charToFreqMap = new ArrayList<>();
    List<Character> allChars = new ArrayList<>();
    List<Character> randChars = new ArrayList<>();

    public SubstitutionCipher(long key) {
        super(key);
        // TODO Auto-generated constructor stub

        for (int i = 0; i < 256; i++) {
            allChars.add((char) i);
        }

        Random rand = new Random(key);
        while (allChars.size() != 0) {
            char randChar = (char) rand.nextInt(allChars.size());
            randChars.add(randChar);
            allChars.remove(randChar);
        }
    }


    /**
     * @param listOfChars
     * @return table of char and their usage frequencies
     */
    // public void findRandFreq(List<Character> clearListOfChars, List<Character>
    // randListOfChars) {
    public void findRandFreq(List<Character> clearListOfChars) {
        // int count = 0;
        for (char aChar : clearListOfChars) {
            int randCharFreq = Collections.frequency(clearListOfChars, aChar);
            System.out.printf("%c  appears %d times \n", aChar, randCharFreq);
        }
    }

    /**
     * @param both encrypt and decrypt list of chars
     * @return mapped freqs
     */
    public void findFreqOfChar(List<Character> clearListOfChars) {
        for (char aChar : clearListOfChars) {
            int randCharFreq = Collections.frequency(clearListOfChars, aChar);
            System.out.printf("%c  is mapped to %c - %d times \n", aChar, randCharFreq);
        }
    }

    @Override
    // public List<Character> encrypt(List<Character> cleartext){
    public List<Character> encrypt(List<Character> clearText) {
        // create random array of chars using char(rand number)
        // loop through cleartext and get each charValue(char in cleartext) using
        // foreach and retrieve
        // that value from random array and add it to string decryptedMsg

        List<Character> ciphertext = new ArrayList<>();

        for (char aChar : clearText) {
            // System.out.print(aChar);
            // get index of aChar
            // retrieve what's at index=index(achar)
            // add it to ciphertext
            ciphertext.add(randChars.get((int) aChar));
        }
        // System.out.print("Cipher Text" + ciphertext);
        return ciphertext;
    }

    public List<Character> decrypt(List<Character> encryptedText) {
        List<Character> originaltext = new ArrayList<>();
        // System.out.println(encryptedText);
        for (char aChar : encryptedText) {
            // System.out.print(aChar);
            // get index of aChar
            // retrieve what's at (char) index(achar)
            // add it to originaltext
            originaltext.add((char) randChars.indexOf(aChar));
        }
        // System.out.print("OG Text" + originaltext);
        return originaltext;
    }

    public static void main(String[] args) {
        // for (int i = 0; i < 256; i++) {
        // System.out.println((char) i);
        // }
        List<Character> clearSample = new ArrayList() {
            {
                add('H');
                add('o');
                add('b');
                add('e');
            }
        };
        SubstitutionCipher s = new SubstitutionCipher(19);
        // SubstitutionCipher c = new SubstitutionCipher(19);
        List<Character> encryptSample = s.encrypt(clearSample);
        System.out.println(s.decrypt(encryptSample));
        // s.findRandFreq(encryptSample);
        // c.findRandFreq(encryptSample);
    }
}

/**
 * Problems: If I don't change the key, and I change the OG Text,
 * if I use the previous cipher Text, then it will return the previous OG Text
 * 
 * Also, some characters are not displayed but using copy-n-paste into my code,
 * works eventho I can't see anything
 * 
 * 
 * ASK PROF: What are we limited to using? Just simple core java, or we can
 * import * libraries for instance the Joiner method that turns list of chars to
 * str. * Imported * from com.google.common.base.Joiner
 */


/**
* Code to run:
* java Crypt Substitution encrypt 17 < og.txt > enc.txt 
* java Crypt Substitution decrypt 17 < enc.txt > og2.txt
*/