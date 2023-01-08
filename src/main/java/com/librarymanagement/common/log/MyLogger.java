package com.librarymanagement.common.log;

import org.apache.log4j.Logger;

public class MyLogger {

    private static Logger logger = Logger.getLogger(MyLogger.class);

    public static void getLogger(String msg) {
        logger.info(msg);
    }

}
