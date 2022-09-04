package LC2101_2400;
import java.util.*;
public class LC2349_DesignaNumberContainerSystem {
    /**
     * Design a number container system that can do the following:
     *
     * Insert or Replace a number at the given index in the system.
     * Return the smallest index for the given number in the system.
     * Implement the NumberContainers class:
     *
     * NumberContainers() Initializes the number container system.
     * void change(int index, int number) Fills the container at index with the number. If there is already a number at
     * that index, replace it.
     * int find(int number) Returns the smallest index for the given number, or -1 if there is no index that is filled
     * by number in the system.
     *
     * Input
     * ["NumberContainers", "find", "change", "change", "change", "change", "find", "change", "find"]
     * [[], [10], [2, 10], [1, 10], [3, 10], [5, 10], [10], [1, 20], [10]]
     * Output
     * [null, -1, null, null, null, null, 1, null, 2]
     *
     * Constraints:
     *
     * 1 <= index, number <= 10^9
     * At most 105 calls will be made in total to change and find.
     */
    HashMap<Integer, Integer> idx2Num;
    HashMap<Integer, TreeSet<Integer>> num2Idx;
    public LC2349_DesignaNumberContainerSystem() {
        idx2Num = new HashMap<>();
        num2Idx = new HashMap<>();
    }
    // time = O(logn), space = O(n)
    public void change(int index, int number) {
        if (idx2Num.containsKey(index)) {
            int num = idx2Num.get(index);
            num2Idx.get(num).remove(index);
            if (num2Idx.get(num).size() == 0) num2Idx.remove(num);
        }
        idx2Num.put(index, number);
        num2Idx.putIfAbsent(number, new TreeSet<>());
        num2Idx.get(number).add(index);
    }
    // time = O(n), space = O(logn)
    public int find(int number) {
        if (!num2Idx.containsKey(number)) return -1;
        return num2Idx.get(number).first();
    }
}
