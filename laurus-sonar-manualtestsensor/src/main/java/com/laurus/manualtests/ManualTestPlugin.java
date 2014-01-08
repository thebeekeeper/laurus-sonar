package com.laurus.manualtests;

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
