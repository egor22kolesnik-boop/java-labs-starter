package edu.course.lab03;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NoDiscountTest {

    private final DiscountPolicy policy = new NoDiscount();

    @Test
    void returnsOriginalPrice() {
        assertEquals(100.0, policy.apply(100.0), 1e-9);
    }

    @Test
    void zeroPriceIsAllowed() {
        assertEquals(0.0, policy.apply(0.0), 1e-9);
    }

    @Test
    void negativePriceIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> policy.apply(-1.0));
    }
}

class PercentDiscountTest {

    @Test
    void twentyPercentOf100Gives80() {
        DiscountPolicy policy = new PercentDiscount(20);
        assertEquals(80.0, policy.apply(100.0), 1e-9);
    }

    @Test
    void fiftyPercentOf200Gives100() {
        DiscountPolicy policy = new PercentDiscount(50);
        assertEquals(100.0, policy.apply(200.0), 1e-9);
    }

    @Test
    void zeroPercentKeepsPrice() {
        DiscountPolicy policy = new PercentDiscount(0);
        assertEquals(100.0, policy.apply(100.0), 1e-9);
    }

    @Test
    void hundredPercentGivesZero() {
        DiscountPolicy policy = new PercentDiscount(100);
        assertEquals(0.0, policy.apply(100.0), 1e-9);
    }

    @Test
    void negativePercentIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> new PercentDiscount(-1));
    }

    @Test
    void percentAbove100IsRejected() {
        assertThrows(IllegalArgumentException.class, () -> new PercentDiscount(101));
    }

    @Test
    void negativePriceIsRejected() {
        DiscountPolicy policy = new PercentDiscount(20);
        assertThrows(IllegalArgumentException.class, () -> policy.apply(-1.0));
    }
}

class PriceCalculatorTest {

    @Test
    void usesNoDiscountPolicy() {
        PriceCalculator calculator = new PriceCalculator(new NoDiscount());
        assertEquals(100.0, calculator.finalPrice(100.0), 1e-9);
    }

    @Test
    void usesPercentDiscountPolicy() {
        PriceCalculator calculator = new PriceCalculator(new PercentDiscount(20));
        assertEquals(80.0, calculator.finalPrice(100.0), 1e-9);
    }

    // тест заменяемости: один и тот же клиентский код, разные реализации
    @Test
    void policyCanBeReplacedWithoutChangingCalculator() {
        double price = 200.0;

        PriceCalculator withoutDiscount = new PriceCalculator(new NoDiscount());
        PriceCalculator withDiscount = new PriceCalculator(new PercentDiscount(25));

        assertEquals(200.0, withoutDiscount.finalPrice(price), 1e-9);
        assertEquals(150.0, withDiscount.finalPrice(price), 1e-9);
    }

    // заменяемость через интерфейсный тип: переменная одна, объекты разные
    @Test
    void calculatorWorksWithAnyDiscountPolicy() {
        DiscountPolicy[] policies = {new NoDiscount(), new PercentDiscount(10)};
        double[] expected = {100.0, 90.0};

        for (int i = 0; i < policies.length; i++) {
            PriceCalculator calculator = new PriceCalculator(policies[i]);
            assertEquals(expected[i], calculator.finalPrice(100.0), 1e-9);
        }
    }
}

class FeatureProcessorTest {
    @Test
    void strategyCanBeReplacedWithoutChangingProcessor() {
        double[] vector = {2, 4, 6};

        NormalizationStrategy strategy = new MinMaxNormalization();
        assertArrayEquals(new double[] {0.0, 0.5, 1.0},
                new FeatureProcessor(strategy).process(vector), 1e-9);

        strategy = new MeanCenteringNormalization();
        assertArrayEquals(new double[] {-2.0, 0.0, 2.0},
                new FeatureProcessor(strategy).process(vector), 1e-9);
    }

    @Test
    void rejectsNullStrategy() {
        assertThrows(IllegalArgumentException.class, () -> new FeatureProcessor(null));
    }
}

class MeanCenteringNormalizationTest {
    private final NormalizationStrategy strategy = new MeanCenteringNormalization();

    @Test
    void subtractsMean() {
        assertArrayEquals(new double[] {-1.0, 0.0, 1.0},
                strategy.normalize(new double[] {1, 2, 3}), 1e-9);
    }

    @Test
    void doesNotModifyInput() {
        double[] input = {10, 20};
        strategy.normalize(input);
        assertArrayEquals(new double[] {10, 20}, input, 0.0);
    }

    @Test
    void rejectsNullVector() {
        assertThrows(IllegalArgumentException.class, () -> strategy.normalize(null));
    }
}

class MinMaxNormalizationTest {
    private final NormalizationStrategy strategy = new MinMaxNormalization();

    @Test
    void scalesToZeroOne() {
        assertArrayEquals(new double[] {0.0, 0.5, 1.0},
                strategy.normalize(new double[] {2, 4, 6}), 1e-9);
    }

    @Test
    void constantVectorGivesZeros() {
        assertArrayEquals(new double[] {0.0, 0.0},
                strategy.normalize(new double[] {5, 5}), 1e-9);
    }

    @Test
    void doesNotModifyInput() {
        double[] input = {2, 4, 6};
        strategy.normalize(input);
        assertArrayEquals(new double[] {2, 4, 6}, input, 0.0);
    }

    @Test
    void rejectsEmptyVector() {
        assertThrows(IllegalArgumentException.class, () -> strategy.normalize(new double[0]));
    }
}