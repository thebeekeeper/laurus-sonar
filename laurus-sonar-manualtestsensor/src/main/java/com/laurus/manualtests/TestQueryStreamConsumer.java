package com.laurus.manualtests;

import org.sonar.api.utils.command.StreamConsumer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: thebeekeeper
 * Date: 1/6/14
 * Time: 1:01 PM
 */
public class TestQueryStreamConsumer implements StreamConsumer {

    public TestQueryStreamConsumer(String regexPattern) {
       this.regexPattern = regexPattern;
    }

    @Override
    public void consumeLine(String s) {
        Pattern p = Pattern.compile(regexPattern);
        Matcher m = p.matcher(s);
        if(m.find()) {
            String stringCount = m.group(2);
            testCount = Integer.parseInt(stringCount);
        }
    }

    public int getTestCount() {
        return testCount;
    }

    private int testCount = 0;
    private String regexPattern;
}
