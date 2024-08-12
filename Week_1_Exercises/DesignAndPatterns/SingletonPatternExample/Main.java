package SingletonPatternExample;

public class Main {
    public static void main(String[] args) {
        Logger logger_1 = Logger.getInstance();
        Logger logger_2 = Logger.getInstance();

        logger_1.log(LogLevel.WARNING, "This is a warning log statement");
        logger_2.log(LogLevel.ERROR, "This is a error log statement");
        logger_1.log(LogLevel.INFO, "This is a Info Log statement");

        if (logger_1.hashCode() == logger_2.hashCode()) {
            System.out.println("Same instance is being used");
        } else {
            System.out.println("Different Instances are being used");
        }
    }
}
