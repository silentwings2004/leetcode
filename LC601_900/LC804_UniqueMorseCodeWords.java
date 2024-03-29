package LC601_900;
import java.util.*;
public class LC804_UniqueMorseCodeWords {
    /**
     * International Morse Code defines a standard encoding where each letter is mapped to a series of dots and dashes,
     * as follows:
     *
     * 'a' maps to ".-",
     * 'b' maps to "-...",
     * 'c' maps to "-.-.", and so on.
     * For convenience, the full table for the 26 letters of the English alphabet is given below:
     *
     * [".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.",
     * "...","-","..-","...-",".--","-..-","-.--","--.."]
     * Given an array of strings words where each word can be written as a concatenation of the Morse code of each letter.
     *
     * For example, "cab" can be written as "-.-..--...", which is the concatenation of "-.-.", ".-", and "-...". We
     * will call such a concatenation the transformation of a word.
     * Return the number of different transformations among all words we have.
     *
     * Input: words = ["gin","zen","gig","msg"]
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= words.length <= 100
     * 1 <= words[i].length <= 12
     * words[i] consists of lowercase English letters.
     * @param words
     * @return
     */
    // time = O(n), space = O(n)
    public int uniqueMorseRepresentations(String[] words) {
        String[] dict = new String[]{".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.",
                "---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};

        HashSet<String> set = new HashSet<>();
        for (String word : words) {
            StringBuilder sb = new StringBuilder();
            for (char c : word.toCharArray()) {
                sb.append(dict[c - 'a']);
            }
            set.add(sb.toString());
        }
        return set.size();
    }
}