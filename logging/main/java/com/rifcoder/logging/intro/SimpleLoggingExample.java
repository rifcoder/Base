package com.rifcoder.logging.intro;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Sergey.Savchuk@db.com
 */
public class SimpleLoggingExample {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(SimpleLoggingExample.class);
        logger.debug("Hello world.");

        // print internal state
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        StatusPrinter.print(lc);
    }
}
