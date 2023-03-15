// package Projects.Project 1;

import java.util.*;

public class SubstitutionCipher extends Cipher {

    List<Character> randChars = new ArrayList<>();

    public SubstitutionCipher(long key) {
        super(key);
        // TODO Auto-generated constructor stub

        Random rand = new Random(key);
        for (int i = 255; i > 0; i--) {
            // char aChar = (char) rand.nextInt(i);
            // if (Character.isLetter(aChar)) {
            randChars.add((char) rand.nextInt(i));
            // }
        }

        System.out.print(randChars);
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
        System.out.print("Cipher Text" + ciphertext);
        return ciphertext;
    }

    public List<Character> decrypt(List<Character> encryptedText) {
        List<Character> originaltext = new ArrayList<>();

        for (char aChar : encryptedText) {
            // System.out.print(aChar);
            // get index of aChar
            // retrieve what's at (char) index(achar)
            // add it to originaltext
            originaltext.add((char) randChars.indexOf(aChar));
        }
        System.out.print("OG Text" + originaltext);
        return originaltext;
    }

    public static void main(String[] args) {
        // for (int i = 0; i < 256; i++) {
        // System.out.println((char) i);
        // }
        List<Character> clearSample = new ArrayList() {
            {
                add('H');
                add('e');
                add('l');
                add('l');
            }
        };
        List<Character> encryptSample = new ArrayList() {
            {
                add('?');
                add('k');
                add('U');
                add('U');
            }
        };
        SubstitutionCipher s = new SubstitutionCipher(20);
        s.encrypt(clearSample);
        s.decrypt(encryptSample);
    }
}
