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
