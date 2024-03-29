package LC301_600;
import java.util.*;
public class LC588_DesignInMemoryFileSystem {
    /**
     * Design a data structure that simulates an in-memory file system.
     *
     * Implement the FileSystem class:
     *
     * FileSystem() Initializes the object of the system.
     * List<String> ls(String path)
     * If path is a file path, returns a list that only contains this file's name.
     * If path is a directory path, returns the list of file and directory names in this directory.
     * The answer should in lexicographic order.
     * void mkdir(String path) Makes a new directory according to the given path. The given directory path does not
     * exist. If the middle directories in the path do not exist, you should create them as well.
     * void addContentToFile(String filePath, String content)
     * If filePath does not exist, creates that file containing given content.
     * If filePath already exists, appends the given content to original content.
     * String readContentFromFile(String filePath) Returns the content in the file at filePath.
     *
     * Input
     * ["FileSystem", "ls", "mkdir", "addContentToFile", "ls", "readContentFromFile"]
     * [[], ["/"], ["/a/b/c"], ["/a/b/c/d", "hello"], ["/"], ["/a/b/c/d"]]
     * Output
     * [null, [], null, null, ["a"], "hello"]
     *
     * Constraints:
     *
     * 1 <= path.length, filePath.length <= 100
     * path and filePath are absolute paths which begin with '/' and do not end with '/' except that the path is just "/".
     * You can assume that all directory names and file names only contain lowercase letters, and the same names will
     * not exist in the same directory.
     * You can assume that all operations will be passed valid parameters, and users will not attempt to retrieve file
     * content or list a directory or file that does not exist.
     * 1 <= content.length <= 50
     * At most 300 calls will be made to ls, mkdir, addContentToFile, and readContentFromFile.
     */
    // S1
    TrieNode root;
    HashMap<String, String> fc; // fileContent
    public LC588_DesignInMemoryFileSystem() {
        root = new TrieNode();
        fc = new HashMap<>();
    }
    // time = O(n), space = O(n)
    public List<String> ls(String path) {
        List<String> res = new ArrayList<>();
        TrieNode node = root;
        String str = "";
        for (int i = 1; i < path.length(); i++) { // 根目录下有个斜杠
            int j = i;
            while (j < path.length() && path.charAt(j) != '/') j++;
            str = path.substring(i, j);
            node = node.map.get(str);
            i = j;
        }
        if (node.isFile) res.add(str);
        else {
            for (String x : node.map.keySet()) res.add(x);
        }
        return res;
    }
    // time = O(n), space = O(n)
    public void mkdir(String path) {
        TrieNode node = root;
        for (int i = 1; i < path.length(); i++) {
            int j = i;
            while (j < path.length() && path.charAt(j) != '/') j++;
            String str = path.substring(i, j);
            if (!node.map.containsKey(str)) node.map.put(str, new TrieNode());
            node = node.map.get(str);
            i = j;
        }
    }
    // time = O(n), space = O(n)
    public void addContentToFile(String filePath, String content) {
        TrieNode node = root;
        for (int i = 1; i < filePath.length(); i++) {
            int j = i;
            while (j < filePath.length() && filePath.charAt(j) != '/') j++;
            String str = filePath.substring(i, j);
            if (!node.map.containsKey(str)) node.map.put(str, new TrieNode());
            node = node.map.get(str);
            i = j;
        }
        node.isFile = true;
        fc.put(filePath, fc.getOrDefault(filePath, "") + content);
    }
    // time = O(1), space = O(n)
    public String readContentFromFile(String filePath) {
        return fc.get(filePath);
    }

    private class TrieNode {
        private TreeMap<String, TrieNode> map;
        private boolean isFile;
        public TrieNode() {
            this.map = new TreeMap<>();
            this.isFile = false;
        }
    }

    // S2
    class FileSystem {
        HashMap<String, String> fm; // {path -> content}
        HashMap<String, TreeSet<String>> dm; //{path -> directories + files}
        public FileSystem() {
            fm = new HashMap<>();
            dm = new HashMap<>();
        }

        public List<String> ls(String path) {
            if (fm.containsKey(path)) {
                String[] strs = path.split("/");
                List<String> res = new ArrayList<>();
                res.add(strs[strs.length - 1]);
                return res;
            }
            return new ArrayList<>(dm.getOrDefault(path, new TreeSet<>()));
        }

        public void mkdir(String path) {
            String[] strs = path.split("/");
            String s = "";
            dm.putIfAbsent("/", new TreeSet<>());
            dm.get("/").add(strs[1]); // strs[0]是个空字符，所以一个字符要跳过单独处理
            for (int i = 1; i + 1 < strs.length; i++) {
                s += "/" + strs[i];
                dm.putIfAbsent(s, new TreeSet<>());
                dm.get(s).add(strs[i + 1]);
            }
        }

        public void addContentToFile(String filePath, String content) {
            fm.put(filePath, fm.getOrDefault(filePath, "") + content);
            mkdir(filePath);
        }

        public String readContentFromFile(String filePath) {
            return fm.get(filePath);
        }
    }
}
/**
 * 字典树结构
 * 充分利用字典树的特点
 * 额外标记是文件而不是文件夹
 * abc/xyz/w.cpp
 * abc/def
 * abc/def/ghi
 */