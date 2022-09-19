package framework.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class LoggerUtils {

    private LoggerUtils() {}

    private static final Logger logger = LogManager.getLogger("FileLogger");

    public static Logger getLogger() {
        return logger;
    }

    public static void info(String log ){
        logger.info(log);
    }

    public static void warn(String log){
        logger.warn(log);
    }

    public static void error(String log){
        logger.error(log);
    }

}