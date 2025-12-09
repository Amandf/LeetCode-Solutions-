public class FoodRatings {

    private static class FoodItem {
        String name;
        int rating;
        FoodItem(String n, int r) {
            name = n;
            rating = r;
        }
    }

    // Comparator: higher rating first. If tie, lexicographically smaller name first.
    private static class FoodComparator implements Comparator<FoodItem> {
        public int compare(FoodItem a, FoodItem b) {
            if (a.rating != b.rating) {
                return Integer.compare(b.rating, a.rating);
            }
            return a.name.compareTo(b.name);
        }
    }

    // Maps a food name -> its cuisine
    private Map<String, String> foodToCuisine;
    // Maps a food name -> its current rating
    private Map<String, Integer> foodRating;

    // For each cuisine, a TreeSet of foods in that cuisine, ordered by rating & name
    private Map<String, TreeSet<FoodItem>> cuisineFoods;

    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        foodToCuisine = new HashMap<>();
        foodRating    = new HashMap<>();
        cuisineFoods  = new HashMap<>();

        for (int i = 0; i < foods.length; i++) {
            String food = foods[i];
            String cuisine = cuisines[i];
            int rating = ratings[i];

            foodToCuisine.put(food, cuisine);
            foodRating.put(food, rating);

            cuisineFoods.computeIfAbsent(cuisine, c -> new TreeSet<>(new FoodComparator()));
            cuisineFoods.get(cuisine).add(new FoodItem(food, rating));
        }
    }

    public void changeRating(String food, int newRating) {
        String cuisine = foodToCuisine.get(food);
        int oldRating   = foodRating.get(food);

        TreeSet<FoodItem> ts = cuisineFoods.get(cuisine);
        // Remove the old entry: need an object equal to old (rating, name)
        ts.remove(new FoodItem(food, oldRating));

        // Update rating
        foodRating.put(food, newRating);

        // Add new entry
        ts.add(new FoodItem(food, newRating));
    }

    public String highestRated(String cuisine) {
        TreeSet<FoodItem> ts = cuisineFoods.get(cuisine);
        // first element in the TreeSet is the one with highest rating (and smallest name if tie)
        return ts.first().name;
    }
}
