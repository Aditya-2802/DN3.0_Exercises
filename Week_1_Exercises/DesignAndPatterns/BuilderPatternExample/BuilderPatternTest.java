package BuilderPatternExample;

// BuilderPatternTest.java
public class BuilderPatternTest {
    public static void main(String[] args) {
        Computer basicComputer = new Computer.Builder("Intel i5", "8GB")
                .setStorage("256GB SSD")
                .build();

        Computer gamingComputer = new Computer.Builder("Intel i7", "16GB")
                .setStorage("1TB SSD")
                .setGraphicsCard("NVIDIA RTX 3080")
                .setOperatingSystem("Windows 10")
                .build();

        System.out.println("Basic Computer: " + basicComputer);
        System.out.println("Gaming Computer: " + gamingComputer);
    }
}
