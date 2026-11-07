package edu.course.lab03;

public class PriceCalculator {
    private final DiscountPolicy policy;

    public PriceCalculator(DiscountPolicy policy) {
        if (policy == null) {
            throw new IllegalArgumentException("Политика скидки не задана");
        }
        this.policy = policy;
    }

    public double finalPrice(double price) {
        return policy.apply(price);
    }
}