package com.laurus.manualtests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.utils.command.StreamConsumer;

/**
 * User: thebeekeeper
 * Date: 1/6/14
 * Time: 1:03 PM
 */
public class ErrorStreamConsumer implements StreamConsumer {
    @Override
    public void consumeLine(String s) {
       logger.error("Error running manual test query: " + s);
    }

    private Logger logger = LoggerFactory.getLogger(getClass());
}
