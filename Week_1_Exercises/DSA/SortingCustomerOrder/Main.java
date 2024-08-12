package SortingCustomerOrder;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Order[] orders = {
                new Order("O001", "Alice", 250.50),
                new Order("O002", "Bob", 150.75),
                new Order("O003", "Charlie", 300.00),
                new Order("O004", "David", 100.25),
                new Order("O005", "Eve", 200.10)
        };

        // Bubble Sort
        Order[] bubbleSortedOrders = Arrays.copyOf(orders, orders.length);
        BubbleSort.bubbleSort(bubbleSortedOrders);
        System.out.println("Bubble Sorted Orders:");
        for (Order order : bubbleSortedOrders) {
            System.out.println(order);
        }

        // Quick Sort
        Order[] quickSortedOrders = Arrays.copyOf(orders, orders.length);
        QuickSort.quickSort(quickSortedOrders, 0, quickSortedOrders.length - 1);
        System.out.println("Quick Sorted Orders:");
        for (Order order : quickSortedOrders) {
            System.out.println(order);
        }
    }
}
