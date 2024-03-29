package LC1201_1500;
import java.util.*;
public class LC1357_ApplyDiscountEverynOrders {
    /**
     * There is a supermarket that is frequented by many customers. The products sold at the supermarket are represented
     * as two parallel integer arrays products and prices, where the ith product has an ID of products[i] and a price of
     * prices[i].
     *
     * When a customer is paying, their bill is represented as two parallel integer arrays product and amount, where the
     * jth product they purchased has an ID of product[j], and amount[j] is how much of the product they bought. Their
     * subtotal is calculated as the sum of each amount[j] * (price of the jth product).
     *
     * The supermarket decided to have a sale. Every nth customer paying for their groceries will be given a percentage
     * discount. The discount amount is given by discount, where they will be given discount percent off their subtotal.
     * More formally, if their subtotal is bill, then they would actually pay bill * ((100 - discount) / 100).
     *
     * Implement the Cashier class:
     *
     * Cashier(int n, int discount, int[] products, int[] prices) Initializes the object with n, the discount, and the
     * products and their prices.
     * double getBill(int[] product, int[] amount) Returns the final total of the bill with the discount applied (if any).
     * Answers within 10-5 of the actual value will be accepted.
     *
     * Input
     * ["Cashier","getBill","getBill","getBill","getBill","getBill","getBill","getBill"]
     * [[3,50,[1,2,3,4,5,6,7],[100,200,300,400,300,200,100]],[[1,2],[1,2]],[[3,7],[10,10]],[[1,2,3,4,5,6,7],
     * [1,1,1,1,1,1,1]],[[4],[10]],[[7,3],[10,10]],[[7,5,3,1,6,4,2],[10,10,10,9,9,9,7]],[[2,3,5],[5,3,2]]]
     * Output
     * [null,500.0,4000.0,800.0,4000.0,4000.0,7350.0,2500.0]
     *
     * Constraints:
     *
     * 1 <= n <= 10^4
     * 0 <= discount <= 100
     * 1 <= products.length <= 200
     * prices.length == products.length
     * 1 <= products[i] <= 200
     * 1 <= prices[i] <= 1000
     * The elements in products are unique.
     * 1 <= product.length <= products.length
     * amount.length == product.length
     * product[j] exists in products.
     * 1 <= amount[j] <= 1000
     * The elements of product are unique.
     * At most 1000 calls will be made to getBill.
     * Answers within 10-5 of the actual value will be accepted.
     * @param n
     * @param discount
     * @param products
     * @param prices
     */
    // time = O(n), space = O(n)
    int k, n, d;
    HashMap<Integer, Integer> map;
    public LC1357_ApplyDiscountEverynOrders(int n, int discount, int[] products, int[] prices) {
        k = 0;
        d = discount;
        this.n = n;
        map = new HashMap<>();
        int m = products.length;
        for (int i = 0; i < m; i++) map.put(products[i], prices[i]);
    }

    public double getBill(int[] product, int[] amount) {
        k++;
        int m = product.length;
        double s = 0;
        for (int i = 0; i < m; i++) {
            int p = product[i];
            s += map.get(p) * amount[i];
        }
        if (k % n == 0) s = s - (d * s) / 100;
        return s;
    }
}