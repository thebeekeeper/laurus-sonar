package com.laurus.manualtests;

import org.sonar.api.SonarPlugin;

import java.util.Arrays;
import java.util.List;

/**
 * User: thebeekeeper
 * Date: 1/5/14
 * Time: 3:56 PM
 */
public class ManualTestPlugin extends SonarPlugin {
    @Override
    public List getExtensions() {
        return Arrays.asList(
                ManualTestSensor.class
        );
    }
}
