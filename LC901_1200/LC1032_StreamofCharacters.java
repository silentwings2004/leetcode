package LC901_1200;

public class LC1032_StreamofCharacters {
    /**
     * Design an algorithm that accepts a stream of characters and checks if a suffix of these characters is a string of
     * a given array of strings words.
     *
     * For example, if words = ["abc", "xyz"] and the stream added the four characters (one by one) 'a', 'x', 'y', and
     * 'z', your algorithm should detect that the suffix "xyz" of the characters "axyz" matches "xyz" from words.
     *
     * Implement the StreamChecker class:
     *
     * StreamChecker(String[] words) Initializes the object with the strings array words.
     * boolean query(char letter) Accepts a new character from the stream and returns true if any non-empty suffix from
     * the stream forms a word that is in words.
     *
     * Input
     * ["StreamChecker", "query", "query", "query", "query", "query", "query", "query", "query", "query", "query",
     * "query", "query"]
     * [[["cd", "f", "kl"]], ["a"], ["b"], ["c"], ["d"], ["e"], ["f"], ["g"], ["h"], ["i"], ["j"], ["k"], ["l"]]
     * Output
     * [null, false, false, false, true, false, true, false, false, false, false, false, true]
     *
     * Constraints:
     *
     * 1 <= words.length <= 2000
     * 1 <= words[i].length <= 2000
     * words[i] consists of lowercase English letters.
     * letter is a lowercase English letter.
     * At most 4 * 104 calls will be made to query.
     * @param words
     */
    // time = O(n * m), space = O(n * m)  n: number of input words, m: word length
    TrieNode root;
    StringBuilder sb;
    public LC1032_StreamofCharacters(String[] words) {
        root = new TrieNode();
        sb = new StringBuilder();
        for (String word : words) insert(word);
    }

    public boolean query(char letter) {
        sb.append(letter);
        return find();
    }

    private void insert(String word) { // 反着建树！
        TrieNode node = root;
        int n = word.length();
        for (int i = n - 1; i >= 0; i--) {
            char c = word.charAt(i);
            if (node.next[c - 'a'] == null) {
                node.next[c - 'a'] = new TrieNode();
            }
            node = node.next[c - 'a'];
        }
        node.isEnd = true;
    }

    private boolean find() { // 反过来查！
        int i = sb.length() - 1;
        TrieNode node = root;
        while (i >= 0) {
            if (node.next[sb.charAt(i) - 'a'] != null) {
                node = node.next[sb.charAt(i) - 'a'];
                if (node.isEnd) return true;
                i--;
            } else return false;
        }
        return false;
    }

    private class TrieNode {
        private TrieNode[] next;
        private boolean isEnd;
        public TrieNode() {
            this.next = new TrieNode[26];
            this.isEnd = false;
        }
    }
}
/**
 * 从后往前建立trie
 */
