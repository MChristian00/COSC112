// package Projects.Project 1;

import java.util.*;

public class SubstitutionCipher extends Cipher {

    List<Character> allChars = new ArrayList<>();
    List<Character> randChars = new ArrayList<>();

    public SubstitutionCipher(long key) {
        super(key);

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

    @Override
    public List<Character> encrypt(List<Character> originalText) {
        List<Character> encryptedText = new ArrayList<>();

        for (char aChar : originalText) {
            encryptedText.add(randChars.get((int) aChar));
        }
        return encryptedText;
    }

    @Override
    public List<Character> decrypt(List<Character> encryptedText) {
        List<Character> originaltext = new ArrayList<>();
        for (char aChar : encryptedText) {
            originaltext.add((char) randChars.indexOf(aChar));
        }
        return originaltext;
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
 * 
 * Can I make Experiments.java going to be ran on it's own or is it to
 * contain an interface? Or is it supposed to be called
 * through the SubsitutionCipher, in that case, it has to be an interface since
 * SC already extends 1 class (Cipher).
 */

/**
 * Code to run:
 * java Crypt Substitution encrypt 17 < og.txt > enc.txt
 * java Crypt Substitution decrypt 17 < enc.txt > og2.txt
 */