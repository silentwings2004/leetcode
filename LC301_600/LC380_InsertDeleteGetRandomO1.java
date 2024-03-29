package LC301_600;
import java.util.*;
public class LC380_InsertDeleteGetRandomO1 {
    /**
     * Implement the RandomizedSet class:
     *
     * RandomizedSet() Initializes the RandomizedSet object.
     * bool insert(int val) Inserts an item val into the set if not present. Returns true if the item was not present,
     * false otherwise.
     * bool remove(int val) Removes an item val from the set if present. Returns true if the item was present, false
     * otherwise.
     * int getRandom() Returns a random element from the current set of elements (it's guaranteed that at least one
     * element exists when this method is called). Each element must have the same probability of being returned.
     *
     * Input
     * ["RandomizedSet", "insert", "remove", "insert", "getRandom", "remove", "insert", "getRandom"]
     * [[], [1], [2], [2], [], [1], [2], []]
     * Output
     * [null, true, false, true, 2, true, false, 2]
     *
     * Constraints:
     *
     * -2^31 <= val <= 2^31 - 1
     * At most 10^5 calls will be made to insert, remove, and getRandom.
     * There will be at least one element in the data structure when getRandom is called.
     *
     *
     * Follow up: Could you implement the functions of the class with each function works in average O(1) time?
     */
    /** Initialize your data structure here. */
    // time = O(1), space = O(n)
    Random random;
    List<Integer> nums;
    HashMap<Integer, Integer> map;
    public LC380_InsertDeleteGetRandomO1() {
        random = new Random();
        nums = new ArrayList<>();
        map = new HashMap<>();
    }

    public boolean insert(int val) {
        if (map.containsKey(val)) return false;
        nums.add(val);
        map.put(val, nums.size() - 1);
        return true;
    }

    public boolean remove(int val) {
        if (!map.containsKey(val)) return false;
        int a = map.get(val), b = nums.size() - 1;
        if (a == b) {
            map.remove(val);
            nums.remove(b);
        } else {
            nums.set(a, nums.get(b));
            nums.remove(b);
            map.put(nums.get(a), a);
            map.remove(val);
        }
        return true;
    }

    public int getRandom() {
        int idx = random.nextInt(nums.size());
        return nums.get(idx);
    }
}
/**
 * pool里每个元素都是unique的，distinct的
 * O(1)时间getRandom()
 * idx = rand()
 * a b c d e f g
 * 要用一个数组, O(1)时间访问任意index
 * remove O(1)? => 保持数组，里面都是连续的
 * use swap(j, x) => 与最后一个交换，这样删掉最后一个即可
 * 必须知道要删除的元素宰哪 => use HashMap
 * update HashMap
 */