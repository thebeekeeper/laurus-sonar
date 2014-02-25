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
import org.sonar.api.Properties;
import org.sonar.api.Property;
import org.sonar.api.SonarPlugin;

import java.util.Arrays;
import java.util.List;

/**
 * User: thebeekeeper
 * Date: 1/5/14
 * Time: 3:56 PM
 */

@Properties({
        @Property(key = "laurus.manualtestcmd", name = "Manual Test Query Executor", defaultValue = ""),
        @Property(key = "laurus.manualtestquery", name = "Manual Test Query", defaultValue = ""),
        @Property(key = "laurus.manualtestregex", name = "Manual Test Query Regex", defaultValue = "")
})

public class ManualTestPlugin extends SonarPlugin {
    @Override
    public List getExtensions() {
        return Arrays.asList(
                ManualTestSensor.class
        );
    }
}
