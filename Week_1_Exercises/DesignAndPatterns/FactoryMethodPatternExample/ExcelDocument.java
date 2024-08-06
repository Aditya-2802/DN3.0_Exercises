package FactoryMethodPatternExample;

// ExcelDocument.java
public class ExcelDocument implements Document {
    @Override
    public void open() {
        System.out.println("Opening Excel document.");
    }
}
