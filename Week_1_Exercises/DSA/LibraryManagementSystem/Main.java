package LibraryManagementSystem;

public class Main {
    public static void main(String[] args) {
        Library library = new Library(5);

        // Adding books
        library.addBook(new Book("B001", "The Great Gatsby", "F. Scott Fitzgerald"));
        library.addBook(new Book("B002", "Moby Dick", "Herman Melville"));
        library.addBook(new Book("B003", "1984", "George Orwell"));
        library.addBook(new Book("B004", "To Kill a Mockingbird", "Harper Lee"));
        library.addBook(new Book("B005", "The Catcher in the Rye", "J.D. Salinger"));

        // Linear Search
        System.out.println("Linear Search for '1984':");
        Book linearSearchResult = library.linearSearchByTitle("1984");
        System.out.println(linearSearchResult != null ? linearSearchResult : "Book not found");

        // Binary Search
        System.out.println("\nBinary Search for '1984':");
        Book binarySearchResult = library.binarySearchByTitle("1984");
        System.out.println(binarySearchResult != null ? binarySearchResult : "Book not found");
    }
}
