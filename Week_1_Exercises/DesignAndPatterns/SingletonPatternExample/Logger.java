package SingletonPatternExample;

import java.util.Date;

enum LogLevel {
    TRACE(1),
    DEBUG(2),
    INFO(3),
    WARNING(4),
    ERROR(5);

    final int level;

    LogLevel(int level) {
        this.level = level;
    }
}

class Logger {
    private static Logger instance;
    private LogLevel logLevel;

    // constructor
    private Logger() {
        logLevel = LogLevel.INFO;
    }

    public static Logger getInstance() {
        if (instance == null) {
            System.out.println("instance is null");
            instance = new Logger();
            return instance;
        }

        return instance;
    }

    public void setLogLevel(LogLevel level) {
        logLevel = level;
    }

    public void log(LogLevel level, String message) {
        if (logLevel.ordinal() <= level.ordinal()) {
            String logMessage = "[" + new Date() + "] [" + level + "] " + message;
            System.out.println(logMessage);
        }
    }

}