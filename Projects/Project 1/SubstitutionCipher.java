import java.util.*;

public class SubstitutionCipher extends Cipher {

    List<Character> unShuffledChars = new ArrayList<>();
    List<Character> shuffledChars = new ArrayList<>();
    int index;

    public SubstitutionCipher(long key) {
        super(key);
        generateUnShuffledChars();
        generateShuffledChars(key);
        generateUnShuffledChars();
    }

    public void generateUnShuffledChars() {
        for (int i = 0; i < 256; i++) {
            unShuffledChars.add((char) i);
        }
    }

    /**
     * @param key
     * @return shuffledChars an array of randomly placed characters
     */
    public void generateShuffledChars(long key) {
        Random rand = new Random(key);
        List<Character> copyArray = unShuffledChars;
        while (copyArray.size() != 0) {
            int randCharIndex = rand.nextInt(copyArray.size());
            char randChar = copyArray.get(randCharIndex);
            shuffledChars.add(randChar);
            copyArray.remove(copyArray.indexOf(randChar));
        }
    }

    @Override
    public List<Character> encrypt(List<Character> originalText) {

        List<Character> encryptedText = new ArrayList<>();
        for (char aChar : originalText) {
            index = unShuffledChars.indexOf(aChar);
            encryptedText.add(shuffledChars.get(index));
        }
        return encryptedText;
    }

    @Override
    public List<Character> decrypt(List<Character> encryptedText) {
        List<Character> originaltext = new ArrayList<>();
        for (char aChar : encryptedText) {
            index = shuffledChars.indexOf(aChar);
            originaltext.add(unShuffledChars.get(index));
        }
        return originaltext;
    }
}