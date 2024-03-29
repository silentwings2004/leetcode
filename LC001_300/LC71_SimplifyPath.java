package LC001_300;
import java.util.*;
public class LC71_SimplifyPath {
    /**
     * Given a string path, which is an absolute path (starting with a slash '/') to a file or directory in a
     * Unix-style file system, convert it to the simplified canonical path.
     *
     * In a Unix-style file system, a period '.' refers to the current directory, a double period '..' refers to the
     * directory up a level, and any multiple consecutive slashes (i.e. '//') are treated as a single slash '/'.
     * For this problem, any other format of periods such as '...' are treated as file/directory names.
     *
     * The canonical path should have the following format:
     *
     * The path starts with a single slash '/'.
     * Any two directories are separated by a single slash '/'.
     * The path does not end with a trailing '/'.
     * The path only contains the directories on the path from the root directory to the target file or directory
     * (i.e., no period '.' or double period '..')
     * Return the simplified canonical path.
     *
     * Input: path = "/home//foo/"
     * Output: "/home/foo"
     *
     * Input: path = "/a/./b/../../c/"
     * Output: "/c"
     *
     * Constraints:
     *
     * 1 <= path.length <= 3000
     * path consists of English letters, digits, period '.', slash '/' or '_'.
     * path is a valid absolute Unix path.
     *
     * @param path
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public String simplifyPath(String path) {
        // corner case
        if (path == null || path.length() == 0) return "";

        Stack<String> stack = new Stack<>();
        String[] strs = path.split("/+");

        for (String s : strs) {
            if (s.equals("..")) {
                if (!stack.isEmpty()) stack.pop();
            } else {
                if (s.length() > 0 && !s.equals(".")) stack.push(s);
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.insert(0, stack.pop()).insert(0, "/");
        }
        return sb.length() == 0 ? "/" : sb.toString();
    }

    // S2
    // time = O(n), space = O(n)
    final int N = 3010;
    public String simplifyPath2(String path) {
        char[] stk = new char[N];
        int tt = 0;
        StringBuilder sb = new StringBuilder();

        if (path.charAt(path.length() - 1) != '/') path += '/';
        for (char c : path.toCharArray()) {
            if (c != '/') sb.append(c);
            else {
                String s = sb.toString();
                if (s.equals("..")) {
                    while (tt > 0 && stk[tt] != '/') tt--;
                    if (tt > 0) tt--; // remove '/'
                } else if (!s.equals(".") && !s.equals("")) {
                    stk[++tt] = '/';
                    for (int i = 0; i < s.length(); i++) stk[++tt] = s.charAt(i);
                }
                sb = new StringBuilder();
            }
        }
        if (tt == 0) stk[++tt] = '/';
        sb = new StringBuilder();
        for (int i = 1; i <= tt; i++) sb.append(stk[i]);
        return sb.toString();
    }
}
/**
 * 先预处理字符串，将所有用"/"分割的字符串放置于一个字符串数组里。
 * 再根据每个字符串的具体含义，模拟一个栈的操作：遇到".."就退栈，遇到"."和""就不入栈，其他的时候都入栈。
 * eg. "/a/./b/../../c/"  => ["","a",".","b","..","..","c"]，第一个会分出空string来，要特别注意！
 * 最后把栈里面的所有字符串用"/"再连接起来。
 */