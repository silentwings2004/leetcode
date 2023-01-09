package LC2101_2400;
import java.util.*;
public class LC2353_DesignaFoodRatingSystem {
    /**
     * Design a food rating system that can do the following:
     *
     * Modify the rating of a food item listed in the system.
     * Return the highest-rated food item for a type of cuisine in the system.
     * Implement the FoodRatings class:
     *
     * FoodRatings(String[] foods, String[] cuisines, int[] ratings) Initializes the system. The food items are
     * described by foods, cuisines and ratings, all of which have a length of n.
     * foods[i] is the name of the ith food,
     * cuisines[i] is the type of cuisine of the ith food, and
     * ratings[i] is the initial rating of the ith food.
     * void changeRating(String food, int newRating) Changes the rating of the food item with the name food.
     * String highestRated(String cuisine) Returns the name of the food item that has the highest rating for the given
     * type of cuisine. If there is a tie, return the item with the lexicographically smaller name.
     * Note that a string x is lexicographically smaller than string y if x comes before y in dictionary order, that is,
     * either x is a prefix of y, or if i is the first position such that x[i] != y[i], then x[i] comes before y[i] in
     * alphabetic order.
     *
     * Input
     * ["FoodRatings", "highestRated", "highestRated", "changeRating", "highestRated", "changeRating", "highestRated"]
     * [[["kimchi", "miso", "sushi", "moussaka", "ramen", "bulgogi"], ["korean", "japanese", "japanese", "greek", "japanese", "korean"], [9, 12, 8, 15, 14, 7]], ["korean"], ["japanese"], ["sushi", 16], ["japanese"], ["ramen", 16], ["japanese"]]
     * Output
     * [null, "kimchi", "ramen", null, "sushi", null, "ramen"]
     *
     * Constraints:
     *
     * 1 <= n <= 2 * 10^4
     * n == foods.length == cuisines.length == ratings.length
     * 1 <= foods[i].length, cuisines[i].length <= 10
     * foods[i], cuisines[i] consist of lowercase English letters.
     * 1 <= ratings[i] <= 10^8
     * All the strings in foods are distinct.
     * food will be the name of a food item in the system across all calls to changeRating.
     * cuisine will be a type of cuisine of at least one food item in the system across all calls to highestRated.
     * At most 2 * 10^4 calls in total will be made to changeRating and highestRated.
     */
    HashMap<String, Integer> f2r;
    HashMap<String, String> f2c;
    HashMap<String, TreeSet<Pair>> c2r;
    public LC2353_DesignaFoodRatingSystem(String[] foods, String[] cuisines, int[] ratings) {
        f2r = new HashMap<>();
        f2c = new HashMap<>();
        c2r = new HashMap<>();

        int n = foods.length;
        for (int i = 0; i < n; i++) {
            f2r.put(foods[i], ratings[i]);
            f2c.put(foods[i], cuisines[i]);
            c2r.putIfAbsent(cuisines[i], new TreeSet<>((o1, o2) -> o1.r != o2.r ? o2.r - o1.r : o1.f.compareTo(o2.f)));
            c2r.get(cuisines[i]).add(new Pair(foods[i], ratings[i]));
        }
    }
    // time = O(logn), space = O(n)
    public void changeRating(String food, int newRating) {
        int oldRating = f2r.get(food);
        f2r.put(food, newRating);
        String cuisine = f2c.get(food);
        c2r.get(cuisine).remove(new Pair(food, oldRating));
        c2r.get(cuisine).add(new Pair(food, newRating));
    }
    // time = O(logn), space = O(n)
    public String highestRated(String cuisine) {
        return c2r.get(cuisine).first().f;
    }

    private class Pair {
        private String f;
        private int r;
        public Pair(String f, int r) {
            this.f = f;
            this.r = r;
        }
    }
}
