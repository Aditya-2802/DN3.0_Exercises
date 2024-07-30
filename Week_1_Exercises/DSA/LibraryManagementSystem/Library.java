package LibraryManagementSystem;

import java.util.Arrays;
import java.util.Comparator;

public class Library {
    private Book[] books;
    private int size;

    public Library(int capacity) {
        books = new Book[capacity];
        size = 0;
    }

    public void addBook(Book book) {
        if (size >= books.length) {
            System.out.println("Library is full");
            return;
        }
        books[size++] = book;
    }

    public Book linearSearchByTitle(String title) {
        for (int i = 0; i < size; i++) {
            if (books[i].getTitle().equalsIgnoreCase(title)) {
                return books[i];
            }
        }
        return null;
    }

    public Book binarySearchByTitle(String title) {
        Arrays.sort(books, 0, size, Comparator.comparing(Book::getTitle));

        int left = 0;
        int right = size - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int compareResult = books[mid].getTitle().compareToIgnoreCase(title);

            if (compareResult == 0) {
                return books[mid];
            } else if (compareResult < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }
}
