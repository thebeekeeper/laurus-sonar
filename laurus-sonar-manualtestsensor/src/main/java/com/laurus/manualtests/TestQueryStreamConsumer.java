package com.laurus.manualtests;

/*
    Copyright 2014 Nick Gamroth

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
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
