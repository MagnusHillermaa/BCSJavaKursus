    public static int closest_neg_to_zero(int a, int b, int c) {
        int closest_to_zero;

        if (a == 0 || b == 0 || c == 0) {
            return 0;
        }
        if (a > 0 && b > 0 && c > 0) {
            closest_to_zero = (a < b) && (a < c) ? a : b;
            closest_to_zero = (closest_to_zero < c) ? closest_to_zero : c;
        }else{
            closest_to_zero = ((1 / (float) a * -1) > (1 / (float) b * -1) && (1 / (float) a * -1) > (1 / (float) c * -1)) ? a : b;
            closest_to_zero = ((1 / (float) closest_to_zero * -1) > (1 / (float) c * -1)) ? closest_to_zero : c;
        }

        return closest_to_zero;
    }