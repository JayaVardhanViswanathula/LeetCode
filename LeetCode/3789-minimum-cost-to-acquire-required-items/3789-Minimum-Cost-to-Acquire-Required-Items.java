
class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {

        long both = Math.min(need1, need2);

        long cost = both * Math.min(costBoth, cost1 + cost2);

        if (need1 > need2) {
            cost += (long) (need1 - need2) * Math.min(cost1, costBoth);
        } else {
            cost += (long) (need2 - need1) * Math.min(cost2, costBoth);
        }

        return cost;
    }
}
