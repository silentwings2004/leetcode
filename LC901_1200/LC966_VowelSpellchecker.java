package LC901_1200;
import java.util.*;
public class LC966_VowelSpellchecker {
    /**
     * Given a wordlist, we want to implement a spellchecker that converts a query word into a correct word.
     *
     * For a given query word, the spell checker handles two categories of spelling mistakes:
     *
     * Capitalization: If the query matches a word in the wordlist (case-insensitive), then the query word is returned
     * with the same case as the case in the wordlist.
     * Example: wordlist = ["yellow"], query = "YellOw": correct = "yellow"
     * Example: wordlist = ["Yellow"], query = "yellow": correct = "Yellow"
     * Example: wordlist = ["yellow"], query = "yellow": correct = "yellow"
     * Vowel Errors: If after replacing the vowels ('a', 'e', 'i', 'o', 'u') of the query word with any vowel
     * individually, it matches a word in the wordlist (case-insensitive), then the query word is returned with the
     * same case as the match in the wordlist.
     *
     * Example: wordlist = ["YellOw"], query = "yollow": correct = "YellOw"
     * Example: wordlist = ["YellOw"], query = "yeellow": correct = "" (no match)
     * Example: wordlist = ["YellOw"], query = "yllw": correct = "" (no match)
     * In addition, the spell checker operates under the following precedence rules:
     *
     * When the query exactly matches a word in the wordlist (case-sensitive), you should return the same word back.
     * When the query matches a word up to capitlization, you should return the first such match in the wordlist.
     * When the query matches a word up to vowel errors, you should return the first such match in the wordlist.
     * If the query has no matches in the wordlist, you should return the empty string.
     * Given some queries, return a list of words answer, where answer[i] is the correct word for query = queries[i].
     *
     * Input: wordlist = ["KiTe","kite","hare","Hare"], queries = ["kite","Kite","KiTe","Hare","HARE","Hear","hear",
     * "keti","keet","keto"]
     * Output: ["kite","KiTe","KiTe","Hare","hare","","","KiTe","","KiTe"]
     *
     * Note:
     *
     * 1 <= wordlist.length <= 5000
     * 1 <= queries.length <= 5000
     * 1 <= wordlist[i].length <= 7
     * 1 <= queries[i].length <= 7
     * All strings in wordlist and queries consist only of english letters.
     * @param wordlist
     * @param queries
     * @return
     */
    // time = O(m + n), space = O(m + n)
    public String[] spellchecker(String[] wordlist, String[] queries) {
        // corner case
        if (wordlist == null || wordlist.length == 0 || queries == null || queries.length == 0) return new String[0];

        String[] res = new String[queries.length];
        HashSet<String> set = new HashSet<>(Arrays.asList(wordlist));
        HashMap<String, String> lowMap = new HashMap();
        HashMap<String, String> vowelMap = new HashMap<>();

        for (String word : wordlist) {
            String wl = word.toLowerCase();
            if (!lowMap.containsKey(wl)) lowMap.put(wl, word);
            String deVowel = devowl(wl);
            if (!vowelMap.containsKey(deVowel)) vowelMap.put(deVowel, word);
        }

        for (int i = 0; i < queries.length; i++) {
            String query = queries[i];
            if (set.contains(query)) res[i] = query;
            else if (lowMap.containsKey(query.toLowerCase())) res[i] = lowMap.get(query.toLowerCase());
            else res[i] = vowelMap.getOrDefault(devowl(query.toLowerCase()), "");
        }
        return res;
    }

    private String devowl(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            sb.append(isVowel(c) ? '*' : c);
        }
        return sb.toString();
    }

    private boolean isVowel(char c) {
        if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c =='u') return true;
        return false;
    }

    // S2
    // time = O((m + n) * k), space = O(n * k)
    public String[] spellchecker2(String[] wordlist, String[] queries) {
        HashMap<String, String> s1 = new HashMap<>();
        HashMap<String, String> s2 = new HashMap<>();
        HashMap<String, String> s3 = new HashMap<>();

        for (String w : wordlist) {
            if (!s1.containsKey(w)) s1.put(w, w);
            if (!s2.containsKey(filter1(w))) s2.put(filter1(w), w);
            if (!s3.containsKey(filter2(w))) s3.put(filter2(w), w);
        }

        int m = queries.length;
        String[] res = new String[m];
        for (int i = 0; i < m; i++) {
            String q = queries[i];
            if (s1.containsKey(q)) res[i] = q;
            else if (s2.containsKey(filter1(q))) res[i] = s2.get(filter1(q));
            else if (s3.containsKey(filter2(q))) res[i] = s3.get(filter2(q));
            else res[i] = "";
        }
        return res;
    }

    private String filter1(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) sb.append(Character.toLowerCase(c));
        return sb.toString();
    }

    private String filter2(String s) {
        s = filter1(s);
        StringBuilder sb = new StringBuilder();
        String vowel = "aeiou";
        for (char c : s.toCharArray()) {
            if (vowel.indexOf(c) != -1) sb.append('a');
            else sb.append(c);
        }
        return sb.toString();
    }
}
