package FinancialForecasting;

import java.util.HashMap;
import java.util.Map;

public class FinancialForecasting {

    // Memoization map to store computed values
    private static Map<Integer, Double> memo = new HashMap<>();

    // Method to calculate future value recursively with memoization
    public static double calculateFutureValue(double initialValue, double growthRate, int years) {
        // Base case
        if (years == 0) {
            return initialValue;
        }
        // Check if the value is already computed
        if (memo.containsKey(years)) {
            return memo.get(years);
        }
        // Recursive case
        double result = calculateFutureValue(initialValue, growthRate, years - 1) * (1 + growthRate);
        // Store the computed value in the memoization map
        memo.put(years, result);
        return result;
    }

    public static void main(String[] args) {
        double initialValue = 1000; // Initial value
        double growthRate = 0.05; // 5% annual growth rate
        int years = 10; // Number of years into the future

        double futureValue = calculateFutureValue(initialValue, growthRate, years);
        System.out.println("The predicted future value after " + years + " years is: " + futureValue);
    }
}
