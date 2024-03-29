package LC1501_1800;
import java.util.*;
public class LC1570_DotProductofTwoSparseVectors {
    /**
     * Given two sparse vectors, compute their dot product.
     *
     * Implement class SparseVector:
     *
     * SparseVector(nums) Initializes the object with the vector nums
     * dotProduct(vec) Compute the dot product between the instance of SparseVector and vec
     * A sparse vector is a vector that has mostly zero values, you should store the sparse vector efficiently
     * and compute the dot product between two SparseVector.
     *
     * Follow up: What if only one of the vectors is sparse?
     *
     * Input: nums1 = [1,0,0,2,3], nums2 = [0,3,0,4,0]
     * Output: 8
     *
     * Input: nums1 = [0,1,0,0,2,0,0], nums2 = [1,0,0,0,3,0,4]
     * Output: 6
     *
     * Constraints:
     *
     * n == nums1.length == nums2.length
     * 1 <= n <= 10^5
     * 0 <= nums1[i], nums2[i] <= 100
     *
     * @param nums
     */
    // time = O(n), space = O(k) k: the number of non-zero elements
    private HashMap<Integer, Integer> map;
    LC1570_DotProductofTwoSparseVectors(int[] nums) {
        map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                map.put(i, nums[i]);
            }
        }
    }

    // Return the dotProduct of two sparse vectors
    public int dotProduct(SparseVector vec) {
        if (vec.map.size() < this.map.size()) return vec.dotProduct(this);
        int res = 0;
        for (int key : this.map.keySet()) {
            if (vec.map.containsKey(key)) {
                res += this.map.get(key) * vec.map.get(key);
            }
        }
        return res;
    }

    // S2
    // time = O(n), space = O(L)
    class SparseVector {
        List<int[]> q;
        SparseVector(int[] nums) {
            q = new ArrayList<>();
            int n = nums.length;
            for (int i = 0; i < n; i++) {
                if (nums[i] != 0) q.add(new int[]{i, nums[i]});
            }
        }

        // Return the dotProduct of two sparse vectors
        public int dotProduct(SparseVector vec) {
            int res = 0;
            int m = q.size(), n = vec.q.size();
            for (int i = 0, j = 0; i < m && j < n;) {
                if (q.get(i)[0] == vec.q.get(j)[0]) {
                    res += q.get(i)[1] * vec.q.get(j)[1];
                    i++;
                    j++;
                } else if (q.get(i)[0] > vec.q.get(j)[0]) j++;
                else i++;
            }
            return res;
        }
    }

// Your SparseVector object will be instantiated and called as such:
// SparseVector v1 = new SparseVector(nums1);
// SparseVector v2 = new SparseVector(nums2);
// int ans = v1.dotProduct(v2);
}
